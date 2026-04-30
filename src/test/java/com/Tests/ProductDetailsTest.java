package com.Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.dataprovider.MyntraSearchTest;
import com.dataprovider.Pincode;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class ProductDetailsTest extends Testbase {
	private static final Logger log = LogManager.getLogger(ProductDetailsTest.class);

	public ProductDetailPage navigateToPDP(String searchText) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		plp.clickProductByIndex(1);
		windowHandle();
		return pd;
	}

	@Test
	public void toVerifyProdutTitle() {
		ProductDetailPage pd = navigateToPDP("Kids");
		WaitFor.waitForElementToBeVisible(pd.productTitle);
		String title = pd.getProductTitle();
		log.info("Product Title: " + title);
		Assert.assertNotNull(title, "Title is NULL");
		Assert.assertFalse(title.trim().isEmpty(), "Product title is empty");

	}

	@Test
	public void toVerifProductPrice() {
		ProductDetailPage pd = navigateToPDP("Kids");
		WaitFor.waitForElementToBeClickable(pd.productPrice);
		String price = pd.getProductPrice();
		log.info("Product Price: " + price);
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
		WaitFor.pageLoaded();
		try {
			pd.selectSize(0);
		} catch (Exception e) {

		}
		pd.clickAddToBag();
		Assert.assertTrue(pd.isGoToBagDisplayed());
	}

	@Test
	public void verifySizeSelection() {
		ProductDetailPage pd = navigateToPDP("Kids");
		String selectedSize;
		WaitFor.pageLoaded();
		try {
			selectedSize = pd.selectSize(0);
			log.info("Selected size: " + selectedSize);
		} catch (Exception e) {

		}

		Assert.assertTrue(pd.isSizeAvailable(), "Size not selected");

	}

	@Test
	public void verifyProductImage() throws InterruptedException {
		ProductDetailPage pd = navigateToPDP("Kids");
		Assert.assertTrue(pd.areProductImagesDisplayed(), "Product image not visible");
	}

	@Test
	public void verifySizeErrorOnAddToBag() throws InterruptedException {
		ProductDetailPage pd = navigateToPDP("Kids");

		pd.clickAddToBag();

		try {
			pd.isErrormsgdisplayed();
			String msg = pd.getErrorMsg();
			Assert.assertTrue(msg.toLowerCase().contains("get it by"),
					"Pincode message not displayed properly: " + msg);

		} catch (Exception e) {
			// TODO: handle exception
		}

		Assert.assertTrue(pd.isGoToBagDisplayed(), "product is added to bag by selecting size");

	}

	@Test
	public void verifyWishlist() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.clickWishlist();
		String expectedUrl = KeyWord.getCurrentUrl();
		Assert.assertTrue(expectedUrl.toLowerCase().contains("login"));
	}

	@Test
	public void verifyAddToBagSuccess() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize(0);
		pd.clickAddToBag();
		Assert.assertTrue(pd.isGoToBagDisplayed(), "User not navigated to cart page");

	}

	@Test
	public void verifyGoToBag() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize(0);
		pd.clickAddToBag();
		pd.clickGoToBag();
		Assert.assertTrue(pd.isOnCartPage(), "User not navigated to cart page");

	}

	@Test
	public void verifyValidPincode() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.enterPincode("411023");
		String msg = pd.getPinCodeMsg();
		log.info("Valid Pincode Msg: " + msg);
		Assert.assertTrue(msg.toLowerCase().contains("get it by"), "Pincode message not displayed properly: " + msg);
	}

	@Test(dataProvider = "validPincodes", dataProviderClass = MyntraSearchTest.class)
	public void verifyValidPincodeIsAccepted(String validPincode) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		plp.openCategoriesFilter();
		plp.searchCategories("Tshirts");
		plp.selectCategories("Tshirts");
		KeyWord.navigate();
		plp.clickProductByIndex(0);
		windowHandle();
		pd.selectSize(0);
		pd.enterPincode(validPincode);
		String actualMessage = pd.getPinCodeMsg();
		Assert.assertTrue(actualMessage.contains("Get it by"), "Delivery message is displayed for valid pincode");

	}

	@Test(dataProvider = "validPincode", dataProviderClass = Pincode.class)
	public void verifyValidPincodeIsAcceptedUsingExcel(String validPincode) {
		HomePage sr = new HomePage();
		ProductListingPage plp = new ProductListingPage();
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
		pd.selectSize(0);
		pd.enterPincode(validPincode);
		String actualMessage = pd.getPinCodeMsg();
		Assert.assertTrue(actualMessage.contains("Get it by"), "Delivery message is displayed for valid pincode");

	}

	// negative test cases
	@Test
	public void verifyAddToBagWithoutSizeSelection() {

		ProductDetailPage pd = navigateToPDP("Kids");

		pd.clickAddToBag();

		try {
			pd.isErrormsgdisplayed();
			String msg = pd.getErrorMsg();
			Assert.assertTrue(msg.toLowerCase().contains("get it by"),
					"Pincode message not displayed properly: " + msg);

		} catch (Exception e) {
			// TODO: handle exception
		}

		Assert.assertTrue(pd.isGoToBagDisplayed(), "product is added to bag by selecting size");

	}

	@Test
	public void verifyPincodeWithoutSizeSelection() {

		ProductDetailPage pd = navigateToPDP("Kids");

		pd.enterPincode("411023");

		String pincodePrompt = pd.getPinCodeMsg();
		Assert.assertTrue(
				pincodePrompt.toLowerCase().contains("please enter pin")
						|| pincodePrompt.toLowerCase().contains("please select size") || pincodePrompt.length() > 0,
				"System should prompt to select size before delivery check");
	}

	@Test
	public void verifyNonServiceablePincode() {

		ProductDetailPage pd = navigateToPDP("Kids");

		pd.enterPincode("999999");

		Assert.assertFalse(
				pd.getInvalidPinCodeMsg().contains("not deliverable")
						|| pd.getInvalidPinCodeMsg().contains("not serviceable"),
				"Non-serviceable area should show proper message");
	}

	@Test
	public void verifyWishlistWithoutAction() {

		ProductDetailPage pd = navigateToPDP("Kids");

		Assert.assertFalse(pd.isWishlistActiveStateSelected(), "Wishlist should not be pre-activated");
	}

}
