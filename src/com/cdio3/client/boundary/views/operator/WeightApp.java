package com.cdio3.client.boundary.views.operator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.event.shared.UmbrellaException;

public class WeightApp extends Composite implements HasText {

	private static WeightAppUiBinder uiBinder = GWT.create(WeightAppUiBinder.class);

	interface WeightAppUiBinder extends UiBinder<Widget, WeightApp> {
	}

	public WeightApp() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField TextBox nettoTextBox;
	@UiField TextBox tareTextBox;
	@UiField Button submitButton;
	
	@UiHandler("submitButton")
	void handleClick(ClickEvent event){
		try{
			Window.alert("" + (Double.parseDouble(nettoTextBox.getText()) + Double.parseDouble(tareTextBox.getText())));
		} catch(NumberFormatException | UmbrellaException error){
			Window.alert("Der skal indtastes et tal i begge bokse (Der skal benyttes \".\" som decimal tegn)");
		}
		
	}
	
	
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

}
