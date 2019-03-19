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
	
	/* query: */
	private String is_selectAll = "SELECT * FROM cidades ORDER BY estado DESC, pais DESC";
	private String is_select = "SELECT * FROM cidades WHERE cidade = ? AND estado = ? AND pais = ?";
	private String is_selectPais = "SELECT DISTINCT pais FROM cidades ORDER BY pais";
	private String is_selectEstado = "SELECT DISTINCT estado FROM cidades WHERE pais = ? ORDER BY estado";
	private String is_insert = "INSERT INTO cidades (cidade, estado, pais) VALUES (?, ?, ?)";
	private String is_update = "UPDATE cidades SET cidade = ? WHERE estado = ? AND pais = ?";
	private String is_delete = "DELETE FROM cidades WHERE cidade = ? AND estado = ? AND pais = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_selectPais, pst_selectEstado, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public LocalDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		this.pst_select = conn.prepareStatement(is_select);
		this.pst_selectAll = conn.prepareStatement(is_selectAll);
		this.pst_selectPais = conn.prepareStatement(is_selectPais);
		this.pst_selectEstado = conn.prepareStatement(is_selectEstado);
		this.pst_insert = conn.prepareStatement(is_insert);
		this.pst_update = conn.prepareStatement(is_update);
		this.pst_delete = conn.prepareStatement(is_delete);
		
	}
	
	/* methods: */
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// run query and store the result
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Local tmp = new Local(rst.getString("cidade"), rst.getString("estado"), rst.getString("pais"));
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object Select(Object obj) throws SQLException {
		
		Object tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((Local) obj).getCidade());
		Set(pst_select, 2, ((Local) obj).getEstado());
		Set(pst_select, 3, ((Local) obj).getPais());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		if (rst.next()) {
			tmp = new Local(rst.getString("cidade"), rst.getString("estado"), rst.getString("pais"));
		}
		
		return tmp;
	}
	
	@Override
	public void Insert(Object obj) throws SQLException {
		
		Local tmp = (Local) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
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
	public void Delete(Object obj) throws SQLException {
		
		Local tmp = (Local) obj;
		
		// clear previous query
		pst_delete.clearParameters();
		
		// fill query
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
	
	/* returns a list of string with all different 'paises'. */
	public List<Object> SelectPais() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// clear previous query
		pst_selectPais.clearParameters();
		
		// run query and store the result
		ResultSet rst = pst_selectPais.executeQuery();
		
		while (rst.next()) {
			list.add(rst.getString("pais"));
		}
		
		return list;
	}
	
	/* returns a list of strings with all the different 'estados' */
	public List<Object> SelectEstado(String parameter) throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// clear previous query
		pst_selectEstado.clearParameters();
		
		// fill query
		Set(pst_selectEstado, 1, parameter);
		
		// run query and store the result
		ResultSet rst = pst_selectEstado.executeQuery();
		
		while (rst.next()) {
			list.add(rst.getString("estado"));
		}
		
		return list;
	}
	
	@Override
	public void Update(Object obj) throws SQLException {
		
		// we are note using this method
		Local tmp = (Local) obj;
		
		// clear previous query
		pst_update.clearParameters();
		
		// fill query
		Set(pst_update, 1, tmp.getCidade());
		Set(pst_update, 2, tmp.getEstado());
		Set(pst_update, 3, tmp.getPais());
		
		System.out.println(pst_update.toString());

	}
}
