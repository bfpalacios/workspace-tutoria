package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoParDAO;
import pe.edu.sistemas.unayoe.services.AlumnoParServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoParServicesImpl.
 */
@Component("alumnoParServices")
public class AlumnoParServicesImpl implements AlumnoParServices {

	/** The alumno par dao. */
	@Autowired
	private AlumnoParDAO alumnoParDao;

	/**
	 * Instantiates a new alumno par services impl.
	 */
	public AlumnoParServicesImpl() {

	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoParServices#createAlumno(pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO)
	 */
	public boolean createAlumno(AlumnoParBO alumno) {
		return this.alumnoParDao.createAlumno(alumno);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoParServices#getAlumnoPorUsuario(java.lang.String)
	 */
	public AlumnoParBO getAlumnoPorUsuario(String usuario) {
		return this.alumnoParDao.getAlumnoPorUsuario(usuario);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoParServices#deleteAlumno(java.lang.String)
	 */
	public boolean deleteAlumno(String codigo) {
		return this.alumnoParDao.deleteAlumno(codigo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoParServices#getAlumno(java.lang.String)
	 */
	public AlumnoParBO getAlumno(String codigo) {
		return this.alumnoParDao.getAlumno(codigo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoParServices#updateAlumno(pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO)
	 */
	public boolean updateAlumno(AlumnoParBO alumno) {
		return false;
	}

}
