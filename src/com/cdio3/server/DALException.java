package com.cdio3.server;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	public DALException(String message) {
		super(message);
	}

	public DALException(Exception e) {
		super(e);
	}
}
