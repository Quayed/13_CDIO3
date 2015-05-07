package com.cdio3.client.boundary.views.operator;

import com.cdio3.client.boundary.views.AbstractContentView;
import com.cdio3.client.service.DataServiceAsync;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public class OperatorView extends AbstractContentView {

	private DataServiceAsync service;

	public OperatorView(DataServiceAsync service) {
		this.service = service;
	    addNavItem("Change password", new ScheduledCommand() {
			@Override
			public void execute() {
				
			}
		});
	}

}
