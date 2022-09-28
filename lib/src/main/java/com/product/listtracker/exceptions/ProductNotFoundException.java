package com.product.listtracker.exceptions;

public class ProductNotFoundException extends Exception{
	
	private static final long serialVersionUID = 7001746976620554755L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
