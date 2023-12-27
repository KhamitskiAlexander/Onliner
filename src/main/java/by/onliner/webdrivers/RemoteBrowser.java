package by.onliner.webdrivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteBrowser implements Browser {
    /**
     * Initializes an instance of {@link RemoteBrowser}.
     */
    public RemoteBrowser() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebDriver getDriver() {
        String nodeURL = "http://127.0.0.1:4444/wd/hub";
        try {
            return new RemoteWebDriver(new URL(nodeURL), getChromeOptions());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets chrome options.
     *
     * @return the chrome options
     */
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }
}

