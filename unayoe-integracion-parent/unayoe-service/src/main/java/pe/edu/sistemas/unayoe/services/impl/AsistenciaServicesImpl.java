package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import pe.edu.sistemas.unayoe.dao.jdbc.AsistenciaAlumnoDAO;
import pe.edu.sistemas.unayoe.services.AsistenciaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;

 

@Service("asistenciaServices")
public class AsistenciaServicesImpl implements AsistenciaServices {

	@Autowired
	private AsistenciaAlumnoDAO asistenciaAlumnoDAO;
	

	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoClase(String a_codigo ,Integer anio ,Integer periodo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaAlumnoClase(a_codigo, anio, periodo, fdesde, fhasta) ;
	}

	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaAlumnoTutotria(a_codigo, fdesde, fhasta) ;
	}
	
	public List<AsistenciaCAlumnoBO> buscarAsistenciaTutorTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception {
		return asistenciaAlumnoDAO.buscarAsistenciaTutorTutotria(a_codigo, fdesde, fhasta) ;
	}
	
}
