package by.onliner.webdrivers;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class FirefoxBrowser implements Browser {
    private final FirefoxOptions options;

    /**
     * Initializes an instance of {@link ChromeBrowser}.
     */
    public FirefoxBrowser() {
        options = buildFirefoxOptions();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }

    /**
     * Builds the Chrome options object.
     *
     * @return Chrome options object.
     */
    private FirefoxOptions buildFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("window-size=1920,1080");
        return options;
    }
}
