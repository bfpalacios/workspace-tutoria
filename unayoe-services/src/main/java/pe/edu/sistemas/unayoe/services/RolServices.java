package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

/**
 * Interfaz Servicios de Rol
 * @author Bruno Palacios
 *
 */

public interface RolServices {

	public List<RolBO> listarRoles();
	public List<RolBO> getRolesByUser(String id);
	
}
