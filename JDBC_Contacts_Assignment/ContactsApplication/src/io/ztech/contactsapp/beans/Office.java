package io.ztech.contactsapp.beans;

public class Office extends CommonDetails {
	int o_id;
	String areaCode, extension;
	
	// Setters
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	// Getters
	public int getO_id() {
		return o_id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getExtension() {
		return extension;
	}
}
