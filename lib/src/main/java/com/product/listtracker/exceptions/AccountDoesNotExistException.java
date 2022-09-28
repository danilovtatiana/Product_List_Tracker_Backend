package com.product.listtracker.exceptions;

public class AccountDoesNotExistException extends Exception {
private static final long serialVersionUID = 7001746976620554755L;
	
	public AccountDoesNotExistException(String email) {
		super("Account with email: " + email + " does not exist");
	}
}