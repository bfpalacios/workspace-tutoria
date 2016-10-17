package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

public interface AlumnoParServices {

	public AlumnoParBO getAlumno(String codigo);
	
	public AlumnoParBO getAlumnoPorUsuario(String usuario);

	public boolean updateAlumno(AlumnoParBO alumno);

	public boolean createAlumno(AlumnoParBO alumno);

	public boolean deleteAlumno(String codigo);

}
