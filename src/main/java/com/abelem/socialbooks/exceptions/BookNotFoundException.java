package com.abelem.socialbooks.exceptions;

public class BookNotFoundException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable causa;
	
	public BookNotFoundException(String message, Throwable causa) {
		super();
		this.message = message;
		this.causa = causa;
	}
	
	public BookNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
