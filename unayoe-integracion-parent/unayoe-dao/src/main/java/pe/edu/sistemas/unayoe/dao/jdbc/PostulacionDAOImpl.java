package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.IOUtils;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

@Component("postulacionDao")
public class PostulacionDAOImpl implements PostulacionDAO {

	public PostulacionDAOImpl() {
	}

	public boolean deletePostulacion(Integer id) {
		boolean ok = true;
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "DELETE FROM POSTULACION_PAR WHERE COD_POSTULACION = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception err) {
			ok = false;
		}
		return ok;
	}

	public boolean updatePostulacion(PostulacionBO postulacion) {
		return false;
	}

	@Override
	public PostulacionBO getPostulacion(Integer id) {
		PostulacionBO postulacion = new PostulacionBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT A_CODIGO, CODCONVOCATOR, CURRICULUM FROM POSTULACION_PAR WHERE COD_POSTULACION = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				postulacion.setId(id);
				postulacion.setIdConvocatoria(rs.getInt("CODCONVOCATOR"));
				postulacion.setCodigoAlumno(rs.getString("A_CODIGO"));
				postulacion.setArchivoCV(IOUtils.inputStreamToByteArray(rs.getBinaryStream("CURRICULUM")));
			} else {
				postulacion = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			postulacion = null;
		}
		return postulacion;
	}

	public boolean createPostulacion(PostulacionBO postulacion) {

		boolean ok = true;
		Connection conn = null;
		try {
			conn = Conexion.obtenerConexion();
			conn.setAutoCommit(false);

			// registrar la postulacion
			String query = "INSERT INTO POSTULACION_PAR (COD_POSTULACION, A_CODIGO, CODCONVOCATOR, CURRICULUM) VALUES (SEC_COD_POSTULACION.NEXTVAL, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			System.out.println("alumno: " + postulacion.getCodigoAlumno());
			ps.setString(1, postulacion.getCodigoAlumno());
			System.out.println("convocatoria: " + postulacion.getIdConvocatoria());
			ps.setInt(2, postulacion.getIdConvocatoria());
			System.out.println("CV: " + postulacion.getArchivoCV().toString());
			ps.setBinaryStream(3, IOUtils.byteArrayToInputStream(postulacion.getArchivoCV()));
			ps.executeUpdate();

			// registrar la disponibilidad de la postulacion
			String query_disp = "INSERT INTO DISPONIBILIDAD_PAR(ID_DISPONIBILIDAD, COD_POSTULACION, DIA, TURNO) VALUES (SEC_ID_DISPONIBILIDAD.NEXTVAL, SEC_COD_POSTULACION.CURRVAL, ?, ?)";
			PreparedStatement ps_disp = conn.prepareStatement(query_disp);
			for (DisponibilidadTutoriaParBO disponibilidad : postulacion.getDisponibilidad()) {
				System.out.println("dia: " + disponibilidad.getNombreDia());
				ps_disp.setString(1, disponibilidad.getNombreDia());
				System.out.println("turno: " + disponibilidad.getNombreTurno());
				ps_disp.setString(2, disponibilidad.getNombreTurno());
				ps_disp.executeQuery();
			}

			// registrar los temas de la postulacion
			String query_tema = "INSERT INTO ASOC_POSTULACION_TEMA_PAR (COD_POSTULACION, CODIGO_TEMA, FLAG_APROBACION) VALUES (SEC_COD_POSTULACION.CURRVAL, ?, ?)";
			PreparedStatement ps_tema = conn.prepareStatement(query_tema);
			for (Integer id : postulacion.getIdTemas()) {
				System.out.println("id tema: " + id);
				ps_tema.setInt(1, id);
				ps_tema.setInt(2, 0);
				ps_tema.executeUpdate();
			}

			conn.commit();
			ps.close();
			ps_disp.close();
			ps_tema.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			ok = false;

			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
				err.printStackTrace();
			}
		}
		return ok;
	}

	public List<PostulacionBO> getPostulaciones() {
		List<PostulacionBO> postulaciones = new ArrayList<PostulacionBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT COD_POSTULACION, A_CODIGO, CODCONVOCATOR, CURRICULUM FROM POSTULACION_PAR";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PostulacionBO postulacion = new PostulacionBO();
				postulacion.setId(rs.getInt("COD_POSTULACION"));
				postulacion.setIdConvocatoria(rs.getInt("CODCONVOCATOR"));
				postulacion.setCodigoAlumno(rs.getString("A_CODIGO"));
				postulacion.setArchivoCV(IOUtils.inputStreamToByteArray(rs.getBinaryStream("CURRICULUM")));
				postulaciones.add(postulacion);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			postulaciones = null;
		}
		return postulaciones;
	}

	public List<PostulacionBO> getPostulacionesPorConvocatoriaTema(Integer idConvocatoria, Integer idTema) {
		List<PostulacionBO> postulaciones = new ArrayList<PostulacionBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT " + "T1.COD_POSTULACION AS COD_POSTULACION," + "T1.A_CODIGO AS A_CODIGO,"
					+ "T1.CODCONVOCATOR AS CODCONVOCATOR," + "T1.CURRICULUM AS CURRICULUM "
					+ "FROM POSTULACION_PAR T1, ASOC_POSTULACION_TEMA_PAR T2 "
					+ "WHERE T1.COD_POSTULACION = T2.COD_POSTULACION AND T1.CODCONVOCATOR = ? AND T2.CODIGO_TEMA = ? "
					+ "AND FLAG_APROBACION = 0"; // PARA QUE NO MUESTRE LOS YA
													// APROBADOS
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idConvocatoria);
			ps.setInt(2, idTema);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PostulacionBO postulacion = new PostulacionBO();
				postulacion.setId(rs.getInt("COD_POSTULACION"));
				postulacion.setIdConvocatoria(rs.getInt("CODCONVOCATOR"));
				postulacion.setCodigoAlumno(rs.getString("A_CODIGO"));
				postulacion.setArchivoCV(IOUtils.inputStreamToByteArray(rs.getBinaryStream("CURRICULUM")));
				postulaciones.add(postulacion);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			postulaciones = null;
		}
		return postulaciones;
	}

	@Override
	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema) {
		boolean ok = true;
		Connection conn = null;
		try {
			conn = Conexion.obtenerConexion();
			conn.setAutoCommit(false);
			String query = "UPDATE ASOC_POSTULACION_TEMA_PAR SET FLAG_APROBACION = 1 WHERE COD_POSTULACION = ? AND CODIGO_TEMA = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idPostulacion);
			ps.setInt(2, idTema);
			ps.executeUpdate();

			String sql = "select AP.ID_USUARIO AS ID_USUARIO from POSTULACION_PAR PP, ALUMNO_PAR AP where PP.COD_POSTULACION = ? and AP.A_CODIGO = PP.A_CODIGO";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, idPostulacion);
			ResultSet rs = ps2.executeQuery();
			String usuario = null;
			if (rs.next()) {
				usuario = rs.getString("ID_USUARIO");
			} else {
				throw new Exception();
			}

			sql = "select ROL FROM USUARIO_ROL WHERE ID_USUARIO = ? and ROL = 'ROLE_TUTOR_P'";
			PreparedStatement ps3 = conn.prepareStatement(sql);
			ps3.setString(1, usuario);
			ResultSet rs2 = ps3.executeQuery();
			if (!rs2.next()) {
				// no tiene rol de tutor
				System.out.println("Agregando rol de tutor");

				sql = "INSERT INTO USUARIO_ROL (ID_ROL, ID_USUARIO, ROL) values (12, ?, 'ROLE_TUTOR_P')";
				PreparedStatement ps4 = conn.prepareStatement(sql);
				ps4.setString(1, usuario);
				ps4.executeUpdate();
			}

			conn.commit();

			rs2.close();
			ps3.close();
			rs.close();
			ps2.close();
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			ok = false;
		}
		return ok;
	}

}
