package com.cdio3.client.boundary.views.admin;

import java.util.List;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.FieldVerifier;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
public class OperatorsWidget extends Composite {
	
	interface OperatorsUiBinder extends UiBinder<Widget, OperatorsWidget> {
	}

	private static OperatorsUiBinder uiBinder = GWT.create(OperatorsUiBinder.class);
	
	private DataServiceAsync service;
	
	@UiField FlexTable table;
	
	private int editRow = -1;
	
	private boolean validName;
	private boolean validCPR;
	private boolean validIni;
	private boolean validPassword;
	
	public OperatorsWidget(DataServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		this.service = service;

		table.setStyleName("operators");
		service.getAllOperators(new getOperatorsCallback());

		table.setWidget(0, 0, new Label("ID:"));
		table.setWidget(0, 1, new Label("Name:"));
		table.setWidget(0, 2, new Label("Ini:"));
		table.setWidget(0, 3, new Label("CPR:"));
		table.setWidget(0, 4, new Label("Password:"));

	}

	private class getOperatorsCallback implements AsyncCallback<List<OperatorDTO>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Database error");
		}

		@Override
		public void onSuccess(List<OperatorDTO> operators) {
			for (int i = 0; i < operators.size(); i++) {
				table.setWidget(i + 1, 0, new Label("" + operators.get(i).getOprID()));
				table.setWidget(i + 1, 1, new Label(operators.get(i).getOprName()));
				table.setWidget(i + 1, 2, new Label(operators.get(i).getIni()));
				table.setWidget(i + 1, 3, new Label(operators.get(i).getCpr()));
				table.setWidget(i + 1, 4, new Label(operators.get(i).getPassword()));
				
				Anchor editAnchor = new Anchor("Edit");
				editAnchor.addClickHandler(new EditClickHandler());
				table.setWidget(i + 1, 5, editAnchor);
				
				Anchor deleteAnchor = new Anchor("Delete");
				deleteAnchor.addClickHandler(new deleteClickHandler());
				table.setWidget(i + 1, 6, deleteAnchor);
			}
		}

	}

	private Label oprID = new Label();
	@UiField TextBox oprName;
	@UiField TextBox oprIni;
	@UiField TextBox oprCPR;
	@UiField TextBox oprPassword;
	@UiField Button submitButton;
	@UiField Button cancelButton;
	
	private class EditClickHandler implements ClickHandler {

		private String getTableLabelText(int column){
			return ((Label) table.getWidget(editRow+1, column)).getText();
		}
		
		@Override
		public void onClick(ClickEvent event) {
			
			if (editRow > -1) {
				cancelButton.fireEvent(new ClickEvent() {});
			}

			editRow = table.getCellForEvent(event).getRowIndex();
			table.insertRow(editRow);
			
			table.getRowFormatter().setVisible(editRow+1, false);
			
			oprID.setText(getTableLabelText(0));
			table.setWidget(editRow, 0, oprID);
						
			oprName.setText(getTableLabelText(1));
			table.setWidget(editRow, 1, oprName);
			
			oprIni.setText(getTableLabelText(2));
			table.setWidget(editRow, 2, oprIni);
			
			oprCPR.setText(getTableLabelText(3));
			table.setWidget(editRow, 3, oprCPR);
			
			oprPassword.setText(getTableLabelText(4));
			table.setWidget(editRow, 4, oprPassword);

			table.setWidget(editRow, 5, submitButton);
			table.setWidget(editRow, 6, cancelButton);
						
			KeyUpEvent.fireNativeEvent(Document.get().createKeyUpEvent(false, false, false, false, 65), oprName);
			KeyUpEvent.fireNativeEvent(Document.get().createKeyUpEvent(false, false, false, false, 65), oprIni);
			KeyUpEvent.fireNativeEvent(Document.get().createKeyUpEvent(false, false, false, false, 65), oprCPR);
			KeyUpEvent.fireNativeEvent(Document.get().createKeyUpEvent(false, false, false, false, 65), oprPassword);

			submitButton.setEnabled(false);
		}
	}
	
	private class deleteClickHandler implements ClickHandler{
		private int deleteRow;

		@Override
		public void onClick(ClickEvent event) {
			deleteRow = table.getCellForEvent(event).getRowIndex();
			int operatorID = Integer.parseInt(((Label) table.getWidget(deleteRow, 0)).getText());
			if(Window.confirm("Are you sure to delete operator "+operatorID+"?")){
				service.deleteOperator(operatorID, new AsyncCallback<Void>(){
	
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Something went wrong");
					}
	
					@Override
					public void onSuccess(Void result) {
						table.removeRow(deleteRow);
						Window.alert("The Operator has been deleted");
					}
					
				});
			}
		}
		
	}	
	
	private void checkForm(){
		if(validName && validIni && validCPR && validPassword){
			submitButton.setEnabled(true);
		} else{
			submitButton.setEnabled(false);
		}
	}
	
	@UiHandler("oprName")
	void keyUpName(KeyUpEvent event) {
		if(FieldVerifier.isValidName(oprName.getText())){
			oprName.removeStyleName("invalidEntry");
			validName = true;
		} else{
			oprName.setStyleName("invalidEntry");
			validName = false;
		}
		checkForm();
	}
	
	@UiHandler("oprIni")
	void keyUpIni(KeyUpEvent event) {
		if(FieldVerifier.isValidInitials(oprIni.getText())){
			oprIni.removeStyleName("invalidEntry");
			validIni = true;
		} else{
			oprIni.setStyleName("invalidEntry");
			validIni = false;
		}
		checkForm();
	}
	
	@UiHandler("oprCPR")
	void keyUpCPR(KeyUpEvent event) {
		if(FieldVerifier.isValidCPR(oprCPR.getText())){
			oprCPR.removeStyleName("invalidEntry");
			validCPR = true;
		} else{
			oprCPR.setStyleName("invalidEntry");
			validCPR = false;
		}
		checkForm();
	}
	
	@UiHandler("oprPassword")
	void keyUpPassword(KeyUpEvent event) {
		if(FieldVerifier.isValidPassword(oprPassword.getText())){
			oprPassword.removeStyleName("invalidEntry");
			validPassword = true;
		} else{
			oprPassword.setStyleName("invalidEntry");
			validPassword = false;
		}
		checkForm();
	}
	
	@UiHandler("submitButton")
	void clickSubmit(ClickEvent event) {
		int oprID = Integer.parseInt(((Label) table.getWidget(editRow, 0)).getText());

		OperatorDTO operator = new OperatorDTO(oprID, oprName.getText(), oprIni.getText(), oprCPR.getText(), oprPassword.getText());
		service.updateOperator(operator, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Operatøren blev IKKE oprettet, der kunne ikke komme forbindelse til din database");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Operatøren er blevet opdateret");
				
				((Label) table.getWidget(editRow+1, 1)).setText(oprName.getText());
				((Label) table.getWidget(editRow+1, 2)).setText(oprIni.getText());
				((Label) table.getWidget(editRow+1, 3)).setText(oprCPR.getText());
				((Label) table.getWidget(editRow+1, 4)).setText(oprPassword.getText());
				
				cancelButton.fireEvent(new ClickEvent() {});
			}

		});
	}

	@UiHandler("cancelButton")
	void clickCancel(ClickEvent event) {
				
		table.getRowFormatter().setVisible(editRow+1, true);
		table.getRowFormatter().setVisible(editRow, false);
		table.removeRow(editRow);
		editRow = -1;
	}
	
}
