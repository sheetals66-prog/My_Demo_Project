package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class LoginPage {
	
	@FindBy(css = ".wishlistLogin-button")
	WebElement  wishListLoginPopUp;
	
	@FindBy(xpath="//div[@class=\"signInContainer\"]")
	WebElement loginPage;
	
	{
		
		PageFactory.initElements(KeyWord.driver, this);
	}
	
	public boolean isLoginPageDisplayed() {
		WaitFor.visibilityOfelement(loginPage);
		try {
			return KeyWord.isDisplayed(loginPage);
		}
		catch(RuntimeException e) {
			
		}
		return false;
	}
	
	
	public boolean isLoginPopUpDisplayed() {
		com.utilities.WaitFor.visibilityOfelement(wishListLoginPopUp);
		try {
		return KeyWord.isDisplayed(wishListLoginPopUp);
		}
		catch(Exception e) {
			return false;
		}
		
	}
	
	
	

}