package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConvocatoriaServices.
 */
public interface ConvocatoriaServices {

	/**
	 * Creates the convocatoria.
	 *
	 * @param convocatoria the convocatoria
	 * @return true, if successful
	 */
	public boolean createConvocatoria(ConvocatoriaBO convocatoria);

	/**
	 * Gets the convocatorias.
	 *
	 * @return the convocatorias
	 */
	public List<ConvocatoriaBO> getConvocatorias();

	/**
	 * Gets the convocatoria.
	 *
	 * @param id the id
	 * @return the convocatoria
	 */
	public ConvocatoriaBO getConvocatoria(Integer id);

	/**
	 * Delete convocatoria.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteConvocatoria(Integer id);
	
	/**
	 * Update convocatoria.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean updateConvocatoria(Integer id);
	
	/**
	 * Gets the convocatoria actual.
	 *
	 * @return the convocatoria actual
	 */
	public ConvocatoriaBO getConvocatoriaActual();

}
