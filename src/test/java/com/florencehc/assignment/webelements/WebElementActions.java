package com.florencehc.assignment.webelements;

import com.florencehc.assignment.config.ConfigurationManager;
import com.florencehc.assignment.core.WebDriverActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class WebElementActions {
    private static Logger log = LoggerFactory.getLogger(WebElementActions.class);

    /**
     * Method webElement gets the webElement by providing a locator, with fluent wait implemented
     *
     * @param locator used for finding element with that locator
     * @return WebElement
     */
    public static WebElement webElement(By locator) {
        waitForElement(locator);
        try {
            return WebDriverActions.driver.findElement(locator);
        } catch (NoSuchElementException e) {
            log.error("Element located by " + locator.toString().substring(3) + " - NoSuchElementException: " + e);
            throw e;
        } catch (Exception e) {
            log.error("Element located by " + locator.toString().substring(3) + " - Exception: " + e);
            throw e;
        }
    }

    /**
     * Method webElements gets the webElement list by providing a locator, with fluent wait implemented
     *
     * @param locator used for finding elements with that locator
     * @return List<WebElement>
     */
    public static List<WebElement> webElements(By locator) {
        waitForElement(locator);
        try {
            return WebDriverActions.driver.findElements(locator);
        } catch (NoSuchElementException e) {
            log.error("Element located by " + locator.toString().substring(3) + " - NoSuchElementException: " + e);
            throw e;
        } catch (Exception e) {
            log.error("Element located by " + locator.toString().substring(3) + " - Exception: " + e);
            throw e;
        }
    }

    /**
     * Method to perform a click on provided element
     *
     * @param element element to click on
     */
    public static void clickOnElement(WebElement element) {
        waitForElementToBeClickable(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            try {
                Thread.sleep(1000);
                element.click();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        log.info("Successfully clicked on the WebElement located by" + element.toString().substring(72));
    }

    /**
     * Method to input text to provided element
     *
     * @param text    provided text
     * @param element provided element
     */
    public static void enterTextToElement(WebElement element, String text) {
        waitForElementToBeClickable(element);
        element.clear();
        element.sendKeys(text);
        log.info("Entered text '" + text + "' in WebElement located by" + element.toString().substring(72));
    }

    /**
     * Method to press enter key on provided element
     * @param element provided element
     */
    public static void pressEnterOnElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Method to wait for an element by provided locator and a default timeout duration
     *
     * @param locator provided locator
     */
    public static void waitForElement(By locator) {
        waitForElementWithTimeout(locator, ConfigurationManager.DEFAULT_WAIT_TIMEOUT);
    }

    /**
     * Method to wait for an element by provided locator and a short timeout duration
     *
     * @param locator provided locator
     */
    public static void waitForElementShort(By locator) {
        waitForElementWithTimeout(locator, ConfigurationManager.SHORT_WAIT_TIMEOUT);
    }
    /**
     * Method to use fluent wait to wait for an element by provided locator, and a custom timeout duration
     *
     * @param locator  provided locator
     * @param duration provided duration
     */
    public static void waitForElementWithTimeout(By locator, Duration duration) throws TimeoutException {
        FluentWait<WebDriver> wait = new FluentWait<>(WebDriverActions.driver);
        wait.withTimeout(duration)
                .pollingEvery(ConfigurationManager.SHORT_POLLING_FREQUENCY)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Method to wait for element to be clickable, by provided WebElement and default timeout duration
     *
     * @param element provided element
     */
    public static void waitForElementToBeClickable(WebElement element) {
        waitForElementToBeClickableWithTimeout(element, ConfigurationManager.DEFAULT_WAIT_TIMEOUT);
    }

    /**
     * Method to wait for element to be clickable by provided WebElement and a custom timeout duration
     *
     * @param element  provided element
     * @param duration provided duration
     */
    public static void waitForElementToBeClickableWithTimeout(WebElement element, Duration duration) throws TimeoutException {
        FluentWait<org.openqa.selenium.WebDriver> wait = new FluentWait<>(WebDriverActions.driver);
        wait.withTimeout(duration)
                .pollingEvery(ConfigurationManager.SHORT_POLLING_FREQUENCY)
                .ignoring(NoSuchElementException.class, ElementClickInterceptedException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Method to check if element is present by provided locator,
     * returning a boolean, so it can be used as a condition in an if statement
     *
     * @param locator provided locator
     * @return true if present, false if not present
     */
    public static boolean elementIsPresent(By locator) {
        boolean elementIsPresent = false;
        try {
            waitForElementShort(locator);
            WebDriverActions.driver.findElement(locator);
            elementIsPresent = true;
        } catch (Exception e) {
            log.info("Element located by " + locator.toString().substring(3) + " not needed in this execution");
        }
        return elementIsPresent;
    }
}