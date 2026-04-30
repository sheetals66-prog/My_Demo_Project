package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import static com.testbase.KeyWord.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class ProductDetailPage {

	public ProductDetailPage() {
		PageFactory.initElements(KeyWord.driver, this);
	}

	@FindBy(css = "h1.pdp-title")
	public WebElement productTitle;

	@FindBy(css = "h1.pdp-name")
	WebElement productName;

	@FindBy(css = "span.pdp-price")
	public WebElement productPrice;

	@FindBy(css = "span.pdp-mrp")
	WebElement originalPrice;

	@FindBy(xpath = "//div[@class=\"size-buttons-tipAndBtnContainer\"]")
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
	public WebElement addToBag;

	@FindBy(xpath = "//span[text()='GO TO BAG']")
	WebElement goToBag;

	@FindBy(css = "input.pincode-code")
	WebElement pinCode;

	@FindBy(css = "input.pincode-check")
	WebElement checkButton;

	@FindBy(xpath = "//button[@class=\"pincode-check-another-pincode pincode-button\"]")
	WebElement changePinCodeBtn;

	@FindBy(css = "li.pincode-serviceabilityItem")
	List<WebElement> pincodeMsg;

	@FindBy(xpath = "//p[contains(text(),'Please enter PIN code to check delivery time & Pay on Delivery Availability')]")
	WebElement enterValidPincodeMsg;

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

	@FindBy(css = ".image-image")
	WebElement popupCloseBtn;
	@FindBy(css = ".pdp-add-to-wishlist")
	WebElement WishListButton;

	public boolean isWishListButtonIsPresent() {
		return KeyWord.isDisplayed(WishListButton);
	}

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

	public void closePopupIfPresent() {
		try {
			WaitFor.waitForElementToBeClickable(popupCloseBtn);

			popupCloseBtn.click();
		} catch (Exception e) {

		}
	}

	public void clickAddToBag() {
//		closePopupIfPresent();
//	    try {
//	        WaitFor.waitForElementToBeClickable(addToBag);
//	        addToBag.click();
//
//	    } catch (Exception e) {
//	        System.out.println("Retry clicking Add to Bag");
//
//	        // fallback click using JS
//	        JavascriptExecutor js = (JavascriptExecutor) KeyWord. driver;
//	        js.executeScript("arguments[0].click();", addToBag);
//	    }

		WaitFor.waitForElementToBeVisible(addToBag);
		WaitFor.waitForElementToBeClickable(addToBag);
		addToBag.click();
		waitForSeconds(2000);
	}

	public void clickGoToBag() {
		WaitFor.waitForElementToBeClickable(goToBag);
		goToBag.click();
	}

	public void clickWishlist() {
		WaitFor.waitForElementToBeClickable(wishList);
		wishList.click();
	}

	public void clickChangePinCode() {
		WaitFor.waitForElementToBeClickable(changePinCodeBtn);
		changePinCodeBtn.click();
	}

	public String generateRandomPin() {
		Random rand = new Random();
		int pin = 100000 + rand.nextInt(900000);
		return String.valueOf(pin);
	}

	public void enterPincode(String code) {

		WaitFor.waitForElementToBeVisible(pinCode);

		try {
			pinCode.clear();
			KeyWord.waitForSeconds(1000);
		} catch (Exception e) {
			KeyWord.waitForSeconds(1000);
		}

		pinCode.sendKeys(code);

		WaitFor.waitForElementToBeClickable(checkButton);
		checkButton.click();
		WaitFor.waitForElements(pincodeMsg);

		try {
			if (changePinCodeBtn.isDisplayed()) {
				System.out.println("Pincode applied, change option available");
			}
		} catch (Exception e) {

		}
	}

	public String getInvalidPincodeToast() {
		WebDriverWait wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(10));

		try {
			WebElement toast = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'valid pincode')]")));

			return toast.getText();
		} catch (Exception e) {

			try {
				if (invalidPincodeMsg != null && invalidPincodeMsg.isDisplayed()) {
					return invalidPincodeMsg.getText();
				}
			} catch (Exception ex) {

			}
			return "";
		}
	}

	public String getPinCodeMsg() {
		WaitFor.waitForElements(pincodeMsg); // imp
		for (WebElement el : pincodeMsg) {
			String text = el.getText();
			if (!text.trim().isEmpty()) {
				return text;
			}
		}
		return "";
	}

	public String getInvalidPinCodeMsg() {
		try {
			WaitFor.waitForElementToBeVisible(invalidPincodeMsg);
			return invalidPincodeMsg.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public void clickFirstSimilarProduct() {
		similarProductsList.get(0).click();
	}

	public boolean isProductPageLoaded() {
		WaitFor.waitForElementToBeVisible(productName);
		return productName.isDisplayed();
	}

	public boolean isAddToBagDisplayed() {

		return addToBag.isDisplayed();
	}

	public boolean isErrormsgdisplayed() {
		return errormsg.isDisplayed();
	}

	public boolean isOnCartPage() {
		return KeyWord.getCurrentUrl().contains("cart");
	}

	public boolean isWishlistSelected() {
		return KeyWord.getCurrentUrl().contains("login");
	}

	public String selectSize(int index) {
		try {
			WaitFor.waitForElementToBeClickable(sizes.get(index));

			String selectedSize = sizes.get(index).getText(); // capture text
			sizes.get(index).click();

			Thread.sleep(1000);

			return selectedSize;

		} catch (Exception e) {
			System.out.println("No size available");
			return null;
		}
	}

	public boolean isGoToBagDisplayed() {
		try {
			WaitFor.waitForElementToBeVisible(goToBag);
			return goToBag.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAddToBagClickable() {
		try {
			WaitFor.waitForElementToBeClickable(addToBag);
			return addToBag.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isWishlistActiveStateSelected() {
		try {
			if (wishList == null)
				return false;
			String cls = wishList.getAttribute("class");
			if (cls != null && cls.toLowerCase().contains("active"))
				return true;
			String aria = wishList.getAttribute("aria-pressed");
			return "true".equalsIgnoreCase(aria);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSizeAvailable() {
		return sizes.size() > 0;
	}

	public String getErrorMsg() {
		// TODO Auto-generated method stub
		WaitFor.waitForElementToBeVisible(errormsg);
		return errormsg.getText();

	}

}