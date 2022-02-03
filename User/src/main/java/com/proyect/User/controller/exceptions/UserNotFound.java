package com.proyect.User.controller.exceptions;

public class UserNotFound extends RuntimeException{
	
	private static final long serialVersionUID = 5L;

	public UserNotFound () {
		super("FAILED OPERATION: User and password incorrect");
	}
	public UserNotFound (String name) {
		super("FAILED OPERATION: User and password incorrect "+ name);
	}
	public UserNotFound (int id) {
		super("FAILED OPERATION: User not found by: "+ id);
	}
	
	public UserNotFound (String message, String name) {
		super(message + " " + name);
	}
	
}
