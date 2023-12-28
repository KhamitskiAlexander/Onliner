package by.onliner.webdrivers;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import by.onliner.utils.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class DriverManager {

    private static DriverManager instance;
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    /**
     * Initializes an instance of {@link DriverManager}.
     */
    private DriverManager() {
        initializeDriver();
    }

    /**
     * Initializes the Singleton Driver Manager instance.
     *
     * @return singleton instance.
     */
    public static DriverManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * Initializes the Web Driver instance.
     */
    public void initializeDriver() {
        PropertiesManager propertiesManager = new PropertiesManager();
        String browser = System.getProperty("browser", propertiesManager.get("browser"));
        if (browser.equalsIgnoreCase("chrome")) {
            webDriver = DriverFactory.getDriver(DriverType.CHROME);
        } else if (browser.equalsIgnoreCase("firefox")) {
            webDriver = DriverFactory.getDriver(DriverType.FIREFOX);
        } else if (browser.equalsIgnoreCase("remote")) {
            webDriver = DriverFactory.getDriver(DriverType.REMOTE);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        webDriver.manage().window().maximize();
        setDefaultTimeWaits();
    }

    /**
     * Quits the Web Driver instance.
     */
    public void quitDriver() {
        webDriver.quit();
        webDriver = null;
        instance = null;
    }

    /**
     * Gets the Web Driver instance.
     *
     * @return Web Driver instance.
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Gets the Web Driver Wait instance.
     *
     * @return Web Driver Wait instance.
     */
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    /**
     * Sets the default time waits from environment configuration file.
     */
    public void setDefaultTimeWaits() {
        int explicitTimeWait = 15000;
        setExplicitTimeWait(explicitTimeWait);
    }

    /**
     * Sets explicit time wait.
     *
     * @param explicitTimeWait explicit time wait in seconds.
     */
    public void setExplicitTimeWait(final int explicitTimeWait) {
        webDriverWait = new WebDriverWait(webDriver, TimeUnit.MILLISECONDS.toSeconds(explicitTimeWait));
    }

    /**
     * Checks if driver was initialized.
     *
     * @return true if was initialized, false otherwise.
     */
    public static boolean isInitialized() {
        return Objects.nonNull(instance);
    }
}
