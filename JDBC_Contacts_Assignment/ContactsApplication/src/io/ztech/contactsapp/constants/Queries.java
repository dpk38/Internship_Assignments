package io.ztech.contactsapp.constants;

public class Queries {
	public static final String INSERT_INTO_CONTACTS = "insert into contacts (firstName, lastName) values (?, ?)";
	public static final String INSERT_INTO_MOBILE = "insert into mobile values (?, ?, ?, ?)";
	public static final String INSERT_INTO_HOME= "insert into home values (?, ?, ?, ?, ?)";
	public static final String INSERT_INTO_OFFICE = "insert into office values (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_INTO_EMAIL = "insert into email values (?, ?, ?)";
	
	public static final String FETCH_RECENT_CID = "select c_id from contacts order by c_id desc limit 1";
	public static final String FETCH_RECENT_MID = "select m_id from mobile where c_id=? order by m_id desc limit 1";
	public static final String FETCH_RECENT_HID = "select h_id from home where c_id=? order by h_id desc limit 1";
	public static final String FETCH_RECENT_OID = "select o_id from office where c_id=? order by o_id desc limit 1";
	public static final String FETCH_RECENT_EID = "select e_id from email where c_id=? order by e_id desc limit 1";
	public static final String FETCH_ALL_CONTACTS = "select * from contacts order by firstName";
	public static final String FETCH_ALL_CONTACTS_DESC = "select * from contacts order by firstName desc";
	public static final String FETCH_SPECIFIED_CONTACT = "select * from contacts where firstName=? order by c_id";
	public static final String FETCH_SPECIFIED_MOBILE = "select * from mobile where c_id=?";
	public static final String FETCH_SPECIFIED_HOME = "select * from home where c_id=?";
	public static final String FETCH_SPECIFIED_OFFICE = "select * from office where c_id=?";
	public static final String FETCH_SPECIFIED_EMAIL = "select * from email where c_id=?";
	
	public static final String UPDATE_NAME = "update contacts set ?=? where c_id=?";
	public static final String UPDATE_MOBILE_NUMBER = "update mobile set m_number=? where c_id=? and m_id=?";
	public static final String UPDATE_HOME_NUMBER = "update home set h_number=? where c_id=? and h_id=?";
	public static final String UPDATE_OFFICE_NUMBER = "update office set o_number=? where c_id=? and o_id=?";
	public static final String UPDATE_MOBILE_COUNTRY_CODE = "update mobile set countryCode=? where c_id=? and m_id=?";
	public static final String UPDATE_HOME_COUNTRY_CODE = "update home set countryCode=? where c_id=? and h_id=?";
	public static final String UPDATE_OFFICE_COUNTRY_CODE = "update office set countryCode=? where c_id=? and o_id=?";
	public static final String UPDATE_HOME_AREA_CODE = "update home set areaCode=? where c_id=? and h_id=?";
	public static final String UPDATE_OFFICE_AREA_CODE = "update office set areaCode=? where c_id=? and o_id=?";
	public static final String UPDATE_OFFICE_EXTENSION = "update office set extension=? where c_id=? and o_id=?";
	public static final String UPDATE_EMAIL = "update email set email=? where c_id=? and o_id=?";
	
	public static final String DELETE_MOBILE_ROW = "delete from mobile where c_id=? and m_id=?";
	public static final String DELETE_HOME_ROW = "delete from home where c_id=? and h_id=?";
	public static final String DELETE_OFFICE_ROW = "delete from office where c_id=? and o_id=?";
	public static final String DELETE_EMAIL_ROW = "delete from email where c_id=? and e_id=?";
}
