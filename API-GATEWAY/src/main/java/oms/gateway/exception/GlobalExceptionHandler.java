package com.oms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oms.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse>getRuntimeException(RuntimeException e){
		
		String msg = e.getMessage();
		
		ApiResponse response = ApiResponse.builder().msg(msg).status(HttpStatus.NOT_FOUND).sucess(false).build();
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
}
