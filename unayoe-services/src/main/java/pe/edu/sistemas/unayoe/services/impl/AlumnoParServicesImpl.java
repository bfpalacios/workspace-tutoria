package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoParDAO;
import pe.edu.sistemas.unayoe.services.AlumnoParServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

@Component("alumnoParServices")
public class AlumnoParServicesImpl implements AlumnoParServices {

	@Autowired
	private AlumnoParDAO alumnoParDao;

	public AlumnoParServicesImpl() {

	}

	public boolean createAlumno(AlumnoParBO alumno) {
		return this.alumnoParDao.createAlumno(alumno);
	}

	public AlumnoParBO getAlumnoPorUsuario(String usuario) {
		return this.alumnoParDao.getAlumnoPorUsuario(usuario);
	}

	public boolean deleteAlumno(String codigo) {
		return this.alumnoParDao.deleteAlumno(codigo);
	}

	public AlumnoParBO getAlumno(String codigo) {
		return this.alumnoParDao.getAlumno(codigo);
	}

	public boolean updateAlumno(AlumnoParBO alumno) {
		return false;
	}

}
