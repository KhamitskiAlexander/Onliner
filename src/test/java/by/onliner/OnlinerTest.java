package by.onliner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources"},
        tags = "@ui",
        glue = {"by/onliner/stepdefinitions", "by/onliner/hooks"},
        plugin = {"pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json"}
)
public class OnlinerTest extends AbstractTestNGCucumberTests {
}
