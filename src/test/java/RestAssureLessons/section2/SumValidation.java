package RestAssureLessons.section2;

import RestAssureLessons.section1.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses(){
        JsonPath jsonPath = new JsonPath(PayLoad.CoursePrice());
        int numberOfCourses = jsonPath.getInt("courses.size()");
        int sum = 0 ;


        for (int i = 0; i < numberOfCourses; i++) {
            int price = jsonPath.getInt("courses["+i+"].price");
            int numOfCopy = jsonPath.getInt("courses["+i+"].copies");
            int totalAmount = price * numOfCopy;
            System.out.println(totalAmount);
            sum += totalAmount;
        }
        System.out.println(sum);

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseAmount);

    }
}
