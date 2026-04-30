package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

import static com.testbase.KeyWord.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProductListingPage {

	String pageLoad = "https://www.myntra.com/kids?rawQuery=kids";
	By breadcrum = By.xpath("//h1[text()='Kids Wear Online Store']");

	By products = By.xpath("//li[@class=\"product-base\"]");
	public By title_count = By.xpath("//span[@class='title-count']");

	By filters = By.xpath("//span[text()='FILTERS']");
	By boys_filter = By.xpath("//label[contains(text(),'Boys')]");
	By girls_filter = By.xpath("//label[contains(text(),'Girls')]");

	By categories = By.xpath("//span[contains(text(),'Categories')]");
	By categories_searchbar = By
			.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[1]");
	By categories_list = By.xpath("//ul[@class='categories-list']");
	By categories_search = By.xpath("//input[@placeholder=\"Search for Categories\"]");

	By brand = By.xpath("//span[contains(text(),'Brand')]");
	By brandInput = By.xpath("//input[contains(@placeholder,'Brand')]");
	By brand_searchbar = By.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[2]");
	By brand_list = By.xpath("//ul[@class='brand-list']");

	By price = By.xpath("//span[text()='Price']");
	By price_range = By.xpath("//div[@class='slider-root']");

	By color = By.xpath("//span[text()='Color']");
	By color_searchbar = By.xpath("(//span[@class=\"myntraweb-sprite filter-search-iconSearch sprites-search\"])[3]");
	By color_list = By.xpath("//ul//li[@class='colour-listItem']");
	By color_search = By.xpath("//input[@placeholder='Search for Color']");
	@FindBy(xpath = "//div[@id='rootRailThumbLeft']")
	WebElement leftSlider;

	@FindBy(xpath = "//div[@id='rootRailThumbRight']")
	WebElement rightSlider;

	By discount = By.xpath("//span[text()='Discount Range']");
	By discount_list = By.xpath("//label[@class=\"common-customRadio vertical-filters-label\"]");

	By agefilter = By.xpath("//h4[text()='Age']");

	By bundles = By.xpath("//label[contains(text(),'Bundles')]");

	By countryOfOrigin = By.xpath("//h4[contains(text(),'Country of Origin')]");

	By size = By.xpath("//h4[text()=\"Size\"]");

	@FindBy(css = ".sort-list li")
	List<WebElement> sortOptions;
	@FindBy(css = ".sort-sortBy, .sort-label")
	WebElement sortButton;

	By sort = By.xpath("//div[@class=\"sort-sortBy\"]");
	// sort option locator is dynamic and built via helper below
	By sortlist = By.xpath("//ul[@class=\"sort-list\"]/li");

	By productcards = By.xpath("//li[@class=\"product-base\"]");

	By clear_all = By.xpath("//span[text()=\"CLEAR ALL\"]");

	public boolean isPageLoaded() {
		return driver.getCurrentUrl().contains("kids") && driver.getTitle().toLowerCase().contains("kids");
	}

	public String getPlpUrl() {
		return KeyWord.driver.getCurrentUrl();
	}

	public boolean getPlpBreadcrum() {
		return getElement(breadcrum).isDisplayed();
	}

	//
	public boolean productsIsDisplayedBeforeApplyingFilters() {
		return getElement(products).isDisplayed();
	}

	public String getProductCountTextBeforeApplyingFilter() {
		return getElement(title_count).getText();
	}

	public boolean isFiltersSectionDisplayed() {
		return getElement(filters).isDisplayed();
	}

	public void selectBoysFilter() {

		getElement(boys_filter).click();

	}

	public void selectGirlsFilter() {
		getElement(girls_filter).click();
	}

	public boolean isBoysFilterSelected() {
		return getElement(boys_filter).isSelected();
	}

	public boolean isGirlsFilterSelected() {
		return getElement(girls_filter).isSelected();
	}

	public boolean isCategoriesSectionDisplayed() {
		return getElement(categories).isDisplayed();
	}

	public void openCategoriesFilter() {
		getElement(categories_searchbar).click();

	}

	public boolean isCategoriesListDisplayed() {
		return getElement(categories_list).isDisplayed();
	}

	public void searchCategories(String category) {
		WaitFor.waitForElementToBeVisible(categories_search);
		getElement(categories_search).clear();
		getElement(categories_search).sendKeys(category);
	}

	public void selectCategories(String categoryname) {

		if (categoryname == null) {
			System.out.println("selectCategories: categoryname is null");
			return;
		}

		String cat = categoryname.trim();
		if (cat.isEmpty()) {
			System.out.println("selectCategories: categoryname is empty after trim");
			return;
		}

		try {
			WaitFor.waitForElementToBeVisible(categories_list);
		} catch (Exception e) {

			try {
				openCategoriesFilter();
				WaitFor.waitForElementToBeVisible(categories_list);
			} catch (Exception e2) {
				System.out.println("selectCategories: categories list not visible");
			}
		}
		String xpath;
		if (cat.contains("'")) {
			xpath = "//label[contains(.,\"" + cat + "\")]";
		} else {
			xpath = "//label[contains(.,'" + cat + "')]";
		}

		By option = By.xpath(xpath);

		try {
			WaitFor.waitForElementToBeVisible(option);
		} catch (Exception e) {

		}

		try {
			if (driver.findElements(option).size() > 0) {
				WebElement el = getElement(option);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", el);
				js.executeScript("arguments[0].click();", el);
				return;
			}
		} catch (Exception e) {
			System.out.println("selectCategories: direct option click failed: " + e.getMessage());
		}

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

	public boolean isBrandOptionDisplayed() {
		return getElement(brand).isDisplayed();
	}

	public void openBrandFilter() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement brandSearch = getElement(brand_searchbar);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", brandSearch);

		wait.until(ExpectedConditions.elementToBeClickable(brandSearch));

		try {
			brandSearch.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", brandSearch);
		}
	}

	public boolean isBrandListDisplayed() {
		return getElement(brand_list).isDisplayed();
	}

	public void searchBrand(String brandName) {

		By brandFilter = By.xpath("//span[normalize-space()='Brand']");
		WaitFor.waitForElementToBeLocated(brandFilter);
		driver.findElement(brandFilter).click();

		By searchBox = By.xpath("//input[@placeholder='Search for brand']");
		WaitFor.waitForElementToBeVisible(searchBox);

		driver.findElement(searchBox).sendKeys(brandName);
	}

	public void selectBrandDirectly(String brandname) {

	    By brandOption = By.xpath("//label[contains(.,'" + brandname + "')]");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    List<WebElement> brands = driver.findElements(brandOption);

	    if (brands.size() == 0) {
	        System.out.println("Brand not found: " + brandname);
	        return; 
	    }
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(brandOption));

	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block:'center'});", element);

	    element.click();
	}

	public boolean isPriceFilterSectionDisplayed() {
		return getElement(price).isDisplayed();
	}

	public boolean isPriceRangeDisplayed() {
		return getElement(price_range).isDisplayed();
	}

	public boolean isColorOptionDisplayed() {
		return getElement(color).isDisplayed();
	}

	public void openColorFilter() {

		WebElement element = getElement(color);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		js.executeScript("arguments[0].click();", element);
	}

	public boolean isColorListDisplayed() {
		return getElement(color_list).isDisplayed();
	}

	public void searchColor(String colorname) {
		WaitFor.waitForElementToBeVisible(color_search);
		getElement(color_search).sendKeys(colorname);
	}

	public void selectcolor(String colorname) {
		By color_select = By.xpath("//li[@class='colour-listItem']//input[@value='" + colorname + "']/parent::label");
		WaitFor.waitForElementToBeClickable(color_select);
		getElement(color_select).click();
	}

	public void setPriceSlider(int xOffsetLeft, int xOffsetRight) {

		RemoteWebDriver driver = KeyWord.driver;
		Actions actions = new Actions(driver);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", leftSlider);

		try {
			Thread.sleep(500);
		} catch (Exception e) {

		}

		actions.clickAndHold(leftSlider).moveByOffset(xOffsetLeft, 0).release().perform();

		actions.clickAndHold(rightSlider).moveByOffset(xOffsetRight, 0).release().perform();
	}

	public boolean isDiscountFilterDisplayed() {
		return getElement(discount).isDisplayed();
	}

	public boolean isDiscountListDisplayed() {
		return getElement(discount_list).isDisplayed();
	}

	private By discountOptionLocator(String discountText) {
		return By.xpath(
				"//div[@class='vertical-filters-filters']//label[contains(normalize-space(.),'" + discountText + "')]");
	}

	public void selectDiscount(String selectdiscount) {
		By discount_option = discountOptionLocator(selectdiscount);
		getElement(discount_option).click();
	}

	public boolean isAgeFilterDisplayed() {
		return getElement(agefilter).isDisplayed();
	}

	public void openAgeFilter() {
		WaitFor.waitForElementToBeVisible(agefilter);
		getElement(agefilter).click();
	}

	public void selectAge(String selectage) {
		By ageOption = By.xpath("//label[contains(text(),'" + selectage + "')]");
		WaitFor.waitForElementToBeVisible(ageOption);
		WebElement element = getElement(ageOption);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);

	}

	public boolean IsSizeSectionDisplayed() {
		return getElement(size).isDisplayed();
	}

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

	public void selectSize(String i) {

		String mappedSize = mapSize(i);
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

	public boolean isSortSectionDisplayed() {
		return getElement(sort).isDisplayed();
	}

	/** opens the dropdown and select by entering text **/
	public void sortBy(String optionText) {
		WaitFor.waitForElementToBeVisible(sortButton);
		WaitFor.waitForElementToBeClickable(sortButton);
		KeyWord.clickOn(sortButton);
		for (WebElement option : sortOptions) {
			if (option.getText().trim().equalsIgnoreCase(optionText)) {
				KeyWord.clickOn(option);
				return;
			}

		}
		throw new RuntimeException("Sort option not found: " + optionText);

	}

	public String getSelectedSortOption() {

		WaitFor.waitForElementToBeVisible(sortButton);

		return sortButton.getText().replace("Sort by :", "").trim();
	}

	public String getProductCountTextAfterApplyingFilter() {
		return getElement(title_count).getText();
	}

	public int getProductCountBeforeFilter() {
		String text = KeyWord.getText(title_count);
		String number = text.replaceAll("[^0-9]", "");
		return Integer.parseInt(number);
	}

	public int getProductCountAfterFilter() {
		String text = KeyWord.getText(title_count);
		String number = text.replaceAll("[^0-9]", "");
		return Integer.parseInt(number);
	}

	public void clearAllFilters() {
		WebElement element = getElement(clear_all);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		js.executeScript("arguments[0].click();", element);

	}

	public boolean isColorFilterApplied(String color) {
		return driver.getCurrentUrl().toLowerCase().contains(color.toLowerCase());
	}

	public boolean isDiscountFilterApplied(String string) {
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

	/*
	 * public void clickProductByIndex(int index) { List<WebElement> products =
	 * KeyWord.driver.findElements(By.xpath("//li[@class='product-base']"));
	 * WebElement product = products.get(index); JavascriptExecutor js =
	 * (JavascriptExecutor) KeyWord.driver;
	 * js.executeScript("arguments[0].scrollIntoView(true);", product);
	 * WebDriverWait wait = new WebDriverWait(KeyWord.driver,
	 * Duration.ofSeconds(10));
	 * wait.until(ExpectedConditions.elementToBeClickable(product));
	 * product.click(); }
	 */
	public void clickProductByIndex(int index) {

		By productsLocator = By.xpath("//li[@class='product-base']");
		WebDriverWait wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(10));

		wait.until(driver -> driver.findElements(productsLocator).size() > index);

		// FIRST FETCH
		WebElement product = KeyWord.driver.findElements(productsLocator).get(index);

		((JavascriptExecutor) KeyWord.driver).executeScript("arguments[0].scrollIntoView(true);", product);

		// WAIT AGAIN AFTER SCROLL (DOM refresh safe)
		wait.until(driver -> driver.findElements(productsLocator).size() > index);

		// SECOND FETCH (IMPORTANT)
		product = KeyWord.driver.findElements(productsLocator).get(index);

		wait.until(ExpectedConditions.elementToBeClickable(product));
		product.click();
	}

	public void searchAgeGroup(String ageGroup) {
		selectAge(ageGroup);

	}

	public void waitForProductsToLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(driver -> driver.findElements(products).size() > 0);
	}

	public boolean isProductDetailPageLoaded() {
		// return true only when the URL indicates a product detail or product elements
		// are visible
		try {
			String url = KeyWord.getCurrentUrl();
			if (url != null && url.toLowerCase().contains("/product/"))
				return true;
			// fallback: if product page has product cards it is not a detail page
			return driver.findElements(products).size() > 0 ? false : true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isNoResults() throws TimeoutException {

		By products = By.xpath("//li[@class='product-base']");
		By noResults = By.xpath(
				"//*[contains(text(),'No results') or contains(text(),'No Products') or contains(text(),'Sorry')]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(driver -> driver.findElements(products).size() > 0 || driver.findElements(noResults).size() > 0);

		return driver.findElements(noResults).size() > 0;
	}

	public boolean isCategoryFilterApplied(String category) {
		try {
			List<WebElement> appliedFilters = driver
					.findElements(By.xpath("//div[contains(@class,'appliedFilters')]//span"));

			for (WebElement filter : appliedFilters) {
				if (filter.getText().trim().equalsIgnoreCase(category.trim())) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("Error in isCategoryFilterApplied: " + e.getMessage());
		}
		return false;
	}

	public List<Integer> getAllProductPrices() {

		List<WebElement> priceElements = driver
				.findElements(By.xpath("//div[contains(@class,'product')]//span[contains(text(),'₹')]"));

		List<Integer> prices = new ArrayList<>();

		for (WebElement el : priceElements) {
			String priceText = el.getText(); // Example: "₹1,299"
			priceText = priceText.replaceAll("[^0-9]", ""); // remove ₹ and commas
			prices.add(Integer.parseInt(priceText));
		}

		return prices;
	}

	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

}
