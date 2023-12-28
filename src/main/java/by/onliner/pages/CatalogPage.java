package by.onliner.pages;

import by.onliner.config.BaseUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogPage.class);
    final String url = BaseUrl.CATALOG_ONLINER;

    public void openCatalogPage() {
        LOGGER.info("Open: '{}'", url);
        getBrowserDriver().get(url);
    }

    public void redirectToSection(final String endpoint) {
        LOGGER.info("Redirect to '{}' with '{}' endpoint", url, endpoint);
        getBrowserDriver().get(url + endpoint);
    }
}
