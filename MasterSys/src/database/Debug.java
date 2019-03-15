package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.dao.*;
import database.models.*;
import image.MasterImage;

public class Debug {

	public static void main(String[] args) {
		
		MasterImage img = new MasterImage();
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");
			
			ModalidadesDAO dao = new ModalidadesDAO(conn);
			
			Modalidades mod = new Modalidades("Ciências da Computação");
			
			dao.Insert(mod);
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
