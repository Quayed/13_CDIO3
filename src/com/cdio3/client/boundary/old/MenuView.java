package com.cdio3.client.boundary.old;

import com.cdio3.client.boundary.MainView;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MenuView extends Composite {
	private HorizontalPanel menuPanel;
	private final MainView mainView;

	public MenuView(MainView main) {
		menuPanel = new HorizontalPanel();
		initWidget(this.menuPanel);
		mainView = main;

		Anchor createOperatorAnchor = new Anchor();
		createOperatorAnchor.setText("Create Operator");
		createOperatorAnchor.setHref("#");
		createOperatorAnchor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainView.showCreateOperator();
			}
		});

		Anchor viewOperatorAnchor = new Anchor();
		viewOperatorAnchor.setText("View operators");
		viewOperatorAnchor.setHref("#");
		viewOperatorAnchor.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				mainView.showViewOperator();
			}
		});

		menuPanel.add(createOperatorAnchor);
		menuPanel.add(viewOperatorAnchor);
	}
}
