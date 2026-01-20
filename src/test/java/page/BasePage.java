package page;

import org.openqa.selenium.WebDriver;

import action.Keyword;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;
    public Keyword keyword;

    public BasePage(WebDriver driver) {
        super();
        this.driver = driver;
        this.keyword = new Keyword(driver);
        PageFactory.initElements(driver, this);
    }
}
