package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class FlightSelection extends BasePage {

    // === Locator ===
    @FindBy(xpath = "(//*[@id='bbki-trip-info-slice-info'])[1]//*[text()='Business']//ancestor::button")
    @CacheLookup
    WebElement btnFirstBusiness;

    @FindBy(xpath = "//button[@id='AVAILABILITY_CONTINUE_BUTTON']")
    @CacheLookup
    WebElement btnEnterPassengerDetail;

    public FlightSelection(WebDriver driver) {
        super(driver);
    }

    // === Action ===
    public void clickFirstBusiness() {
        keyword.click(btnFirstBusiness);
    }

    public  void clickEnterPassengerDetail() {
        keyword.click(btnEnterPassengerDetail);
    }

    // === Assertion ===

}
