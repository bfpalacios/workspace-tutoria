package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.ConvocatoriaDAO;
import pe.edu.sistemas.unayoe.services.ConvocatoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvocatoriaServicesImpl.
 */
@Service("convocatoriaServices")
public class ConvocatoriaServicesImpl implements ConvocatoriaServices {

	/** The convocatoria dao. */
	@Autowired
	private ConvocatoriaDAO convocatoriaDao;

	/**
	 * Instantiates a new convocatoria services impl.
	 */
	public ConvocatoriaServicesImpl() {
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#createConvocatoria(pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO)
	 */
	@Override
	public boolean createConvocatoria(ConvocatoriaBO convocatoria) {
		return this.convocatoriaDao.createConvocatoria(convocatoria);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#deleteConvocatoria(java.lang.Integer)
	 */
	@Override
	public boolean deleteConvocatoria(Integer id) {
		return this.convocatoriaDao.deleteConvocatoria(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#getConvocatoria(java.lang.Integer)
	 */
	@Override
	public ConvocatoriaBO getConvocatoria(Integer id) {
		return this.convocatoriaDao.getConvocatoria(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#getConvocatorias()
	 */
	@Override
	public List<ConvocatoriaBO> getConvocatorias() {
		return this.convocatoriaDao.getConvocatorias();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#updateConvocatoria(java.lang.Integer)
	 */
	@Override
	public boolean updateConvocatoria(Integer id) {
		return false;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ConvocatoriaServices#getConvocatoriaActual()
	 */
	@Override
	public ConvocatoriaBO getConvocatoriaActual() {
		return this.convocatoriaDao.getConvocatoriaActual();
	}

}
