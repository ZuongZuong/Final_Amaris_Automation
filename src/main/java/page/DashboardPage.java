package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import utils.LogUtils;

import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//*[@placeholder='Departure']")
    @CacheLookup
    private WebElement txtDeparture;

    @FindBy(xpath = "//*[@placeholder='Destination']")
    @CacheLookup
    private WebElement txtDestination;

    @FindBy(xpath = "//*[@id='ticketops-seeker-button']")
    @CacheLookup
    private WebElement optTrip;

    @FindBy(xpath = "//input[@alt-placeholder='Departure date']")
    @CacheLookup
    private WebElement txtDepartureDate;

    @FindBy(xpath = "//button[@id='buttonSubmit1']")
    @CacheLookup
    private WebElement btnSearch;

    private static final String OPT_TRIP_SELECTION = "//li[@class='ui-menu-item']//span[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Accept cookies if present")
    public void acceptCookies() {
        By cookieBtn = By.id("onetrust-accept-btn-handler");
        try {
            List<WebElement> buttons = keyword.findElements(cookieBtn);
            if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
                keyword.click(buttons.get(0));
            }
        } catch (Exception e) {
            LogUtils.warn("Cookie banner not found or not clickable: " + e.getMessage());
        }
    }

    @Step("Select departure: {departure}")
    public void selectDepature(String departure) {
        keyword.sendText(txtDeparture, departure);
    }

    @Step("Select destination: {destination}")
    public void selectDestination(String destination) {
        keyword.sendText(txtDestination, destination);
    }

    @Step("Click Search button")
    public void clickSearch() {
        keyword.click(btnSearch);
    }

    @Step("Select trip option: {option}")
    public void selectTripOption(String option) {
        keyword.click(optTrip);
        By optionLocator = By.xpath(String.format(OPT_TRIP_SELECTION, option));
        keyword.click(keyword.findElement(optionLocator));
    }

    @Step("Set departure date: {text}")
    public void setTextDeparture(String text) {
        keyword.sendText(txtDepartureDate, text);
    }
}