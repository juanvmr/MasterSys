package database.dao;

import java.sql.*;
import java.util.Date;
import java.util.List;

public abstract class MasterDAO {

	/* methods: */

	public abstract int count(Object obj) throws SQLException;

	public abstract List<Object> select() throws SQLException;

	public abstract List<Object> select(Object obj) throws SQLException;
	
	public abstract Object find(Object obj) throws SQLException;

	public abstract void insert(Object obj) throws SQLException;

	public abstract void update(Object obj) throws SQLException;
	
	public abstract void delete(Object obj) throws SQLException;

	public boolean contains(Object obj) throws SQLException {
		return count(obj) > 0;
	};
	
	public void Set(PreparedStatement pst, int position, Object parameter) throws SQLException {
		if (parameter == null) {
			pst.setNull(position, Types.NULL);
		} else if (parameter instanceof Integer) {
			pst.setInt(position, (Integer) parameter);
		} else if (parameter instanceof Float) {
			pst.setFloat(position, (Float) parameter);
		} else if (parameter instanceof Double) {
			pst.setDouble(position, (Double) parameter);
		} else if (parameter instanceof Character) {
			pst.setString(position,  ((Character) parameter).toString());
		} else if (parameter instanceof String) {
			pst.setString(position,  (String) parameter);
		} else if (parameter instanceof Date) {
			pst.setTimestamp(position, new Timestamp(((java.util.Date)parameter).getTime()));
		}
	}
}
