package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Graduacao;
import database.models.Modalidade;

public class GraduacaoDAO extends MasterDAO {
	
	/* attributes: */
	private Connection connection;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public GraduacaoDAO(Connection connection) {
		this.connection = connection;
	}

	/* methods: */
	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(graduacao) FROM graduacoes WHERE modalidade = ? AND graduacao = ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		int pos = 0;
		Set(pst_select, ++pos, ((Graduacao) obj).getModalidade());
		Set(pst_select, ++pos, ((Graduacao) obj).getGraduacao());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> select() throws SQLException {

		String query = "SELECT * FROM graduacoes ORDER BY modalidade, graduacao";

		// build query
		pst_select = connection.prepareStatement(query);

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Graduacao tmp = new Graduacao(
				rst.getString("modalidade"),
				rst.getString("graduacao")
			);
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public List<Object> select(Object obj) throws SQLException {

		String query = "SELECT * FROM graduacoes WHERE modalidade = ? ORDER BY modalidade, graduacao";

		// build query
		pst_select = connection.prepareStatement(query);

		if (obj instanceof Graduacao) {
			Set(pst_select, 1, ((Graduacao) obj).getModalidade());
		} else if (obj instanceof Modalidade) {
			Set(pst_select, 1, ((Modalidade) obj).getModalidade());
		} else if (obj instanceof String) {
			Set(pst_select, 1, obj);
		}

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<>();
		while (rst.next()) {
			Graduacao tmp = new Graduacao(
					rst.getString("modalidade"),
					rst.getString("graduacao")
			);
			list.add(tmp);
		}

		return list;
	}
	
	@Override
	public Object find(Object obj) throws SQLException {

		String query = "SELECT * FROM graduacoes WHERE modalidade = ? AND graduacao = ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Graduacao) obj).getModalidade());
		Set(pst_select, 2, ((Graduacao) obj).getGraduacao());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		Object tmp = null;
		if (rst.next()) {
			tmp = new Graduacao(
				rst.getString("modalidade"),
				rst.getString("graduacao")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		if (this.contains(obj)) {
			System.err.println("GraduacaoDAO: " + obj);
			return;
		}

		String query = "INSERT INTO graduacoes(modalidade, graduacao) VALUES (?, ?)";

		// build query
		pst_insert = connection.prepareStatement(query);

		// fill query
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_insert,  1, tmp.getModalidade());
		Set(pst_insert,  2, tmp.getGraduacao());

		// run query
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE graduacoes SET modalidade = ? WHERE graduacao = ?";

		// build query
		pst_update = connection.prepareStatement(query);

		// fill query
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_update, 1, tmp.getModalidade());
		Set(pst_update, 2, tmp.getGraduacao());

		// run query
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			this.connection.commit();
		}

	}
	
	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM graduacoes WHERE modalidade = ? AND graduacao = ?";

		// build query
		pst_delete = connection.prepareStatement(query);

		// fill query
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_delete, 1, tmp.getModalidade());
		Set(pst_delete, 2, tmp.getGraduacao());

		// run query
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}

}
