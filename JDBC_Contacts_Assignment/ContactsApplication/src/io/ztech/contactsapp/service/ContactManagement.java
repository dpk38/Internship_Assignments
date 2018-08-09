package io.ztech.contactsapp.service;

import java.util.ArrayList;
import java.util.Scanner;

import io.ztech.contactsapp.beans.Contacts;
import io.ztech.contactsapp.beans.Email;
import io.ztech.contactsapp.beans.Home;
import io.ztech.contactsapp.beans.Mobile;
import io.ztech.contactsapp.beans.Office;
import io.ztech.contactsapp.constants.PrintStatements;
import io.ztech.contactsapp.dao.DatabaseInsertion;

public class ContactManagement {

	public static void addMobileNumber(int cid, int mid) {
		Scanner sc = new Scanner(System.in);
		Mobile newMobile = new Mobile();

		System.out.print(PrintStatements.ENTER_COUNTRY);
		newMobile.setCountryCode(sc.nextLine());
		System.out.print(PrintStatements.ENTER_MOBILE);
		String number = sc.nextLine();
		while (!(new Validation()).validateNumber(number)) {
			System.out.print(PrintStatements.INVALID_NUMBER);
			number = sc.nextLine();
		}
		newMobile.setNumber(number);
		newMobile.setC_id(cid);
		newMobile.setM_id(mid);

		(new DatabaseInsertion()).insertIntoMobile(newMobile);
	}

	public static void addHomeNumber(int cid, int hid) {
		Scanner sc = new Scanner(System.in);
		Home newHome = new Home();

		System.out.print(PrintStatements.ENTER_COUNTRY);
		newHome.setCountryCode(sc.nextLine());
		System.out.print(PrintStatements.ENTER_AREA);
		String areaCode = sc.nextLine();
		while (!(new Validation()).validateNumber(areaCode)) {
			System.out.print(PrintStatements.INVALID_CODE);
			areaCode = sc.nextLine();
		}
		newHome.setAreaCode(areaCode);
		System.out.print(PrintStatements.ENTER_HOME);
		String number = sc.nextLine();
		while (!(new Validation()).validateNumber(number)) {
			System.out.print(PrintStatements.INVALID_NUMBER);
			number = sc.nextLine();
		}
		newHome.setNumber(number);
		newHome.setC_id(cid);
		newHome.setH_id(hid);

		(new DatabaseInsertion()).insertIntoHome(newHome);
	}

	public static void addOfficeNumber(int cid, int oid) {
		Scanner sc = new Scanner(System.in);
		Office newOffice = new Office();

		System.out.print(PrintStatements.OFFICE_TYPE);
		int op = sc.nextInt();
		sc.nextLine();
		System.out.print("\n" + PrintStatements.ENTER_COUNTRY);
		newOffice.setCountryCode(sc.nextLine());
		newOffice.setC_id(cid);
		newOffice.setO_id(oid);
		switch (op) {
		case 1:
			System.out.print(PrintStatements.ENTER_AREA);
			String areaCode = sc.nextLine();
			while (!(new Validation()).validateNumber(areaCode)) {
				System.out.print(PrintStatements.INVALID_CODE);
				areaCode = sc.nextLine();
			}
			newOffice.setAreaCode(areaCode);
			System.out.print(PrintStatements.ENTER_EXTENSION);
			String extension = sc.nextLine();
			while (!(new Validation()).validateNumber(extension)) {
				System.out.print(PrintStatements.INVALID_CODE);
				extension = sc.nextLine();
			}
			newOffice.setExtension(extension);
			System.out.print(PrintStatements.ENTER_OFFICE_LANDLINE);
			String number = sc.nextLine();
			while (!(new Validation()).validateNumber(number)) {
				System.out.print(PrintStatements.INVALID_NUMBER);
				number = sc.nextLine();
			}
			newOffice.setNumber(number);
			break;
		case 2:
			System.out.print(PrintStatements.ENTER_OFFICE_MOBILE);
			number = sc.nextLine();
			while (!(new Validation()).validateNumber(number)) {
				System.out.print(PrintStatements.INVALID_NUMBER);
				number = sc.nextLine();
			}
			newOffice.setNumber(number);
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
			addOfficeNumber(cid, oid);
			return;
		}

		(new DatabaseInsertion()).insertIntoOffice(newOffice);
	}

