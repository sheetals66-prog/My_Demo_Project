package com.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;
import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class PlaceOrderTest extends Testbase {
	
	private static final Logger log = LogManager.getLogger(PlaceOrderTest.class);

	@Test
	public void userAbleToPlaceOrderWithoutLogin() throws InterruptedException {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("Kids");
		WaitFor.pageLoaded();
		plp.selectBoysFilter();
		WaitFor.pageLoaded();
		plp.isBoysFilterSelected();
		WaitFor.pageLoaded();
		plp.openCategoriesFilter();
		WaitFor.pageLoaded();
		plp.searchCategories("Tshirts");
		WaitFor.pageLoaded();
		plp.selectCategories("Tshirts");

		WaitFor.pageLoaded();
		plp.clickProductByIndex(0);
		WaitFor.pageLoaded();
		windowHandle();
		Thread.sleep(3000);
		try {
			pd.selectSize(0);
		}
	catch(Exception e) {
	}
	
		pd.clickAddToBag();
		pd.enterPincode("411023");
		Thread.sleep(3000);
		pd.clickGoToBag();
		cart.clickCouponButton();
		cart.clickApplyCouponButton();
		cart.isCouponApplied();
		cart.clickPlaceOrder();
		Assert.assertFalse(KeyWord.driver.getCurrentUrl().toLowerCase().contains("checkcout"), "Checkout page did not load after clicking Place Order");
	}
}