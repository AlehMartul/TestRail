package framework.tools;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class OtherTools {

    public static void takeScreenshot (String filename) throws IOException {
        File scrFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filename));
    }
}
