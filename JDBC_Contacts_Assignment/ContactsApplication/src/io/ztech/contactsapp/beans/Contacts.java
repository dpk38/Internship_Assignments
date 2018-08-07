package io.ztech.contactsapp.beans;

public class Contacts {
	int c_id;
	String firstName, lastName;
	
	// Setters
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Getters
	public int getC_id() {
		return c_id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
