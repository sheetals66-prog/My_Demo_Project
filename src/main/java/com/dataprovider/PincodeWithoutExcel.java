package com.dataprovider;

import org.testng.annotations.DataProvider;

public class PincodeWithoutExcel {
	@DataProvider(name = "validPincodes")
	public Object[][] validPincodes() {
	    return new Object[][] {
	        {"400001"},
	        {"110001"},
	        {"411001"},
	        {"560001"}
	    };
	    
	}
	
	public Object[][] invalidPincodes() {
	    return new Object[][] {
	        {"12345"},   // too short
	        {"1234567"}, // too long
	        {"ABCDE"},   // non-numeric
	        {"12A45"},   // mixed characters
	        {""},        // empty string
	        {null}       // null value
	    };
	}
}

