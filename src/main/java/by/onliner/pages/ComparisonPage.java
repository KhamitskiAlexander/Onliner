package by.onliner.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import by.onliner.pages.enums.ItemFeature;
import by.onliner.helpers.CollectionHelper;
import by.onliner.utils.DriverActions;
import by.onliner.helpers.IntHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComparisonPage extends BasePage {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComparisonPage.class);

    private final By title = By.xpath("//h1");
    private final By itemName =
            By.xpath("//tr[contains(@class, 'header product')]//span[contains(@class, 'product-summary__caption')]");
    private final By deleteItemButton = By.xpath("//tr[contains(@class, 'header product')]//a[contains(@class, 'product-icon_trash')]");

    public List<String> getItemsForComparing() {
        DriverActions.waitToBeVisible(itemName);
        return DriverActions.getListOfStrings(itemName);
    }

    public List<Integer> getListOfIndicesWithMaxValues(final String itemFeature) {
        //get list of item feature elements
        List<WebElement> itemFeatureElements = getListOfItemFeatureElements(itemFeature);

        //get list of item feature values
        List<Integer> itemFeatureValues = getListOfValuesForItemFeatureElements(itemFeature, itemFeatureElements);

        //return list with indices of max values in item feature values list
        return CollectionHelper.getIndecesOfMaxValuesInList(itemFeatureValues);
    }

    /**
     * @param itemFeature name of item feature
     * @return list of item feature elements
     */
    private List<WebElement> getListOfItemFeatureElements(final String itemFeature) {
        final By columnElement = By.xpath(ItemFeature.getItemFeatureValueXpath(itemFeature));
        DriverActions.waitToBeVisible(columnElement);
        LOGGER.info("Get list of elements for '{}'", itemFeature);
        return DriverActions.getListOfWebElements(columnElement);
    }

    /**
     * @param itemFeatureElements list of item feature elements
     * @return list of values for item feature elements
     */
    private List<Integer> getListOfValuesForItemFeatureElements(final String itemFeature, final List<WebElement> itemFeatureElements) {
        List<Integer> itemFeatureValues = new ArrayList<>();
        for (WebElement element : itemFeatureElements) {
            final int itemFeatureValue = IntHelper.formatItemFeatureText(itemFeature, DriverActions.getText(element));
            itemFeatureValues.add(itemFeatureValue);
        }
        LOGGER.info("Values of '{}' are: '{}'", itemFeature, Arrays.toString(itemFeatureValues.toArray()));
        return  itemFeatureValues;
    }

    /**
     * @param itemFeature name of item feature
     * @return list of column cells for item feature
     */
    public List<WebElement> getListOfCellsInRow(final String itemFeature) {
        final By cellInRow = By.xpath(ItemFeature.getItemFeatureCellXpath(itemFeature));
        DriverActions.waitToBeVisible(cellInRow);
        DriverActions.waitToBeVisible(cellInRow);
        LOGGER.info("Get list of column cells in row for '{}'", itemFeature);
        return DriverActions.getListOfWebElements(cellInRow);
    }

    public void deleteRandomItem() {
        DriverActions.waitToBeVisible(deleteItemButton);
        List<WebElement> items = DriverActions.getListOfWebElements(deleteItemButton);
        Random random = new Random();
        final int expectedListSize = items.size() - 1;
        DriverActions.click(items.get(random.nextInt(expectedListSize)));
        DriverActions.waitUntilElementListHasSize(deleteItemButton, expectedListSize);
    }

    public void hoverToTitle() {
        DriverActions.hoverToElement(title);
    }
}
