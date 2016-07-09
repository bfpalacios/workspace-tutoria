package pe.edu.sistemas.unayoe.dao.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.TutoriaIJdbcDao;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.DiasClaseBO;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

@Repository("asistenciaTutoriaJdbcDao")
public class TutoriaJdbcDaoImpl  extends BaseDAO implements TutoriaIJdbcDao{
	
	public List<ClaseMaestra> llenarCombo(String tabla, String campo){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<ClaseMaestra> listaTablaMaestra = new ArrayList<ClaseMaestra>(); 
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LEER_TABLAMAESTRA(?,?,?)}");
			cstm.setString(1, tabla);
			cstm.setString(2, campo);				
			cstm.registerOutParameter(3, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(3);
			
			while(rs.next()){
				ClaseMaestra claseMaestra = new ClaseMaestra();
				claseMaestra.setIdCampo(rs.getInt(1));
				claseMaestra.setValorCampo(rs.getString(2));
				listaTablaMaestra.add(claseMaestra);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return listaTablaMaestra;
	}
	
	
	//obtiene la informacion del role_admin
	public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoria(
			String periodo, String anio, String dia, String curso, String fecha) throws Exception {
		 
		
		String query = "select alu.a_codigo, alu.a_nombre, alu.a_apellidos, tut.hora_ini,tut.hora_fin, cur.NOMBRE, asis.fecha_T, "
				+ " asis.ASISTENCIA_T, asis.OBSERVACION_T from tutoria tut "
				+ "inner join profesor pro on pro.p_codigo = tut.p_codigo "
				+ "inner join ASISTENCIA_T_ALUM asis on asis.T_ANIO = tut.t_anio and "
				+ "asis.T_PERIODO = tut.T_PERIODO and asis.t_codigo = tut.T_CODIGO inner join alumno alu "
				+ "on alu.a_codigo = tut.a_codigo inner join curso cur on cur.c_codigo = tut.C_CODIGO "
				+ "where tut.t_anio = ? and "
				+ "tut.t_periodo = ?  and " 
				+ "tut.c_codigo = ?  and "
				+ "trim(tut.dia) = ? and "
				+ "to_char(asis.fecha_T,'dd/mm/yyyy')  = ?  "; 
		
		List<AsistenciaTAlumBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTAlumBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, anio);
			stmt.setString(2,periodo);
			stmt.setString(3,curso);
			stmt.setString(4,dia); 
			stmt.setString(5,fecha); 
			rs = stmt.executeQuery();
			while (rs.next()) {
				AsistenciaTAlumBO asistencia = new AsistenciaTAlumBO();
				
				AlumnoBO alumno = new AlumnoBO();				
				alumno.setaCodigo(rs.getString("a_codigo"));
				alumno.setaNombre(rs.getString("a_nombre"));
				alumno.setaApellido(rs.getString("a_apellidos"));
				asistencia.setAlumno(alumno);
				
				asistencia.setAsistenciaT(rs.getString("ASISTENCIA_T"));
				asistencia.setObservacionT(rs.getString("OBSERVACION_T"));
				 
				DiasClaseBO diasClase = new DiasClaseBO();
				diasClase.setHoraIni(rs.getString("hora_ini"));
				diasClase.setHoraFin(rs.getString("hora_fin")); 
				asistencia.setDiasClases(diasClase);
				listaAsistenciaAlumnoTutoria.add(asistencia);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaAsistenciaAlumnoTutoria.size());
		return listaAsistenciaAlumnoTutoria;
	}
	
	
	//obtiene la informacion del user role_user
		public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoriaUser(
				String periodo, String anio, String dia, String curso, String codigoUser, String horaIni, String horaFin) throws Exception {
			 
			
			String query = "select alu.a_codigo, alu.a_nombre, alu.a_apellidos, tut.hora_ini,tut.hora_fin, cur.NOMBRE,cur.C_CODIGO, asis.fecha_T, "
					+ " asis.ASISTENCIA_T, asis.OBSERVACION_T from tutoria tut "
					+ "inner join profesor pro on pro.p_codigo = tut.p_codigo "
					+ "inner join ASISTENCIA_T_ALUM asis on asis.T_ANIO = tut.t_anio and "
					+ "asis.T_PERIODO = tut.T_PERIODO and asis.t_codigo = tut.T_CODIGO inner join alumno alu "
					+ "on alu.a_codigo = tut.a_codigo inner join curso cur on cur.c_codigo = tut.C_CODIGO "
					+ "where tut.p_codigo = ? and "
					+ "tut.t_anio = ? and "
					+ "tut.t_periodo = ? and "
					+ "tut.hora_ini <= ? and "
					+ "tut.hora_fin >= ?  ";
					//+ "tut.dia = ? ";
			
			List<AsistenciaTAlumBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTAlumBO>();
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = Conexion.obtenerConexion();
				stmt = con.prepareStatement(query);
				stmt.setString(1, codigoUser);
				stmt.setString(2,anio);
				stmt.setString(3,periodo);  
				stmt.setString(4,horaIni); 
				stmt.setString(5,horaFin); 
				//stmt.setString(6,dia); 
				rs = stmt.executeQuery();
				while (rs.next()) {
					AsistenciaTAlumBO asistencia = new AsistenciaTAlumBO();
					
					AlumnoBO alumno = new AlumnoBO();				
					alumno.setaCodigo(rs.getString("a_codigo"));
					alumno.setaNombre(rs.getString("a_nombre"));
					alumno.setaApellido(rs.getString("a_apellidos"));
					asistencia.setAlumno(alumno);
					
					CursoBO cursoBO = new CursoBO();
					cursoBO.setcCodigo(rs.getString("C_CODIGO"));
					cursoBO.setNombre(rs.getString("NOMBRE"));
					asistencia.setCurso(cursoBO);
					asistencia.setAsistenciaT(rs.getString("ASISTENCIA_T"));

					asistencia.setObservacionT(rs.getString("OBSERVACION_T"));
					DiasClaseBO diasClase = new DiasClaseBO();
					diasClase.setHoraIni(rs.getString("hora_ini"));
					diasClase.setHoraFin(rs.getString("hora_fin")); 
					
					listaAsistenciaAlumnoTutoria.add(asistencia);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new DAOExcepcion(e.getMessage());
			} finally {
				this.cerrarResultSet(rs);
				this.cerrarStatement(stmt);
				this.cerrarConexion(con);
			}
			System.out.println(listaAsistenciaAlumnoTutoria.size());
			return listaAsistenciaAlumnoTutoria;
		}

