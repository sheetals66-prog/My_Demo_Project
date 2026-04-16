package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.testbase.Keyword;
import com.utilities.WaitFor;

public class ProductDetailPage {

	public ProductDetailPage() {
		PageFactory.initElements(Keyword.driver, this);
	}

	// Locator using @FindBy

	// Product Details
	@FindBy(css = "h1.pdp-title")
	public WebElement productTitle;

	@FindBy(css = "h1.pdp-name")
	WebElement productName;

	@FindBy(css = "span.pdp-price")
	public WebElement productPrice;

	@FindBy(css = "span.pdp-mrp")
	WebElement originalPrice;

	@FindBy(css = "div.size-buttons-size-buttons button")
	List<WebElement> sizes;

	@FindBy(css = "span.pdp-discount")
	WebElement discount;

	@FindBy(css = "div.index-overallRating")
	WebElement rating;
	@FindBy(css = "span.size-buttons-size-error-message")
	WebElement errormsg;

	// Actions
	@FindBy(xpath = "//div[contains(@class,'pdp-add-to-wishlist')]")
	WebElement wishList;

	@FindBy(css = "div.pdp-add-to-bag")
	WebElement addToBag;
	@FindBy(xpath = "//*[contains(text(),'GO TO BAG')]")
	WebElement goToBag;
	
	@FindBy(css = "input.pincode-code")
	WebElement pinCode;

	@FindBy(css = "input.pincode-check")
	WebElement checkButton;

	@FindBy(css="li.pincode-serviceabilityItem")
	List<WebElement> pincodeMsg;
	
	@FindBy(css = "p.pincode-error")
	WebElement invalidPincodeMsg;
	
	@FindBy(css = "div.pdp-offers-container")
	WebElement offers;

	@FindBy(css = "div.pdp-productDescriptorsContainer")
	WebElement productDetails;

	@FindBy(css = "li.product-base")
	List<WebElement> similarProductsList;

	@FindBy(css = "div.image-grid-image")
	List<WebElement> productImages;

	@FindBy(css = "[href=\"/checkout/cart\"]")
	List<WebElement> onCart;

	// Product Info Methods
	public String getProductTitle() {
		return productTitle.getText();
	}

	public String getProductName() {
		return productName.getText();
	}

	public boolean areProductImagesDisplayed() {
		WaitFor.waitForElementToBeClickable(productImages);
		return productImages.size() > 0;
	}

	public String getProductPrice() {
		return productPrice.getText();
	}

	public String getOriginalPrice() {
		return originalPrice.getText();
	}

	public String getDiscount() {
		return discount.getText();
	}

	public String getRating() {
		return rating.getText();
	}

	// Action Methods
	public void clickAddToBag() {
		WaitFor.waitForElementToBeClickable(addToBag);

		addToBag.click();
		WaitFor.waitForElementToBeVisible(goToBag);
	}

	public void clickGoToBag() {
		WaitFor.waitForElementToBeClickable(goToBag);
		goToBag.click();
	}

	public void clickWishlist() {
		WaitFor.waitForElementToBeClickable(wishList);
		wishList.click();
	}

	// pincode
	public void enterPincode(String code) {
		pinCode.clear();
		pinCode.sendKeys(code);
		checkButton.click();
		
	    
 
	}
	public String getPinCodeMsg() {
		WaitFor.waitForElements(pincodeMsg); //imp
	    for (WebElement el : pincodeMsg) {
	        String text = el.getText();
	        if (!text.trim().isEmpty()) {
	            return text;
	        }
	    }
	    return "";
	}
	public String getInvalidPinCodeMsg() {
		WaitFor.waitForElementToBeVisible(invalidPincodeMsg);
	    return invalidPincodeMsg.getText();
	}
	
	// Similar Products
	public void clickFirstSimilarProduct() {
		similarProductsList.get(0).click();
	}

	// Validation
	public boolean isProductPageLoaded() {
		return productName.isDisplayed();
	}

	public boolean isAddToBagDisplayed() {
		// TODO Auto-generated method stub
		return addToBag.isDisplayed();
	}

	public boolean isErrormsgdisplayed() {
		return errormsg.isDisplayed();
	}


	public boolean isOnCartPage() {

		return Keyword.getCurrentUrl().contains("cart");
	}

	public boolean isWishlistSelected() {
		return Keyword.getCurrentUrl().contains("login");
	}
	
	public boolean selectSize() {

	    for (WebElement size : sizes) {

	        if (size.isDisplayed() && size.isEnabled()) {

	            try {
	                size.click();
	            } catch (Exception e) {
	                ((JavascriptExecutor)Keyword.driver)
	                    .executeScript("arguments[0].click();", size);
	            }

	            // wait a bit for UI update
	            try { Thread.sleep(500); } catch (Exception e) {}

	            if (size.getAttribute("class").contains("selected")) {
	                System.out.println("Size selected: " + size.getText());
	                return true;
	            }
	        }
	    }

	    throw new RuntimeException("Size not getting selected ❌");
	}
	
  
	public boolean isGoToBagDisplayed() {
		try {
			WaitFor.waitForElementToBeVisible(goToBag);
			return goToBag.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	
	

}
