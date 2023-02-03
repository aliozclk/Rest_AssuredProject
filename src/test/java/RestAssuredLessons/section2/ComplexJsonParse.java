package RestAssuredLessons.section2;

import RestAssuredLessons.section1.PayLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(PayLoad.CoursePrice());

        int numberOfCourses = jsonPath.getInt("courses.size()");

        int totalPrice = jsonPath.getInt("dashboard.purchaseAmount");

        int prices = 0;
        for (int i = 0; i < numberOfCourses; i++) {
            prices += jsonPath.getInt("courses["+i+"].price");

        }

        System.out.println("Total Price : " + totalPrice);
        System.out.println("Courses Price : " + prices);


    }
}
