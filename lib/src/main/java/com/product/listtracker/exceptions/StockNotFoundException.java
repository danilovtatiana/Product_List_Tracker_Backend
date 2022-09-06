package com.product.listtracker.exceptions;

public class StockNotFoundException extends RuntimeException{
	
	public StockNotFoundException(String message) {
		super(message);
	}

}
