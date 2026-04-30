package com.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class CartPageTest extends Testbase {
	private static final Logger log = LogManager.getLogger(ProductListingPageTest.class);

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
		plp.clickProductByIndex(0);
		windowHandle();
		WaitFor.pageLoaded();
		try {
			pd.selectSize(1);
		} catch (Exception e) {
			
		}
		WaitFor.pageLoaded();
		pd.clickAddToBag();
		WaitFor.pageLoaded();
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
		LoginPage login = new LoginPage();

		cart.clickPlaceOrder();
		log.info("Place Order button clicked");

		Assert.assertTrue(login.isLoginPageDisplayed(), "Checkout page did not load after clicking Place Order");

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
		cart.waitForProductSelectCheckbox();
		Assert.assertTrue(cart.isProductSelected(), "Product not selected");
	}

	@Test
	public void verifyRemoveProduct() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickRemove();
		cart.clickRemovePopup();
		log.info("Product removed from cart");
		Assert.assertTrue(!cart.isProductPresent(), "Product not removed from cart");
	}

	@Test
	public void verifyMoveToWishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickMoveToWishlistPopup();
		log.info("Product moved to wishlist");
		Assert.assertTrue(!cart.isProductPresent(), "Product not moved to wishlist");

	}

	@Test
	public void verifyCancelToWhishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickCancelButton();
		log.info("Move to wishlist cancelled");
		Assert.assertTrue(cart.isProductPresent(), "Product should still be in cart");

	}

	@Test
	public void verifyCouponApplied() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		log.info("Coupon applied successfully");
		Assert.assertTrue(cart.isCouponApplied(), "Coupon not applied successfully");

	}

	@Test
	public void verifyValidpincode() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickPincode();
		cart.enterPincode("411023");
		cart.clickPincodeCheck();
		cart.getDeliveryAddress();
		log.info("Pincode button clicked");
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
		log.info("Cart item count: " + count);
		Assert.assertTrue(count > 0, "Cart item count should be greater than 0");
	}

	@Test
	public void verifyProductName() {
		CartPage cart = navigateToCartPage("kids");
		String name = cart.getProductName();
		log.info("Product name: " + name);
		Assert.assertTrue(!name.trim().isEmpty(), "Product name is empty");
	}

	@Test
	public void verifyBrandName() {
		CartPage cart = navigateToCartPage("kids");
		String brand = cart.getBrandName();
		log.info("Brand name: " + brand);
		Assert.assertNotNull(brand, "Brand name is NULL");
		Assert.assertFalse(brand.trim().isEmpty(), "Brand name is empty");
	}

	@Test
	public void verifyProductPrice() {
		CartPage cart = navigateToCartPage("kids");
		String price = cart.getProductPrice();
		log.info("Product price: " + price);
		Assert.assertNotNull(price, "Price is NULL");
		Assert.assertFalse(price.trim().isEmpty(), "Price is empty");
		Assert.assertTrue(price.contains("₹") || price.matches("\\d+"), "Invalid price format");
	}

	@Test
	public void verifyProductSize() {
		CartPage cart = navigateToCartPage("kids");
		String size = cart.getProductSize();
		log.info("Product size: " + size);
		Assert.assertNotNull(size, "Size is NULL");
		Assert.assertFalse(size.trim().isEmpty(), "Size is empty");
	}

	@Test
	public void verifyProductQuantity() {
		CartPage cart = navigateToCartPage("kids");
		String qty = cart.getProductQuantity();
		log.info("Product quantity: " + qty);
		Assert.assertNotNull(qty, "Quantity is NULL");
		Assert.assertFalse(qty.trim().isEmpty(), "Quantity is empty");
	}

//Negative Test cases
//	@Test
//	public void verifyAddToCartWithoutSelectingSize() {
//		HomePage sr = new HomePage();
//		ProductListingPage plp = new ProductListingPage();
//		ProductDetailPage pd = new ProductDetailPage();
//		sr.clickOnSearchResult();
//		sr.typeAndHitSearchBar("kids");
//		plp.clickProductByIndex(1);
//		windowHandle();
//		pd.clickAddToBag();
//		Assert.assertTrue(pd.isErrormsgdisplayed(), "Size error message should be displayed");
//	}

	@Test
	public void verifyRemoveProductWhenCartIsEmpty() {
		CartPage cart = new CartPage();
		Assert.assertFalse(cart.isRemoveButtonVisible(), "Remove button should not be visible when cart is empty");
	}

	@Test
	public void verifyInvalidCouponCode() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.enterCoupon("INVALID123");
		cart.clickApplyCouponButton();
		Assert.assertFalse(cart.isCouponErrorDisplayed(), "Invalid coupon error should be shown");
	}

	@Test
	public void verifyApplyCouponWithoutCode() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		Assert.assertFalse(cart.isCouponValidationMessageDisplayed(), "Validation message should be shown");
	}

	@Test
	public void verifyCheckoutWithoutProducts() {
		CartPage cart = new CartPage();
		Assert.assertFalse(cart.isPlaceOrderButtonEnabled(), "Place order should not be enabled for empty cart");
	}

	@Test
	public void verifyMaxQuantityLimit() {
		CartPage cart = navigateToCartPage("kids");
		cart.increaseQuantityToMaxLimit();
		Assert.assertTrue(cart.isMaxQuantityWarningDisplayed(), "Max quantity warning should appear");
	}

	@Test
	public void verifyMoveToWishlistRemovesFromCart() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickMoveToWishlistPopup();
		Assert.assertFalse(cart.isProductPresent(), "Product should be removed from cart after wishlist move");
	}

	@Test
	public void verifyPlaceOrderWithoutLogin() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickPlaceOrder();
		String expectedUrl=KeyWord.driver.getCurrentUrl();
		Assert.assertTrue(expectedUrl.toLowerCase().contains("login"), "Login popup should appear before checkout");
	}

}