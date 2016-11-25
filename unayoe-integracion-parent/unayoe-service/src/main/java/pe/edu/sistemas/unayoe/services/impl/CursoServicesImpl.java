package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.unayoe.dao.CursoIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.dao.jdbc.CursoDAO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToENTIDAD;

// TODO: Auto-generated Javadoc
/**
 * The Class CursoServicesImpl.
 */
@Service("cursoServices")
public class CursoServicesImpl implements CursoServices {

	/** The curso I dao. */
	@Autowired
	private CursoIDao cursoIDao;

	/** The curso DAO. */
	@Autowired
	private CursoDAO cursoDAO;

	/** The curso transformer to BO. */
	@Autowired
	private CursoTransformerToBO cursoTransformerToBO;

	/** The curso transformer to ENTIDAD. */
	@Autowired
	private CursoTransformerToENTIDAD cursoTransformerToENTIDAD;

	/**
	 * Obtener alumno.
	 *
	 * @param codigo the codigo
	 * @return the curso BO
	 * @throws Exception the exception
	 */
	public CursoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : " + codigo);
		Curso cursoEntidad = cursoIDao.obtenerCurso(codigo);
		return cursoTransformerToBO.transformer(cursoEntidad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursos()
	 */
	public List<CursoBO> listarCursos() throws Exception {
		return cursoTransformerToBO.transformer(cursoIDao.listarCursos());
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#guardarCursos(java.util.List)
	 */
	public void guardarCursos(List<CursoBO> lista) throws Exception {
		cursoDAO.insertarLista(cursoTransformerToENTIDAD.transformer(lista));
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosPorTutor(java.lang.String)
	 */
	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosxDocenteRegular(java.lang.String)
	 */
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception {
		return cursoIDao.listarCursosxDocenteRegular(codDocente);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosDocente(java.lang.String, int, int)
	 */
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception {
		return cursoIDao.listarCursosDocente(codDocente, proceso, modo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosPorAreaConocimiento(java.lang.String)
	 */
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento) throws Exception {
		return cursoIDao.listarCursosPorAreaConocimiento(codigoAreaConocimiento);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosAprobadosPorAreaConocimiento(java.lang.String, java.lang.String)
	 */
	@Override
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoTutor, String codigoAreaConocimiento) throws Exception {
		return  cursoIDao.listarCursosAprobadosPorAreaConocimiento(codigoTutor, codigoAreaConocimiento);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#obtnerCursoTema(int)
	 */
	@Override
	public CursoBO obtnerCursoTema(int codigoTema) {
		return cursoIDao.obtenerCursoTema(codigoTema);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosPorConvocatoria(java.lang.Integer)
	 */
	@Override
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria) {
		return cursoIDao.listarCursosPorConvocatoria(idConvocatoria);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.CursoServices#listarCursosTutorias()
	 */
	@Override
	public List<CursoBO> listarCursosTutorias() {
		return cursoIDao.listarCursosTutorias();
	}
}

