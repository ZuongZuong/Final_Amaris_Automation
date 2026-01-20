package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    // === Locator ===
    @FindBy(xpath = "//*[@placeholder=\"Departure\"]")
    @CacheLookup
    WebElement txtDeparture;

//    @FindBy(xpath = "//*[@placeholder=\"Departure\"]")
//    @CacheLookup
//    WebElement drpdwnDeparture;

    @FindBy(xpath = "//*[@placeholder=\"Destination\"]")
    @CacheLookup
    WebElement txtDestination;

    @FindBy(xpath = "//*[@id=\"ticketops-seeker-button\"]")
    @CacheLookup
    WebElement optTrip;

    @FindBy(xpath = "//input[@alt-placeholder=\"Departure date\"]")
    @CacheLookup
    WebElement txtDepatureDate;

//    @FindBy(xpath = "//*[@placeholder=\"Departure\"]")
//    @CacheLookup
//    WebElement drpdwnTicketTrip;

    @FindBy(xpath = "//button[@id=\"buttonSubmit1\"]")
    @CacheLookup
    WebElement btnSearch;

    //a[@title="Valencia (VLC)" and contains(@class, 'ui-state-active')]
    String drpdwnLocation = "//a[@title='%s' and contains(@class, 'ui-state-active')]";

    String optTripSelection = "//li[@class=\"ui-menu-item\"]//span[text()='%s']";


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // === Action ===
    public void selectDepature(String depature){
        keyword.sendText(txtDeparture, depature);
//        keyword.click(driver.findElement(By.xpath(String.format(drpdwnLocation, depature))));
    }

    public void selectDestination(String destination){
        keyword.sendText(txtDestination, destination);
//        keyword.click(driver.findElement(By.xpath(String.format(drpdwnLocation, destination))));
    }

    public void clickSearch(){
        keyword.click(btnSearch);
    }

    public void selectTripOption(String option){
        keyword.click(optTrip);
        keyword.click(driver.findElement(By.xpath(String.format(optTripSelection, option))));
    }

    public void setTextDeparture(String text){
        keyword.sendText(txtDepatureDate, text);
    }


    // === Assertion ===
//    public boolean isDashboardPageDisplayed() {
//        return lblOverview.isDisplayed();
//    }

}
