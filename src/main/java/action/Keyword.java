package action;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LogUtils;

public class Keyword {
	private WebDriver driver;
	private WebDriverWait wait;
	public JavascriptExecutor executor;
	public Actions action;

	public Keyword(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		this.action = new Actions(driver);
	}


	public void openUrl(String url) throws Exception {
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new Exception("Invalid URL format");
        }
		driver.get(url);
        LogUtils.info("Opening URL: " + url);
	}

	public void navigate(String url) throws Exception {
		if (!(url.startsWith("http://") || url.startsWith("https://"))) {
			throw new Exception("Invalid URL format");
		}
		driver.navigate().to(url);
        LogUtils.info("Navigating to URL: " + url);
	}

	public WebElement findElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void sendText(WebElement element, String text) {
		try {
			element.clear();
			element.sendKeys(text);
            LogUtils.info("Sending text to element: " + text);
		} catch (WebDriverException e) {
			throw new WebDriverException("Element is not enable to set text");

		}
	}

	public void click(WebElement element) {
		action.moveToElement(element).build().perform();
		element.click();
        LogUtils.info("Clicking on element: " + element);
	}

	public void clickWithJs(WebElement element) {
		executor = (JavascriptExecutor) this.driver;
		executor.executeScript("arguments[0].click();", element);
        LogUtils.info("Clicking on element: " + element);
	}

	public void select(WebElement element, SelectType type, String options) throws Exception {
		Select select = new Select(element);
		switch (type) {
		case SELECT_BY_INDEX:
			try {
				select.selectByIndex(Integer.parseInt(options));
                LogUtils.info("Selected by index: " + options);
			} catch (Exception e) {
				throw new Exception("Please input numberic on selectOption for SelectType.SelectByIndex");
			}
			break;
		case SELECT_BY_TEXT:
			select.selectByVisibleText(options);
            LogUtils.info("Selected by text: " + options);
			break;
		case SELECT_BY_VALUE:
			select.selectByValue(options);
            LogUtils.info("Selected by value: " + options);
			break;
		default:
			throw new Exception("Get error in using Selected");
		}
	}

	public enum SelectType {
		SELECT_BY_INDEX, SELECT_BY_TEXT, SELECT_BY_VALUE,
	}

	public String getTitle() {
        LogUtils.info("Getting title");
		return driver.getTitle();
	}

	public String getCssValue(WebElement element, String value) {
		return element.getCssValue(value);
	}

	public void enter() {
		action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
        LogUtils.info("Entering keyboard");
	}

	public List<WebElement> findElements(String value) throws Exception {
		List<WebElement> elements = null;
		String locatorType = value.split(";")[0];
		String locatorValue = value.split(";")[1];
		switch (locatorType.toLowerCase()) {
		case "id":
			elements = driver.findElements(By.id(locatorValue));
			break;
		case "name":
			elements = driver.findElements(By.name(locatorValue));
			break;
		case "xpath":
			elements = driver.findElements(By.xpath(locatorValue));
			break;
		case "tag":
			elements = driver.findElements(By.tagName(locatorValue));
			break;
		case "link":
			elements = driver.findElements(By.linkText(locatorValue));
			break;
		case "css":
			elements = driver.findElements(By.cssSelector(locatorValue));
			break;
		case "class":
			elements = driver.findElements(By.className(locatorValue));
			break;
		default:
			throw new Exception("Support FindElement with 'id' 'name' 'xpath' 'tag' 'link' 'css' 'class'");
		}
		return elements;
	}

	public void iFrameHandle(By iFrameNodeSelector,By iFrameBodySelector, String text) throws InterruptedException {
		WebElement iframeElem = driver.findElement(iFrameNodeSelector);
		driver.switchTo().frame(iframeElem);
		Thread.sleep(10);
		WebElement iframeBodyElem = driver.findElement(iFrameBodySelector);
		iframeBodyElem.clear();
		iframeBodyElem.sendKeys(text);
        LogUtils.info("IFrameHandle found text: " + text);
	}

	public void dndHandle(By leftColumnSelector,By rightColumnSelector) throws AWTException {
		WebElement leftColumnElem = driver.findElement(leftColumnSelector);
		WebElement rightColumnElem = driver.findElement(rightColumnSelector);

		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size from all elements

		Dimension fromSize = leftColumnElem.getSize();
		Dimension toSize = rightColumnElem.getSize();

		//Get the location for all elemets
		Point fromLocation = leftColumnElem.getLocation();
		Point toLocation = rightColumnElem.getLocation();


		// CAlculate the cordinate for the moves
		fromLocation.x += fromSize.width / 2;
		fromLocation.y += fromSize.height / 2 + fromSize.height;
		toLocation.x += toSize.width /2;
		toLocation.y += toSize.height /2 + toSize.height;

		// Use the robot instance to make the moves

		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(fromLocation.x,fromLocation.y);
		robot.mousePress(InputEvent.BUTTON1_MASK);

		// Move to the target position
		robot.mouseMove(toLocation.x,toLocation.y);

		// Release the mouse
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void JsAlert(By jsAlertSelector ) {
		driver.findElement(jsAlertSelector).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void JsConfirmAlert(By jsConfirmbtnSelector) {
		driver.findElement(jsConfirmbtnSelector).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void JsPomptAlert(By jsPromtSelector, String sendtext) {
		driver.findElement(jsPromtSelector).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		alert.sendKeys(sendtext);
		alert.accept();
	}

	public void rightClickHandle(By Locator ) {
		Actions actions = new Actions(driver);
		WebElement RightClickPlaceElem = driver.findElement(Locator);
		actions.contextClick(RightClickPlaceElem).perform();
        LogUtils.info("Right click found text: " + RightClickPlaceElem.getText());

		WebDriverWait wait = new WebDriverWait(driver,30);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void waitUntilElementDisplayed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
        LogUtils.info("Element Displayed: " + element.getText());
	}

	public void hoverOnElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
        LogUtils.info("Element Hovered: " + element.getText());
	}
}
