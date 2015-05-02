package com.cdio3.client.boundary;

import java.util.List;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class UpdateView extends Composite{
	private FlexTable table;
	private int editRow;
	private Button cancelButton;
	public UpdateView(DataServiceAsync service){
		table = new FlexTable();
		initWidget(table);
		String cellWidth = "150px";
		service.getAllOperators(new getOperatorsCallback());
		CellFormatter tableCellFormatter = table.getCellFormatter();
		tableCellFormatter.setWidth(0, 0, "50px");
		tableCellFormatter.setWidth(0, 1, cellWidth);
		tableCellFormatter.setWidth(0, 2, "50px");
		tableCellFormatter.setWidth(0, 3, cellWidth);
		tableCellFormatter.setWidth(0, 4, cellWidth);
		tableCellFormatter.setStyleName(0, 0, "tableHeader");
		tableCellFormatter.setStyleName(0, 1, "tableHeader");
		tableCellFormatter.setStyleName(0, 2, "tableHeader");
		tableCellFormatter.setStyleName(0, 3, "tableHeader");
		tableCellFormatter.setStyleName(0, 4, "tableHeader");
		
		table.setWidget(0, 0, new Label("ID:"));
		table.setWidget(0, 1, new Label("Name:"));
		table.setWidget(0, 2, new Label("Ini:"));
		table.setWidget(0, 3, new Label("CPR:"));
		table.setWidget(0, 4, new Label("Password:"));
		
		
		
	}
	
	private class getOperatorsCallback implements AsyncCallback<List<OperatorDTO>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Database error");
		}

		@Override
		public void onSuccess(List<OperatorDTO> operators) {
			for(int i = 0; i < operators.size(); i++){
				UpdateView.this.table.setWidget(i+1, 0, new Label("" + operators.get(i).getOprID()));
				UpdateView.this.table.setWidget(i+1, 1, new Label(operators.get(i).getOprName()));
				UpdateView.this.table.setWidget(i+1, 2, new Label(operators.get(i).getIni()));
				UpdateView.this.table.setWidget(i+1, 3, new Label(operators.get(i).getCpr()));
				UpdateView.this.table.setWidget(i+1, 4, new Label(operators.get(i).getPassword()));
				Anchor editAnchor = new Anchor("Edit");
				editAnchor.addClickHandler(new EditClickHandler());
				UpdateView.this.table.setWidget(i+1, 5, editAnchor);
				
			}
		}
		
	}
	
	private class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			if(cancelButton != null){
				cancelButton.fireEvent(new ClickEvent(){});
			}
			
			editRow = table.getCellForEvent(event).getRowIndex();
			
			for (int i = 0; i < 5; i++){
				TextBox editTextBox = new TextBox();
				Label oldLabel = (Label) table.getWidget(editRow, i);
				editTextBox.setText(oldLabel.getText());
				table.clearCell(editRow, i);
				table.setWidget(editRow, i, editTextBox);
			}
			Button submitButton = new Button("Submit");
			submitButton.addClickHandler(new SubmitClick());
			table.clearCell(editRow, 5);
			table.setWidget(editRow, 5, submitButton);
			
			
			cancelButton = new Button("Cancel");
			cancelButton.addClickHandler(new CancelClick());
			table.setWidget(editRow, 6, cancelButton);
			
		}
		
		private class SubmitClick implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
		private class CancelClick implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				for(int i = 0; i < 5; i++){
					Label newLabel = new Label();
					TextBox oldTextBox = (TextBox) table.getWidget(editRow, i);
					newLabel.setText(oldTextBox.getText());
					table.clearCell(editRow, i);
					table.setWidget(editRow, i, newLabel);
				}
				table.clearCell(editRow, 5);
				Anchor editAnchor = new Anchor("Edit");
				editAnchor.addClickHandler(new EditClickHandler());
				table.setWidget(editRow, 5, editAnchor);
				table.clearCell(editRow, 6);
				cancelButton = null;
			}
			
		}
		
	}
}
