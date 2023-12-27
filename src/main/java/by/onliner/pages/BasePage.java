package by.onliner.pages;

import by.onliner.webdrivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public WebDriver getBrowserDriver() {
        return DriverManager.getInstance().getWebDriver();
    }

    /**
     * Initializes an instance of {@link BasePage}.
     */
    public BasePage() {
        PageFactory.initElements(getBrowserDriver(), this);
    }
}