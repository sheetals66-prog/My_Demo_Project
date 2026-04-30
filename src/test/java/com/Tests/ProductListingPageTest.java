package com.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.dataprovider.MyntraSearchTest;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.Testbase;
import com.utilities.WaitFor;


public class ProductListingPageTest extends Testbase {
	private static final Logger log = LogManager.getLogger(ProductListingPageTest.class);
	SoftAssert softly = new SoftAssert();

	@Test
	public void toVerifyProductListingPageIsLoaded() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		boolean status = plp.isPageLoaded();
		Assert.assertTrue(status, "Product listing page not loaded");

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
		log.info("Total products count: " + count);
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
		log.info("products count before filter:" + before);
		plp.selectGirlsFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		log.info("products count after filter:" + after);
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
		log.info("products count before filter:" + before);
		plp.selectBoysFilter();
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		log.info("products count after filter:" + after);
		Assert.assertTrue(before >= after, "filter is not working properly");

	}

	@Test
	public void toVerifyGirlsFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		int before = plp.getProductCountBeforeFilter();
		log.info("products count before filter:" + before);
		plp.selectGirlsFilter();
		int after = plp.getProductCountAfterFilter();
		log.info("products count after filter:" + after);
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
		log.info("searched category: Tshirts");
		plp.selectCategories("Tshirts");
		WaitFor.waitForElementToBeVisible(plp.title_count);
		int after = plp.getProductCountAfterFilter();
		softly.assertTrue(after > 0, "No products displayed after filter");
		softly.assertTrue(after <= before, " Product count increased after filter");
		softly.assertAll();
	}

	@Test(dataProvider = "Category", dataProviderClass = MyntraSearchTest.class)
	public void toVerifyAppliedCategoryFiltersIsWorking(String category) {

		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories(category);
		plp.selectCategories(category);
		log.info("Category filter applied: " + category);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(category.toLowerCase()),
				"URL does not contain the applied Category filter");
		log.info("Category  filter applied successfully..");

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
		log.info("Brand filter applied: " + brand);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(brand.toLowerCase()),
				"URL does not contain the applied Category filter");
		log.info("Brand filter applied successfully..");

	}

	@Test
	public void toVerifyBrandFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openBrandFilter();
		plp.selectBrandDirectly("YK");
		int after = plp.getProductCountAfterFilter();
		softly.assertTrue(after > 0, "No products displayed after filter");
		softly.assertTrue(after <= before, " Product count increased after filter");
		softly.assertAll();

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
		softly.assertTrue(after > 0, "No products displayed after filter");
		softly.assertTrue(after <= before, " Product count increased after filter");
		softly.assertAll();
		Assert.assertTrue(plp.isColorFilterApplied("Red"), "Red filter is not applied");

	}

	@Test(dataProvider = "colourDataForTshirts", dataProviderClass = MyntraSearchTest.class)
	public void toVerifyApplyColorFiltersIsWorking(String color) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		WaitFor.pageLoaded();
		plp.openColorFilter();
		plp.selectcolor(color);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(color.toLowerCase()),
				"URL does not contain the applied Category filter");
		log.info("Color filter applied successfully..");

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

		plp.sortBy(sortByOption);

		String SortText = plp.getSelectedSortOption();
		Assert.assertTrue(SortText.contains(sortByOption), "Sort By filter not applied correctly");
		log.info("Sort By filter applied successfully..");

	}

	@Test(dataProvider = "discountFilterForTshirts", dataProviderClass = MyntraSearchTest.class)
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
		log.info("selected discount: " + discountRange);
		int after = plp.getProductCountAfterFilter();
		
		Assert.assertTrue(after <= before, "Product count did not reduce after applying discount filter");
	}

	@Test
	public void toVerifyDiscountFilterIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.selectDiscount("50% and above");
		log.info("selected discount: 50% and above");
		int after = plp.getProductCountAfterFilter();
		
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
		plp.sortBy("Low to High");
		int after = plp.getProductCountAfterFilter();
		Assert.assertTrue(after <= before, "Product count did not reduce after applying price filter");
	}

	@Test
	public void toVerifyClearAllFunctionalityOfFilterss() {
		HomePage sr = new HomePage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");

		ProductListingPage plp = new ProductListingPage();
		plp.openBrandFilter();
		plp.selectBrandDirectly("BAESD");
		WaitFor.pageLoaded();
		plp.openColorFilter();
		plp.searchColor("Red");
		plp.selectcolor("Red");
		WaitFor.pageLoaded();
		int beforeFilterClearCount = plp.getProductCountBeforeFilter();
		WaitFor.pageLoaded();
		plp.clearAllFilters();
		int AfterClearFilterCount = plp.getProductCountBeforeFilter();
		String url = plp.getPlpUrl();

		softly.assertFalse(url.contains("f="), "Filter query still present in URL");
		softly.assertTrue(beforeFilterClearCount <= AfterClearFilterCount,
				"after clearing filters no products are displayed which is wrong");
	}

	@Test
	public void toVerifyProductIsSelected() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		plp.selectBoysFilter();
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		plp.isProductDetailPageLoaded();
		plp.openBrandFilter();
		plp.selectBrandDirectly("BAESD");
		plp.openColorFilter();
		plp.searchColor("Red");
		plp.selectcolor("Red");
		plp.clickProductByIndex(0);
		Assert.assertTrue(plp.isCategoryFilterApplied("BAESD"));
		Assert.assertTrue(plp.isColorFilterApplied("Red"));
		Assert.assertTrue(plp.isAgeFilterApplied("YK"));
		System.out.println("Product is selected");
	}


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
		plp.selectBrandDirectly("KY");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count should remain same for invalid Brand applied");

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
		Assert.assertEquals(after, before, "Product count should remain same for invalid color filter");

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
		Assert.assertEquals(after, before, "\"Product count should remain same for invalid Discount filter");
	}

	@Test
	public void verifyNoResultsForvalidSearchWithInvalidBrand() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openBrandFilter();
		plp.selectBrandDirectly("mapu");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product count should remain same for invalid brand applied");

	}



	@Test
	public void verifyNoResultsForMultipleInvalidFilters() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		int before = plp.getProductCountBeforeFilter();
		plp.openCategoriesFilter();
		plp.searchCategories("shitrs");
		plp.selectCategories("shitrs");
		plp.openBrandFilter();
		plp.selectBrandDirectly("KY");
		plp.isPageLoaded();
		plp.selectDiscount("80% and above");
		int after = plp.getProductCountAfterFilter();
		Assert.assertEquals(after, before, "Product countshould remain same for invalid multiple filter applied");

	}



	

}
