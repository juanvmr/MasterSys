package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.*;

public class AlunoDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM alunos ORDER BY aluno";
	private String is_select = "SELECT * FROM alunos WHERE aluno = ?";
	private String is_insert = "INSERT INTO alunos (codigo_aluno, aluno, data_nascimento, sexo, "
			+ "telefone, celular, email, observacao, endereco, numero, complemento, bairro, cidade, "
			+ "estado, pais, cep) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String is_update = "UPDATE alunos SET aluno = ?, data_nascimento = ?, sexo = ?, telefone = ?, "
			+ "celular = ?, email = ?, observacao = ?, endereco = ?, numero = ?, complemento = ?, "
			+ "bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ? WHERE codigo_aluno = ?";
	private String is_delete = "DELETE FROM alunos WHERE codigo_aluno = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public AlunoDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_select = conn.prepareStatement(is_select);
		pst_insert = conn.prepareStatement(is_insert);
		pst_update = conn.prepareStatement(is_update);
		pst_delete = conn.prepareStatement(is_delete);
	}
	
	/* methods: */
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// receive query result
		ResultSet rst = pst_selectAll.executeQuery();
		
		// check what query returned
		while (rst.next()) {
			Aluno tmp = new Aluno();
			tmp.setCodigoAluno(rst.getInt("codigo_aluno"));
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
			tmp.setLocal(new Local(rst.getString("cidade"), rst.getString("estado"), rst.getString("pais")));
			tmp.setCep(rst.getString("cep"));
			
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object Select(Object obj) throws SQLException {
		
		/* initialize a new object */
		Aluno tmp = null;
		
		// clear previous statement parameters
		pst_select.clearParameters();
		
		// fill statement
		Set(pst_select, 1, ((Aluno) obj).getAluno());
		
		// receive query result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query returned something
		if (rst.next()) {
			tmp = new Aluno();
			tmp.setCodigoAluno(rst.getInt("codigo_aluno"));
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
			tmp.setLocal(new Local(rst.getString("cidade"), rst.getString("estado"), rst.getString("pais")));
			tmp.setCep(rst.getString("cep"));
		}
		
		return tmp;
	}

	@Override
	public void Update(Object obj) throws SQLException {
		
		Aluno tmp = (Aluno) obj;
		
		// clear previous statement parameters
		pst_update.clearParameters();
		
		// fill statement
		Set(pst_update,  1, tmp.getAluno());
		Set(pst_update,  2, tmp.getDataNascimento());
		Set(pst_update,  3, tmp.getSexo());
		Set(pst_update,  4, tmp.getTelefone());
		Set(pst_update,  5, tmp.getCelular());
		Set(pst_update,  6, tmp.getEmail());
		Set(pst_update,  7, tmp.getObs());
		Set(pst_update,  8, tmp.getEndereco());
		Set(pst_update,  9, tmp.getNumero());
		Set(pst_update, 10, tmp.getComplemento());
		Set(pst_update, 11, tmp.getBairro());
		Set(pst_update, 12, tmp.getLocal().getCidade());
		Set(pst_update, 13, tmp.getLocal().getEstado());
		Set(pst_update, 14, tmp.getLocal().getPais());
		Set(pst_update, 15, tmp.getCep());
		Set(pst_update, 16, tmp.getCodigoAluno());
		
		// run query
		pst_update.execute();
		
		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Insert(Object obj) throws SQLException {
		
		Aluno tmp = (Aluno) obj;
		
		// clear previous statement parameters
		pst_insert.clearParameters();
		
		// fill statement
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
		Set(pst_insert, 12, tmp.getLocal().getCidade());
		Set(pst_insert, 13, tmp.getLocal().getEstado());
		Set(pst_insert, 14, tmp.getLocal().getPais());
		Set(pst_insert, 15, tmp.getCep());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Delete(Object obj) throws SQLException {
		
		Aluno tmp = (Aluno) obj;
		
		// clear previous statement parameters
		pst_delete.clearParameters();
		
		// fill statement
		Set(pst_delete, 1, tmp.getCodigoAluno());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
