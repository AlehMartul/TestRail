package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.tools.ReadPropertyTool;
import framework.tools.TestRailApiTools;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

public abstract class BaseTest {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String PROPERTIES = "config.properties";
    private static final String MAIN_URL = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.PROPERTIES)
            .getProperty("mainUrl");

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
        getBrowser().goTo(MAIN_URL);
        AqualityServices.getLogger().info("Opening login page");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(ITestResult result) throws IOException{
        TestRailApiTools.addAttachmentToResult();
        if (result.getStatus() == ITestResult.SUCCESS) {
            TestRailApiTools.addResultToRun("passed");
        } else {
            TestRailApiTools.addResultToRun("failed");
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