package com.proyect.User.controller.exceptions;

public class UserAlreadyExists extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserAlreadyExists() {
		super("FAILED OPERATION: USER ALREADY EXISTS");
	}
	public UserAlreadyExists (String name) {
		super("FAILED OPERATION: USER ALTEADY EXISTS "+ name);
	}
}
