package com.abelem.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abelem.socialbooks.domain.ErrorDetails;
import com.abelem.socialbooks.exceptions.AuthorExistsException;
import com.abelem.socialbooks.exceptions.AuthorNotFoundException;
import com.abelem.socialbooks.exceptions.BookNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleBookNotFoundException
		(BookNotFoundException e, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("Book not found");
		errorDetails.setMessageToDeveloper("http://erros.socialbooks.com/404");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(errorDetails);
	}
	
	
	@ExceptionHandler(AuthorExistsException.class)
	public ResponseEntity<ErrorDetails> handleAuthorExists
		(AuthorExistsException e, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(409l);
		errorDetails.setTitle("Author already exist");
		errorDetails.setMessageToDeveloper("http://erros.socialbooks.com/404");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpServletResponse.SC_CONFLICT).body(errorDetails);
	}
	
	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleAuthorNotFoundException
		(AuthorNotFoundException e, HttpServletRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(404l);
		errorDetails.setTitle("Author not found");
		errorDetails.setMessageToDeveloper("http://erros.socialbooks.com/404");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(errorDetails);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException
		(DataIntegrityViolationException e, HttpServletRequest request){
		
		e.printStackTrace();
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setStatus(400l);
		errorDetails.setTitle("Error on try to parser a date");
		errorDetails.setMessageToDeveloper("http://erros.socialbooks.com/400");
		errorDetails.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(errorDetails);
	}
}
