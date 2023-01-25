package RestAssureLessons.section1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicMethods {
    public static void main(String[] args) {


        //add place
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(PayLoad.addPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);//for parsing json
        String placeID = js.getString("place_id");
        System.out.println(placeID);

        //Add place -> Update Place with new address -> get place to validate if new address is present

        //update place

        String newAddress = "Summer Walk, Africa";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        //get place
        String getPlaceResponse =  given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js2 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js2.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress,newAddress);


    }
}
