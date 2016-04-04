package com.stormpath.rule;

/**
 * This is a data class to store rules.
 *  
 * @author weipengbian
 * @version 1.0
 */

public class Rule {
	
	private String regex;
	private String msg;
	
	/**
	 * Constructor
	 * 
	 * @param String msg
	 * @param String regex
	 */
	public Rule(String msg, String regex) {

		this.regex = regex;
		this.msg = msg;
	}
	
	/**
	 * getter to get regex
	 * 
	 * @return String
	 */
	public String getRegex() {
		return regex;
	}
	
	/**
	 * setter method for regex
	 * 
	 * @param String regex
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	/**
	 * getter to get message in rule
	 * 
	 * @return String
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * setter method for rule message
	 * @param String msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
