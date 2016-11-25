package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl; 
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.dao.TutoriaIDao; 
import pe.edu.sistemas.unayoe.dao.dominio.Profesor;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaDaoImpl.
 */
@Repository("asistenciaTutoriaDao")
@Transactional
public class TutoriaDaoImpl extends DAOImpl<Profesor,String> implements TutoriaIDao{
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.dao.TutoriaIDao#listarProfesores()
	 */
	public List<Profesor> listarProfesores() throws Exception {		 
		return super.listarTodos(Profesor.class);		 
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.dao.TutoriaIDao#listarCursosDocente(java.lang.String)
	 */
	public List<CursoBO> listarCursosDocente(String usuarioDocente){
		Connection con = null;
		ResultSet rs = null;
		
		List<CursoBO> listaCursosDocente = new ArrayList<CursoBO>();
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOSDOCENTE(?,?)}");
			cstm.setString(1, usuarioDocente);						
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(2);
			
			while(rs.next()){
				CursoBO cursoBO = new CursoBO();
				cursoBO.setcCodigo(rs.getString(1));
				cursoBO.setNombre(rs.getString(2));
				listaCursosDocente.add(cursoBO);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return listaCursosDocente;
	}
	
	/**
	 * Listar alumno tutoria.
	 *
	 * @param usuarioDocente the usuario docente
	 * @param codCurso the cod curso
	 * @param tipoAlumno the tipo alumno
	 * @param modo the modo
	 * @return the list
	 */
	public List<AlumnoBO> listarAlumnoTutoria(String usuarioDocente, String codCurso, int tipoAlumno, int modo){
		Connection con = null;
		ResultSet rs = null;
		
		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		CallableStatement cstm = null;
		
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_ALUMNOSDOCENTE(?,?,?,?,?)}");
			cstm.setString(1, usuarioDocente);	
			cstm.setString(2, codCurso);	
			cstm.setInt(3, tipoAlumno);
			cstm.setInt(4, modo);
			cstm.registerOutParameter(5, OracleTypes.CURSOR);			
			cstm.execute();
			
			rs = (ResultSet) cstm.getObject(5);
			
			while(rs.next()){
				AlumnoBO alumnoBO = new AlumnoBO();
				alumnoBO.setaCodigo(rs.getString(1));
				alumnoBO.setaNombre(rs.getString(2));
				listaAlumnos.add(alumnoBO);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return listaAlumnos;
	}
}
