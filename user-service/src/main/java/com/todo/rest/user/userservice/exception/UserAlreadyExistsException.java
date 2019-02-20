package com.todo.rest.user.userservice.exception;

public class UserAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = -7517933656541516275L;

	public UserAlreadyExistsException() {
		super();
	}
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}

}
