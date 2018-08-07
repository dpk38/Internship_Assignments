package io.ztech.contactsapp.beans;

public class CommonDetails {
	int c_id;
	String countryCode, number;
	
	//Setters
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	//Getters
	public int getC_id() {
		return c_id;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public String getNumber() {
		return number;
	}
	
}
