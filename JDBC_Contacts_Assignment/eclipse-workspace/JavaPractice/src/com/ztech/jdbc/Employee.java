package com.ztech.jdbc;

public class Employee {
	
	private String firstName, lastName;
	private String email;
	private long homeNumber, officeNumber, mobileNumber;
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public long getHomeNumber() {
		return homeNumber;
	}
	public long getOfficeNumber() {
		return officeNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public void setNumber(long homeNumber, long officeNumber, long mobileNumber) {
		this.homeNumber = homeNumber;
		this.officeNumber = officeNumber;
		this.mobileNumber = mobileNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}