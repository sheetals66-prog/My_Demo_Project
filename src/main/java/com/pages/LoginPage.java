package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testbase.KeyWord;
import com.utilities.WaitFor;

public class LoginPage {

    @FindBy(css = ".wishlistLogin-button")
    private WebElement wishListLoginPopUp;

    @FindBy(xpath = "//div[@class='signInContainer']")
    private WebElement loginPage;

 
    public LoginPage() {
        PageFactory.initElements(KeyWord.driver, this);
    }

 
    public boolean isLoginPageDisplayed() {
        try {
            WaitFor.waitForElementToBeVisible(loginPage);
            return loginPage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPopUpDisplayed() {
        try {
            WaitFor.waitForElementToBeVisible(wishListLoginPopUp);
            return wishListLoginPopUp.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

