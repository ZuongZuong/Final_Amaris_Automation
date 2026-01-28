package driver;

import constants.FrameworkConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.LogUtils;

import java.net.URL;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        String target = FrameworkConstants.TARGET.toUpperCase();
        MutableCapabilities options = BrowserFactory.valueOf(browser.toUpperCase()).getOptions();

        if (target.equals("REMOTE")) {
            driver = createRemoteInstance(options);
        } else {
            driver = createLocalInstance(browser, options);
        }
        return driver;
    }

    private static WebDriver createLocalInstance(String browser, MutableCapabilities options) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver((ChromeOptions) options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver((FirefoxOptions) options);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver((EdgeOptions) options);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver((ChromeOptions) options);
        }
        return driver;
    }

    private static WebDriver createRemoteInstance(MutableCapabilities capability) {
        RemoteWebDriver remoteWebDriver = null;
        try {
            String gridURL = String.format("http://%s:%s/wd/hub",
                    FrameworkConstants.REMOTE_URL, FrameworkConstants.REMOTE_PORT);
            LogUtils.info("Connecting to Selenium Grid at: " + gridURL);
            remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
        } catch (Exception e) {
            LogUtils.error("Grid URL is invalid or Grid Port is not available");
            LogUtils.error(e.getMessage());
        }
        return remoteWebDriver;
    }
}
