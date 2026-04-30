package StepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.pages.HomePage;
import com.pages.ProductListingPage;
import com.utilities.WaitFor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductListingPageStep {

	private static final Logger log = LogManager.getLogger(ProductListingPageStep.class);

	HomePage home = new HomePage();
	ProductListingPage plp = new ProductListingPage();

	@When("User searches for {string}")
	public void userSearchesForProduct(String product) {
		log.info("Searching product: {}", product);
		home.clickOnSearchResult();
		home.typeAndHitSearchBar(product);
	}

	@And("products should be visible on product listing page")
	public void verifyProductsVisible() {
		log.info("Checking products visibility on PLP");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not visible on PLP");
	}

	@Then("product count should be greater than 0")
	public void verifyProductCount() {
		int count = plp.getProductCountBeforeFilter();
		log.info("Product count: {}", count);

		Assert.assertTrue(count > 0, "Product count is zero");
	}

	@And("user applies category filter {string}")
	public void applyCategoryFilter(String category) {

		log.info("Applying category filter: {}", category);

		plp.openCategoriesFilter();
		plp.searchCategories(category);
		plp.selectCategories(category);

		WaitFor.waitForElementToBeVisible(plp.title_count);
	}

	@Then("User should see  {string}  category filter applied in URL")
	public void verifyCategoryFilter(String category) {
		log.info("Category filter applied: " + category);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(category.toLowerCase()),
				"URL does not contain the applied Category filter");
		log.info("Category  filter applied successfully..");

	}

	@And("User applies colour filter {string}")
	public void applyColorFilter(String colour) {
		log.info("Applying category filter: {}", colour);
		plp.openColorFilter();
		plp.selectcolor(colour);
		WaitFor.waitForElementToBeVisible(plp.title_count);
		
	}

	@Then("User should see {string} colour filter applied in URL")
	public void verifyColorInUrl(String colour) {
		log.info("Colour filter applied: " + colour);
		String ActualUrl = plp.getPlpUrl();
		Assert.assertTrue(ActualUrl.toLowerCase().contains(colour.toLowerCase()),
				"URL does not contain the applied Colour filter");
		log.info("Colour filter applied successfully..");
	}

	@And("User applies discount filter {string}")
	public void applyDiscountFilter(String discount) {
		log.info("Applying category filter: {}", discount);
		plp.selectDiscount(discount);
		log.info("selected discount: " + discount);
		
	}

	@Then("User should see {string} discount filter applied in URL")
	public void verifyDiscountInUrl(String Discount) {
		log.info("discount filter applied: " + Discount);
		String ActualUrl = plp.getPlpUrl();
		  String expectedValue = Discount.replaceAll("[^0-9]", "");
		Assert.assertTrue(ActualUrl.contains(expectedValue),
		        "Discount not applied correctly in URL: " + ActualUrl);
		log.info("discount filter applied successfully..");
		
		
	}

	@Then("Product listing page should be displayed")
	public void verifyProductListingPage() {

		log.info("Verifying PLP page loaded");

		Assert.assertTrue(plp.isProductDetailPageLoaded(), "PLP page not loaded");
	}
}