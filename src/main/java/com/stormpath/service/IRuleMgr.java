package com.stormpath.service;

import java.util.LinkedHashMap;

import com.stormpath.rule.Rule;

/**
 * IRuleMgr interface for rule managers
 * @author weipengbian
 * @version 1.0
 */

public interface  IRuleMgr {
	
	/**
	 * method to add a rule
	 * 
	 * @param String msg
	 * @param String rule
	 * @return int
	 */
	public int addRule(String msg, String rule);
	
	/**
	 * method to remove a rule
	 * 
	 * @param String rule
	 * @return int
	 */
	public int removeRule(String rule);
	
	/**
	 * method to remove all rules
	 * @return int
	 */
	public int removeAllRule();
	
	/**
	 * method to get a rule
	 * @param String rule
	 * @return Rule
	 */
	public Rule getRule(String rule);
	
	/**
	 * method to get a rule map
	 * @return LinkedHashMap<String, Rule>
	 */
	public LinkedHashMap<String, Rule> getRuleMap(); 
	
	/**
	 * method to force to load default rule map
	 * @param String defaultbundle
	 */
	public void loadDefaultRuleMap(String defaultbundle);

}
