package com.wxp.favorites.test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.wxp.favorites.actions.AddToFavoritesActionDelegate;

import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;

public class AddToFavoritesTest extends AbstractFavoritesTest{

	public AddToFavoritesTest(String name) {
		super(name);
	} 
	
	protected IProject project;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		project = root.getProject("TestProj");
		project.create(null);
		project.open(null);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		delay(3000);
		waitForJobs();
		project.delete(true, true, null);
	}
	
	public void testAddToFavorites() throws PartInitException {
		// Show the resource navigator and select the project.
		IViewPart navigator = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.views.ResourceNavigator");
		StructuredSelection selection = new StructuredSelection(project);
		((ISetSelectionTarget)navigator).selectReveal(selection);
		
		// Execute the action.
		final IObjectActionDelegate delegate = new AddToFavoritesActionDelegate();
		IAction action = new Action("Test Add to Favorites") {
			@Override
			public void run() {
				delegate.run(this);
			}
		};
		
		delegate.setActivePart(action, navigator);
		delegate.selectionChanged(action, selection);
		action.run();
	}

}
