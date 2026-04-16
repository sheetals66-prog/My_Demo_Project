package com.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage {
	WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();

		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Myntra"), "Title validation is failed");

		WebElement logo = driver.findElement(By.xpath("//div[@class=\"desktop-logoContainer\"]"));
		Assert.assertTrue(logo.isDisplayed(), "logo is not visible");

		List<WebElement> menu = driver.findElements(By.xpath("//a[@class=\"desktop-main\"]"));
		System.out.println("count of menu:" + menu.size());

		for (WebElement m : menu) {
			System.out.println("Menu items:" + m.getText());
			Assert.assertTrue(m.isDisplayed(), "menu is not visible" + m.getText());
		}

		WebElement searchBox = driver
				.findElement(By.xpath("//input[@placeholder=\"Search for products, brands and more\"]"));
		Assert.assertTrue(searchBox.isDisplayed(), "searchBox is not displayed");
		Assert.assertTrue(searchBox.isEnabled(), "searchBox is not enabled");
		searchBox.sendKeys("Kids shoes");

		WebElement profile = driver.findElement(By.xpath("//span[text()=\"Profile\"]"));
		profile.click();
		Assert.assertTrue(profile.isEnabled(), "profile is not enabled");

		WebElement loginBtn = driver.findElement(By.xpath("//a[contains(text(),'login')]"));
		Assert.assertTrue(loginBtn.isDisplayed(), "loginBtn is not visible");

		WebElement wishList = driver.findElement(By.xpath("//span[text() = 'Wishlist']"));
		Assert.assertTrue(wishList.isDisplayed(), "wishList is not displayed");

		WebElement bag = driver.findElement(By.xpath("//span[text()=\"Bag\"]"));
		Assert.assertTrue(bag.isDisplayed(), "bag is not displayed");
		
		List<WebElement> footerlink= driver.findElements(By.xpath("//div[@class=\"desktop-pSearchlinks\"]//a"));
		for(WebElement link:footerlink) {
			Assert.assertTrue(link.isDisplayed(),"Footerlink is not visible"+link.getText());
			Assert.assertTrue(link.isEnabled(),"Footerlink is not clickable"+link.getText());
		//	System.out.println("Footer link: "+link.getText());
			
		}
	}
}
