package pe.edu.sistemas.unayoe.core.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class Conexion.
 */
public class Conexion {
	
	/**
	 * Obtener conexion.
	 *
	 * @return the connection
	 * @throws SQLException the SQL exception
	 */
	public static Connection obtenerConexion() throws SQLException {

		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","DBTUTORIA", "DBTUTORIA");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
