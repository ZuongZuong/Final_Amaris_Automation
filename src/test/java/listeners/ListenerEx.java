package listeners;

import io.qameta.allure.Allure;

import org.testng.ITestListener;
import org.testng.ITestResult;
import test.BaseTest;
import utils.LogUtils;

public class ListenerEx implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Test Started: " + result.getName());
        Allure.label("suite", result.getTestClass().getRealClass().getSimpleName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test Failed: " + result.getName());
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;
            baseTest.takeScreenShot(result.getName());
            baseTest.takeScreenshotForAllure();
            saveTextLog("Test Failed: " + result.getName() + " - " + result.getThrowable().getMessage());
        }
    }

    @io.qameta.allure.Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.info("Test Skipped (or will be retried): " + result.getName());
    }
}
