package com.cdio3.server.connection;

import com.cdio3.client.data.DataConnection;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class DataConnectionImpl extends RemoteServiceServlet implements DataConnection{

	@Override
	public String sayHello(String message) {
		System.out.println(message);
		return message.toUpperCase();
	}

}
