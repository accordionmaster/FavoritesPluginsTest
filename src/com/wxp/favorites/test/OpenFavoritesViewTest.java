package com.wxp.favorites.test;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.junit.Before;
import org.junit.Test;

import com.wxp.favorites.actions.OpenFavoritesViewActionDelegate;
import com.wxp.favorites.handlers.OpenFavoritesViewHandler;
import com.wxp.favorites.views.FavoritesView;
public class OpenFavoritesViewTest extends AbstractFavoritesTest {



	/**
	 * Ensure the system state before the test
	 */
	@Before
	public void setUp() throws Exception {
		// Ensure that hte view is not open.
		waitForJobs();
		IViewPart view = getJavaPage().findView(FavoritesView.ID);
		if (view != null) {
			getJavaPage().hideView(view);
		}
		
		waitForJobs();
	}
	
	@Test
	public void testOpenFavoritesView() throws WorkbenchException, ExecutionException {
		
		// Setup execution context
		IWorkbenchWindow window = getJavaPage().getWorkbenchWindow();
		EvaluationContext context = new EvaluationContext(null, window);
		context.addVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME, window);
		ExecutionEvent event = new ExecutionEvent(null, Collections.EMPTY_MAP, null, context);
		
		// Execute the operation.
		new OpenFavoritesViewHandler().execute(event);
		
		// Test that the operation completed successfully.
		waitForJobs();
		IViewPart view = getJavaPage().findView(FavoritesView.ID);
		assertTrue(view != null);
	}
	
	
}





















