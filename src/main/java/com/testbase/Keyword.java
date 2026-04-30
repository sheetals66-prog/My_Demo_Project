package com.testbase;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.utilities.WaitFor;

public class KeyWord {

	public static RemoteWebDriver driver;

	public static void openBrowser(String browsername) {

		if (browsername.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--incognito");

			driver = new ChromeDriver(option);
		} else if (browsername.equalsIgnoreCase("Firefox")) {
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("-private");
			driver = new FirefoxDriver(option);
		} else if (browsername.equalsIgnoreCase("Edge")) {
			EdgeOptions option = new EdgeOptions();
			option.addArguments("--inprivate");
			driver = new EdgeDriver(option);
		} else {
			throw new InvalidArgumentException("Invalid browser :  " + browsername);
		}

		System.out.println("Browser is launched Successfully...........");
	}

	public static void getUrl(String url) {
		driver.get(url);

	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();

	}

	public static void clickOn(String LocatorType, String Locator) {
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(Locator)).click();
		} else if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("tagname")) {
			driver.findElement(By.tagName(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("linktext")) {
			driver.findElement(By.linkText(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("partiallinktext")) {
			driver.findElement(By.partialLinkText(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("classname")) {
			driver.findElement(By.className(Locator)).click();

		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(Locator)).click();
		}
	}

	public static void enterText(String locatorType, String Locator, String Text) {
		if (locatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("tagname")) {
			driver.findElement(By.tagName(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("linktext")) {
			driver.findElement(By.linkText(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("partiallinktext")) {
			driver.findElement(By.partialLinkText(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("cssSelector")) {
			driver.findElement(By.cssSelector(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("classname")) {
			driver.findElement(By.className(Locator)).sendKeys(Text);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(Locator)).sendKeys(Text);
		}
	}

	public static void tearDown() {
		driver.quit();

	}

	public static void windowHandle() {
		String currentWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		if (set.size() > 1) {
			for (String window : set) {
				if (!window.equals(currentWindow)) {
					driver.switchTo().window(window);
				}
			}
		}
	}

	public static boolean clickOn(WebElement option) {
		option.click();
		return false;
	}

	public static void clickOn(By option) {
		driver.findElement(option).click();

	}

	public static WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public static void enterText(By searchresult, String text) {
		driver.findElement(searchresult).sendKeys(text);

	}

	public static void enterTextAndHit(By searchresult, String text) {
		enterText(searchresult, text);
		driver.findElement(searchresult).sendKeys(Keys.ENTER);
	}

	public static String getText(By text) {

		return driver.findElement(text).getText();
	}

	public static void scrollToElement(By Locator) {

		WebElement element = driver.findElement(Locator);
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();

	}

	public static void waitForSeconds(int mili) {
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	public static String getCurrentUrl() {

		return driver.getCurrentUrl();
	}

	public static void scrollToElement(WebElement size) {
		Actions action = new Actions(driver);
		action.moveToElement(size).perform();

	}

	public static void navigate() {

		driver.navigate().refresh();
	}

	public static void scrollToElement(int i) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public static boolean isDisplayed(WebElement element) {
		WaitFor.waitForElementToBeVisible(element);
		return element.isDisplayed();
	}

	public static boolean isDisplayed(By locator) {
		WaitFor.waitForElementToBeVisible(locator);
		return KeyWord.driver.findElement(locator).isDisplayed();
	}

}
