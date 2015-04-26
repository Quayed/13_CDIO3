package com.cdio3.client.data;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IMainDataAsync {
	void sayHello(String message, AsyncCallback<String> callback);
}
