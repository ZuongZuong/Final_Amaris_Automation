package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerInformation extends BasePage {

    // === Locator ===
    @FindBy(xpath = "//*[text()='First name']//parent::div//input")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(xpath = "//*[text()='Last name']//parent::div//input")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(xpath = "//*[contains(text(), 'Email')]/parent::div//input[not(@id='register_module_email')]")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(xpath = "//*[contains(text(), 'Email')]/parent::div//input[not(@id='register_module_email')]")
    @CacheLookup
    WebElement txtTelePhoneNumber;

    @FindBy(xpath = "//*[@id='bbki-passenger-info-simplified-total-price']//*[@id='AVAILABILITY_CONTINUE_BUTTON']")
    @CacheLookup
    WebElement btnContinue;


    public PassengerInformation(WebDriver driver) {
        super(driver);
    }

    // === Action ===
    public void setTextFirstName(String firstName) {
        txtFirstName.sendKeys(firstName);
    }

    public void setTextLastName(String lastName) {
        txtLastName.sendKeys(lastName);
    }

    public void setTextEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTextTelePhoneNumber(String telePhoneNumber) {
        txtTelePhoneNumber.sendKeys(telePhoneNumber);
    }

    public void clickContinue() {
        keyword.click(btnContinue);
    }


    // === Assertion ===

}
