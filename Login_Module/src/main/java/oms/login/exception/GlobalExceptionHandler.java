package com.oms.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundExcception.class)
	public ResponseEntity<MyErrorDetails> myExcpHandler(UserNotFoundExcception e, WebRequest wr){
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<MyErrorDetails> myExcpHandler(WrongPasswordException e, WebRequest wr){
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(FailedToSaveUserException.class)
	public ResponseEntity<MyErrorDetails> myExcpHandler(FailedToSaveUserException e, WebRequest wr){
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(Exception e, WebRequest wr) {
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<MyErrorDetails> myExpHandler(BadCredentialsException e, WebRequest wr) {
		MyErrorDetails med = new MyErrorDetails("Credentilas Invalid!!", wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<MyErrorDetails> myNullPointerExcpHandler(NullPointerException e, WebRequest wr) {
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVNExceptionHandler(MethodArgumentNotValidException me) {
		System.out.println("inside MethodArgumentNotValidException method...");
		MyErrorDetails err = new MyErrorDetails(me.getBindingResult().getFieldError().getDefaultMessage(), "Validation Error", LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myExcpHandler(NoHandlerFoundException e, WebRequest wr) {
		MyErrorDetails med = new MyErrorDetails(e.getMessage(), wr.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
	}
	
}
