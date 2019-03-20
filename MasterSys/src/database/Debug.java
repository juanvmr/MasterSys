package database;

import java.sql.Connection;
import java.sql.SQLException;

import panels.*;
import database.models.*;
import database.dao.*;

public class Debug {

	public static void main(String[] args) {
		
		new Menu();
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}