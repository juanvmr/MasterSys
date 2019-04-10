package database;

import frames.PlanosFrame;
import image.MasterImage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Debug {

	public static void main(String[] args) {
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");

			MasterImage img = new MasterImage();

			PlanosFrame frame = new PlanosFrame("Testing", conn);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.initComponents(frame.getContentPane());
			frame.pack();
			frame.setVisible(true);

		} catch (SQLException e) {
			System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Database not found.");
			e.printStackTrace();
		}

	}

}