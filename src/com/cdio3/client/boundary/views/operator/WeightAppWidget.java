package com.cdio3.client.boundary.views.operator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.cdio3.client.service.DataServiceAsync;

public class WeightAppWidget extends Composite implements HasText {

	private static WeightAppWidgetUiBinder uiBinder = GWT.create(WeightAppWidgetUiBinder.class);

	interface WeightAppWidgetUiBinder extends UiBinder<Widget, WeightAppWidget> {}
	private DataServiceAsync service;
	public WeightAppWidget(DataServiceAsync service) {
		this.service = service;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField TextBox bruttoTextBox;
	@UiField TextBox tareTextBox;
	@UiField Button submitButton;
	
	@UiHandler("submitButton")
	
	void handleClick(ClickEvent event){
		double brutto;
		double tare;
		try{
			brutto = Double.parseDouble(bruttoTextBox.getText());
			tare = Double.parseDouble(tareTextBox.getText());
			service.calculateNetto(brutto, tare, new AsyncCallback<Double>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Server error");
				}

				@Override
				public void onSuccess(Double result) {
					Window.alert(result.toString());
				}
				
			});
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
