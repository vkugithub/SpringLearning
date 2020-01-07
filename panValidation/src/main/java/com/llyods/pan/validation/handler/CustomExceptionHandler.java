package com.llyods.pan.validation.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.llyods.pan.validation.exception.ExceptionResponse;
import com.llyods.pan.validation.exception.InvalidPanNumberException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

	

	@ExceptionHandler(InvalidPanNumberException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(InvalidPanNumberException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				"FAILURE");
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed : "+ex.getBindingResult(),
				"FAILURE");
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}