	public static void addEmailId(int cid, int eid) {
		Scanner sc = new Scanner(System.in);
		Email newEmail = new Email();

		System.out.print(PrintStatements.ENTER_EMAIL);
		String email = sc.nextLine();
		while (!(new Validation()).validateEmail(email)) {
			System.out.print(PrintStatements.INVALID_EMAIL);
			email = sc.nextLine();
		}
		newEmail.setEmail(email);
		newEmail.setC_id(cid);
		newEmail.setE_id(eid);

		(new DatabaseInsertion()).insertIntoEmail(newEmail);
	}

	public void updateMobile(int cid, int mid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.MOBILE_UPDATE_OPTIONS);
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + PrintStatements.ENTER_COUNTRY);
			di.updateCountry(cid, mid, sc.nextLine(), "mobile");
			break;
		case 2:
			System.out.print(PrintStatements.ENTER_NEW_NUMBER);
			String number = sc.nextLine();
			while (!(new Validation()).validateNumber(number)) {
				System.out.print(PrintStatements.INVALID_NUMBER);
				number = sc.nextLine();
			}
			di.updateNumber(cid, mid, number, "mobile");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void updateHome(int cid, int hid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.HOME_UPDATE_OPTIONS);
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + PrintStatements.ENTER_COUNTRY);
			di.updateCountry(cid, hid, sc.nextLine(), "home");
			break;
		case 2:
			System.out.print("\n" + PrintStatements.ENTER_AREA);
			String areaCode = sc.nextLine();
			while (!(new Validation()).validateNumber(areaCode)) {
				System.out.print(PrintStatements.INVALID_CODE);
				areaCode = sc.nextLine();
			}
			di.updateArea(cid, hid, areaCode, "home");
			break;
		case 3:
			System.out.print(PrintStatements.ENTER_NEW_NUMBER);
			String number = sc.nextLine();
			while (!(new Validation()).validateNumber(number)) {
				System.out.print(PrintStatements.INVALID_NUMBER);
				number = sc.nextLine();
			}
			di.updateNumber(cid, hid, number, "home");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void updateOffice(int cid, int oid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.OFFICE_UPDATE_OPTIONS);
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\n" + PrintStatements.ENTER_COUNTRY);
			di.updateCountry(cid, oid, sc.nextLine(), "office");
			break;
		case 2:
			System.out.print("\n" + PrintStatements.ENTER_AREA);
			String areaCode = sc.nextLine();
			while (!(new Validation()).validateNumber(areaCode)) {
				System.out.print(PrintStatements.INVALID_CODE);
				areaCode = sc.nextLine();
			}
			di.updateArea(cid, oid, areaCode, "office");
			break;
		case 3:
			System.out.print("\n" + PrintStatements.ENTER_EXTENSION);
			String extension = sc.nextLine();
			while (!(new Validation()).validateNumber(extension)) {
				System.out.print(PrintStatements.INVALID_CODE);
				extension = sc.nextLine();
			}
			di.updateExtension(cid, oid, extension);
			break;
		case 4:
			System.out.print(PrintStatements.ENTER_NEW_NUMBER);
			String number = sc.nextLine();
			while (!(new Validation()).validateNumber(number)) {
				System.out.print(PrintStatements.INVALID_NUMBER);
				number = sc.nextLine();
			}
			di.updateNumber(cid, oid, number, "office");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void editMobileNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.NUMBER_EDIT);
		char op = sc.next().charAt(0);
		sc.nextLine();
		switch (op) {
		case 'a':
			int mid = di.fetchRecentId(cid, "mobile");
			if (mid == -1) {
				addMobileNumber(cid, 1);
			} else {
				addMobileNumber(cid, mid + 1);
			}
			break;
		case 'b':
			ArrayList<Mobile> mobileList = di.fetchMobile(cid);
			if (mobileList.size() == 0) {
				System.out.println(PrintStatements.NO_MOBILE_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.MOBILE_TAB);
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print(PrintStatements.WHICH_NUMBER);
			mid = sc.nextInt();
			sc.nextLine();
			updateMobile(cid, mid);
			break;
		case 'c':
			mobileList = di.fetchMobile(cid);
			if (mobileList.size() == 0) {
				System.out.println(PrintStatements.NO_MOBILE_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.MOBILE_TAB);
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print(PrintStatements.NUMBER_DELETE);
			mid = sc.nextInt();
			sc.nextLine();
			di.deleteRow(cid, mid, "mobile");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void editHomeNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.NUMBER_EDIT);
		char op = sc.next().charAt(0);
		sc.nextLine();
		switch (op) {
		case 'a':
			int hid = di.fetchRecentId(cid, "home");
			if (hid == -1) {
				addHomeNumber(cid, 1);
			} else {
				addHomeNumber(cid, hid + 1);
			}
			break;
		case 'b':
			ArrayList<Home> homeList = di.fetchHome(cid);
			if (homeList.size() == 0) {
				System.out.println(PrintStatements.NO_HOME_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.HOME_TAB);
			for (Home home : homeList) {
				System.out.print("\n" + home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print(PrintStatements.WHICH_NUMBER);
			hid = sc.nextInt();
			sc.nextLine();
			updateHome(cid, hid);
			break;
		case 'c':
			homeList = di.fetchHome(cid);
			if (homeList.size() == 0) {
				System.out.println(PrintStatements.NO_HOME_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.HOME_TAB);
			for (Home home : homeList) {
				System.out.print("\n" + home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print(PrintStatements.WHICH_NUMBER);
			hid = sc.nextInt();
			sc.nextLine();
			di.deleteRow(cid, hid, "home");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void editOfficeNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.NUMBER_EDIT);
		char op = sc.next().charAt(0);
		sc.nextLine();
		switch (op) {
		case 'a':
			int oid = di.fetchRecentId(cid, "office");
			if (oid == -1) {
				addOfficeNumber(cid, 1);
			} else {
				addOfficeNumber(cid, oid + 1);
			}
			break;
		case 'b':
			ArrayList<Office> officeList = di.fetchOffice(cid);
			if (officeList.size() == 0) {
				System.out.println(PrintStatements.NO_OFFICE_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.OFFICE_TAB);
			for (Office office : officeList) {
				System.out.print("\n" + office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print(PrintStatements.WHICH_NUMBER);
			oid = sc.nextInt();
			sc.nextLine();
			updateOffice(cid, oid);
			break;
		case 'c':
			officeList = di.fetchOffice(cid);
			if (officeList.size() == 0) {
				System.out.println(PrintStatements.NO_OFFICE_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.OFFICE_TAB);
			for (Office office : officeList) {
				System.out.print("\n" + office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print(PrintStatements.NUMBER_DELETE);
			oid = sc.nextInt();
			sc.nextLine();
			di.deleteRow(cid, oid, "office");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void editEmailId(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.EMAIL_EDIT);
		int op = sc.next().charAt(0);
		sc.nextLine();
		switch (op) {
		case 'a':
			int eid = di.fetchRecentId(cid, "email");
			if (eid == -1) {
				addEmailId(cid, 1);
			} else {
				addEmailId(cid, eid + 1);
			}
			break;
		case 'b':
			ArrayList<Email> emailList = di.fetchEmail(cid);
			if (emailList.size() == 0) {
				System.out.println(PrintStatements.NO_EMAIL_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.EMAIL_TAB);
			for (Email email : emailList) {
				System.out.print("\n" + email.getE_id() + "\t" + email.getEmail());
			}
			System.out.print(PrintStatements.WHICH_EMAIL);
			eid = sc.nextInt();
			sc.nextLine();
			System.out.println(PrintStatements.ENTER_NEW_EMAIL);
			di.updateEmail(cid, eid, sc.nextLine());
			break;
		case 'c':
			emailList = di.fetchEmail(cid);
			if (emailList.size() == 0) {
				System.out.println(PrintStatements.NO_EMAIL_ASSOCIATED);
				return;
			}
			System.out.print(PrintStatements.EMAIL_TAB);
			for (Email email : emailList) {
				System.out.print("\n" + email.getE_id() + "\t" + email.getEmail());
			}
			System.out.print(PrintStatements.EMAIL_DELETE);
			eid = sc.nextInt();
			sc.nextLine();
			di.deleteRow(cid, eid, "email");
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public void expandContactDetails(int cid) {
		DatabaseInsertion di = new DatabaseInsertion();
		ArrayList<Mobile> mobileList = di.fetchMobile(cid);
		System.out.println(PrintStatements.MOBILE_TAB);
		for (Mobile mobile : mobileList) {
			System.out.println(mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
		}

		ArrayList<Home> homeList = di.fetchHome(cid);
		System.out.println(PrintStatements.HOME_TAB);
		for (Home home : homeList) {
			System.out.println(home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
					+ home.getNumber());
		}

		ArrayList<Office> officeList = di.fetchOffice(cid);
		System.out.println(PrintStatements.OFFICE_TAB);
		for (Office office : officeList) {
			System.out.println(office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode() + "\t"
					+ office.getExtension() + "\t" + office.getNumber());
		}

		ArrayList<Email> emailList = di.fetchEmail(cid);
		System.out.println(PrintStatements.EMAIL_TAB);
		for (Email email : emailList) {
			System.out.println(email.getE_id() + "\t" + email.getEmail());
		}
	}

	public void createContact() {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();
		Contacts newContact = new Contacts();

		System.out.print(PrintStatements.ENTER_FIRST_NAME);
		newContact.setFirstName(sc.nextLine());
		System.out.print(PrintStatements.ENTER_LAST_NAME);
		newContact.setLastName(sc.nextLine());
		di.insertIntoContacts(newContact);

		System.out.print(PrintStatements.MOBILE_CHOICE);
		char ch = sc.next().charAt(0);
		sc.nextLine();
		int counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addMobileNumber(cid, counter);
			counter++;
			System.out.print(PrintStatements.ANOTHER_MOBILE);
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print(PrintStatements.HOME_CHOICE);
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addHomeNumber(cid, counter);
			counter++;
			System.out.print(PrintStatements.ANOTHER_HOME);
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print(PrintStatements.OFFICE_CHOICE);
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addOfficeNumber(cid, counter);
			counter++;
			System.out.print(PrintStatements.ANOTHER_OFFICE);
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print(PrintStatements.EMAIL_CHOICE);
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addEmailId(cid, counter);
			counter++;
			System.out.print(PrintStatements.ANOTHER_EMAIL);
			ch = sc.next().charAt(0);
			sc.nextLine();
		}
	}

	public void updateContact() {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(PrintStatements.ENTER_FIRST_NAME_TO_UPDATE);
		String search = sc.nextLine();
		ArrayList<Contacts> contactList = di.fetchContacts(search);
		if (contactList.size() == 0) {
			System.out.println(PrintStatements.NO_SUCH_CONTACT);
			return;
		}
		char reload;
		do {
			int ignoreFlag = displayContact(search);
			if (ignoreFlag == 0) {
				break;
			}
			System.out.print(PrintStatements.RELOAD_LIST);
			reload = sc.next().charAt(0);
			sc.nextLine();
		} while (reload == 'Y' || reload == 'y');
		System.out.print(PrintStatements.CHOOSE_CONTACT_ID);
		int cid = sc.nextInt();

		System.out.print(PrintStatements.CONTACT_UPDATE_OPTIONS);
		int option = sc.nextInt();
		sc.nextLine();
		switch (option) {
		case 1:
			System.out.print(PrintStatements.ENTER_UPDATED_FIRST_NAME);
			di.updateName("firstName", sc.nextLine(), cid);
			break;
		case 2:
			System.out.print(PrintStatements.ENTER_UPDATED_LAST_NAME);
			di.updateName("lastName", sc.nextLine(), cid);
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
			System.out.println(PrintStatements.INVALID_OPTION);
		}
	}

	public int displayContact(String name) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		int option;
		if (name == "") {
			System.out.print(PrintStatements.DISPLAY_CHOICE);
			option = sc.nextInt();
			sc.nextLine();
		} else {
			option = 0;
		}
		System.out.println(PrintStatements.CONTACT_TABLE);
		switch (option) {
		case 0:
			ArrayList<Contacts> contactList = di.fetchContacts(name);
			for (Contacts contact : contactList) {
				System.out
						.println(contact.getC_id() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		case 1:
			contactList = di.fetchContacts(0);
			for (Contacts contact : contactList) {
				System.out
						.println(contact.getC_id() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		case 2:
			contactList = di.fetchContacts(1);
			for (Contacts contact : contactList) {
				System.out
						.println(contact.getC_id() + "\t\t" + contact.getFirstName() + "\t\t" + contact.getLastName());
			}
			break;
		default:
			System.out.println(PrintStatements.INVALID_OPTION);
		}
		System.out.print(PrintStatements.EXPAND_CONTACT);
		int cid = sc.nextInt();
		sc.nextLine();
		if (cid == 0) {
			return 0;
		}
		expandContactDetails(cid);
		return 1;
	}

	public void displayMenu() {
		Scanner sc = new Scanner(System.in);
		char ch;
		do {
			System.out.print(PrintStatements.MENU);
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				createContact();
				break;
			case 2:
				updateContact();
				break;
			case 3:
				displayContact("");
				break;
			default:
				System.out.println(PrintStatements.INVALID_OPTION);
			}
			System.out.print(PrintStatements.CONTINUE);
			ch = sc.next().charAt(0);
			sc.nextLine();
		} while (ch == 'y' || ch == 'Y');
	}
}
