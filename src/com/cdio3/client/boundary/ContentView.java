package com.cdio3.client.boundary;

import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class ContentView{
	private RootPanel content;
	private DataServiceAsync service;
	public ContentView(RootPanel content, DataServiceAsync service){
		this.content = content;
		this.service = service;
	}
	
	public void showCreateOperator(){
		Label createOperatorHeader = new Label("Create Operator:");
		createOperatorHeader.setStylePrimaryName("h3");
		content.add(createOperatorHeader);
		content.add(new CreateView(this.service));
	}
	
	public void showViewOperator(){
		Label viewOperatorHeader = new Label("View Operators:");
		viewOperatorHeader.setStylePrimaryName("h3");
		content.add(viewOperatorHeader);
		content.add(new ViewView(this.service));
	}
	
	public void showUpdateOperator(){
		Label updateOperatorHeader = new Label("Update Operators:");
		updateOperatorHeader.setStylePrimaryName("h3");
		content.add(updateOperatorHeader);
		content.add(new UpdateView(this.service));
	}
	
	public void showDeleteOperator(){
		Label deleteOperatorHeader = new Label("Delete Operators:");
		deleteOperatorHeader.setStylePrimaryName("h3");
		content.add(deleteOperatorHeader);
		content.add(new DeleteView(this.service));
	}
	
	public void clearContent(){
		content.clear();
	}

	public void showStartView() {
		
	}
}
