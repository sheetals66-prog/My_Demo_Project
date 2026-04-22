package Hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.testbase.KeyWord;
import com.utilities.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
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
		getUrl(url);
		LOG.info("url is launched..!");
	}

	@After
	public void tearDown() {
		// Call the static tearDown from KeyWord to avoid recursive call to this method
		KeyWord.tearDown();
		LOG.info("Driver is Quit successfully....!");
	}

}