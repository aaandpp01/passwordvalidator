package com.stormpath.service;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * test class for PasswordRuleMgr
 * @author weipengbian
 * @version 1.0
 */
public class PasswordRuleMgrTest extends AbstractServiceForTesting {

	PasswordRuleMgr passwordRuleMgr = ServiceProvider.getRuleMgr();

	/**
	 * tests for loadDefaultRuleMap()
	 */
	@Test
	public void testLoadDefaultRuleMap() {
		log.info("Start testLoadDefaultRuleMap()...");
		passwordRuleMgr.loadDefaultRuleMap("");
		assertNotNull(passwordRuleMgr.getRuleMap());
		/* Test if rule map has all the default rules */
		assertTrue(passwordRuleMgr.getRuleMap().containsKey(
				"Password must be between 5 and 12 characters in length"));
		assertTrue(passwordRuleMgr
				.getRuleMap()
				.containsKey(
						"Password must not contain any sequence of characters immediately followed by the same sequence"));
		assertTrue(passwordRuleMgr.getRuleMap().containsKey(
				"Password must consist of a mixture of lowercase letters and numerical "
						+ "digits only with at least one of each"));
		log.info("Finished... testLoadDefaultRuleMap()...");
	}

	/**
	 * tests for addRule()
	 */
	@Test
	public void testAddRule() {
		log.info("Start testAddRule()...");
		/* Test rule before and after added */
		assertFalse(passwordRuleMgr.getRuleMap().containsKey("Test"));
		passwordRuleMgr.addRule("Test", "custom");
		assertTrue(passwordRuleMgr.getRuleMap().containsKey("Test"));
		log.info("Finished... testAddRule()...");
	}

	/**
	 * tests for removeAllRule()
	 */
	@Test
	public void testRemoveAllRule() {
		log.info("Start testRemoveAllRule()...");
		/* Test rule map before and after removed all*/
		assertFalse(passwordRuleMgr.getRuleMap().isEmpty());
		passwordRuleMgr.removeAllRule();
		assertTrue(passwordRuleMgr.getRuleMap().isEmpty());
		log.info("Finished... testRemoveAllRule()...");
	}

	/**
	 * tests for removeRule()
	 */
	@Test
	public void testRemoveRule() {
		log.info("Start testRemoveRule()...");
		/* Test rule before and after removed*/
		passwordRuleMgr.addRule("Test", "Custom");
		assertTrue(passwordRuleMgr.getRuleMap().containsKey("Test"));
		passwordRuleMgr.removeRule("Test");
		assertFalse(passwordRuleMgr.getRuleMap().containsKey("Test"));
		log.info("Finished... testRemoveRule()...");
	}

}
