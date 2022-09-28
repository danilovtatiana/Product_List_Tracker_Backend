package com.product.listtracker.exceptions;

public class PznAlreadyExistsException extends Exception{
	private static final long serialVersionUID = 7001746976620554755L;
	
public PznAlreadyExistsException(String pzn) {
	super("Product by pzn " + pzn + "already exists");

}
}