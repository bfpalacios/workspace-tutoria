package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

public interface RolDAO {
	
	public List<RolBO> listarRoles();
	public List<RolBO> getRolesByUser(String id);

}
