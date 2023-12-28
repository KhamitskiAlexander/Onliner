package by.onliner.stepdefinitions;

import by.onliner.pages.ItemsPage;
import io.cucumber.java.en.And;

public class ItemsPageSteps {
    private final ItemsPage itemsPage;

    public ItemsPageSteps(ItemsPage itemsPage) {
        this.itemsPage = itemsPage;
    }

    @And("user choose {int} random items on Items page")
    public void userChooseRandomItemsOnItemsPage(final int number) {
        itemsPage.chooseSomeItems(number);
    }

    @And("user click Compare button on Items page")
    public void userClickCompareButtonOnItemsPage() {
        itemsPage.clickCompareButton();
    }
}
