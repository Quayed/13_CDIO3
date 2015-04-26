package com.cdio3.client.boundary;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MenuView extends Composite{
	private HorizontalPanel menuPanel;
	
	public MenuView(MainView main){
		menuPanel = new HorizontalPanel();
		initWidget(this.menuPanel);
		
		
		Anchor createOperatorAnchor = new Anchor();
		createOperatorAnchor.setText("Create Operator");
		createOperatorAnchor.setHref("#");
		createOperatorAnchor.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				main.showCreateOperator();
			}
		});
		
		Anchor viewOperatorAnchor = new Anchor();
		viewOperatorAnchor.setText("View operators");
		viewOperatorAnchor.setHref("#");
		viewOperatorAnchor.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				main.showViewOperator();
			}
		});
		
		Anchor updateOperatorAnchor = new Anchor();
		updateOperatorAnchor.setText("Update Operator");
		updateOperatorAnchor.setHref("#");
		updateOperatorAnchor.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				main.showUpdateOperator();
			}
		});
		
		menuPanel.add(createOperatorAnchor);
		menuPanel.add(viewOperatorAnchor);
		menuPanel.add(updateOperatorAnchor);
	}
}
