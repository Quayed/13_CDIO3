package com.cdio3.client.boundary.views.admin;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.DALException;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.cdio3.shared.FieldVerifier;

public class CreateWidget extends Composite {
	private VerticalPanel view;
	private DataServiceAsync serverConn;
	private TextBox oprNameTextBox;
	private TextBox iniTextBox;
	private TextBox cprTextBox;
	private boolean validName;
	private boolean validCPR;
	private boolean validIni;
	private Button submitCreateOperator;
	
	public CreateWidget(DataServiceAsync service) {

		this.serverConn = service;

		// create the verticalPanel to hold this view.
		view = new VerticalPanel();
		initWidget(this.view);

		// Create FlexTable to hold the labels and text box's
		FlexTable table = new FlexTable();
		table.setStyleName("create-operator");
		
		// Insert label and textbox for Opr_name
		Label oprNameLabel = new Label("Operator name:");
		oprNameTextBox = new TextBox();
		table.setWidget(0, 0, oprNameLabel);
		table.setWidget(0, 1, oprNameTextBox);

		// Insert label and textbox for Ini
		Label iniLabel = new Label("Ini:");
		iniTextBox = new TextBox();
		table.setWidget(1, 0, iniLabel);
		table.setWidget(1, 1, iniTextBox);

		// Insert label and textbox for cpr
		Label cprLabel = new Label("CPR-number:");
		cprTextBox = new TextBox();
		table.setWidget(2, 0, cprLabel);
		table.setWidget(2, 1, cprTextBox);

		// create the submit button
		submitCreateOperator = new Button("Create Operator");
		submitCreateOperator.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				OperatorDTO newOperator = new OperatorDTO(0, oprNameTextBox.getText(), iniTextBox.getText(), cprTextBox.getText(), "");
				serverConn.createOperator(newOperator, new AsyncCallback<OperatorDTO>() {

					@Override
					public void onFailure(Throwable caught) {
						
				
						
						if (caught instanceof DALException) {
							Window.alert("Kunne ikke forbinde til databasen.");
						}
						
						else {
							Window.alert("I give up");
						}
						

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
		});

	/*	oprNameTextBox.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(FieldVerifier.isValidName(oprNameTextBox.getText())){
					oprNameTextBox.removeStyleName("gwt-TextBox-invalidEntry");
					validName = true;
				} else{
					oprNameTextBox.setStyleName("gwt-TextBox-invalidEntry");
					validName = false;
				}
				checkForm();
			}
		});
		
		
		iniTextBox.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(FieldVerifier.isValidInitials(iniTextBox.getText())){
					iniTextBox.removeStyleName("gwt-TextBox-invalidEntry");
					validIni = true;
				} else{
					iniTextBox.setStyleName("gwt-TextBox-invalidEntry");
					validIni = false;
				}
				checkForm();
			}
		});
		
		cprTextBox.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(FieldVerifier.isValidCPR(cprTextBox.getText())){
					cprTextBox.removeStyleName("gwt-TextBox-invalidEntry");
					validName = true;
				} else{
					cprTextBox.setStyleName("gwt-TextBox-invalidEntry");
					validName = false;
				}
				checkForm();
			}
		});*/
		
		table.setWidget(3, 0, submitCreateOperator);
		view.add(table);
	}
	
	private void checkForm(){
		if (validName && validCPR && validIni){
			submitCreateOperator.setEnabled(true);
		} else{
			submitCreateOperator.setEnabled(false);
		}
	}
}