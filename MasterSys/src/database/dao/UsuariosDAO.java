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
	private PreparedStatement pst_select;
	private PreparedStatement pst_insert;
	private PreparedStatement pst_update;
	private PreparedStatement pst_delete;

	private PreparedStatement pst_create;
	private PreparedStatement pst_alter;
	private PreparedStatement pst_drop;
	
	private String is_create_role = "CREATE ROLE ? WITH LOGIN ENCRYPTED PASSWORD ? IN ROLE admin";
	private String is_alter_role = "ALTER ROLE ? WITH LOGIN ENCRYPTED PASSWORD ?";
	private String is_drop_role = "DROP ROLE ?";
	
	/* constructor: */
	public UsuariosDAO(Connection conn) throws SQLException {
		this.conn = conn;
	}
	
	/* methods: */
	private void createUser(String username, String password) throws SQLException {
		
		pst_create = conn.prepareStatement(is_create_role);
		
		Set(pst_create, 1, username);
		Set(pst_create, 2, password);
		
		pst_create.execute();
		
		if (pst_create.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	private void alterUser(String username, String new_password) throws SQLException {
		
		pst_alter = conn.prepareStatement(is_alter_role);
		
		Set(pst_alter, 1, username);
		Set(pst_alter, 2, new_password);
		
		pst_alter.execute();
		
		if (pst_alter.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	private void dropUser(String username) throws SQLException {
		
		pst_drop = conn.prepareStatement(is_drop_role);
		
		Set(pst_drop, 1, username);
		
		pst_drop.execute();
		
		if (pst_drop.getUpdateCount() > 0) {
			conn.commit();
		}
	}

	@Override
	public List<Object> selectAll() throws SQLException {

		String query = "SELECT * FROM usuarios ORDER BY usuario";

		pst_select = conn.prepareStatement(query);

		ResultSet rst = pst_select.executeQuery();

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

		pst_select = conn.prepareStatement(query);
		
		Set(pst_select, 1, ((Usuario) parameter).getUsuario());
		
		ResultSet rst = pst_select.executeQuery();

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
		
		Set(pst_insert,  1, tmp.getUsuario());
		Set(pst_insert,  2, tmp.getPerfil());

		// run query
		pst_insert.execute();

		// create database user
		createUser(tmp.getUsuario(), tmp.getPassword());

		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE usuarios SET perfil = ? WHERE usuario = ?";

		pst_update = conn.prepareStatement(query);
		
		Usuario tmp = (Usuario) obj;
		
		Set(pst_update,  1, tmp.getPerfil());
		Set(pst_update,  2, tmp.getUsuario());
		
		pst_update.execute();

		alterUser(tmp.getUsuario(), tmp.getPassword());

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
		
		Set(pst_delete, 1, tmp.getUsuario());

		// run query
		pst_delete.execute();

		// drop user from database
		dropUser(tmp.getUsuario());
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
