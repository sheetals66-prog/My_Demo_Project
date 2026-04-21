package com.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.Testbase;

public class PlaceOrderTest extends Testbase {
	@Test
	public void userAbleToPlaceOrderWithoutLogin() {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		plp.selectBoysFilter();
		plp.isBoysFilterSelected();
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		
		plp.openColorFilter();
		plp.searchColor("Red");
		plp.selectcolor("Red");
		plp.selectDiscount("30% and above");
		plp.openAgeFilter();
		plp.searchAgeGroup("6Y-8Y");
		plp.selectAge("6Y-8Y");
		plp.openSortOptions();
		
		plp.selectSortOption("Popularity");
		plp.clickProductByIndex(0);
		windowHandle();
		pd.selectSize();
		pd.enterPincode("411023");
		pd.clickAddToBag();
		pd.clickGoToBag();
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		cart.isCouponApplied();
		cart.clickPlaceOrder();
		Assert.assertTrue(cart.isCheckoutPageLoaded(), "Checkout page did not load after clicking Place Order");
	}
}