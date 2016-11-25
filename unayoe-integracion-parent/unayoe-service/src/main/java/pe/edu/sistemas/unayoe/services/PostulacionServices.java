package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PostulacionServices.
 */
public interface PostulacionServices {

	/**
	 * Registrar postulacion.
	 *
	 * @param postulacion the postulacion
	 * @return true, if successful
	 */
	public boolean registrarPostulacion(PostulacionBO postulacion);

	/**
	 * Gets the lista postulaciones.
	 *
	 * @return the lista postulaciones
	 */
	public List<PostulacionBO> getListaPostulaciones();

	/**
	 * Gets the postulacion.
	 *
	 * @param id the id
	 * @return the postulacion
	 */
	public PostulacionBO getPostulacion(Integer id);

	/**
	 * Update postulacion.
	 *
	 * @param postulacion the postulacion
	 * @return true, if successful
	 */
	public boolean updatePostulacion(PostulacionBO postulacion);

	/**
	 * Removes the postulacion.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean removePostulacion(Integer id);

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
