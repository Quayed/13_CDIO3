package com.cdio3.client.boundary;

import com.google.gwt.user.client.ui.RootPanel;

public class MainView {
	private ContentView content;
	
	public MainView(){
		MenuView menu = new MenuView(this);
		RootPanel.get("nav").add(menu);
		
		content = new ContentView(RootPanel.get("content"));
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
