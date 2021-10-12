package com.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DConnection {

	String url = "jdbc:mysql://localhost:3306/db1";
	String uname = "root";
	String passw = "Aditya@20";
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, passw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
