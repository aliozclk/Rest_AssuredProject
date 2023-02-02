package RestAssureLessons.section7;

import RestAssureLessons.section1.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.remote.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(PayLoad.addBook(isbn,aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();


        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);


    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][] {{"fdjd","65464"},{"hgkhjk","54684"},{"fkhgf","698754"}};
    }
}
