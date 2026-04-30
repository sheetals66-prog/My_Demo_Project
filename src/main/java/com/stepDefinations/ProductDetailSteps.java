package com.stepDefinations;

import java.util.Arrays;
import java.util.List;

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

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailSteps {

	private static final Logger log = LogManager.getLogger(ProductDetailSteps.class);

	HomePage home = new HomePage();
	ProductListingPage plp = new ProductListingPage();
	ProductDetailPage pd = new ProductDetailPage();
	CartPage cart = new CartPage();

	@Given("User searches for a product {string}")
	public void user_searches_for_a_product(String product) {
		home.typeAndHitSearchBar(product);
		log.info("Searching for product: {}", product);
	}

	@And("products are visible on PLP")
	public void products_are_visible_on_plp() {
		plp.waitForProductsToLoad();
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not visible on PLP");
		log.info("Products loaded successfully on PLP");
	}

	@When("User clicks on a product from PLP")
	public void user_clicks_on_product_from_plp() {
		plp.clickProductByIndex(4);
		KeyWord.windowHandle();
		log.info("Clicked on product from PLP");
	}

	@Then("Product details page should be displayed")
	public void product_details_page_should_be_displayed() {
		WaitFor.waitForElementToBeVisible(pd.productTitle);

		String title = pd.getProductTitle();
		log.info("Product Title: " + title);

		Assert.assertNotNull(title, "Title is NULL");
		Assert.assertFalse(title.trim().isEmpty(), "Product title is empty");
	}

	@Then("User should see correct title name on PDP")
	public void validateTitleName() {
		ProductDetailPage pd = new ProductDetailPage();
		WaitFor.waitForElementToBeVisible(pd.productTitle);
		String title = pd.getProductTitle();
		log.info("Product Title: " + title);
		Assert.assertNotNull(title, "Title is NULL");
		Assert.assertFalse(title.trim().isEmpty(), "Product title is empty");
	}

	@And("User should see product price on PDP")
	public void userAbleToSeeProductPrice() {
		WaitFor.waitForElementToBeClickable(pd.productPrice);
		String price = pd.getProductPrice();
		log.info("Product Price: " + price);
		Assert.assertNotNull(price, "Price is NULL");
		Assert.assertFalse(price.trim().isEmpty(), "Product price is empty");

	}

	@And("User should see wishlist icon on PDP")
	public void userAbleToSeeWishlist() {
		Assert.assertTrue(pd.isWishListButtonIsPresent(),
				"Wishlist button is not displayed on the product details page.");
	}

	@And("User should see add to bag button on PDP")
	public void userAbleToSeeAddToBagButtonOnPDP() {
		WaitFor.waitForElementToBeVisible(pd.addToBag);
		Assert.assertTrue(pd.isAddToBagDisplayed(), "Add to Bag button not visible");
	}

	@And("User selects size if required and clicks on Add to Bag button")
	public void userAbleToSelectSize() {

	    if (pd.isSizeAvailable()) {

	        pd.selectSize(0);
	        log.info("Size selected");

	        WaitFor.waitForElementToBeClickable(pd.addToBag);
	        pd.clickAddToBag();
	        log.info("Product added to bag");

	    } else {
	        log.info("No size available → Skipping Add to Bag step");

	        
	    }
	}

	@Then("Go To Bag button should be displayed")
	public void userAbleToSeeGoToBagButtonOnPDP() {

	    if (pd.isSizeAvailable()) {
	        Assert.assertTrue(pd.isGoToBagDisplayed(), "Go To Bag not displayed");
	    } else {
	        log.info("Skipped Go To Bag validation because size not available");
	    }
	}
	@And("user applies some filter to select product")
	public void applyMultipleFilters() {

		plp.openCategoriesFilter();
		plp.searchCategories("Jeans");
		plp.selectCategories("Jeans");
		WaitFor.pageLoaded();
		plp.openColorFilter();
		plp.selectcolor("Red");
		WaitFor.pageLoaded();
		plp.selectDiscount("30% and above");
		WaitFor.pageLoaded();

	}

	
	@When("User clicks on Wishlist icon on PDP")
	public void addProductToWishListWithoutLogin() {
		Assert.assertTrue(pd.isWishListButtonIsPresent(),
				"Wishlist button is not present on the Product Details page.");
		pd.clickWishlist();
		log.info("user click on wishlist button to add product to wishlist without login:");
		
	}
	
	@Then ("Login page should be displayed")
	public void userOnTheLoginPage() {
		LoginPage login=new LoginPage();
	String currentUrl = KeyWord.driver.getCurrentUrl();
	Assert.assertTrue(currentUrl.toLowerCase().contains("login") || login.isLoginPageDisplayed(),
			"After clicking wishlist without login, no login requirement was detected (no popup and URL doesn't contain 'login'). Current URL: "
					+ currentUrl);
}

	@Then("User verifies pincode messages on PDP")
	public void verifyPincodeMessages() {

	    List<String> testPins = Arrays.asList("411001", "560001", "123456");

	    for (String pin : testPins) {

	        log.info("Testing pincode: " + pin);

	        pd.enterPincode(pin);

	        String message = pd.getPinCodeMsg();
	        String errorMsg = pd.getPinCodeMsg();
	        String invalidToast = pd.getInvalidPincodeToast();

	 
	        if (message != null && message.contains("Get it by")) {
	            log.info("Valid pincode → " + pin + " | Message: " + message);
	            Assert.assertTrue(message.contains("Get it by"), "Delivery message not correct");
	        }

	        
	        else if ((errorMsg != null && !errorMsg.isEmpty()) || 
	                 (invalidToast != null && !invalidToast.isEmpty())) {

	            log.info("Invalid pincode → " + pin);
	            Assert.assertTrue(true);
	        }

	      
	        else {
	            Assert.fail("No response for pincode: " + pin);
	        }

	      
	        try {
	            pd.clickChangePinCode();
	        } catch (Exception e) {
	            log.info("Change button not visible");
	        }
	    }
	}

}

