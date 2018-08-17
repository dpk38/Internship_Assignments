package io.ztech.cricketapp.controller;

import java.util.regex.Pattern;

public class Validator {
	public Validator() {}
	
	public boolean validateInput(String regex, String input, String error) {
		if (Pattern.matches(regex, input)) {
			return true;
		}
		System.out.println(error);
		return false;
	}
}
