package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.PeriodoDAO;
import pe.edu.sistemas.unayoe.services.PeriodoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

@Service("periodoServices")
public class PeriodoServicesImpl implements PeriodoServices {

	@Autowired
	private PeriodoDAO periodoDao;

	public PeriodoServicesImpl() {

	}

	@Override
	public List<PeriodoBO> getPeriodos() {
		return this.periodoDao.getPeriodos();
	}

	public PeriodoBO getPeriodo(Integer id) {
		return this.periodoDao.getPeriodo(id);
	}

	@Override
	public PeriodoBO getPeriodoActual() {
		return this.periodoDao.getPeriodoActual();
	}

}
