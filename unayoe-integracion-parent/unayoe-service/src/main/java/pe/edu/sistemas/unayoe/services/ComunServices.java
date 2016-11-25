package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComunServices.
 */
public interface ComunServices {
	
	/**
	 * Buscar ciclo actual.
	 *
	 * @return the ciclo BO
	 * @throws Exception the exception
	 */
	CicloBO buscarCicloActual() throws Exception;
	
	/**
	 * Buscar ciclo.
	 *
	 * @param codCiclo the cod ciclo
	 * @return the matricula BO
	 * @throws Exception the exception
	 */
	MatriculaBO buscarCiclo(int codCiclo) throws Exception;
	
	/**
	 * Buscar datos curso.
	 *
	 * @param codCurso the cod curso
	 * @return the curso BO
	 * @throws Exception the exception
	 */
	CursoBO buscarDatosCurso(String codCurso) throws Exception;
	
	/**
	 * Buscar indicador.
	 *
	 * @param codIndicador the cod indicador
	 * @return the indicadores BO
	 * @throws Exception the exception
	 */
	IndicadoresBO buscarIndicador(int codIndicador) throws Exception;
	
	/**
	 * Obtener ultima sesion tutoria.
	 *
	 * @param codTutoria the cod tutoria
	 * @return the int
	 * @throws Exception the exception
	 */
	int obtenerUltimaSesionTutoria(String codTutoria) throws Exception;
	
	/**
	 * Obtener datos frecuencia.
	 *
	 * @param codFrecuencia the cod frecuencia
	 * @return the clase maestra
	 * @throws Exception the exception
	 */
	ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia) throws Exception;
	
	/**
	 * Actualizar hora fin.
	 *
	 * @param idHoraInicio the id hora inicio
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio) throws Exception;
	
	/**
	 * Buscar datos area conocimiento.
	 *
	 * @param codArea the cod area
	 * @return the area conocimiento BO
	 * @throws Exception the exception
	 */
	AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception;
	
	/**
	 * Listar clase maestra.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ClaseMaestra> listarClaseMaestra(String tabla, String campo) throws Exception;
	
	/**
	 * Obtener sesion tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codCurso the cod curso
	 * @param codTutor the cod tutor
	 * @param codAlumno the cod alumno
	 * @param sesionTutoria the sesion tutoria
	 * @param modo the modo
	 * @param tipoAlumno the tipo alumno
	 * @return the tutoria BO
	 * @throws Exception the exception
	 */
	TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception;
	
	/**
	 * Listar tabla maestra.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception;
}
