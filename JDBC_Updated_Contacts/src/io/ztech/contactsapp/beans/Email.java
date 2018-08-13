package io.ztech.contactsapp.beans;

public class Email {
	int cId, eId;
	String email;
	
	// Setters
	public void setcId(int cId) {
		this.cId = cId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Getters
	public int getcId() {
		return cId;
	}
	
	public int geteId() {
		return eId;
	}
	
	public String getEmail() {
		return email;
	}
}
