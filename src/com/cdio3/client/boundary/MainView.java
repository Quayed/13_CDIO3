package com.cdio3.client.boundary;

import com.google.gwt.user.client.ui.RootPanel;

public class MainView {
	private ContentView content;
	
	public MainView(){
		MenuView menu = new MenuView(this);
		RootPanel.get("nav").add(menu);
		
		content = new ContentView();
		
	}

	public void showCreateOperator() {
		// TODO Auto-generated method stub
		// this should show the create operator panel inside the content view	
	}

	public void showViewOperator() {
		// TODO Auto-generated method stub
		
	}

	public void showUpdateOperator() {
		// TODO Auto-generated method stub
		
	}
	
}
