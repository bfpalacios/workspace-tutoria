package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConvocatoriaDAO.
 */
public interface ConvocatoriaDAO {

	/**
	 * Gets the convocatoria.
	 *
	 * @param id the id
	 * @return the convocatoria
	 */
	public ConvocatoriaBO getConvocatoria(Integer id);

	/**
	 * Gets the convocatorias.
	 *
	 * @return the convocatorias
	 */
	public List<ConvocatoriaBO> getConvocatorias();

	/**
	 * Delete convocatoria.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteConvocatoria(Integer id);

	/**
	 * Creates the convocatoria.
	 *
	 * @param convocatoria the convocatoria
	 * @return true, if successful
	 */
	public boolean createConvocatoria(ConvocatoriaBO convocatoria);

	/**
	 * Update convocatoria.
	 *
	 * @param convocatoria the convocatoria
	 * @return true, if successful
	 */
	public boolean updateConvocatoria(ConvocatoriaBO convocatoria);

	/**
	 * Gets the convocatoria actual.
	 *
	 * @return the convocatoria actual
	 */
	public ConvocatoriaBO getConvocatoriaActual();

}
