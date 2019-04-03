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
	
	/* query: */
	private String is_selectAll = "SELECT codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim FROM matriculas_modalidades ORDER BY data_inicio";
	private String is_select = "SELECT codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim FROM matriculas_modalidades WHERE codigo_aluno = ? AND modalidade = ?";
	private String is_insert = "INSERT INTO matriculas_modalidades(codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?)";
	private String is_update = "UPDATE matriculas_modalidades SET graduacao = ?, plano = ?, data_inicio = ?, data_fim = ? WHERE codigo_matricula = ?  modalidade = ?";
	private String is_delete = "DELETE FROM matriculas_modalidades WHERE codigo_matricula = ? AND modalidade = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public MatriculaModalidadeDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_select = conn.prepareStatement(is_select);
		pst_insert = conn.prepareStatement(is_insert);
		pst_update = conn.prepareStatement(is_update);
		pst_delete = conn.prepareStatement(is_delete);
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
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
		
		MatriculaModalidade tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((MatriculaModalidade) obj).getCodigoMatricula());
		Set(pst_select, 2, ((MatriculaModalidade) obj).getModalidade());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
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
		
		MatriculaModalidade tmp = (MatriculaModalidade) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
		Set(pst_insert, 1, ((MatriculaModalidade) obj).getCodigoMatricula());
		Set(pst_insert, 2, ((MatriculaModalidade) obj).getModalidade());
		Set(pst_insert, 3, ((MatriculaModalidade) obj).getGraduacao());
		Set(pst_insert, 4, ((MatriculaModalidade) obj).getDataInicio());
		Set(pst_insert, 5, ((MatriculaModalidade) obj).getDataFim());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {
		/* DUVIDAS ??? */
	}

	@Override
	public void delete(Object obj) throws SQLException {
		
		MatriculaModalidade tmp = (MatriculaModalidade) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
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
