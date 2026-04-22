package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class HomePage {
	By searchresult = By.xpath("//input[contains(@class,'desktop-searchBar')]");
	By Noresult = By.xpath("//p[@class=\"index-infoBig\"]");
	By suggestions = By.xpath("//li[contains(@class,'desktop-suggestion')]");
	
	@FindBy(xpath = "(//a[@href=\"/my/orders\"])[2]")
	WebElement myOrdersLink;

	@FindBy(xpath = "//span[text()=\"Wishlist\"]")
	WebElement wishlistIcon;

	public void clickOnSearchResult() {
		WaitFor.waitForElementToBeClickable(searchresult);
		KeyWord.clickOn(searchresult);
	}

	public void typeText(String text) {
		KeyWord.enterText(searchresult, text); // only type, no Enter
	}

	public void typeAndHitSearchBar(String Text) {
		KeyWord.enterTextAndHit(searchresult, Text);
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
		return KeyWord.getText(Noresult);
	}

	public boolean isHomePageDisplayed() {
		return KeyWord.isDisplayed(searchresult);

	}

	public void clickOnWishlistIcon() {
		WaitFor.waitForElementToBeClickable(wishlistIcon);
		wishlistIcon.click();
	}

	public void clickOnMyOrders() {
		WaitFor.waitForElementToBeClickable(myOrdersLink);
		myOrdersLink.click();

	}
}
