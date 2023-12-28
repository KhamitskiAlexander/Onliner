package by.onliner.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.onliner.webdrivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DriverActions {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverActions.class);
    private static final String EMPTY_STRING = "";
    private static final String SCROLL_TO_JS_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String BUTTON_WITH_NAME = "//button[contains(., '%s')]";
    private static final int DEFAULT_TIMEOUT = 5;

    /**
     * Private constructor for DriverActions utility class.
     */
    private DriverActions() {
    }

    private static WebDriverWait getDriverWait() {
        return DriverManager.getInstance().getWebDriverWait();
    }

    public static String getUrl() {
        return DriverManager.getInstance().getWebDriver().getCurrentUrl();
    }

    /**
     * Clicks on a button identified by its name. The method utilizes an XPath
     * expression to locate the button based on the provided name.
     *
     * @param buttonName The name of the button to click on. This is used to dynamically
     *                   create an XPath expression to locate the button.
     */
    public static void clickOnButtonWithName(String buttonName) {
        final By buttonWithNameXpath = By.xpath(String.format(BUTTON_WITH_NAME, buttonName));
        WebElement element = getDriverWait().until(elementToBeClickable(buttonWithNameXpath));
        element.click();
    }

    /**
     * Checks if a button identified by its name is displayed.
     *
     * @param buttonName The name of the button to check. This is used to dynamically
     *                   create an XPath expression to locate the button.
     * @return {@code true} if the button is displayed, {@code false} otherwise.
     */
    public static boolean isButtonDisplayedWithName(String buttonName) {
        final By buttonWithNameXpath = By.xpath(String.format(BUTTON_WITH_NAME, buttonName));
        List<WebElement> matchingElements = DriverManager.getInstance().getWebDriver().findElements(buttonWithNameXpath);
        return !matchingElements.isEmpty() && matchingElements.get(0).isDisplayed();
    }

    /**
     * Get of list with web elements
     *
     * @param locator webElements locator.
     */
    public static List<WebElement> getListOfWebElements(By locator) {
        return DriverManager.getInstance().getWebDriver().findElements(locator);
    }

    /**
     * Get of list with strings elements
     *
     * @param locator webElement locator.
     */
    public static List<String> getListOfStrings(By locator) {
        List<String> stringsList = new ArrayList<>();
        List<WebElement> elements = DriverManager.getInstance().getWebDriver().findElements(locator);

        for (WebElement webElement : elements) {
            try {
                stringsList.add(webElement.getText());
            } catch (StaleElementReferenceException ignored) {
                LOGGER.info("StaleElementReferenceException: Stale element found in getListOfStrings method");
            }
        }
        return stringsList;
    }

    /**
     * Waits and clicks on a webElement.
     *
     * @param locator WebElement to wait and click.
     */
    public static void click(final By locator) {
        LOGGER.info("Click element with {} locator.", locator.toString());
        waitToBeVisible(locator);
        WebElement element = getDriverWait().until(elementToBeClickable(locator));
        element.click();
    }

    /**
     * Waits and clicks on a webElement.
     *
     * @param element WebElement to wait and click.
     */
    public static void click(final WebElement element) {
        LOGGER.info("Click element {}", element);
        waitToBeVisible(element);
        getDriverWait().until(visibilityOf(element)).click();
    }

    /**
     * Waits and gets the text of an WebElement.
     *
     * @param locator WebElement to wait and get the text.
     * @return Text of element.
     */
    public static String getText(final By locator) {
        LOGGER.info("Get text from element with {} locator.", locator.toString());
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }

    /**
     * Waits and gets the text of an WebElement.
     *
     * @param element WebElement to wait and get the text.
     * @return Text of element.
     */
    public static String getText(final WebElement element) {
        LOGGER.info("Get text from element with {} locator.", element);
        waitToBeVisible(element);
        return element.getText();
    }

    /**
     * Gets Web Element attribute value.
     *
     * @param locator   page object Web Element.
     * @param attribute attribute name.
     * @return attribute value.
     */
    public static String getAttribute(final By locator, final String attribute) {
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (getDriverWait().until(ExpectedConditions.attributeToBeNotEmpty(element, attribute))) {
            LOGGER.info("Get {} attribute from element with {} locator.", attribute, locator.toString());
            return element.getAttribute(attribute);
        }
        LOGGER.info("Empty attribute value.");
        return EMPTY_STRING;
    }

    /**
     * Switches to the specified iframe by its locator.
     *
     * @param iframeLocator locator of the iframe element.
     */
    public static void switchToIframe(final By iframeLocator) {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        WebElement iframeElement = getDriverWait().until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframeElement);
    }

    /**
     * Switches back to the default content.
     */
    public static void switchToDefaultContent() {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        driver.switchTo().defaultContent();
    }

    /**
     * Switches to the previous page in browser.
     */
    public static void switchToThePreviousPageInBrowser() {
        WebDriver driver = DriverManager.getInstance().getWebDriver();
        driver.navigate().back();
        driver.navigate().refresh();
    }

    /**
     * Verifies if Web element is Displayed.
     *
     * @param locator WebElement.
     * @return True if the element is Displayed.
     */
    public static boolean isDisplayed(final By locator) {
        try {
            WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            LOGGER.error("Element not found." + locator);
            return false;
        }
    }

    public static void jsScrollTo(final By locator) {
        WebElement element = getDriverWait().until(elementToBeClickable(locator));
        JavascriptExecutor jsExec = (JavascriptExecutor) DriverManager.getInstance().getWebDriver();
        jsExec.executeScript(SCROLL_TO_JS_SCRIPT, element);
    }

    public static void jsScrollTo(final WebElement element) {
        waitToBeVisible(element);
        JavascriptExecutor jsExec = (JavascriptExecutor) DriverManager.getInstance().getWebDriver();
        jsExec.executeScript(SCROLL_TO_JS_SCRIPT, element);
    }

    /**
     * Waits until number of elements by locator is greater than to specified value
     *
     * @param locator locator to element/elements
     * @param minSize minimal number of found elements
     * @return list of found elements
     */
    public static List<WebElement> waitUntilElementListHasSizeGreaterThan(By locator, int minSize) {
        return getDriverWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, minSize));
    }

    /**
     * Waits until number of elements by locator is equal to specified value
     *
     * @param locator      locator to element/elements
     * @param expectedSize expected number of found elements
     * @return list of found elements
     */
    public static List<WebElement> waitUntilElementListHasSize(By locator, int expectedSize) {
        return getDriverWait().until(ExpectedConditions.numberOfElementsToBe(locator, expectedSize));
    }

    /**
     * Sets Input Field.
     *
     * @param locator WebElement to wait and fill.
     * @param text    Text to fill.
     */
    public static void setText(final By locator, final String text) {
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        clearText(locator);
        element.sendKeys(text);
    }

    /**
     * Clears the WebElement text content.
     *
     * @param locator WebElement locator.
     */
    public static void clearText(final By locator) {
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
    }

    public static void setSelectByText(final By locator, final String value) {
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    /**
     * Waits for an element to be visible - isDisplayed().
     *
     * @param locator the locator of an element to be found.
     * @throws TimeoutException if failed to wait for the visibility of the element.
     */
    public static void waitToBeVisible(final By locator) {
        defaultFluentWait().until(visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be visible - isDisplayed().
     *
     * @param element the locator of an element to be found.
     * @throws TimeoutException if failed to wait for the visibility of the element.
     */
    public static void waitToBeVisible(final WebElement element) {
        defaultFluentWait().until(visibilityOf(element));
    }

    private static FluentWait<WebDriver> defaultFluentWait() {
        return new FluentWait<WebDriver>(DriverManager.getInstance().getWebDriver())
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(500))
                .ignoreAll(Arrays.asList(ElementNotInteractableException.class,
                        NoSuchElementException.class,
                        StaleElementReferenceException.class,
                        WebDriverException.class));
    }

    /**
     * hover to element by locator
     * @param locator locator to hover
     */
    public static void hoverToElement(final By locator) {
        Actions actions = new Actions(DriverManager.getInstance().getWebDriver());
        WebElement element = getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        LOGGER.info("Hover nouse to: '{}'", locator.toString());
        actions.moveToElement(element).perform();
    }
}
