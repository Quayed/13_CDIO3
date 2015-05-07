package com.cdio3.client.boundary;

import com.cdio3.client.boundary.admin.AdminView;
import com.cdio3.client.boundary.old.ContentView;
import com.cdio3.client.boundary.old.MenuView;
import com.cdio3.client.service.DataService;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

public class MainView {
	private ContentView content;
	private DataServiceAsync service;

	public interface ILoggedInEvent{
		void adminLoggedIn();
		void operatorLoggedIn();
	}
	
	public MainView(String url) {
		this.service = GWT.create(DataService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		RootPanel.get("login").add(new LoginView(this.service, new ILoggedInEvent(){
			@Override
			public void adminLoggedIn(){
				RootPanel.get("login").clear();
				adminView();
			}

			@Override
			public void operatorLoggedIn() {
				
			}
		}));
		
	}

	public void adminView(){
		AdminView adminView = new AdminView(RootPanel.get("content"), service);
		RootPanel.get("content").add(adminView);
	}
	
	
	
	// This method should be called on start up.
	public void showStartView() {
		MenuView menu = new MenuView(this);
		RootPanel.get("nav").add(menu);

		content = new ContentView(RootPanel.get("content"), service);	
		content.showViewOperator();
	}

	public void showCreateOperator() {
		content.showCreateOperator();
	}

	public void showViewOperator() {
		content.showViewOperator();
	}

}
