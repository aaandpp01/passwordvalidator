package com.stormpath.service;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * test class for ServiceProvider
 * @author weipengbian
 * @version 1.0
 */
public class ServiceProviderTest extends AbstractServiceForTesting {
	
	/**
	 * test method for getRuleMgr()
	 */
	@Test
	public void testGetRuleMgr() {
		
		log.info("Start testGetRuleMgr()...");
		assertNotNull(ServiceProvider.getRuleMgr() );
		assertTrue(ServiceProvider.getRuleMgr() instanceof PasswordRuleMgr);
    	ServiceProvider.setContext(null, "");
		assertNotNull(ServiceProvider.getRuleMgr() );
		assertTrue(ServiceProvider.getRuleMgr() instanceof PasswordRuleMgr);
		log.info("Finished... testGetRuleMgr()...");
	}
	
	/**
	 * test method for getPasswordValidator()
	 */
	@Test
	public void testGetPasswordValidator() {
		log.info("Start testGetPasswordValidator()...");
		assertNotNull(ServiceProvider.getPasswordValidator() );
		assertTrue(ServiceProvider.getPasswordValidator() instanceof PasswordValidator);
    	ServiceProvider.setContext(null, "");
		assertNotNull(ServiceProvider.getRuleMgr() );
		assertTrue(ServiceProvider.getRuleMgr() instanceof PasswordRuleMgr);
		log.info("Finished... testGetPasswordValidator()...");
	}
	
}
