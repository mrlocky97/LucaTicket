package com.proyect.Event.controller.exceptions;

public class EventAlreadyExists extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EventAlreadyExists() {
		super("FAILED OPERATION: Item already exists");
	}
	public EventAlreadyExists (String name) {
		super("FAILED OPERATION: Item already exists --name: "+ name);
	}
}
