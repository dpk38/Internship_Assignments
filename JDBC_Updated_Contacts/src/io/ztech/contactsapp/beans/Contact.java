package io.ztech.contactsapp.beans;

import java.util.ArrayList;

public class Contact {
	int cId;
	String firstName, lastName;
	ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
	Email email = new Email();
	
	// Setters
	public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	
	// Getters
	public ArrayList<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public int getcId() {
		return cId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Email getEmail() {
		return email;
	}
	
	public void addNumber(PhoneNumber newNumber) {
		phoneNumbers.add(newNumber);
	}
}
