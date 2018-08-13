package io.ztech.contactsapp.beans;

public class Home extends PhoneNumber {
	int hId;
	String areaCode;
	
	// Setters
	public void sethId(int hId) {
		this.hId = hId;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	// Getters
	public int gethId() {
		return hId;
	}
	public String getAreaCode() {
		return areaCode;
	}
}
