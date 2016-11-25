package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import pe.edu.sistemas.unayoe.dao.jdbc.AsistenciaAlumnoDAO;
import pe.edu.sistemas.unayoe.services.AsistenciaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;

 

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaServicesImpl.
 */
@Service("asistenciaServices")
public class AsistenciaServicesImpl implements AsistenciaServices {

	/** The asistencia alumno DAO. */
	@Autowired
	private AsistenciaAlumnoDAO asistenciaAlumnoDAO;
	

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AsistenciaServices#buscarAsistenciaAlumnoClase(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoClase(String a_codigo ,Integer anio ,Integer periodo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaAlumnoClase(a_codigo, anio, periodo, fdesde, fhasta) ;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AsistenciaServices#buscarAsistenciaAlumnoTutotria(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaAlumnoTutotria(a_codigo, fdesde, fhasta) ;
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AsistenciaServices#buscarAsistenciaTutorTutotria(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaTutorTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaTutorTutotria(a_codigo, fdesde, fhasta) ;
	}
	
}
