package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Aluno;
import database.models.Matricula;

public class MatriculaDAO extends MasterDAO {
	
	/* attributes: */
	private Connection connection;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public MatriculaDAO(Connection connection) {
		this.connection = connection;
	}

	/* methods: */
	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(m.codigo_matricula) FROM matriculas m INNER JOIN alunos a ON " +
			"(m.codigo_aluno = a.codigo_aluno) WHERE m.codigo_aluno = ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Matricula) obj).getCodigoAluno());

		// run query
		ResultSet rst = pst_select.executeQuery();

		if (rst.next() ) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> select() throws SQLException {

		String query = "SELECT * FROM matriculas ORDER BY codigo_matricula";

		// build query
		pst_select = connection.prepareStatement(query);
		
		// receive query result
		ResultSet rst = pst_select.executeQuery();
		
		// check what query returned
		List<Object> list = new ArrayList<>();

		while (rst.next()) {
			Matricula tmp = new Matricula(
				rst.getInt("codigo_matricula"),
				rst.getInt("codigo_aluno"),
				rst.getDate("data_matricula"),
				rst.getInt("dia_vencimento"),
				rst.getDate("data_encerramento")
			);
			
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public List<Object> select(Object obj) throws SQLException {
		return null;
	}

	@Override
	public Object find(Object obj) throws SQLException {
		if (obj == null) return null;

		String query = "SELECT m.*, a.* FROM matriculas m INNER JOIN alunos a ON (m.codigo_aluno = a.codigo_aluno) " +
			"WHERE a.codigo_aluno = ?";

		// build query
		pst_select = connection.prepareStatement(query);
		
		// fill query
		int pos = 0;
		if (obj instanceof Matricula) {
			Set(pst_select, ++pos, ((Matricula) obj).getCodigoAluno());
		} else if (obj instanceof Aluno) {
			Set(pst_select, ++pos, ((Aluno) obj).getCodigoAluno());
		} else if (obj instanceof Integer) {
			Set(pst_select, ++pos, obj);
		} else if (obj instanceof String) {
			Set(pst_select, ++pos, Integer.parseInt((String) obj));
		}

		// run query
		ResultSet rst = pst_select.executeQuery();

		Matricula tmp = null;
		if (rst.next() ) {
			tmp = new Matricula(
				rst.getInt("codigo_matricula"),
				rst.getInt("codigo_aluno"),
				rst.getDate("data_matricula"),
				rst.getInt("dia_vencimento"),
				rst.getDate("data_encerramento")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

//		String query = "INSERT INTO matriculas(codigo_matricula, codigo_aluno, data_matricula, dia_vencimento, data_encerramento) VALUES (DEFAULT, ?, ?, ?, ?)";
		String query = "INSERT INTO matriculas(codigo_aluno, data_matricula, dia_vencimento, data_encerramento) VALUES (?, ?, ?, ?)";

		// build query
		pst_insert = connection.prepareStatement(query);

		// fill query
		Matricula tmp = (Matricula) obj;

		int pos = 0;
		Set(pst_insert, ++pos, tmp.getCodigoAluno());
		Set(pst_insert, ++pos, tmp.getDataMatricula());
		Set(pst_insert, ++pos, tmp.getDiaVencimento());
		Set(pst_insert, ++pos, tmp.getDataEncerramento());

		// run query
		pst_insert.execute();

		if (pst_insert.getUpdateCount() > 0) {
			connection.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE matriculas SET data_matricula = ?, dia_vencimento = ?, data_encerramento = ? " +
			"WHERE codigo_matricula = ? AND codigo_aluno = ?";
		
		// build query
		pst_update = connection.prepareStatement(query);
		
		// fill query
		Matricula tmp = (Matricula) obj;

		Set(pst_update, 1, tmp.getDataMatricula());
		Set(pst_update, 2, tmp.getDiaVencimento());
		Set(pst_update, 3, tmp.getDataEncerramento());
		Set(pst_update, 4, tmp.getCodigoMatricula());
		Set(pst_update, 5, tmp.getCodigoAluno());
		
		// run query
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			connection.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM matriculas WHERE codigo_aluno = ?";

		// clear previous query
		pst_delete = connection.prepareStatement(query);
		
		// fill query
		if (obj instanceof Matricula) {
			Set(pst_delete, 1, ((Matricula) obj).getCodigoAluno());
		} else if (obj instanceof Integer) {
			Set(pst_delete, 1, obj);
		}
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			connection.commit();
		}
	}
}
