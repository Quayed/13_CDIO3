package com.cdio3.client.boundary;

import java.util.List;

import com.cdio3.client.service.DataServiceAsync;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewView extends Composite{
	private VerticalPanel view;
	private FlexTable table;
	public ViewView(DataServiceAsync service){
		table = new FlexTable();
		initWidget(table);
		
		service.getAllOperators(new getOperatorsCallback());
		
		Label idLabel = new Label("ID:");
		Label nameLabel = new Label("Name;");
		Label iniLabel = new Label("Ini:");
		Label cprLabel = new Label("CPR:");
		Label passwordLabel = new Label("Password:");
		table.setWidget(0, 0, idLabel);
		table.setWidget(0, 1, nameLabel);
		table.setWidget(0, 2, iniLabel);
		table.setWidget(0, 3, cprLabel);
		table.setWidget(0, 4, passwordLabel);
		
		
		
	}
	
	private class getOperatorsCallback implements AsyncCallback<List<OperatorDTO>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Database error");
		}

		@Override
		public void onSuccess(List<OperatorDTO> operators) {
			for(int i = 0; i < operators.size(); i++){
				ViewView.this.table.setWidget(i+1, 0, new Label("" + operators.get(i).getOprID()));
				ViewView.this.table.setWidget(i+1, 1, new Label(operators.get(i).getOprName()));
				ViewView.this.table.setWidget(i+1, 2, new Label(operators.get(i).getIni()));
				ViewView.this.table.setWidget(i+1, 3, new Label(operators.get(i).getCpr()));
				ViewView.this.table.setWidget(i+1, 4, new Label(operators.get(i).getPassword()));
			}
		}
		
	}
}
