package com.testbase;

import static com.testbase.KeyWord.*;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.utilities.ConfigReader;



public class Testbase extends KeyWord {
	@BeforeClass
	public void setup() throws IOException {

		String browser = "";
		String url = "";
		browser = ConfigReader.getProperties("browser");
		//url = ConfigReader.getProperties("url");

		openBrowser(browser);
		maximizeWindow();
		//getUrl(url);

	}

	@BeforeMethod
	public void beforeEachTest() throws IOException {

	    String url = ConfigReader.getProperties("url");

	    try {
	        getUrl(url);
	    } catch (Exception e) {
	        System.out.println("Retry loading URL...");
	        getUrl(url);   // retry once
	    }
	    
	}
	/*
	 * @AfterMethod
	 *  public void closeBrowser() { 
	 * tearDown();
	 * 
	 * }
	 */
}
//Initialize browser before test