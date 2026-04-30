package com.stepDefinations;

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

public class CartPageStep {
	HomePage homePage = new HomePage();
	ProductListingPage plp = new ProductListingPage();
	ProductDetailPage pdp = new ProductDetailPage();
	CartPage cart;

	@Given("User searches for  product {string}")
	public void userSearchesForProduct(String product) {
		homePage.clickOnSearchResult();
		homePage.typeAndHitSearchBar(product);
	}

	@And("User selects a product from PLP")
	public void userSelectsProductFromPLP() {
		plp.clickProductByIndex(4);
		KeyWord.windowHandle();

	}

	@And("User adds product to bag and navigates to cart page")
	public void userAddsProductToBagAndNavigatesToCartPage() {

		try {
			pdp.selectSize(0); 
		} catch (Exception e) {
			System.out.println("Size not available, skipping...");
		}

		pdp.clickAddToBag();
		pdp.clickGoToBag();
		Assert.assertTrue(pdp.isGoToBagDisplayed(), "Go To Bag button not visible");
		
	}

	@Then("User should see Place Order button")
	public void userShouldSeePlaceOrderButton() {
		Assert.assertTrue(cart.isPlaceOrderButtonDisplayed(), "Place Order button not visible");
	}

	@When("User clicks on Place Order button")
	public void userClicksOnPlaceOrderButton() {
		cart.clickPlaceOrder();
	}

	@Then("User should be navigated to checkout page")
	public void userShouldBeNavigatedToCheckoutPage() {
		LoginPage login = new LoginPage();

		Assert.assertTrue(login.isLoginPageDisplayed(), "Checkout page did not load");
	}

	@Then("User should see price details on cart page")
	public void userShouldSeePriceDetails() {
		String priceDetails = cart.getPriceDetails();
		Assert.assertNotNull(priceDetails);
		Assert.assertFalse(priceDetails.trim().isEmpty());
	}

	@When("User removes the product")
	public void userRemovesTheProduct() {
		cart.selectProduct();
		cart.clickRemove();
		cart.clickRemovePopup();
	}

	@Then("Product should be removed from cart")
	public void productShouldBeRemoved() {
		Assert.assertFalse(cart.isProductPresent(), "Product not removed");
	}

	@When("User moves product to wishlist")
	public void userMovesProductToWishlist() {
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickMoveToWishlistPopup();
	}

	@When("User cancels wishlist move")
	public void userCancelsWishlistMove() {
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickCancelButton();
	}

	@Then("Product should remain in cart")
	public void productShouldRemainInCart() {
		Assert.assertFalse(cart.isProductPresent(), "Product should be removed from cart");
	}

	@When("User applies coupon")
	public void userAppliesCoupon() {
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
	}

	@Then("Coupon should be applied successfully")
	public void couponShouldBeApplied() {
		Assert.assertTrue(cart.isCouponApplied(), "Coupon not applied");
	}

	@Given("User selects product without size")
	public void userSelectsProductWithoutSize() {
		HomePage homePage = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		homePage.clickOnSearchResult();
		homePage.typeAndHitSearchBar("kids");
		plp.clickProductByIndex(4);
		KeyWord.windowHandle();
	}

	@When("User clicks Add to Bag")
	public void userClicksAddToBag() {
		pdp.clickAddToBag();
	}

	@Then("Error message should be displayed")
	public void errorMessageShouldBeDisplayed() {
		ProductDetailPage pd = new ProductDetailPage();
		Assert.assertTrue(pd.isErrormsgdisplayed());
	}
	@When("User enters invalid coupon {string}")
	public void userEntersInvalidCoupon(String code) {
	    cart.clickCouponButton();
	    cart.enterCoupon(code);
	    cart.clickApplyCouponButton();
	}

	@Then("Error message should be shown")
	public void errorMessageShouldBeShown() {
	    Assert.assertTrue(cart.isCouponErrorDisplayed());
	}
}
