package JiraTest;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GetIssue {
    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        //Login
        SessionFilter session = new SessionFilter();
        String resp = given().relaxedHTTPSValidation()
                .header("Content-Type","application/json")
                .body("{ \"username\": \"alliozcelik\", \"password\": \"ali.1996\" }")
                .log().all().filter(session).when().post("/rest/auth/1/session").then()
                .extract().response().asString();

        String expectedMsg = "This is expected comment here !!!";

        String comment = given().pathParam("id","10104").log().all()
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"body\": \"" +expectedMsg+ "\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then()
                .assertThat().statusCode(201).extract().response().asString();

        JsonPath js = new JsonPath(comment);
        String commentId=js.getString("id");

        String issueDetails =  given().filter(session).pathParam("key","10104")
                .queryParam("fields","comment")
                .log().all()
                .when().get("/rest/api/2/issue/{key}")
                .then().log().all().extract().response().asString();
        System.out.println(issueDetails);

        JsonPath js1 = new JsonPath(issueDetails);
        int commentsCount = js1.get("fields.comment.comments.size()");
        for (int i = 0; i < commentsCount; i++) {
            String commentIdIssue=js1.getString("fields.comment.comments["+i+"].id");
            if(commentIdIssue.equalsIgnoreCase(commentId)){
                String message = js1.get("fields.comment.comments["+i+"].body").toString();
                System.out.println(message);
                Assert.assertEquals(message,expectedMsg);
            }
        }




    }
}
