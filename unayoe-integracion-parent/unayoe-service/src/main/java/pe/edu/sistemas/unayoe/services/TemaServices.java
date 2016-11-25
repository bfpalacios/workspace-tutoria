package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface TemaServices.
 */
public interface TemaServices {

	/**
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	public List<TemaBO> getTemas();

	/**
	 * Gets the temas.
	 *
	 * @param codigoCurso the codigo curso
	 * @return the temas
	 */
	public List<TemaBO> getTemas(String codigoCurso);

	/**
	 * Gets the tema.
	 *
	 * @param id the id
	 * @return the tema
	 */
	public TemaBO getTema(Integer id);

	/**
	 * Creates the tema.
	 *
	 * @param tema the tema
	 * @return true, if successful
	 */
	public boolean createTema(TemaBO tema);

	/**
	 * Update tema.
	 *
	 * @param tema the tema
	 * @return true, if successful
	 */
	public boolean updateTema(TemaBO tema);

	/**
	 * Delete tema.
	 *
	 * @param tema the tema
	 * @return true, if successful
	 */
	public boolean deleteTema(TemaBO tema);

	/**
	 * Delete tema.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteTema(Integer id);

	/**
	 * Listar temas por curso.
	 *
	 * @param codigoCurso the codigo curso
	 * @return the list
	 */
	public List<TemaBO> listarTemasPorCurso(String codigoCurso);

	/**
	 * Gets the temas por convocatoria.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @return the temas por convocatoria
	 */
	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria);

	/**
	 * Listar temas aprobados por curso.
	 *
	 * @param codigoTutor the codigo tutor
	 * @param codigoCurso the codigo curso
	 * @return the list
	 */
	public List<TemaBO> listarTemasAprobadosPorCurso(String codigoTutor, String codigoCurso);

	/**
	 * Gets the temas por convocatoria curso.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @param codigoCurso the codigo curso
	 * @return the temas por convocatoria curso
	 */
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso);

}
