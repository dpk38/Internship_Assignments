package io.ztech.contactsapp.beans;

public class Home extends CommonDetails {
	int h_id;
	String areaCode;
	
	// Setters
	public void setH_id(int h_id) {
		this.h_id = h_id;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	// Getters
	public int getH_id() {
		return h_id;
	}
	public String getAreaCode() {
		return areaCode;
	}
}
