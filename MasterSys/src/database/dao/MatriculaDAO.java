package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Matricula;

public class MatriculaDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM matriculas ORDER BY codigo_matricula";
	private String is_select = "SELECT m.* FROM matriculas m JOIN alunos a ON (m.codigo_aluno = a.codigo_aluno) WHERE m.codigo_matricula = ? AND m.codigo_aluno = ?";
	private String is_insert = "INSERT INTO matriculas(codigo_matricula, codigo_aluno, data_matricula, dia_vencimento, data_encerramento) VALUES (DEFAULT, ?, ?, ?, ?)";
	private String is_update = "UPDATE matriculas SET data_matricula = ?, dia_vencimento = ?, data_encerramento = ? WHERE codigo_matricula = ? AND codigo_aluno = ?";
	private String is_delete = "DELETE FROM matriculas WHERE codigo_matricula = ? AND codigo_aluno = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public MatriculaDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		this.pst_selectAll = conn.prepareStatement(is_selectAll);
		this.pst_select = conn.prepareStatement(is_select);
		this.pst_insert = conn.prepareStatement(is_insert);
		this.pst_update = conn.prepareStatement(is_update);
		this.pst_delete = conn.prepareStatement(is_delete);
		
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// receive query result
		ResultSet rst = pst_selectAll.executeQuery();
		
		// check what query returned
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
	public Object select(Object obj) throws SQLException {
		
		Matricula tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((Matricula) obj).getCodigoMatricula());
		Set(pst_select, 2, ((Matricula) obj).getCodigoAluno());
		
		ResultSet rst = pst_select.executeQuery();
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

		Matricula tmp = (Matricula) obj;

		// clear previous query
		pst_insert.clearParameters();

		// fill query
		Set(pst_insert, 1, tmp.getCodigoAluno());
		Set(pst_insert, 2, tmp.getDataMatricula());
		Set(pst_insert, 3, tmp.getDiaVencimento());
		Set(pst_insert, 4, tmp.getDataEncerramento());

		// run query
		pst_insert.execute();

		if (pst_insert.getUpdateCount() > 0) {
			conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {
		
		Matricula tmp = (Matricula) obj;
		
		// clear previous query
		pst_update.clearParameters();
		
		// fill query
		Set(pst_update, 1, tmp.getDataMatricula());
		Set(pst_update, 2, tmp.getDiaVencimento());
		Set(pst_update, 3, tmp.getDataEncerramento());
		Set(pst_update, 4, tmp.getCodigoMatricula());
		Set(pst_update, 5, tmp.getCodigoAluno());
		
		// run query
		pst_insert.execute();
		
		if (pst_insert.getUpdateCount() > 0) {
			conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {
		
		Matricula tmp = (Matricula) obj;
		
		// clear previous query
		pst_delete.clearParameters();
		
		// fill query
		Set(pst_delete, 1, tmp.getCodigoMatricula());
		Set(pst_delete, 2, tmp.getCodigoAluno());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			conn.commit();
		}
	}
}
