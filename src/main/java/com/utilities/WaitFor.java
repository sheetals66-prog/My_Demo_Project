package com.utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testbase.KeyWord;

public class WaitFor {

	public static WebDriverWait getWait() {
		WebDriverWait wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(30));

		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);

		return wait;
	}

		private WaitFor() {

	}

	public static void waitForElements(List<WebElement> elements) {
		getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static WebElement waitForElementToBeVisible(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeClickable(By element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeClickable(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForPresence(By locator) {
		getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForPageReload(WebElement oldElement, By newLocator) {
		if (oldElement != null) {
			getWait().until(ExpectedConditions.stalenessOf(oldElement)); // old container gone
		}
		getWait().until(ExpectedConditions.visibilityOfElementLocated(newLocator)); // new container visible
	}

	public static boolean waitForTitle(String title) {
		return getWait().until(ExpectedConditions.titleContains(title));
	}

	public static void waitForUrl(String url) {
		getWait().until(ExpectedConditions.urlContains(url));
	}

	public static void waitForElementToBeClickable(List<WebElement> productcards) {

		getWait().until(ExpectedConditions.visibilityOfAllElements(productcards));
	}

	public static void waitForElementToBeVisible(WebElement productTitle) {

		getWait().until(ExpectedConditions.visibilityOf(productTitle));
	}

	public static void until(ExpectedCondition<Boolean> attributeContains) {

		getWait().until(attributeContains);
	}

	public static void waitForElementToBeLocated(By brand_search) {
		
		getWait().until(ExpectedConditions.visibilityOfElementLocated(brand_search));

	}

	public static void pageLoaded() {
		WebDriverWait wait = getWait();

		wait.until(webDriver -> ((JavascriptExecutor) KeyWord.driver).executeScript("return document.readyState")
				.toString().equals("complete"));
	}

}