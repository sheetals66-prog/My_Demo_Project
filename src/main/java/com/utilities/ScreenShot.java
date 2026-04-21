package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.testbase.KeyWord;

public class ScreenShot {

		public static void getScreenShot(String testName)
	{
		File src=KeyWord.driver.getScreenshotAs(OutputType.FILE);	
		
		String DateTime= new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		
		File dest=new File("./reports/"+testName+" "+DateTime+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
