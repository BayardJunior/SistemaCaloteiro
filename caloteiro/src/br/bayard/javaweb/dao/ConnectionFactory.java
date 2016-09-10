package br.bayard.javaweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private String URL  = "jdbc:mysql://localhost:3308/javaWeb/";
	private String USER = "root";
	private String PASS = "";
	
	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(URL, USER, PASS); 
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle.getMessage());
		}
	}
	
}
