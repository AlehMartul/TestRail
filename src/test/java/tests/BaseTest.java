package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.tools.ApiTool;
import framework.tools.OtherTools;
import framework.tools.ReadPropertyTool;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

public abstract class BaseTest {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String PROPERTIES = "config.properties";
    private static final String MAIN_URL = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("mainUrl");
    private static final String URI = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("UriApi");
    String addResult = "add_result";
    String addAttach = "add_attachment_to_result";
    String testId = "55338098";
    String resultId = "2105605";
    String baseUri = String.format(URI, addResult, testId);
    String baseUriForAttachment = String.format(URI, addAttach, resultId);
    String login = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("login");
    String password = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("password");
    String bodyPassed = "{\"status_id\": 1,\"comment\": \"This test passsed\"}";
    String bodyFailed = "{\"status_id\": 5,\"comment\": \"This test Failed\"}";
    ApiTool apiTool = new ApiTool();
    String nameOfScreenshot = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("screenshot");

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
        getBrowser().goTo(MAIN_URL);
        AqualityServices.getLogger().info("Opening login page");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(ITestResult result) throws IOException{
        OtherTools.takeScreenshot(nameOfScreenshot);
        apiTool.postScreenshot1(baseUriForAttachment, login, password, nameOfScreenshot);
        if (result.getStatus() == ITestResult.SUCCESS) {
            apiTool.postResult(baseUri, login, password, bodyPassed);
        } else {
            apiTool.postResult(baseUri, login, password, bodyFailed);
        }
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
            AqualityServices.getLogger().info("Closing browser");
        }
    }

    private Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}