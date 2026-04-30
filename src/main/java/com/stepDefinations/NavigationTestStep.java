package com.stepDefinations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.pages.HomePage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationTestStep {

	private static final Logger LOG = LogManager.getLogger(NavigationTestStep.class);
	HomePage hm = new HomePage();

	@When("user open the url")
	public void user_opens_the_url() {
		KeyWord.driver.get("https://www.myntra.com/");
		KeyWord.driver.manage().window().maximize();
	}

	@Then("home page should be display")
	public void homepage_should_be_displayed_successfully() {

		boolean status = hm.isHomePageDisplayed();
		Assert.assertTrue(status, "Homepage is NOT displayed successfully");
	}

	@Then("logo should be display")
	public void myntra_logo_should_be_displayed() {

		boolean logoStatus = hm.isLogoDisplayed();
		Assert.assertTrue(logoStatus, "Myntra logo is NOT visible");
	}

	@Then("search bar should be visible on homepage")
	public void searchBarShouldBeVisible() {
		boolean searchbar = hm.SearchBarDisplayed();
		Assert.assertTrue(searchbar, "Search bar is not visible on the homepage.");
		LOG.info("search bar is visible....");
	}

	@Then("WishListIcon should be visible")
	public void wishListShouldBeVisible() {
		boolean wishlist = hm.isWishlistIconDisplayed();
		Assert.assertTrue(wishlist, "Search bar is not visible on the homepage.");
		LOG.info("wishList Icon is visible....");
	}

	@And("search kids product on search bar")
	public void clickOnKids() {
		hm.clickOnSearchResult();
		hm.typeAndHitSearchBar("kids");
	}

	@Then("kids page should be display")
	public void kidsPageDisplay() {
		ProductListingPage plp = new ProductListingPage();
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		LOG.info("Products are Displayed..");
	}

	@Then("Kids page should be displayed successfully")
	public void openKidsPageByDirectUrl() {

		String title = KeyWord.driver.getTitle();
		Assert.assertTrue(title.contains("Kids"), "Expected kids page but URL does not contain 'kids'.");
		LOG.info("kids page displays...");
	}
}
