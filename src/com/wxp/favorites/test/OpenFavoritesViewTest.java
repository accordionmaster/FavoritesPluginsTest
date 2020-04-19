package com.wxp.favorites.test;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import com.wxp.favorites.actions.OpenFavoritesViewActionDelegate;
import com.wxp.favorites.views.FavoritesView;
public class OpenFavoritesViewTest extends AbstractFavoritesTest {

	public OpenFavoritesViewTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Ensure the system state before the test
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		// Ensure that hte view is not open.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage();
		IViewPart view = page.findView(FavoritesView.ID);
		if (view != null) {
			page.hideView(view);
		}
		
		// Delay for 3 seconds so that 
		// the Favorites view can be seen.
		waitForJobs();
		delay(3000);
	}
	
	public void testOpenFavoritesView() {
		// Execute the operation.
		(new Action("OpenFavoritesViewTest") {
			public void run() {
				 IWorkbenchWindowActionDelegate delegate = 
						 new OpenFavoritesViewActionDelegate();
				 delegate.init(PlatformUI.getWorkbench()
						 .getActiveWorkbenchWindow());
				 delegate.selectionChanged(this, StructuredSelection.EMPTY);
				 delegate.run(this);
			};
		}).run();
		
		// Test that the operation completed successfully.
		waitForJobs();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		assertTrue(page.findView(FavoritesView.ID) != null);
	}
	
	
}





















