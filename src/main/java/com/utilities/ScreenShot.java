package com.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ScreenShot {

	public static void captureScreenshot(RemoteWebDriver driver, String testname) {
		// TODO Auto-generated method stub
		File src=driver.getScreenshotAs(OutputType.FILE);
		String time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmmss"));
		File dest=new File("ScreenShots/"+ testname+"_"+time+".png");
		//FileHandler.copy(src, dest);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
