package page;

import org.openqa.selenium.WebDriver;
import action.Keyword;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected Keyword keyword;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.keyword = new Keyword(driver);
        PageFactory.initElements(driver, this);
    }
}
