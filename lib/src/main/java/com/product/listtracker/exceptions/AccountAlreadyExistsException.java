package com.product.listtracker.exceptions;

public class AccountAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 7001746976620554755L;
	
	public AccountAlreadyExistsException(String email) {
		super("Account with this email already exists");
	
	}
}

