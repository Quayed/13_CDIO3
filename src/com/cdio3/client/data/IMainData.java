package com.cdio3.client.data;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("someStuff")
public interface IMainData extends RemoteService{
	String sayHello(String message);
}
