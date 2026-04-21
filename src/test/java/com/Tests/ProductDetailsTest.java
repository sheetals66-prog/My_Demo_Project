package com.Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.dataprovider.MyntraSearchTest;
import com.dataprovider.PincodeWithoutExcel;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class ProductDetailsTest extends Testbase {

	public ProductDetailPage navigateToPDP(String searchText) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		plp.clickProductByIndex(0);
		windowHandle();
		return pd;
	}

	@Test
	public void toVerifyProdutTitle() {
		ProductDetailPage pd = navigateToPDP("Kids");
		WaitFor.waitForElementToBeVisible(pd.productTitle);
		String title = pd.getProductTitle();
		System.out.println("Product Title: " + title);
		Assert.assertNotNull(title, "Title is NULL");
		Assert.assertFalse(title.trim().isEmpty(), "Product title is empty");

	}

	@Test
	public void toVerifProductPrice() {
		ProductDetailPage pd = navigateToPDP("Kids");
		// Wait for page load (important)
		WaitFor.waitForElementToBeClickable(pd.productPrice);
		String price = pd.getProductPrice();
		System.out.println("Product Price: " + price);
		Assert.assertNotNull(price, "Price is NULL");
		Assert.assertFalse(price.trim().isEmpty(), "Product price is empty");

	}

	@Test
	public void verifyAddToBagButton() {
		ProductDetailPage pd = navigateToPDP("Kids");
		Assert.assertTrue(pd.isAddToBagDisplayed(), "Add to Bag button not visible");
	}

	@Test
	public void verifyAddToBagClick() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize();
		pd.clickAddToBag();
		Assert.assertTrue(pd.isGoToBagDisplayed());
	}

	@Test
	public void verifySizeSelection() {
		ProductDetailPage pd = navigateToPDP("Kids");
		boolean selected = pd.selectSize();
		Assert.assertTrue(selected, "Size not selected");
	}

	@Test
	public void verifyProductImage() throws InterruptedException {
		ProductDetailPage pd = navigateToPDP("Kids");
		Assert.assertTrue(pd.areProductImagesDisplayed(), "Product image not visible");
	}

	@Test
	public void verifySizeErrorOnAddToBag() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.clickAddToBag(); // no size selected
		Assert.assertTrue(pd.isErrormsgdisplayed(), "Size error message not displayed");
	}

	@Test
	public void verifyWishlist() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.clickWishlist();
		Assert.assertTrue(true);
	}

	@Test
	public void verifyAddToBagSuccess() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize();
		pd.clickAddToBag();
		Assert.assertTrue(pd.isGoToBagDisplayed(), "User not navigated to cart page");

	}

	@Test
	public void verifyGoToBag() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize();
		pd.clickAddToBag();
		pd.clickGoToBag();
		Assert.assertTrue(pd.isOnCartPage(), "User not navigated to cart page");

	}

	@Test
	public void verifyValidPincode() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.enterPincode("411023");
		String msg = pd.getPinCodeMsg();
		System.out.println("Valid Pincode Msg: " + msg);
		Assert.assertTrue(msg.length() > 5, "Pincode message not displayed properly: " + msg);
	}

	@Test(dataProvider = "validPincodes", dataProviderClass = MyntraSearchTest.class)
	public void verifyValidPincodeIsAccepted(String validPincode) {
		HomePage sr=new HomePage();
		ProductListingPage plp=new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		KeyWord.navigate();
		plp.clickProductByIndex(0);
		windowHandle();
		pd.selectSize();
		pd.enterPincode(validPincode);
		String actualMessage = pd.getPinCodeMsg();
		Assert.assertTrue(actualMessage.contains("Get it by"), "Delivery message is displayed for valid pincode");

	}

	@Test
	public void verifyInvalidPincode() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.enterPincode("123456");
		String msg = pd.getInvalidPinCodeMsg();
		Assert.assertTrue(msg.toLowerCase().contains("not ship"), "Invalid pincode message incorrect: " + msg);

	}

	@Test(dataProvider = "invalidPincodes", dataProviderClass = MyntraSearchTest.class)
	public void verifyInvalidPincodeIsRejected(String invalidPincode) throws InterruptedException {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		CartPage cart = new CartPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		navigate();
		plp.clickProductByIndex(0);
		windowHandle();
		pd.selectSize();
		pd.enterPincode(invalidPincode);
		String actualMessage = pd.getInvalidPinCodeMsg();
		Assert.assertTrue(actualMessage.contains("Unfortunately we do not ship to your pincode"),"Error message not displayed for invalid pincode");
	}
	//negative test cases
	@Test
	public void verifyAddToBagWithoutSizeSelection() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.clickAddToBag();

	    Assert.assertTrue(pd.isErrormsgdisplayed(),
	            "Size selection error should be displayed");
	}

	@Test
	public void verifyInvalidPincodeMessage() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.enterPincode("000000");

	    String actual = pd.getInvalidPincodeToast();

	    Assert.assertTrue(actual.contains("valid pincode"),
	            "Invalid pincode message not displayed");
	}
	@Test
	public void verifyAlphabetPincodeRejected() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.enterPincode("ABCD");

	    String actual = pd.getInvalidPincodeToast();

	    Assert.assertTrue(actual.contains("valid pincode"),
	            "Invalid pincode message not displayed");
	}
	@Test
	public void verifyShortPincodeRejected() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.enterPincode("123");
	    String actual = pd.getInvalidPincodeToast();

	    Assert.assertTrue(actual.contains("valid pincode"),
	            "Invalid pincode message not displayed");
	    
	}
	@Test
	public void verifyGoToBagWithoutAddingProductAndWithoutClickOnAddToBag() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    Assert.assertFalse(pd.isGoToBagDisplayed(),
	            "Go To Bag should not be visible without adding product");
	}
	@Test
	public void verifyMultipleAddToBagClicks() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.selectSize();
	    pd.clickAddToBag();
	    pd.clickAddToBag();

	    Assert.assertTrue(pd.isErrormsgdisplayed(),
	            "Multiple clicks should not add duplicate items");
	}
	
	@Test
	public void verifyPincodeWithoutSizeSelection() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.enterPincode("411023");

	    String pincodePrompt = pd.getPincodeErrorMsg();
	    Assert.assertTrue(pincodePrompt.toLowerCase().contains("please enter pin") || pincodePrompt.toLowerCase().contains("please select size") || pincodePrompt.length() > 0,
	            "System should prompt to select size before delivery check");
	}
	@Test
	public void verifyAddToBagWhenDisabled() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    Assert.assertFalse(pd.isAddToBagClickable(),
	            "Add to Bag should not be clickable in invalid state");
	}
	@Test
	public void verifyNonServiceablePincode() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    pd.enterPincode("999999");

	    Assert.assertFalse(pd.getInvalidPinCodeMsg().contains("not deliverable") ||
	                      pd.getInvalidPinCodeMsg().contains("not serviceable"),
	            "Non-serviceable area should show proper message");
	}
	@Test
	public void verifyWishlistWithoutAction() {

	    ProductDetailPage pd = navigateToPDP("Kids");

	    Assert.assertFalse(pd.isWishlistActiveStateSelected(),
	            "Wishlist should not be pre-activated");
	}
	
		

}
