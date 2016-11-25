package pe.edu.sistemas.unayoe.core.dao;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDAO.
 *
 * @param <Entidad> the generic type
 * @param <Id> the generic type
 */
public interface IDAO<Entidad, Id> {

	/**
	 * Insertar.
	 *
	 * @param entidad the entidad
	 * @throws Exception the exception
	 */
	public void insertar(Entidad entidad) throws Exception;
	
	/**
	 * Actualizar.
	 *
	 * @param entidad the entidad
	 * @throws Exception the exception
	 */
	public void actualizar(Entidad entidad) throws Exception;
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @throws Exception the exception
	 */
	public void eliminar(Serializable id) throws Exception;
	
	/**
	 * Obtener entidad por id.
	 *
	 * @param clase the clase
	 * @param id the id
	 * @return the entidad
	 * @throws Exception the exception
	 */
	public Entidad obtenerEntidadPorId(Class<Entidad> clase, Serializable id) throws Exception;
	
	/**
	 * Listar todos.
	 *
	 * @param clase the clase
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Entidad> listarTodos(Class<Entidad> clase) throws Exception;
}
