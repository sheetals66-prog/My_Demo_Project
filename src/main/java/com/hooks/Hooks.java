package com.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.testbase.KeyWord;
import com.utilities.ConfigReader;
import com.utilities.ScreenShot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.testbase.KeyWord.*;

import java.io.IOException;

public class Hooks {

	private static final Logger LOG = LogManager.getLogger(Hooks.class);

	@Before
	public void setUp() throws IOException {
		String browser = ConfigReader.getProperties("browser");
		String url = ConfigReader.getProperties("url");
		openBrowser(browser);
		LOG.info("Browser is opened..!");
		KeyWord.maximizeWindow();
		getUrl(url);
		LOG.info("url is launched..!");
		
	}

	
	@After
	public void tearDown(Scenario scenario) {

	    if (scenario.isFailed()) {
	        ScreenShot.takeFullPageScreenshot(driver, scenario.getName());
	    }

	    driver.quit();
	}
}