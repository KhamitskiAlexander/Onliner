package by.onliner.pages;

import by.onliner.config.BaseUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainPage.class);

    public void openMainPage() {
        final String url = BaseUrl.ONLINER;
        LOGGER.info("Open: '{}'", url);
        getBrowserDriver().get(url);
    }
}
