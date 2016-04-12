package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.dao.dominio.Alumno;


public interface AlumnoIDao {

	public void insertarAlumno(Alumno alumno)  throws Exception;
	
	public Alumno obtenerAlumno(String usuario) throws Exception; 
}
