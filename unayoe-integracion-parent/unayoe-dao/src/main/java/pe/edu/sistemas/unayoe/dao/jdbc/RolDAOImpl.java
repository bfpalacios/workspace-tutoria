package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

@Repository("rolDao")
public class RolDAOImpl implements RolDAO {

	@Override
	public List<RolBO> listarRoles() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<RolBO> roles = new ArrayList<RolBO>();

		try {
			conn = Conexion.obtenerConexion();
			String sql = "SELECT ID_ROL, ROL_USUARIO, DESC_ROL FROM ROL";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				RolBO rol = new RolBO();
				rol.setId(rs.getInt("ID_ROL"));
				rol.setNombre(rs.getString("ROL_USUARIO"));
				rol.setDescripcion(rs.getString("DESC_ROL"));
				roles.add(rol);
			}
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception err2) {
			}
		}
		return roles;
	}
}
