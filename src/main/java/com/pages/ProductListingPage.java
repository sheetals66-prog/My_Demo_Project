package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.testbase.Keyword;
import com.utilities.WaitFor;

import static com.testbase.Keyword.*;

import java.time.Duration;
import java.util.List;

public class ProductListingPage {
	// page url
	String pageLoad = "https://www.myntra.com/kids?rawQuery=kids";
	By breadcrum = By.xpath("//h1[text()='Kids Wear Online Store']");

	// products and count
	By products = By.xpath("//li[@class=\"product-base\"]");
	public By title_count = By.xpath("//span[@class='title-count']");

	// filters
	By filters = By.xpath("//span[text()='FILTERS']");
	By boys_filter = By.xpath("//label[contains(text(),'Boys')]");
	By girls_filter = By.xpath("//label[contains(text(),'Girls')]");

	// filter options
	By categories = By.xpath("//span[contains(text(),'Categories')]");
	By categories_searchbar = By
			.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[1]");
	By categories_list = By.xpath("//ul[@class='categories-list']");
	By categories_search = By.xpath("//input[@placeholder=\"Search for Categories\"]");

	// brand
	By brand = By.xpath("//span[contains(text(),'Brand')]");
	By brand_searchbar = By.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[2]");
	By brand_list = By.xpath("//ul[@class='brand-list']");
	By brand_search = By.xpath("//input[contains(@placeholder,'Brand')]");

	// price
	By price = By.xpath("//span[text()='Price']");
	By price_range = By.xpath("//div[@class='slider-root']");

	// color
	By color = By.xpath("//span[text()='Color']");
	By color_searchbar = By.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[3]");
	By color_list = By.xpath("//ul//li[@class='colour-listItem']");
	By color_search = By.xpath("//input[@placeholder='Search for Color']");

	// discount
	By discount = By.xpath("//span[text()='Discount Range']");
	By discount_list = By.xpath("//label[@class=\"common-customRadio vertical-filters-label\"]");
	By discount_option = By
			.xpath("//div[@class='vertical-filters-filters']//label[contains(text(),'" + discount + "')]");

	//
	By agefilter = By.xpath("//h4[text()='Age']");

	By bundles = By.xpath("//label[contains(text(),'Bundles')]");
	By bundlesoption = By.xpath("//ul[@class='atsa-filters']//label[contains(text(),' \" + bundles + \" ')]");

	By countryOfOrigin = By.xpath("//h4[contains(text(),'Country of Origin')]");
	By countryOfOriginoption = By
			.xpath("//ul[@class='atsa-filters']//label[contains(text(),' \" + CountryofOrigin + \" ')]");

	By size = By.xpath("//h4[text()=\"Size\"]");

	// sort by options
	By sort = By.xpath("//div[@class=\"sort-sortBy\"]");
	By sort_list = By.xpath("//label[contains(text(),'\" + sortOption + \"')]");

	// selected products after filters
	By productcards = By.xpath("//li[@class=\"product-base\"]");

	// clear all filters
	By clear_all = By.xpath("//span[text()=\"CLEAR ALL\"]");

	// Verify Kids page loaded
	public void loadPage() {
		getUrl(pageLoad);
	}

	// Verify page loaded using Breadcrum
	public boolean isKidsPageDisplayed() {
		return getElement(breadcrum).isDisplayed();
	}

	//
	public boolean productsIsDisplayedBeforeApplyingFilters() {
		return getElement(products).isDisplayed();
	}

	// Get product count before applying filter
	public String getProductCountTextBeforeApplyingFilter() {
		return getElement(title_count).getText();
	}

	// Verify Filters section is displayed
	public boolean isFiltersSectionDisplayed() {
		return getElement(filters).isDisplayed();
	}

	// Apply Boys filter
	public void selectBoysFilter() {

		getElement(boys_filter).click();

	}

	// Apply Girls filter
	public void selectGirlsFilter() {
		getElement(girls_filter).click();
	}

	// verify Boys filter is selected
	public boolean isBoysFilterSelected() {
		return getElement(boys_filter).isSelected();
	}

	// verify Girls filter is selected
	public boolean isGirlsFilterSelected() {
		return getElement(girls_filter).isSelected();
	}

	// Verify Categories filter section is displayed
	public boolean isCategoriesSectionDisplayed() {
		return getElement(categories).isDisplayed();
	}

	// open categories filter
	public void openCategoriesFilter() {

		getElement(categories_searchbar).click();

	}

	// Verify categories list is displayed
	public boolean isCategoriesListDisplayed() {
		return getElement(categories_list).isDisplayed();
	}

	// Enter category name in category search box
	public void searchCategories(String category) {

		WaitFor.waitForElementToBeVisible(categories_search);

		getElement(categories_search).clear();

		getElement(categories_search).sendKeys(category);
	}

