package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import pe.edu.sistemas.unayoe.dao.ComunIDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

public class ComunDAOImpl extends BaseDAO implements ComunIDAO{
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
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);
		}
		return listaTablaMaestra;
	}
	
	public String buscarCicloAcademico(Integer idCiclo){
		Connection con = null;
		CallableStatement cstm = null;
		
		String cicloAcademico = ""; 		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_CICLOACADEMICO(?,?)}");
			cstm.setInt(1, idCiclo);						
			cstm.registerOutParameter(2, OracleTypes.VARCHAR);			
			cstm.execute();			
			cicloAcademico = cstm.getString(2)==null?"":cstm.getString(2);		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarCallable(cstm);
			this.cerrarConexion(con);
		}
		return cicloAcademico;
	}
	
	public List<UsuarioBO> obtenerRoles(int proceso){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<UsuarioBO> rolesUsuarios = new ArrayList<UsuarioBO>(); 		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_ROL(?,?)}");						
			cstm.setInt(1, proceso);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){
				UsuarioBO usuario = new UsuarioBO();
				usuario.setIdRol(String.valueOf(rs.getInt(1)));
				usuario.setRole(rs.getString(2));
				rolesUsuarios.add(usuario);
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
		return rolesUsuarios;
	}
	
	public MatriculaBO buscarCiclo(Integer idCiclo){		
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;		
		
		MatriculaBO datosCiclo = new MatriculaBO();
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_CICLO(?,?)}");
			cstm.setInt(1, idCiclo);						
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();		
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				datosCiclo.setAnio(String.valueOf(rs.getInt(1)));	
				datosCiclo.setPeriodo(String.valueOf(rs.getInt(2)));
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
		return datosCiclo;
	}
	
	public List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		List<ClaseMaestra> listaHoraFin = new ArrayList<ClaseMaestra>(); 		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call ACTUALIZAR_HORAFIN_GENERAL(?,?)}");						
			cstm.setInt(1, idHoraInicio);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){
				ClaseMaestra horaFin = new ClaseMaestra();
				horaFin.setIdCampo(rs.getInt(1));
				horaFin.setValorCampo(rs.getString(2));
				listaHoraFin.add(horaFin);
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
	
	public AreaConocimientoBO buscarDatosAreaConocimiento(String codArea){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		AreaConocimientoBO areaConocimiento = new AreaConocimientoBO(); 
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOS_AREACONOCIMIENTO(?,?)}");						
			cstm.setString(1, codArea);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				areaConocimiento.setCodAreaConocimiento(rs.getString(1));
				areaConocimiento.setNomAreaConocimiento(rs.getString(2));				
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
		return areaConocimiento;
	}
	
	public CursoBO buscarDatosCurso(String codCurso){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		CursoBO curso = new CursoBO(); 		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOS_CURSO(?,?)}");						
			cstm.setString(1, codCurso);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));						
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
		return curso;
	}
	
	public CicloBO buscarCicloActual() {		
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		CicloBO ciclo = new CicloBO();		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_CICLOACTUAL(?)}");
			cstm.registerOutParameter(1, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(1);
			
			while(rs.next()){				
				ciclo.setAnio(rs.getInt(1));
				ciclo.setPeriodo(rs.getInt(2));				
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
		return ciclo;
	}
	
	public ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia){
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		ClaseMaestra datosFrecuencia = new ClaseMaestra();		
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call BUSCAR_DATOSFRECUENCIA(?,?)}");
			cstm.setInt(1, codFrecuencia);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){				
				datosFrecuencia.setIdCampo(rs.getInt(1));
				datosFrecuencia.setValorCampo(rs.getString(2));		
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
		return datosFrecuencia;
	}
	
	public TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, 
			                              String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws SQLException, Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		TutoriaBO sesionTutoriaBO = new TutoriaBO();
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call BUSCAR_SESION_TUTORIA(?,?,?,?,?,?,?,?,?)}");
		cstm.setInt(1, anio);
		cstm.setInt(2, periodo);
		cstm.setString(3, codCurso);
		cstm.setString(4, codTutor);
		cstm.setString(5, codAlumno);			
		cstm.setInt(6, sesionTutoria);
		cstm.setInt(7, modo);
		cstm.setInt(8, tipoAlumno);
		cstm.registerOutParameter(9, OracleTypes.CURSOR);			
		cstm.execute();
			
		rs = (ResultSet) cstm.getObject(9);
			
		while(rs.next()){				
			sesionTutoriaBO.settAnio(String.valueOf(rs.getInt(1)));
			sesionTutoriaBO.settPeriodo(String.valueOf(rs.getInt(2)));
			sesionTutoriaBO.settCodigo(rs.getString(3));
			sesionTutoriaBO.setSesion(rs.getInt(4));
			sesionTutoriaBO.setFecha(rs.getString(5));
			sesionTutoriaBO.setHoraIni(rs.getString(6));
			sesionTutoriaBO.setHoraFin(rs.getString(7));
			sesionTutoriaBO.setEstadoSesion(rs.getString(8));		
		}
		
		this.cerrarResultSet(rs);
		this.cerrarCallable(cstm);
		this.cerrarConexion(con);
		
		return sesionTutoriaBO;
	}
	
	public int obtenerUltimaSesionTutoria(String codTutoria) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		int ultimaSesionTutoria = 0;
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call BUSCAR_ULTIMA_SESION(?,?)}");		
		cstm.setString(1, codTutoria);		
		cstm.registerOutParameter(2, OracleTypes.INTEGER);			
		cstm.execute();
			
		ultimaSesionTutoria = cstm.getInt(2);
		
		if (cstm.wasNull()){
			ultimaSesionTutoria = 0;
		}
		
		this.cerrarResultSet(rs);
		this.cerrarCallable(cstm);
		this.cerrarConexion(con);
		
		return ultimaSesionTutoria;
	}
	
	public IndicadoresBO buscarIndicador(int codIndicador) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		
		IndicadoresBO indicadores = new IndicadoresBO();
		
		con = Conexion.obtenerConexion();
		cstm = con.prepareCall("{call INDICADORES(?,?)}");
		cstm.setInt(1, codIndicador);		
		cstm.registerOutParameter(2, OracleTypes.CURSOR);			
		cstm.execute();
			
		rs = (ResultSet) cstm.getObject(2);
			
		while(rs.next()){				
			indicadores.setIndicador(rs.getString(1));
			indicadores.setIndicadorObservados(rs.getInt(2));
			indicadores.setIndicadorRegulares(rs.getInt(3));
		}
		
		this.cerrarResultSet(rs);
		this.cerrarCallable(cstm);
		this.cerrarConexion(con);
		
		return indicadores;
	}


	public List<ClaseMaestra> listarTablaMaestra(String tabla, String campo){
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
}
