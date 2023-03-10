package oAuthLessons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
public class serializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setPhoneNumber("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Frontline house");
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        Response res = given().queryParam("key","qaclick123").body(addPlace)
                .when().post("/maps/api/place/add/json").then()
                .assertThat().statusCode(200).extract().response();
        String response = res.asString();
        System.out.println(response);
    }
}
