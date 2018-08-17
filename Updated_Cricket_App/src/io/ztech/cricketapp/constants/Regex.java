package io.ztech.cricketapp.constants;

public class Regex {
	private Regex() {}
	
	public static final String numberRegex = "\\d+";
	public static final String nameRegex = "[A-Za-z]+";
	public static final String dateRegex = "^(20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
	public static final String ballRegex = "(^(out|w|no)\\+[0-9]$|^[0-9]$)";
}
