package by.onliner.webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeBrowser implements Browser {

    private final ChromeOptions options;

    /**
     * Initializes an instance of {@link ChromeBrowser}.
     */
    public ChromeBrowser() {
        options = buildChromeOptions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    /**
     * Builds the Chrome options object.
     *
     * @return Chrome options object.
     */
    private ChromeOptions buildChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }
}
