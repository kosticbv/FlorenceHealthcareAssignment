package com.florencehc.assignment.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.florencehc.assignment.core.WebDriverActions.driver;

public class Utilities {
    public static Logger log = LoggerFactory.getLogger(Utilities.class);

    public static String timeStampDayMonthYearHourMinute() {
        return new SimpleDateFormat("dd.MM.yyyy-HH.mm").format(Calendar.getInstance().getTime());
    }

    public static String regexString(String regex, String text) {
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    public static String stripString(String stringToStrip, String ... charsToStripFromString) {
        for (String charToStrip : charsToStripFromString) {
            stringToStrip = stringToStrip.replace(charToStrip,"");
        }
        return stringToStrip;
    }

    public static List<String> readSingleColumnCSV(String source) {
        String line;
        List<String> csvData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            while ((line = br.readLine()) != null) {
                csvData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    public static boolean pageIsLoadedFully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String readyState = js.executeScript("return document.readyState").toString();
        return readyState.equals("complete");
    }
}