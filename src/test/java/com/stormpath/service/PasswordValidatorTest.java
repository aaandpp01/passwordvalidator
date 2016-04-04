package com.stormpath.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * test class for PasswordValidator
 * @author weipengbian
 * @version 1.0
 */
public class PasswordValidatorTest extends AbstractServiceForTesting {

	@Autowired
	PasswordValidator passwordValidator;
	
	/**
	 * test method for validate(String password)
	 */
	@Test
	public void testValidate() {
		log.info("Start testValidate()...");
		/* Test all test cases with different combinations of characters */
		assertFalse(passwordValidator.validate(""));
		assertFalse(passwordValidator.validate(null));
		assertFalse(passwordValidator.validate("1"));
		assertFalse(passwordValidator.validate("a"));
		assertFalse(passwordValidator.validate("1234"));
		assertFalse(passwordValidator.validate("abcd"));
		assertFalse(passwordValidator.validate("12ab"));
		assertFalse(passwordValidator.validate("12345"));
		assertFalse(passwordValidator.validate("12345678901"));
		assertFalse(passwordValidator.validate("123456789012"));
		assertFalse(passwordValidator.validate("1234567890123"));
		assertFalse(passwordValidator.validate("abcde"));
		assertFalse(passwordValidator.validate("abcdefghi"));
		assertFalse(passwordValidator.validate("abcdefghijklm"));
		assertFalse(passwordValidator.validate("A&D12%"));
		assertFalse(passwordValidator.validate(".,.,23sfd"));
		assertFalse(passwordValidator.validate("abdc2134AD"));
		assertFalse(passwordValidator.validate("abc1234abc1234"));
		assertFalse(passwordValidator.validate("aabd1234"));
		assertFalse(passwordValidator.validate("1234aab"));
		assertFalse(passwordValidator.validate("1234abab"));
		assertFalse(passwordValidator.validate("1234abaaa"));
		assertFalse(passwordValidator.validate("abc1234abb"));
		
		assertTrue(passwordValidator.validate("abc1abc"));
		assertTrue(passwordValidator.validate("a1234"));
		assertTrue(passwordValidator.validate("abc123abc"));
		assertTrue(passwordValidator.validate("acabc123abc"));
		assertTrue(passwordValidator.validate("acabc123abcd"));
		assertTrue(passwordValidator.validate("acabcdc3abcd"));
		
		log.info("Finished... testValidate()...");
	}
	
	/**
	 * test method for validate(PasswordRuleMgr mgr, String password)
	 */
	@Test
	public void testValidateWithMgr() {
		log.info("Start testValidateWithMgr()...");
		/* Test validate method with a rule manager with empty rule map */
		PasswordRuleMgr mgr = ServiceProvider.getRuleMgr();
		mgr.removeAllRule();
		//no password rules, should return true for anything
		assertTrue(passwordValidator.validate(mgr, "@$#@$"));
		mgr.addRule("length has to be between 3 and 5", ".{3,5}");
		assertTrue(passwordValidator.validate(mgr, "@$#@$"));
		assertFalse(passwordValidator.validate(mgr, "@$"));
		log.info("Finished... testValidateWithMgr()...");
	}
}
