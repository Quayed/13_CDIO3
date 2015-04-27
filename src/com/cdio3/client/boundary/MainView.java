package com.cdio3.client.boundary;

import com.cdio3.client.data.DataConnection;
import com.cdio3.client.data.DataConnectionAsync;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class MainView {
	private ContentView content;
	private DataConnectionAsync service;
	
	public MainView(String url){
		this.service = GWT.create(DataConnection.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		MenuView menu = new MenuView(this);
		RootPanel.get("nav").add(menu);
		
		content = new ContentView(RootPanel.get("content"), service);
		showStartView();
	}
	
	// This method should be called on start up.
	public void showStartView(){
		content.clearContent();
		content.showStartView();
	}

	public void showCreateOperator() {
		// TODO Auto-generated method stub
		// this should show the create operator panel inside the content view	
		content.clearContent();
		content.showCreateOperator();
	}

	public void showViewOperator() {
		// TODO Auto-generated method stub
		content.clearContent();
		content.showViewOperator();
		
	}

	public void showUpdateOperator() {
		// TODO Auto-generated method stub
		
	}
	
}
