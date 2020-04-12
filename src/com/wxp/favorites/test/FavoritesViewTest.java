package com.wxp.favorites.test;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.wxp.favorites.views.FavoritesView;

public class FavoritesViewTest {

	private static final String VIEW_ID = "com.wxp.favorites.views.FavoritesView";
	
	/**
	 * The object that is being tested.
	 */
	private FavoritesView testView;
	
	@Before
	public void setUp() throws Exception {
		// Initialize the test fixture for each test
		// that is run.
		waitForJobs();
		testView = (FavoritesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(VIEW_ID);
		// Delay for 3 seconds so that 
		// the Favorites view can be seen.
		waitForJobs();
		delay(3000);
		// Add additional setup code here.
	}
	
	@Test
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

	/**
	 * Process UI input but do not return for the specified time interval.
	 * @param i
	 */
	private void delay(long waitTimeMillis) {
		Display display = Display.getCurrent();
		
		// If this is the UI thread,
		// then process input.
		if (display != null) {
			long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
				display.update();
			}
		}
		// Otherwise, perform a simple sleep.
		else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
		
	}

	/**
	 * Wait until all background tasks are complete.
	 */
	private void waitForJobs() {
		while (Job.getJobManager().currentJob() != null) {
			delay(1000);
		}
	}
	
	
}
