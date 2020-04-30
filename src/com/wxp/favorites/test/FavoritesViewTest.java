package com.wxp.favorites.test;

import org.eclipse.ui.WorkbenchException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wxp.favorites.views.FavoritesView;

public class FavoritesViewTest extends AbstractFavoritesTest{

	/**
	 * The object that is being tested.
	 */
	private FavoritesView testView;
	
	@Before
	public void setUp() throws Exception {
		waitForJobs();
		testView = (FavoritesView) getJavaPage().showView(FavoritesView.ID);
		waitForJobs();
		delay(3000);
	}
	
	@Test
	public void testView() {
		assertFavoritesViewContent(testView, new Object[] {}, new String[] {});
	}
	
	/**
	 * Perform post-test cleanup
	 * @throws WorkbenchException 
	 */
	@After
	public void tearDown() throws WorkbenchException {
		// Dispose of test fixture.
		waitForJobs();
		getJavaPage().hideView(testView);
		// Add additional teardown code here.
	}

	
}
