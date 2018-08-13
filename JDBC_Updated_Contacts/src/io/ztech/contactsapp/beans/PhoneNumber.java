package io.ztech.contactsapp.beans;

public class PhoneNumber {
	int cId;
	String countryCode, number;
	
	//Setters
	public void setcId(int cId) {
		this.cId = cId;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	//Getters
	public int getcId() {
		return cId;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	public String getNumber() {
		return number;
	}
	
}
