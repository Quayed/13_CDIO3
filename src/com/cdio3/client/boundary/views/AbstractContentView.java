package com.cdio3.client.boundary.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractContentView extends Composite {
	
	interface SuperUiBinder extends UiBinder<Widget, AbstractContentView> {}

	private static SuperUiBinder uiBinder = GWT.create(SuperUiBinder.class);
	
	@UiField MenuBar navbar;
	@UiField Label headerLabel;
	@UiField FlowPanel contentPanel;
	
	public AbstractContentView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.headerLabel.setStylePrimaryName("h3");
		this.contentPanel.setStylePrimaryName("content");    
	}

	public void addNavItem(String text, ScheduledCommand cmd){
		navbar.addItem(text, cmd);
	}
	
	public void setHeaderLabel(String text) {
		this.headerLabel.setText(text);
	}

	public void setView(Widget w){
		this.contentPanel.clear();
		this.contentPanel.add(w);
	}

}
