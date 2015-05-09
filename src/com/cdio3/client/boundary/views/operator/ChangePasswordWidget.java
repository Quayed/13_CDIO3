package com.cdio3.client.boundary.views.operator;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.DALException;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChangePasswordWidget extends Composite {

	interface ChangePasswordUiBinder extends UiBinder<Widget, ChangePasswordWidget> {}

	private static ChangePasswordUiBinder uiBinder = GWT.create(ChangePasswordUiBinder.class);
	
	private DataServiceAsync service;
	
	@UiField PasswordTextBox passwordOld;
	@UiField PasswordTextBox passwordNew;
	@UiField PasswordTextBox passwordNewRepeat;
	
	@UiField Button submitButton;
	
	public ChangePasswordWidget(DataServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		this.service = service;
		
	}
	
	@UiHandler("submitButton")
	void clickSubmit(ClickEvent e) {
		service.changePassword(passwordOld.getValue(), passwordNew.getValue(), passwordNewRepeat.getValue(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof DALException) {
					Window.alert("Fejl: "+caught.getMessage());
				}
				else {
					Window.alert("I give up");
				}
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Operatør password opdateret");
				passwordOld.setText("");
				passwordNew.setText("");
				passwordNewRepeat.setText("");				
			}

		});
	}
	
	@UiHandler("passwordOld")
	void keyUpName(KeyUpEvent e) {
		checkForm();
	}

	@UiHandler("passwordNew")
	void keyUpIni(KeyUpEvent e) {
		checkForm();
	}
	
	@UiHandler("passwordNewRepeat")
	void keyUpCpr(KeyUpEvent e) {
		checkForm();
	}

	private void checkForm(){
		if (true){
			submitButton.setEnabled(true);
		} else{
			submitButton.setEnabled(false);
		}
	}
}