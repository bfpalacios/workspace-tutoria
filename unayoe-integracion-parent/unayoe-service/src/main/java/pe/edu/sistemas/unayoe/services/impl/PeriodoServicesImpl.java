package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.PeriodoDAO;
import pe.edu.sistemas.unayoe.services.PeriodoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class PeriodoServicesImpl.
 */
@Service("periodoServices")
public class PeriodoServicesImpl implements PeriodoServices {

	/** The periodo dao. */
	@Autowired
	private PeriodoDAO periodoDao;

	/**
	 * Instantiates a new periodo services impl.
	 */
	public PeriodoServicesImpl() {

	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PeriodoServices#getPeriodos()
	 */
	@Override
	public List<PeriodoBO> getPeriodos() {
		return this.periodoDao.getPeriodos();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PeriodoServices#getPeriodo(java.lang.Integer)
	 */
	public PeriodoBO getPeriodo(Integer id) {
		return this.periodoDao.getPeriodo(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.PeriodoServices#getPeriodoActual()
	 */
	@Override
	public PeriodoBO getPeriodoActual() {
		return this.periodoDao.getPeriodoActual();
	}

}
