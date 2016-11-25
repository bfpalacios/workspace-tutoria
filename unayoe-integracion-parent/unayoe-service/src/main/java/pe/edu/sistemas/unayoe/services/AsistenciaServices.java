package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;

 

// TODO: Auto-generated Javadoc
/**
 * The Interface AsistenciaServices.
 */
public interface AsistenciaServices {

	/**
	 * Buscar asistencia alumno clase.
	 *
	 * @param a_codigo the a codigo
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param fdesde the fdesde
	 * @param fhasta the fhasta
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoClase(String a_codigo ,Integer anio ,Integer periodo ,String fdesde ,String fhasta) throws Exception;
	
	/**
	 * Buscar asistencia alumno tutotria.
	 *
	 * @param a_codigo the a codigo
	 * @param fdesde the fdesde
	 * @param fhasta the fhasta
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception;
	
	/**
	 * Buscar asistencia tutor tutotria.
	 *
	 * @param a_codigo the a codigo
	 * @param fdesde the fdesde
	 * @param fhasta the fhasta
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaTutorTutotria(String a_codigo ,String fdesde ,String fhasta) throws Exception;
}
