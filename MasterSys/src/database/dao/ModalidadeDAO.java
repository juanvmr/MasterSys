package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Modalidade;

public class ModalidadeDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM modalidades ORDER BY modalidade";
	private String is_select = "SELECT * FROM modalidades WHERE modalidade = ?";
	private String is_insert = "INSERT INTO modalidades (modalidade) VALUES (?)";
	private String is_update = "UPDATE modalidades SET modalidade = ? WHERE modalidade = ?";
	private String is_delete = "DELETE FROM modalidades WHERE modalidades = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public ModalidadeDAO(Connection conn) throws SQLException {
		
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
			Modalidade tmp = new Modalidade(rst.getString("modalidade"));
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public void insert(Object obj) throws SQLException {
		
		Modalidade tmp = (Modalidade) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
		Set(pst_insert,  1, tmp.getModalidade());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {
		
		Modalidade tmp = (Modalidade) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
		Set(pst_delete, 1, tmp.getModalidade());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	/************************************************************
	 * DUVIDAS ???
	 ************************************************************/
	@Override
	public Object select(Object obj) throws SQLException {
		
		Modalidade tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((Modalidade) obj).getModalidade());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		if (rst.next()) {
			tmp = new Modalidade(rst.getString("modalidade"));
		}
		
		return tmp;
	}

	@Override
	public void update(Object obj) throws SQLException {
		
		Modalidade tmp = (Modalidade) obj;
		
		// clear previous query
		pst_update.clearParameters();
		
		// fill query
		Set(pst_update, 1, tmp.getModalidade());
		Set(pst_update, 2, tmp.getModalidade());
		
		System.out.println(pst_update.toString());
	}
	
}
