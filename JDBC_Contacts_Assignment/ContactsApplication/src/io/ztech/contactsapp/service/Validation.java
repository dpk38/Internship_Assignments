package io.ztech.contactsapp.service;

import java.util.regex.Pattern;

public class Validation {
	public boolean validateNumber(String number) {
		String numberRegex = "\\d+";
		Pattern p = Pattern.compile(numberRegex);
		if (number != null) {
			return p.matcher(number).matches();
		}
		return false;
	}

	public boolean validateEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";		//"^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"+ "A-Z]{2,7}$";
		Pattern p = Pattern.compile(emailRegex);
		if (email != null) {
			return p.matcher(email).matches();
		}
		return false;
	}
}
