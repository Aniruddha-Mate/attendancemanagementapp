package com.capgemini.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation Failed",ex.getBindingResult().toString());
		  return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
		}
	
	    @ExceptionHandler(AttendanceExistsException.class)
	    public ResponseEntity<ErrorDetails> AttendanceExistsException(AttendanceExistsException ex, WebRequest request) {
	         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
	         return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
	    }
	    
	    @ExceptionHandler(RecordNotFoundException.class)
	    public ResponseEntity<ErrorDetails> RecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
	         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
	         return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(AttendanceIdNotFoundException.class) 
		  public ResponseEntity<ErrorDetails> AttendanceIdNotFoundException(Exception ex, WebRequest request) { 
	         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false)); 
		     return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND); }
	    
	    @ExceptionHandler(CourseIdNotFoundException.class) 
		  public ResponseEntity<ErrorDetails> CourseIdNotFoundException(Exception ex, WebRequest request) { 
	         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false)); 
		     return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND); }


	 
	    @ExceptionHandler(Exception.class) 
		  public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) { 
	         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false)); 
		     return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR); }
	    
	    }
