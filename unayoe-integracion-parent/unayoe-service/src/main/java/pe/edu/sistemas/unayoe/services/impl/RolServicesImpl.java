package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.jdbc.RolDAO;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;

@Component("rolServices")
public class RolServicesImpl implements RolServices {

	@Autowired
	private RolDAO rolDao;

	@Override
	public List<RolBO> listarRoles() {
		return rolDao.listarRoles();
	}

	@Override
	public List<RolBO> getRolesByUser(String id) {
		return  rolDao.getRolesByUser(id);
	}

}
