package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.jdbc.TemaDAO;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class TemaServicesImpl.
 */
@Service("temaServices")
public class TemaServicesImpl implements TemaServices {

	/** The tema dao. */
	@Autowired
	private TemaDAO temaDao;

	/**
	 * Instantiates a new tema services impl.
	 */
	public TemaServicesImpl() {
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#getTema(java.lang.Integer)
	 */
	public TemaBO getTema(Integer id) {
		return temaDao.getTema(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#getTemas()
	 */
	public List<TemaBO> getTemas() {
		return temaDao.getTemas();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#getTemas(java.lang.String)
	 */
	public List<TemaBO> getTemas(String codigoCurso) {
		return temaDao.getTemasPorCurso(codigoCurso);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#createTema(pe.edu.sistemas.unayoe.unayoe.bo.TemaBO)
	 */
	public boolean createTema(TemaBO tema) {
		return temaDao.createTema(tema);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#deleteTema(java.lang.Integer)
	 */
	public boolean deleteTema(Integer id) {
		return temaDao.deleteTema(id);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#deleteTema(pe.edu.sistemas.unayoe.unayoe.bo.TemaBO)
	 */
	public boolean deleteTema(TemaBO tema) {
		return this.deleteTema(tema.getId());
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#updateTema(pe.edu.sistemas.unayoe.unayoe.bo.TemaBO)
	 */
	public boolean updateTema(TemaBO tema) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#listarTemasPorCurso(java.lang.String)
	 */
	public List<TemaBO> listarTemasPorCurso(String codigoCurso) {
		return temaDao.getTemasPorCurso(codigoCurso);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#getTemasPorConvocatoria(java.lang.Integer)
	 */
	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria) {
		return temaDao.getTemasPorConvocatoria(idConvocatoria);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#listarTemasAprobadosPorCurso(java.lang.String, java.lang.String)
	 */
	@Override
	public List<TemaBO> listarTemasAprobadosPorCurso(String codigoCurso, String codigoTutor) {
		return temaDao.listarTemasAprobadosPorCurso(codigoCurso, codigoTutor);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TemaServices#getTemasPorConvocatoriaCurso(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso) {
		return temaDao.getTemasPorConvocatoriaCurso(idConvocatoria, codigoCurso);
	}
}
