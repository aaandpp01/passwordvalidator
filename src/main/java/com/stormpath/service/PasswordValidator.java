package com.stormpath.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stormpath.rule.Rule;

/**
 * Password validation class which contains all validation operations
 * 
 * @author weipengbian
 * @version 1.0
 * 
 */

public class PasswordValidator implements Validator {

	static Logger log= LogManager.getLogger( PasswordValidator.class.getName());
	
	/**
	 * This is a validation method used with default rules in properties file
	 * @param String password
	 * @return boolean
	 */	
	public boolean validate(String password) {
		log.info("Enter...validate::password: " + password);
		if(password == null) return false;
		return validate(ServiceProvider.getRuleMgr(), password);
	}
	
	/**
	 * This is a validation method when temporary customization rules required
	 * For example, if you need to used your own rule set X, you can clear all
	 * rules in PasswordRuleMgr mgr, and add rule set X to mgr and validate 
	 * against this rule manager and password
	 * 
	 * @param PasswordRuleMgr mgr
	 * @param String password
	 * @return boolean
	 */	
	public boolean validate(PasswordRuleMgr mgr, String password) {
		log.info("Enter...validate::mgr: " + mgr + "password: " + password);
		if(password == null) return false;
		LinkedHashMap<String, Rule> rulemap = mgr.getRuleMap();
		boolean isValid = true;
		for(Map.Entry<String, Rule> entry: rulemap.entrySet()) {
			if(entry.getValue().getRegex().equals("custom")) {
				log.info("validating rule: " + entry.getKey());
				isValid = validateCustom(entry.getKey(), password);
				log.info("Password is: " + (isValid ? "good" :"bad"));
				if(!isValid) return false;
				else continue;
			}
			log.info("validating regex: " + entry.getValue().getRegex());
			isValid = Pattern.matches(entry.getValue().getRegex(), password);
			log.info("Password is: " + (isValid ? "good" :"bad"));
			if(!isValid) return false;
		}
		return true;
	}
	
	/**
	 * This validation method is used to add more complication rules which defined 
	 * as "custom" in rule properties file
	 * 
	 * @param String msg
	 * @param String password
	 * @return boolean
	 */
	public boolean validateCustom(String msg, String password) {
		log.info("Enter...validateCustom: customized rule: " + msg + ", password: " + password);
		String no_repeating_sequence = "Password must not contain any sequence of characters immediately followed by the same sequence";
		if(msg.equalsIgnoreCase(no_repeating_sequence)) {
			int size = password.length();
			if(size == 0) return false;
			for(int i = 0; i< size; i++) {
				for(int l = 1; l <= (size-i)/2; l++) {
					if(password.substring(i, i+l).equals(password.substring(i+l, i+l+l))) return false;
				}
			}
			return true;	
		}
		return true;
	}
}
