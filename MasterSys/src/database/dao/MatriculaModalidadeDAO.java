package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.MatriculaModalidade;

public class MatriculaModalidadeDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public MatriculaModalidadeDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim FROM " +
			"matriculas_modalidades ORDER BY data_inicio";

		// build query
		pst_select = conn.prepareStatement(query);

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			MatriculaModalidade tmp = new MatriculaModalidade(
				rst.getInt("codigo_matricula"),
				rst.getString("modalidade"),
				rst.getString("graduacao"),
				rst.getString("plano"),
				rst.getDate("data_inicio"),
				rst.getDate("data_fim")
			);
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object select(Object obj) throws SQLException {

		String query = "SELECT codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim FROM " +
			"matriculas_modalidades WHERE codigo_matricula = ? AND modalidade = ?";
		
		// build query
		pst_select = conn.prepareStatement(query);
		
		// fill query
		Set(pst_select, 1, ((MatriculaModalidade) obj).getCodigoMatricula());
		Set(pst_select, 2, ((MatriculaModalidade) obj).getModalidade());
		
		// run query
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		MatriculaModalidade tmp = null;
		if (rst.next()) {
			tmp = new MatriculaModalidade(
				rst.getInt("codigo_matricula"),
				rst.getString("modalidade"),
				rst.getString("graduacao"),
				rst.getString("plano"),
				rst.getDate("data_inicio"),
				rst.getDate("data_fim")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO matriculas_modalidades(codigo_matricula, modalidade, graduacao, " +
			"plano, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?)";
		
		// build query
		pst_insert = conn.prepareStatement(query);
		
		// fill query
		MatriculaModalidade tmp = (MatriculaModalidade) obj;

		Set(pst_insert, 1, tmp.getCodigoMatricula());
		Set(pst_insert, 2, tmp.getModalidade());
		Set(pst_insert, 3, tmp.getGraduacao());
		Set(pst_insert, 3, tmp.getPlano());
		Set(pst_insert, 4, tmp.getDataInicio());
		Set(pst_insert, 5, tmp.getDataFim());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE matriculas_modalidades SET graduacao = ?, plano = ?, data_inicio = ?, data_fim = ? " +
			"WHERE codigo_matricula = ? AND modalidade = ?";

		// build query
		pst_update = conn.prepareStatement(query);

		// fill query
		MatriculaModalidade tmp = (MatriculaModalidade) obj;

		Set(pst_update, 1, tmp.getGraduacao());
		Set(pst_update, 2, tmp.getPlano());
		Set(pst_update, 3, tmp.getDataInicio());
		Set(pst_update, 4, tmp.getDataFim());
		Set(pst_update, 5, tmp.getCodigoMatricula());
		Set(pst_update, 6, tmp.getModalidade());

		// run query
		pst_update.execute();

		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM matriculas_modalidades WHERE codigo_matricula = ? AND modalidade = ?";
		
		// build query
		pst_delete = conn.prepareStatement(query);
		
		// fill query
		MatriculaModalidade tmp = (MatriculaModalidade) obj;

		Set(pst_delete, 1, tmp.getCodigoMatricula());
		Set(pst_delete, 1, tmp.getModalidade());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
}
