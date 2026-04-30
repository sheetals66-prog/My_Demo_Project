package StepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;
import com.utilities.WaitFor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceOrderStep {

	private static final Logger log = LogManager.getLogger(ProductDetailSteps.class);

	HomePage sr = new HomePage();
	ProductListingPage plp = new ProductListingPage();
	ProductDetailPage pd = new ProductDetailPage();
	CartPage cart = new CartPage();
	LoginPage login = new LoginPage();

	@Given("user searches for product {string}")

	public void userSearchesForProduct(String search) {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
	}

	@Given("user applies Boys filter on PLP")
	public void userAppliesBoysFilterOnPLP() {
		plp.selectBoysFilter();
	}

	@Given("user applies Tshirts category filter")
	public void userAppliesTshirtsCategoryFilter() {
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
	}

	@Given("user selects a product from PLP")
	public void userSelectsProductFromPLP() {
		plp.clickProductByIndex(4);

	}

	@Given("user selects product size if available")
	public void userSelectsProductSizeIfAvailable() {

		WaitFor.pageLoaded();
		KeyWord.windowHandle();
		pd.selectSize(0);
	}

	@Given("user adds product to bag and navigates to cart page")
	public void userAddsProductToBagAndNavigatesToCartPage() {
		pd.clickAddToBag();
		pd.clickGoToBag();
	}

	@When("user applies coupon {string} on cart page")
	public void userAppliesCouponOnCartPage(String coupon) {
		WaitFor.pageLoaded();
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
	}

	@When("user clicks on Place Order button")
	public void userClicksOnPlaceOrderButton() {
		WaitFor.pageLoaded();
		cart.clickPlaceOrder();
	}

	@Then("user should be navigated to checkout page")
	public void userShouldBeNavigatedToCheckoutPage() {
		Assert.assertTrue(login.isLoginPageDisplayed(), "User is not able to see login Page");
	}
}
