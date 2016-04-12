package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;

 

public interface AsistenciaServices {

	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoClase(String a_codigo ,Integer anio ,Integer periodo ,String fdesde ,String fhasta) throws Exception;
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception;
	public List<AsistenciaCAlumnoBO> buscarAsistenciaTutorTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception;
}
