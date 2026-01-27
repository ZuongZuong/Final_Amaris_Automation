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
            ((BaseTest) testClass).takeScreenShot(result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.info("Test Skipped: " + result.getName());
    }
}
