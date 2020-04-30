package com.wxp.favorites.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import com.wxp.favorites.views.FavoritesView;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public class AbstractFavoritesTest {

	private static final String JAVA_PERSPECTIVE_ID = "org.eclipse.jdt.ui.JavaPerspective";
	private static IWorkbenchPage javaPage = null;
	
	public static IWorkbenchPage getJavaPage() throws WorkbenchException {
		if (javaPage == null) {
			javaPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.openPage(JAVA_PERSPECTIVE_ID, null);
		}
		return javaPage;
	}
	

	/**
	 * Process UI input but do not return for the specified time interval.
	 * 
	 * @param i
	 */
	public void delay(long waitTimeMillis) {
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
	public void waitForJobs() {
		while (Job.getJobManager().currentJob() != null) {
			delay(1000);
		}
	}
	
	protected void assertFavoritesViewContent(FavoritesView favoritesView, Object[] expectedContent, Object[] expectedLabels) {
		TableViewer viewer = favoritesView.getFavoritesViewer();
		
		// Assert valid content.
		IStructuredContentProvider contentProvider = 
				(IStructuredContentProvider) viewer.getContentProvider();
		assertArrayEquals(expectedContent, contentProvider.getElements(viewer.getInput()));
		
		// Assert valid labels
		ITableLabelProvider labelProvider = 
				(ITableLabelProvider) viewer.getLabelProvider();
		for (int i = 0; i < expectedLabels.length; i++) {
			assertEquals(expectedLabels[i], labelProvider.getColumnText(expectedContent[i], 1));
		}
	}
	
}
