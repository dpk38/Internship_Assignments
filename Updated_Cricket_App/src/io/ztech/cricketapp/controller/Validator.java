package io.ztech.cricketapp.controller;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.ztech.cricketapp.ui.UserEntry;

public class Validator {
	Logger logger;
	public Validator() {
		logger = Logger.getLogger(UserEntry.class.getName());
	}
	
	public boolean validateInput(String regex, String input, String error) {
		if (Pattern.matches(regex, input)) {
			return true;
		}
		logger.info(error);
		return false;
	}
}
