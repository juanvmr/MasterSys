package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Usuario;

public class UsuariosDAO extends MasterDAO {

	/* attributes: */
	private Connection conn;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	private PreparedStatement pst_create, pst_alter, pst_drop;
	
	/* constructor: */
	public UsuariosDAO(Connection conn) {
		this.conn = conn;
	}
	
	/* methods: */
	private void createUser(String username, String password) throws SQLException {

		String query = "CREATE ROLE ? WITH LOGIN ENCRYPTED PASSWORD ? IN ROLE admin";

		pst_create = conn.prepareStatement(query);
		
		Set(pst_create, 1, username);
		Set(pst_create, 2, password);
		
		pst_create.execute();
		
		if (pst_create.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	private void alterUser(String username, String new_password) throws SQLException {

		String query = "ALTER ROLE ? WITH LOGIN ENCRYPTED PASSWORD ?";

		pst_alter = conn.prepareStatement(query);
		
		Set(pst_alter, 1, username);
		Set(pst_alter, 2, new_password);
		
		pst_alter.execute();
		
		if (pst_alter.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	private void dropUser(String username) throws SQLException {

		String query = "DROP ROLE ?";

		pst_drop = conn.prepareStatement(query);
		
		Set(pst_drop, 1, username);
		
		pst_drop.execute();
		
		if (pst_drop.getUpdateCount() > 0) {
			conn.commit();
		}
	}

	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(usuario) FROM usuarios WHERE usuario = ?";

		// build statement
		pst_select = conn.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Usuario) obj).getUsername());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM usuarios ORDER BY usuario";

		// build statement
		pst_select = conn.prepareStatement(query);

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<Object>();
		while (rst.next()) {
			Usuario tmp = new Usuario(
				rst.getString("usuario"),
				rst.getString("perfil")
			);
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object select(Object parameter) throws SQLException {

		String query = "SELECT * FROM usuarios WHERE usuario = ?";

		// build statement
		pst_select = conn.prepareStatement(query);

		// fill query
		Set(pst_select, 1, ((Usuario) parameter).getUsername());

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		Usuario tmp = null;
		if (rst.next()) {
			tmp = new Usuario(
				rst.getString("usuario"),
				rst.getString("perfil")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO usuarios (usuario, perfil) VALUES (?, ?)";

		// build statement
		pst_insert = conn.prepareStatement(query);

		// fill query
		Usuario tmp = (Usuario) obj;
		
		Set(pst_insert,  1, tmp.getUsername());
		Set(pst_insert,  2, tmp.getPerfil());

		// run query
		pst_insert.execute();

		// create database user
		createUser(tmp.getUsername(), tmp.getPassword());

		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE usuarios SET perfil = ? WHERE usuario = ?";

		// build statement
		pst_update = conn.prepareStatement(query);

		// fill statement
		Usuario tmp = (Usuario) obj;
		
		Set(pst_update,  1, tmp.getPerfil());
		Set(pst_update,  2, tmp.getUsername());

		// run query
		pst_update.execute();

		// change user password
		alterUser(tmp.getUsername(), tmp.getPassword());

		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM usuarios WHERE usuario = ?";

		// build statement
		pst_delete = conn.prepareStatement(query);

		// fill statement
		Usuario tmp = (Usuario) obj;
		
		Set(pst_delete, 1, tmp.getUsername());

		// run query
		pst_delete.execute();

		// drop user from database
		dropUser(tmp.getUsername());
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
