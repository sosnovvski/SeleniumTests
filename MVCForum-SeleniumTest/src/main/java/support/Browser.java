package support;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class Browser {

	private RemoteWebDriver driver;

	public Browser(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}
	public void open(String url) {
		driver.get(url);
	}

	public void quit() {
		driver.quit();
	}

	public WebElement find(By by) {
		return find(by, driver);
	}

	public WebElement find(By by, SearchContext searchContext) {
		return searchContext.findElement(by);
	}

	public List<WebElement> findAll(By by) {
		return driver.findElements(by);
	}

	public void type(By by, CharSequence charSequence) {
		type(find(by), charSequence);
	}

	public void type(WebElement element, CharSequence value) {
		element.sendKeys(value);
	}

	public void click(By by) {
		click(by, driver);
	}

	public void click(By by, SearchContext searchContext) {
		WebElement element = searchContext.findElement(by);
		element.click();
	}

	public void moveToElement(WebElement element) {
		new Actions(driver).moveToElement(element).perform();
	}

	public void doubleClick(WebElement element) {
		new Actions(driver).doubleClick(element).perform();
	}

	public void executeScript(String script, Object... arguments) {
		driver.executeScript(script, arguments);
	}

}
