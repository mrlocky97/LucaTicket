package com.proyect.Event.controller.exceptions;

public class EventAlreadyExists extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EventAlreadyExists() {
		super("FAILED OPERATION: ITEM ALREADY EXISTS");
	}
	public EventAlreadyExists (String name) {
		super("FAILED OPERATION: ITEM ALTEADY EXISTS "+ name);
	}
}
