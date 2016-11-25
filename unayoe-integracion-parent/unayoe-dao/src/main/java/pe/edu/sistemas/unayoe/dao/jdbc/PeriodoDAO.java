package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PeriodoDAO.
 */
public interface PeriodoDAO {

	/**
	 * Gets the periodo.
	 *
	 * @param id the id
	 * @return the periodo
	 */
	public PeriodoBO getPeriodo(Integer id);

	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<PeriodoBO> getPeriodos();

	/**
	 * Gets the periodo actual.
	 *
	 * @return the periodo actual
	 */
	public PeriodoBO getPeriodoActual();

}
