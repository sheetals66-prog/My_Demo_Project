package com.listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.testbase.KeyWord;
import com.testbase.Testbase;
import com.utilities.ScreenShot;

public class Mylistener implements ITestListener{


	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ScreenShot.getScreenShot(result.getName());
		
	
	}

}
