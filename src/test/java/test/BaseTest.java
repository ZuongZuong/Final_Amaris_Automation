package test;

import constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import action.Keyword;
import configuration.Configuration;
import driver.DriverFactory;
import utils.CookieUtil;
import utils.LogUtils;

import java.io.File;
import java.io.IOException;

public class BaseTest {
	public WebDriver driver;
    public Keyword keyword;
    public Configuration config;
    public static int count = 1;

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = DriverFactory.getDriver(FrameworkConstants.BROWSER);
        this.keyword = new Keyword(driver);
        keyword.openUrl(FrameworkConstants.URL);
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        CookieUtil.bypassIberiaCookie(driver);
        // this.driver.findElement(By.xpath("//div[@id=\"onetrust-button-group\"]//*[@id=\"onetrust-accept-btn-handler\"]")).click();
    }

    @AfterMethod
    public void tearDown(){
        this.driver.close();
        this.driver.quit();
    }

    public void takeScreenShot(String method) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        String fileName = this.getClass().getSimpleName();

        File dest = new File(FrameworkConstants.PROJECT_PATH + File.separator + "screenshots" + File.separator + fileName + File.separator + method + "_" + count + ".png");
        count++;

        try {
            FileUtils.copyFile(src, dest);
            LogUtils.info("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            LogUtils.error("Failed to take screenshot: " + e.getMessage());
        }
    }
    
}
