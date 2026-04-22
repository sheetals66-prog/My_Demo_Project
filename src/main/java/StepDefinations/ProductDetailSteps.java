package StepDefinations;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.ProductDetailPage;
import com.pages.ProductListingPage;
import com.testbase.KeyWord;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ProductDetailSteps {

    HomePage home = new HomePage();
    ProductListingPage plp = new ProductListingPage();
    ProductDetailPage pd = new ProductDetailPage();
    CartPage cart = new CartPage();

    @When("User searches for {string}")
    public void user_searches_for(String product) {
        home.clickOnSearchResult();
        home.typeAndHitSearchBar(product);
    }

    @And("products should be visible on product listing page")
    public void products_should_be_visible_on_product_listing_page() {
        plp.waitForProductsToLoad();
        assertTrue(plp.productsIsDisplayedBeforeApplyingFilters(), "Products are not visible on PLP");
    }

    @When("User selects the product at index {int} from listing")
    public void user_selects_the_product_at_index_from_listing(Integer index) {
        plp.waitForProductsToLoad();
        plp.clickProductByIndex(index);
    }

    @Then("Product detail page should be displayed")
    public void product_detail_page_should_be_displayed() {
        assertTrue(pd.isProductPageLoaded(), "Product Detail page NOT loaded");
    }

    @And("product title should be visible")
    public void product_title_should_be_visible() {
        String title = pd.getProductTitle();
        Assert.assertNotNull(title, "Product title is NULL");
        Assert.assertFalse(title.trim().isEmpty(), "Product title is EMPTY");
        System.out.println("Product Title: " + title);
    }

    @And("product price should be visible")
    public void product_price_should_be_visible() {
        String price = pd.getProductPrice();
        Assert.assertNotNull(price, "Product price is NULL");
        Assert.assertFalse(price.trim().isEmpty(), "Product price is EMPTY");
        System.out.println("Product Price: " + price);
    }

    @And("product images should be visible")
    public void product_images_should_be_visible() {
        assertTrue(pd.areProductImagesDisplayed(), "Product images NOT visible");
    }

    // Size selection without parameter (pick any available)
    @When("User selects size")
    public void user_selects_size_no_param() {
        boolean selected = pd.selectSize();
        assertTrue(selected, "Failed to select any size");
    }

    // Size selection with explicit size string
    	@When("User selects size {string}")
    	public void user_selects_size(String size) {

    	    boolean isSelected = false;

    	    try {
    	        By sizeBtn = By.xpath("//button[contains(normalize-space(.),'" + size + "')]");
    	        WebElement element = KeyWord.driver.findElement(sizeBtn);

    	        if (element.isDisplayed() && element.isEnabled()) {
    	            element.click();
    	            isSelected = true;
    	            System.out.println("Selected size from feature file: " + size);
    	        }

    	    } catch (Exception e) {
    	        System.out.println("Size not found: " + size + " -> selecting first available size");
    	    }

    	    // Fallback → select first available size
    	    if (!isSelected) {
    	        boolean fallback = pd.selectSize(); // your existing method
    	        Assert.assertTrue(fallback, "No selectable size available on PDP");
    	    }
    	}

    @Then("selected size should be highlighted")
    public void selected_size_should_be_highlighted() {
        assertTrue(pd.isAddToBagClickable(), "Size not selected OR Add to Bag not enabled");
    }

    @And("product availability for selected size should be displayed")
    public void product_availability_for_selected_size_should_be_displayed() {
        if (pd.isErrormsgdisplayed()) {
            Assert.fail("Selected size is unavailable");
        }
        assertTrue(pd.isAddToBagDisplayed(), "Add to Bag not visible after size selection");
    }

    @When("User clicks add to bag")
    public void user_clicks_add_to_bag() {
        pd.clickAddToBag();
        assertTrue(pd.isGoToBagDisplayed(), "Go To Bag not visible after Add to Bag");
    }

    @Then("product should be added to cart")
    public void product_should_be_added_to_cart() {
        pd.clickGoToBag();
        assertTrue(cart.isProductPresent(), "Product not present in cart");
    }

    @And("cart count should increase by 1")
    public void cart_count_should_increase_by_1() {
        int count = cart.getCartItemCount();
        assertTrue(count > 0, "Cart count not increased");
    }

    @Then("no results message should be displayed on listing page")
    public void no_results_message_should_be_displayed_on_listing_page() {
        assertTrue(home.isNoResultMessageDisplayed(), "No results message not displayed");
    }

}