	// Select category from categories list
	public void selectCategories(String categoryname) {

		// Defensive checks and normalization
		if (categoryname == null) {
			System.out.println("selectCategories: categoryname is null");
			return;
		}

		String cat = categoryname.trim();
		if (cat.isEmpty()) {
			System.out.println("selectCategories: categoryname is empty after trim");
			return;
		}

		// Ensure categories list is visible
		try {
			WaitFor.waitForElementToBeVisible(categories_list);
		} catch (Exception e) {
			// try to open the categories filter if not visible
			try {
				openCategoriesFilter();
				WaitFor.waitForElementToBeVisible(categories_list);
			} catch (Exception e2) {
				System.out.println("selectCategories: categories list not visible");
			}
		}

		// Build XPath safely depending on quotes in the category text
		String xpath;
		if (cat.contains("'")) {
			xpath = "//label[contains(.,\"" + cat + "\")]";
		} else {
			xpath = "//label[contains(.,'" + cat + "')]";
		}

		By option = By.xpath(xpath);

		// Wait a short while for the option to appear (search may be async)
		try {
			WaitFor.waitForElementToBeVisible(option);
		} catch (Exception e) {
			// ignore - we'll try alternative matching below
		}

		// Try direct find first
		try {
			if (driver.findElements(option).size() > 0) {
				WebElement el = getElement(option);
				// click using JS to avoid overlay issues
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", el);
				js.executeScript("arguments[0].click();", el);
				return;
			}
		} catch (Exception e) {
			System.out.println("selectCategories: direct option click failed: " + e.getMessage());
		}

		// Fallback: iterate through labels under categories_list and match ignoring case
		try {
			List<WebElement> labels = driver.findElements(By.xpath("//ul[@class='categories-list']//label"));
			for (WebElement label : labels) {
				String text = label.getText();
				if (text != null && text.trim().toLowerCase().contains(cat.toLowerCase())) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", label);
					js.executeScript("arguments[0].click();", label);
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("selectCategories: fallback label search failed: " + e.getMessage());
		}

		System.out.println("Category not found: " + categoryname);
	}

	// Verify Brand filter section is displayed
	public boolean isBrandOptionDisplayed() {
		return getElement(brand).isDisplayed();
	}

	// open brand filter
	public void openBrandFilter() {

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll to Brand section
	    WebElement brandSection = getElement(brand);
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", brandSection);

	    // Click Brand (expand)
	    brandSection.click();

	    // Wait for animation
	    try {
	        Thread.sleep(1500);
	    } catch (InterruptedException e) {}

	    // Click search icon
	    WaitFor.waitForElementToBeClickable(brand_searchbar);
	    getElement(brand_searchbar).click();
	}
	// Verify brand list is displayed
	public boolean isBrandListDisplayed() {
		return getElement(brand_list).isDisplayed();
	}

	
	  //Enter brand name in brand search box 
	public void searchBrand(String brandname) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    WebElement input = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(brand_search)
	    );

	    input.clear();
	    input.sendKeys(brandname);
	}

	// Select brand from brand list
	
	public void selectBrandDirectly(String brandname) {

	    By brandOption = By.xpath("//label[contains(.,'" + brandname + "')]");

	    WebElement element = getElement(brandOption);

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    js.executeScript("arguments[0].click();", element);
	}
	

	// Verify Price Filter Section Displayed
	public boolean isPriceFilterSectionDisplayed() {
		return getElement(price).isDisplayed();
	}

	// Verify Price Range Slider Displayed
	public boolean isPriceRangeDisplayed() {
		return getElement(price_range).isDisplayed();
	}

	// Verify color filter section is displayed
	public boolean isColorOptionDisplayed() {
		return getElement(color).isDisplayed();
	}

