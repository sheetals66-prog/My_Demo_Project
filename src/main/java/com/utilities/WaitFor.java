package com.utilities;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testbase.KeyWord;

public class WaitFor {

// Removed static initializer that accessed Keyword.driver too early

	public static WebDriverWait getWait() {
	    WebDriverWait wait =
	        new WebDriverWait(KeyWord.driver, Duration.ofSeconds(30));

	    wait.pollingEvery(Duration.ofMillis(500));
	    wait.ignoring(NoSuchElementException.class);

	    return wait;
	}

	/**
	 * Waits until the element is visible on screen. Returns the element once it
	 * becomes visible.
	 *
	 * Use before: reading text, checking if displayed
	 */
	private WaitFor() {
		// Private constructor to prevent instantiation
	}

    public static void waitForElements(List<WebElement> elements) {
        getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

   
    public static void waitForElementToBeVisible(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
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
		// TODO Auto-generated method stub
		getWait().until(ExpectedConditions.visibilityOfElementLocated(brand_search));
		
	}

	
	
	
}