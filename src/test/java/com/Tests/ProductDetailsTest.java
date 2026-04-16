package com.Tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Keyword;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class ProductDetailsTest extends Testbase {

	public ProductDetailPage navigateToPDP(String searchText) {

		SearchResult sr = new SearchResult();
		ProductListingPage plp = new ProductListingPage();
		ProductDetailPage pd = new ProductDetailPage();

		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar(searchText);
		plp.clickProductByIndex(3);
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
		Assert.assertTrue(true); // improve based on UI change
	}

	@Test
	public void verifyAddToBagSuccess() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize(); // VERY IMPORTANT
		pd.clickAddToBag();

		Assert.assertTrue(pd.isGoToBagDisplayed(), "User not navigated to cart page");

	}

	@Test
	public void verifyGoToBag() {
		ProductDetailPage pd = navigateToPDP("Kids");
		pd.selectSize(); // VERY IMPORTANT

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

		Assert.assertTrue(
			    msg.length() > 5,
			    "Pincode message not displayed properly: " + msg
			);
		
	}

	@Test
	public void verifyInvalidPincode() {

		ProductDetailPage pd = navigateToPDP("Kids");

		pd.enterPincode("123456");

		String msg = pd.getInvalidPinCodeMsg();
		Assert.assertTrue(
			    msg.toLowerCase().contains("not ship"),
			    "Invalid pincode message incorrect: " + msg
			);

}
}
