package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
	
	@FindBy(xpath="//p[contains(text(),'Please enter PIN code to check delivery time & Pay on Delivery Availability')]")
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
		closePopupIfPresent();
		WaitFor.waitForElementToBeClickable(addToBag);
		addToBag.click();

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
		WaitFor.waitForElementToBeVisible(checkButton);
		try {
			pinCode.clear();
		} catch (Exception e) {
			// ignore
		}
		pinCode.sendKeys(code);
		checkButton.click();
		try {
			if (changePinCodeBtn.isDisplayed()) {
				WaitFor.waitForElementToBeClickable(changePinCodeBtn);
				pinCode.sendKeys(code);
				checkButton.click();
			} else {
				WaitFor.waitForElements(pincodeMsg);
			}
		} catch (Exception e) {
			// fallback wait
			WaitFor.waitForElements(pincodeMsg);
		}
	}

	public String getPincodeErrorMsg() {
		try {
			if (enterValidPincodeMsg != null && enterValidPincodeMsg.isDisplayed()) {
				return enterValidPincodeMsg.getText();
			}
		} catch (Exception e) {
			// ignore
		}
		// if specific element not found, look into pincode messages list
		try {
			WaitFor.waitForElements(pincodeMsg);
			for (WebElement el : pincodeMsg) {
				String text = el.getText();
				if (!text.trim().isEmpty()) {
					return text;
				}
			}
		} catch (Exception e) {
			// ignore
		}
		return "";
		
	}
	public String getInvalidPincodeToast() {
	    WebDriverWait wait = new WebDriverWait(KeyWord.driver, Duration.ofSeconds(10));

	    try {
	        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//*[contains(text(),'valid pincode')]")
	        ));

	        return toast.getText();
	    } catch (Exception e) {
	        // fallback to invalidPincodeMsg element
	        try {
	            if (invalidPincodeMsg != null && invalidPincodeMsg.isDisplayed()) {
	                return invalidPincodeMsg.getText();
	            }
	        } catch (Exception ex) {
	            // ignore
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

	public boolean selectSize() {
		for (int i = 0; i < sizes.size(); i++) {

			try {
				WebElement size = sizes.get(i);

				if (size.isDisplayed() && size.isEnabled()) {

					((JavascriptExecutor) KeyWord.driver).executeScript("arguments[0].scrollIntoView(true);", size);

					WaitFor.waitForElementToBeClickable(size);
					size.click();

					try {
						WaitFor.until(ExpectedConditions.attributeContains(size, "class", "selected"));
					} catch (Exception e) {

					}

					if (size.getAttribute("class").contains("selected")) {
						System.out.println("Size selected: " + size.getText());
						return true;
					}
				}

			} catch (StaleElementReferenceException e) {

				System.out.println("Stale element, retrying...");

				try {
					WebElement size = sizes.get(i);

					((JavascriptExecutor) KeyWord.driver).executeScript("arguments[0].scrollIntoView(true);", size);

					((JavascriptExecutor) KeyWord.driver).executeScript("arguments[0].click();", size);

					return true;

				} catch (Exception ex) {

				}

			} catch (Exception e) {

				try {
					WebElement size = sizes.get(i);
					((JavascriptExecutor) KeyWord.driver).executeScript("arguments[0].click();", size);
					return true;
				} catch (Exception ex) {

				}
			}
		}

		return false;
	}

	public boolean isGoToBagDisplayed() {
		try {
			WaitFor.waitForElementToBeVisible(goToBag);
			return goToBag.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// New helper: is add to bag clickable
	public boolean isAddToBagClickable() {
		try {
			WaitFor.waitForElementToBeClickable(addToBag);
			return addToBag.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	// New helper: check wishlist active state by checking class or aria attribute
	public boolean isWishlistActiveStateSelected() {
		try {
			if (wishList == null) return false;
			String cls = wishList.getAttribute("class");
			if (cls != null && cls.toLowerCase().contains("active")) return true;
			String aria = wishList.getAttribute("aria-pressed");
			return "true".equalsIgnoreCase(aria);
		} catch (Exception e) {
			return false;
		}
	}

}