package com.testbase;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.utilities.ConfigReader;



public class Testbase extends KeyWord {
	@BeforeMethod
	public void setup() throws IOException {

		String browser = ConfigReader.getProperties("browser");
		String url = ConfigReader.getProperties("url");
		openBrowser(browser);
		getUrl(url);
		maximizeWindow();

	}

	
	  @AfterMethod
	   public void closeBrowser() { 
	  tearDown();
	  
	  }
	 
}
