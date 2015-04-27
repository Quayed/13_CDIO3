package com.cdio3.client.boundary;

import com.cdio3.client.data.DataConnection;
import com.cdio3.client.data.DataConnectionAsync;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateView extends Composite {
	VerticalPanel view;
	DataConnectionAsync serverConn;
	ServiceDefTarget target;
	public CreateView(DataConnectionAsync service){
		
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
		TextBox oprNameTextBox = new TextBox();
		oprNameTextBox.setWidth(textBoxWidth);
		table.setWidget(0, 0, oprNameLabel);
		table.setWidget(0, 1, oprNameTextBox);
	
		// Insert label and textbox for Ini
		Label iniLabel = new Label("Ini:");
		iniLabel.setWidth(labelWidth);
		TextBox iniTextBox = new TextBox();
		iniTextBox.setWidth(textBoxWidth);
		table.setWidget(1, 0, iniLabel);
		table.setWidget(1, 1, iniTextBox);
	
		// Insert label and textbox for password
		Label cprLabel = new Label("CPR-number:");
		cprLabel.setWidth(labelWidth);
		TextBox cprTextBox = new TextBox();
		cprTextBox.setWidth(textBoxWidth);
		table.setWidget(2, 0, cprLabel);
		table.setWidget(2, 1, cprTextBox);
		
		// create the submit button
		Button submitCreateOperator = new Button("Create Operator");
		submitCreateOperator.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				serverConn.sayHello("mathias", new DefaultCallback());
			}
		});
	
		table.setWidget(3, 0, submitCreateOperator);
		view.add(table);
	}
	
	private class DefaultCallback implements AsyncCallback<String>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("FAIL");			
		}

		@Override
		public void onSuccess(String result) {
			Window.alert("Hej");
		}
		
	}
}
