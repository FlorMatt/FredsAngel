package com.bookstore.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static String dbUrl = "jdbc:mysql://localhost:3306/fredsangel";
	private static String dbUsername = "root";
	private static String dbPassword = "YouSmell1!";
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		
	}

}
