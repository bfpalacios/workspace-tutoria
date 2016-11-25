package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostulacionDAO.
 */
public interface PostulacionDAO {

	/**
	 * Gets the postulacion.
	 *
	 * @param id the id
	 * @return the postulacion
	 */
	public PostulacionBO getPostulacion(Integer id);

	/**
	 * Gets the postulaciones.
	 *
	 * @return the postulaciones
	 */
	public List<PostulacionBO> getPostulaciones();

	/**
	 * Creates the postulacion.
	 *
	 * @param postulacion the postulacion
	 * @return true, if successful
	 */
	public boolean createPostulacion(PostulacionBO postulacion);

	/**
	 * Update postulacion.
	 *
	 * @param postulacion the postulacion
	 * @return true, if successful
	 */
	public boolean updatePostulacion(PostulacionBO postulacion);

	/**
	 * Delete postulacion.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deletePostulacion(Integer id);

	/**
	 * Gets the postulaciones por convocatoria tema.
	 *
	 * @param idConvocatoria the id convocatoria
	 * @param idTema the id tema
	 * @return the postulaciones por convocatoria tema
	 */
	public List<PostulacionBO> getPostulacionesPorConvocatoriaTema(Integer idConvocatoria, Integer idTema);
	
	/**
	 * Aprobar postulante.
	 *
	 * @param idPostulacion the id postulacion
	 * @param idTema the id tema
	 * @return true, if successful
	 */
	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema);

}
