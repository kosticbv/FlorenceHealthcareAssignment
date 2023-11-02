package com.florencehc.assignment.core;

import io.qameta.allure.maven.AllureCommandline;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static com.florencehc.assignment.core.WebDriverActions.browserCapabilities;
import static com.florencehc.assignment.helpers.Utilities.timeStampDayMonthYearHourMinute;

public class AllureReportGenerator {
    public static void generateAllureReport() {
        String reportfolder = "testresults/allure-report_" + timeStampDayMonthYearHourMinute();
        AllureCommandline allureCommandline = new AllureCommandline(Paths.get("src/test/resources/report-resources/"), AllureCommandline.ALLURE_DEFAULT_VERSION);
        List<Path> paths = new ArrayList<>();
        paths.add(Paths.get("allure-results"));
        try {
            generateEnvironmentInformation();
            allureCommandline.generateReport(paths, Paths.get(reportfolder + "/allure-report"));
            FileUtils.copyDirectoryToDirectory(new File("src/test/resources/report-resources/allure-2.20.1/"), new File(reportfolder));
            FileUtils.copyFileToDirectory(new File("src/test/resources/report-resources/open_report_windows.bat"), new File(reportfolder));
            FileUtils.copyFileToDirectory(new File("src/test/resources/report-resources/open_report_mac.sh"), new File(reportfolder));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File allureResults = new File("allure-results");
        for (File file : Objects.requireNonNull(allureResults.listFiles())) {
            file.delete();
        }
        allureResults.delete();
    }

    public static void generateEnvironmentInformation() throws IOException {
        File envProperties = new File("allure-results/environment.properties");
        Properties p = new Properties();
        FileOutputStream outputStream = new FileOutputStream(envProperties);
        p.setProperty("Name", "N/A");
        p.store(outputStream, null);
        p.setProperty("URL", "N/A");
        p.store(outputStream, null);
        p.setProperty("Browser", browserCapabilities != null ? StringUtils.capitalize(browserCapabilities.getBrowserName()) : "N/A");
        p.store(outputStream, null);
        p.setProperty("Browser Version", browserCapabilities != null ? browserCapabilities.getBrowserVersion() : "N/A");
        p.store(outputStream, null);
    }
}