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
	private String is_insert = "INSERT INTO modalidades (modalidade) VALUES (?)";
	private String is_delete = "DELETE FROM modalidades WHERE modalidades = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_insert, pst_delete;
	
	/* constructor: */
	public ModalidadeDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_insert = conn.prepareStatement(is_insert);
		pst_delete = conn.prepareStatement(is_delete);
	}
	
	/* methods: */
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Modalidade tmp = new Modalidade(rst.getString("modalidade"));
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public void Insert(Object obj) throws SQLException {
		
		pst_insert.clearParameters();
		
		Modalidade tmp = (Modalidade) obj;
		
		Set(pst_insert,  1, tmp.getModalidade());
		
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Delete(Object obj) throws SQLException {
		
		Modalidade tmp = (Modalidade) obj;
		
		Set(pst_delete, 1, tmp.getModalidade());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	/************************************************************
	 * O QUE FAZER NESTES METODOS ??
	 ************************************************************/
	@Override
	public Object Select(Object parameter) throws SQLException {
		
		// ???
		
		return null;
	}
	
	@Override
	public void Update(Object obj) throws SQLException {
		
		// ???
		
	}
	
}
