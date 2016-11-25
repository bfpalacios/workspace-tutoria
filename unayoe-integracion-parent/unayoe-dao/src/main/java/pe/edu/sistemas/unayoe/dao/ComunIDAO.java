/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComunIDAO.
 */
public interface ComunIDAO {
	
	/**
	 * Llenar combo.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> llenarCombo(String tabla, String campo) throws Exception;
	
	/**
	 * Buscar ciclo academico.
	 *
	 * @param idCiclo the id ciclo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String buscarCicloAcademico(Integer idCiclo) throws Exception;
	
	/**
	 * Obtener roles.
	 *
	 * @param proceso the proceso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<UsuarioBO> obtenerRoles(int proceso) throws Exception;
	
	/**
	 * Buscar ciclo.
	 *
	 * @param idCiclo the id ciclo
	 * @return the matricula BO
	 * @throws Exception the exception
	 */
	public MatriculaBO buscarCiclo(Integer idCiclo) throws Exception;
	
	/**
	 * Actualizar hora fin.
	 *
	 * @param idHoraInicio the id hora inicio
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio) throws Exception;
	
	/**
	 * Buscar datos area conocimiento.
	 *
	 * @param codArea the cod area
	 * @return the area conocimiento BO
	 * @throws Exception the exception
	 */
	public AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception;
	
	/**
	 * Buscar datos curso.
	 *
	 * @param codCurso the cod curso
	 * @return the curso BO
	 * @throws Exception the exception
	 */
	public CursoBO buscarDatosCurso(String codCurso) throws Exception;
	
	/**
	 * Buscar ciclo actual.
	 *
	 * @return the ciclo BO
	 * @throws Exception the exception
	 */
	public CicloBO buscarCicloActual() throws Exception;
	
	/**
	 * Obtener datos frecuencia.
	 *
	 * @param codFrecuencia the cod frecuencia
	 * @return the clase maestra
	 * @throws Exception the exception
	 */
	public ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia) throws Exception;
	
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
	public TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, 
			                              String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception;
	
	/**
	 * Obtener ultima sesion tutoria.
	 *
	 * @param codTutoria the cod tutoria
	 * @return the int
	 * @throws Exception the exception
	 */
	public int obtenerUltimaSesionTutoria(String codTutoria) throws Exception;
	
	/**
	 * Buscar indicador.
	 *
	 * @param codIndicador the cod indicador
	 * @return the indicadores BO
	 * @throws Exception the exception
	 */
	public IndicadoresBO buscarIndicador(int codIndicador) throws Exception;
	
	/**
	 * Listar tabla maestra.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception;
}
