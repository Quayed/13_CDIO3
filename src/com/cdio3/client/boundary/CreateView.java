package com.cdio3.client.boundary;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateView extends Composite {
	private VerticalPanel view;
	private DataServiceAsync serverConn;
	private TextBox oprNameTextBox;
	private TextBox iniTextBox;
	private TextBox cprTextBox;
	
	public CreateView(DataServiceAsync service){
		
		this.serverConn = service;
		//define width of labels and textBox
		String labelWidth = "200px";
		String textBoxWidth = "200px";
		
		// create the verticalPanel to hold this view.
		view = new VerticalPanel();
		initWidget(this.view);
		
		//Create FlexTable to hold the labels and text box's
		FlexTable table = new FlexTable();		
		
		// Insert label and textbox for Opr_name	
		Label oprNameLabel = new Label("Operator name:");
		oprNameLabel.setWidth(labelWidth);
		oprNameTextBox = new TextBox();
		oprNameTextBox.setWidth(textBoxWidth);
		table.setWidget(0, 0, oprNameLabel);
		table.setWidget(0, 1, oprNameTextBox);
	
		// Insert label and textbox for Ini
		Label iniLabel = new Label("Ini:");
		iniLabel.setWidth(labelWidth);
		iniTextBox = new TextBox();
		iniTextBox.setWidth(textBoxWidth);
		table.setWidget(1, 0, iniLabel);
		table.setWidget(1, 1, iniTextBox);
	
		// Insert label and textbox for cpr
		Label cprLabel = new Label("CPR-number:");
		cprLabel.setWidth(labelWidth);
		cprTextBox = new TextBox();
		cprTextBox.setWidth(textBoxWidth);
		table.setWidget(2, 0, cprLabel);
		table.setWidget(2, 1, cprTextBox);
		
		// create the submit button
		Button submitCreateOperator = new Button("Create Operator");
		submitCreateOperator.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				OperatorDTO newOperator = new OperatorDTO(0, oprNameTextBox.getText(), iniTextBox.getText(), cprTextBox.getText(), "");
				serverConn.createOperator(newOperator, new createOperatorCallback());
			}
		});
	
		table.setWidget(3, 0, submitCreateOperator);
		view.add(table);
	}
	
	private class createOperatorCallback implements AsyncCallback<OperatorDTO>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Some error happend");
			
		}

		@Override
		public void onSuccess(OperatorDTO newOperator) {
			Window.alert("Operatør oprettet - password er: " + newOperator.getOprID() + " og password er " + newOperator.getPassword());
			
		}
		
	}
}
