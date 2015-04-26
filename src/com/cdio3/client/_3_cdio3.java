package com.cdio3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _3_cdio3 implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	private String currentState = "startUP";
	private RootPanel contentPanel;
	public void onModuleLoad() {
		contentPanel = RootPanel.get("content");
		
		// Create operator
		Anchor createOperatorAnchor = new Anchor();
		createOperatorAnchor.setText("Create Operator");
		createOperatorAnchor.setHref("#");
		
		createOperatorAnchor.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(!currentState.equals("create")){
					clearContent();
					
					//define width of labels and textBox
					String labelWidth = "200px";
					String textBoxWidth = "200px";
					
					// Insert the header
					HeadingElement createOperatorHeader = Document.get().createHElement(3);
					createOperatorHeader.setInnerHTML("Create Operator");
					contentPanel.getElement().appendChild(createOperatorHeader);
					
					//Create the horizontal panel
					//HorizontalPanel namePanel = new HorizontalPanel();
					//HorizontalPanel iniPanel = new HorizontalPanel();
					//HorizontalPanel cprPanel = new HorizontalPanel();
					FlexTable table = new FlexTable();
					
					// Insert label and textbox for Opr_name	
					Label oprNameLabel = new Label("Operator name:");
					oprNameLabel.setWidth(labelWidth);
					TextBox oprNameTextBox = new TextBox();
					oprNameTextBox.setWidth(textBoxWidth);
					table.setWidget(0, 0, oprNameLabel);
					table.setWidget(0, 1, oprNameTextBox);
					//namePanel.add(oprNameLabel);
					//namePanel.add(oprNameTextBox);
				
					// Insert label and textbox for Ini
					Label iniLabel = new Label("Ini:");
					iniLabel.setWidth(labelWidth);
					TextBox iniTextBox = new TextBox();
					iniTextBox.setWidth(textBoxWidth);
					table.setWidget(1, 0, iniLabel);
					table.setWidget(1, 1, iniTextBox);
					//iniPanel.add(iniLabel);
					//iniPanel.add(iniTextBox);
				
					// Insert label and textbox for password
					Label cprLabel = new Label("CPR-number:");
					cprLabel.setWidth(labelWidth);
					TextBox cprTextBox = new TextBox();
					cprTextBox.setWidth(textBoxWidth);
					table.setWidget(2, 0, cprLabel);
					table.setWidget(2, 1, cprTextBox);
					//cprPanel.add(cprLabel);
					//cprPanel.add(cprTextBox);
					
					// create the submit button
					Button submitCreateOperator = new Button("Create Operator");
					submitCreateOperator.addClickHandler(new ClickHandler(){
						public void onClick(ClickEvent event){
							Window.alert("subimt time!!");
						}
					});
					
					table.setWidget(3, 0, submitCreateOperator);
					// Add all of the horizontal panels to the rootPanel
					//contentPanel.add(namePanel);
					//contentPanel.add(iniPanel);
					//contentPanel.add(cprPanel);
					//contentPanel.add(submitCreateOperator);
					contentPanel.add(table);
					
				}
				
				// show the fields needed to create an operator.
			}
		});
		
		Anchor viewOperatorAnchor = new Anchor();
		viewOperatorAnchor.setText("View operators");
		viewOperatorAnchor.setHref("#");
		
		Anchor updateOperatorAnchor = new Anchor();
		updateOperatorAnchor.setText("Update Operator");
		updateOperatorAnchor.setHref("#");
		
		RootPanel.get("nav").add(createOperatorAnchor);
		RootPanel.get("nav").add(viewOperatorAnchor);
		RootPanel.get("nav").add(updateOperatorAnchor);
		
		
	};
	
	private void clearContent(){
		// This function should clear the content div from everything inside of it.
		contentPanel.clear();
	}
	
}
