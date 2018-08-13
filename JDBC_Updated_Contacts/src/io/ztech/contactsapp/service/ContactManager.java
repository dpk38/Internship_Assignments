package io.ztech.contactsapp.service;

import java.util.ArrayList;
import java.util.Scanner;

import io.ztech.contactsapp.beans.Contact;
import io.ztech.contactsapp.beans.Email;
import io.ztech.contactsapp.beans.Home;
import io.ztech.contactsapp.beans.Mobile;
import io.ztech.contactsapp.beans.Office;
import io.ztech.contactsapp.constants.MainMenu;
import io.ztech.contactsapp.constants.Regex;
import io.ztech.contactsapp.constants.UserMessages;
import io.ztech.contactsapp.dao.ContactDAO;

public class ContactManager {

	ContactDAO dao;
	Validator validator;

	public ContactManager() {
		dao = new ContactDAO();
		validator = new Validator();
	}

	public void addMobileNumber(Contact newContact, int mid) {
		Scanner scanner = new Scanner(System.in);
		Mobile newMobile = new Mobile();

		System.out.print(UserMessages.ENTER_COUNTRY);
		newMobile.setCountryCode(scanner.nextLine());
		System.out.print(UserMessages.ENTER_MOBILE);
		String number = scanner.nextLine();
		while (!validator.validateInput(number, Regex.numberRegex)) {
			System.out.print(UserMessages.INVALID_NUMBER);
			number = scanner.nextLine();
		}
		newMobile.setNumber(number);
		newMobile.setmId(mid);

		newContact.addNumber(newMobile);
		//dao.insertIntoMobile(newMobile);
	}

	public void addHomeNumber(Contact newContact, int hid) {
		Scanner scanner = new Scanner(System.in);
		Home newHome = new Home();

		System.out.print(UserMessages.ENTER_COUNTRY);
		newHome.setCountryCode(scanner.nextLine());
		System.out.print(UserMessages.ENTER_AREA);
		String areaCode = scanner.nextLine();
		while (!validator.validateInput(areaCode, Regex.numberRegex)) {
			System.out.print(UserMessages.INVALID_CODE);
			areaCode = scanner.nextLine();
		}
		newHome.setAreaCode(areaCode);
		System.out.print(UserMessages.ENTER_HOME);
		String number = scanner.nextLine();
		while (!validator.validateInput(number, Regex.numberRegex)) {
			System.out.print(UserMessages.INVALID_NUMBER);
			number = scanner.nextLine();
		}
		newHome.setNumber(number);
		newHome.sethId(hid);

		newContact.addNumber(newHome);
		//(new ContactDAO()).insertIntoHome(newHome);
	}

	public void addOfficeNumber(Contact newContact, int oid) {
		Scanner scanner = new Scanner(System.in);
		Office newOffice = new Office();

		System.out.print(UserMessages.OFFICE_TYPE);
		int op = scanner.nextInt();
		scanner.nextLine();
		System.out.print("\n" + UserMessages.ENTER_COUNTRY);
		newOffice.setCountryCode(scanner.nextLine());
		newOffice.setoId(oid);
		switch (op) {
		case 1:
			System.out.print(UserMessages.ENTER_AREA);
			String areaCode = scanner.nextLine();
			while (!validator.validateInput(areaCode, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_CODE);
				areaCode = scanner.nextLine();
			}
			newOffice.setAreaCode(areaCode);
			System.out.print(UserMessages.ENTER_EXTENSION);
			String extension = scanner.nextLine();
			while (!validator.validateInput(extension, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_CODE);
				extension = scanner.nextLine();
			}
			newOffice.setExtension(extension);
			System.out.print(UserMessages.ENTER_OFFICE_LANDLINE);
			String number = scanner.nextLine();
			while (!validator.validateInput(number, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_NUMBER);
				number = scanner.nextLine();
			}
			newOffice.setNumber(number);
			break;
		case 2:
			System.out.print(UserMessages.ENTER_OFFICE_MOBILE);
			number = scanner.nextLine();
			while (!validator.validateInput(number, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_NUMBER);
				number = scanner.nextLine();
			}
			newOffice.setNumber(number);
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
			addOfficeNumber(newContact, oid);
			return;
		}

		newContact.addNumber(newOffice);
		//(new ContactDAO()).insertIntoOffice(newOffice);
	}

