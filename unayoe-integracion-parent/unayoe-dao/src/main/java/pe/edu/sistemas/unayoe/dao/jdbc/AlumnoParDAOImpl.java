package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

@Component("alumnoParDao")
public class AlumnoParDAOImpl implements AlumnoParDAO {
	
	public AlumnoParDAOImpl() {

	}

	@Override
	public boolean createAlumno(AlumnoParBO alumno) {
		boolean ok = true;

		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "INSERT INTO ALUMNO_PAR (A_CODIGO, A_NOMBRE, A_APELLIDOS, A_FNACIMIENTO, A_DIRECCION, A_EMAIL, A_TELEFONO, "
					+ "A_DNI, A_PLAN, FLG_TUTOR_PAR) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, alumno.getCodigo());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellidos());
			ps.setDate(4, new Date(alumno.getFecha_nac().getTime()));
			ps.setString(5, alumno.getDireccion());
			ps.setString(6, alumno.getEmail());
			ps.setInt(7, Integer.parseInt(alumno.getTelefono()));
			ps.setInt(8, Integer.parseInt(alumno.getDni()));
			ps.setInt(9, alumno.getPlan());
			ps.setBoolean(10, alumno.isFlag_tutor());
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean deleteAlumno(String codigo) {
		boolean ok = true;

		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "DELETE FROM ALUMNO_PAR WHERE A_CODIGO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codigo);
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			ok = false;
		}
		return ok;
	}

	public AlumnoParBO getAlumno(String codigo) {
		AlumnoParBO alumno = new AlumnoParBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT A_CODIGO, A_NOMBRE, A_APELLIDOS, A_FNACIMIENTO, A_DIRECCION, A_EMAIL, A_TELEFONO, A_DNI, A_PLAN, FLG_TUTOR_PAR "
					+ "FROM ALUMNO_PAR WHERE A_CODIGO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codigo);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				alumno.setCodigo(rs.getString("A_CODIGO"));
				alumno.setNombre(rs.getString("A_NOMBRE"));
				alumno.setApellidos(rs.getString("A_APELLIDOS"));
				alumno.setFecha_nac(new java.util.Date(rs.getDate("A_FNACIMIENTO").getTime()));
				alumno.setDireccion(rs.getString("A_DIRECCION"));
				alumno.setEmail(rs.getString("A_EMAIL"));
				alumno.setDni("" + rs.getInt("A_DNI"));
				alumno.setPlan(rs.getInt("A_PLAN"));
				alumno.setFlag_tutor(rs.getBoolean("FLG_TUTOR_PAR"));
			} else {
				alumno = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			alumno = null;
		}
		return alumno;
	}
	
	public AlumnoParBO getAlumnoPorUsuario(String usuario) {
		AlumnoParBO alumno = new AlumnoParBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT A_CODIGO, A_NOMBRE, A_APELLIDOS, A_FNACIMIENTO, A_DIRECCION, A_EMAIL, A_TELEFONO, A_DNI, A_PLAN, FLG_TUTOR_PAR "
					+ "FROM ALUMNO_PAR WHERE ID_USUARIO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, usuario);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				alumno.setCodigo(rs.getString("A_CODIGO"));
				alumno.setNombre(rs.getString("A_NOMBRE"));
				alumno.setApellidos(rs.getString("A_APELLIDOS"));
				if(rs.getDate("A_FNACIMIENTO") != null)
					alumno.setFecha_nac(new java.util.Date(rs.getDate("A_FNACIMIENTO").getTime()));
				alumno.setDireccion(rs.getString("A_DIRECCION"));
				alumno.setEmail(rs.getString("A_EMAIL"));
				alumno.setDni("" + rs.getInt("A_DNI"));
				alumno.setPlan(rs.getInt("A_PLAN"));
				alumno.setFlag_tutor(rs.getBoolean("FLG_TUTOR_PAR"));
			} else {
				alumno = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			alumno = null;
		}
		return alumno;
	}

	@Override
	public boolean updateAlumno(AlumnoParBO alumno) {
		return false;
	}

}
