package com.florencehc.assignment.test;

import com.florencehc.assignment.core.WebDriverActions;
import com.florencehc.assignment.webelements.Locators;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.florencehc.assignment.core.WebDriverActions.waitForPageToLoadFully;
import static com.florencehc.assignment.helpers.Utilities.stripString;
import static com.florencehc.assignment.webelements.WebElementActions.*;

public class TestSteps {
    private static Logger log = LoggerFactory.getLogger(TestSteps.class);

    Locators locators = new Locators();

    public int getWebsiteResponseCode(String url) {
        try {
            log.info("Getting response code for " + url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            log.info("Response code for " + url + " is: " + responseCode + "\n");
            return responseCode;
        } catch (IOException e) {
            log.error("Error getting response code from website " + url);
            throw new RuntimeException(e);
        }
    }

    @Step("Navigate to {url}")
    public void openURL(String url) {
        WebDriverActions.driver.navigate().to(url);
        log.info("Navigated to url: " + url);
        AssignmentTests.website = stripString(url,"https:",".rs","www.","/");
    }

    @Step("Accept cookies")
    public void acceptCookies() {
        switch (AssignmentTests.website) {
            case "gigatron":
                clickOnElement(webElement(locators.gigatronAcceptCookiesButton));
                break;
            case "ananas":
                clickOnElement(webElement(locators.ananasAcceptCookiesButton));
                break;
            case "tehnomedia":
                break;
            case "ctshop":
                clickOnElement(webElement(locators.ctshopAcceptCookiesButton));
                break;
            default: log.error("Website not specified!");
        }
    }

    @Step("Search for item - {searchKeyword}")
    public void searchForItem(String searchKeyword) {
        switch (AssignmentTests.website) {
            case "gigatron":
                enterTextToElement(webElement(locators.gigatronSearchField), searchKeyword);
                pressEnterOnElement(webElement(locators.gigatronSearchField));
                break;
            case "ananas":
                enterTextToElement(webElement(locators.ananasSearchField), searchKeyword);
                pressEnterOnElement(webElement(locators.ananasSearchField));
                break;
            case "tehnomedia":
                enterTextToElement(webElement(locators.tehnomediaSearchField), searchKeyword);
                pressEnterOnElement(webElement(locators.tehnomediaSearchField));
                break;
            case "ctshop":
                enterTextToElement(webElement(locators.ctshopSearchField), searchKeyword);
                pressEnterOnElement(webElement(locators.ctshopSearchField));
                break;
            default: log.error("Website not specified!");
        }
        waitForPageToLoadFully();
    }

    @Step("Calculate average result price")
    public int calculateAverageResultPrice() {
        List<Integer> prices = new ArrayList<>();
        switch (AssignmentTests.website) {
            case "gigatron":
                for (WebElement element:webElements(locators.gigatronSearchResultsPrices)) {
                    prices.add(Integer.parseInt(stripString(element.getText(),".",",00"," RSD")));
                }
                break;
            case "ananas":
                for (WebElement element:webElements(locators.ananasSearchResultsPrices)) {
                    prices.add(Integer.parseInt(stripString(element.getText(),".",",00")));
                }
                break;
            case "tehnomedia":
                for (WebElement element:webElements(locators.tehnomediaSearchResultsPrices)) {
                    prices.add(Integer.parseInt(stripString(element.getText(),".",",00")));
                }
                break;
            case "ctshop":
                for (WebElement element:webElements(locators.ctshopSearchResultsPrices)) {
                    prices.add(Integer.parseInt(stripString(element.getText(),".",",00")));
                }
                break;
            default: log.error("Website not specified!");
        }
        int sum = 0;
        for (Integer price:prices) {
            sum += price;
        }
        int average = sum / prices.size();
        System.out.println();
        log.info("The average price of items in the first page results is: " + average + " RSD\n");
        return average;
    }

    @Step("Get product name from search results")
    public String getProductName() {
        Random random = new Random();
        int index;
        String productName;
        switch (AssignmentTests.website) {
            case "gigatron":
                index = random.nextInt(webElements(locators.gigatronProductNames).size()-1);
                waitForPageToLoadFully();
                productName = webElement(locators.gigatronProductName(index+1)).getText();
                clickOnElement(webElement(locators.gigatronProductName(index+1)));
                return productName;
            case "ananas":
                index = random.nextInt(webElements(locators.ananasProductNames).size()-1);
                waitForPageToLoadFully();
                productName = webElement(locators.ananasProductName(index+1)).getText();
                clickOnElement(webElement(locators.ananasProductName(index+1)));
                return productName;
            case "tehnomedia":
                index = random.nextInt(webElements(locators.tehnomediaProductNames).size()-1);
                waitForPageToLoadFully();
                productName = webElement(locators.tehnomediaProductName(index+1)).getText();
                clickOnElement(webElement(locators.tehnomediaProductName(index+1)));
                return productName;
            case "ctshop":
                index = random.nextInt(webElements(locators.ctshopProductNames).size()-1);
                waitForPageToLoadFully();
                productName = webElement(locators.ctshopProductName(index+1)).getText();
                clickOnElement(webElement(locators.ctshopProductName(index+1)));
                return productName;
            default:
                log.error("Website not specified!");
                return "";
        }
    }
}
