/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface CursoIDao.
 */
public interface CursoIDao {
	
	/**
	 * Listar cursos.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Curso>   listarCursos()  throws Exception;
	
	/**
	 * Obtener curso.
	 *
	 * @param codigo the codigo
	 * @return the curso
	 * @throws Exception the exception
	 */
	public               Curso obtenerCurso(String codigo) throws Exception;
	
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
	 */
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento);
	
	/**
	 * Listar cursos aprobados por area conocimiento.
	 *
	 * @param codigoAreaConocimiento the codigo area conocimiento
	 * @param codigoTutor the codigo tutor
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoAreaConocimiento,String codigoTutor) throws Exception;

	/**
	 * Obtener curso tema.
	 *
	 * @param codigoTema the codigo tema
	 * @return the curso BO
	 */
	CursoBO obtenerCursoTema(int codigoTema);
	
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
