package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//*[@placeholder='Origen']")
    @CacheLookup
    private WebElement txtDeparture;

    @FindBy(xpath = "//*[@placeholder='Destino']")
    @CacheLookup
    private WebElement txtDestination;

    @FindBy(xpath = "//*[@id=\"ticketops-seeker-button\"]")
    @CacheLookup
    private WebElement optTrip;

    @FindBy(xpath = "//input[@alt-placeholder='Fecha ida']")
    @CacheLookup
    private WebElement txtDepatureDate;

    @FindBy(xpath = "//button[@id='buttonSubmit1']")
    @CacheLookup
    private WebElement btnSearch;

    private String optTripSelection = "//li[@class='ui-menu-item']//span[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Accept cookies")
    public void acceptCookies() {
        By cookieBtn = By.id("onetrust-accept-btn-handler");
        if (keyword.findElements(cookieBtn).size() > 0) {
            keyword.click(driver.findElement(cookieBtn));
        } else {
            By alternateBtn = By.xpath("//button[contains(text(),'Aceptar')]");
            if (keyword.findElements(alternateBtn).size() > 0) {
                keyword.click(driver.findElement(alternateBtn));
            }
        }
    }

    @Step("Select departure {departure}")
    public void selectDepature(String departure) {
        keyword.sendText(txtDeparture, departure);
    }

    @Step("Select destination {destination}")
    public void selectDestination(String destination) {
        keyword.sendText(txtDestination, destination);
    }

    @Step("Click Search button")
    public void clickSearch() {
        keyword.click(btnSearch);
    }

    @Step("Select {option}")
    public void selectTripOption(String option) {
        keyword.click(optTrip);
        keyword.click(driver.findElement(By.xpath(String.format(optTripSelection, option))));
    }

    @Step("Set departure date {text}")
    public void setTextDeparture(String text) {
        keyword.sendText(txtDepatureDate, text);
    }
}
