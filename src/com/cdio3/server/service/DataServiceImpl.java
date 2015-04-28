package com.cdio3.server.service;

import com.cdio3.client.service.DataService;
import com.cdio3.shared.OperatorDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class DataServiceImpl extends RemoteServiceServlet implements DataService{

	@Override
	public String sayHello(String message) {
		System.out.println(message);
		return message.toUpperCase();
	}

	@Override
	public OperatorDTO createOperator(OperatorDTO operator) {
		// TODO Auto-generated method stub
		// this method should call the createOperator method inside of the OperatorDAO class
		return null;
	}

}