package com.Tests;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;

import com.testbase.Testbase;

public class CartPageTest extends Testbase {
	public CartPage navigateToCartPage(String searchText) {

		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		plp.getProductCountTextBeforeApplyingFilter();

		plp.openCategoriesFilter();
		plp.selectCategories("Tshirts");
		plp.searchCategories("Tshirts");
		plp.clickProductByIndex(1);
		windowHandle();

		pd.selectSize(); // Select size before adding to bag
		pd.clickAddToBag();

		pd.clickGoToBag();

		return new CartPage();
	}

	@Test
	public void verifyPlaceOrderButtonVisible() {

		CartPage cart = navigateToCartPage("kids");

		Assert.assertTrue(cart.isPlaceOrderButtonDisplayed(), "Place Order button not visible");
	}

	@Test
	public void verifyPlaceOrderButtonClickable() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickPlaceOrder();
		System.out.println("Place Order button clicked");

		Assert.assertTrue(cart.isCheckoutPageLoaded(), "Checkout page did not load after clicking Place Order");

	}

	@Test
	public void verifyPriceDetails() {
		CartPage cart = navigateToCartPage("kids");
		String priceDetails = cart.getPriceDetails();
		Assert.assertNotNull(priceDetails, "Price details are NULL");
		Assert.assertFalse(priceDetails.trim().isEmpty(), "Price details are empty");

	}

	@Test
	public void verifyProductSelection() {
		CartPage cart = navigateToCartPage("kids");
		// Wait for the selection checkbox to be available
		cart.waitForProductSelectCheckbox();
		Assert.assertTrue(cart.isProductSelected(), "Product not selected");
	}

	@Test
	public void verifyRemoveProduct() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickRemove();
		cart.clickRemovePopup();
		System.out.println("Product removed from cart");

		Assert.assertTrue(!cart.isProductPresent(), "Product not removed from cart");
	}

	@Test
	public void verifyMoveToWishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickMoveToWishlistPopup();
		System.out.println("Product moved to wishlist");

		Assert.assertTrue(!cart.isProductPresent(), "Product not moved to wishlist");

	}

	@Test
	public void verifyCancelToWhishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickCancelButton();
		System.out.println("Move to wishlist cancelled");

		Assert.assertTrue(cart.isProductPresent(), "Product should still be in cart");

	}

	@Test
	public void verifyCouponApplied() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		cart.isCouponApplied();
		System.out.println("Coupon applied successfully");

		Assert.assertTrue(cart.isCouponApplied(), "Coupon not applied successfully");

	}

	@Test
	public void verifyValidpincode() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickPincode();
		cart.enterPincode("411023");
		cart.clickPincodeCheck();
		cart.getDeliveryAddress();
		System.out.println("Pincode button clicked");
		Assert.assertNotNull(cart.getDeliveryAddress(), "Delivery address is NULL");
	}

	@Test
	public void verifyProductPresent() {
		CartPage cart = navigateToCartPage("kids");
		Assert.assertTrue(cart.isProductPresent(), "Product is not present in cart");
	}

	@Test
	public void verifyCartItemCount() {
		CartPage cart = navigateToCartPage("kids");
		int count = cart.getCartItemCount();
		System.out.println("Cart item count: " + count);
		Assert.assertTrue(count > 0, "Cart item count should be greater than 0");
	}

	@Test
	public void verifyProductName() {
		CartPage cart = navigateToCartPage("kids");
		String name = cart.getProductName();
		System.out.println("Product name: " + name);
		// Assert.assertNotNull(name, "Product name is NULL");
		Assert.assertTrue(!name.trim().isEmpty(), "Product name is empty");
	}

	@Test
	public void verifyBrandName() {
		CartPage cart = navigateToCartPage("kids");
		String brand = cart.getBrandName();
		System.out.println("Brand name: " + brand);
		Assert.assertNotNull(brand, "Brand name is NULL");
		Assert.assertFalse(brand.trim().isEmpty(), "Brand name is empty");
	}

	@Test
	public void verifyProductPrice() {
		CartPage cart = navigateToCartPage("kids");
		String price = cart.getProductPrice();
System.out.println("Product price: " + price);
		Assert.assertNotNull(price, "Price is NULL");
		Assert.assertFalse(price.trim().isEmpty(), "Price is empty");
		Assert.assertTrue(price.contains("₹") || price.matches("\\d+"), "Invalid price format");
	}

	@Test
	public void verifyProductSize() {
		CartPage cart = navigateToCartPage("kids");
		String size = cart.getProductSize();
		System.out.println("Product size: " + size);
		Assert.assertNotNull(size, "Size is NULL");
		Assert.assertFalse(size.trim().isEmpty(), "Size is empty");
	}

	@Test
	public void verifyProductQuantity() {
		CartPage cart = navigateToCartPage("kids");
		String qty = cart.getProductQuantity();
		System.out.println("Product quantity: " + qty);
		Assert.assertNotNull(qty, "Quantity is NULL");
		Assert.assertFalse(qty.trim().isEmpty(), "Quantity is empty");
	}
