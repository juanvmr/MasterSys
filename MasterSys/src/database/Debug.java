package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.dao.*;
import database.models.*;
//import image.MasterImage;

public class Debug {

	public static void main(String[] args) {
		
		// MasterImage img = new MasterImage();
		
		Connection conn = ConnectionFactory.getConnection("master", "admin", "admin");
		try {
			conn.setAutoCommit(false);
			System.out.println("Connectado com sucesso!");
			
			LocalDAO local_dao = new LocalDAO(conn);
			Local tmp = (Local) local_dao.Select(new Local("CRICIUMA", "SC", "Brasil"));
			
			System.out.println(tmp.toString());
			
			Aluno aluno = new Aluno();
			aluno.setAluno("Pedro");
			aluno.setDataNascimento(new Date("02/06/1990"));
			aluno.setSexo('M');
			aluno.setTelefone("(48) 3433-4664");
			aluno.setCelular("(47) 99707-0799");
			aluno.setEmail("email@domain.com");
			aluno.setObs("empty");
			aluno.setEndereco("Rua XYZ");
			aluno.setNumero("120");
			aluno.setComplemento("apto 400");
			aluno.setBairro("Pio Corrêa");
			aluno.setLocal(tmp);
			aluno.setCep("88811-510");
			
			AlunoDAO dao = new AlunoDAO(conn);
			dao.Insert(aluno);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}

/*
	// lista de tables
	- alunos
	- cidades
	- graduacoes
	- modalidades
	- planos
	- usuarios
	assiduidade
	faturas_matriculas
	matriculas
	matriculas_modalidades
	perfis
*/