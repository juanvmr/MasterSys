package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {
	
	public static Connection getConnection(final String dbAddress, final String dbPort, final String dbName, final String dbUsername, final String dbPassword) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://" + dbAddress + ":" + dbPort + "/" + dbName, dbUsername, dbPassword);
		}catch (SQLException e) {
			System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
		}
		return conn;
	}
	
	public static Connection getConnection(final String dbName, final String dbUsername, final String dbPassword) {
		return getConnection("localhost", "5432", dbName, dbUsername, dbPassword);
	}

	@Override
	public void close() {
		System.out.println("closing conenciton");
	}
}
