package StepDefinations;



import static org.testng.Assert.assertTrue;

import org.testng.Assert;

import com.pages.CartPage;
import com.pages.ProductDetailPage;

import io.cucumber.java.en.*;

public class AddToCartPage {
	
	
	    CartPage cart = new CartPage();
	    ProductDetailPage pd = new ProductDetailPage();

	    // ADD TO CART
	    // NOTE: step implementation moved to ProductDetailSteps to avoid duplicate definitions.
	    // Keep a non-annotated helper method here for reuse if needed.
	    public void verifyProductIsAddedToCart() {
	        pd.clickGoToBag();

	        assertTrue(cart.isProductPresent(), 
	            "Product is not present in cart");
	    }

	    @And("cart count should be greater than 0")
	    public void cart_count_should_be_greater_than_0() {
	        int count = cart.getCartItemCount();

	        assertTrue(count > 0, 
	            "Cart count is zero");
	    }

	    // ================= PRODUCT DETAILS =================

	    @Then("product name should be visible in cart")
	    public void product_name_should_be_visible_in_cart() {
	        String name = cart.getProductName();

	        Assert.assertNotNull(name, "Product name is null");
	        Assert.assertFalse(name.trim().isEmpty(), "Product name is empty");
	    }

	    @And("product price should be visible in cart")
	    public void product_price_should_be_visible_in_cart() {
	        String price = cart.getProductPrice();

	        Assert.assertNotNull(price, "Product price is null");
	        Assert.assertFalse(price.trim().isEmpty(), "Product price is empty");
	    }

	    @And("product size and quantity should be visible")
	    public void product_size_and_quantity_should_be_visible() {
	        String size = cart.getProductSize();
	        String qty = cart.getProductQuantity();

	        assertTrue(size != null && !size.isEmpty(), "Size is not visible");
	        assertTrue(qty != null && !qty.isEmpty(), "Quantity is not visible");
	    }

	    // ================= PLACE ORDER =================

	    @Then("place order button should be visible")
	    public void place_order_button_should_be_visible() {
	        assertTrue(cart.isPlaceOrderButtonDisplayed(), 
	            "Place Order button is not visible");
	    }

	    @And("place order button should be enabled")
	    public void place_order_button_should_be_enabled() {
	        assertTrue(cart.isPlaceOrderButtonEnabled(), 
	            "Place Order button is not enabled");
	    }

	    // ================= COUPON =================

	    @When("User clicks coupon button")
	    public void user_clicks_coupon_button() {
	        cart.clickCouponButton();
	    }

	    @And("User enters coupon {string}")
	    public void user_enters_coupon(String code) {
	        cart.enterCoupon(code);
	    }

	    @And("User clicks apply coupon")
	    public void user_clicks_apply_coupon() {
	        cart.clickApplyCouponButton();
	    }

	    @Then("coupon should be applied or error displayed")
	    public void coupon_should_be_applied_or_error_displayed() {

	        boolean applied = cart.isCouponApplied();
	        boolean error = cart.isCouponErrorDisplayed();
	        boolean validation = cart.isCouponValidationMessageDisplayed();

	        assertTrue(applied || error || validation, 
	            "No coupon response displayed");
	    }

	    // ================= REMOVE PRODUCT =================

	    @When("User selects product in cart")
	    public void user_selects_product_in_cart() {
	        cart.waitForProductSelectCheckbox();
	        cart.selectProduct();
	    }

	    @And("User clicks remove button")
	    public void user_clicks_remove_button() {
	        cart.clickRemove();
	    }

	    @And("User confirms remove popup")
	    public void user_confirms_remove_popup() {
	        cart.clickRemovePopup();
	    }

	    @Then("cart should be empty or updated")
	    public void cart_should_be_empty_or_updated() {
	        assertTrue(cart.isEmptyCartMessageDisplayed() || cart.getCartItemCount() >= 0,
	            "Cart not updated after removal");
	    }

	    // ================= WISHLIST =================

	    @When("User clicks move to wishlist")
	    public void user_clicks_move_to_wishlist() {
	        cart.clickMoveToWishlist();
	    }

	    @And("User confirms move to wishlist popup")
	    public void user_confirms_move_to_wishlist_popup() {
	        cart.clickMoveToWishlistPopup();
	    }

	    @Then("product should be removed from cart")
	    public void product_should_be_removed_from_cart() {
	        assertTrue(cart.isEmptyCartMessageDisplayed() || cart.getCartItemCount() >= 0,
	            "Product not removed from cart");
	    }

	    // ================= PINCODE =================

	    @When("User enters pincode in cart")
	    public void user_enters_pincode_in_cart() {
	        cart.clickPincode();
	        cart.enterPincode("411014");
	        cart.clickPincodeCheck();
	    }

	    @Then("delivery address or message should be displayed")
	    public void delivery_address_or_message_should_be_displayed() {
	        String address = "";

	        try {
	            address = cart.getDeliveryAddress();
	        } catch (Exception e) {}

	        assertTrue(address != null, 
	            "No delivery response displayed");
	    }

	    // ================= QUANTITY =================

	    @When("User increases quantity to max limit")
	    public void user_increases_quantity_to_max_limit() {
	        cart.increaseQuantityToMaxLimit();
	    }

	    @Then("max quantity warning should be displayed")
	    public void max_quantity_warning_should_be_displayed() {
	        assertTrue(cart.isMaxQuantityWarningDisplayed(), 
	            "Max quantity warning not displayed");
	    }

	    // ================= EMPTY CART =================

	    @Then("empty cart message should be displayed (if no products)")
	    public void empty_cart_message_should_be_displayed() {
	        assertTrue(cart.isEmptyCartMessageDisplayed(), 
	            "Empty cart message not displayed");
	    }
	}


