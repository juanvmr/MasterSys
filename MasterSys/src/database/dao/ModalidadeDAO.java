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
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public ModalidadeDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM modalidades ORDER BY modalidade";

		// build query
		pst_select = conn.prepareStatement(query);

		// run query
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Modalidade tmp = new Modalidade(
				rst.getString("modalidade")
			);
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object select(Object obj) throws SQLException {

		String query = "SELECT * FROM modalidades WHERE modalidade = ?";

		// build query
		pst_select = conn.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Modalidade) obj).getModalidade());

		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		// check if query return a result
		Modalidade tmp = null;
		if (rst.next()) {
			tmp = new Modalidade(rst.getString("modalidade"));
		}

		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO modalidades (modalidade) VALUES (?)";

		// build query
		pst_insert = conn.prepareStatement(query);
		
		// fill query
		Modalidade tmp = (Modalidade) obj;

		Set(pst_insert,  1, tmp.getModalidade());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {
		String query = "UPDATE modalidades SET modalidade = ? WHERE modalidade = ?";
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM modalidades WHERE modalidades = ?";

		// build query
		pst_delete = conn.prepareStatement(query);
		
		// fill query
		Modalidade tmp = (Modalidade) obj;

		Set(pst_delete, 1, tmp.getModalidade());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
}
