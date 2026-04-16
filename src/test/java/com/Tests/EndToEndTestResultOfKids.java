package com.Tests;

import java.security.PublicKey;

import org.testng.annotations.Test;

import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Testbase;
import com.utilities.WaitFor;

public class EndToEndTestResultOfKids extends Testbase{

	SearchResult s=new SearchResult();
	ProductListingPage plp=new ProductListingPage();
	
	@Test
	public void verifyKidsProductIsGettingSearch() throws InterruptedException {
		s.clickOnSearchResult();
	Thread.sleep(5000);
		s.typeAndHitSearchBar("kids");	
	}
	
	
	
	
}
