package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PeriodoServices.
 */
public interface PeriodoServices {

	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<PeriodoBO> getPeriodos();

	/**
	 * Gets the periodo.
	 *
	 * @param id the id
	 * @return the periodo
	 */
	public PeriodoBO getPeriodo(Integer id);

	/**
	 * Gets the periodo actual.
	 *
	 * @return the periodo actual
	 */
	public PeriodoBO getPeriodoActual();

}
