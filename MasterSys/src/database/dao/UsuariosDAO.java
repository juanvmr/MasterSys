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
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public UsuariosDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		pst_selectAll = conn.prepareStatement(is_selectAll);
		pst_select = conn.prepareStatement(is_select);
		pst_insert = conn.prepareStatement(is_insert);
		pst_update = conn.prepareStatement(is_update);
		pst_delete = conn.prepareStatement(is_delete);
	}
	
	/* methods: */
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
