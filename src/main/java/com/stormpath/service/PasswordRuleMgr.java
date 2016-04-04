package com.stormpath.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stormpath.rule.Rule;

/**
 * Implementation class for password rule managers
 * 
 * @author weipengbian
 * @version 1.0
 */
public class PasswordRuleMgr implements IRuleMgr {

	static Logger log= LogManager.getLogger( PasswordRuleMgr.class.getName());
	
	LinkedHashMap<String, Rule> rulemap;
	
	
	public PasswordRuleMgr(String defaultbundle) {
		super();
		this.rulemap = new LinkedHashMap<String, Rule> ();
		loadDefaultRuleMap(defaultbundle);
	}

	/**
	 * method to add a rule
	 * 
	 * @param String msg
	 * @param String rule
	 * @return -1 if rule already exists or 1 if added successfully
	 */
	
	public int addRule(String msg, String rule) {
		log.info("Enter...addRule:msg: " + msg + ", rule: " + rule);
		if(this.rulemap.containsKey(msg)) {
			log.error("This rule already exists!!");
			return -1;
		}
		
		this.rulemap.put(msg, new Rule(msg, rule));
		return 1;
	}

	/**
	 * method to remove a rule
	 * 
	 * @param String rule
	 * @return -1 if rule doesn't exist or 1 if removed successfully
	 */
	public int removeRule(String msg) {
		log.info("Enter...removeRule msg: " + msg );
		if(!this.rulemap.containsKey(msg)) {
			log.error("Can't remove this rule. It doesn't exist!!");
			return -1;
		}
		this.rulemap.remove(msg);
		return 1;
	}
	
	/**
	 * method to remove all rules
	 * @return -1 if map is null or 1
	 */
	public int removeAllRule() {
		log.info("Enter...removeAllRule " );
		if(this.rulemap == null) return -1;
		this.rulemap.clear();
		return 1;
	}

	/**
	 * method to get a rule
	 * @param String rule
	 * @return Rule
	 */
	public Rule getRule(String msg) {
		log.info("Enter...getRule msg: " + msg );
		if(this.rulemap.containsKey(msg)) {
			return rulemap.get(msg);
		} else {
			log.error("Can't get this rule. It doesn't exist!!");
			return null;
		}
	}

	/**
	 * method to get a rule map
	 * @return LinkedHashMap<String, Rule>
	 */
	public LinkedHashMap<String, Rule> getRuleMap() {
		log.info("Enter...getRuleMap" );
		if(this.rulemap == null) return new LinkedHashMap<String, Rule> ();
		return this.rulemap;
	}
	
	/**
	 * method to force to load default rule map
	 * @param String defaultbundle
	 */
	//Only load rules from rules.properties
	//doesn't provide functions to changes rules.properites
	//This file has permanent rules which needs to be changed by admin
	public void loadDefaultRuleMap(String defaultbundle) {
		if(defaultbundle == null || defaultbundle.equals("")) defaultbundle = "rules";
		log.info("...loading default rules..." );
		log.info("rule file:" + defaultbundle );
		ResourceBundle rb = ResourceBundle.getBundle(defaultbundle);
		Enumeration<String> enuKeys = rb.getKeys();
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = rb.getString(key);
			log.info(key + ": " + value);
			addRule(key, value);
		}
	}

}
