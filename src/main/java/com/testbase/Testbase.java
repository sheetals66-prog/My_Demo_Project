package com.testbase;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.utilities.ConfigReader;



public class Testbase extends KeyWord {
	@BeforeClass
	public void setup() throws IOException {

		
		
		 String browser = ConfigReader.getProperties("browser");
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
	
	  @AfterMethod
	   public void closeBrowser() { 
	  tearDown();
	  
	  }
	 
}
//Initialize browser before test