package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Graduacao;

public class GraduacaoDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM graduacoes ORDER BY modalidade, graduacao";
	private String is_select = "SELECT * FROM graduacoes WHERE modalidade = ? ORDER BY graduacao";
	private String is_insert = "INSERT INTO graduacoes(modalidade, graduacao) VALUES (?, ?)";
	private String is_update = "UPDATE graduacoes SET modalidade = ? WHERE graduacao = ?";
	private String is_delete = "DELETE FROM graduacoes WHERE modalidade = ? AND graduacao = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public GraduacaoDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		this.pst_selectAll = conn.prepareStatement(is_selectAll);
		this.pst_select = conn.prepareStatement(is_select);
		this.pst_insert = conn.prepareStatement(is_insert);
		this.pst_update = conn.prepareStatement(is_update);
		this.pst_delete = conn.prepareStatement(is_delete);
		
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Graduacao tmp = new Graduacao(rst.getString("modalidade"), rst.getString("graduacao"));
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object select(Object obj) throws SQLException {
		
		Object tmp = null;
		
		Set(pst_select, 1, ((Graduacao) obj).getModalidade());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			tmp = new Graduacao(rst.getString("modalidade"), rst.getString("graduacao"));
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {
		
		pst_insert.clearParameters();
		
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_insert,  1, tmp.getModalidade());
		Set(pst_insert,  2, tmp.getGraduacao());
		
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {
		
		pst_update.clearParameters();
		
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_update, 1, tmp.getModalidade());
		Set(pst_update, 2, tmp.getGraduacao());
		
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}

	}
	
	@Override
	public void delete(Object obj) throws SQLException {
		
		Graduacao tmp = (Graduacao) obj;
		
		Set(pst_delete, 1, tmp.getModalidade());
		Set(pst_delete, 2, tmp.getGraduacao());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
}
