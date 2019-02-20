package com.todo.rest.user.userservice.exception;

public class UserNotExistException extends Exception {

	private static final long serialVersionUID = 6236367282014212775L;

	public UserNotExistException() {
		super();
	}
	
	public UserNotExistException(String msg) {
		super(msg);
	}
}
