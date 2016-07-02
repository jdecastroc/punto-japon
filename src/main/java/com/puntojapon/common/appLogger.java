package com.puntojapon.common;

import org.apache.log4j.Logger;

public class appLogger {

	final static Logger logger = Logger.getLogger(appLogger.class);
	
	public static void logInfo(String message) {
		logger.info(message);
	}
	
	public static void logWarn(String message) {
		logger.warn(message);
	}
	
	public static void logDebug(String message) {
		logger.debug(message);
	}
	
	public static void logError(String message) {
		logger.error(message);
	}
}
