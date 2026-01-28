package driver;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import constants.FrameworkConstants;

public enum BrowserFactory {

    CHROME {
        @Override
        public ChromeOptions getOptions() {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--start-maximized");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
            options.setExperimentalOption("useAutomationExtension", false);

            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless=new");
            }
            options.setAcceptInsecureCerts(true);
            return options;
        }
    },
    FIREFOX {
        @Override
        public FirefoxOptions getOptions() {
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
                options.addArguments("--headless");
            }
            return options;
        }
        // },
        // EDGE {
        // @Override
        // public EdgeOptions getOptions() {
        // EdgeOptions options = new EdgeOptions();
        // if (Boolean.parseBoolean(FrameworkConstants.HEADLESS)) {
        // options.addArguments("--headless");
        // }
        // return options;
        // }
    };

    public abstract MutableCapabilities getOptions();
}
