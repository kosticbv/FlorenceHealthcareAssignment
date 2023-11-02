package com.florencehc.assignment.test;

import org.testng.annotations.DataProvider;

import java.util.*;

import static com.florencehc.assignment.helpers.Utilities.readSingleColumnCSV;

public class DataProviders {

    @DataProvider(name = "Websites To Test")
    public static Iterator<Object[]> getListOfWebsitesToTest() {
        List<String> websiteURLs = readSingleColumnCSV("src/test/resources/websites.csv");
        Collection<Object[]> dp = new ArrayList<>();
        for(String websiteURL:websiteURLs){
            dp.add(new Object[]{websiteURL});
        }
        return dp.iterator();
    }

    @DataProvider(name = "Websites For Monitors")
    public static Iterator<Object[]> getListOfWebsitesForMonitorsToTest() {
        List<String> websiteURLs = readSingleColumnCSV("src/test/resources/websitesForMonitors.csv");
        Collection<Object[]> dp = new ArrayList<>();
        for(String websiteURL:websiteURLs){
            dp.add(new Object[]{websiteURL});
        }
        return dp.iterator();
    }
}