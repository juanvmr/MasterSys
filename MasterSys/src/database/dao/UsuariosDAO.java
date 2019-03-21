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
	
	/* query: */
	private String is_selectAll = "SELECT * FROM usuarios ORDER BY usuario";
	private String is_select = "SELECT * FROM usuarios WHERE usuario = ? ORDER BY usuario";
	private String is_insert = "INSERT INTO usuarios (usuario, perfil) VALUES (?, ?)";
	private String is_update = "UPDATE usuarios SET perfil = ? WHERE usuario = ?";
	private String is_delete = "DELETE FROM usuarios WHERE usuario = ?";
	
	private String is_create_role = "CREATE ROLE ? WITH LOGIN ENCRYPTED PASSWORD ? IN ROLE admin";
	private String is_alter_role = "ALTER ROLE ? WITH LOGIN ENCRYPTED PASSWORD ?";
	private String is_drop_role = "DROP ROLE ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	private PreparedStatement pst_create_role, pst_alter_role, pst_drop_role;
	
	/* constructor: */
	public UsuariosDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_select = conn.prepareStatement(is_select);
		pst_insert = conn.prepareStatement(is_insert);
		pst_update = conn.prepareStatement(is_update);
		pst_delete = conn.prepareStatement(is_delete);
		
		pst_create_role = conn.prepareStatement(is_create_role);
		pst_alter_role = conn.prepareStatement(is_alter_role);
		pst_drop_role = conn.prepareStatement(is_drop_role);
	}
	
	/* methods: */
	public void CreateUser(String username, String password) throws SQLException {
		
		pst_create_role.clearParameters();
		
		Set(pst_create_role, 1, username);
		Set(pst_create_role, 2, password);
		
		pst_create_role.execute();
		
		if (pst_create_role.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	public void ChangeUserPassword(String username, String new_password) throws SQLException {
		
		pst_alter_role.clearParameters();
		
		Set(pst_alter_role, 1, username);
		Set(pst_alter_role, 2, new_password);
		
		pst_alter_role.execute();
		
		if (pst_alter_role.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	public void DeleteUser(String username) throws SQLException {
		
		pst_drop_role.clearParameters();
		
		Set(pst_drop_role, 1, username);
		
		pst_drop_role.execute();
		
		if (pst_drop_role.getUpdateCount() > 0) {
			conn.commit();
		}
	}
	
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Usuario tmp = new Usuario(rst.getString("usuario"), rst.getString("perfil"));
			list.add(tmp);
		}
		
		return list;
	}

	@Override
	public Object Select(Object parameter) throws SQLException {
		
		Usuario tmp = null;
		
		Set(pst_select, 1, ((Usuario) parameter).getUsuario());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			tmp = new Usuario(rst.getString("usuario"), rst.getString("perfil"));
		}
		
		return tmp;
	}

	@Override
	public void Insert(Object obj) throws SQLException {
		
		pst_insert.clearParameters();
		
		Usuario tmp = (Usuario) obj;
		
		Set(pst_insert,  1, tmp.getUsuario());
		Set(pst_insert,  2, tmp.getPerfil());
		
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void Update(Object obj) throws SQLException {
		
		pst_update.clearParameters();
		
		Usuario tmp = (Usuario) obj;
		
		Set(pst_update,  1, tmp.getPerfil());
		Set(pst_update,  2, tmp.getUsuario());
		
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Delete(Object obj) throws SQLException {
		
		Usuario tmp = (Usuario) obj;
		
		Set(pst_delete, 1, tmp.getUsuario());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

}
