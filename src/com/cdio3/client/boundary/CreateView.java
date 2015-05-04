package com.cdio3.client.boundary;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.DALException;
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

	public CreateView(DataServiceAsync service) {

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
		Button submitCreateOperator = new Button("Create Operator");
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

		table.setWidget(3, 0, submitCreateOperator);
		view.add(table);
	}

}