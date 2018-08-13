package io.ztech.contactsapp.service;

import java.util.regex.Pattern;

public class Validator {
	public boolean validateInput(String stringToValidate, String regex) {
		Pattern p = Pattern.compile(regex);
		if (stringToValidate != null) {
			return p.matcher(stringToValidate).matches();
		}
		return false;
	}
}
