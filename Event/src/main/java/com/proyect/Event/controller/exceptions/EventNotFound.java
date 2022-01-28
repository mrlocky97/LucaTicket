package com.proyect.Event.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EventNotFound () {
		super("FAILED OPERATION: Item DOES NOT exist");
	}
	public EventNotFound (String code) {
		super("FAILED OPERATION: Item DOES NOT exit --code number: "+ code);
	}
}
