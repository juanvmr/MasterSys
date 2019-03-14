package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.dao.AlunoDAO;
import database.models.*;
import image.MasterImage;

public class Debug {

	public static void main(String[] args) {
		
		MasterImage img = new MasterImage();
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");
			
			AlunoDAO dao = new AlunoDAO(conn);
			
			/*
			List<Object> list = dao.SelectAll();
			
			System.out.println("Number of Alunos: " + list.size());
			for (int i = 0; i < list.size(); i++) {
				Aluno aluno = (Aluno) list.get(i);
				System.out.println(aluno.toString());
			}
			
			Aluno tmp = (Aluno)dao.Select("Pedro Zanette de Campos");
			System.out.println(tmp.toString());
			*/
			
			Aluno tmp = new Aluno();
			tmp.setAluno("Pedro");
			tmp.setDataNascimento(new Date("06/03/2019"));
			tmp.setSexo('M');
			tmp.setTelefone("(48) 0000-0000");
			tmp.setCelular("(48) 0000-0000");
			tmp.setEmail("email@domain.com");
			tmp.setObs("testing");
			tmp.setEndereco("Rua A");
			tmp.setNumero("100");
			tmp.setComplemento("apto 100");
			tmp.setBairro("Centro");
			tmp.setLocal(new Local("CRICIUMA", "SC","Brasil"));
			tmp.setCep("88811-510");
			
			dao.Insert(tmp);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	/*
	Teste alteração*/

}
