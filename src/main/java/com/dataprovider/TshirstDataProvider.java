package com.dataprovider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.utilities.ExcelReader;


public class TshirstDataProvider {
	
		@DataProvider(name="brandData")
		public static Object[][] readExcelOfTshirts_Brands() throws IOException {
			return ExcelReader.readExcel(1);
		}
		
		@DataProvider(name="pincodeData")
		public static Object[][] readExcelOfPincodes() throws IOException {
			return ExcelReader.readExcel(1);
		}
		@DataProvider(name = "Category")
		public static Object[][] readExcelOfCategory() throws IOException {
			return ExcelReader.readExcel(1);
		}
		@DataProvider(name = "Brand")
		public static Object[][] readExcelOfBrand() throws IOException {
			return ExcelReader.readExcel(1);
		}
		@DataProvider(name = "Color")
		public static Object[][] readExcelOfColor() throws IOException {
			return ExcelReader.readExcel(1);
		}
	
		@DataProvider(name = "CombinData")
		public static Object[][] readExcelOfCombineData() throws IOException {
			return ExcelReader.readExcel(1);
		}
	}
	

