package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

import static com.utilities.WaitFor.*;

public class CartPage {

	public CartPage() {
		PageFactory.initElements(KeyWord.driver, this);
	}

	@FindBy(xpath = "(//div[@class=\"css-xjhrni\"])[1]")
	WebElement couponButton;
	@FindBy(xpath = "//button[@id=\"applyCoupon\"]/div[@class=\"css-xjhrni\"]")
	WebElement applyCouponButton;
	@FindBy(xpath = "//div[@role='dialog']//*[name()='svg' and @data-testid='modal-close']")
	WebElement closeCouponPopup;

	@FindBy(xpath = "//div[contains(text(),'Coupon applied')]")
	WebElement couponAppliedMessage;

	@FindBy(css = "div.priceBlock-base-container")
	WebElement priceDetails;
	@FindBy(xpath = "//*[contains(text(),'PLACE ORDER')]")
	WebElement placeOrderButton;

	@FindBy(xpath = "//div[contains(@class,'bulkActionStrip')]")
	WebElement productSelectCheckbox;

	@FindBy(xpath = "//button[text()=\"REMOVE\"]")
	WebElement removeButton;
	@FindBy(css = "div[role='dialog'] button.inlinebuttonV2-base-actionButton")
	WebElement removePopup;

	@FindBy(xpath = "//button[text()=\"MOVE TO WISHLIST\"] ")
	WebElement moveToWishlist;
	@FindBy(xpath = "//div[@role='dialog']//button[normalize-space()='MOVE TO WISHLIST']")
	WebElement moveToWishlistPopup;
	@FindBy(xpath = "//div[@role='dialog']//button[normalize-space()='CANCEL']")
	WebElement cancelButton;

	@FindBy(xpath = "//div[text()=\"ENTER PIN CODE\"]")
	WebElement pincode;
	@FindBy(css = "input[placeholder='Enter Pincode']")
	WebElement pincodeInput;
	@FindBy(xpath = "//div[@class=\"CheckDeliveryModalV2-base-pincodeContainer\"]/div[text()=\"CHECK\"]")
	WebElement pincodeCheck;
	@FindBy(xpath = "//div[contains(@class,'addressStripV2-base')]")
	WebElement deliveryAddress;

	// Product details
	@FindBy(css = "div.itemContainer-base-brand")
	WebElement brandName;
	@FindBy(css = "a.itemContainer-base-itemLink")
	WebElement productName;
	@FindBy(xpath = "//div[@class='priceDetail-base-total']")
	WebElement productPrice;
	@FindBy(css = "div.itemComponents-base-size")
	WebElement productSize;
	@FindBy(css = "div.itemComponents-base-quantity")
	WebElement productQuantity;
	@FindBy(xpath = "//h1[contains(text(),'Checkout')]") // adjust locator
	WebElement checkoutPageTitle;
	@FindBy(xpath = "//div[text()=\"ADD GIFT PACKAGE\"]")
	WebElement addGiftPackage;
	@FindBy(xpath = "//div[@id='cartItemsList']//div[@class='item-base-item']")
	List<WebElement> productcards;
	@FindBy(css = "div.itemContainer-base-itemLeft")
	List<WebElement> productImages;

	// New locators for coupon input and messages
	@FindBy(xpath = "(//div[@role='dialog']//input)[1]")
	WebElement couponInput;
	@FindBy(xpath = "//div[contains(text(),'Invalid coupon') or contains(text(),'Coupon code is invalid')]")
	WebElement couponErrorMessage;
	@FindBy(xpath = "//div[contains(text(),'Please enter') and contains(text(),'coupon')]")
	WebElement couponValidationMessage;

	@FindBy(xpath = "//*[contains(text(),'Maximum') or contains(text(),'max quantity') or contains(text(),'only')]")
	WebElement maxQuantityWarning;

	@FindBy(xpath = "//*[contains(text(),'Your cart is empty') or contains(text(),'cart is empty')]")
	WebElement emptyCartMessage;

	@FindBy(xpath = "//div[contains(@class,'login') or //h2[contains(.,'Login')]]")
	WebElement loginPopup;

	public void clickPlaceOrder() {
		waitForElementToBeVisible(placeOrderButton);
		placeOrderButton.click();
	}

	public void clickCouponButton() {
		try {
			waitForElementToBeVisible(couponButton);
			couponButton.click();
		} catch (Exception e) {
			// ignore
		}
	}

	public void clickApplyCouponButton() {
		waitForElementToBeVisible(applyCouponButton);
		applyCouponButton.click();
		try {
			closeCouponPopup.click();
		} catch (Exception e) {
			// ignore
		}
	}

