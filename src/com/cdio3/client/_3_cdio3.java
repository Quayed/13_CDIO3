package com.cdio3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _3_cdio3 implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Create operator
		Anchor createOperatorAnchor = new Anchor();
		createOperatorAnchor.setText("Create Operator");
		createOperatorAnchor.setHref("#");
		
		Anchor viewOperatorAnchor = new Anchor();
		viewOperatorAnchor.setText("View operators");
		viewOperatorAnchor.setHref("#");
		
		Anchor updateOperatorAnchor = new Anchor();
		updateOperatorAnchor.setText("Update Operator");
		updateOperatorAnchor.setHref("#");
		
		RootPanel.get("nav").add(createOperatorAnchor);
		RootPanel.get("nav").add(viewOperatorAnchor);
		RootPanel.get("nav").add(updateOperatorAnchor);
	};

}
