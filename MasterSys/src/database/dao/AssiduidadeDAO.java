package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.models.Assiduidade;

public class AssiduidadeDAO extends MasterDAO {
	
	/* attributes: */
	private Connection conn;
	
	/* query: */
	private String is_selectAll = "SELECT * FROM assiduidade ORDER BY data_entrada";
	private String is_select = "SELECT * FROM assiduidade WHERE codigo_matricula = ?";
	private String is_insert = "INSERT INTO assiduidade(codigo_matricula, data_entrada) VALUES (?, ?)";
	private String is_update = "UPDATE assiduidade SET data_entrada = ? WHERE codigo_matricula = ?";
	private String is_delete = "DELETE FROM assiduidade WHERE codigo_matricula = ?";
	
	/* statements: */
	private PreparedStatement pst_selectAll, pst_select, pst_insert, pst_update, pst_delete;
	
	/* constructor: */
	public AssiduidadeDAO(Connection conn) throws SQLException {
		
		this.conn = conn;
		
		this.pst_selectAll = conn.prepareStatement(is_selectAll);
		this.pst_select = conn.prepareStatement(is_select);
		this.pst_insert = conn.prepareStatement(is_insert);
		this.pst_update = conn.prepareStatement(is_update);
		this.pst_delete = conn.prepareStatement(is_delete);
		
	}

	@Override
	public int count() throws SQLException {
		return 0;
	}

	/* methods: */
	@Override
	public List<Object> selectAll() throws SQLException {
		
		List<Object> list = new ArrayList<Object>();
		
		// run query and store the result
		ResultSet rst = pst_selectAll.executeQuery();
		
		while (rst.next()) {
			Assiduidade tmp = new Assiduidade(rst.getInt("codigo_matricula"), rst.getDate("data_entrada"));
			list.add(tmp);
		}
		
		return list;
	}
	
	@Override
	public Object select(Object obj) throws SQLException {
		
		Object tmp = null;
		
		// clear previous query
		pst_select.clearParameters();
		
		// fill query
		Set(pst_select, 1, ((Assiduidade) obj).getCodigoMatricula());
		
		// run query and store the result
		ResultSet rst = pst_select.executeQuery();
		
		// check if query return a result
		if (rst.next()) {
			new Assiduidade(rst.getInt("codigo_matricula"), rst.getDate("data_entrada"));
		}
		
		return tmp;
	}

	@Override
	public Object selectByName(String name) throws SQLException {
		return null;
	}

	@Override
	public void insert(Object obj) throws SQLException {

		Assiduidade tmp = (Assiduidade) obj;
		
		// clear previous query
		pst_insert.clearParameters();
		
		// fill query
		Set(pst_insert, 1, tmp.getCodigoMatricula());
		Set(pst_insert, 2, tmp.getDataEntrada());
		
		// run query
		pst_insert.execute();

		// check if query worked
		if (pst_insert.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}
	
	@Override
	public void update(Object obj) throws SQLException {

		Assiduidade tmp = (Assiduidade) obj;
		
		// clear previous query
		pst_update.clearParameters();
		
		// fill query
		Set(pst_update, 1, tmp.getDataEntrada());
		Set(pst_update, 2, tmp.getCodigoMatricula());
		
		// run query
		pst_update.execute();

		// check if query worked
		if (pst_update.getUpdateCount() > 0) {
			this.conn.commit();
		}
	}

	@Override
	public void delete(Object obj) throws SQLException {
		
		Assiduidade tmp = (Assiduidade) obj;
		
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
