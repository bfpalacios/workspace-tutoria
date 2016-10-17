package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.TemaDAO;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

@Service("temaServices")
public class TemaServicesImpl implements TemaServices {

	@Autowired
	private TemaDAO temaDao;

	public TemaServicesImpl() {
	}

	public TemaBO getTema(Integer id) {
		return temaDao.getTema(id);
	}

	public List<TemaBO> getTemas() {
		return temaDao.getTemas();
	}

	public List<TemaBO> getTemas(String codigoCurso) {
		return temaDao.getTemasPorCurso(codigoCurso);
	}

	public boolean createTema(TemaBO tema) {
		return temaDao.createTema(tema);
	}

	public boolean deleteTema(Integer id) {
		return temaDao.deleteTema(id);
	}

	public boolean deleteTema(TemaBO tema) {
		return this.deleteTema(tema.getId());
	}

	public boolean updateTema(TemaBO tema) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<TemaBO> listarTemasPorCurso(String codigoCurso) {
		return temaDao.getTemasPorCurso(codigoCurso);
	}

	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria) {
		return temaDao.getTemasPorConvocatoria(idConvocatoria);
	}

	@Override
	public List<TemaBO> listarTemasAprobadosPorCurso(String codigoCurso, String codigoTutor) {
		return temaDao.listarTemasAprobadosPorCurso(codigoCurso, codigoTutor);
	}

	@Override
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso) {
		return temaDao.getTemasPorConvocatoriaCurso(idConvocatoria, codigoCurso);
	}
}
