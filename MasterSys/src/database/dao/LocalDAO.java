package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Local;

public class LocalDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;
	
	/* constructor: */
	public LocalDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}

	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM cidades ORDER BY estado DESC, pais DESC";

		pst_select = conn.prepareStatement(query);
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Local tmp = new Local(
				rst.getString("cidade"),
				rst.getString("estado"),
				rst.getString("pais")
			);
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object select(Object obj) throws SQLException {

		String query = "SELECT * FROM cidades WHERE cidade = ? AND estado = ? AND pais = ?";
		
		// clear previous query
		pst_select = conn.prepareStatement(query);
		
		// fill query
		Set(pst_select, 1, ((Local) obj).getCidade());
		Set(pst_select, 2, ((Local) obj).getEstado());
		Set(pst_select, 3, ((Local) obj).getPais());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		Object tmp = null;
		if (rst.next()) {
			tmp = new Local(
				rst.getString("cidade"),
				rst.getString("estado"),
				rst.getString("pais")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO cidades (cidade, estado, pais) VALUES (?, ?, ?)";

		// build statement
		pst_insert = conn.prepareStatement(query);
		
		// fill query
		Local tmp = (Local) obj;

		Set(pst_insert,  1, tmp.getCidade());
		Set(pst_insert,  2, tmp.getEstado());
		Set(pst_insert,  3, tmp.getPais());
		
		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE cidades SET cidade = ? WHERE estado = ? AND pais = ?";

		// build statement
		pst_update = conn.prepareStatement(query);

	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM cidades WHERE cidade = ? AND estado = ? AND pais = ?";

		// build statement
		pst_delete = conn.prepareStatement(query);
		
		// fill query
		Local tmp = (Local) obj;

		Set(pst_delete, 1, tmp.getCidade());
		Set(pst_delete, 2, tmp.getEstado());
		Set(pst_delete, 3, tmp.getPais());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	public List<Object> selectPais() throws SQLException {

		String query = "SELECT DISTINCT pais FROM cidades ORDER BY pais";

		pst_select = conn.prepareStatement(query);
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<Object>();
		while (rst.next()) {
			list.add(rst.getString("pais"));
		}
		
		return list;
	}

	public List<Object> selectEstado(String parameter) throws SQLException {

		String query = "SELECT DISTINCT estado FROM cidades WHERE pais = ? ORDER BY estado";

		// build statement
		pst_select = conn.prepareStatement(query);

		// fill query
		Set(pst_select, 1, parameter);

		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			list.add(rst.getString("estado"));
		}
		
		return list;
	}
}
