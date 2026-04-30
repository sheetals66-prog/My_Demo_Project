package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.testbase.KeyWord;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot {

	public static void getScreenShot(String testName) {
		File src = KeyWord.driver.getScreenshotAs(OutputType.FILE);

		String DateTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());

		File dest = new File("./reports/" + testName + " " + DateTime + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static void takeFullPageScreenshot(RemoteWebDriver driver, String testName) {

		try {

			String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);

			File dest = new File("./reports/" + testName + "_" + dateTime + ".png");

			ImageIO.write(screenshot.getImage(), "PNG", dest);

			System.out.println("Screenshot saved: " + dest.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
