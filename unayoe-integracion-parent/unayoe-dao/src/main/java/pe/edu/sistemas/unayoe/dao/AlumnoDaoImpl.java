/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoDaoImpl.
 */
@Repository("alumnoDao")
@Transactional
public class AlumnoDaoImpl extends DAOImpl<Alumno,String> implements AlumnoIDao{
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.dao.AlumnoIDao#insertarAlumno(pe.edu.sistemas.unayoe.dao.dominio.Alumno)
	 */
	public void insertarAlumno(Alumno alumno) throws Exception {
		//guarda a  un alumno
		super.insertar(alumno);
	}
	
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.dao.AlumnoIDao#obtenerAlumno(java.lang.String)
	 */
	public Alumno obtenerAlumno(String codigo) throws Exception {
		return super.obtenerEntidadPorId(Alumno.class,codigo);
	}

}
