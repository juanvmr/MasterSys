package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Assiduidade;

public class AssiduidadeDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public AssiduidadeDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM assiduidade ORDER BY data_entrada";

		// build query
		pst_select = conn.prepareStatement(query);
		
		// run query
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Assiduidade tmp = new Assiduidade(
				rst.getInt("codigo_matricula"),
				rst.getDate("data_entrada")
			);
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object select(Object obj) throws SQLException {

		String query = "SELECT * FROM assiduidade WHERE codigo_matricula = ?";

		// build query
		pst_select = conn.prepareStatement(query);
		
		// fill query
		Set(pst_select, 1, ((Assiduidade) obj).getCodigoMatricula());
		
		// run query
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		Object tmp = null;
		if (rst.next()) {
			tmp = new Assiduidade(
				rst.getInt("codigo_matricula"),
				rst.getDate("data_entrada")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO assiduidade(codigo_matricula, data_entrada) VALUES (?, ?)";
		
		// build query
		pst_insert = conn.prepareStatement(query);
		
		// fill query
		Assiduidade tmp = (Assiduidade) obj;

		Set(pst_insert, 1, tmp.getCodigoMatricula());
		Set(pst_insert, 2, tmp.getDataEntrada());
		
		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE assiduidade SET data_entrada = ? WHERE codigo_matricula = ?";

		// build query
		pst_update = conn.prepareStatement(query);

		// fill query
		Assiduidade tmp = (Assiduidade) obj;
		Set(pst_update, 1, tmp.getDataEntrada());
		Set(pst_update, 2, tmp.getCodigoMatricula());
		
		// run query
		pst_update.execute();

		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM assiduidade WHERE codigo_matricula = ?";
		
		// build query
		pst_delete = conn.prepareStatement(query);
		
		// fill query
		Assiduidade tmp = (Assiduidade) obj;

		Set(pst_delete, 1, tmp.getCodigoMatricula());
		
		// run query
		pst_delete.execute();

		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
}
