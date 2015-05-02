package com.cdio3.client.service;

import java.util.List;

import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	void sayHello(String message, AsyncCallback<String> callback);

	void createOperator(OperatorDTO operator, AsyncCallback<OperatorDTO> callback);

	void getAllOperators(AsyncCallback<List<OperatorDTO>> callback);

	void updateOperator(OperatorDTO operator, AsyncCallback<Void> callback);
	
	void deleteOperator(int id, AsyncCallback<Void> callback);
}
