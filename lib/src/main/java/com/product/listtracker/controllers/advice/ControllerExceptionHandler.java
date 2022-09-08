package com.product.listtracker.controllers.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.listtracker.exceptions.AccountAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityException(DataIntegrityViolationException e) {
		return new ResponseEntity<>("Data integrity violation! Some required property may be missing", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<String> dataIntegrityException(AccountAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuilder sb = new StringBuilder();
		e.getAllErrors().stream().forEach(exception -> {
			sb.append(exception.getDefaultMessage() + "\n");
		});
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
	}
	
}