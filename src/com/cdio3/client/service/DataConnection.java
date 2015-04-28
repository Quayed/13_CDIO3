package com.cdio3.client.service;

import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataConnection")
public interface DataConnection extends RemoteService{
	String sayHello(String message);
	OperatorDTO createOperator(OperatorDTO operator);
}
