package JiraTest;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AddAttachment {

    public static void main(String[] args) {


        RestAssured.baseURI = "http://localhost:8080";

        //Login
        SessionFilter session = new SessionFilter();
        String resp = given().header("Content-Type","application/json")
                .body("{ \"username\": \"alliozcelik\", \"password\": \"ali.1996\" }")
                .log().all().filter(session).when().post("/rest/auth/1/session").then()
                .extract().response().asString();

        //Add Attachment
        given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key","10104")
                .header("Content-Type","multipart/form-data")
                .multiPart("file" ,new File("C:\\Users\\alioz\\Desktop\\workspace\\InarAcademy\\Rest_AssuredProject\\src\\test\\java\\JiraTest\\jira.txt")).when()
                .post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);


    }
}
