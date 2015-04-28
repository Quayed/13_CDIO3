package com.cdio3.client.boundary;

import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.ui.RootPanel;

public class ContentView{
	private RootPanel content;
	private DataServiceAsync service;
	public ContentView(RootPanel content, DataServiceAsync service){
		this.content = content;
		this.service = service;
	}
	
	public void showCreateOperator(){
		// Insert the header
		HeadingElement createOperatorHeader = Document.get().createHElement(3);
		createOperatorHeader.setInnerHTML("Create Operator");
		content.getElement().appendChild(createOperatorHeader);
		content.add(new CreateView(this.service));
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
