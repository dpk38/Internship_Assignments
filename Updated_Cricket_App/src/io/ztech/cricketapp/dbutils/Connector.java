package io.ztech.cricketapp.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Cricket_App?autoReconnect=true&useSSL=false", "root",
					"Ztech@123");
			return con;
		} catch (Exception e) {
			System.out.println("Exception caught at openConnection(): " + e);
			return null;
		}
	}

	public void closeConnection(Connection con, ResultSet rs, PreparedStatement ps) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.print("Exception caught at closeConnection(): ");
			e.printStackTrace();
		}
	}
}
