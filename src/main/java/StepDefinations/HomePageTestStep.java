package StepDefinations;

import org.testng.Assert;

import com.pages.HomePage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;

import io.cucumber.java.en.*;

public class HomePageTestStep {

    HomePage home = new HomePage();
    ProductListingPage plp = new ProductListingPage();



    @Given("user is on homepage")
    public void userIsOnHomepage() {
        String title = KeyWord.driver.getTitle();
        System.out.println("Page Title: " + title);

        Assert.assertTrue(title.toLowerCase().contains("myntra"),
                "Homepage not loaded properly");
    }



    @Then("homepage should be displayed")
    public void homepageShouldBeDisplayed() {
        String title = KeyWord.driver.getTitle();

        Assert.assertTrue(title.toLowerCase().contains("myntra"),
                "Homepage not displayed");
    }

    @When("user searches for {string}")
    public void userSearchesFor(String product) {
        home.clickOnSearchResult();
        home.typeAndHitSearchBar(product);
    }

    @Then("product results should be displayed")
    public void productResultsShouldBeDisplayed() {
        Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(),
                "Product results not displayed");
    }

   

    @Then("no results message should be displayed")
    public void noResultsMessageShouldBeDisplayed() {

        String text = home.getCouldnotFindAnyMatches();

        System.out.println("No result message: " + text);

        Assert.assertTrue(
                text != null && (
                        text.toLowerCase().contains("no results") ||
                        text.toLowerCase().contains("couldn't find") ||
                        text.toLowerCase().contains("0 results")
                ),
                "Unexpected results displayed"
        );
    }



    @When("user types keyword {string}")
    public void userTypesKeyword(String keyword) {
        home.clickOnSearchResult();
        home.typeText(keyword);   
    }

    @Then("user should see the autosuggestions")
    public void userShouldSeeAutosuggestions() {
        Assert.assertTrue(home.isAutoSuggestionDisplayed(),
                "Autosuggestions not visible");
    }



    @When("user clicks on wishlist icon")
    public void userClicksWishlistIcon() {
        home.clickOnWishlistIcon();
    }

 

    @When("user clicks on orders list")
    public void userClicksOrdersList() {
        home.clickOnMyOrders();
    }

  

    @Then("user should be redirected to the login page")
    public void userRedirectedToLoginPage() {
        String url = KeyWord.driver.getCurrentUrl();

        System.out.println("Current URL: " + url);

        Assert.assertTrue(url.toLowerCase().contains("login"),
                "User NOT redirected to login page");
    }
}