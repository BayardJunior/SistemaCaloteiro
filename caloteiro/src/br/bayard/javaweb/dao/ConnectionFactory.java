package br.bayard.javaweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnetion() {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/3WJavaWeb", "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}	