package com.Tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.listener.Mylistener;
import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Testbase;
import com.utilities.WaitFor;

@Listeners(Mylistener.class)
public class ProductListingPageTest extends Testbase {
	SearchResult sr = new SearchResult();
	ProductListingPage plp = new ProductListingPage();

	@Test
	public void toVerifyBoysFilterIsSelected() throws InterruptedException {
		// Step 1: Click search bar
		sr.clickOnSearchResult();

		// Step 2: Search kids
		sr.typeAndHitSearchBar("kids");

		// Step 3: Verify product listing page loaded
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());

		// Step 4: Get count before filter
		int before = plp.getProductCountBeforeFilter();
		System.out.println("products count before filter:" + before);

		// Step 5: Apply boys filter
		plp.selectBoysFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);

		// Step 6: Get count after filter
		int after = plp.getProductCountAfterFilter();
		System.out.println("products count after filter:" + after);

		// Step 7: Validate filter applied
		Assert.assertTrue(before >= after, "filter is not working properly");

	}

	@Test
	public void toVerifyGirlsFilterIsSelected() {
		// Step 1: Click search bar
		sr.clickOnSearchResult();
		// Step 2: Search kids
		sr.typeAndHitSearchBar("kids");
		// Step 3: Get count before filter
		int before = plp.getProductCountBeforeFilter();
		System.out.println("products count before filter:" + before);
		// Step 4: Apply boys filter
		plp.selectGirlsFilter();
		// Step 5: Get count before filter
		int after = plp.getProductCountAfterFilter();
		System.out.println("products count after filter:" + after);
		// Step 7: Validate filter applied
		Assert.assertTrue(before >= after, "filter is not working properly");

	}

	@Test
	public void toVerifyCategoriesFilterIsSelected() {

		sr.clickOnSearchResult();

		sr.typeAndHitSearchBar("kids");

		int before = plp.getProductCountBeforeFilter();

		// open categories filter
		plp.openCategoriesFilter();

		// search category
		plp.searchCategories("Tshirts");
		System.out.println("searched category: Tshirts");

		// select category
		plp.selectCategories("Tshirts");

		WaitFor.waitForElementToBeVisible(plp.title_count);

		int after = plp.getProductCountAfterFilter();

		Assert.assertTrue(after <= before);
	}

	@Test
	public void toVerifyBrandFilterIsSelected() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openBrandFilter();
		plp.searchBrand("Puma");
		System.out.println("searched brand: Puma");
		plp.selectBrandDirectly("Puma");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(after <= before);

	}

	@Test
	public void toVerifycolorFilterIsSelected() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openColorFilter();
		plp.selectcolor("Red");
		int after = plp.getProductCountAfterFilter();

		// Main validation
		Assert.assertTrue(plp.isColorFilterApplied("Red"), "Red filter is not applied");

		// Optional validation
		Assert.assertTrue(after <= before, "Product count did not reduce after applying filter");
	}

	@Test
	public void toVerifyDiscountFilterIsSelected() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();

		plp.selectDiscount("50% and above");
		System.out.println("selected discount: 50% and above");
		int after = plp.getProductCountAfterFilter();

		// Main validation
		Assert.assertTrue(plp.isDiscountFilterApplied(), "Discount filter is not applied");

		// Optional validation
		Assert.assertTrue(after <= before, "Product count did not reduce after applying discount filter");
	}

	@Test
	public void toVerifyAgeFilerIsSelected() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();

		plp.openAgeFilter();

		plp.selectAge("8Y-10Y");
		System.out.println("selected age: 2-4 years");
		int after = plp.getProductCountAfterFilter();

		// Main validation
		Assert.assertTrue(plp.isAgeFilterApplied("8Y-10Y"), "Age filter is not applied");

		// Optional validation
		Assert.assertTrue(after <= before, "Product count did not reduce after applying age filter");
	}

	@Test
	public void toVerifySizeFilterIsSelected() {

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openSizeFilter();
		plp.selectSize("S");
		System.out.println("selected size: S");
		int after = plp.getProductCountAfterFilter();

		// Main validation
		Assert.assertTrue(plp.isSizeFilterApplied("S"));

		// Optional validation
		Assert.assertTrue(after <= before, "Product count did not reduce after applying size filter");
	}

	@Test
	public void toVerifyPriceFilterIsSelected() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();

		plp.openSortOptions();
		plp.selectSortOption("Price: Low to High");

		int after = plp.getProductCountAfterFilter();

		// validation
		Assert.assertTrue(after <= before, "Product count did not reduce after applying price filter");

	}

	@Test
	public void toVerifyClearAllFilters() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		// Apply any filter
		plp.openSizeFilter();
		plp.selectSize("S");

		int afterFilter = plp.getProductCountAfterFilter();
		// Apply Clear All

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		plp.clearAllFilters();
		int afterClear = plp.getProductCountAfterFilter();
		// Filter applied
		Assert.assertTrue(afterFilter < before, "Filter not applied");
		// Clear all worked
		Assert.assertTrue(afterClear > afterFilter, "Clear All not working");

	}

	@Test
	public void toVerifyProductIsSelected() {

		// Step 1: Wait for search bar and type
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");

		// Step 2: Wait for product page
		WaitFor.waitForElementToBeVisible(plp.title_count);

		// Step 3: Apply filters
		plp.selectBoysFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);

		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.isProductDetailPageLoaded();

		// Use direct brand selection 
		plp.openBrandFilter();
		plp.selectBrandDirectly("BAESD");
		WaitFor.waitForElementToBeVisible(plp.title_count);

		plp.openColorFilter();
		plp.searchColor("Red");
		plp.selectcolor("Red");
		WaitFor.waitForElementToBeVisible(plp.title_count);

		// Step 4: Click product
		plp.clickProductByIndex(0);
		Assert.assertTrue(plp.isProductDetailPageLoaded(), "Product detail page did not load");

		System.out.println("Product is selected");
	}

}
