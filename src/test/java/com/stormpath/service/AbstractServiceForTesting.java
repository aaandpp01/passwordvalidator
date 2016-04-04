package com.stormpath.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * This is an abstract class.
 * It has context configuration and logger field
 * 
 * @author weipengbian
 * @version 1.0
 */

@ContextConfiguration("/application-test-context.xml")
public class AbstractServiceForTesting extends AbstractJUnit4SpringContextTests {
    final protected Logger log= LogManager.getLogger( this.getClass().getName());
}
