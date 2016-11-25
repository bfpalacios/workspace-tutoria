/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.dao.dominio.Alumno;


// TODO: Auto-generated Javadoc
/**
 * The Interface AlumnoIDao.
 */
public interface AlumnoIDao {

	/**
	 * Insertar alumno.
	 *
	 * @param alumno the alumno
	 * @throws Exception the exception
	 */
	public void insertarAlumno(Alumno alumno)  throws Exception;
	
	/**
	 * Obtener alumno.
	 *
	 * @param usuario the usuario
	 * @return the alumno
	 * @throws Exception the exception
	 */
	public Alumno obtenerAlumno(String usuario) throws Exception; 
}
