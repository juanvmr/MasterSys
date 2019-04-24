package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.models.FaturaMatricula;

public class FaturaMatriculaDAO extends MasterDAO {
	
	/* attributes: */
	private Connection connection;
	private PreparedStatement pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public FaturaMatriculaDAO(Connection connection) {
		this.connection = connection;
	}

	/* methods: */
	@Override
	public int count(Object obj) throws SQLException {

		String query = "SELECT COUNT(*) FROM faturas_matriculas WHERE codigo_matricula = ? AND data_vencimento = ?";

		// clear previous query
		pst_select = connection.prepareStatement(query);

		// fill query
		int pos = 0;
		Set(pst_select, ++pos, ((FaturaMatricula) obj).getCodigoMatricula());
		Set(pst_select, ++pos, ((FaturaMatricula) obj).getDataVencimento());

		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		// check if query return a result
		if (rst.next()) {
			return rst.getInt(1);
		}
		return 0;
	}

	@Override
	public List<Object> select() throws SQLException {

		String query = "SELECT * FROM faturas_matriculas ORDER BY data_vencimento";

		// build query
		pst_select = connection.prepareStatement(query);
		
		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<Object>();
		while (rst.next()) {
			FaturaMatricula tmp = new FaturaMatricula(
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
	public List<Object> select(Object obj) throws SQLException {

		String query = "SELECT * FROM faturas_matriculas WHERE codigo_matricula = ? ORDER BY data_vencimento";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		if (obj instanceof FaturaMatricula) {
			Set(pst_select, 1, ((FaturaMatricula) obj).getCodigoMatricula());
		} else if (obj instanceof Integer) {
			Set(pst_select, 1, obj);
		}

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<Object>();
		while (rst.next()) {
			FaturaMatricula tmp = new FaturaMatricula(
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
	public Object find(Object obj) throws SQLException {

		String query = "SELECT * FROM faturas_matriculas WHERE codigo_matricula = ? AND data_vencimento = ?";

		// clear previous query
		pst_select = connection.prepareStatement(query);
		
		// fill query
		int pos = 0;
		Set(pst_select, ++pos, ((FaturaMatricula) obj).getCodigoMatricula());
		Set(pst_select, ++pos, ((FaturaMatricula) obj).getDataVencimento());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		Object tmp = null;
		if (rst.next()) {
			tmp = new FaturaMatricula(
				rst.getInt("codigo_matricula"),
				rst.getDate("data_vencimento"),
				rst.getDouble("valor"),
				rst.getDate("data_pagamento"),
				rst.getDate("data_cancelamento")
			);
		}
		
		return tmp;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		String query = "INSERT INTO faturas_matriculas(codigo_matricula, data_vencimento, valor, " +
			"data_pagamento, data_cancelamento) VALUES (?, ?, ?, ?, ?)";
		
		// build query
		pst_update = connection.prepareStatement(query);
		
		// fill query
		FaturaMatricula tmp = (FaturaMatricula) obj;

		int pos = 0;
		Set(pst_update, ++pos, tmp.getCodigoMatricula());
		Set(pst_update, ++pos, tmp.getDataVencimento());
		Set(pst_update, ++pos, tmp.getValor());
		Set(pst_update, ++pos, tmp.getDataPagamento());
		Set(pst_update, ++pos, tmp.getDataCancelamento());
		
		// run query
		pst_update.execute();

		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}

	@Override
	public void update(Object obj) throws SQLException {

		String query = "UPDATE faturas_matriculas SET data_vencimento = ?, valor = ?, data_pagamento = ?, " +
			"data_cancelamento = ? WHERE codigo_matricula = ?";
		
		// build query
		pst_insert = connection.prepareStatement(query);
		
		// fill query
		FaturaMatricula tmp = (FaturaMatricula) obj;

		Set(pst_insert, 1, tmp.getDataVencimento());
		Set(pst_insert, 2, tmp.getValor());
		Set(pst_insert, 3, tmp.getDataPagamento());
		Set(pst_insert, 4, tmp.getDataCancelamento());
		Set(pst_insert, 5, tmp.getCodigoMatricula());
		
		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {

		String query = "DELETE FROM faturas_matriculas WHERE codigo_matricula = ? AND data_vencimento = ?";

		// build query
		pst_delete = connection.prepareStatement(query);
		
		// fill query
		FaturaMatricula tmp = (FaturaMatricula) obj;

		int pos = 0;
		Set(pst_select, ++pos, tmp.getCodigoMatricula());
		Set(pst_select, ++pos, tmp.getDataVencimento());
		
		// run query
		pst_delete.execute();

		// check if query worked
		if (pst_delete.getUpdateCount() > 0) {
			this.connection.commit();
		}
	}

	public double getFaturaValue(int codigo_matricula, Date data_vencimento) throws SQLException {
		String query = "SELECT SUM(c.valor_mensal) FROM ((matriculas a " +
			"INNER JOIN matriculas_modalidades b ON a.codigo_matricula = b.codigo_matricula) " +
			"INNER JOIN planos c ON b.modalidade = c.modalidade AND b.plano = c.plano) " +
			"WHERE a.codigo_matricula = ? " +
			"AND a.data_matricula < ? " +
			"AND b.data_inicio < ? " +
			"AND b.data_fim > ? " +
			"AND isNull(a.data_encerramento) " +
			"OR a.data_encerramento > ?";

		// build query
		pst_select = connection.prepareStatement(query);

		// fill query
		int pos = 0;
		Set(pst_select, ++pos, codigo_matricula);
		Set(pst_select, ++pos, data_vencimento);
		Set(pst_select, ++pos, data_vencimento);
		Set(pst_select, ++pos, data_vencimento);
		Set(pst_select, ++pos, data_vencimento);

		// run query and store the result
		ResultSet rst = pst_select.executeQuery();

		// check if query return a result
		double value = 0;
		if (rst.next()) {
			value = rst.getDouble(1);
		}
		return value;
	}

	public List<Object> select(Date fromDate, Date toDate, String type) throws SQLException {

		String query = "SELECT a.*, b.codigo_aluno, c.aluno FROM ((faturas_matriculas a " +
			"INNER JOIN matriculas b ON a.codigo_matricula = b.codigo_matricula) " +
			"INNER JOIN alunos c ON b.codigo_aluno = c.codigo_aluno) " +
			"WHERE a.data_vencimento >= ? AND a.data_vencimento <= ?";

		switch (type) {
			case "Todas":
				break;
			case "Em Aberto":
				query += " AND isNull(a.data_pagamento)";        // EM ABERTO
				break;
			case "Pagas":
				query += " AND !isNull(a.data_pagamento)";        // PAGAS
				break;
			case "Canceladas":
				query += " AND !isNull(a.data_cancelamento)";    // CANCELADAS
				break;
		}

		pst_select = connection.prepareStatement(query);

		int pos = 0;
		Set(pst_select, ++pos, fromDate);
		Set(pst_select, ++pos, toDate);

		// run query
		ResultSet rst = pst_select.executeQuery();

		// check result
		List<Object> list = new ArrayList<Object>();
		while (rst.next()) {
			FaturaMatricula tmp = new FaturaMatricula(
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
}
