package com.cdio3.client;

import com.cdio3.client.boundary.MainView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class _3_cdio3 implements EntryPoint {

	public void onModuleLoad() {
		new MainView(GWT.getModuleBaseURL() + "dataConnection");
	}
	
}