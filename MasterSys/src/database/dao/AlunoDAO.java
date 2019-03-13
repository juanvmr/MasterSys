package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Aluno;

public class AlunoDAO extends MasterDAO {
	
	/* create variables with select statement. */
	private String is_selectAll = "SELECT * FROM alunos ORDER BY aluno";
	private String is_select = "SELECT * FROM alunos WHERE aluno = ? ORDER BY aluno";
	private String is_insert = "INSERT INTO alunos (codigo_aluno, aluno, data_nascimento, sexo, telefone, celular, " + 
		"email, observacao, endereco, numero, complemento, bairro, cidade, estado, pais, cep) " + 
		"VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String is_update = "UPDATE alunos SET data_nascimento = ?, sexo = ?, telefone = ?, " + 
		"celular = ?, email = ?, observacao = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ? " + 
		"WHERE aluno = ?";
	private String is_delete = "DELETE FROM alunos WHERE aluno = ?";
	
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	private Connection conn;
	
	public AlunoDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_select = conn.prepareStatement(is_select);
		pst_insert = conn.prepareStatement(is_insert);
		pst_update = conn.prepareStatement(is_update);
		pst_delete = conn.prepareStatement(is_delete);
	}

	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Aluno tmp = new Aluno();
			tmp.setAluno(rst.getString("aluno"));
			tmp.setDataNascimento(rst.getDate("data_nascimento"));
			tmp.setSexo(rst.getString("sexo").charAt(0));
			tmp.setTelefone(rst.getString("telefone"));
			tmp.setCelular(rst.getString("celular"));
			tmp.setEmail(rst.getString("email"));
			tmp.setObs(rst.getString("observacao"));
			tmp.setEndereco(rst.getString("endereco"));
			tmp.setNumero(rst.getString("numero"));
			tmp.setComplemento(rst.getString("complemento"));
			tmp.setBairro(rst.getString("bairro"));
			tmp.setCidade(rst.getString("cidade"));
			tmp.setEstado(rst.getString("estado"));
			tmp.setPais(rst.getString("pais"));
			tmp.setCep(rst.getString("cep"));
			
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		/* initialize a new object */
		Aluno tmp = null;
		
		/* set query parameters */
		Set(pst_select, 1, ((Aluno) parameter).getAluno());
		Set(pst_select, 2, ((Aluno) parameter).getEmail());
		
		/* receive query result */
		ResultSet rst = pst_select.executeQuery();
		
		/* check if query returned something */
		if (rst.next()) {
			tmp = new Aluno();
			tmp.setAluno(rst.getString("aluno"));
			tmp.setDataNascimento(rst.getDate("data_nascimento"));
			tmp.setSexo(rst.getString("sexo").charAt(0));
			tmp.setTelefone(rst.getString("telefone"));
			tmp.setCelular(rst.getString("celular"));
			tmp.setEmail(rst.getString("email"));
			tmp.setObs(rst.getString("observacao"));
			tmp.setEndereco(rst.getString("endereco"));
			tmp.setNumero(rst.getString("numero"));
			tmp.setComplemento(rst.getString("complemento"));
			tmp.setBairro(rst.getString("bairro"));
			tmp.setCidade(rst.getString("cidade"));
			tmp.setEstado(rst.getString("estado"));
			tmp.setPais(rst.getString("pais"));
			tmp.setCep(rst.getString("cep"));
		}
		
		return tmp;
	}

	@Override
	public void Update(Object obj) throws SQLException {
		
		pst_update.clearParameters();
		
		Aluno tmp = (Aluno) obj;
		
		Set(pst_update,  1, tmp.getDataNascimento());
		Set(pst_update,  2, tmp.getSexo());
		Set(pst_update,  3, tmp.getTelefone());
		Set(pst_update,  4, tmp.getCelular());
		Set(pst_update,  5, tmp.getEmail());
		Set(pst_update,  6, tmp.getObs());
		Set(pst_update,  7, tmp.getEndereco());
		Set(pst_update,  8, tmp.getNumero());
		Set(pst_update,  9, tmp.getComplemento());
		Set(pst_update, 10, tmp.getBairro());
		Set(pst_update, 11, tmp.getCidade());
		Set(pst_update, 12, tmp.getEstado());
		Set(pst_update, 13, tmp.getPais());
		Set(pst_update, 14, tmp.getCep());
		Set(pst_update, 15, tmp.getAluno());
		
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Insert(Object obj) throws SQLException {
		
		pst_insert.clearParameters();
		
		Aluno tmp = (Aluno) obj;
		
		Set(pst_insert,  1, tmp.getAluno());
		Set(pst_insert,  2, tmp.getDataNascimento());
		Set(pst_insert,  3, tmp.getSexo());
		Set(pst_insert,  4, tmp.getTelefone());
		Set(pst_insert,  5, tmp.getCelular());
		Set(pst_insert,  6, tmp.getEmail());
		Set(pst_insert,  7, tmp.getObs());
		Set(pst_insert,  8, tmp.getEndereco());
		Set(pst_insert,  9, tmp.getNumero());
		Set(pst_insert, 10, tmp.getComplemento());
		Set(pst_insert, 11, tmp.getBairro());
		Set(pst_insert, 12, tmp.getCidade());
		Set(pst_insert, 13, tmp.getEstado());
		Set(pst_insert, 14, tmp.getPais());
		Set(pst_insert, 15, tmp.getCep());
		
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Delete(Object obj) throws SQLException {
		
		Aluno tmp = (Aluno) obj;
		
		Set(pst_delete, 1, tmp.getAluno());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
