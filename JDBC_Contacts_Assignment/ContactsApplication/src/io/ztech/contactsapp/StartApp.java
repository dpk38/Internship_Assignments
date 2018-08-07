package io.ztech.contactsapp;

import io.ztech.contactsapp.service.*;

public class StartApp {
	public static void main (String[] args) {
		(new ContactManagement()).displayMenu();
	}
}
