package pe.edu.sistemas.unayoe.dao.jdbc;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface AlumnoParDAO.
 */
public interface AlumnoParDAO {
	
	/**
	 * Gets the alumno.
	 *
	 * @param codigo the codigo
	 * @return the alumno
	 */
	public AlumnoParBO getAlumno(String codigo);
	
	/**
	 * Gets the alumno por usuario.
	 *
	 * @param usuario the usuario
	 * @return the alumno por usuario
	 */
	public AlumnoParBO getAlumnoPorUsuario(String usuario);

	/**
	 * Update alumno.
	 *
	 * @param alumno the alumno
	 * @return true, if successful
	 */
	public boolean updateAlumno(AlumnoParBO alumno);

	/**
	 * Creates the alumno.
	 *
	 * @param alumno the alumno
	 * @return true, if successful
	 */
	public boolean createAlumno(AlumnoParBO alumno);

	/**
	 * Delete alumno.
	 *
	 * @param codigo the codigo
	 * @return true, if successful
	 */
	public boolean deleteAlumno(String codigo);
}
