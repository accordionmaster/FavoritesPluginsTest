package com.wxp.favorites.test;


import junit.framework.Test;
import junit.framework.TestSuite;

public class FavoritesTestSuite {

	public static Test suite() {
		TestSuite suite = 
				new TestSuite("Favorites test suite");
		
		suite.addTest(
				new TestSuite(FavoritesViewTest.class));
		
		suite.addTest(
				new TestSuite(OpenFavoritesViewTest.class));
			
		return suite;
	}
}
