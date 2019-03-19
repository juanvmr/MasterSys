package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Assiduidade;
import database.models.Fatura;

public class FaturaDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM faturas_matriculas ORDER BY data_vencimento";
	private String is_select = "SELECT * FROM faturas_matriculas WHERE codigo_matricula = ?";
	private String is_insert = "INSERT INTO faturas_matriculas(codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento) VALUES (?, ?, ?, ?, ?)";
	private String is_update = "UPDATE faturas_matriculas SET data_vencimento = ?, valor = ?, data_pagamento = ?, data_cancelamento = ? WHERE codigo_matricula = ?";
	private String is_delete = "DELETE FROM faturas_matriculas WHERE codigo_matricula = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public FaturaDAO(Connection conn) throws SQLException {
		
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
		
		// run query and store the result
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Fatura tmp = new Fatura(
				rst.getInt("codigo_matricula"),
				rst.getDate("data_vencimento"),
				rst.getDouble("valor"),
				rst.getDate("data_pagamento"),
				rst.getDate("data_cancelamento")
			);
			
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object Select(Object obj) throws SQLException {
		
		Object tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((Assiduidade) obj).getCodigoMatricula());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		if (rst.next()) {
			new Fatura(rst.getInt("codigo_matricula"), rst.getDate("data_vencimento"), rst.getDouble("valor"), rst.getDate("data_pagamento"), rst.getDate("data_cancelamento"));
		}
		
		return tmp;
	}

	@Override
	public void Update(Object obj) throws SQLException {
		
		Fatura tmp = (Fatura) obj;
		
		// clear previous query
		pst_update.clearParameters();
		
		// fill query
		Set(pst_update, 1, tmp.getCodigoMatricula());
		Set(pst_update, 2, tmp.getDataVencimento());
		Set(pst_update, 3, tmp.getValor());
		Set(pst_update, 4, tmp.getDataPagamento());
		Set(pst_update, 5, tmp.getDataCancelamento());
		
		// run query
		pst_update.execute();

		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Insert(Object obj) throws SQLException {
		
		Fatura tmp = (Fatura) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
		Set(pst_insert, 1, tmp.getDataVencimento());
		Set(pst_insert, 2, tmp.getValor());
		Set(pst_insert, 3, tmp.getDataPagamento());
		Set(pst_insert, 4, tmp.getDataCancelamento());
		Set(pst_insert, 5, tmp.getCodigoMatricula());
		
		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void Delete(Object obj) throws SQLException {

		Fatura tmp = (Fatura) obj;
		
		// clear previous query
		pst_delete.clearParameters();
		
		// fill query
		Set(pst_delete, 1, tmp.getCodigoMatricula());
		
		// run query
		pst_delete.execute();

		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
}
