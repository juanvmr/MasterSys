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
	private Connection connection;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public ModalidadeDAO(Connection connection) {
		this.connection = connection;
	}

	/* methods: */
	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(*) FROM modalidades WHERE modalidade = ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Modalidade) obj).getModalidade());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check if query return a result
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> select() throws SQLException {

		String query = "SELECT * FROM modalidades ORDER BY modalidade";

		// build query
		pst_select = connection.prepareStatement(query);

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
	public List<Object> select(Object obj) throws SQLException {
		return null;
	}

	@Override
	public Object find(Object obj) throws SQLException {

		String query = "SELECT * FROM modalidades WHERE modalidade = ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Modalidade) obj).getModalidade());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check if query return a result
		Modalidade tmp = null;
		if (rst.next()) {
			tmp = new Modalidade(
				rst.getString("modalidade")
			);
		}

		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		if (this.contains(obj)) return;

		String query = "INSERT INTO modalidades (modalidade) VALUES (?)";

		// build query
		pst_insert = connection.prepareStatement(query);
		
		// fill query
		Modalidade tmp = (Modalidade) obj;

		Set(pst_insert,  1, tmp.getModalidade());
		
		// run query
		pst_insert.execute();
		
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {
		String query = "UPDATE modalidades SET modalidade = ? WHERE x = ?";
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM modalidades WHERE modalidades = ?";

		// build query
		pst_delete = connection.prepareStatement(query);
		
		// fill query
		Modalidade tmp = (Modalidade) obj;

		Set(pst_delete, 1, tmp.getModalidade());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}
	
}
