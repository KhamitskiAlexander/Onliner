package by.onliner.hooks;

import java.io.ByteArrayInputStream;

import by.onliner.webdrivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    private static final String IMAGE_NAME = "Page screenshot";
    private static final String IMAGE_TYPE = "image/png";
    private static final String IMAGE_EXT = "png";

    @After(order = 10)
    public void getScreenShotOnFailure(final Scenario scenario) {
        if (scenario.isFailed() && Allure.getLifecycle().getCurrentTestCase().isPresent()) {
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(IMAGE_NAME, IMAGE_TYPE, new ByteArrayInputStream(screenshot), IMAGE_EXT);
            } catch (WebDriverException e) {
                LOGGER.error("Error when take screenshot for ${scenario.getName()} Scenario");
                LOGGER.error(e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void quitWebDriverAfterTest() {
        if (DriverManager.isInitialized()) {
            DriverManager.getInstance().quitDriver();
        }
    }
}

