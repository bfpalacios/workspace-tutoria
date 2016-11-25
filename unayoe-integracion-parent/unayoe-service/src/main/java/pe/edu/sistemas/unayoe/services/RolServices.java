package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface RolServices.
 */
public interface RolServices {

	/**
	 * Listar roles.
	 *
	 * @return the list
	 */
	public List<RolBO> listarRoles();
	
	/**
	 * Gets the roles by user.
	 *
	 * @param id the id
	 * @return the roles by user
	 */
	public List<RolBO> getRolesByUser(String id);
	
}
