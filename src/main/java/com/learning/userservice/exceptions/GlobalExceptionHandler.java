package com.learning.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.userservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
		String message = exception.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).success(true).build();
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}

}
