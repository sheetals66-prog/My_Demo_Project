package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KidsPage {
	RemoteWebDriver driver;

	@BeforeMethod
	public void launchBrowser() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		
	}

	@Test(priority = 1)
	public void toAutomateAddToCartGirlsDresses() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Hover on Kids
		WebElement kids = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-group='kids']")));

		Actions action = new Actions(driver);
		action.moveToElement(kids).perform();// hover on Kids Menu

		WebElement dresses =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/girls-dresses']")));
		dresses.click();// click on dresses

		//verify page Title
		String title = driver.getTitle();
		Assert.assertTrue(title.toLowerCase().contains("dress"), "Dresses page is not found");
		System.err.println(title);

		//verify products displayed
		List<WebElement> products= driver.findElements(By.xpath("//li[@class=\"product-base\"]"));
		System.out.println("Total products: "+products.size());
		Assert.assertTrue(products.size()>0,"products is not displayed");
		//Apply filters ->again verify products displayed
		
		//click first product
		products.get(3).click();
		
		//switch to new Tab
				String main=driver.getWindowHandle();
				Set<String> windows=driver.getWindowHandles();
				 for(String w:windows) {
					 if(!w.equals(main)) {
						 driver.switchTo().window(w);
					 }
				 }
		Thread.sleep(3000);
		 //select size
		List<WebElement>	 sizes=driver.findElements(By.xpath("//p[@class='size-buttons-unified-size']"));
		/*for(WebElement s:sizes) {
			if(s.isEnabled()) {
			s.click();
			break;
			 
			
		}*/
		//}
		sizes.get(0).click();
        Assert.assertTrue(sizes.size() > 0);
		
		
		//driver.findElement(By.tagName("body")).click();
		 
		 //Add to bag
			/*
			 * WebElement addtobag= wait.until(ExpectedConditions.elementToBeClickable(By.
			 * xpath("//button//span[text()='ADD TO BAG']"))); addtobag.click();
			 */
      
        WebElement addtobag=driver.findElement(By. xpath("//div[text()='ADD TO BAG']")); 
        addtobag.click();
	/*
	 * @AfterMethod public void close() { driver.quit(); }
	 */
	}
	}

