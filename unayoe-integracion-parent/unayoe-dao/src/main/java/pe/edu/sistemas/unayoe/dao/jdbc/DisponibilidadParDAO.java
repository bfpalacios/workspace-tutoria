package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface DisponibilidadParDAO.
 */
public interface DisponibilidadParDAO {

	/**
	 * Gets the disponibilidad.
	 *
	 * @param idPostulacion the id postulacion
	 * @return the disponibilidad
	 */
	public List<DisponibilidadTutoriaParBO> getDisponibilidad(Integer idPostulacion);



}
