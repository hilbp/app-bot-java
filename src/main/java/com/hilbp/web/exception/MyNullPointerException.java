package com.hilbp.web.exception;

public class MyNullPointerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyNullPointerException(String msg) {
		super(msg);
	}
}
