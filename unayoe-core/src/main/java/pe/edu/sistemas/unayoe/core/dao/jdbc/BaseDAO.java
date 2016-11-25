package pe.edu.sistemas.unayoe.core.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDAO.
 */
public class BaseDAO {

	/**
	 * Cerrar conexion.
	 *
	 * @param con the con
	 * @throws RuntimeException the runtime exception
	 */
	protected void cerrarConexion(Connection con) throws RuntimeException {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarConexion: " + se);
		}
	}

	/**
	 * Cerrar result set.
	 *
	 * @param rs the rs
	 * @throws RuntimeException the runtime exception
	 */
	protected void cerrarResultSet(ResultSet rs) throws RuntimeException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarResultSet: " + se);
		}
	}

	/**
	 * Cerrar statement.
	 *
	 * @param stmt the stmt
	 * @throws RuntimeException the runtime exception
	 */
	protected void cerrarStatement(PreparedStatement stmt)
			throws RuntimeException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarStatement: " + se);
		}
	}

	/**
	 * Cerrar callable.
	 *
	 * @param callstmt the callstmt
	 * @throws RuntimeException the runtime exception
	 */
	protected void cerrarCallable(CallableStatement callstmt)
			throws RuntimeException {
		try {
			if (callstmt != null) {
				callstmt.close();
			}
		} catch (SQLException se) {
			System.err.println("Error: cerrarCallable: " + se);
		}
	}
}

