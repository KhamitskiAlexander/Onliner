package by.onliner.stepdefinitions;

import java.util.Arrays;
import java.util.List;

import by.onliner.pages.ComparisonPage;
import by.onliner.pages.enums.Color;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.onliner.pages.ItemsPage.EXPECTED_ITEMS_FOR_COMPARING;

public class ComparisonPageSteps {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComparisonPageSteps.class);
    private final ComparisonPage comparisonPage;
    private List<String> actualItemsOnComparisonPage;

    public ComparisonPageSteps(ComparisonPage comparisonPage) {
        this.comparisonPage = comparisonPage;
    }

    @And("save actual list of items on Comparison page")
    public void setActualItemsOnComparisonPage() {
        actualItemsOnComparisonPage = comparisonPage.getItemsForComparing();
    }

    @Then("user sees {int} items on Comparison page")
    public void userSeesItemsOnComparisonPage(int number) {
        Assertions
                .assertThat(actualItemsOnComparisonPage.size())
                .as(String.format("Expected number of items should be: '%d'", number))
                .isEqualTo(number);
    }

    @Then("user sees expected item names on Comparison page")
    public void userSeesExpectedItemNamesOnComparisonPage() {
        Assertions
                .assertThat(actualItemsOnComparisonPage)
                .as(String.format("Expected item names should be: '%s'", Arrays.toString(actualItemsOnComparisonPage.toArray())))
                .hasSameElementsAs(EXPECTED_ITEMS_FOR_COMPARING);
    }

    @Then("user sees correct comparing results as cells highlighting for next values on Comparison page:")
    public void userSeesCorrectComparingResultsAsCellsHighlightingForNextValuesOnComparisonPage(final DataTable table) {
        List<String> itemFeatures = table.asList();

        for (String itemFeature : itemFeatures) {
            //get lists of cell in row and of indices with max values
            List<WebElement> cellsInRow = comparisonPage.getListOfCellsInRow(itemFeature);
            List<Integer> maxIndices = comparisonPage.getListOfIndicesWithMaxValues(itemFeature);
            //assert cell colors
            for (int i = 0; i < cellsInRow.size(); i++) {
                //hover to title just to avoid any issue with colors
                comparisonPage.hoverToTitle();
                final String actualCssValue = cellsInRow.get(i).getCssValue("background-color");
                //all cells with max values should be highlighted
                //all cells with not max values should have ordinary color
                if (maxIndices.contains(i)) {
                    assertHighlightedYellowColor(actualCssValue, i, itemFeature);
                } else {
                    assertOrdinaryWhiteColor(actualCssValue, i, itemFeature);
                }
            }
        }
    }

    private void assertHighlightedYellowColor(final String actualCssValue, final int i, final String itemFeature) {
        final String expectedCssValue = Color.YELLOW_COLOR.getColor();
        Assertions
                .assertThat(actualCssValue)
                .as(String.format("Actual color '%s' should match expected highlighted yellow color '%s' for cell with index '%d'. Item feature: '%s'",
                        actualCssValue, expectedCssValue, i, itemFeature))
                .isEqualTo(expectedCssValue);
        LOGGER.info("Actual color '{}' should match expected highlighted yellow color '{}' for cell with index '{}'. Item feature: '{}'",
                actualCssValue, expectedCssValue, i, itemFeature);
    }

    private void assertOrdinaryWhiteColor(final String actualCssValue, final int i, final String itemFeature) {
        final String expectedCssValue = Color.WHITE_COLOR.getColor();
        Assertions
                .assertThat(actualCssValue)
                .as(String.format("Actual color '%s' matches expected ordinary white color '%s' for cell with index '%d'. Item feature: '%s'",
                        actualCssValue, expectedCssValue, i, itemFeature))
                .isEqualTo(expectedCssValue);
        LOGGER.info("Actual color '{}' matches expected ordinary white color '{}' for cell with index '{}'. Item feature: '{}'",
                actualCssValue, expectedCssValue, i, itemFeature);
    }

    @When("user delete random item on Comparison page")
    public void userDeleteRandomItemOnComparisonPage() {
        comparisonPage.deleteRandomItem();
    }
}
