package io.ztech.contactsapp.service;

import java.util.ArrayList;
import java.util.Scanner;

import io.ztech.contactsapp.beans.Contacts;
import io.ztech.contactsapp.beans.Email;
import io.ztech.contactsapp.beans.Home;
import io.ztech.contactsapp.beans.Mobile;
import io.ztech.contactsapp.beans.Office;
import io.ztech.contactsapp.dao.DatabaseInsertion;

public class ContactManagement {

	public static void addMobileNumber(int cid, int mid) {
		Scanner sc = new Scanner(System.in);
		Mobile newMobile = new Mobile();

		System.out.print("Enter country code: ");
		newMobile.setCountryCode(sc.nextLine());
		System.out.print("Enter mobile number: ");
		newMobile.setNumber(sc.nextLine());
		newMobile.setC_id(cid);
		newMobile.setM_id(mid);

		(new DatabaseInsertion()).insertIntoMobile(newMobile);
	}

	public static void addHomeNumber(int cid, int hid) {
		Scanner sc = new Scanner(System.in);
		Home newHome = new Home();

		System.out.print("Enter country code: ");
		newHome.setCountryCode(sc.nextLine());
		System.out.print("Enter area code: ");
		newHome.setAreaCode(sc.nextLine());
		System.out.print("Enter home number: ");
		newHome.setNumber(sc.nextLine());
		newHome.setC_id(cid);
		newHome.setH_id(hid);

		(new DatabaseInsertion()).insertIntoHome(newHome);
	}

	public static void addOfficeNumber(int cid, int oid) {
		Scanner sc = new Scanner(System.in);
		Office newOffice = new Office();

		System.out.print("\nPress 1 for landline\nPress 2 for mobile: ");
		int op = sc.nextInt();
		sc.nextLine();
		System.out.print("\nEnter country code: ");
		newOffice.setCountryCode(sc.nextLine());
		newOffice.setC_id(cid);
		newOffice.setO_id(oid);
		switch (op) {
		case 1:
			System.out.print("Enter area code: ");
			newOffice.setAreaCode(sc.nextLine());
			System.out.print("Enter extension code: ");
			newOffice.setExtension(sc.nextLine());
			System.out.print("Enter office landline number: ");
			newOffice.setNumber(sc.nextLine());
			break;
		case 2:
			System.out.print("Enter office mobile number: ");
			newOffice.setNumber(sc.nextLine());
			break;
		default:
			System.out.println("Invalid option entered! Try again");
			addOfficeNumber(cid, oid);
			return;
		}

		(new DatabaseInsertion()).insertIntoOffice(newOffice);
	}

	public static void addEmailId(int cid, int eid) {
		Scanner sc = new Scanner(System.in);
		Email newEmail = new Email();

		System.out.print("Enter the email id: ");
		newEmail.setEmail(sc.nextLine());
		newEmail.setC_id(cid);
		newEmail.setE_id(eid);

		(new DatabaseInsertion()).insertIntoEmail(newEmail);
	}

