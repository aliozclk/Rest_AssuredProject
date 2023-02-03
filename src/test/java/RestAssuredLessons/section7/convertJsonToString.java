package RestAssuredLessons.section7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class convertJsonToString {

    public static void main(String[] args) throws IOException {
        //use this method Files.readAllBytes(path)
        //and then convert it to string
        //new String(file-byte)

        System.out.println(new String(Files.readAllBytes(Paths.get("C:\\Users\\alioz\\Desktop\\workspace\\InarAcademy\\Rest_AssuredProject\\src\\test\\java\\RestAssureLessons\\section7\\example.json"))));


    }
}
