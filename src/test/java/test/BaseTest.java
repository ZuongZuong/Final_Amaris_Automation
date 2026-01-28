package test;

import constants.FrameworkConstants;
import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import action.Keyword;
import driver.DriverFactory;
import utils.CookieUtil;
import utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public Keyword keyword;
    public static int count = 1;

    @BeforeSuite
    public void beforeSuite() {
        LogUtils.info("Set up Environment for Allure Report");
        setupAllureEnvironment();
    }

    private void setupAllureEnvironment() {
        try {
            Properties properties = new Properties();
            properties.setProperty("OS", System.getProperty("os.name"));
            properties.setProperty("Java Version", System.getProperty("java.version"));
            properties.setProperty("Browser", FrameworkConstants.BROWSER);
            properties.setProperty("Env", FrameworkConstants.URL);

            File allureResultsDir = new File("target/allure-results");
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }
            try (FileOutputStream fos = new FileOutputStream("target/allure-results/environment.properties")) {
                properties.store(fos, "Allure Environment Properties");
            }
        } catch (IOException e) {
            LogUtils.error("Failed to create environment.properties: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
        WebDriver driver = DriverFactory.getDriver(FrameworkConstants.BROWSER);
        DriverManager.setDriver(driver);
        this.keyword = new Keyword(driver);

        driver.manage().window().maximize();
        keyword.openUrl(FrameworkConstants.URL);

        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        CookieUtil.bypassIberiaCookie(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quit();
    }

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshotForAllure() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void takeScreenShot(String method) {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = this.getClass().getSimpleName();
        File dest = new File(FrameworkConstants.PROJECT_PATH + File.separator + "screenshots" + File.separator
                + fileName + File.separator + method + "_" + count + ".png");
        count++;

        try {
            FileUtils.copyFile(src, dest);
            LogUtils.info("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            LogUtils.error("Failed to take screenshot: " + e.getMessage());
        }
    }
}
