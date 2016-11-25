package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

/**
 * Interface para GET,UPDATE,CREATE y DELETE Alumno
 * @author Bruno Palacios
 *
 */

public interface AlumnoParServices {

	public AlumnoParBO getAlumno(String codigo);
	
	public AlumnoParBO getAlumnoPorUsuario(String usuario);

	public boolean updateAlumno(AlumnoParBO alumno);

	public boolean createAlumno(AlumnoParBO alumno);

	public boolean deleteAlumno(String codigo);

}
