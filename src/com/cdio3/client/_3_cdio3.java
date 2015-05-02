package com.cdio3.client;

import com.cdio3.client.boundary.MainView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _3_cdio3 implements EntryPoint {
	/**
	 * This is the entry point method.
	 */

	public void onModuleLoad() {
		new MainView(GWT.getModuleBaseURL() + "dataConnection");
	}	
}
