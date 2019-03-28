package database;

import java.sql.Connection;
import java.sql.SQLException;

public class Debug {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");

			System.out.println("connection is closed: " + conn.isClosed());
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}