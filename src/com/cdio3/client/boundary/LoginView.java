package com.cdio3.client.boundary;

import com.cdio3.client.boundary.MainView.ILoggedInEvent;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginView extends Composite {

	interface LoginUiBinder extends UiBinder<Widget, LoginView> {}

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

	private DataServiceAsync service;
	private ILoggedInEvent loggedInEvent;
	
	
	@UiField
	Button loginButton;
	
	@UiField
	TextBox userNameField;
	
	@UiField
	PasswordTextBox passwordField;
	

	public LoginView(DataServiceAsync service, ILoggedInEvent loggedInEvent) {
		this.service = service;
		this.loggedInEvent = loggedInEvent;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("loginButton")
	void handleClick(ClickEvent e) {
		service.login(userNameField.getValue(), userNameField.getValue(), new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Something went wrong");
			}
			
			@Override
			public void onSuccess(Integer result) {
				if(result == 1){
					Window.alert("Admin login ok");
					//Do admin login screen
				} else
					
				if(result == 2){
					Window.alert("Operatør login ok");
					loggedInEvent.loggedIn();
				}
				else{
					Window.alert("Forkerte loginoplysninger");
				}
			}

			

		});
	}

}
