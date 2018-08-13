package io.ztech.contactsapp.constants;

public interface UserMessages {
	String ENTER_FIRST_NAME = "\nEnter first name: ";
	String ENTER_LAST_NAME = "\nEnter last name: ";
	String ENTER_FIRST_NAME_TO_UPDATE = "\nEnter the first name of the contact to update: ";
	String ENTER_UPDATED_FIRST_NAME = "\nEnter the updated first name: ";
	String ENTER_UPDATED_LAST_NAME = "\nEnter the updated last name: ";
	String ENTER_NEW_NUMBER= "\nEnter the new number: ";
	String ENTER_COUNTRY = "Enter country code: ";
	String ENTER_AREA = "Enter area code: ";
	String ENTER_EXTENSION = "Enter extension code: ";
	String ENTER_MOBILE = "Enter mobile number: ";
	String ENTER_HOME = "Enter home number: ";
	String ENTER_OFFICE_LANDLINE = "Enter office landline: ";
	String ENTER_OFFICE_MOBILE = "Enter office mobile: ";
	String ENTER_EMAIL = "Enter the email id: ";
	String ENTER_NEW_EMAIL = "\nEnter the new email address: ";
	
	String INVALID_NUMBER = "\nInvalid number entered! Enter again: ";
	String INVALID_CODE = "\nInvalid code entered! Enter again: ";
	String INVALID_OPTION = "Invalid option entered! Try again";
	String INVALID_EMAIL = "\nInvalid email entered! Enter again: ";
	
	String NUMBER_EDIT = "\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ";
	String EMAIL_EDIT = "\nDo you wish to:\na) Add new email id\nb) Change existing email id\nc) Delete an email id\nEnter your choice: ";
	String CONTACT_UPDATE_OPTIONS = "\n1. First Name\n2. Last Name\n3. Mobile Number\n4. Home Number\n5. Office Number\n6. Email Id\nWhich field do you wish to update? ";
	String MOBILE_UPDATE_OPTIONS = "\nWhat do you want to alter?\n1. Country code\n2. Mobile number\nEnter your choice: ";
	String HOME_UPDATE_OPTIONS = "\nWhat do you want to alter?\n1. Country code\n2. Area Code\n3. Home number\nEnter your choice: ";
	String OFFICE_UPDATE_OPTIONS = "\nWhat do you want to alter?\n1. Country code\n2. Area Code\n3. Extension\n4. Office number\nEnter your choice: ";
	
	String CONTINUE = "Do you wish to continue (y/n)? ";
	String OFFICE_TYPE = "\nPress 1 for landline\nPress 2 for mobile: ";
	String MENU = "\nMenu:\n1. Create contact\n2. Edit Contact\n3. View Contact\nEnter your choice: ";
	String EXPAND_CONTACT = "Enter contact id to expand (or press 0 to ignore): ";
	String CONTACT_TABLE = "\nContact ID\tFirst Name\tLast Name\n------------------------------------------";
	String DISPLAY_CHOICE = "\n1. Display all contacts\n2. Display all contacts (By descending)\nYour choice: ";
	String CHOOSE_CONTACT_ID = "\nChoose the appropriate contact id to alter: ";
	String RELOAD_LIST = "\nDo you wish to reload list (y/n)?: ";
	
	String ANOTHER_EMAIL = "\nDo you wish to add another email (y/n)? ";	
	String EMAIL_CHOICE = "\nDo you wish to enter your email id (y/n)? ";
	String ANOTHER_OFFICE = "\nDo you wish to add another office number (y/n)? ";
	String OFFICE_CHOICE = "\nDo you wish to add an office number (y/n)? ";
	String ANOTHER_HOME = "\nDo you wish to add another home number (y/n)? ";
	String HOME_CHOICE = "\nDo you wish to add a home number (y/n)? ";
	String ANOTHER_MOBILE = "\nDo you wish to add another mobile number (y/n)? ";
	String MOBILE_CHOICE = "\nDo you wish to add a mobile number (y/n)? ";
	
	String EMAIL_TAB = "\nE_id\tEmail\n-------------------------------";
	String OFFICE_TAB = "\nOff_ID  Country\tArea\tExt\tOffice Number\n-------------------------------------------------";
	String HOME_TAB = "\nHom_ID\tCountry\tArea\tHome Number\n-------------------------------------";
	String MOBILE_TAB = "\nMob_ID\tCountry\tMobile Number\n-------------------------------";
	
	String NO_EMAIL_ASSOCIATED = "\nThere are no email IDs associated to this contact!";
	String NO_OFFICE_ASSOCIATED = "\nThere are no office numbers associated to this contact!";
	String NO_HOME_ASSOCIATED = "\nThere are no home numbers associated to this contact!";
	String NO_MOBILE_ASSOCIATED = "\nThere are no mobile numbers associated to this contact!";
	String NO_SUCH_CONTACT = "There is no such contact present!";
	
	String EMAIL_DELETE = "\nWhich email address do you wish to delete? Enter e_id: ";
	String NUMBER_DELETE = "\nWhich number do you wish to delete? Enter o_id: ";
	String WHICH_EMAIL = "\nWhich email address do you wish to update? Enter e_id: ";
	String WHICH_NUMBER = "\nWhich number do you wish to update? Enter o_id: ";
}
