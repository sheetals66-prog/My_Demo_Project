package com.Tests;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.dataprovider.MyntraSearchTest;
import com.listener.Mylistener;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;

import com.testbase.Testbase;
import com.utilities.WaitFor;

//@Listeners(Mylistener.class)
public class ProductListingPageTest extends Testbase {
	@Test
	public void toVerifyProductListingPageIsLoaded() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.isProductDetailPageLoaded(), "Product listing page did not load properly");
	}

	@Test
	public void toVerifyProductsAreDisplayed() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not displayed on listing page");
		System.out.println("Products are Displayed..");
	}

	@Test
	public void toVerifyProductCountIsDisplayed() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Product count is not displayed");
		int count = plp.getProductCountBeforeFilter();
		System.out.println("Total products count: " + count);
		Assert.assertTrue(count > 0, "Product count should be greater than zero");
	}

	@Test
	public void toVerifyProductCountIsDisplayedAfterApplyingFilter() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not displayed on listing page");
		int before = plp.getProductCountBeforeFilter();
		System.out.println("products count before filter:" + before);
		plp.selectGirlsFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		System.out.println("products count after filter:" + after);
		Assert.assertTrue(after <= before, "Product count did not reduce after applying filter");
	}

	@Test
	public void toVerifyBoysFilterIsSelected() throws InterruptedException {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		int before = plp.getProductCountBeforeFilter();
		System.out.println("products count before filter:" + before);
		plp.selectBoysFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		System.out.println("products count after filter:" + after);
		Assert.assertTrue(before >= after, "filter is not working properly");

	}

	@Test
	public void toVerifyGirlsFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		int before = plp.getProductCountBeforeFilter();
		System.out.println("products count before filter:" + before);
		plp.selectGirlsFilter();
		int after = plp.getProductCountAfterFilter();
		System.out.println("products count after filter:" + after);
		Assert.assertTrue(before >= after, "filter is not working properly");

	}

	@Test
	public void toVerifyCategoriesFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		System.out.println("searched category: Tshirts");
		plp.selectCategories("Tshirts");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(after <= before);
	}

	@Test(dataProvider = "Category", dataProviderClass = MyntraSearchTest.class)
	public void toVerifyApplyFiltersIsWorking(String category) {

		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories(category);
		plp.selectCategories(category);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Category filter applied: " + category);
		plp.isProductDetailPageLoaded();
		Assert.assertTrue(plp.isProductDetailPageLoaded(),
				"Product detail page did not load after applying category filter: " + category);
	}

	@Test(dataProvider = "BrandData", dataProviderClass = MyntraSearchTest.class)
	public void toVerifyApplyBrandFiltersIsWorking(String brand) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openBrandFilter();
		plp.selectBrandDirectly(brand);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Brand filter applied: " + brand);
		plp.isProductDetailPageLoaded();
		Assert.assertTrue(plp.isProductDetailPageLoaded(),
				"Product detail page did not load after applying brand filter: " + brand);

	}

	@Test
	public void toVerifyBrandFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
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
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openColorFilter();
		plp.selectcolor("Red");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(plp.isColorFilterApplied("Red"), "Red filter is not applied");
		Assert.assertTrue(after <= before, "Product count did not reduce after applying filter");
	}

	@Test(dataProvider = "colourDataForTshirts", dataProviderClass = MyntraSearchTest.class)
	public void toVerifyApplyColorFiltersIsWorking(String color) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openColorFilter();
		plp.selectcolor(color);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Color filter applied: " + color);
		plp.isProductDetailPageLoaded();
		Assert.assertTrue(plp.isProductDetailPageLoaded(),
				"Product detail page did not load after applying color filter: " + color);
	}


	@Test(dataProvider = "sortBy", dataProviderClass = MyntraSearchTest.class)
	public void verifySortByFilterOnPlpPageForTShirts(String sortByOption) {

		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		plp.openSortOptions();
		plp.selectSortOption(sortByOption);
		Assert.assertTrue(plp.isSortOptionApplied(sortByOption), "Sort By filter not applied correctly");
		System.out.println("Sort By filter applied successfully..");
	}
	
	
	
	@Test(  dataProvider = "discountFilterForTshirts", dataProviderClass = MyntraSearchTest.class)
		public void toVerifyDiscountFilterOnPlpPageForTshirts(String discountRange) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		plp.selectDiscount(discountRange);
		System.out.println("selected discount: "+discountRange);
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(plp.isDiscountFilterApplied(), "Discount filter is not applied");
		Assert.assertTrue(after <= before, "Product count did not reduce after applying discount filter");
		    }

	 


	@Test(dataProvider = "CombinData", dataProviderClass = MyntraSearchTest.class)
	public void verify_products_are_displayed_when_search_and_filters_applied(String SearchText, String Category,
			String Color, String Brand) {

		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(SearchText);
		plp.openCategoriesFilter();
		plp.searchCategories(Category);
		plp.selectCategories(Category);
		plp.openColorFilter();
		plp.selectcolor(Color);
		plp.openBrandFilter();
		plp.selectBrandDirectly(Brand);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Filters applied - Search: " + SearchText + ", Category: " + Category + ", Color: " + Color
				+ ", Brand: " + Brand);
		Assert.assertTrue(plp.isProductDetailPageLoaded(),
				"Product detail page did not load after applying combined filters");

	}

	@Test
	public void toVerifyDiscountFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.selectDiscount("50% and above");
		System.out.println("selected discount: 50% and above");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(plp.isDiscountFilterApplied(), "Discount filter is not applied");
		Assert.assertTrue(after <= before, "Product count did not reduce after applying discount filter");
	}

	@Test
	public void toVerifyAgeFilerIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openAgeFilter();
		plp.selectAge("8Y-10Y");
		System.out.println("selected age: 2-4 years");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(plp.isAgeFilterApplied("8Y-10Y"), "Age filter is not applied");
		Assert.assertTrue(after <= before, "Product count did not reduce after applying age filter");
	}

	@Test
	public void toVerifySizeFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openSizeFilter();
		plp.selectSize("S");
		System.out.println("selected size: S");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(plp.isSizeFilterApplied("S"));
		Assert.assertTrue(after <= before, "Product count did not reduce after applying size filter");
	}

	@Test
	public void toVerifyPriceFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openSortOptions();
		plp.selectSortOption("Price: Low to High");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(after <= before, "Product count did not reduce after applying price filter");
	}

	@Test
	public void toVerifyClearAllFilters() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openSizeFilter();
		plp.selectSize("S");
		int afterFilter = plp.getProductCountAfterFilter();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		plp.clearAllFilters();
		int afterClear = plp.getProductCountAfterFilter();
		Assert.assertTrue(afterFilter < before, "Filter not applied");
		Assert.assertTrue(afterClear > afterFilter, "Clear All not working");

	}

	@Test
	public void toVerifyProductIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.selectBoysFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.isProductDetailPageLoaded();
		plp.openBrandFilter();
		plp.selectBrandDirectly("BAESD");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.openColorFilter();
		plp.searchColor("Red");
		plp.selectcolor("Red");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		plp.clickProductByIndex(0);
		Assert.assertTrue(plp.isProductDetailPageLoaded(), "Product detail page did not load");
		System.out.println("Product is selected");
	}

	/*
	 * Negative test case for search and filter combination that should yield no
	 * results
	 */

	@Test
	public void verifyNoresultsForInvalidFilter() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openCategoriesFilter();
		plp.searchCategories("Tsthsis");
		plp.selectCategories("Tsthsis");
		int after = plp.getProductCountAfterFilter();

		Assert.assertEquals(after, before, "Product count not changed even when invalid category applied");
	}

	@Test
	public void verifyNoresultsDisplayForInvalidBrand() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.isBoysFilterSelected();
		plp.openBrandFilter();
		plp.searchBrand("KY");
		plp.selectBrandDirectly("KY");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count not changed even when invalid Brand applied");

	}

	@Test
	public void toverifyNoresultsDisplayForInvalidColor() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openColorFilter();
		plp.searchColor("pink123");
		plp.selectcolor("pink123");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count not changed even when invalid colorapplied");

	}

	@Test
	public void verifyNoResultsForInvalidDiscount() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.selectDiscount("10% ");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count not changed even when invalid Brand applied");
	}

