package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.dao.ActividadAcademicaDao;
import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

@Repository
public class ActividadAcademicaDaoImpl extends BaseDAO implements ActividadAcademicaDao {

	private Connection connection;

	@Override
	public boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones) {
		boolean isOk = true;
		try {
			connection = Conexion.obtenerConexion();
			connection.setAutoCommit(false);
			actividadAcademica = guardarActividadAcademica(actividadAcademica);
			if (actividadAcademica == null)
				isOk = false;

			System.out.println(actividadAcademica);

			if (isOk) // Guardar horarios
				for (HorarioBO horarioBO : horarios) {
					horarioBO.setCodigoProgramacion(actividadAcademica.getCodigo());
					if (guardarHorarioActividad(horarioBO) == null)
						isOk = false;
				}

			if (isOk)// Guardar sesiones
				for (SesionParBO sesionParBO : sesiones) {
					sesionParBO.setCodigoActividadAcademica(actividadAcademica.getCodigo());
					if (guardarSesionPar(sesionParBO) == null)
						isOk = false;
				}
			if (isOk)
				connection.commit();
			else
				connection.rollback();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			System.out.println("finally");
			connection = null;
			// return isOk;
		}

	}

	public ActividadAcademicaBO guardarActividadAcademica(ActividadAcademicaBO actividadAcademica) {
		Connection con = null;
		try {
			con = connection != null ? connection : Conexion.obtenerConexion();
			System.out.println(actividadAcademica.toString());
			CallableStatement cstm = con.prepareCall(
					"{call USP_GUARDAR_ACTIVIDAD(:cod_tutor,:nombre_act,:cod_tema,:vacantes,:out_codigo_act)}");

			cstm.setString("cod_tutor", actividadAcademica.getCodigoTutor());
			cstm.setString("nombre_act", actividadAcademica.getNombre());
			cstm.setInt("cod_tem", actividadAcademica.getCodigoTema());
			cstm.setInt("vacantes", actividadAcademica.getNumeroVacantes());

			cstm.registerOutParameter("out_codigo_act", OracleTypes.INTEGER);

			cstm.execute();

			int codigoActividad = cstm.getInt("out_codigo_act"); // El codigo
																	// generado
																	// ...

			actividadAcademica.setCodigo(codigoActividad);

			return actividadAcademica;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ActividadAcademicaBO> actividades = new ArrayList<ActividadAcademicaBO>();
		try {
			conn = Conexion.obtenerConexion();
			String sql = "SELECT PA.COD_PROGR AS COD_PROGR, PA.CODTUT AS CODTUT, PA.COD_POSTULACION AS COD_POSTULACION, PA.CODIGO_TEMA AS CODIGO_TEMA, PA.NUMVACANTES AS NUMVACANTES, PA.NOMBRE AS NOMBRE "
					+ "FROM MATRICULA_PAR MP, PROG_ACADEMICA_PAR PA WHERE MP.COD_PROGR = PA.COD_PROGR AND MP.A_CODIGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, codAlumno);
			rs = ps.executeQuery();
			while (rs.next()) {
				ActividadAcademicaBO acti = new ActividadAcademicaBO();
				acti.setCodigo(rs.getInt("COD_PROGR"));
				acti.setCodigoPostulacion(rs.getInt("COD_POSTULACION"));
				acti.setCodigoTema(rs.getInt("CODIGO_TEMA"));
				acti.setCodigoTutor(rs.getString("CODTUT"));
				acti.setNombre(rs.getString("NOMBRE"));
				acti.setNumeroVacantes(rs.getInt("NUMVACANTES"));

				actividades.add(acti);
			}
		} catch (Exception err) {
			err.printStackTrace();
			actividades = null;
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return actividades;
	}

	@Override
	public HorarioBO guardarHorarioActividad(HorarioBO horarioBO) {
		Connection con = null;
		try {
			con = connection != null ? connection : Conexion.obtenerConexion();

			CallableStatement cstm = con
					.prepareCall("{call USP_GUARDAR_HORARIO_PAR(:id_prog,:hora_ini,:hora_fin,:dia,:out_id_horario)}");

			cstm.setInt("id_prog", horarioBO.getCodigoProgramacion());
			cstm.setString("hora_ini", horarioBO.getHoraInicio());
			cstm.setString("hora_fin", horarioBO.getHoraFin());
			cstm.setString("dia", horarioBO.getDia());

			cstm.registerOutParameter("out_id_horario", OracleTypes.INTEGER);
			cstm.execute();

			int idInsertado = cstm.getInt("out_id_horario"); // El id que se
																// genera en la
																// DB
			horarioBO.setCodigo(idInsertado);

			return horarioBO;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SesionParBO guardarSesionPar(SesionParBO sesionParBO) {
		Connection conn = null;
		try {
			conn = connection != null ? connection : Conexion.obtenerConexion();

			CallableStatement cstm = conn
					.prepareCall("{call USP_GUARDAR_SESION_PAR(:tipo_ses,:cod_prog,:numero,:fecha,:descripcion)}");
			cstm.setInt("tipo_ses", sesionParBO.getTipo());
			cstm.setInt("cod_prog", sesionParBO.getCodigoActividadAcademica());
			cstm.setInt("numero", sesionParBO.getNumero());
			cstm.setDate("fecha", new java.sql.Date(sesionParBO.getFecha().getTime()));
			cstm.setString("descripcion", sesionParBO.getDescripcion());
			cstm.execute();

			return sesionParBO;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActividadAcademicaBO> listarActividades() {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<ActividadAcademicaBO> listaActividades = new ArrayList<ActividadAcademicaBO>();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_LISTAR_ACTIVIDADES(:out_cursor)}");
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				ActividadAcademicaBO actividad = builActivity(rs);

				listaActividades.add(actividad);
			}
			return listaActividades;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}

	}

	@Override
	public ActividadAcademicaBO obtenerActividad(int codigoActividad) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		ActividadAcademicaBO actividadAcademicaBO = new ActividadAcademicaBO();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call OBTENER_ACTIVIDAD(:cod_actividad,:out_cursor)}");
			cstm.setInt("cod_actividad", codigoActividad);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				actividadAcademicaBO = builActivity(rs);
			}

			return actividadAcademicaBO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<ActividadAcademicaBO> listaActividades = new ArrayList<ActividadAcademicaBO>();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_LISTAR_ACTIVIDADES_TUTOR(:cod_tutor,:out_cursor)}");
			cstm.setString("cod_tutor", codTutor);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				ActividadAcademicaBO actividad = builActivity(rs);
				listaActividades.add(actividad);
			}

			return listaActividades;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<ActividadAcademicaBO> listaActividades = new ArrayList<ActividadAcademicaBO>();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_LISTAR_ACT_MAT_ALUMNO(:cod_alumno,:out_cursor)}");
			cstm.setString("cod_alumno", codAlumno);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				ActividadAcademicaBO actividad = builActivity(rs);
				listaActividades.add(actividad);
			}

			return listaActividades;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}

	@Override
	public List<HorarioBO> horariosPorActividad(int codigoActividad) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<HorarioBO> listaHorarios = new ArrayList<HorarioBO>();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_LISTAR_HORARIOS_PAR(:codigo_actividad,:out_cursor)}");
			cstm.setInt("codigo_actividad", codigoActividad);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				HorarioBO horario = new HorarioBO();
				horario.setCodigo(rs.getInt("ID_HORARIO"));
				horario.setCodigoProgramacion(rs.getInt("COD_PROGR"));
				horario.setHoraInicio(rs.getString("HORA_INI"));
				horario.setHoraFin(rs.getString("HORA_FIN"));
				horario.setDia(rs.getString("DIA_SEMANA"));

				listaHorarios.add(horario);
			}
			return listaHorarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}

	@Override
	public List<SesionParBO> sesionesPorActividad(int codigoActividad) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<SesionParBO> listaSesiones = new ArrayList<SesionParBO>();

		try {
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_LISTAR_SESIONES_PAR(:codigo_actividad,:out_cursor)}");
			cstm.setInt("codigo_actividad", codigoActividad);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while (rs.next()) {
				SesionParBO sesion = new SesionParBO();
				sesion.setTipo(rs.getInt("COD_TIPSES"));
				sesion.setCodigoActividadAcademica(rs.getInt("COD_PROGR"));
				sesion.setNumero(rs.getInt("NUMSESION"));
				sesion.setFecha(rs.getDate("FECHA"));
				sesion.setDescripcion(rs.getString("DESCRIPCION"));

				listaSesiones.add(sesion);
			}
			return listaSesiones;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}

	private ActividadAcademicaBO builActivity(ResultSet rs) throws SQLException {
		ActividadAcademicaBO actividad = new ActividadAcademicaBO();
		actividad.setCodigo(rs.getInt("COD_PROGR"));
		actividad.setNombre(rs.getString("NOMBRE"));
		actividad.setCodigoPostulacion(rs.getInt("COD_POSTULACION"));
		actividad.setCodigoTema(rs.getInt("CODIGO_TEMA"));
		actividad.setNumeroVacantes(rs.getInt("NUMVACANTES"));
		actividad.setVacantesRestantes(rs.getInt("VACANTES_RESTANTES"));

		return actividad;
	}

}
