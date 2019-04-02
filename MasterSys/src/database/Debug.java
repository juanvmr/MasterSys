package database;

import java.sql.Connection;
import java.sql.SQLException;

public class Debug {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");

		} catch (SQLException e) {
			System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Database not found.");
		}

	}

}