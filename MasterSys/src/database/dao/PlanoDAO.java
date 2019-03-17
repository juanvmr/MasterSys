package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Aluno;
import database.models.Plano;

public class PlanoDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM planos ORDER BY plano, modalidade";
	private String is_select = "SELECT * FROM planos WHERE modalidade = ?";
	private String is_insert = "INSERT INTO planos(modalidade, plano, valor_mensal) VALUES (?, ?, ?)";
	private String is_update = "UPDATE planos SET modalidade = ?, plano = ?, valor_mensal = ? WHERE modalidade = ? AND plano = ?";
	private String is_delete = "DELETE FROM planos WHERE modalidade = ? AND plano = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public PlanoDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		this.pst_selectAll = conn.prepareStatement(is_selectAll);
		this.pst_select = conn.prepareStatement(is_select);
		this.pst_insert = conn.prepareStatement(is_insert);
		this.pst_update = conn.prepareStatement(is_update);
		this.pst_delete = conn.prepareStatement(is_delete);
		
	}
	
	/* methods: */
	@Override
	public List<Object> SelectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Plano tmp = new Plano(rst.getString("modalidade"), rst.getString("plano"), rst.getFloat("valor_mensal"));
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object Select(Object obj) throws SQLException {
		
		Object tmp = null;
		
		Set(pst_select, 1, ((Plano) obj).getModalidade());
		
		ResultSet rst = pst_select.executeQuery();
		
		if (rst.next()) {
			tmp = new Plano(rst.getString("modalidade"), rst.getString("plano"), rst.getFloat("valor_mensal"));
		}
		
		return tmp;
	}
	
	@Override
	public void Insert(Object obj) throws SQLException {
		
		pst_insert.clearParameters();
		
		Plano tmp = (Plano) obj;
		
		Set(pst_insert,  1, tmp.getModalidade());
		Set(pst_insert,  2, tmp.getPlano());
		Set(pst_insert,  3, tmp.getValor());
		
		pst_insert.execute();
				
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void Delete(Object obj) throws SQLException {
		
		Plano tmp = (Plano) obj;
		
		Set(pst_delete, 1, tmp.getModalidade());
		Set(pst_delete, 2, tmp.getPlano());
		
		pst_delete.execute();
		
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void Update(Object obj) throws SQLException {
		
		pst_update.clearParameters();
		
		Plano tmp = (Plano) obj;
		
		Set(pst_update, 1, tmp.getModalidade());
		Set(pst_update, 2, tmp.getPlano());
		Set(pst_update, 3, tmp.getValor());
		
		pst_update.execute();
		
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}

	}
}