//Negative Test cases
	@Test
	public void verifyAddToCartWithoutSelectingSize() {

	    HomePage sr = new HomePage();
	    ProductListingPage plp = new ProductListingPage();
	    ProductDetailPage pd = new ProductDetailPage();

	    sr.clickOnSearchResult();
	    sr.typeAndHitSearchBar("kids");

	    plp.clickProductByIndex(1);
	    windowHandle();

	  
	    pd.clickAddToBag();

	    Assert.assertTrue(pd.isErrormsgdisplayed(),
	        "Size error message should be displayed");
	}
	@Test
	public void verifyRemoveProductWhenCartIsEmpty() {

	    CartPage cart = new CartPage();

	    Assert.assertFalse(cart.isRemoveButtonVisible(),
	        "Remove button should not be visible when cart is empty");
	}
	
	@Test
	public void verifyInvalidCouponCode() {

	    CartPage cart = navigateToCartPage("kids");

	    cart.clickCouponButton();
	    cart.enterCoupon("INVALID123");
	    cart.clickApplyCouponButton();

	    Assert.assertTrue(cart.isCouponErrorDisplayed(),
	        "Invalid coupon error should be shown");
	}
	
	@Test
	public void verifyApplyCouponWithoutCode() {

	    CartPage cart = navigateToCartPage("kids");

	    cart.clickCouponButton();
	    cart.clickApplyCouponButton();

	    Assert.assertTrue(cart.isCouponValidationMessageDisplayed(),
	        "Validation message should be shown");
	}
	@Test
	public void verifyCheckoutWithoutProducts() {

	    CartPage cart = new CartPage();

	    Assert.assertFalse(cart.isPlaceOrderButtonEnabled(),
	        "Place order should not be enabled for empty cart");
	}
	
	@Test
	public void verifyMaxQuantityLimit() {

	    CartPage cart = navigateToCartPage("kids");

	    cart.increaseQuantityToMaxLimit();

	    Assert.assertTrue(cart.isMaxQuantityWarningDisplayed(),
	        "Max quantity warning should appear");
	}
	
	@Test
	public void verifyMoveToWishlistRemovesFromCart() {

	    CartPage cart = navigateToCartPage("kids");

	    cart.selectProduct();
	    cart.clickMoveToWishlist();
	    cart.clickMoveToWishlistPopup();

	    Assert.assertFalse(cart.isProductPresent(),
	        "Product should be removed from cart after wishlist move");
	}
	
	@Test
	public void verifyPlaceOrderWithoutLogin() {

	    CartPage cart = navigateToCartPage("kids");

	    cart.clickPlaceOrder();

	    Assert.assertTrue(cart.isLoginPopupDisplayed(),
	        "Login popup should appear before checkout");
	}
	@Test
	public void verifyEmptyCartState() {

	    CartPage cart = new CartPage();

	    Assert.assertTrue(cart.isEmptyCartMessageDisplayed(),
	        "Empty cart message should be shown");
	}
	
	
}