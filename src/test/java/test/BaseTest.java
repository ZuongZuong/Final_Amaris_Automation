package test;

import constants.FrameworkConstants;
import io.qameta.allure.Attachment;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import action.Keyword;
import configuration.Configuration;
import driver.DriverFactory;
import utils.CookieUtil;
import utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Keyword keyword;
    public Configuration config;
    public static int count = 1;

    @BeforeSuite
    public void beforeSuite() {
        LogUtils.info("Set up Environment for Allure Report");
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
            FileOutputStream fos = new FileOutputStream("target/allure-results/environment.properties");
            properties.store(fos, "Allure Environment Properties");
            fos.close();
        } catch (IOException e) {
            LogUtils.error("Failed to create environment.properties: " + e.getMessage());
        }
    }

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = DriverFactory.getDriver(FrameworkConstants.BROWSER);
        this.keyword = new Keyword(driver);
        keyword.openUrl(FrameworkConstants.URL);
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        CookieUtil.bypassIberiaCookie(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            this.driver.quit();
        }
    }

    public void takeScreenShot(String method) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
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

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshotForAllure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
