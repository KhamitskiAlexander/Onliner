package by.onliner.stepdefinitions;

import by.onliner.config.BaseUrl;
import by.onliner.config.Section;
import by.onliner.pages.CatalogPage;
import by.onliner.utils.DriverActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class CatalogPageSteps {
    private final CatalogPage catalogPage;

    public CatalogPageSteps(CatalogPage catalogPage) {
        this.catalogPage = catalogPage;
    }

    @Given("user open Catalog page on Onliner")
    public void userOpenCatalogPageOnOnliner() {
        catalogPage.openCatalogPage();
        Assertions
                .assertThat(DriverActions.getUrl())
                .as(String.format("Current URL should be: '%s", BaseUrl.CATALOG_ONLINER))
                .contains(BaseUrl.CATALOG_ONLINER);
    }

    @When("user redirects to {string} section")
    public void userRedirectsToSection(final String section) {
        final String endpoint = Section.getEndpoint(section);
        catalogPage.redirectToSection(endpoint);
        Assertions
                .assertThat(DriverActions.getUrl())
                .as(String.format("Current URL should contains next endpoint: '%s'", endpoint))
                .contains(endpoint);
    }
}
