package com.testbase;

import static com.testbase.Keyword.*;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utilities.ConfigReader;

public class Testbase extends Keyword {
	@BeforeMethod
	public void setup() throws IOException {

		String browser = "";
		String url = "";
		browser = ConfigReader.getProperties("browser");
		url = ConfigReader.getProperties("url");

		openBrowser(browser);
		maximizeWindow();
		getUrl(url);

	}

	/*
	 * @AfterMethod public void closeBrowser() { tearDown();
	 * 
	 * }
	 */
}
//Initialize browser before test