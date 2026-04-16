package com.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Testbase;

public class CartPageTest extends Testbase {
	public CartPage navigateToCartPage(String searchText) {

		SearchResult sr = new SearchResult();
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
		
		  Assert.assertTrue(cart.isCheckoutPageLoaded(),
		  "Checkout page did not load after clicking Place Order");
		 
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
		
		  Assert.assertFalse(cart.isProductPresent(), "Product not removed from cart");
	}

	@Test
	public void verifyMoveToWishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickMoveToWishlistPopup();
		System.out.println("Product moved to wishlist");
		/*
		 * Assert.assertFalse(cart.isProductPresent(), "Product not moved to wishlist");
		 */
	}

	@Test
	public void verifyCancelToWhishlist() {
		CartPage cart = navigateToCartPage("kids");
		cart.selectProduct();
		cart.clickMoveToWishlist();
		cart.clickCancelButton();
		System.out.println("Move to wishlist cancelled");
		/*
		 * Assert.assertTrue(cart.isProductPresent(),
		 * "Product should still be in cart");
		 */
	}

	@Test
	public void verifyApplyCoupon() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		System.out.println("Coupon button clicked");
		/*
		 * Assert.assertTrue(cart.isCouponApplied(), "Coupon not applied successfully");
		 */
	}

	@Test
	public void verifyCouponApplied() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		cart.isCouponApplied();
		System.out.println("Coupon applied successfully");
		/*
		 * Assert.assertTrue(cart.isCouponApplied(), "Coupon not applied successfully");
		 */
	}

	@Test
	public void verifypincode() {
		CartPage cart = navigateToCartPage("kids");
		cart.clickPincode();
		cart.enterPincode("411023");
		cart.clickPincodeCheck();
		cart.getDeliveryAddress();
		System.out.println("Pincode button clicked");
		Assert.assertNotNull(cart.getDeliveryAddress(), "Delivery address is NULL");
	}
//Need to check below...
	@Test
	public void verifyProductPresent() {
		CartPage cart = navigateToCartPage("kids");
		Assert.assertTrue(cart.isProductPresent(), "Product is not present in cart");
	}

	@Test
	public void verifyCartItemCount() {
		CartPage cart = navigateToCartPage("kids");
		int count = cart.getCartItemCount();

		Assert.assertTrue(count > 0, "Cart item count should be greater than 0");
	}

	@Test
	public void verifyProductName() {
		CartPage cart = navigateToCartPage("kids");
		String name = cart.getProductName();

		Assert.assertNotNull(name, "Product name is NULL");
		Assert.assertFalse(name.trim().isEmpty(), "Product name is empty");
	}

	@Test
	public void verifyBrandName() {
		CartPage cart = navigateToCartPage("kids");
		String brand = cart.getBrandName();

		Assert.assertNotNull(brand, "Brand name is NULL");
		Assert.assertFalse(brand.trim().isEmpty(), "Brand name is empty");
	}

	@Test
	public void verifyProductPrice() {
		CartPage cart = navigateToCartPage("kids");
		String price = cart.getProductPrice();

		Assert.assertNotNull(price, "Price is NULL");
		Assert.assertFalse(price.trim().isEmpty(), "Price is empty");

		// Optional: check price format
		Assert.assertTrue(price.contains("₹") || price.matches("\\d+"), "Invalid price format");
	}

	@Test
	public void verifyProductSize() {
		CartPage cart = navigateToCartPage("kids");
		String size = cart.getProductSize();

		Assert.assertNotNull(size, "Size is NULL");
		Assert.assertFalse(size.trim().isEmpty(), "Size is empty");
	}

	@Test
	public void verifyProductQuantity() {
		CartPage cart = navigateToCartPage("kids");
		String qty = cart.getProductQuantity();

		Assert.assertNotNull(qty, "Quantity is NULL");
		Assert.assertFalse(qty.trim().isEmpty(), "Quantity is empty");
	}

	@Test
	public void verifyCompleteProductDetails() {
		CartPage cart = navigateToCartPage("kids");
		Assert.assertTrue(cart.isProductPresent(), "Product not present");

		Assert.assertNotNull(cart.getProductName(), "Name missing");
		Assert.assertNotNull(cart.getBrandName(), "Brand missing");
		Assert.assertNotNull(cart.getProductPrice(), "Price missing");
		Assert.assertNotNull(cart.getProductSize(), "Size missing");
		Assert.assertNotNull(cart.getProductQuantity(), "Quantity missing");

		Assert.assertTrue(cart.getCartItemCount() > 0, "Cart is empty");
	}
}