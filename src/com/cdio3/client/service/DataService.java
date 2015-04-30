package com.cdio3.client.service;

import java.util.List;

import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataConnection")
public interface DataService extends RemoteService{
	String sayHello(String message);
	OperatorDTO createOperator(OperatorDTO operator);
	List<OperatorDTO> getAllOperators();
}
