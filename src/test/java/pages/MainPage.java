package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class MainPage extends BasePage {

    private final static String X_PATH_OF_MAIN_LOGO = "//img[@alt='Google']";
    private ILabel mainLogo = AqualityServices.getElementFactory()
            .getLabel(By.xpath(X_PATH_OF_MAIN_LOGO), "mainLogo");

    public MainPage() {
        super(By.xpath(X_PATH_OF_MAIN_LOGO), "MainPage");
    }


}