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
	        {"12345"},  
	        {"1234567"}, 
	        {"ABCDE"},  
	        {"12A45"}, 
	        {""},        
	        {null}       
	    };
	}
}

