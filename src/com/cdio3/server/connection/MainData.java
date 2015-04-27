package com.cdio3.server.connection;

import com.cdio3.client.data.IMainData;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MainData extends RemoteServiceServlet implements IMainData{

	@Override
	public String sayHello(String message) {
		System.out.println(message);
		return message.toUpperCase();
	}

}
