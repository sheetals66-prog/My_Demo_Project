package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class HomePage {
	By searchresult = By.xpath("//input[contains(@class,'desktop-searchBar')]");
	By Noresult = By.xpath("//p[@class=\"index-infoBig\"]");
	By suggestions = By.xpath("//li[contains(@class,'desktop-suggestion')]");
	By myntraLogo = By.xpath("//a[@class='myntraweb-sprite desktop-logo sprites-headerLogo']");
	By logo = By.xpath("//a[@class='myntraweb-sprite desktop-logo sprites-headerLogo']");
	By myOrdersLink = By.xpath("(//a[@href=\"/my/orders\"])[2]");
	By wishlistIcon = By.xpath("//span[text()=\"Wishlist\"]");

	{
		PageFactory.initElements(KeyWord.driver, this);
	}

	public boolean isHomePageLoaded() {
		return KeyWord.driver.getTitle().contains("Online Shopping");
	}

	public boolean isLogoDisplayed() {
		WaitFor.waitForElementToBeVisible(myntraLogo);
		return KeyWord.driver.findElement(myntraLogo).isDisplayed();
	}

	public void clickOnSearchResult() {
		WaitFor.waitForElementToBeClickable(searchresult);
		KeyWord.clickOn(searchresult);
	}

	public void typeText(String text) {
		KeyWord.enterText(searchresult, text);
	}

	public void typeAndHitSearchBar(String product) {
	    By searchLocator = By.xpath("//input[contains(@class,'desktop-searchBar')]");
	    for (int i = 0; i < 2; i++) {
	        try {
	            WebElement searchBar = WaitFor.waitForElementToBeVisible(searchLocator);
	            searchBar.clear();
	            searchBar.sendKeys(product);
	            searchBar.sendKeys(Keys.ENTER);
	            WaitFor.pageLoaded();
	            break;

	        } catch (StaleElementReferenceException e) {
	            System.out.println("Retrying due to stale element...");
	        }
	    }
	}

	public boolean isNoResultMessageDisplayed() {
		try {
			return KeyWord.isDisplayed(Noresult);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAutoSuggestionDisplayed() {
		try {
			WaitFor.waitForElementToBeVisible(suggestions);
			return KeyWord.driver.findElements(suggestions).size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public String getCouldnotFindAnyMatches() {
		WaitFor.waitForElementToBeVisible(Noresult);
		return KeyWord.getText(Noresult);
	}

	public boolean isHomePageDisplayed() {
		return KeyWord.isDisplayed(searchresult);

	}

	public void clickOnWishlistIcon() {
		WaitFor.waitForElementToBeClickable(wishlistIcon);
		((WebElement) wishlistIcon).click();
	}

	public void clickOnMyOrders() {
		WaitFor.waitForElementToBeClickable(myOrdersLink);
		((WebElement) myOrdersLink).click();

	}

	public boolean SearchBarDisplayed() {
		WaitFor.waitForElementToBeVisible(searchresult);
		return KeyWord.driver.findElement(searchresult).isDisplayed();
	}

	public boolean isWishlistIconDisplayed() {
		WaitFor.waitForElementToBeVisible(wishlistIcon);
		return KeyWord.driver.findElement(wishlistIcon).isDisplayed();
	}
}
