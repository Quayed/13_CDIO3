package com.cdio3.client.boundary.admin;

import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminView extends Composite {
	
	interface AdminUiBinder extends UiBinder<Widget, AdminView> {
	}

	private static AdminUiBinder uiBinder = GWT.create(AdminUiBinder.class);
	
	@UiField
	Label headerLabel;
	
	@UiField
	MenuBar navbar;
	
	@UiField
	FlowPanel contentPanel;
	
	private RootPanel content;
	private DataServiceAsync service;

	public AdminView(RootPanel content, DataServiceAsync service) {
		this.content = content;
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
		headerLabel.setStylePrimaryName("h3");

		operatorsView();

	    navbar.addItem("Operators", new ScheduledCommand() {
			@Override
			public void execute() {
				operatorsView();
			}
		});
	    navbar.addItem("Create Operator", new ScheduledCommand() {
			@Override
			public void execute() {
				createView();
			}
		});
	    
	}
	
	private void operatorsView() {
		headerLabel.setText("View Operators");
		contentPanel.clear();
		contentPanel.add(new ViewView(this.service));
	}
	
	public void createView(){
		headerLabel.setText("Create Operator");
		contentPanel.clear();
		contentPanel.add(new CreateView(this.service));		
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
