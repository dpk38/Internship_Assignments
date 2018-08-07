package io.ztech.contactsapp.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnectivity {

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Contact_Management?autoReconnect=true&useSSL=false", "root", "Ztech@123");
			return con;
		} catch (Exception e) {
			System.out.println("Excpetion caught at openConnection(): " + e);
			return null;
		}
	}
	
	public void closeConnection(Connection con, ResultSet rs, PreparedStatement ps) {
		try {
			if (rs != null) {
				rs.close();
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.print("Exception caught at closeConnection(): ");
			e.printStackTrace();
		}
	}
}
