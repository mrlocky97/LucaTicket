package com.proyect.Event.controller.exceptions;


public class EventNotFound extends RuntimeException{

	private static final long serialVersionUID = 5L;

	public EventNotFound () {
		super("FAILED OPERATION: Item DOES NOT exist");
	}
	public EventNotFound (String name) {
		super("FAILED OPERATION: Item DOES NOT exit --code number: "+ name);
	}
}
