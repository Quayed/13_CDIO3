package com.cdio3.client.boundary.views.admin;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.FieldVerifier;
import com.cdio3.shared.OperatorDTO;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CreateWidget extends Composite {

	interface CreateUiBinder extends UiBinder<Widget, CreateWidget> {}

	private static CreateUiBinder uiBinder = GWT.create(CreateUiBinder.class);
	
	private DataServiceAsync service;
		
	@UiField TextBox oprNameTextBox;
	@UiField TextBox iniTextBox;
	@UiField TextBox cprTextBox;
	@UiField Button submitCreateOperator;
		
	private boolean validName;
	private boolean validCPR;
	private boolean validIni;
	
	public CreateWidget(DataServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		this.service = service;
	}
	
	@UiHandler("submitCreateOperator")
	void clickSubmit(ClickEvent e) {
		OperatorDTO newOperator = new OperatorDTO(0, oprNameTextBox.getText(), iniTextBox.getText(), cprTextBox.getText(), "");
		service.createOperator(newOperator, new AsyncCallback<OperatorDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(OperatorDTO newOperator) {
				Window.alert("Operatør oprettet med ID: " + newOperator.getOprID() + " og password: " + newOperator.getPassword());
				oprNameTextBox.setText("");
				cprTextBox.setText("");
				iniTextBox.setText("");

			}

		});
	}
	
	@UiHandler("oprNameTextBox")
	void keyUpName(KeyUpEvent e) {
		if(FieldVerifier.isValidName(oprNameTextBox.getText())){
			oprNameTextBox.removeStyleName("invalidEntry");
			validName = true;
		} else{
			oprNameTextBox.setStyleName("invalidEntry");
			validName = false;
		}
		checkForm();
	}

	@UiHandler("iniTextBox")
	void keyUpIni(KeyUpEvent e) {
		if(FieldVerifier.isValidInitials(iniTextBox.getText())){
			iniTextBox.removeStyleName("invalidEntry");
			validIni = true;
		} else{
			iniTextBox.setStyleName("invalidEntry");
			validIni = false;
		}
		checkForm();
	}
	
	@UiHandler("cprTextBox")
	void keyUpCpr(KeyUpEvent e) {
		if(FieldVerifier.isValidCPR(cprTextBox.getText())){
			cprTextBox.removeStyleName("invalidEntry");
			validCPR = true;
		} else{
			cprTextBox.setStyleName("invalidEntry");
			validCPR = false;
		}
		checkForm();
	}

	private void checkForm(){
		if (validName && validCPR && validIni){
			submitCreateOperator.setEnabled(true);
		} else{
			submitCreateOperator.setEnabled(false);
		}
	}
	
}