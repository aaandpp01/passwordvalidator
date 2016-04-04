package com.stormpath.service;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Service provider class to provide services,
 * including get rule manager, get password validator, 
 * set custom context, log level
 * 
 * @author weipengbian
 * @version 1.0
 */
public class ServiceProvider {
	
	static Logger log= LogManager.getLogger( ServiceProvider.class.getName());
	
	private static ApplicationContext context;
	private static String  defaultrb;


	/**
	 * method to set log levels
	 * 
	 * @param String level (only differentiate error, debug, info at this version)
	 */
	public static void setRootLogLevel(String level) {
		log.info("Enter...setRootLogLevel...setting logs");
	    LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	    Configuration conf = ctx.getConfiguration();
	    
		Level loglevel = Level.ERROR;
		if(level.equalsIgnoreCase("debug")) {
			conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.DEBUG);
			log.info("Debug log is on.");
		} else if(level.equalsIgnoreCase("info")) {
			conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.INFO);
			log.info("Info log is on.");
		} else {
			conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.ERROR);
			log.error("Error log is on.");
		} 
		
		ctx.updateLoggers(conf);
		
	}
	
	/**
	 * This method is to set application context and rule properties
	 * @param ApplicationContext txt, default null
	 * @param String defaultBundle, default empty string ""
	 */
	public static void setContext(ApplicationContext txt, String defaultBundle) {
		log.info("Enter...setContext()");
		if(txt == null ) context = new ClassPathXmlApplicationContext("application-password-context.xml");
		else context = txt;
		
		defaultrb = defaultBundle;
	}

	/**
	 * This method is to get rule manager
	 * @return PasswordRuleMgr
	 */
	public static PasswordRuleMgr getRuleMgr () {
		log.info("Enter...getRuleMgr()");
		if(context == null ) context = new ClassPathXmlApplicationContext("application-password-context.xml");
		PasswordRuleMgr mgr = 	(PasswordRuleMgr) context.getBean("rulemanager", defaultrb);		
		return mgr;
	}
	
	/**
	 * This method is to get password validator
	 * @return PasswordValidator
	 */
	public static PasswordValidator getPasswordValidator() {
		log.info("Enter...getPasswordValidator()");
		if(context == null ) context = new ClassPathXmlApplicationContext("application-password-context.xml");
		PasswordValidator validator = (PasswordValidator) context.getBean("passwordValidator");
		return validator;
	}
}
