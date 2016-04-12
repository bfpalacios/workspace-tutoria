package pe.edu.sistemas.unayoe.dao.jdbc;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

public interface AlumnoParDAO {
	public AlumnoParBO getAlumno(String codigo);
	
	public AlumnoParBO getAlumnoPorUsuario(String usuario);

	public boolean updateAlumno(AlumnoParBO alumno);

	public boolean createAlumno(AlumnoParBO alumno);

	public boolean deleteAlumno(String codigo);
}
