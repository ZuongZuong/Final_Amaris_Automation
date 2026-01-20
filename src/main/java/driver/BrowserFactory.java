package driver;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import constants.FrameworkConstants;

public enum BrowserFactory {

    CHROME {
        @Override
        public WebDriver createDriver() {
            System.setProperty("webdriver.chrome.driver", FrameworkConstants.PROJECT_PATH + "/source/driver/chromedriver.exe");
            return new ChromeDriver(getOptions());
        }

        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("start-maximized");
            options.addArguments("Incognito");
            if (Boolean.valueOf(FrameworkConstants.HEADLESS) == true) {
                options.addArguments("--headless=new");
            }
            options.setAcceptInsecureCerts(true);

            return options;
        }

    }, FIREFOX {
        @Override
        public WebDriver createDriver() {
            //WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            System.setProperty("webdriver.chrome.driver", FrameworkConstants.PROJECT_PATH + "/source/driver/fixfore.exe");
            return new FirefoxDriver(getOptions());
        }

        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            if (Boolean.valueOf(FrameworkConstants.HEADLESS) == true) {
                options.addArguments("--headless");
                options.addArguments("window-size=1800,900");
            }

            return options;
        }
    };

    public abstract WebDriver createDriver();

    public abstract MutableCapabilities getOptions();
}
