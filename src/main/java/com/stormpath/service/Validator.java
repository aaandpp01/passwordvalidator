package com.stormpath.service;

/**
 * validator interface
 * @author weipengbian
 * @version 1.0
 */
public interface Validator {
	
	/**
	 * method to validate a string
	 * @param String password
	 * @return
	 */
	public boolean validate(String password);
}
