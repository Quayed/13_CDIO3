package com.cdio3.client.boundary;

import java.util.List;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.Label;

public class DeleteView extends Composite{
	private FlexTable table;
	public DeleteView(DataServiceAsync service){
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
				DeleteView.this.table.setWidget(i+1, 0, new Label("" + operators.get(i).getOprID()));
				DeleteView.this.table.setWidget(i+1, 1, new Label(operators.get(i).getOprName()));
				DeleteView.this.table.setWidget(i+1, 2, new Label(operators.get(i).getIni()));
				DeleteView.this.table.setWidget(i+1, 3, new Label(operators.get(i).getCpr()));
				DeleteView.this.table.setWidget(i+1, 4, new Label(operators.get(i).getPassword()));
				Anchor deleteAnchor = new Anchor("Delete");
				
			}
		}
		
	}
	
	
}
