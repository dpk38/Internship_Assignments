package io.ztech.contactsapp.constants;

public interface Queries {
	String INSERT_INTO_CONTACTS = "insert into contacts (firstName, lastName) values (?, ?)";
	String INSERT_INTO_MOBILE = "insert into mobile values (?, ?, ?, ?)";
	String INSERT_INTO_HOME= "insert into home values (?, ?, ?, ?, ?)";
	String INSERT_INTO_OFFICE = "insert into office values (?, ?, ?, ?, ?, ?)";
	String INSERT_INTO_EMAIL = "insert into email values (?, ?, ?)";
	
	String FETCH_RECENT_CID = "select c_id from contacts order by c_id desc limit 1";
	String FETCH_RECENT_MID = "select m_id from mobile where c_id = ? order by m_id desc limit 1";
	String FETCH_RECENT_HID = "select h_id from home where c_id = ? order by h_id desc limit 1";
	String FETCH_RECENT_OID = "select o_id from office where c_id = ? order by o_id desc limit 1";
	String FETCH_RECENT_EID = "select e_id from email where c_id = ? order by e_id desc limit 1";
	String FETCH_ALL_CONTACTS = "select * from contacts order by firstName";
	String FETCH_ALL_CONTACTS_DESC = "select * from contacts order by firstName desc";
	String FETCH_SPECIFIED_CONTACT = "select * from contacts where firstName = ? order by c_id";
	String FETCH_SPECIFIED_MOBILE = "select * from mobile where c_id = ?";
	String FETCH_SPECIFIED_HOME = "select * from home where c_id = ?";
	String FETCH_SPECIFIED_OFFICE = "select * from office where c_id = ?";
	String FETCH_SPECIFIED_EMAIL = "select * from email where c_id = ?";
	
	String UPDATE_FIRST_NAME = "update contacts set firstName = ? where c_id = ?";
	String UPDATE_LAST_NAME = "update contacts set lastName = ? where c_id = ?";
	String UPDATE_MOBILE_NUMBER = "update mobile set m_number = ? where c_id = ? and m_id = ?";
	String UPDATE_HOME_NUMBER = "update home set h_number = ? where c_id = ? and h_id = ?";
	String UPDATE_OFFICE_NUMBER = "update office set o_number = ? where c_id = ? and o_id = ?";
	String UPDATE_MOBILE_COUNTRY_CODE = "update mobile set countryCode = ? where c_id = ? and m_id = ?";
	String UPDATE_HOME_COUNTRY_CODE = "update home set countryCode = ? where c_id = ? and h_id = ?";
	String UPDATE_OFFICE_COUNTRY_CODE = "update office set countryCode = ? where c_id = ? and o_id = ?";
	String UPDATE_HOME_AREA_CODE = "update home set areaCode = ? where c_id = ? and h_id = ?";
	String UPDATE_OFFICE_AREA_CODE = "update office set areaCode = ? where c_id = ? and o_id = ?";
	String UPDATE_OFFICE_EXTENSION = "update office set extension = ? where c_id = ? and o_id = ?";
	String UPDATE_EMAIL = "update email set email = ? where c_id = ? and e_id = ?";
	
	String DELETE_MOBILE_ROW = "delete from mobile where c_id = ? and m_id = ?";
	String DELETE_HOME_ROW = "delete from home where c_id = ? and h_id = ?";
	String DELETE_OFFICE_ROW = "delete from office where c_id = ? and o_id = ?";
	String DELETE_EMAIL_ROW = "delete from email where c_id = ? and e_id = ?";
}
