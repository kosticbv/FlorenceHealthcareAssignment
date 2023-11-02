package com.florencehc.assignment.test;

import com.florencehc.assignment.webelements.Locators;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.florencehc.assignment.webelements.WebElementActions.*;

public class Assertions {

    private Logger log = LoggerFactory.getLogger(Assertions.class);
    Locators locators = new Locators();

    @Step("Verify that the website status is accessible")
    public void assertWebsiteStatusIsAccessible(int actualWebsiteStatus) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualWebsiteStatus).isEqualTo(200);
        try {
            softly.assertAll();
            log.info("Test passed!\n\n");
        } catch (AssertionError e) {
            log.error("Test failed:\n");
            throw new AssertionError(e);
        }
    }

    @Step("Verify that the search results are displayed")
    public void assertResultsAreDisplayed() {
        SoftAssertions softly = new SoftAssertions();
        switch (AssignmentTests.website) {
            case "gigatron":
                softly.assertThat(elementIsPresent(locators.gigatronSearchResults)).isEqualTo(true);
                break;
            case "ananas":
                softly.assertThat(elementIsPresent(locators.ananasSearchResults)).isEqualTo(true);
                break;
            case "tehnomedia":
                softly.assertThat(elementIsPresent(locators.tehnomediaSearchResults)).isEqualTo(true);
                break;
            case "ctshop":
                softly.assertThat(elementIsPresent(locators.ctshopSearchResults)).isEqualTo(true);
                break;
            default:
                log.error("Website not specified!");
        }
        try {
            softly.assertAll();
            log.info("Step passed - search results are displayed");
        } catch (AssertionError e) {
            log.error("Test failed:\n");
            throw new AssertionError(e);
        }
    }

    @Step("Verify that the correct product is loaded after clicking on one of the search results")
    public void assertCorrectProductIsLoaded(String expectedProductName) {
        SoftAssertions softly = new SoftAssertions();
        switch (AssignmentTests.website) {
            case "gigatron":
                softly.assertThat(webElement(locators.gigatronProductPageName).getText()).isEqualTo(expectedProductName);
                break;
            case "ananas":
                waitForElement(locators.ananasProductPageLoaded);
                softly.assertThat(webElement(locators.ananasProductPageName).getText()).isEqualTo(expectedProductName);
                break;
            case "tehnomedia":
                softly.assertThat(webElement(locators.tehnomediaProductPageName).getText()).isEqualTo(expectedProductName);
                break;
            case "ctshop":
                softly.assertThat(webElement(locators.ctshopProductPageName).getText()).isEqualTo(expectedProductName);
                break;
            default:
                log.error("Website not specified!");
        }
        try {
            softly.assertAll();
            log.info("Step passed - correct product name is displayed");
        } catch (AssertionError e) {
            log.error("Test failed:\n");
            throw new AssertionError(e);
        }
    }
}
