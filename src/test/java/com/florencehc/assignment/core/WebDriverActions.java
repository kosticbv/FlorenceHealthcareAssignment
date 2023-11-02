package com.florencehc.assignment.core;


import com.florencehc.assignment.config.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.florencehc.assignment.helpers.Utilities.pageIsLoadedFully;

public class WebDriverActions {
    private static Logger log = LoggerFactory.getLogger(WebDriverActions.class);

    public static WebDriver driver;
    public static Capabilities browserCapabilities;

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void startChromeDriver() {
        //using Java 11 HTTP client, avoiding the Netty library which uses the now obsolete AsyncHttpClient
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        browserCapabilities = ((RemoteWebDriver) driver).getCapabilities();
        if(driver != null) {
            driver.manage().window().maximize();
        }
    }

    public static void reload(){
        driver.navigate().refresh();
    }

    public static void waitForPageToLoadFully() throws TimeoutException {
        ExpectedCondition<Boolean> pageIsLoadedFully = driver -> pageIsLoadedFully();
        FluentWait<WebDriver> wait = new FluentWait<>(WebDriverActions.driver);
        wait.withTimeout(ConfigurationManager.DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(ConfigurationManager.SHORT_POLLING_FREQUENCY)
                .until(pageIsLoadedFully);
    }

    public static void acceptAlert(){
        try {
            Alert alt = driver.switchTo().alert();
            alt.accept();
        }
        catch (NoAlertPresentException e){
            log.error("Alert not present!");
        }
    }
}