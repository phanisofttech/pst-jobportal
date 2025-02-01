package com.pst.jobportal.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private static Connection con = null;
	
	public static Connection getDataBaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pst_job_portal","root","Phani@123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
