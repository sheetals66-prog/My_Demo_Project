package StepDefinations;

import static com.testbase.KeyWord.driver;

import java.util.Locale.Category;
import java.util.logging.LogManager;

import org.openqa.selenium.manager.SeleniumManagerOutput.Log;
import org.testng.Assert;

import com.pages.HomePage;
import com.pages.ProductListingPage;
import com.utilities.WaitFor;

import Hooks.Hooks;
import io.cucumber.core.logging.Logger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ProductListingPageStep {
//private static final Logger LOG = LogManager.getLogger(Hooks.class);
	
	HomePage srp = new HomePage();
	ProductListingPage plp = new ProductListingPage();

	@When("User searches for {string}")
	public void user_searches_for(String product) {
		srp.clickOnSearchResult();
		srp.typeAndHitSearchBar(product);
	}

	@And("products should be visible on product listing page")
	public void products_should_be_visible() {
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not visible on PLP");
	}

	@Then("product count should be greater than 0")
	public void product_count_should_be_greater_than_0() {
		int count = plp.getProductCountBeforeFilter();
		Assert.assertTrue(count > 0, "Product count is zero");
	}

	@And("user applies on category filter {string}")
	public void user_applies_category_filter(String category) {

		plp.openCategoriesFilter();
		plp.searchCategories(category);

		plp.selectCategories(category);

		WaitFor.waitForElementToBeVisible(plp.title_count);
	}

	
	@Then("User should see category filter {string} applied")
	public void verify_category_filter(String category) {
		boolean result = plp.isCategoryFilterApplied(category);
		//LOG.info("colour filter applied successfully....!!!1" + result);
		Assert.assertTrue(result, "Category filter NOT applied: " + category);
	}
	
	
	@And("User applies colour filter {string}")
	public void user_applies_colour_filter(String color) {

		plp.openColorFilter();
		plp.selectcolor(color);

		WaitFor.waitForElementToBeVisible(plp.title_count);
	}

	@Then("User should see colour filter applied in URL")
	public void verify_colour_in_url() {

		String url = plp.getCurrentUrl();
		Assert.assertTrue(url.contains("color"), "Color filter not applied in URL");
	}

	
	@And("User applies discount filter {string}")
	public void user_applies_discount_filter(String discount) {

		plp.selectDiscount(discount);
		WaitFor.waitForElementToBeVisible(plp.title_count);
	}

	@Then("User should see discount filter applied in URL")
	public void verify_discount_in_url() {

		String url = plp.getCurrentUrl();
		Assert.assertTrue(url.contains("discount"), "Discount filter not applied in URL");
	}

	
	@Then("Product listing page should be displayed")
	public void product_listing_page_should_be_displayed() {

		Assert.assertTrue(plp.isProductDetailPageLoaded(), "PLP page not loaded");
	}
}
