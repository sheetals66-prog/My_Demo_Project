package com.pages;


import org.openqa.selenium.By;

import com.testbase.Keyword;

import static com.testbase.Keyword.*;

public class SearchResult {
	By searchresult = By.xpath("//input[@placeholder=\"Search for products, brands and more\"]");

	public void clickOnSearchResult() {
		Keyword.clickOn(searchresult);
	}
	
	/*
	 * public void TypeOnSearchBar(String Text) {
	 * Keyword.enterText(searchresult,Text); }
	 */
	public void typeAndHitSearchBar(String Text) {
	Keyword.enterTextAndHit(searchresult,Text);
}
}
