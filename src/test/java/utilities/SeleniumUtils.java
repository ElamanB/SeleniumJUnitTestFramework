package utilities;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumUtils {
    /**
     * This method clicks on the given element, switches to the newly opened tab
     * and prints its url.
     *
     * @param driver  - is used to open web application.
     * @param element - is clicked to open new tab
     */
    public static void switchToNewTab(WebDriver driver, WebElement element) {
        String mainWindow = driver.getWindowHandle();

        element.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        System.out.println("Currently, the driver is on: " + driver.getCurrentUrl());

    }

    /**
     * This method waits for element to be clickable
     * before clicking on it
     *
     * @param driver  - is used to open web application.
     * @param element - is clicked to open new tab
     */
    public static void click(WebDriver driver, WebElement element) {
        FluentWait wait = new FluentWait(driver)
                .ignoring(ElementClickInterceptedException.class)
                .withTimeout(Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * This method accepts alert, if alert is not there
     * it ignore the exception
     *
     * @param driver - is used to open web application.
     */
    public static void acceptAlert(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert does not exist!");
            e.printStackTrace();
        }

    }

    /**
     * This method dismisses alert, if alert is not there
     * it ignore the exception
     *
     * @param driver - is used to open web application.
     */
    public static void dismissAlert(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert does not exist!");
            e.printStackTrace();
        }

    }

    /**
     * The method returns true if element is present in the DOM
     *
     * @param driver  to go to webpage
     * @param locator of the web element
     * @return
     */


    public static boolean isElementPresent(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * This method will try to click on element multiple times
     * by ignoring StaleElement exception
     * @param driver to go to webpage
     * @param locator to click on
     * @param retries number fo times to try
     */

    public static void clickWithRetries(WebDriver driver, By locator, int retries) {

        int numOfTrials = 0;

        while (numOfTrials < retries) {

            try {
                WebElement element = driver.findElement(locator);
                element.click();
                return;

            } catch (StaleElementReferenceException e) {
                numOfTrials++;
                waitForSeconds(1);
            } catch (NoSuchElementException e) {
                System.out.println("Wrong locator!");
                e.printStackTrace();
                return;
            }
        }
    }


    public static void waitForSeconds(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method waits for text to appear in the element
     * then verifies if it matches with expected text
     * @param driver
     * @param numOfSec to wait for
     * @param element to verify
     * @param expectedText to verify
     */
    public static void verifyTextInElement(WebDriver driver, int numOfSec, WebElement element, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(numOfSec));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));

        Assertions.assertEquals(expectedText,element.getText());
    }

    public static void cleanAndSendKeys(WebDriver driver, WebElement element, String textToSend) {
        Actions actions = new Actions(driver);
        actions.keyDown(element, Keys.CONTROL).sendKeys("a");
        actions.keyUp(element, Keys.CONTROL);
        actions.keyDown(element, Keys.BACK_SPACE);
        actions.keyUp(element, Keys.BACK_SPACE);

        actions.build().perform();

        element.sendKeys(textToSend);
    }

    /**
     * Waits until the list of WebElements located by the given locator is present and returns them.
     * @param driver The WebDriver instance to use.
     * @param locator The By locator to find the elements.
     * @param timeoutSeconds The maximum time in seconds to wait.
     * @return A list of WebElements once they are located and loaded.
     */
    public static List<WebElement> waitForElementsToBePresent(WebDriver driver, By locator, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }





}
