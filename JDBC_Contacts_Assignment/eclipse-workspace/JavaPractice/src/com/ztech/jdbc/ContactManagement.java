package com.ztech.jdbc;

import java.sql.*;
import java.util.Scanner;

public class ContactManagement {

	// Class.forName("com.mysql.cj.jdbc.Driver");
	static Connection con;

	public static void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Contact_Management", "root", "Ztech@123");
		} catch (Exception e) {
			System.out.println("Connection exception: " + e);
		}
	}

	public static void addNumber(int cid, int nid, String type, Contact newContact, Statement stm) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter country code: ");
		String countryCode = sc.nextLine();
		newContact.setCountryCode(countryCode);
		System.out.print("Enter " + type + " number: ");
		String number = sc.nextLine();
		if (type.equals("mobile")) {
			newContact.setMobileNumber(number);
			try {
				stm.execute("insert into mobile values (" + cid + "," + nid + ",'" + newContact.getCountryCode() + "','"
						+ newContact.getMobileNumber() + "')");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("home")) {
			newContact.setHomeNumber(number);
			try {
				stm.execute("update home set countryCode = '" + newContact.getCountryCode() + "', h_number = '"
						+ newContact.getHomeNumber() + "' where c_id=" + cid + " and h_id=" + nid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			newContact.setOfficeNumber(number);
			try {
				stm.execute("update office set countryCode = '" + newContact.getCountryCode() + "', o_number = '"
						+ newContact.getOfficeNumber() + "' where c_id=" + cid + " and o_id=" + nid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createContact(Contact newContact) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.print("\nEnter first name: ");
			String firstName = sc.nextLine();
			System.out.print("Enter last name: ");
			String lastName = sc.nextLine();
			newContact.setName(firstName, lastName);
			{
				Statement stmt = con.createStatement();
				stmt.execute("insert into contacts (firstName, lastName) values ('" + newContact.getFirstName() + "', '"
						+ newContact.getLastName() + "')");
				stmt.close();
			}

			System.out.print("\nDo you wish to add your mobile number (y/n)? ");
			char ch = sc.next().charAt(0);
			sc.nextLine();
			int counter = 1;
			while (ch == 'y' || ch == 'Y') {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select c_id from contacts order by c_id desc limit 1");
				rs.next();
				addNumber(rs.getInt(1), counter, "mobile", newContact, stmt);
				stmt.close();
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
				System.out.print("Enter the area code: ");
				String areaCode = sc.nextLine();
				newContact.setAreaCode(areaCode);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select c_id from contacts order by c_id desc limit 1");
				rs.next();
				int cid = rs.getInt(1);
				stmt.execute("insert into home (c_id, h_id, areaCode) values (" + cid + "," + counter + ", '" + areaCode
						+ "')");
				addNumber(cid, counter, "home", newContact, stmt);
				stmt.close();
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
				System.out.println("\nPress 1 for landline\nPress 2 for mobile");
				int op = sc.nextInt();
				sc.nextLine();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select c_id from contacts order by c_id desc limit 1");
				rs.next();
				int cid = rs.getInt(1);
				switch (op) {
				case 1:
					System.out.print("Enter the area code: ");
					String areaCode = sc.nextLine();
					newContact.setAreaCode(areaCode);
					System.out.print("Enter the extension number: ");
					String extension = sc.nextLine();
					newContact.setExtension(extension);
					stmt.execute("insert into office (c_id, o_id, areaCode, extension) values (" + cid + "," + counter
							+ ",'" + areaCode + "','" + extension + "')");
					addNumber(cid, counter, "office", newContact, stmt);
					break;
				case 2:
					stmt.execute("insert into office (c_id, o_id) values (" + cid + "," + counter + ")");
					addNumber(cid, counter, "office", newContact, stmt);
					break;
				default:
					System.out.println("Invalid option entered! Try again");
					continue;
				}
				stmt.close();
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
				System.out.print("Enter the email id: ");
				String email = sc.nextLine();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select c_id from contacts order by c_id desc limit 1");
				rs.next();
				stmt.execute("insert into email values (" + rs.getInt(1) + "," + counter + ",'" + email + "')");
				stmt.close();
				counter++;
				System.out.print("\nDo you wish to add another email (y/n)? ");
				ch = sc.next().charAt(0);
				sc.nextLine();
			}
		} catch (Exception e) {
			System.out.println("Exception caught! " + e);
		}
	}

	public static void displayContact(String name) {
		Scanner sc = new Scanner(System.in);
		int option;
		if (name == "") {
			System.out.print("\n1. Display all contacts\n2. Display all contacts (By descending)\nYour choice: ");
			option = sc.nextInt();
			sc.nextLine();
		} else {
			option = 0;
		}
		try {
			Statement stmt = con.createStatement();
			System.out.println("\nContact ID\tFirst Name\tLast Name\n------------------------------------------");
			switch (option) {
			case 0:
				ResultSet rs = stmt.executeQuery("select * from contacts where firstName ='" + name + "' order by c_id");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3));
				}
				break;
			case 1:
				rs = stmt.executeQuery("select * from contacts order by firstName");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3));
				}
				break;
			case 2:
				rs = stmt.executeQuery("select * from contacts order by firstName desc");
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3));
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
			ResultSet rs = stmt.executeQuery("select * from mobile where c_id =" + cid);
			System.out.println("\nMob_ID\tCountry\tMobile Number\n-------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			rs = stmt.executeQuery("select * from home where c_id =" + cid);
			System.out.println("\nHom_ID\tCountry\tArea\tHome Number\n-------------------------------------");
			while (rs.next()) {
				System.out.println(
						rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
			}
			rs = stmt.executeQuery("select * from office where c_id =" + cid);
			System.out.println(
					"\nOff_ID  Country\tArea\tExt\tOffice Number\n-------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t"
						+ rs.getString(5) + "\t" + rs.getString(6));
			}
			rs = stmt.executeQuery("select * from email where c_id =" + cid);
			System.out.println("\nE_id\tEmail\n-------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(2) + "\t" + rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println("Exception caught: " + e);
		}
	}

	public static void updateContact(Contact newContact) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the first name of the contact to update: ");
		String search = sc.nextLine();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from contacts where firstName='" + search + "'");
			int cid;
			if (!rs.next()) {
				System.out.println("There is no such contact present!");
				return;
			} else {
//				System.out.print("\nC_ID\tFirst Name\tLast Name\n-----------------------------------");
//				rs = stmt.executeQuery("select * from contacts where firstName='" + search + "'");
//				while (rs.next()) {
//					System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3));
//				}
				char reload;
				do {
					displayContact(search);
					System.out.print("\nDo you wish to reload list (y/n)?: ");
					reload = sc.next().charAt(0);
					sc.nextLine();
				} while (reload == 'Y' || reload == 'y');
				System.out.print("\nChoose the appropriate contact id to alter: ");
				cid = sc.nextInt();
			}
			System.out.print(
					"\n1. First Name\n2. Last Name\n3. Mobile Number\n4. Home Number\n5. Office Number\n6. Email Id\nWhich field do you wish to update? ");
			int option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			case 1:
				System.out.print("\nEnter the updated first name: ");
				String newFirstName = sc.nextLine();
				stmt.execute("update contacts set firstName='" + newFirstName + "' where firstName='" + search + "'");
				break;
			case 2:
				System.out.print("\nEnter the updated last name: ");
				stmt.execute("update contacts set lastName='" + sc.nextLine() + "' where firstName='" + search + "'");
				break;
			case 3:
				System.out.print(
						"\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ");
				char op = sc.next().charAt(0);
				sc.nextLine();
				switch (op) {
				case 'a':
					rs = stmt.executeQuery("select m_id from mobile where c_id=" + cid + " order by m_id desc limit 1");
					rs.next();
					addNumber(cid, rs.getInt(1) + 1, "mobile", newContact, stmt);
					break;
				case 'b':
					rs = stmt.executeQuery("select m_id, countryCode, m_number from mobile where c_id=" + cid);
					int flag = 0;
					System.out.print("\nM_ID\tCountry\tMobile Number\n------------------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
					}
					if (flag == 0) {
						System.out.println("\nThere are no mobile numbers associated to this contact!");
						return;
					}
					System.out.print("\nWhich number do you wish to change? Enter m_id: ");
					int mid = sc.nextInt();
					System.out.print(
							"\nWhat do you want to alter?\n1. Country code\n2. Mobile number\nEnter your choice: ");
					int selection = sc.nextInt();
					sc.nextLine();
					switch (selection) {
					case 1:
						System.out.print("\nEnter the new country code: ");
						String newCountryCode = sc.nextLine();
						stmt.execute("update mobile set countryCode = " + newCountryCode + " where c_id=" + cid
								+ " and m_id=" + mid);
						break;
					case 2:
						System.out.print("\nEnter the new number: ");
						String newNumber = sc.nextLine();
						stmt.execute("update mobile set m_number= '" + newNumber + "' where c_id=" + cid + " and m_id="
								+ mid);
						break;
					default:
						System.out.println("Invalid option entered!");
					}
					break;
				case 'c':
					rs = stmt.executeQuery("select m_id, countryCode, m_number from mobile where c_id=" + cid);
					flag = 0;
					System.out.print("\nM_ID\tCountry\tMobile Number\n------------------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
					}
					if (flag == 0) {
						System.out.println("\nThere are no mobile numbers associated to this contact!");
						return;
					}
					System.out.print("\nWhich number do you wish to delete? Enter m_id: ");
					mid = sc.nextInt();
					stmt.execute("delete from mobile where c_id=" + cid + " and m_id=" + mid);
					break;
				default:
					System.out.println("Invalid option entered!");
				}
				break;
			case 4:
				System.out.print(
						"\nDo you wish to:\na) Add new number\nb) Change existing number\nc) Delete a number\nEnter your choice: ");
				op = sc.next().charAt(0);
				sc.nextLine();
				switch (op) {
				case 'a':
					rs = stmt.executeQuery("select h_id from home where c_id=" + cid + " order by h_id desc limit 1");
					rs.next();
					int hid = rs.getInt(1);
					System.out.print("\nEnter the area code: ");
					String areaCode = sc.nextLine();
					newContact.setAreaCode(areaCode);
					stmt.execute("insert into home (c_id, h_id, areaCode) values (" + cid + "," + (hid + 1) + ", '"
							+ areaCode + "')");
					addNumber(cid, hid + 1, "home", newContact, stmt);
					break;
				case 'b':
					rs = stmt.executeQuery("select h_id, countryCode, areaCode, h_number from home where c_id=" + cid);
					int flag = 0;
					System.out.print("\nH_ID\tCountry\tArea\tHome Number\n---------------------------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4));
					}
					if (flag == 0) {
						System.out.println("\nThere are no home numbers associated to this contact!");
						return;
					}
					System.out.print("\nWhich number do you wish to change? Enter h_id: ");
					hid = sc.nextInt();
					System.out.print(
							"\nWhat do you want to alter?\n1. Country code\n2. Area Code\n3. Home number\nEnter your choice: ");
					int selection = sc.nextInt();
					sc.nextLine();
					switch (selection) {
					case 1:
						System.out.print("\nEnter the new country code: ");
						String newCountryCode = sc.nextLine();
						stmt.execute("update home set countryCode = " + newCountryCode + " where c_id=" + cid
								+ " and h_id=" + hid);
						break;
					case 2:
						System.out.print("\nEnter the new area code: ");
						String newAreaCode = sc.nextLine();
						stmt.execute("update home set areaCode = " + newAreaCode + " where c_id=" + cid + " and h_id="
								+ hid);
						break;
					case 3:
						System.out.print("\nEnter the new number: ");
						String newNumber = sc.nextLine();
						stmt.execute(
								"update home set h_number= '" + newNumber + "' where c_id=" + cid + " and h_id=" + hid);
						break;
					default:
						System.out.println("Invalid option entered!");
					}
					break;
				case 'c':
					rs = stmt.executeQuery("select h_id, countryCode, areaCode, h_number from home where c_id=" + cid);
					flag = 0;
					System.out.print("\nH_ID\tCountry\tArea\tHome Number\n---------------------------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4));
					}
					if (flag == 0) {
						System.out.println("\nThere are no home numbers associated to this contact!");
						return;
					}
					System.out.print("\nWhich number do you wish to delete? Enter h_id: ");
					hid = sc.nextInt();
					stmt.execute("delete from home where c_id=" + cid + " and h_id=" + hid);
					break;
				default:
					System.out.println("Invalid option entered!");
				}
				break;

			case 5:
				break;
			case 6:
				System.out.print(
						"\nDo you wish to:\na) Add new email id\nb) Change existing email id\nc) Delete an email id\nEnter your choice: ");
				op = sc.next().charAt(0);
				sc.nextLine();
				switch (op) {
				case 'a':
					rs = stmt.executeQuery("select e_id from email where c_id=" + cid + " order by e_id desc limit 1");
					rs.next();
					System.out.print("\nEnter new email id: ");
					String newEmail = sc.nextLine();
					stmt.execute("insert into email values (" + cid + ", " + (rs.getInt(1) + 1) + ", '" + newEmail + "')");
					break;
				case 'b':
					rs = stmt.executeQuery("select e_id, email from email where c_id=" + cid);
					int flag = 0;
					System.out.print("\nE_ID\tEmail\n-----------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2));
					}
					if (flag == 0) {
						System.out.println("\nThere are no email ids associated to this contact!");
						return;
					}
					System.out.print("\nWhich email do you wish to change? Enter e_id: ");
					int eid = sc.nextInt();
					sc.nextLine();
					System.out.print("\nEnter the new email ID: ");
					newEmail = sc.nextLine();
					stmt.execute("update email set email = '" + newEmail + "' where c_id=" + cid
							+ " and e_id=" + eid);
					break;
				case 'c':
					rs = stmt.executeQuery("select e_id, email from email where c_id=" + cid);
					flag = 0;
					System.out.print("\nE_ID\tEmail\n-----------------------");
					while (rs.next()) {
						flag++;
						System.out.print("\n" + rs.getInt(1) + "\t" + rs.getString(2));
					}
					if (flag == 0) {
						System.out.println("\nThere are no email ids associated to this contact!");
						return;
					}
					System.out.print("\nWhich email do you wish to delete? Enter e_id: ");
					eid = sc.nextInt();
					stmt.execute("delete from email where c_id=" + cid + " and e_id=" + eid);
					break;
				default:
					System.out.println("Invalid option entered!");
				}
				break;
			default:
				System.out.println("Invalid option entered!");
			}
		} catch (Exception e) {
			System.out.println("Exception caught at updateContact: " + e);
		}

	}

	public static void main(String[] args) {
		Contact newContact = new Contact();
		Scanner sc = new Scanner(System.in);
		connect();
		char ch;
		do {
			System.out.print("\nMenu:\n1. Create contact\n2. View Contact\n3. Edit Contact\nEnter your choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				createContact(newContact);
				break;
			case 2:
				displayContact("");
				break;
			case 3:
				updateContact(newContact);
				break;
			default:
				System.out.println("Invalid option entered!");
			}
			System.out.print("Do you wish to continue (y/n)? ");
			ch = sc.next().charAt(0);
			sc.nextLine();
		} while (ch == 'y' || ch == 'Y');

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}