package com.abelem.socialbooks.exceptions;

public class AuthorExistsException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable causa;
	
	public AuthorExistsException(String message, Throwable causa) {
		super();
		this.message = message;
		this.causa = causa;
	}
	
	public AuthorExistsException(String message) {
		super();
		this.message = message;
	}
	
}
