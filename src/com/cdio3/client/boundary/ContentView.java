package com.cdio3.client.boundary;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class ContentView{
	private RootPanel content;
	
	public void ContentView(RootPanel content){
		this.content = content;
	}
	
	public void showCreateOperator(){
		
	}
	
	public void showViewOperator(){
		
	}
	
	public void showUpdateOperator(){
		
	}
	
	public void clearContent(){
		content.clear();
	}
}
