package com.pages;

import org.openqa.selenium.By;
import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class HomePage {
	By searchresult = By.xpath("//input[contains(@class,'desktop-searchBar')]");
	By Noresult = By.xpath("//p[@class=\"index-infoBig\"]");

	public void clickOnSearchResult() {
		WaitFor.waitForElementToBeClickable(searchresult);
		KeyWord.clickOn(searchresult);
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

	public boolean isHomePageDisplayed() {
			return KeyWord.isDisplayed(searchresult);
		
	}

	
}