	public List<AsistenciaTProfBO> buscarAsistenciaDocenteTutoria(
			String fecha, String codigoCurso) throws Exception {
		String query = "SELECT C.NOMBRE, P.P_CODIGO, P.P_NOMBRE, P.P_APELLIDOS,ACP.ASISTENCIA "
				+ "FROM  ASISTENCIA_C_PROF ACP inner join PROFESOR P on ACP.P_CODIGO = P.P_CODIGO "
				+ "inner join CURSO C on ACP.C_CODIGO = C.C_CODIGO "
				+ "WHERE C.C_CODIGO = ? "
				+ "AND to_char(ACP.fecha,'dd/mm/yyyy') = ?";
		List<AsistenciaTProfBO> listaAsistenciaAlumnoTutoria = new ArrayList<AsistenciaTProfBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, codigoCurso);
			stmt.setString(2, fecha);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AsistenciaTProfBO asistencia = new AsistenciaTProfBO();
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString("NOMBRE"));
				curso.setNombre(rs.getString("NOMBRE"));
				asistencia.setCurso(curso);
				
				ProfesorBO profesor = new ProfesorBO();
				profesor.setpCodigo(rs.getString("P_CODIGO"));
				profesor.setpNombre(rs.getString("P_NOMBRE"));
				profesor.setpCodigo(rs.getString("P_CODIGO"));
				profesor.setpApellidos(rs.getString("P_APELLIDOS"));				 
				asistencia.setProfesor(profesor);
				
				DiasClaseBO diasClase = new DiasClaseBO();
				asistencia.setDiasClase(diasClase);
				asistencia.getDiasClase().setHoraIni(rs.getString("P_NOMBRE"));
				asistencia.getDiasClase().setHoraFin(rs.getString("P_NOMBRE"));
				asistencia.setAsistenciaT(rs.getString("P_APELLIDOS"));
				asistencia.setObservacionT(rs.getString("ASISTENCIA"));
				 
				
				listaAsistenciaAlumnoTutoria.add(asistencia);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaAsistenciaAlumnoTutoria.size());
		return listaAsistenciaAlumnoTutoria;
	}

	public void insertarTutoria(TutoriaBO tutoriabo) throws Exception{
		boolean esNuevo=false;// para actualizar
		
		String querySaveOrUpdate = "MERGE INTO tutoria t USING "+
		"(SELECT ? T_ANIO,"+
		"? T_PERIODO,"+
		"? T_CODIGO ,"+
		"? A_CODIGO ,"+
		"? P_CODIGO,"+
		"? DIA,"+
		"? HORA_INI,"+
		"? HORA_FIN ,"+
		"? C_CODIGO "+
		"FROM dual "+
		") tutoria ON (t.T_ANIO= tutoria.T_ANIO AND t.T_PERIODO= tutoria.T_PERIODO AND TRIM(t.T_CODIGO) = tutoria.T_CODIGO  ) "+
		"WHEN MATCHED THEN "+
		"UPDATE "+
		"SET "+
		"t.A_CODIGO = tutoria.A_CODIGO, "+
		"t.DIA      = tutoria.DIA, "+
		"t.HORA_INI = tutoria.HORA_INI, "+
		"t.HORA_FIN = tutoria.HORA_FIN, "+
		"t.C_CODIGO = tutoria.C_CODIGO "+
		"WHEN NOT MATCHED THEN "+
		"INSERT "+
		"( "+
		"t.T_ANIO, "+
		"t.T_PERIODO, "+
		"t.T_CODIGO, "+
		"t.A_CODIGO, "+
		"t.P_CODIGO, "+
		"t.DIA, "+
		"t.HORA_INI, "+
		"t.HORA_FIN, "+
		"t.C_CODIGO "+
		") "+
        "VALUES "+
      "("+
      "tutoria.T_ANIO, "+
      "tutoria.T_PERIODO, "+
      "tutoria.T_CODIGO,  "+
      "tutoria.A_CODIGO,  "+
      "tutoria.P_CODIGO,  "+
      "tutoria.DIA,   "+
      "tutoria.HORA_INI, "+
      "tutoria.HORA_FIN, "+
      "tutoria.C_CODIGO "+
      ")";
		
		String t_codigo="codigo";
		Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        try {
        	con = Conexion.obtenerConexion();
            if(tutoriabo.gettCodigo()==null ||tutoriabo.gettCodigo().isEmpty()){// si no tiene codigo
            	esNuevo = true;
            	CallableStatement cstm = con.prepareCall("{call GENERAR_TUTORIA(?,?,?)}");
            	cstm.setInt(1, Integer.parseInt(tutoriabo.gettAnio()));
            	cstm.setInt(2, Integer.parseInt(tutoriabo.gettPeriodo()));
            	cstm.registerOutParameter(3, OracleTypes.VARCHAR);
            	cstm.execute();           	
            	
            	t_codigo = cstm.getString(3);
            	
            	if(t_codigo == null){
            		t_codigo = "T0000001";
            	}
            }
                
            stmt = con.prepareStatement(querySaveOrUpdate);
            stmt.setInt(1,Integer.parseInt(tutoriabo.gettAnio()));
            stmt.setInt(2,Integer.parseInt(tutoriabo.gettPeriodo()));
                if(esNuevo)
                	stmt.setString(3,t_codigo);
                stmt.setString(4,tutoriabo.getaCodigo());
                stmt.setString(5,tutoriabo.getpCodigo());
                stmt.setString(6,tutoriabo.getDia());
                stmt.setString(7,tutoriabo.getHoraIni()+":00:00");
                stmt.setString(8,tutoriabo.getHoraFin()+":00:00");
                stmt.setString(9,tutoriabo.getcCodigo());  
                
                int i = stmt.executeUpdate();
                
                if (i != 1) {
                       //throw new SQLException("No se pudo insertar el grupo");
               	  System.err.println("No se pudo insertar la matricula , el registro ya existe.");
                }

        } catch (SQLException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
                throw new DAOExcepcion(e.getMessage());
        } finally {
                this.cerrarResultSet(rs);
                this.cerrarStatement(stmt);
                this.cerrarConexion(con);
        }
	}
	

	
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio, Integer periodo, String a_codigo, String codUsuario, int procesoTutoria)throws Exception{
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			CallableStatement cstm = con.prepareCall("{call LISTAR_HORARIOSTUTORIA_ALUMNO(?,?,?,?,?,?)}");
	    	cstm.setInt(1, anio);
	    	cstm.setInt(2, periodo);
	    	cstm.setString(3, a_codigo);	    	
	    	cstm.setString(4, codUsuario);
	    	cstm.setInt(5, procesoTutoria);
	    	cstm.registerOutParameter(6, OracleTypes.CURSOR);
	    	cstm.execute();
			
			rs = (ResultSet) cstm.getObject(6);
			
			while (rs.next()) {
				TutoriaBO horarioTutoria = new TutoriaBO();
				
				horarioTutoria.settAnio(String.valueOf(rs.getInt(1)));
				horarioTutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				horarioTutoria.settCodigo(rs.getString(3).trim());			
				horarioTutoria.setaCodigo(rs.getString(4).trim());
				horarioTutoria.setpCodigo(rs.getString(5).trim());
				horarioTutoria.setcCodigo(rs.getString(6).trim());
				horarioTutoria.setDia(rs.getString(7).trim());
				horarioTutoria.setHoraIni(rs.getString(8).trim());
				horarioTutoria.setHoraFin(rs.getString(9).trim());				
				horarioTutoria.setcNombre(rs.getString(10).trim());
				horarioTutoria.setpNombre(rs.getString(11).trim());
				horarioTutoria.setRepitencias(String.valueOf(rs.getInt(12)));
				horarioTutoria.setDesc_frecuencia(rs.getString(13).trim());
				listaHorarioTutoria.add(horarioTutoria);
			}			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio, Integer periodo, String p_codigo) throws Exception{
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			CallableStatement cstm = con.prepareCall("{call LISTAR_HORARIOSTUTORIA_DOCENTE(?,?,?,?)}");
	    	cstm.setInt(1, anio);
	    	cstm.setInt(2, periodo);
	    	cstm.setString(3, p_codigo);	    	
	    	cstm.registerOutParameter(4, OracleTypes.CURSOR);
	    	cstm.execute();
			
			rs = (ResultSet) cstm.getObject(4);
			
			while (rs.next()) {
				TutoriaBO horarioTutoria = new TutoriaBO();
				
				horarioTutoria.settAnio(String.valueOf(rs.getInt(1)));
				horarioTutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				horarioTutoria.settCodigo(rs.getString(3).trim());			
				horarioTutoria.setaCodigo(rs.getString(4).trim());
				horarioTutoria.setpCodigo(rs.getString(5).trim());
				horarioTutoria.setDia(rs.getString(6).trim());
				horarioTutoria.setHoraIni(rs.getString(7).trim());
				horarioTutoria.setHoraFin(rs.getString(8).trim());
				horarioTutoria.setcCodigo(rs.getString(9).trim());
				horarioTutoria.setcNombre(rs.getString(10).trim());
				horarioTutoria.setpNombre(rs.getString(11).trim());//deberia ser setaNombre PERO PARA LA TABLA LO PONEMOS ASÍ
				horarioTutoria.setRepitencias(String.valueOf(rs.getInt(12)));
				horarioTutoria.setDesc_frecuencia(rs.getString(13).trim());
				horarioTutoria.setaNombre(rs.getString(14).trim());//deberia ser setPnombre por profesor pero en la tabla lo ponemos asi
				horarioTutoria.setNum_asistencia_asistio(rs.getInt(15));
				horarioTutoria.setNum_asistencia_falto(rs.getInt(16));
				horarioTutoria.setNum_asistencia_tardanza(rs.getInt(17));
				horarioTutoria.setNum_sesiones(rs.getInt(18));
				horarioTutoria.setNum_tareas_pendiente(rs.getInt(19));
				horarioTutoria.setNum_tareas_parcialmente(rs.getInt(20));
				horarioTutoria.setNum_tareas_cerrado(rs.getInt(21));
				horarioTutoria.setNum_actas(rs.getInt(22));
				listaHorarioTutoria.add(horarioTutoria);
			}			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia)throws Exception{
		List<TutoriaBO> listaHorarioTutoria = new ArrayList<TutoriaBO>();
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = Conexion.obtenerConexion();
			CallableStatement cstm = con.prepareCall("{call LISTAR_HORARIOSTUTORIA_SEMANAL(?,?,?,?,?)}");
	    	cstm.setInt(1, anio);
	    	cstm.setInt(2, periodo);
	    	cstm.setString(3, "%"+dia+"%");
	    	cstm.setString(4, c_codigo);
	    	cstm.registerOutParameter(5, OracleTypes.CURSOR);
	    	cstm.execute();
			
			rs = (ResultSet) cstm.getObject(5);
			
			while (rs.next()) {
				TutoriaBO horarioTutoria = new TutoriaBO();
				
				horarioTutoria.settAnio(String.valueOf(rs.getInt(1)));
				horarioTutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				horarioTutoria.settCodigo(rs.getString(3).trim());			
				horarioTutoria.setaCodigo(rs.getString(4).trim());
				horarioTutoria.setpCodigo(rs.getString(5).trim());
				horarioTutoria.setDia(rs.getString(6).trim());
				horarioTutoria.setHoraIni(rs.getString(7).trim());
				horarioTutoria.setHoraFin(rs.getString(8).trim());
				horarioTutoria.setcCodigo(rs.getString(9).trim());
				horarioTutoria.setcNombre(rs.getString(10).trim());
				horarioTutoria.setaNombre(rs.getString(11).trim());
				horarioTutoria.setpNombre(rs.getString(12).trim());
				horarioTutoria.setRepitencias(String.valueOf(rs.getInt(13)));
				listaHorarioTutoria.add(horarioTutoria);
			}			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		System.out.println(listaHorarioTutoria.size());
		return listaHorarioTutoria;		
	}
	
	public List<ProfesorBO> listarTutoresRegulares(String codCurso){		
		Connection con = null;
		ResultSet rs = null;
		
		List<ProfesorBO> listaProfesores = new ArrayList<ProfesorBO>(); 
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DOCENTE_CURSO_REGULARES(?,?)}");			
			cstm.setString(1, codCurso);				
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
	
			while(rs.next()){
				ProfesorBO profesor = new ProfesorBO();
				profesor.setpNombre(rs.getString(1));
				profesor.setpCodigo(rs.getString(2));
				listaProfesores.add(profesor);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);			
		}
		
		return listaProfesores;
	}
	
	public List<ProfesorBO> listarTutoresObservados(String codCurso) throws Exception{
		Connection con = null;
		ResultSet rs = null;
		
		List<ProfesorBO> listaTutores = new ArrayList<ProfesorBO>(); 
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DOCENTECURSO(?,?,?)}");			
			cstm.setInt(1, 0);
			cstm.setString(2, codCurso);				
			cstm.registerOutParameter(3, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(3);
	
			while(rs.next()){
				ProfesorBO profesor = new ProfesorBO();
				profesor.setpNombre(rs.getString(1));
				profesor.setpCodigo(rs.getString(2));
				listaTutores.add(profesor);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);			
		}		
		return listaTutores;
	}
	
	public List<ClaseMaestra> actualizarHoraFin(int idHoraInicio){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<ClaseMaestra> listaHoraFin = new ArrayList<ClaseMaestra>(); 
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call ACTUALIZAR_HORAFIN(?,?)}");
			cstm.setInt(1, idHoraInicio);							
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){
				ClaseMaestra claseMaestra = new ClaseMaestra();
				claseMaestra.setIdCampo(rs.getInt(1));
				claseMaestra.setValorCampo(rs.getString(2));
				listaHoraFin.add(claseMaestra);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);	
		}
		return listaHoraFin;
	}
	
	public AlumnoBO buscarDatosAlumno(String codAlumno){		
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		AlumnoBO datosAlumno = new AlumnoBO();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOSALUMNO(?,?)}");
			cstm.setString(1, codAlumno);							
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				datosAlumno.setaCodigo(rs.getString(1)==null?"":rs.getString(1).toString());
				datosAlumno.setaNombre(rs.getString(2)==null?"":rs.getString(2).toString());
				datosAlumno.setaFNacimiento(rs.getDate(3)==null?"":rs.getDate(3).toString());
				datosAlumno.setaDireccion(rs.getString(4)==null?"":rs.getString(4));
				datosAlumno.setaEmail(rs.getString(5)==null?"":rs.getString(5));
				datosAlumno.setaTelefono(rs.getString(6)==null?"":rs.getString(6));
				datosAlumno.setaDNI(rs.getString(7)==null?"":rs.getString(7));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return datosAlumno;
	}
	
	public String buscarTutoria(int anio, int periodo, String codCurso, String codAlumno, String codDocente) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
		String codtutoria = "";
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_TUTORIA(?,?,?,?,?,?)}");
	    	cstm.setInt(1, anio);
	    	cstm.setInt(2, periodo);
	    	cstm.setString(3, codCurso);
	    	cstm.setString(4, codAlumno);
	    	cstm.setString(5, codDocente);	    	
	    	cstm.registerOutParameter(6, OracleTypes.VARCHAR);
	    	cstm.execute();           	
	    	
	    	codtutoria = (String) cstm.getObject(6);
	    	
	    	if(codtutoria == null){
	    		codtutoria = "";
	    	}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);			
		}
		return codtutoria;		
	}
	
	public void procesarTutoriaRegulares(TutoriaBO tutoriaBO, int modo) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call PROCESAR_TUTORIA_REGULARES(?,?,?,?,?,?,?,?,?,?,?)}");
	    	cstm.setInt(1, Integer.parseInt(tutoriaBO.gettAnio()));
	    	cstm.setInt(2, Integer.parseInt(tutoriaBO.gettPeriodo()));
	    	cstm.setString(3, tutoriaBO.getcCodigo());
	    	cstm.setString(4, tutoriaBO.getaCodigo());
	    	cstm.setString(5, tutoriaBO.getpCodigo());
	    	cstm.setString(6, tutoriaBO.getDia());
	    	cstm.setString(7, tutoriaBO.getHoraIni());
	    	cstm.setString(8, tutoriaBO.getHoraFin());
	    	cstm.setString(9, tutoriaBO.gettCodigo());
	    	cstm.setInt(10, tutoriaBO.getFrecuencia());
	    	cstm.setInt(11, modo);/*modo 1*/

	    	cstm.execute();     	    			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}
	
	public void procesarTutoriaObservados(TutoriaBO tutoria, String codUsuario, int tipoAlumno){
		Connection con = null;
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call PROCESAR_TUTORIA_OBSERVADOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstm.setString(1, tutoria.getaCodigo());
			cstm.setString(2, tutoria.getaApellidoPaterno());
			cstm.setString(3, tutoria.getaApellidoMaterno());
			cstm.setString(4, tutoria.getaNombre());
			cstm.setInt(5, tutoria.getPlan());
			cstm.setString(6, tutoria.getcCodigo());	
			cstm.setInt(7, Integer.parseInt(tutoria.getRepitencias()));	    	
	    	cstm.setString(8, tutoria.getpCodigo());
	    	cstm.setInt(9, tutoria.getFrecuencia());
	    	cstm.setString(10, tutoria.getDia());
	    	cstm.setString(11, tutoria.getHoraIni());
	    	cstm.setString(12, tutoria.getHoraFin());
	    	cstm.setInt(13, tipoAlumno);
	    	cstm.setString(14, codUsuario);
	    	
	    	cstm.execute();     	    			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}
	
	public List<TutoriaBO> buscarAsistenciaAlumnosTutoria(String codDocente, String codCurso, String codAlumno, String fechaTutoria, int tipoAlumno, int modo){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<TutoriaBO> listaTutoriasAlumno = new ArrayList<TutoriaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOSTUTORIA_ALUMNO(?,?,?,?,?,?,?)}");
			cstm.setString(1, codDocente);
			cstm.setString(2, codCurso);
			cstm.setString(3, codAlumno);
			cstm.setString(4, fechaTutoria);
			cstm.setInt(5, tipoAlumno);
			cstm.setInt(6, modo);
			cstm.registerOutParameter(7, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(7);
			
			while(rs.next()){				
				TutoriaBO tutoriaAlumno = new TutoriaBO();
				tutoriaAlumno.settCodigo(rs.getString(1));
				tutoriaAlumno.setSesion(rs.getInt(2));
				tutoriaAlumno.setaCodigo(rs.getString(3));
				tutoriaAlumno.setaNombre(rs.getString(4));
				tutoriaAlumno.setaApellidos(rs.getString(5));
				tutoriaAlumno.setcCodigo(rs.getString(6));
				tutoriaAlumno.setpCodigo(rs.getString(7));
				tutoriaAlumno.setHoraIni(rs.getString(8));
				tutoriaAlumno.setHoraFin(rs.getString(9));
				tutoriaAlumno.setEstadoSesion(rs.getString(10));
				listaTutoriasAlumno.add(tutoriaAlumno);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return listaTutoriasAlumno;
	}
	
	public void guardarRegistroAsistencia(TutoriaBO asistenciaTutorias, String fecha, int tipoAlumno, int modo){
		Connection con = null;
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call REGISTRAR_ASISTENCIA_ALUMNO_T(?,?,?,?,?,?,?,?)}");			
			cstm.setString(1, asistenciaTutorias.gettCodigo());
			cstm.setInt(2, asistenciaTutorias.getSesion());
			cstm.setString(3, fecha);
			cstm.setString(4, asistenciaTutorias.getAsistencia());
			cstm.setString(5, asistenciaTutorias.getJustificacion());
			cstm.setString(6, asistenciaTutorias.getObservacion());
			System.out.println("OBSERVACIÓN DE TUTORIA: "+ asistenciaTutorias.getObservacion());
			cstm.setInt(7, tipoAlumno);
			cstm.setInt(8, modo);
				
			cstm.execute();			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
	}
	
	public List<AreaConocimientoBO> listarAreaConocimiento(){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<AreaConocimientoBO> listaAreaConocimiento = new ArrayList<AreaConocimientoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_AREACONOCIMIENTO(?)}");			
			cstm.registerOutParameter(1, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(1);
			
			while(rs.next()){				
				AreaConocimientoBO areaConocimiento = new AreaConocimientoBO();
				areaConocimiento.setCodAreaConocimiento(rs.getString(1));
				areaConocimiento.setNomAreaConocimiento(rs.getString(2));
				
				listaAreaConocimiento.add(areaConocimiento);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return listaAreaConocimiento;
	}
	
	public List<CursoBO> listarCursosxAreaConocimiento(String codAreaConocimiento){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<CursoBO> listaCursos = new ArrayList<CursoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOSXAREACONOCIMIENTO(?,?)}");			
			cstm.setString(1, codAreaConocimiento);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));
								
				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return listaCursos;
	}
	
	public List<SesionBO> listarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modo){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<SesionBO> listaSesiones = new ArrayList<SesionBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_SESIONES_TUTORIA(?,?,?,?,?,?,?,?)}");			
			cstm.setInt(1, anio);
			cstm.setInt(2, periodo);			
			cstm.setString(3, codCurso);
			cstm.setString(4, codDocente);
			cstm.setString(5, codAlumno);
			cstm.setInt(6, procesoTutoria);
			cstm.setInt(7, modo);			
			cstm.registerOutParameter(8, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(8);
			
			while(rs.next()){				
				SesionBO sesion = new SesionBO();
				sesion.setAnio(rs.getInt(1));
				sesion.setPeriodo(rs.getInt(2));
				sesion.setCodTutoria(rs.getString(3));
				sesion.setNroSesion(rs.getInt(4));
				sesion.setFechaTutoria(rs.getString(5));
				sesion.setHoraIni(rs.getString(6));
				sesion.setHoraFin(rs.getString(7));
				sesion.setHoraFin(rs.getString(8));	
				listaSesiones.add(sesion);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return listaSesiones;
	}
	
	public SesionBO obtenerActaTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		SesionBO actaSesion = new SesionBO();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call OBTENER_ACTA_TUTORIA(?,?,?,?,?,?,?,?,?)}");			
			cstm.setInt(1, anio);
			cstm.setInt(2, periodo);			
			cstm.setString(3, codCurso);
			cstm.setString(4, codDocente);
			cstm.setString(5, codAlumno);
			cstm.setInt(6, sesion);
			cstm.setInt(7, modo);
			cstm.setInt(8, procesoTutoria);	
			cstm.registerOutParameter(9, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(9);
			
			while(rs.next()){	
				actaSesion.setAnio(rs.getInt(1));
				actaSesion.setPeriodo(rs.getInt(2));
				actaSesion.setCodTutoria(rs.getString(3));
				actaSesion.setNroSesion(rs.getInt(4));
				actaSesion.setEstadoSesion(rs.getString(5));
				actaSesion.setNombreActa(rs.getString(7));
				actaSesion.setEstadoActa(rs.getInt(8));
				byte[] datos = rs.getBytes(6);				
				if(rs.wasNull()){
					datos = "Archivo no encontrado".getBytes();
					actaSesion.setActa(datos);
				}
				else{
					actaSesion.setActa(rs.getBlob(6).getBytes(1, (int)rs.getBlob(6).length()));
				}	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return actaSesion;
	}
	
	public void guardarActaTutoria(int anio, int periodo, String codTutoria, int sesion, byte[] actaTutoria, String nombre, int estadoActa) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		
		InputStream flujoDatosActa = new ByteArrayInputStream(actaTutoria);
		
		con = Conexion.obtenerConexion();		
		cstm = con.prepareCall("{call INSERTAR_ACTA_TUTORIA(?,?,?,?,?,?,?)}");			
		cstm.setInt(1, anio);
		cstm.setInt(2, periodo);		
		cstm.setString(3, codTutoria);
		cstm.setInt(4, sesion);			
		cstm.setBlob(5, flujoDatosActa, actaTutoria.length);
		cstm.setString(6, nombre);		
		cstm.setInt(7, estadoActa);
		
		cstm.execute();		
	}
	
	public void  guardarRegistroDisponibilidad (DisponibilidadBO disponibilidad, String usuario, int modo, int procesoRegistro) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
						
		con = Conexion.obtenerConexion();		
		cstm = con.prepareCall("{call GUARDAR_DISPONIBILIDAD(?,?,?,?,?,?,?)}");	
		
		cstm.setString(1, usuario);
		cstm.setString(2, disponibilidad.getCodCurso());			
		cstm.setString(3, disponibilidad.getDia());
		cstm.setString(4, disponibilidad.getHoraInicio());
		cstm.setString(5, disponibilidad.getHoraFin());
		cstm.setInt(6, modo);
		cstm.setInt(7, procesoRegistro);
		
		cstm.execute();	
	}
	
	public List<ProfesorBO> listarTutoresRegulares() throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<ProfesorBO> listaTutores = new ArrayList<ProfesorBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_TUTORES_REGULARES(?)}");	
			cstm.registerOutParameter(1, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(1);
			
			while(rs.next()){				
				ProfesorBO tutorRegulares = new ProfesorBO();				
				tutorRegulares.setpCodigo(rs.getString(1));		
				tutorRegulares.setpNombre(rs.getString(2));				
				listaTutores.add(tutorRegulares);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaTutores;
	}
	
	public List<AlumnoBO> listarAlumnosRegulares() throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_ALUMNOS_REGULARES(?)}");	
			cstm.registerOutParameter(1, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(1);
			
			while(rs.next()){				
				AlumnoBO alumnoRegular = new AlumnoBO();				
				alumnoRegular.setaCodigo(rs.getString(1));
				alumnoRegular.setaNombre(rs.getString(2));		
				listaAlumnos.add(alumnoRegular);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaAlumnos;
	}
	
	public List<CursoBO> listarCursosxAlumnoRegular(String codAlumno) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<CursoBO> listaCursos = new ArrayList<CursoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOS_REGULARES(?,?)}");	
			cstm.setString(1, codAlumno);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));
				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaCursos;
	}
	
	public List<EncuestaBO> listarPreguntas(String tipoPregunta) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<EncuestaBO> listaPreguntas = new ArrayList<EncuestaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_PREGUNTAS_ENCUESTA(?,?)}");	
			cstm.setString(1, tipoPregunta);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				EncuestaBO encuesta = new EncuestaBO();				
				encuesta.setCodPregunta(rs.getInt(1));
				encuesta.setPregunta(rs.getString(2));
				listaPreguntas.add(encuesta);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaPreguntas;
	}
	
	public List<TutoriaBO> buscarTutoriaxCodigoAlumno(String codAlumno, int tipoAlumno){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<TutoriaBO> listaTutorias = new ArrayList<TutoriaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_TUTORIAXCODIGO_ALUMNO(?,?,?)}");	
			cstm.setString(1, codAlumno);
			cstm.setInt(2, tipoAlumno);
			cstm.registerOutParameter(3, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(3);
			
			while(rs.next()){				
				TutoriaBO tutoria = new TutoriaBO();				
				tutoria.settAnio(String.valueOf(rs.getInt(1)));
				tutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				tutoria.settCodigo(rs.getString(3));
				tutoria.setaCodigo(rs.getString(4));
				tutoria.setpCodigo(rs.getString(5));
				tutoria.setcCodigo(rs.getString(6));
				tutoria.setRepitencias(String.valueOf(rs.getInt(7)));
				tutoria.setDia(rs.getString(8));				
				
				listaTutorias.add(tutoria);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaTutorias;
	}
	
	public void guardarEncuestaInicial(EncuestaBO encuesta, String codTutoria, int modo) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
						
		con = Conexion.obtenerConexion();		
		cstm = con.prepareCall("{call GUARDAR_ENCUESTA_INICIAL(?,?,?,?,?,?,?)}");	
		
		cstm.setString(1, codTutoria);
		cstm.setInt(2, encuesta.getCodPregunta());
		cstm.setString(3, encuesta.getRespuesta());	
		cstm.setString(4, encuesta.getCalificacionTutor());
		cstm.setString(5, encuesta.getCalificacionPsicologa());
		cstm.setString(6, encuesta.getCodPsicologa());
		cstm.setInt(7, modo);
		
		cstm.execute();
	}
	
	public String buscarTutoriaEncuesta(String codTutoria) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		String tutoriaEncuesta = "";
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_TUTORIA_ENCUESTA(?,?)}");	
			cstm.setString(1, codTutoria);			
			cstm.registerOutParameter(2, OracleTypes.VARCHAR);			
			cstm.execute();
			
			tutoriaEncuesta = cstm.getString(2)==null?"":cstm.getString(2);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return tutoriaEncuesta;
	}
	
	public List<AlumnoBO> listarAlumnosRegularesxCurso(String codCurso) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_ALUMNOSCURSO_REGULARES(?,?)}");	
			cstm.setString(1, codCurso);			
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				AlumnoBO alumno = new AlumnoBO();
				alumno.setaCodigo(rs.getString(1));
				alumno.setaNombre(rs.getString(2));					
				listaAlumnos.add(alumno);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaAlumnos;
	}
	
	public List<EncuestaBO> buscarDatosEncuesta(String codTutoria) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<EncuestaBO> listaEncuestas = new ArrayList<EncuestaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOS_ENCUESTA(?,?)}");	
			cstm.setString(1, codTutoria);			
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				EncuestaBO encuesta = new EncuestaBO();
				encuesta.setAnio(rs.getInt(1));
				encuesta.setPeriodo(rs.getInt(2));
				encuesta.setCodTutoria(rs.getString(3));
				encuesta.setCodPsicologa(rs.getString(4));
				encuesta.setPregunta(rs.getString(5));
				encuesta.setRespuesta(rs.getString(6));
				encuesta.setCalificacionTutor(rs.getString(7));
				encuesta.setCalificacionPsicologa(rs.getString(8));	
				encuesta.setCodPregunta(rs.getInt(9));
				listaEncuestas.add(encuesta);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaEncuestas;
	}
	
	public List<TutoriaBO> buscarTutoriaxCodigoAlumnoRegular(String codAlumno) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<TutoriaBO> listaTutorias = new ArrayList<TutoriaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_TUTORIA_ALUMNO_REG(?,?)}");	
			cstm.setString(1, codAlumno);			
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				TutoriaBO tutoria = new TutoriaBO();				
				tutoria.settAnio(String.valueOf(rs.getInt(1)));
				tutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				tutoria.settCodigo(rs.getString(3));
				tutoria.setaCodigo(rs.getString(4));
				tutoria.setpCodigo(rs.getString(5));
				tutoria.setcCodigo(rs.getString(6));
				tutoria.setRepitencias(String.valueOf(rs.getInt(7)));
				tutoria.setDia(rs.getString(8));				
				
				listaTutorias.add(tutoria);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaTutorias;
	}
	
	public void guardarObservacionesAsistencia(String codTutoria, String observacion, String criticidad, int sesion,String razon,String fechaCumplimiento) throws SQLException{
		Connection con = null;
		CallableStatement cstm = null;
						
		con = Conexion.obtenerConexion();		
		cstm = con.prepareCall("{call GUARDAR_OBSERV_ASISTENCIA(?,?,?,?,?,?)}");	
		
		cstm.setString(1, codTutoria);
		cstm.setString(2, razon);
		cstm.setString(3, observacion);		
		cstm.setString(4, criticidad);			
		cstm.setInt(5, sesion);
		cstm.setString(6, fechaCumplimiento);
		
		cstm.execute();
	}
	
	public SesionBO buscarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		SesionBO sesionTutoria = new SesionBO();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_SESION_TUTORIA(?,?,?,?,?,?,?,?,?)}");	
			cstm.setInt(1, anio);
			cstm.setInt(2, periodo);			
			cstm.setString(3, codCurso);
			cstm.setString(4, codDocente);
			cstm.setString(5, codAlumno);
			cstm.setInt(6, sesion);
			cstm.setInt(7, procesoTutoria);
			cstm.setInt(8, modo);	
			cstm.registerOutParameter(9, OracleTypes.CURSOR);
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(9);
			
			while(rs.next()){			
				sesionTutoria.setAnio(rs.getInt(1));
				sesionTutoria.setPeriodo(rs.getInt(2));
				sesionTutoria.setCodTutoria(rs.getString(3));
				sesionTutoria.setNroSesion(rs.getInt(4));
				sesionTutoria.setFechaTutoria(rs.getString(5));
				sesionTutoria.setHoraIni(rs.getString(6));
				sesionTutoria.setHoraFin(rs.getString(7));
				sesionTutoria.setEstadoSesion(rs.getString(8));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return sesionTutoria;
	}
	
	public List<ObservacionBO> listarObservaciones(String codCurso, String codDocente, String codAlumno, int procesoTutoria) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<ObservacionBO> listaObservaciones = new ArrayList<ObservacionBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_OBSERVACIONES(?,?,?,?,?)}");	
			cstm.setString(1, codCurso);
			cstm.setString(2, codDocente);
			cstm.setString(3, codAlumno);
			cstm.setInt(4, procesoTutoria);			
			cstm.registerOutParameter(5, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(5);
			
			while(rs.next()){				
				ObservacionBO observacion = new ObservacionBO();		
				observacion.setAnio(rs.getInt(1));
				observacion.setPeriodo(rs.getInt(2));
				observacion.setCodTutoria(rs.getString(3));
				observacion.setIdObservacion(String.valueOf(rs.getInt(4)));
				observacion.setRazon(rs.getString(5));
				observacion.setTarea(rs.getString(6));
				observacion.setCriticidad(rs.getString(7));
				observacion.setSesionRegistro(String.valueOf(rs.getInt(8)));
				observacion.setFechaRegistro(rs.getString(9));
				observacion.setFecha_entrega(rs.getString(10));
				observacion.setFecha_cumplimiento(rs.getString(11));
				observacion.setEstadoObservacion(rs.getInt(12));				 
				listaObservaciones.add(observacion);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaObservaciones;
	}
	
	public void actualizarEstadoObservacion(ObservacionBO observacion, int indicadorObservacion) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
						
		con = Conexion.obtenerConexion();		
		cstm = con.prepareCall("{call ACTUALIZAR_OBSERVACION_TUTORIA(?,?,?,?,?)}");	
		
		cstm.setString(1, observacion.getCodTutoria());
		cstm.setInt(2, Integer.parseInt(observacion.getSesionRegistro()));
		cstm.setInt(3, Integer.parseInt(observacion.getIdObservacion()));
		cstm.setString(4, observacion.getEstadoControl());				
		cstm.setInt(5, indicadorObservacion);		
		
		cstm.execute();
	}
	
	public List<SesionBO> listarSesionesCierre(String codTutoria, int sesion) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<SesionBO> listaSesionesCierre = new ArrayList<SesionBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_SESIONES_CIERRE(?,?,?)}");	
			cstm.setString(1, codTutoria);			
			cstm.setInt(2, sesion);			
			cstm.registerOutParameter(3, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(3);
			
			while(rs.next()){				
				SesionBO sesionCierre = new SesionBO();
				sesionCierre.setNroSesion(rs.getInt(1));			
				listaSesionesCierre.add(sesionCierre);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaSesionesCierre;
	}
	
	public List<DisponibilidadBO> listarDisponibilidades(String codCurso, String codUsuario, int tipoUsuario) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<DisponibilidadBO> listaDisponibilidades = new ArrayList<DisponibilidadBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_DISPONIBILIDADES(?,?,?,?)}");	
			cstm.setString(1, codCurso);
			cstm.setString(2, codUsuario);
			cstm.setInt(3, tipoUsuario);			
			cstm.registerOutParameter(4, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(4);
			
			while(rs.next()){				
				DisponibilidadBO disponibilidad = new DisponibilidadBO();
				disponibilidad.setDia(rs.getString(1));
				disponibilidad.setHoraInicio(rs.getString(2));
				disponibilidad.setHoraFin(rs.getString(3));		
				listaDisponibilidades.add(disponibilidad);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaDisponibilidades;
	}
	
	public List<TutoriaBO> listarDatosTutoria(String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modoUsuario) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<TutoriaBO> listaDatosTutoria = new ArrayList<TutoriaBO>();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_DATOS_SESIONES_TUTORIA(?,?,?,?,?,?)}");	
			cstm.setString(1, codCurso);
			cstm.setString(2, codDocente);
			cstm.setString(3, codAlumno);
			cstm.setInt(4, procesoTutoria);		
			cstm.setInt(5, modoUsuario);		
			cstm.registerOutParameter(6, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(6);
			
			while(rs.next()){				
				TutoriaBO datosTutoria = new TutoriaBO();
				datosTutoria.settAnio(String.valueOf(rs.getInt(1)));
				datosTutoria.settPeriodo(String.valueOf(rs.getInt(2)));
				datosTutoria.settCodigo(rs.getString(3));
				datosTutoria.setaCodigo(rs.getString(4));
				datosTutoria.setpCodigo(rs.getString(5));
				datosTutoria.setcCodigo(rs.getString(6));
				datosTutoria.setDia(rs.getString(7));
				datosTutoria.setTipoAlumno(rs.getInt(8));
				datosTutoria.setSesion(rs.getInt(9));
				datosTutoria.setFecha(rs.getString(10));
				datosTutoria.setHoraIni(rs.getString(11));
				datosTutoria.setHoraFin(rs.getString(12));
				datosTutoria.setEstadoSesion(rs.getString(13));
				datosTutoria.setEstadoActa(rs.getInt(14));
				listaDatosTutoria.add(datosTutoria);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return listaDatosTutoria;
	}
	
	public int verificarExistenciaObservacion(String codTutoria, int sesion) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		
		int validadorObservacion = 0;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_EXISTENCIA_OBSERVACION(?,?,?)}");	
			cstm.setString(1, codTutoria);
			cstm.setInt(2, sesion);
			cstm.registerOutParameter(3, OracleTypes.INTEGER);			
			cstm.execute();
			
			validadorObservacion = cstm.getInt(3);
			if (cstm.wasNull()){
				validadorObservacion = 0;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{			
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}		
		return validadorObservacion;
	}
}
