package com.product.listtracker.exceptions;

public class UsernameAlreadyExistsException extends Exception  {
	private static final long serialVersionUID = 7001746976620554755L;
	
	public UsernameAlreadyExistsException(String username) {
		super("This username is taken");
	
	}
}
