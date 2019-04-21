package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Aluno;
import database.models.Local;

public class AlunoDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public AlunoDAO(Connection conn) {
		this.conn = conn;
	}

	/* methods: */
	private Aluno getData(ResultSet rst) throws SQLException {
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
		tmp.setCEP(rst.getString("cep"));
		return tmp;
	}

	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(codigo_aluno) FROM alunos WHERE codigo_aluno = ?";

		pst_select = conn.prepareStatement(query);

		// fill statement
		Set(pst_select, 1, ((Aluno) obj).getCodigoAluno());

		// receive query result
		ResultSet rst = pst_select.executeQuery();

		// check if query returned something
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> select() throws SQLException {

		String query = "SELECT * FROM alunos ORDER BY aluno";

		pst_select = conn.prepareStatement(query);

		// receive query result
		ResultSet rst = pst_select.executeQuery();

		// check what query returned
		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Aluno tmp = getData(rst);
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public List<Object> select(Object obj) throws SQLException {

		String query = "SELECT * FROM alunos WHERE aluno = ? ORDER BY aluno";

		// build query
		pst_select = conn.prepareStatement(query);

		// fill query
		if (obj instanceof Aluno) {
			Set(pst_select, 1, ((Aluno) obj).getAluno());
		} else if (obj instanceof String) {
			Set(pst_select, 1, (String) obj);
		}

		// receive query result
		ResultSet rst = pst_select.executeQuery();

		// check what query returned
		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Aluno tmp = getData(rst);
			list.add(tmp);
		}

		return list;
	}

	@Override
	public Object find(Object obj) throws SQLException {

		String query = "SELECT * FROM alunos WHERE aluno = ?";

		pst_select = conn.prepareStatement(query);

		// fill statement
		if (obj instanceof Aluno) {
			Set(pst_select, 1, ((Aluno) obj).getAluno());
		} else if (obj instanceof String) {
			Set(pst_select, 1, (String) obj);
		}
		
		// receive query result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query returned something
		Aluno tmp = null;
		if (rst.next()) {
			tmp = getData(rst);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO alunos (codigo_aluno, aluno, data_nascimento, sexo, "
			+ "telefone, celular, email, observacao, endereco, numero, complemento, bairro, cidade, "
			+ "estado, pais, cep) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// build statement
		pst_insert = conn.prepareStatement(query);

		// fill statement
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
		Set(pst_insert, 12, tmp.getLocal().getCidade());
		Set(pst_insert, 13, tmp.getLocal().getEstado());
		Set(pst_insert, 14, tmp.getLocal().getPais());
		Set(pst_insert, 15, tmp.getCEP());

		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE alunos SET aluno = ?, data_nascimento = ?, sexo = ?, telefone = ?, "
			+ "celular = ?, email = ?, observacao = ?, endereco = ?, numero = ?, complemento = ?, "
			+ "bairro = ?, cidade = ?, estado = ?, pais = ?, cep = ? WHERE codigo_aluno = ?";
		
		// build statement
		pst_update = conn.prepareStatement(query);
		
		// fill statement
		Aluno tmp = (Aluno) obj;

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
		Set(pst_update, 15, tmp.getCEP());
		Set(pst_update, 16, tmp.getCodigoAluno());
		
		// run query
		pst_update.execute();
		
		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM alunos WHERE codigo_aluno = ?";

		// build statement
		pst_delete = conn.prepareStatement(query);
		
		// fill statement
		Aluno tmp = (Aluno) obj;

		Set(pst_delete, 1, tmp.getCodigoAluno());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
