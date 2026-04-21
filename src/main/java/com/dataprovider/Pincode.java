package com.dataprovider;

import org.testng.annotations.DataProvider;

import com.utilities.ExcelReader;


public class Pincode {
	@DataProvider(name ="validPincode")
	public Object[][] getExcelData() throws Exception {
		return ExcelReader.readExcel(0);
	
	
	}
	@DataProvider(name ="invalidPincode")
	public Object[][] getExcelData1() throws Exception {
		return ExcelReader.readExcel(0);
	
	}
	

}
