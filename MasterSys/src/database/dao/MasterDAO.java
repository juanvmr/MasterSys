package database.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

public abstract class MasterDAO {
	
	public abstract List<Object> SelectAll() throws SQLException;
	
	public abstract Object Select(Object obj) throws SQLException;
	
	public abstract void Update(Object obj) throws SQLException;
	
	public abstract void Insert(Object obj) throws SQLException;
	
	public abstract void Delete(Object obj) throws SQLException;
	
	public void Set(PreparedStatement pst, int position, Object parameter) throws SQLException {
		if (parameter == null) {
			pst.setNull(position, Types.NULL);
		} else if (parameter instanceof String) {
			pst.setString(position,  (String) parameter);
		} else if (parameter instanceof Date) {
//			pst.setDate(position,  new java.sql.Date(((Date) parameter).getTime()));
			pst.setTimestamp(position, new Timestamp(((java.util.Date)parameter).getTime()));
		} else if (parameter instanceof Character) {
			pst.setString(position,  ((Character) parameter).toString());
		} else if (parameter instanceof Integer) {
			pst.setInt(position, (Integer) parameter);
		} else if (parameter instanceof Double) {
			pst.setDouble(position, (Double) parameter);
		}
	}
}
