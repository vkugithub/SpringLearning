package com.llyods.pan.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPanNumberException extends RuntimeException {

	public InvalidPanNumberException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
