package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MatriculaServices.
 */
public interface MatriculaServices {
	
	/**
	 * Guardar matriculas.
	 *
	 * @param lista the lista
	 * @return the int
	 * @throws Exception the exception
	 */
	public int  guardarMatriculas(List<MatriculaBO> lista) throws Exception;
	
	/**
	 * Obtener matricula alumno por periodo.
	 *
	 * @param alumno the alumno
	 * @param anio the anio
	 * @param periodo the periodo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String alumno ,Integer anio ,Integer periodo) throws Exception;

}
