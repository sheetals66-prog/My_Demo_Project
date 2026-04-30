package com.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.ScreenShot;

public class Mylistener implements ITestListener{


	@Override
	public void onTestFailure(ITestResult result) {
		ScreenShot.getScreenShot(result.getName());
		
	
	}

}
