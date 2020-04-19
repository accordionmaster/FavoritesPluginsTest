package com.wxp.favorites.test;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

public abstract class AbstractFavoritesTest extends TestCase {

	public AbstractFavoritesTest(String name) {
		super(name);
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
		while (Platform.getJobManager().currentJob() != null) {
			delay(1000);
		}
	}

	/**
	 * Assert that the two arrays are equal. Throw an AssertionException if they are
	 * not.
	 * 
	 * @param expected first array
	 * @param actual   second array
	 */
	public void assertEquals(Object[] expected, Object[] actual) {
		
		if (expected == null) {
			if (actual == null) {
				return;
			}else {
				throw new AssertionFailedError("expected is null, but actual is not");
			}
		}else {
			if (actual == null) {
				throw new AssertionFailedError("actual is null, but expected is not");
			}
		}
		
		assertEquals(
				"expected.length "
					+ expected.length
					+ ", but actual.length " 
					+ actual.length,
				expected.length,
				actual.length);
		
		for (int i = 0; i < actual.length; i++)
			assertEquals(
					"expected[" + i + 
					"] is not equal to actual[" + 
					i + "]", 
					expected[i], 
					actual[i]);
		
		
		
	}
}
