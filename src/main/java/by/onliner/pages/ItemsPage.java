package by.onliner.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.onliner.utils.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ItemsPage extends BasePage {
    public static List<String> EXPECTED_ITEMS_FOR_COMPARING = new ArrayList<>();

    private final By itemName = By.xpath("//span[contains(@data-bind, 'full_name')]");
    private final By itemCheckbox =
            By.xpath("//div[contains(@class, 'product')]//label[contains(@class, 'schema-product')]");
    private final By compareButton = By.xpath("//a[contains(@class, 'compare-button__sub_main')]");


    /**
     * Choose some random items on Items page
     * @param number - count of items to choose
     */
    public void chooseSomeItems(final int number) {
        // get a list of all item names on Items page
        DriverActions.waitToBeVisible(itemName);
        List<WebElement> itemNames = DriverActions.getListOfWebElements(itemName);

        // get a list of all item checkboxes on Items page
        DriverActions.waitToBeVisible(itemCheckbox);
        List<WebElement> itemCheckboxes = DriverActions.getListOfWebElements(itemCheckbox);

        //get a list of unique numbers
        List<Integer> listOfUniqueItems = getListOfUniqueNumbers(itemNames.size(), number);

        //tap expected unique item checkboxes by index
        for (Integer index : listOfUniqueItems) {
            DriverActions.jsScrollTo(itemNames.get(index));
            EXPECTED_ITEMS_FOR_COMPARING.add(itemNames.get(index).getText());
            DriverActions.click(itemCheckboxes.get(index));
        }
    }

    /**
     * Provide a list of unique numbers
     * @param range - max value in list with unique numbers
     * @param listCapacity - list size
     * @return a list with unique numbers
     */
    private List<Integer> getListOfUniqueNumbers(final int range, final int listCapacity) {
        List<Integer> listOfUniqueNumbers = new ArrayList<>();
        Random random = new Random();
        while(listOfUniqueNumbers.size() < listCapacity) {
            int randomItemNumber = random.nextInt(range);
            if (!listOfUniqueNumbers.contains(randomItemNumber)) {
                listOfUniqueNumbers.add(randomItemNumber);
            }
        }
        return listOfUniqueNumbers;
    }

    public void clickCompareButton() {
        DriverActions.waitToBeVisible(compareButton);
        DriverActions.click(compareButton);
    }
}