	public void addEmailId(Contact newContact, int cid, int eid) {
		Scanner scanner = new Scanner(System.in);
		Email newEmail = new Email();

		System.out.print(UserMessages.ENTER_EMAIL);
		String email = scanner.nextLine();
		while (!validator.validateInput(email, Regex.emailRegex)) {
			System.out.print(UserMessages.INVALID_EMAIL);
			email = scanner.nextLine();
		}
		newEmail.setEmail(email);
		newEmail.setcId(cid);
		newEmail.seteId(eid);

		newContact.setEmail(newEmail);
		//(new ContactDAO()).insertIntoEmail(newEmail);
	}

	public void updateMobile(int cid, int mid) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(UserMessages.MOBILE_UPDATE_OPTIONS);
		int selection = scanner.nextInt();
		scanner.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + UserMessages.ENTER_COUNTRY);
			dao.updateCountry(cid, mid, scanner.nextLine(), "mobile");
			break;
		case 2:
			System.out.print(UserMessages.ENTER_NEW_NUMBER);
			String number = scanner.nextLine();
			while (!validator.validateInput(number, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_NUMBER);
				number = scanner.nextLine();
			}
			dao.updateNumber(cid, mid, number, "mobile");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void updateHome(int cid, int hid) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(UserMessages.HOME_UPDATE_OPTIONS);
		int selection = scanner.nextInt();
		scanner.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + UserMessages.ENTER_COUNTRY);
			dao.updateCountry(cid, hid, scanner.nextLine(), "home");
			break;
		case 2:
			System.out.print("\n" + UserMessages.ENTER_AREA);
			String areaCode = scanner.nextLine();
			while (!validator.validateInput(areaCode, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_CODE);
				areaCode = scanner.nextLine();
			}
			dao.updateArea(cid, hid, areaCode, "home");
			break;
		case 3:
			System.out.print(UserMessages.ENTER_NEW_NUMBER);
			String number = scanner.nextLine();
			while (!validator.validateInput(number, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_NUMBER);
				number = scanner.nextLine();
			}
			dao.updateNumber(cid, hid, number, "home");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void updateOffice(int cid, int oid) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(UserMessages.OFFICE_UPDATE_OPTIONS);
		int selection = scanner.nextInt();
		scanner.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + UserMessages.ENTER_COUNTRY);
			dao.updateCountry(cid, oid, scanner.nextLine(), "office");
			break;
		case 2:
			System.out.print("\n" + UserMessages.ENTER_AREA);
			String areaCode = scanner.nextLine();
			while (!validator.validateInput(areaCode, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_CODE);
				areaCode = scanner.nextLine();
			}
			dao.updateArea(cid, oid, areaCode, "office");
			break;
		case 3:
			System.out.print("\n" + UserMessages.ENTER_EXTENSION);
			String extension = scanner.nextLine();
			while (!validator.validateInput(extension, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_CODE);
				extension = scanner.nextLine();
			}
			dao.updateExtension(cid, oid, extension);
			break;
		case 4:
			System.out.print(UserMessages.ENTER_NEW_NUMBER);
			String number = scanner.nextLine();
			while (!validator.validateInput(number, Regex.numberRegex)) {
				System.out.print(UserMessages.INVALID_NUMBER);
				number = scanner.nextLine();
			}
			dao.updateNumber(cid, oid, number, "office");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void editMobileNumber(int cid) {
		Scanner scanner = new Scanner(System.in);
		Contact newContact = new Contact();
		newContact.setcId(cid);

		System.out.print(UserMessages.NUMBER_EDIT);
		char op = scanner.next().charAt(0);
		scanner.nextLine();
		switch (op) {
		case 'a':
			int mid = dao.fetchRecentId(cid, "mobile");
			if (mid == -1) {
				addMobileNumber(newContact, 1);
			} else {
				addMobileNumber(newContact, mid + 1);
			}
			dao.insertNumberDetails(newContact);
			break;
		case 'b':
			ArrayList<Mobile> mobileList = dao.fetchMobile(cid);
			if (mobileList.size() == 0) {
				System.out.println(UserMessages.NO_MOBILE_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.MOBILE_TAB);
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getmId() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print(UserMessages.WHICH_NUMBER);
			mid = scanner.nextInt();
			scanner.nextLine();
			updateMobile(cid, mid);
			break;
		case 'c':
			mobileList = dao.fetchMobile(cid);
			if (mobileList.size() == 0) {
				System.out.println(UserMessages.NO_MOBILE_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.MOBILE_TAB);
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getmId() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print(UserMessages.NUMBER_DELETE);
			mid = scanner.nextInt();
			scanner.nextLine();
			dao.deleteRow(cid, mid, "mobile");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void editHomeNumber(int cid) {
		Scanner scanner = new Scanner(System.in);
		Contact newContact = new Contact();
		newContact.setcId(cid);

		System.out.print(UserMessages.NUMBER_EDIT);
		char op = scanner.next().charAt(0);
		scanner.nextLine();
		switch (op) {
		case 'a':
			int hid = dao.fetchRecentId(cid, "home");
			if (hid == -1) {
				addHomeNumber(newContact, 1);
			} else {
				addHomeNumber(newContact, hid + 1);
			}
			dao.insertNumberDetails(newContact);
			break;
		case 'b':
			ArrayList<Home> homeList = dao.fetchHome(cid);
			if (homeList.size() == 0) {
				System.out.println(UserMessages.NO_HOME_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.HOME_TAB);
			for (Home home : homeList) {
				System.out.print("\n" + home.gethId() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print(UserMessages.WHICH_NUMBER);
			hid = scanner.nextInt();
			scanner.nextLine();
			updateHome(cid, hid);
			break;
		case 'c':
			homeList = dao.fetchHome(cid);
			if (homeList.size() == 0) {
				System.out.println(UserMessages.NO_HOME_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.HOME_TAB);
			for (Home home : homeList) {
				System.out.print("\n" + home.gethId() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print(UserMessages.WHICH_NUMBER);
			hid = scanner.nextInt();
			scanner.nextLine();
			dao.deleteRow(cid, hid, "home");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void editOfficeNumber(int cid) {
		Scanner scanner = new Scanner(System.in);
		Contact newContact = new Contact();
		newContact.setcId(cid);

		System.out.print(UserMessages.NUMBER_EDIT);
		char op = scanner.next().charAt(0);
		scanner.nextLine();
		switch (op) {
		case 'a':
			int oid = dao.fetchRecentId(cid, "office");
			if (oid == -1) {
				addOfficeNumber(newContact, 1);
			} else {
				addOfficeNumber(newContact, oid + 1);
			}
			dao.insertNumberDetails(newContact);
			break;
		case 'b':
			ArrayList<Office> officeList = dao.fetchOffice(cid);
			if (officeList.size() == 0) {
				System.out.println(UserMessages.NO_OFFICE_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.OFFICE_TAB);
			for (Office office : officeList) {
				System.out.print("\n" + office.getoId() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print(UserMessages.WHICH_NUMBER);
			oid = scanner.nextInt();
			scanner.nextLine();
			updateOffice(cid, oid);
			break;
		case 'c':
			officeList = dao.fetchOffice(cid);
			if (officeList.size() == 0) {
				System.out.println(UserMessages.NO_OFFICE_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.OFFICE_TAB);
			for (Office office : officeList) {
				System.out.print("\n" + office.getoId() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print(UserMessages.NUMBER_DELETE);
			oid = scanner.nextInt();
			scanner.nextLine();
			dao.deleteRow(cid, oid, "office");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void editEmailId(int cid) {
		Scanner scanner = new Scanner(System.in);
		Contact newContact = new Contact();
		newContact.setcId(cid);

		System.out.print(UserMessages.EMAIL_EDIT);
		int op = scanner.next().charAt(0);
		scanner.nextLine();
		switch (op) {
		case 'a':
			int eid = dao.fetchRecentId(cid, "email");
			if (eid == -1) {
				addEmailId(newContact, cid, 1);
			} else {
				addEmailId(newContact, cid, eid + 1);
			}
			dao.insertEmailDetails(newContact);
			break;
		case 'b':
			ArrayList<Email> emailList = dao.fetchEmail(cid);
			if (emailList.size() == 0) {
				System.out.println(UserMessages.NO_EMAIL_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.EMAIL_TAB);
			for (Email email : emailList) {
				System.out.print("\n" + email.geteId() + "\t" + email.getEmail());
			}
			System.out.print(UserMessages.WHICH_EMAIL);
			eid = scanner.nextInt();
			scanner.nextLine();
			System.out.println(UserMessages.ENTER_NEW_EMAIL);
			dao.updateEmail(cid, eid, scanner.nextLine());
			break;
		case 'c':
			emailList = dao.fetchEmail(cid);
			if (emailList.size() == 0) {
				System.out.println(UserMessages.NO_EMAIL_ASSOCIATED);
				return;
			}
			System.out.print(UserMessages.EMAIL_TAB);
			for (Email email : emailList) {
				System.out.print("\n" + email.geteId() + "\t" + email.getEmail());
			}
			System.out.print(UserMessages.EMAIL_DELETE);
			eid = scanner.nextInt();
			scanner.nextLine();
			dao.deleteRow(cid, eid, "email");
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public void expandContactDetails(int cid) {
		ArrayList<Mobile> mobileList = dao.fetchMobile(cid);
		System.out.println(UserMessages.MOBILE_TAB);
		for (Mobile mobile : mobileList) {
			System.out.println(mobile.getmId() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
		}

		ArrayList<Home> homeList = dao.fetchHome(cid);
		System.out.println(UserMessages.HOME_TAB);
		for (Home home : homeList) {
			System.out.println(
					home.gethId() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t" + home.getNumber());
		}

		ArrayList<Office> officeList = dao.fetchOffice(cid);
		System.out.println(UserMessages.OFFICE_TAB);
		for (Office office : officeList) {
			System.out.println(office.getoId() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode() + "\t"
					+ office.getExtension() + "\t" + office.getNumber());
		}

		ArrayList<Email> emailList = dao.fetchEmail(cid);
		System.out.println(UserMessages.EMAIL_TAB);
		for (Email email : emailList) {
			System.out.println(email.geteId() + "\t" + email.getEmail());
		}
	}

	public void createContact() {
		Scanner scanner = new Scanner(System.in);
		Contact newContact = new Contact();

		System.out.print(UserMessages.ENTER_FIRST_NAME);
		newContact.setFirstName(scanner.nextLine());
		System.out.print(UserMessages.ENTER_LAST_NAME);
		newContact.setLastName(scanner.nextLine());
		//dao.insertIntoContacts(newContact);

		System.out.print(UserMessages.MOBILE_CHOICE);
		char ch = scanner.next().charAt(0);
		scanner.nextLine();
		int counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = dao.fetchRecentContactId();
			addMobileNumber(newContact, counter);
			counter++;
			System.out.print(UserMessages.ANOTHER_MOBILE);
			ch = scanner.next().charAt(0);
			scanner.nextLine();
		}

		System.out.print(UserMessages.HOME_CHOICE);
		ch = scanner.next().charAt(0);
		scanner.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = dao.fetchRecentContactId();
			addHomeNumber(newContact, counter);
			counter++;
			System.out.print(UserMessages.ANOTHER_HOME);
			ch = scanner.next().charAt(0);
			scanner.nextLine();
		}

		System.out.print(UserMessages.OFFICE_CHOICE);
		ch = scanner.next().charAt(0);
		scanner.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = dao.fetchRecentContactId();
			addOfficeNumber(newContact, counter);
			counter++;
			System.out.print(UserMessages.ANOTHER_OFFICE);
			ch = scanner.next().charAt(0);
			scanner.nextLine();
		}

		System.out.print(UserMessages.EMAIL_CHOICE);
		ch = scanner.next().charAt(0);
		scanner.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = dao.fetchRecentContactId();
			addEmailId(newContact, cid, counter);
			counter++;
			System.out.print(UserMessages.ANOTHER_EMAIL);
			ch = scanner.next().charAt(0);
			scanner.nextLine();
		}
		
		dao.insertContactDetails(newContact);
	}

	public void updateContact() {
		Scanner scanner = new Scanner(System.in);

		System.out.print(UserMessages.ENTER_FIRST_NAME_TO_UPDATE);
		String search = scanner.nextLine();
		ArrayList<Contact> contactList = dao.fetchContacts(search);
		if (contactList.size() == 0) {
			System.out.println(UserMessages.NO_SUCH_CONTACT);
			return;
		}
		char reload;
		do {
			int ignoreFlag = displayContact(search);
			if (ignoreFlag == 0) {
				break;
			}
			System.out.print(UserMessages.RELOAD_LIST);
			reload = scanner.next().charAt(0);
			scanner.nextLine();
		} while (reload == 'Y' || reload == 'y');
		System.out.print(UserMessages.CHOOSE_CONTACT_ID);
		int cid = scanner.nextInt();

		System.out.print(UserMessages.CONTACT_UPDATE_OPTIONS);
		int option = scanner.nextInt();
		scanner.nextLine();
		switch (option) {
		case 1:
			System.out.print(UserMessages.ENTER_UPDATED_FIRST_NAME);
			dao.updateName("firstName", scanner.nextLine(), cid);
			break;
		case 2:
			System.out.print(UserMessages.ENTER_UPDATED_LAST_NAME);
			dao.updateName("lastName", scanner.nextLine(), cid);
			break;
		case 3:
			editMobileNumber(cid);
			break;
		case 4:
			editHomeNumber(cid);
			break;
		case 5:
			editOfficeNumber(cid);
			break;
		case 6:
			editEmailId(cid);
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
	}

	public int displayContact(String name) {
		Scanner scanner = new Scanner(System.in);

		int option;
		if (name == "") {
			System.out.print(UserMessages.DISPLAY_CHOICE);
			option = scanner.nextInt();
			scanner.nextLine();
		} else {
			option = 0;
		}
		System.out.println(UserMessages.CONTACT_TABLE);
		switch (option) {
		case 0:
			ArrayList<Contact> contactList = dao.fetchContacts(name);
			for (Contact contact : contactList) {
				System.out.println(contact.getcId() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		case 1:
			contactList = dao.fetchContacts(0);
			for (Contact contact : contactList) {
				System.out.println(contact.getcId() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		case 2:
			contactList = dao.fetchContacts(1);
			for (Contact contact : contactList) {
				System.out.println(contact.getcId() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		default:
			System.out.println(UserMessages.INVALID_OPTION);
		}
		System.out.print(UserMessages.EXPAND_CONTACT);
		int cid = scanner.nextInt();
		scanner.nextLine();
		if (cid == 0) {
			return 0;
		}
		expandContactDetails(cid);
		return 1;
	}

	public void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		char repeat;
		do {
			System.out.print(UserMessages.MENU);
			int choice = scanner.nextInt();
			MainMenu options = MainMenu.values()[choice - 1];
			scanner.nextLine();
			switch (options) {
			case CREATE_CONTACT:
				createContact();
				break;
			case EDIT_CONTACT:
				updateContact();
				break;
			case VIEW_CONTACT:
				displayContact("");
				break;
			default:
				System.out.println(UserMessages.INVALID_OPTION);
			}
			System.out.print(UserMessages.CONTINUE);
			repeat = scanner.next().charAt(0);
			scanner.nextLine();
		} while (Character.toLowerCase(repeat) == 'y');
	}
}
