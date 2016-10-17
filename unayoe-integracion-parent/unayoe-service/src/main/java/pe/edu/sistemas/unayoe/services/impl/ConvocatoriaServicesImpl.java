package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.ConvocatoriaDAO;
import pe.edu.sistemas.unayoe.services.ConvocatoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

@Service("convocatoriaServices")
public class ConvocatoriaServicesImpl implements ConvocatoriaServices {

	@Autowired
	private ConvocatoriaDAO convocatoriaDao;

	public ConvocatoriaServicesImpl() {
	}

	@Override
	public boolean createConvocatoria(ConvocatoriaBO convocatoria) {
		return this.convocatoriaDao.createConvocatoria(convocatoria);
	}

	@Override
	public boolean deleteConvocatoria(Integer id) {
		return this.convocatoriaDao.deleteConvocatoria(id);
	}

	@Override
	public ConvocatoriaBO getConvocatoria(Integer id) {
		return this.convocatoriaDao.getConvocatoria(id);
	}

	@Override
	public List<ConvocatoriaBO> getConvocatorias() {
		return this.convocatoriaDao.getConvocatorias();
	}

	@Override
	public boolean updateConvocatoria(Integer id) {
		return false;
	}

	@Override
	public ConvocatoriaBO getConvocatoriaActual() {
		return this.convocatoriaDao.getConvocatoriaActual();
	}

}
