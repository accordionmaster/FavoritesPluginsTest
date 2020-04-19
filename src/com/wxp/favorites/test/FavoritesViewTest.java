package com.wxp.favorites.test;

import static org.junit.Assert.assertArrayEquals;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

import com.wxp.favorites.views.FavoritesView;

public class FavoritesViewTest extends AbstractFavoritesTest{

	public FavoritesViewTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private static final String VIEW_ID = "com.wxp.favorites.views.FavoritesView";
	
	/**
	 * The object that is being tested.
	 */
	private FavoritesView testView;
	
	public void setUp() throws Exception {
		super.setUp();
		
		waitForJobs();
		testView = (FavoritesView) 
				PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(FavoritesView.ID);
		waitForJobs();
		delay(3000);
	}
	

	public void testView() {
		TableViewer viewer = testView.getFavoritesViewer();
		Object[] expectedContent = new Object[] {"One", "Two", "Three"};
		Object[] expectedLabels = new Object[] {"One", "Two", "Three"};
		
		// Assert valid content
		IStructuredContentProvider contentProvider = (IStructuredContentProvider)viewer.getContentProvider();
		assertArrayEquals(expectedContent, contentProvider.getElements(viewer.getInput()));
		
		// Assert valid labels.
		ITableLabelProvider labelProvider = (ITableLabelProvider)viewer.getLabelProvider();
		for (int i = 0; i < expectedLabels.length; i++) {
			assertEquals(expectedLabels[i],  labelProvider.getColumnText(expectedContent[i], 1));
		}
	}
	
	/**
	 * Perform post-test cleanup
	 */
	public void tearDown() {
		// Dispose of test fixture.
		waitForJobs();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(testView);
		// Add additional teardown code here.
	}

	
}
