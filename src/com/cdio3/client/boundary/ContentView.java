package com.cdio3.client.boundary;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ContentView{
	private RootPanel content;
	
	public ContentView(RootPanel content){
		this.content = content;
	}
	
	public void showCreateOperator(){
		//define width of labels and textBox
		String labelWidth = "200px";
		String textBoxWidth = "200px";
		
		// Insert the header
		HeadingElement createOperatorHeader = Document.get().createHElement(3);
		createOperatorHeader.setInnerHTML("Create Operator");
		content.getElement().appendChild(createOperatorHeader);
		
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
				Window.alert("subimt time!!");
			}
		});
		
		table.setWidget(3, 0, submitCreateOperator);
		content.add(table);
	
	}
	
	public void showViewOperator(){
		
	}
	
	public void showUpdateOperator(){
		
	}
	
	public void clearContent(){
		content.clear();
	}

	public void showStartView() {
		
	}
}
