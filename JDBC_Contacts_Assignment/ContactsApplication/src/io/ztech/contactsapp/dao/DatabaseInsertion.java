package io.ztech.contactsapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.ztech.contactsapp.beans.Contacts;
import io.ztech.contactsapp.beans.Email;
import io.ztech.contactsapp.beans.Home;
import io.ztech.contactsapp.beans.Mobile;
import io.ztech.contactsapp.beans.Office;
import io.ztech.contactsapp.constants.Queries;
import io.ztech.contactsapp.dbutils.DbConnectivity;

public class DatabaseInsertion {
	
	public void insertIntoContacts(Contacts newContact) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_INTO_CONTACTS);
			ps.setString(1, newContact.getFirstName());
			ps.setString(2, newContact.getLastName());
			ps.execute();
		} catch (SQLException e) {
			System.out.print("Exception caught at insertIntoContacts(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void insertIntoMobile(Mobile newMobile) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_INTO_MOBILE);
			ps.setInt(1, newMobile.getC_id());
			ps.setInt(2, newMobile.getM_id());
			ps.setString(3, newMobile.getCountryCode());
			ps.setString(4, newMobile.getNumber());
			ps.execute();
		} catch (SQLException e) {
			System.out.print("Exception caught at insertIntoMobile(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void insertIntoHome(Home newHome) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_INTO_HOME);
			ps.setInt(1, newHome.getC_id());
			ps.setInt(2, newHome.getH_id());
			ps.setString(3, newHome.getCountryCode());
			ps.setString(4, newHome.getAreaCode());
			ps.setString(5, newHome.getNumber());
			ps.execute();
		} catch (SQLException e) {
			System.out.print("Exception caught at insertIntoHome(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void insertIntoOffice(Office newOffice) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_INTO_OFFICE);
			ps.setInt(1, newOffice.getC_id());
			ps.setInt(2, newOffice.getO_id());
			ps.setString(3, newOffice.getCountryCode());
			ps.setString(4, newOffice.getAreaCode());
			ps.setString(5, newOffice.getExtension());
			ps.setString(6, newOffice.getNumber());
			ps.execute();
		} catch (SQLException e) {
			System.out.print("Exception caught at insertIntoOffice(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void insertIntoEmail(Email newEmail) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_INTO_EMAIL);
			ps.setInt(1, newEmail.getC_id());
			ps.setInt(2, newEmail.getE_id());
			ps.setString(3, newEmail.getEmail());
			ps.execute();
		} catch (SQLException e) {
			System.out.print("Exception caught at insertIntoEmail(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public ArrayList<Contacts> fetchContacts(String fname) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Contacts> contactList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SPECIFIED_CONTACT);
			ps.setString(1, fname);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contacts contact = new Contacts();
				contact.setC_id(rs.getInt("c_id"));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchContacts(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return contactList;		
	}
	
	public ArrayList<Contacts> fetchContacts(int flag) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Contacts> contactList = new ArrayList<>();
		
		try {
			if (flag == 0) {
				ps = con.prepareStatement(Queries.FETCH_ALL_CONTACTS);
			} else {
				ps = con.prepareStatement(Queries.FETCH_ALL_CONTACTS_DESC);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Contacts contact = new Contacts();
				contact.setC_id(rs.getInt("c_id"));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchContacts(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return contactList;		
	}
	
	public ArrayList<Mobile> fetchMobile(int cid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Mobile> mobileList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SPECIFIED_MOBILE);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Mobile mobile = new Mobile();
				mobile.setC_id(rs.getInt("c_id"));
				mobile.setM_id(rs.getInt("m_id"));
				mobile.setCountryCode(rs.getString("countryCode"));
				mobile.setNumber(rs.getString("m_number"));
				mobileList.add(mobile);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchMobile(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return mobileList;
	}
	
	public ArrayList<Home> fetchHome(int cid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Home> homeList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SPECIFIED_HOME);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Home home = new Home();
				home.setC_id(rs.getInt("c_id"));
				home.setH_id(rs.getInt("h_id"));
				home.setCountryCode(rs.getString("countryCode"));
				home.setAreaCode(rs.getString("areaCode"));
				home.setNumber(rs.getString("h_number"));
				homeList.add(home);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchHome(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return homeList;
	}
	
	public ArrayList<Office> fetchOffice(int cid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Office> officeList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SPECIFIED_OFFICE);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Office office = new Office();
				office.setC_id(rs.getInt("c_id"));
				office.setO_id(rs.getInt("o_id"));
				office.setCountryCode(rs.getString("countryCode"));
				office.setAreaCode(rs.getString("areaCode"));
				office.setExtension(rs.getString("extension"));
				office.setNumber(rs.getString("o_number"));
				officeList.add(office);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchOffice(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return officeList;
	}
	
	public ArrayList<Email> fetchEmail(int cid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		ArrayList<Email> emailList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SPECIFIED_EMAIL);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Email email = new Email();
				email.setC_id(rs.getInt("c_id"));
				email.setE_id(rs.getInt("e_id"));
				email.setEmail(rs.getString("email"));
				emailList.add(email);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchEmail(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return emailList;
	}
		
	public int fetchRecentContactId() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		int cid = -1;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_CID);
			rs = ps.executeQuery();
			rs.next();
			cid = rs.getInt("c_id");
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchRecentContactId(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return cid;
	}
	
	public int fetchRecentId(int cid, String type) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		int id=-1;
		
		try {
			if (type.equals("mobile")) {
				ps = con.prepareStatement(Queries.FETCH_RECENT_MID);
			} else if (type.equals("home")) {
				ps = con.prepareStatement(Queries.FETCH_RECENT_HID);
			} else if (type.equals("office")) {
				ps = con.prepareStatement(Queries.FETCH_RECENT_OID);
			} else {
				ps = con.prepareStatement(Queries.FETCH_RECENT_EID);
			}
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at fetchRecentId(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, rs, ps);
		}
		return id;
	}
	
	public void deleteRow(int cid, int id, String type) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			if (type.equals("mobile")) {
				ps = con.prepareStatement(Queries.DELETE_MOBILE_ROW);
			} else if (type.equals("home")) {
				ps = con.prepareStatement(Queries.DELETE_HOME_ROW);
			} else if (type.equals("office")) {
				ps = con.prepareStatement(Queries.DELETE_OFFICE_ROW);
			} else {
				ps = con.prepareStatement(Queries.DELETE_EMAIL_ROW);
			}
			ps.setInt(1, cid);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at deleteRow(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateName(String nameType, String newName, int cid) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			if (nameType.equals("firstName")) {
				ps = con.prepareStatement(Queries.UPDATE_FIRST_NAME);
			} else {
				ps = con.prepareStatement(Queries.UPDATE_LAST_NAME);
			}
			ps.setString(1, newName);
			ps.setInt(2, cid);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateName(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateNumber(int cid, int id, String newNumber, String type) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			if (type.equals("mobile")) {
				ps = con.prepareStatement(Queries.UPDATE_MOBILE_NUMBER);
			} else if (type.equals("home")) {
				ps = con.prepareStatement(Queries.UPDATE_HOME_NUMBER);
			} else {
				ps = con.prepareStatement(Queries.UPDATE_OFFICE_NUMBER);
			}
			ps.setString(1, newNumber);
			ps.setInt(2, cid);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateNumber(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateCountry(int cid, int id, String newCountry, String type) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			if (type.equals("mobile")) {
				ps = con.prepareStatement(Queries.UPDATE_MOBILE_COUNTRY_CODE);
			} else if (type.equals("home")) {
				ps = con.prepareStatement(Queries.UPDATE_HOME_COUNTRY_CODE);
			} else {
				ps = con.prepareStatement(Queries.UPDATE_OFFICE_COUNTRY_CODE);
			}
			ps.setString(1, newCountry);
			ps.setInt(2, cid);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateCountry(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateArea(int cid, int id, String newArea, String type) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			if (type.equals("home")) {
				ps = con.prepareStatement(Queries.UPDATE_HOME_AREA_CODE);
			} else {
				ps = con.prepareStatement(Queries.UPDATE_OFFICE_AREA_CODE);
			}
			ps.setString(1, newArea);
			ps.setInt(2, cid);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateArea(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateExtension(int cid, int oid, String newExtension) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_OFFICE_EXTENSION);
			ps.setString(1, newExtension);
			ps.setInt(2, cid);
			ps.setInt(3, oid);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateExtension(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
	
	public void updateEmail(int cid, int eid, String newEmail) {
		PreparedStatement ps = null;
		DbConnectivity db = new DbConnectivity();
		Connection con = db.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_EMAIL);
			ps.setString(1, newEmail);
			ps.setInt(2, cid);
			ps.setInt(3, eid);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Exception caught at updateEmail(): ");
			e.printStackTrace();
		} finally {
			db.closeConnection(con, null, ps);
		}
	}
}
