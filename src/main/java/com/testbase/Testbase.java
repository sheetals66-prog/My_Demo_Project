package com.testbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.cucumber.messages.types.Hook;

public class Testbase {
	
	private static final Logger LOG = LogManager.getLogger(Hook.class);

	
	@BeforeMethod
	public void setUp() {
		KeyWord.openBrowser();
		LOG.info("Browser is opened..!");
		KeyWord.openUrl();
		LOG.info("Browser is opened..!");
	}

	@AfterMethod
	public void tearDown() {
		KeyWord.closeBrowser();
		LOG.info("Driver Quit successfully......!");
	}

}


