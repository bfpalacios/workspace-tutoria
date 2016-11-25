package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.jdbc.RolDAO;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

// TODO: Auto-generated Javadoc
/**
 * The Class RolServicesImpl.
 */
@Component("rolServices")
public class RolServicesImpl implements RolServices {

	/** The rol dao. */
	@Autowired
	private RolDAO rolDao;

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.RolServices#listarRoles()
	 */
	@Override
	public List<RolBO> listarRoles() {
		return rolDao.listarRoles();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.RolServices#getRolesByUser(java.lang.String)
	 */
	@Override
	public List<RolBO> getRolesByUser(String id) {
		return  rolDao.getRolesByUser(id);
	}

}
