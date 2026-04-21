package com.Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.dataprovider.MyntraSearchTest;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class HomePageTest extends Testbase {

	@Test
	public void verifySearchProductIsDisplayed() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		System.out.println("Products are Displayed..");
	}

	@Test(dataProvider = "SearchData", dataProviderClass = MyntraSearchTest.class)
	public void toVerifySearchProduct(String searchText) throws InterruptedException {
		System.out.println("Searching for: " + searchText);
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int countText = plp.getProductCountAfterFilter();
		Assert.assertTrue(countText > 0, "No products found for search: " + searchText);
	}

	@Test(dataProvider = "invalidSearchData", dataProviderClass = MyntraSearchTest.class)
	public void toVerifySearchProductWithInvalidData(String searchText) {
		System.out.println("Searching for: " + searchText);
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		boolean isNoResultsDisplayed = sr.isNoResultMessageDisplayed();
		boolean productDisplayed=plp.productsIsDisplayedBeforeApplyingFilters();
		Assert.assertTrue(isNoResultsDisplayed || productDisplayed, "Unexpected behavior for search: " + searchText);
	}
	
	@Test
	public void toverifyNoresultsDisplayWithoutSearch() {
		HomePage sr = new HomePage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("@@@@@");
		Assert.assertTrue(sr.isNoResultMessageDisplayed(), "products are not displayed without search");
	}
	@Test
	public void toverifyNoresultsDisplayWithoutEmptySearch() {
		HomePage sr = new HomePage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("   ");
		Assert.assertTrue(sr.isHomePageDisplayed(), "products are not displayed without search");
	}
	@Test
	public void toverifyNoresultsDisplayWithoutSpecialCharSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("!@#$%");
		Assert.assertNotSame(plp.getProductCountTextAfterApplyingFilter(), "products are not displayed without search");
	}
	
	
	@Test
	public void toverifyNoresultsDisplayWithoutNumericSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("123456");
		Assert.assertNotSame(pd.productTitle, "products are not displayed without search");
	}
	
	@Test
	public void toverifyNoresultsDisplayWithoutAlphaNumericSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("abc123");
		Assert.assertNotSame(plp.getProductCountTextAfterApplyingFilter(), "products are not displayed without search");
	}
	
	@Test
	public void toverifyNoresultsDisplayWithoutCaseSensitiveSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("KIDS");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		System.out.println("Products are Displayed..");
	}
	@Test
	public void toverifyNoresultsDisplayWithoutLeadingTrailingSpacesSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("   kids   ");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		System.out.println("Products are Displayed..");
	}
	
	@Test
	public void toverifyNoresultsDisplayWithoutSpecialCharNumericSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("!@#abc123");
		Assert.assertNotSame(plp.getProductCountTextAfterApplyingFilter(), "products are not displayed without search");
	}
	
	@Test
	public void toverifyNoresultsDisplayWithoutSpecialCharCaseSensitiveSearch() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("!@#KIDS");
		Assert.assertNotSame(plp.getProductCountTextAfterApplyingFilter(), "products are not displayed without search");
	}

}