	public void updateMobile(int cid, int mid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print("\nWhat do you want to alter?\n1. Country code\n2. Mobile number\nEnter your choice: ");
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\nEnter the new country code: ");
			di.updateCountry(cid, mid, sc.nextLine(), "mobile");
			break;
		case 2:
			System.out.print("\nEnter the new number: ");
			di.updateNumber(cid, mid, sc.nextLine(), "mobile");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void updateHome(int cid, int hid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nWhat do you want to alter?\n1. Country code\n2. Area Code\n3. Home number\nEnter your choice: ");
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\nEnter the new country code: ");
			di.updateCountry(cid, hid, sc.nextLine(), "home");
			break;
		case 2:
			System.out.print("\nEnter the new country code: ");
			di.updateArea(cid, hid, sc.nextLine(), "home");
			break;
		case 3:
			System.out.print("\nEnter the new number: ");
			di.updateNumber(cid, hid, sc.nextLine(), "home");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void updateOffice(int cid, int oid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nWhat do you want to alter?\n1. Country code\n2. Area Code\n3. Extension\n4. Office number\nEnter your choice: ");
		int selection = sc.nextInt();
		sc.nextLine();
		switch (selection) {
		case 1:
			System.out.print("\nEnter the new country code: ");
			di.updateCountry(cid, oid, sc.nextLine(), "office");
			break;
		case 2:
			System.out.print("\nEnter the new area code: ");
			di.updateArea(cid, oid, sc.nextLine(), "office");
			break;
		case 3:
			System.out.print("\nEnter the new extension code: ");
			di.updateExtension(cid, oid, sc.nextLine());
			break;
		case 4:
			System.out.print("\nEnter the new number: ");
			di.updateNumber(cid, oid, sc.nextLine(), "office");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void editMobileNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ");
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
				System.out.println("\nThere are no mobile numbers associated to this contact!");
				return;
			}
			System.out.print("\nM_ID\tCountry\tMobile Number\n------------------------------");
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print("\nWhich number do you wish to update? Enter m_id: ");
			mid = sc.nextInt();
			updateMobile(cid, mid);
			break;
		case 'c':
			mobileList = di.fetchMobile(cid);
			if (mobileList.size() == 0) {
				System.out.println("\nThere are no mobile numbers associated to this contact!");
				return;
			}
			System.out.print("\nM_ID\tCountry\tMobile Number\n------------------------------");
			for (Mobile mobile : mobileList) {
				System.out.print("\n" + mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
			}
			System.out.print("\nWhich number do you wish to delete? Enter m_id: ");
			mid = sc.nextInt();
			di.deleteRow(cid, mid, "mobile");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void editHomeNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ");
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
				System.out.println("\nThere are no home numbers associated to this contact!");
				return;
			}
			System.out.print("\nH_ID\tCountry\tArea\tHome Number\n-------------------------------------");
			for (Home home : homeList) {
				System.out.print("\n" + home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print("\nWhich number do you wish to update? Enter h_id: ");
			hid = sc.nextInt();
			updateHome(cid, hid);
			break;
		case 'c':
			homeList = di.fetchHome(cid);
			if (homeList.size() == 0) {
				System.out.println("\nThere are no home numbers associated to this contact!");
				return;
			}
			System.out.print("\nH_ID\tCountry\tArea\tHome Number\n-------------------------------------");
			for (Home home : homeList) {
				System.out.print("\n" + home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
						+ home.getNumber());
			}
			System.out.print("\nWhich number do you wish to delete? Enter h_id: ");
			hid = sc.nextInt();
			di.deleteRow(cid, hid, "home");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void editOfficeNumber(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ");
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
				System.out.println("\nThere are no office numbers associated to this contact!");
				return;
			}
			System.out.print("\nO_ID\tCountry\tArea\tExtension\tOffice Number\n-----------------------------------");
			for (Office office : officeList) {
				System.out.print("\n" + office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print("\nWhich number do you wish to update? Enter o_id: ");
			oid = sc.nextInt();
			updateOffice(cid, oid);
			break;
		case 'c':
			officeList = di.fetchOffice(cid);
			if (officeList.size() == 0) {
				System.out.println("\nThere are no office numbers associated to this contact!");
				return;
			}
			System.out.print("\nO_ID\tCountry\tArea\tExtension\tOffice Number\n-----------------------------------");
			for (Office office : officeList) {
				System.out.print("\n" + office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode()
						+ "\t" + office.getExtension() + "\t" + office.getNumber());
			}
			System.out.print("\nWhich number do you wish to delete? Enter o_id: ");
			oid = sc.nextInt();
			di.deleteRow(cid, oid, "office");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void editEmailId(int cid) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print(
				"\nDo you wish to:\na) Add new email id\nb) Change existing email id\nc) Delete an email id\nEnter your choice: ");
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
				System.out.println("\nThere are no email IDs associated to this contact!");
				return;
			}
			System.out.print("\nE_ID\tEmail ID\n---------------------------");
			for (Email email : emailList) {
				System.out.print("\n" + email.getE_id() + "\t" + email.getEmail());
			}
			System.out.print("\nWhich email address do you wish to update? Enter e_id: ");
			eid = sc.nextInt();
			System.out.println("\nEnter the new email address: ");
			di.updateEmail(cid, eid, sc.nextLine());
			break;
		case 'c':
			emailList = di.fetchEmail(cid);
			if (emailList.size() == 0) {
				System.out.println("\nThere are no email IDs associated to this contact!");
				return;
			}
			System.out.print("\nE_ID\tEmail ID\n---------------------------");
			for (Email email : emailList) {
				System.out.print("\n" + email.getE_id() + "\t" + email.getEmail());
			}
			System.out.print("\nWhich email address do you wish to delete? Enter e_id: ");
			eid = sc.nextInt();
			di.deleteRow(cid, eid, "email");
			break;
		default:
			System.out.println("Invalid option entered!");
		}
	}

	public void expandContactDetails(int cid) {
		DatabaseInsertion di = new DatabaseInsertion();
		ArrayList<Mobile> mobileList = di.fetchMobile(cid);
		System.out.println("\nMob_ID\tCountry\tMobile Number\n-------------------------------");
		for (Mobile mobile : mobileList) {
			System.out.println(mobile.getM_id() + "\t" + mobile.getCountryCode() + "\t" + mobile.getNumber());
		}

		ArrayList<Home> homeList = di.fetchHome(cid);
		System.out.println("\nHom_ID\tCountry\tArea\tHome Number\n-------------------------------------");
		for (Home home : homeList) {
			System.out.println(home.getH_id() + "\t" + home.getCountryCode() + "\t" + home.getAreaCode() + "\t"
					+ home.getNumber());
		}

		ArrayList<Office> officeList = di.fetchOffice(cid);
		System.out.println(
				"\nOff_ID  Country\tArea\tExt\tOffice Number\n-------------------------------------------------");
		for (Office office : officeList) {
			System.out.println(office.getO_id() + "\t" + office.getCountryCode() + "\t" + office.getAreaCode() + "\t"
					+ office.getExtension() + "\t" + office.getNumber());
		}

		ArrayList<Email> emailList = di.fetchEmail(cid);
		System.out.println("\nE_id\tEmail\n-------------------------------");
		for (Email email : emailList) {
			System.out.println(email.getE_id() + "\t" + email.getEmail());
		}
	}

	public void createContact() {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();
		Contacts newContact = new Contacts();

		System.out.print("\nEnter first name: ");
		newContact.setFirstName(sc.nextLine());
		System.out.print("Enter last name: ");
		newContact.setLastName(sc.nextLine());
		di.insertIntoContacts(newContact);

		System.out.print("\nDo you wish to add your mobile number (y/n)? ");
		char ch = sc.next().charAt(0);
		sc.nextLine();
		int counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addMobileNumber(cid, counter);
			counter++;
			System.out.print("\nDo you wish to add another mobile number (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print("\nDo you wish to add a home number (y/n)? ");
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addHomeNumber(cid, counter);
			counter++;
			System.out.print("\nDo you wish to add another home number (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print("\nDo you wish to add an office number (y/n)? ");
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addOfficeNumber(cid, counter);
			counter++;
			System.out.print("\nDo you wish to add another office number (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		}

		System.out.print("\nDo you wish to enter your email id (y/n)? ");
		ch = sc.next().charAt(0);
		sc.nextLine();
		counter = 1;
		while (ch == 'y' || ch == 'Y') {
			int cid = di.fetchRecentContactId();
			addEmailId(cid, counter);
			counter++;
			System.out.print("\nDo you wish to add another email (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		}
	}

	public void updateContact() {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		System.out.print("\nEnter the first name of the contact to update: ");
		String search = sc.nextLine();
		ArrayList<Contacts> contactList = di.fetchContacts(search);
		if (contactList.size() == 0) {
			System.out.println("There is no such contact present!");
			return;
		}
		char reload;
		do {
			displayContact(search);
			System.out.print("\nDo you wish to reload list (y/n)?: ");
			reload = sc.next().charAt(0);
			sc.nextLine();
		} while (reload == 'Y' || reload == 'y');
		System.out.print("\nChoose the appropriate contact id to alter: ");
		int cid = sc.nextInt();

		System.out.print(
				"\n1. First Name\n2. Last Name\n3. Mobile Number\n4. Home Number\n5. Office Number\n6. Email Id\nWhich field do you wish to update? ");
		int option = sc.nextInt();
		sc.nextLine();
		switch (option) {
		case 1:
			System.out.print("\nEnter the updated first name: ");
			di.updateName("firstName", sc.nextLine(), cid);
			break;
		case 2:
			System.out.print("\nEnter the updated last name: ");
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
			System.out.println("Invalid option entered!");
		}
	}

	public void displayContact(String name) {
		Scanner sc = new Scanner(System.in);
		DatabaseInsertion di = new DatabaseInsertion();

		int option;
		if (name == "") {
			System.out.print("\n1. Display all contacts\n2. Display all contacts (By descending)\nYour choice: ");
			option = sc.nextInt();
			sc.nextLine();
		} else {
			option = 0;
		}
		System.out.println("\nContact ID\tFirst Name\tLast Name\n------------------------------------------");
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
			System.out.println("Invalid option entered!");
		}
		System.out.print("Enter contact id to expand (or press 0 to ignore): ");
		int cid = sc.nextInt();
		sc.nextLine();
		if (cid == 0) {
			return;
		}
		expandContactDetails(cid);
	}

	public void displayMenu() {
		Scanner sc = new Scanner(System.in);
		char ch;
		do {
			System.out.print("\nMenu:\n1. Create contact\n2. Edit Contact\n3. View Contact\nEnter your choice: ");
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
				System.out.println("Invalid option entered!");
			}
			System.out.print("Do you wish to continue (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		} while (ch == 'y' || ch == 'Y');
	}
}
