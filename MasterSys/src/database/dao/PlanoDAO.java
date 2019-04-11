package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Plano;

public class PlanoDAO extends MasterDAO {

	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public PlanoDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM planos ORDER BY plano, modalidade";

		// build statement
		pst_select = conn.prepareStatement(query);

		// run query
		ResultSet rst = pst_select.executeQuery();

		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Plano tmp = new Plano(
				rst.getString("modalidade"),
				rst.getString("plano"),
				rst.getFloat("valor_mensal")
			);
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object select(Object obj) throws SQLException {

		String query = "SELECT * FROM planos WHERE modalidade = ?";

		// build statement
		pst_select = conn.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Plano) obj).getModalidade());

		// run query
		ResultSet rst = pst_select.executeQuery();

		Object tmp = null;
		if (rst.next()) {
			tmp = new Plano(rst.getString("modalidade"), rst.getString("plano"), rst.getFloat("valor_mensal"));
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO planos(modalidade, plano, valor_mensal) VALUES (?, ?, ?)";
		
		// build statement
		pst_insert = conn.prepareStatement(query);
		
		// fill query
		Plano tmp = (Plano) obj;

		Set(pst_insert,  1, tmp.getModalidade());
		Set(pst_insert,  2, tmp.getPlano());
		Set(pst_insert,  3, tmp.getValor());
		
		// run query
		pst_insert.execute();
			
		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE planos SET modalidade = ?, plano = ?, valor_mensal = ? WHERE modalidade = ? AND plano = ?";
		
		// clear previous query
		pst_update = conn.prepareStatement(query);
		
		// fill query
		Plano tmp = (Plano) obj;

		Set(pst_update, 1, tmp.getModalidade());
		Set(pst_update, 2, tmp.getPlano());
		Set(pst_update, 3, tmp.getValor());
		
		// run query 
		pst_update.execute();
		
		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM planos WHERE modalidade = ? AND plano = ?";

		// build statement
		pst_delete = conn.prepareStatement(query);
		
		// fill query
		Plano tmp = (Plano) obj;

		Set(pst_delete, 1, tmp.getModalidade());
		Set(pst_delete, 2, tmp.getPlano());
		
		// run query
		pst_delete.execute();
		
		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
}
