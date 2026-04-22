package StepDefinations;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.pages.HomePage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;

import io.cucumber.java.en.*;

public class HomePageTestStep {

    SoftAssert softly = new SoftAssert();

    // ---------------- GIVEN ----------------

    @Given("user is on homepage")
    public void user_is_on_homepage() {
        String title = KeyWord.driver.getTitle();
        System.out.println("Page Title: " + title);
        Assert.assertTrue(title.contains("Myntra"), "Homepage not loaded");
    }

    // ---------------- HOME ----------------

    @Then("homepage should be displayed")
    public void homepage_should_be_displayed() {
        String title = KeyWord.driver.getTitle();
        Assert.assertTrue(title.contains("Myntra"), "Homepage not displayed");
    }

    // ---------------- SEARCH ----------------

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        HomePage sr = new HomePage();
        sr.clickOnSearchResult();
        sr.typeAndHitSearchBar(product);
    }

    @Then("product results should be displayed")
    public void product_results_should_be_displayed() {
        ProductListingPage plp = new ProductListingPage();
        Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Product page not loaded");
    }

    // ---------------- NEGATIVE ----------------

    @Then("no results message should be displayed")
    public void no_results_message_should_be_displayed() {
        HomePage sr = new HomePage();
        String text = sr.getCouldnotFindAnyMatches();

        Assert.assertTrue(
            text.toLowerCase().contains("no") || text.toLowerCase().contains("0"),
            "Unexpected results displayed"
        );
    }

    // ---------------- AUTOSUGGEST ----------------

    @When("user types keyword {string}")
    public void user_types_keyword(String keyword) {
        HomePage home = new HomePage();
        home.clickOnSearchResult();
        home.typeText("Kids");   // IMPORTANT: no enter
    }

    @Then("user should see the autosuggestions")
    public void user_should_see_the_autosuggestions() {
        HomePage sr = new HomePage();
        Assert.assertTrue(sr.isAutoSuggestionDisplayed(), "Autosuggestions not visible");
    }

    // ---------------- WISHLIST ----------------

    @When("user clicks on wishlist icon")
    public void user_clicks_on_wishlist_icon() {
        HomePage sr = new HomePage();
       sr.clickOnWishlistIcon();
    }

    // ---------------- ORDERS ----------------

    @When("user clicks on orders list")
    public void user_clicks_on_orders_list() {
        HomePage sr = new HomePage();
       sr.clickOnMyOrders();
    }

    // ---------------- LOGIN REDIRECT ----------------

    @Then("user should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        String url = KeyWord.driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login"), "User NOT redirected to login page");
    }
}