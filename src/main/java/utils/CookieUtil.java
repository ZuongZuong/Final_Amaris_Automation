package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class CookieUtil {

    public static void bypassIberiaCookie(WebDriver driver) {

        driver.manage().addCookie(
                new Cookie("OptanonAlertBoxClosed", "true", ".iberia.com", "/", null)
        );

        driver.manage().addCookie(
                new Cookie("OptanonConsent", "true", ".iberia.com", "/", null)
        );

        driver.navigate().refresh();
    }
}
