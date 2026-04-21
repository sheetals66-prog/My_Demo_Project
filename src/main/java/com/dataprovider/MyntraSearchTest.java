package com.dataprovider;

import org.testng.annotations.DataProvider;

import com.utilities.ExcelReader;

public class MyntraSearchTest {
	@DataProvider(name = "SearchData")
	public  Object[][] searchData() {
		
			    return new Object[][] {
			        {"Kids"},
			        {"Boys"},
			        {"Girls"},
			        {"Footwear"},
			        {"Infants"},
			        {"Toys & Games"},
			        {"Accessories"}
			    };
			}	



	@DataProvider(name = "invalidSearchData")
	public Object[][] invalidSearchData() {
	    return new Object[][] {
	        {""},
	        {"@#$%"},
	        {"!!!"},
	        {"@@@@"},
	        {"kids@@@"},
	        {"123@@abc"},
	        {"123456"},
	        {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}
	        
	    };
	}

	@DataProvider(name = "Category")
	public  Object[][] categoryOfProducts() {

			    	    	    return new Object[][] {

			    	    	        {"Aprons"},
			    	    	        {"Activity Toys and Games"},
			    	    	        {"Bedsheets"},
			    	    	        {"Blankets Quilts and Dohars"},
			    	    	        {"Bodysuit"},
			    	    	        {"Booties"},
			    	    	        {"Backpacks"},
			    	    	        {"Briefs"},
			    	    	        {"Blazers"},
			    	    	        {"Bath Towels"},
			    	    	        {"Baby Apparel Gift Set"},
			    	    	        {"Baby Gear & Nursery"},
			    	    	        {"Baby Utensils"},
			    	    	        {"Bibs"},
			    	    	        {"Belts"},
			    	    	        {"Boots"},
			    	    	        {"Bed Covers"},
			    	    	        {"Baby Carriers"},
			    	    	        {"Clothing Set"},
			    	    	        {"Casual Shoes"},
			    	    	        {"Caps"},
			    	    	        {"Co-Ords"},
			    	    	        {"Curtains and Sheers"},
			    	    	        {"Dungarees"},
			    	    	        {"Diapers"}
			    	    	    };
			    	    	
			    	}
	

		@DataProvider(name = "BrandData")
		public Object[][] brandData() {
		    return new Object[][] {

		        {"BAESD"},
		        {"YK"},
		        {"V-Mart"},
		        {"LULU & SKY"},
		        {"MINI KLUB"},
		        {"INCLUD"},
		        {"H&M"}
		    };
		}
			    	    	    	
			    	    	    	
			    	    	    	
			    	  
	@DataProvider(name = "colourDataForTshirts")
	public Object[][] ProductColors() {
	    return new Object[][] {

	    

	    	        {"Grey"},
	    	        {"White"},
	    	        {"Beige"},
	    	        {"Blue"},
	    	        {"Black"},
	    	        {"Brown"},
	    	        {"Red"},
	    	        {"Green"},
	    	        {"Multi"},
	    	        {"Navy Blue"},
	    	        {"Pink"},
	    	        {"Charcoal"},
	    	        {"Olive"},
	    	        {"Grey Melange"},
	    	        {"Maroon"},
	    	        {"Khaki"},
	    	        {"Sea Green"},
	    	        {"Yellow"},
	    	        {"Tan"},
	    	        {"Purple"},
	    	        {"Orange"},
	    	        {"Lavender"},
	    	        {"Cream"}
	    	    };
	    	}
	   
	
	
	        	@DataProvider(name = "sortBy")
	        	public static Object[][] sortByData() {
	        		return new Object[][] { { "Recommended" }, { "What's New" }, { "Popularity" }, { "Better Discount" },
	        				{ "Price: High to Low" }, { "Price: Low to High" }, { "Customer Rating" } };

	        	}

	        	
	        	@DataProvider(name = "discountFilterForTshirts")
	        	public Object[][] discountFilterData() {
	        	    return new Object[][] {
	        	        {"10% and above"},
	        	        {"20% and above"},
	        	        {"30% and above"},
	        	        {"40% and above"},
	        	        {"50% and above"},
	        	        {"60% and above"},
	        	        {"70% and above"},
	        	        {"80% and above"},
	        	        {"90% and above"}
	        	    };
	        	}
	        	@DataProvider(name = "genderData")
	        	public Object[][] genderData() {
	        	    return new Object[][] {
	        	        {"Boys"},
	        	        {"Girls"}
	        	    };
	        	}
	        	
	        	@DataProvider(name = "validPincodes")
	        	public Object[][] validPincodes() {
	        	    return new Object[][] {
	        	        {"400001"},
	        	        {"110001"},
	        	        {"411001"},
	        	        {"560001"}
	        	    };
	        	    
	        	}
	        	@DataProvider(name = "invalidPincodes")
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


