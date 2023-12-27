package by.onliner.stepdefinitions.sharedSteps;

import by.onliner.utils.DriverActions;
import io.cucumber.java.en.When;

public class SharedSteps {

    @When("user open previous page in browser")
    public void userOpenPreviousPageInBrowser() {
        DriverActions.switchToThePreviousPageInBrowser();
    }
}
