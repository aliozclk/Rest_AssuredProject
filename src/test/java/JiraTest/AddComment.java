package JiraTest;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.given;

public class AddComment {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080";

        //Login
        SessionFilter session = new SessionFilter();
        String resp = given().header("Content-Type","application/json")
                .body("{ \"username\": \"alliozcelik\", \"password\": \"ali.1996\" }")
                .log().all().filter(session).when().post("/rest/auth/1/session").then()
                .extract().response().asString();


        given().pathParam("id","10104").log().all()
                .header("Content-Type","application/json")
                .body("{\n" +
                "    \"body\": \"This is my first comment!!!\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then()
                .assertThat().statusCode(201);


    }
}
