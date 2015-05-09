package com.cdio3.client.boundary.views.operator;

import com.cdio3.client.boundary.views.AbstractContentView;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public class OperatorView extends AbstractContentView {

	private DataServiceAsync service;

	public OperatorView(DataServiceAsync service) {
		this.service = service;
		
		weightAppView();
		
	    addNavItem("Weight App", new ScheduledCommand() {
			@Override
			public void execute() {
				weightAppView();
			}
		});
	    
	    addNavItem("Change password", new ScheduledCommand() {
			@Override
			public void execute() {
				changePasswordView();
			}
		});

	}
	
	private void weightAppView() {
		setHeaderLabel("Weight App");
		setView(new WeightApp());
	}
	
	private void changePasswordView() {
		setHeaderLabel("Change password");
		setView(new ChangePasswordWidget(service));
	}

}
