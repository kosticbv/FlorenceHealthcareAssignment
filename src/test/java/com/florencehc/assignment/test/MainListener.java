package com.florencehc.assignment.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class MainListener implements ITestListener {

    private Logger log = LoggerFactory.getLogger(MainListener.class);

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println();
        log.info("*************************************************************************************************");
        Object[] params = result.getParameters();
        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
        log.info("    " + result.getTestContext().getName() + " started");
        log.info("    " + result.getTestContext().getSuite().getName());
        log.info("    " + testName);
        log.info("    Scenario list - row " + params[0]);
        log.info("*************************************************************************************************\n");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println();
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Object[] params = result.getParameters();
        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
        log.info("    " + result.getTestContext().getName() + " finished");
        log.info("    " + result.getTestContext().getSuite().getName());
        log.info("    " + testName);
        log.info("    Scenario list - row " + params[0] + " PASSED");
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println();
        log.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Object[] params = result.getParameters();
        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
        log.info("    " + result.getTestContext().getName() + " finished");
        log.info("    " + result.getTestContext().getSuite().getName());
        log.info("    " + testName);
        log.info("    Scenario list - row " + params[0] + " FAILED");
        System.out.println();
        log.error("" + result.getThrowable());
        log.warn("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
}