//open color filter
	public void openColorFilter() {

	    WebElement element = getElement(color);

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // Scroll properly
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

	    // Small wait for overlay to settle
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {}

	    // 🔥 Force click using JS (bypasses overlay issue)
	    js.executeScript("arguments[0].click();", element);
	}

	// Verify color list is displayed
	public boolean isColorListDisplayed() {
		return getElement(color_list).isDisplayed();
	}

	// Enter color name in brand search box
	public void searchColor(String colorname) {
		getElement(color_search).sendKeys(colorname);
	}

	// Select color from color list
	public void selectcolor(String colorname) {
		By color_select = By.xpath("//li[@class='colour-listItem']//input[@value='" + colorname + "']/parent::label");
		WaitFor.waitForElementToBeClickable(color_select);
		getElement(color_select).click();
	}

	// Verify discount filter section is displayed
	public boolean isDiscountFilterDisplayed() {
		return getElement(discount).isDisplayed();
	}

	// Verify discount list is displayed
	public boolean isDiscountListDisplayed() {
		return getElement(discount_list).isDisplayed();
	}

	// Select discount from discount list
	public void selectDiscount(String selectdiscount) {
		By discount_option = By
				.xpath("//div[@class='vertical-filters-filters']//label[contains(text(),'" + selectdiscount + "')]");
		getElement(discount_option).click();
	}

	// Verify age filter section is displayed
	public boolean isAgeFilterDisplayed() {
		return getElement(agefilter).isDisplayed();
	}

	// open Age filter
	public void openAgeFilter() {
		getElement(agefilter).click();
	}

	// select Age
	public void selectAge(String selectage) {
		By ageOption = By.xpath("//label[contains(text(),'" + selectage + "')]");

		WaitFor.waitForElementToBeVisible(ageOption);

		WebElement element = getElement(ageOption);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);

	}

	// Verify size section is displayed
	public boolean IsSizeSectionDisplayed() {
		return getElement(size).isDisplayed();
	}

	// open size filter
	public void openSizeFilter() {
		WebElement element = getElement(size);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		WaitFor.waitForElementToBeClickable(size);
		element.click();
	}

	private String mapSize(String size) {
		switch (size.toUpperCase()) {
		case "S":
			return "Small";
		case "M":
			return "Medium";
		case "L":
			return "Large";
		default:
			return size;
		}
	}

	// select size
	public void selectSize(String sizeValue) {

		String mappedSize = mapSize(sizeValue);

		By select_size = By.xpath("//label[contains(.,'" + mappedSize + "')]");

		WaitFor.waitForElementToBeVisible(select_size);

		WebElement element = getElement(select_size);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		js.executeScript("arguments[0].click();", element);
	}

	public boolean isSizeSelected(String sizeValue) {
		By selectedSize = By
				.xpath("//label[contains(text(),'" + sizeValue + "') and contains(@class,'common-customCheckbox')]");
		return getElement(selectedSize).isDisplayed();
	}

	// verify sort Section is displayed
	public boolean isSortSectionDisplayed() {
		return getElement(sort).isDisplayed();
	}

	// open sort options
	public void openSortOptions() {
		getElement(sort).click();
	}

	private String mapSort(String sort) {
		if (sort.equalsIgnoreCase("lowToHigh"))
			return "Low to High";
		else if (sort.equalsIgnoreCase("highToLow"))
			return "High to Low";
		return sort;
	}

	// select sort option
	public void selectSortOption(String sortOption) {
		By sort_option = By.xpath("//label[contains(text(),'" + sortOption + "')]");
		WaitFor.waitForElementToBeVisible(sort_option);

		WebElement element = getElement(sort_option);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// scroll properly (center)
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		// wait little for UI to settle
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// click using JS (VERY IMPORTANT)
		js.executeScript("arguments[0].click();", element);

	}

	// Get product count after applying filter
	public String getProductCountTextAfterApplyingFilter() {
		return getElement(title_count).getText();
	}

	// Get product count before applying filter
	public int getProductCountBeforeFilter() {

		String text = Keyword.getText(title_count);

		String number = text.replaceAll("[^0-9]", "");

		return Integer.parseInt(number);
	}

	// Get product count after applying filter
	public int getProductCountAfterFilter() {

		String text = Keyword.getText(title_count);

		String number = text.replaceAll("[^0-9]", "");

		return Integer.parseInt(number);
	}

	// verify products are unselected after clear all
	public void clearAllFilters() {
		WebElement element = getElement(clear_all);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);

	}

	public boolean isColorFilterApplied(String color) {

		return driver.getCurrentUrl().toLowerCase().contains(color.toLowerCase());
	}

	public boolean isDiscountFilterApplied() {
		return driver.getCurrentUrl().toLowerCase().contains("discount");
	}

	public boolean isAgeFilterApplied(String ageValue) {

		return driver.getCurrentUrl().toLowerCase().contains(ageValue.toLowerCase());
	}

	public boolean isSizeFilterApplied(String sizeValue) {

		return driver.getCurrentUrl().toLowerCase().contains(sizeValue.toLowerCase());
	}

	public boolean isSortOptionApplied(String sortOption) {
		return driver.getCurrentUrl().toLowerCase().contains(sortOption.toLowerCase());
	}

	public void clickProductByIndex(int index) {

		List<WebElement> products = Keyword.driver.findElements(By.xpath("//li[@class='product-base']"));

		WebElement product = products.get(index);

		// Scroll (important for Myntra)
		JavascriptExecutor js = (JavascriptExecutor) Keyword.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", product);

		// Wait
		WebDriverWait wait = new WebDriverWait(Keyword.driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(product));

		// Click
		product.click();
	}

	public void searchSortBy(String sortOrder) {
		// TODO Auto-generated method stub
		selectSortOption(sortOrder);

	}

	public void searchAgeGroup(String ageGroup) {
		// TODO Auto-generated method stub
		selectAge(ageGroup);

	}
	public void waitForProductsToLoad() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    wait.until(driver -> driver.findElements(products).size() > 0);
	}

	public boolean isProductDetailPageLoaded() {
		Keyword.getCurrentUrl().contains("/product/");
		return true;
	}

}
