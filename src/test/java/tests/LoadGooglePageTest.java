package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class LoadGooglePageTest extends BaseTest {

    @Test
    public void testOpenGooglePage(){
        AqualityServices.getLogger().info("Opening Google main page");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isPageLoaded(), "Main page didn't load");
    }
}
