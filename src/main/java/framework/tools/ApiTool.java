package framework.tools;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.io.File;

public class ApiTool {

    public void postResult(String baseUri, String login, String password, String body) {
        RestAssured.given().contentType(ContentType.JSON).auth().preemptive().basic(login, password)
                .when().body(body)
                .post(baseUri);
    }

    public void postScreenshot(String baseUri, String login, String password, String pathToFile) {
        RestAssured.given().contentType("multipart/form-data").multiPart("attachment", new File(pathToFile))
                .auth().preemptive().basic(login, password)
                .post(baseUri);
    }
}