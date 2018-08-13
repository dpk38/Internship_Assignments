package io.ztech.contactsapp.beans;

public class Office extends PhoneNumber {
	int oId;
	String areaCode, extension;
	
	// Setters
	public void setoId(int oId) {
		this.oId = oId;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	// Getters
	public int getoId() {
		return oId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getExtension() {
		return extension;
	}
}
