package by.onliner.stepdefinitions;

import by.onliner.config.BaseUrl;
import by.onliner.pages.MainPage;
import by.onliner.utils.DriverActions;
import io.cucumber.java.en.Given;
import org.assertj.core.api.Assertions;

public class MainPageSteps {
    private final MainPage mainPage;

    public MainPageSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    @Given("user open Onliner main page")
    public void userOpenOnlinerMainPage() {
        mainPage.openMainPage();
        Assertions
                .assertThat(DriverActions.getUrl())
                .as(String.format("Current URL should be: '%s", BaseUrl.ONLINER))
                .contains(BaseUrl.ONLINER + "Unexpected String");
    }
}
