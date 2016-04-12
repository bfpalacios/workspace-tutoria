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

@Component("postulacionServices")
public class PostulacionServicesImpl implements PostulacionServices {

	@Autowired
	private PostulacionDAO postulacionDao;

	@Autowired
	private DisponibilidadParDAO disponibilidadParDao;

	@Autowired
	private AlumnoParDAO alumnoParDao;

	public PostulacionServicesImpl() {

	}

	public List<PostulacionBO> getListaPostulaciones() {
		return postulacionDao.getPostulaciones();
	}

	public boolean registrarPostulacion(PostulacionBO postulacion) {
		return postulacionDao.createPostulacion(postulacion);
	}

	public PostulacionBO getPostulacion(Integer id) {
		return postulacionDao.getPostulacion(id);
	}

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

	public boolean removePostulacion(Integer id) {
		return postulacionDao.deletePostulacion(id);
	}

	public boolean updatePostulacion(PostulacionBO postulacion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema) {
		return this.postulacionDao.aprobarPostulante(idPostulacion, idTema);
	}

}