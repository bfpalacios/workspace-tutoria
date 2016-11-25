package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface CursoServices.
 */
public interface CursoServices {
	
	/**
	 * Listar cursos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursos() throws Exception;
	
	/**
	 * Guardar cursos.
	 *
	 * @param lista the lista
	 * @throws Exception the exception
	 */
	public void          guardarCursos(List<CursoBO> lista) throws Exception;
	
	/**
	 * Listar cursos por tutor.
	 *
	 * @param codTutor the cod tutor
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception;
	
	/**
	 * Listar cursosx docente regular.
	 *
	 * @param codDocente the cod docente
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception;
	
	/**
	 * Listar cursos docente.
	 *
	 * @param codDocente the cod docente
	 * @param proceso the proceso
	 * @param modo the modo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception;
	
	/**
	 * Listar cursos por area conocimiento.
	 *
	 * @param codigoAreaConocimiento the codigo area conocimiento
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento) throws Exception;
	
	/**
	 * Listar cursos aprobados por area conocimiento.
	 *
	 * @param codigoTutor the codigo tutor
	 * @param codigoAreaConocimiento the codigo area conocimiento
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoTutor, String codigoAreaConocimiento) throws Exception;

	/**
	 * Obtner curso tema.
	 *
	 * @param codigoTema the codigo tema
	 * @return the curso BO
	 */
	CursoBO obtnerCursoTema(int codigoTema);
	
	/**
	 * Listar cursos por convocatoria.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @return the list
	 */
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria);
	
	
	/**
	 * Listar cursos tutorias.
	 *
	 * @return the list
	 */
	public List<CursoBO> listarCursosTutorias();
	
}
