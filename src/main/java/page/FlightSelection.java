package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class FlightSelection extends BasePage {

    @FindBy(xpath = "(//*[@id='bbki-trip-info-slice-info'])[1]//*[text()='Business']//ancestor::button")
    @CacheLookup
    private WebElement btnFirstBusiness;

    @FindBy(xpath = "//button[@id='AVAILABILITY_CONTINUE_BUTTON']")
    @CacheLookup
    private WebElement btnEnterPassengerDetail;

    public FlightSelection(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the first Business class option")
    public void clickFirstBusiness() {
        keyword.click(btnFirstBusiness);
    }

    @Step("Click Enter Passenger Detail button")
    public void clickEnterPassengerDetail() {
        keyword.click(btnEnterPassengerDetail);
    }
}
