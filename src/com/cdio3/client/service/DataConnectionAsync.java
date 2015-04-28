package com.cdio3.client.service;

import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataConnectionAsync {
	void sayHello(String message, AsyncCallback<String> callback);
	void createOperator(OperatorDTO operator, AsyncCallback<OperatorDTO> callback);
}
