package com.Tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Keyword;
import com.testbase.Testbase;
import com.utilities.ExcelUtils;
import com.utilities.WaitFor;

public class EndToEndTestCase extends Testbase {

	@DataProvider(name = "TestData")
	public Object[][] getExcelData() throws Exception {
		Object[][] data = ExcelUtils.getData("E:\\MyntraTestData.xlsx", "TestData");
		System.out.println("ROWS: " + data.length);
		System.out.println("COLS: " + data[0].length);

		return data;
	}

	@Test(dataProvider = "TestData")
	public void toVerifySearchProduct(String searchText, String category, String brand, String color)
			throws InterruptedException {

		System.out.println("Searching for: " + searchText + " | Category: " + category + " | brand: " + brand
				+ " | color: " + color);
		Keyword.driver.get("https://www.myntra.com/");
		SearchResult sr = new SearchResult();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();

		// Step 1: Wait for search bar and type
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);

		WaitFor.waitForElementToBeVisible(plp.title_count);

//  category selection
		plp.openCategoriesFilter();
		plp.searchCategories(category);
		plp.selectCategories(category);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Category filter applied: " + category);
		plp.isProductDetailPageLoaded();
		

		// brand selection
		plp.openBrandFilter();
		plp.selectBrandDirectly(brand);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Brand filter applied: " + brand);
		plp.isProductDetailPageLoaded();

		// color selection
		plp.openColorFilter();
		plp.searchColor(color);
		plp.selectcolor(color);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		System.out.println("Color filter applied: " + color);
		plp.isProductDetailPageLoaded();

		// Click product
		plp.clickProductByIndex(0);
		/*
		 * Assert.assertTrue(plp.isProductDetailPageLoaded(),
		 * "Product detail page did not load");
		 * 
		 * System.out.println("Product is selected");
		 */
	}
}
