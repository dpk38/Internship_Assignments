package io.ztech.contactsapp.beans;

public class Email {
	int c_id, e_id;
	String email;
	
	// Setters
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Getters
	public int getC_id() {
		return c_id;
	}
	
	public int getE_id() {
		return e_id;
	}
	
	public String getEmail() {
		return email;
	}
}
