package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	/**
	 *	Returns a PostgreSQL database connection.
	 *	@param dbAddress	-- Server address (Computer name or IP).
	 *	@param dbPort		-- Server port.
	 *	@param dbName		-- Database name.
	 *	@param dbUsername	-- Connetion user.
	 *	@param dbPassword	-- Connection p455w0rd.
	 *	@return PostgreSQL Connecttion.
	 */
	public static Connection getConnection(final String dbAddress, final String dbPort, final String dbName, final String dbUsername, final String dbPassword) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://" + dbAddress + ":" + dbPort + "/" + dbName, dbUsername, dbPassword);
		}catch (SQLException e) {
			System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
		}
		return conn;
	}

	/**
	 *	Returns a PostgreSQL database connection.
	 *	@param dbName		-- Database name.
	 *	@param dbUsername	-- Connetion user.
	 *	@param dbPassword	-- Connection p455w0rd.
	 *	@return PostgreSQL Connecttion.
	 */
	public static Connection getConnection(final String dbName, final String dbUsername, final String dbPassword) {
		return getConnection("localhost", "5432", dbName, dbUsername, dbPassword);
	}

}
