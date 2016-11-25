package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.CursoIDao;
import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.CursoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.GrupoDAO;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.GrupoServices;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToENTIDAD;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class GrupoServicesImpl.
 */
@Service("grupoServices") 	
public class GrupoServicesImpl implements GrupoServices{
	
	/** The curso I dao. */
	@Autowired
	private CursoIDao cursoIDao;
	
	/** The grupo DAO. */
	@Autowired
	private GrupoDAO grupoDAO;
	
	/** The curso transformer to BO. */
	@Autowired
	private CursoTransformerToBO cursoTransformerToBO;

	/** The curso transformer to ENTIDAD. */
	@Autowired
	private CursoTransformerToENTIDAD  cursoTransformerToENTIDAD;
	 
	
	
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.GrupoServices#guardarGrupos(java.util.List)
	 */
	public void  guardarGrupos(List<GrupoBO> lista)throws Exception {
		grupoDAO.insertarLista(lista);
	}

}
