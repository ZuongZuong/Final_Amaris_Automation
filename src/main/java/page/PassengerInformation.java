package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class PassengerInformation extends BasePage {

    @FindBy(xpath = "//*[text()='First name']//parent::div//input")
    @CacheLookup
    private WebElement txtFirstName;

    @FindBy(xpath = "//*[text()='Last name']//parent::div//input")
    @CacheLookup
    private WebElement txtLastName;

    @FindBy(xpath = "//*[contains(text(), 'Email')]/parent::div//input[not(@id='register_module_email')]")
    @CacheLookup
    private WebElement txtEmail;

    @FindBy(xpath = "//*[contains(text(), 'Phone')]/parent::div//input")
    @CacheLookup
    private WebElement txtTelePhoneNumber;

    @FindBy(xpath = "//*[@id='bbki-passenger-info-simplified-total-price']//*[@id='AVAILABILITY_CONTINUE_BUTTON']")
    @CacheLookup
    private WebElement btnContinue;

    public PassengerInformation(WebDriver driver) {
        super(driver);
    }

    @Step("Set first name: {firstName}")
    public void setTextFirstName(String firstName) {
        keyword.sendText(txtFirstName, firstName);
    }

    @Step("Set last name: {lastName}")
    public void setTextLastName(String lastName) {
        keyword.sendText(txtLastName, lastName);
    }

    @Step("Set email: {email}")
    public void setTextEmail(String email) {
        keyword.sendText(txtEmail, email);
    }

    @Step("Set phone number: {telePhoneNumber}")
    public void setTextTelePhoneNumber(String telePhoneNumber) {
        keyword.sendText(txtTelePhoneNumber, telePhoneNumber);
    }

    @Step("Click Continue button")
    public void clickContinue() {
        keyword.click(btnContinue);
    }
}
