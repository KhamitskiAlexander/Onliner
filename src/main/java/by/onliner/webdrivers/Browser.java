package by.onliner.webdrivers;

import org.openqa.selenium.WebDriver;

public interface Browser {
    /**
     * Gets a Web Driver instance of a specific browser.
     *
     * @return Web Driver instance.
     */
    WebDriver getDriver();
}
