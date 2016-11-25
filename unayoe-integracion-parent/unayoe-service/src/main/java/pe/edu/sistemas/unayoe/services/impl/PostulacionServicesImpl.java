package pe.edu.sistemas.unayoe.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoParDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.DisponibilidadParDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.PostulacionDAO;
import pe.edu.sistemas.unayoe.services.PostulacionServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostulacionServicesImpl.
 */
@Component("postulacionServices")
public class PostulacionServicesImpl implements PostulacionServices {

	/** The postulacion dao. */
	@Autowired
	private PostulacionDAO postulacionDao;

	/** The disponibilidad par dao. */
	@Autowired
	private DisponibilidadParDAO disponibilidadParDao;

	/** The alumno par dao. */
	@Autowired
	private AlumnoParDAO alumnoParDao;

	/**
	 * Instantiates a new postulacion services impl.
	 */
	public PostulacionServicesImpl() {

	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#getListaPostulaciones()
	 */
	public List<PostulacionBO> getListaPostulaciones() {
		return postulacionDao.getPostulaciones();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#registrarPostulacion(pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO)
	 */
	public boolean registrarPostulacion(PostulacionBO postulacion) {
		return postulacionDao.createPostulacion(postulacion);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#getPostulacion(java.lang.Integer)
	 */
	public PostulacionBO getPostulacion(Integer id) {
		return postulacionDao.getPostulacion(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#getPostulacionesPorConvocatoriaTema(java.lang.Integer, java.lang.Integer)
	 */
	public List<PostulacionBO> getPostulacionesPorConvocatoriaTema(Integer idConvocatoria, Integer idTema) {
		List<PostulacionBO> postulaciones = postulacionDao.getPostulacionesPorConvocatoriaTema(idConvocatoria, idTema);
		for (PostulacionBO postu : postulaciones) {
			postu.setDisponibilidad(this.disponibilidadParDao.getDisponibilidad(postu.getId()));
			AlumnoParBO alumno = this.alumnoParDao.getAlumno(postu.getCodigoAlumno());
			postu.setNombres(alumno.getNombre());
			postu.setApellidos(alumno.getApellidos());
			Integer edad = (new Date().getYear() - alumno.getFecha_nac().getYear());
			postu.setEdad(edad);
		}
		return postulaciones;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#removePostulacion(java.lang.Integer)
	 */
	public boolean removePostulacion(Integer id) {
		return postulacionDao.deletePostulacion(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#updatePostulacion(pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO)
	 */
	public boolean updatePostulacion(PostulacionBO postulacion) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PostulacionServices#aprobarPostulante(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema) {
		return this.postulacionDao.aprobarPostulante(idPostulacion, idTema);
	}

}