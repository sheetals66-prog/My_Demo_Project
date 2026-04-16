package com.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.ProductListingPage;
import com.pages.SearchResult;
import com.testbase.Testbase;

public class SearchProductTest extends Testbase{
	SearchResult sr=new SearchResult();
	ProductListingPage plp=new ProductListingPage();
	
	@Test
	public void toVerifySearchProduct() {
		sr.clickOnSearchResult();
		sr.typeAndHitSearchBar("kids");
		Assert.assertTrue(plp.productsIsDisplayedBeforeApplyingFilters());
		System.out.println("Products are Displayed..");
	}
	

}
