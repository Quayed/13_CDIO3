package com.cdio3.client.boundary.old;

import com.cdio3.client.boundary.admin.CreateView;
import com.cdio3.client.boundary.admin.ViewView;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class ContentView {
	private RootPanel content;
	private DataServiceAsync service;

	public ContentView(RootPanel content, DataServiceAsync service) {
		this.content = content;
		this.service = service;
	}

	public void showCreateOperator() {
		content.clear();
		Label createOperatorHeader = new Label("Create Operator:");
		createOperatorHeader.setStylePrimaryName("h3");
		content.add(createOperatorHeader);
		content.add(new CreateView(this.service));
	}

	public void showViewOperator() {
		content.clear();
		Label viewOperatorHeader = new Label("View Operators:");
		viewOperatorHeader.setStylePrimaryName("h3");
		content.add(viewOperatorHeader);
		content.add(new ViewView(this.service));
	}
	
}
