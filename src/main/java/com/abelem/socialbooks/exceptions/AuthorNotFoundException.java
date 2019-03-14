package com.abelem.socialbooks.exceptions;

public class AuthorNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable causa;
	
	public AuthorNotFoundException(String message, Throwable causa) {
		super();
		this.message = message;
		this.causa = causa;
	}
	
	public AuthorNotFoundException(String message) {
		super();
		this.message = message;
	}
}
