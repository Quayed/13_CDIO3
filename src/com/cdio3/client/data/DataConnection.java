package com.cdio3.client.data;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataConnection")
public interface DataConnection extends RemoteService{
	String sayHello(String message);
}
