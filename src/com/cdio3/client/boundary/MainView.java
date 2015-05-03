package com.cdio3.client.boundary;

import com.cdio3.client.service.DataService;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class MainView {
	private ContentView content;
	private DataServiceAsync service;

	public interface ILoggedInEvent{
		void loggedIn();
	}
	
	public MainView(String url) {
		this.service = GWT.create(DataService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		RootPanel.get("login").add(new LoginView(this.service, new ILoggedInEvent(){
			@Override
			public void loggedIn(){
				RootPanel.get("login").clear();
				showStartView();
			}
		}));
		
	}

	// This method should be called on start up.
	public void showStartView() {
		MenuView menu = new MenuView(this);
		RootPanel.get("nav").add(menu);

		content = new ContentView(RootPanel.get("content"), service);	
		content.clearContent();
		content.showStartView();
	}

	public void showCreateOperator() {
		content.clearContent();
		content.showCreateOperator();
	}

	public void showViewOperator() {
		content.clearContent();
		content.showViewOperator();
	}

	public void showUpdateOperator() {
		content.clearContent();
		content.showUpdateOperator();
	}

	public void showDeleteOperator() {
		content.clearContent();
		content.showDeleteOperator();
	}

}