//need to check
	@Test
	public void verifyNoResultsForInvalidSearchWithValidBrand() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("@@@@");

		plp.openBrandFilter();
		plp.selectBrandDirectly("mapu");

		Assert.assertTrue(plp.isNoResults(), "Invalid search should not return products");
	}

	@Test
	public void verifyNoResultsForInvalidSearchWithDiscount() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("@@@@");

		plp.selectDiscount("50% and above");
		Assert.assertTrue(plp.isNoResults(), "Invalid search should not return products");
	}

	@Test
	public void verifyNoResultsForMultipleInvalidFilters() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openBrandFilter();
		plp.selectBrandDirectly("Puma");
		plp.openColorFilter();
		plp.selectcolor("Purple");
		plp.selectDiscount("80% and above");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count not changed even when invalid Brand applied");

	}

	@Test
	public void verifyColorWithoutOpeningFilter() {
		ProductListingPage plp = new ProductListingPage();

		try {
			plp.selectcolor("Red");
			Assert.fail("Color selected without opening filter");
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void verifyClearFilterWithoutApplying() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		plp.clearAllFilters();
		Assert.assertTrue(true, "Clear filter should not crash");
	}

	@Test
	public void verifySortWhenNoProducts() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		plp.openSortOptions();
		plp.selectSortOption("Price: Low to High");
		Assert.assertTrue(plp.isNoResults(), "Products are not displayed");
	}

}
