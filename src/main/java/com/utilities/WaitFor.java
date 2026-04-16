package com.utilities;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testbase.Keyword;

public class WaitFor {

    // Removed static initializer that accessed Keyword.driver too early

    private static WebDriverWait getWait() {
        if (Keyword.driver == null) {
            throw new IllegalStateException("WebDriver is not initialized yet. Call openBrowser() before using WaitFor.");
        }
        WebDriverWait wait = new WebDriverWait(Keyword.driver, Duration.ofSeconds(10));
        try {
            wait.pollingEvery(Duration.ofMillis(500));
        } catch (Throwable t) {
            // older/newer selenium might not support pollingEvery; ignore if not available
        }
        try {
            wait.ignoring(NoSuchElementException.class);
        } catch (Throwable t) {
            // ignore
        }
        return wait;
    }

    public static void waitForElements(List<WebElement> elements) {
        getWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // Wait for element visible
    public static void waitForElementToBeVisible(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element clickable
    public static void waitForElementToBeClickable(By element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    // Wait for element presence in DOM
    public static void waitForPresence(By locator) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for page reload (old element stale, new element visible)
    public static void waitForPageReload(WebElement oldElement, By newLocator) {
        if (oldElement != null) {
            getWait().until(ExpectedConditions.stalenessOf(oldElement)); // old container gone
        }
        getWait().until(ExpectedConditions.visibilityOfElementLocated(newLocator)); // new container visible
    }

    // Wait for page title
    public static boolean waitForTitle(String title) {
        return getWait().until(ExpectedConditions.titleContains(title));
    }

    // Wait for URL change
    public static void waitForUrl(String url) {
        getWait().until(ExpectedConditions.urlContains(url));
    }

    public static void waitForElementToBeClickable(List<WebElement> productcards) {
        // TODO Auto-generated method stub
        getWait().until(ExpectedConditions.visibilityOfAllElements(productcards));
    }

    public static void waitForElementToBeVisible(WebElement productTitle) {
        // TODO Auto-generated method stub
        getWait().until(ExpectedConditions.visibilityOf(productTitle));
    }

}