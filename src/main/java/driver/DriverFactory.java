package driver;

import constants.FrameworkConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                options.setExperimentalOption("useAutomationExtension", false);

                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("media.navigator.permission.disabled", true);
                firefoxOptions.addPreference("media.navigator.streams.fake", true);
                firefoxOptions.addPreference("browser.tabs.warnOnClose", false);
                firefoxOptions.addPreference("browser.startup.page", 0);
                firefoxOptions.addPreference("browser.startup.homepage", "about:blank");
                firefoxOptions.addPreference("toolkit.telemetry.enabled", false);
                firefoxOptions.addPreference("toolkit.telemetry.reportingpolicy.firstRun", false);
                firefoxOptions.addPreference("browser.discovery.enabled", false);
                firefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
                WebDriverManager.firefoxdriver().browserVersion("147").setup();
                return new FirefoxDriver(firefoxOptions);
            default:
                return new ChromeDriver();
        }
    }
}
