package com.cdio3.client.boundary.views.admin;

import com.cdio3.client.boundary.views.AbstractContentView;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public class AdminView extends AbstractContentView {

	private DataServiceAsync service;

	public AdminView(DataServiceAsync service) {
		super();
		this.service = service;
		
		operatorsView();

		addNavItem("Operators", new ScheduledCommand() {
			@Override
			public void execute() {
				operatorsView();
			}
		});
	    addNavItem("Create Operator", new ScheduledCommand() {
			@Override
			public void execute() {
				createView();
			}
		});
	}
	
	private void operatorsView() {
		setHeaderLabel("View Operators");
		setView(new ViewView(service));
	}
	
	private void createView(){
		setHeaderLabel("Create Operator");
		setView(new CreateView(service));
	}
	
}
