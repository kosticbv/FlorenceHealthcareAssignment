package com.florencehc.assignment.test;

import com.florencehc.assignment.core.WebDriverActions;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.florencehc.assignment.core.AllureReportGenerator.generateAllureReport;

public class AssignmentTests {
    private Logger log = LoggerFactory.getLogger(AssignmentTests.class);

    public static String website;

    TestSteps steps = new TestSteps();
    Assertions assertions = new Assertions();

    @BeforeMethod(description = "Before test actions")
    public void setUp(ITestResult testResult) {
        if (!testResult.getMethod().toString().contains("checkIfWebsiteIsUp")) {
            WebDriverActions.startChromeDriver();
        }
    }

    @AfterMethod(description = "After test actions")
    public void tearDown(ITestContext testContext) {
        WebDriverActions.quitDriver();
    }

    @AfterSuite
    public void afterSuiteActions() {
        generateAllureReport();
    }

    @Feature("Website Status")
    @Story("Website Status Check")
    @Description("Getting the website response code and comparing it to the expected result")
    @Test(testName = "Check if website is up ",
            description = "Check if website is up and running",
            dataProvider = "Websites To Test",
            dataProviderClass = DataProviders.class)
    public void checkIfWebsiteIsUp(String URL) {
        log.info("Test started - " + URL + "\n");
        int webSiteResponseCode = steps.getWebsiteResponseCode(URL);
        assertions.assertWebsiteStatusIsAccessible(webSiteResponseCode);
    }

    @Feature("Website Stock Availability")
    @Story("Website Monitor Availability Check")
    @Description("Navigating to the website and searching for available monitors")
    @Test(testName = "Check monitors in results ",
            description = "Check monitors in website results",
            dataProvider = "Websites For Monitors",
            dataProviderClass = DataProviders.class)
    public void checkAvailableMonitors(String URL) {
        log.info("Testing monitors on " + URL + "\n");
        steps.openURL(URL);
        steps.acceptCookies();
        steps.searchForItem("monitor");
        assertions.assertResultsAreDisplayed();
        steps.calculateAverageResultPrice();
        assertions.assertCorrectProductIsLoaded(steps.getProductName());
    }


}