	public boolean isCouponApplied() {
		try {
			waitForElementToBeVisible(couponAppliedMessage);
			return couponAppliedMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getPriceDetails() {
		return priceDetails.getText();
	}

	public void selectProduct() {
		productSelectCheckbox.click();
	}

	public void clickRemove() {
		removeButton.click();
	}

	public void clickMoveToWishlist() {
		moveToWishlist.click();
	}

	public void clickPincode() {
		pincode.click();
	}

	public void enterPincode(String pincode) {
		waitForElementToBeVisible(pincodeInput);
		pincodeInput.sendKeys(pincode);
	}

	public void clickPincodeCheck() {
		waitForElementToBeVisible(pincodeCheck);
		pincodeCheck.click();
	}

	public String getDeliveryAddress() {
		waitForElementToBeVisible(deliveryAddress);
		return deliveryAddress.getText();
	}

	public void clickAddGiftPackage() {
		addGiftPackage.click();
	}

	public void clickRemovePopup() {
		waitForElementToBeVisible(removePopup);
		removePopup.click();
	}

	public void clickMoveToWishlistPopup() {
		waitForElementToBeVisible(moveToWishlistPopup);
		moveToWishlistPopup.click();
	}

	public void clickCancelButton() {
		waitForElementToBeVisible(cancelButton);
		cancelButton.click();
	}

	public boolean isPlaceOrderButtonDisplayed() {

		waitForElementToBeVisible(placeOrderButton);
		return placeOrderButton.isDisplayed();
	}

	public boolean isCouponButtonDisplayed() {
		return couponButton.isDisplayed();
	}

	public String getText(WebElement element, String locator) {
		try {
			return element.findElement(By.cssSelector(locator)).getText();
		} catch (Exception e) {
			return "N/A";
		}
	}

	public boolean isProductSelected() {

		String classValue = productSelectCheckbox.getAttribute("class");

		return classValue != null && classValue.contains("selected");

	}
	
	//Product details methods

	public boolean isProductPresent() {
		return !productcards.isEmpty();
	}

	public int getCartItemCount() {
		return productcards.size();
	}

	public String getProductName() {
		return productName.getText();
	}

	public String getBrandName() {
		return brandName.getText();
	}

	public String getProductPrice() {
		WaitFor.waitForElementToBeVisible(productPrice);
		return productPrice.getText();
	}
	public String getProductSize() {
		return productSize.getText();
	}
	public String getProductQuantity() {
		WaitFor.waitForElementToBeVisible(productQuantity);
		return productQuantity.getText();
	}

	public void increaseQuantity() {
		// TODO Auto-generated method stub
		return;
	}

	public boolean isCheckoutPageLoaded() {
		waitForElementToBeVisible(checkoutPageTitle);
		return checkoutPageTitle.isDisplayed();

	}
	
	public void waitForProductSelectCheckbox() {
		try {
			waitForElementToBeVisible(productSelectCheckbox);
		} catch (Exception e) {
			// ignore
		}
	}

	// New helper methods used by tests
	public boolean isRemoveButtonVisible() {
		try {
			return removeButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void enterCoupon(String code) {
		try {
			waitForElementToBeVisible(couponInput);
			couponInput.clear();
			couponInput.sendKeys(code);
		} catch (Exception e) {
			// ignore if not present
		}
	}

	public boolean isCouponErrorDisplayed() {
		try {
			waitForElementToBeVisible(couponErrorMessage);
			return couponErrorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isCouponValidationMessageDisplayed() {
		try {
			waitForElementToBeVisible(couponValidationMessage);
			return couponValidationMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isPlaceOrderButtonEnabled() {
		try {
			waitForElementToBeVisible(placeOrderButton);
			return placeOrderButton.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public void increaseQuantityToMaxLimit() {
		// try to click increase button repeatedly until a warning appears or until a safe cap
		try {
			List<WebElement> incButtons = KeyWord.driver.findElements(By.xpath("//button[contains(@aria-label,'increase') or contains(@class,'qty-increase') or contains(.,'+')]") );
			if (incButtons.isEmpty()) return;
			WebElement inc = incButtons.get(0);
			for (int i=0;i<50;i++) {
				inc.click();
				try { Thread.sleep(200); } catch (InterruptedException e) {}
				if (isMaxQuantityWarningDisplayed()) break;
			}
		} catch (Exception e) {
			// ignore
		}
	}

	public boolean isMaxQuantityWarningDisplayed() {
		try {
			waitForElementToBeVisible(maxQuantityWarning);
			return maxQuantityWarning.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLoginPopupDisplayed() {
		try {
			// Check for a login form or popup
			List<WebElement> loginElements = KeyWord.driver.findElements(By.xpath("//form//input[@type='email' or @name='email' or @placeholder='Email'] | //h2[contains(.,'Login')]"));
			return loginElements.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEmptyCartMessageDisplayed() {
		try {
			waitForElementToBeVisible(emptyCartMessage);
			return emptyCartMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}