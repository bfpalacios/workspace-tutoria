package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface TemaDAO.
 */
public interface TemaDAO {

	/**
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	public List<TemaBO> getTemas();
	
	/**
	 * Gets the temas por curso.
	 *
	 * @param codigoCurso the codigo curso
	 * @return the temas por curso
	 */
	public List<TemaBO> getTemasPorCurso(String codigoCurso);
	
	/**
	 * Gets the temas por convocatoria.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @return the temas por convocatoria
	 */
	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria);

	/**
	 * Gets the tema.
	 *
	 * @param id the id
	 * @return the tema
	 */
	public TemaBO getTema(Integer id);
	
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
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteTema(Integer id);

	/**
	 * Creates the tema.
	 *
	 * @param tema the tema
	 * @return true, if successful
	 */
	public boolean createTema(TemaBO tema);

	/**
	 * Listar temas aprobados por curso.
	 *
	 * @param codigoTutor the codigo tutor
	 * @param codigoCurso the codigo curso
	 * @return the list
	 */
	List<TemaBO> listarTemasAprobadosPorCurso(String codigoTutor,String codigoCurso);
	
	/**
	 * Gets the temas por convocatoria curso.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @param codigoCurso the codigo curso
	 * @return the temas por convocatoria curso
	 */
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso);
	
}
