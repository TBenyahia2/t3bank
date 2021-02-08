package com.t3bank.utility;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class HelloLogger {

	private static final Logger LOGGER = LogManager.getLogger(HelloLogger.class);
	
	public static void main(String[] args) {
		LOGGER.info("Hello Logger started.");
		System.out.println("Hello World!");
	}
	
	public int testAddMe(int num1, int num2) {
		return num1 + num2;
	}
	public void logCheck() {
		LOGGER.info("Info Log");
		LOGGER.error("Error Log");
		LOGGER.warn("Warn Log");
		LOGGER.debug("Debug Log");
	}

}