package RestAssureLessons;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;

public class OAuthTest {
    public static void main(String[] args) throws InterruptedException {

//        System.setProperty("webdriver.chrome.driver","C:\\Users\\alioz\\Downloads\\chromedriver_win32\\chromedriver.exe" );
//        WebDriver driver = new ChromeDriver();
//
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        driver.findElement(By.id("identifierId")).sendKeys("ali41osman24@gmail.com");
//        driver.findElement(By.id("identifierId")).sendKeys(Keys.ENTER);
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("ali.1996");
//        driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(Keys.ENTER);
//        Thread.sleep(2000);
//        String url = driver.getCurrentUrl();

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh42EZaeVo4lwHtfS17TWGu7_wkMMWxaDTJkB8aJ9FEWjzA8bg9LdyHXxaJ5_0N1Fw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";

        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];
        System.out.println(code);


        String accessTokenResp = given().urlEncodingEnabled(false)
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        JsonPath js = new JsonPath(accessTokenResp);
        String accessToken = js.getString("access_token");

        String response = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println(response);
    }
}
