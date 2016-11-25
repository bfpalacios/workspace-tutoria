package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.UsuarioIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.services.transformer.UsuarioTransformerToBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.dao.jdbc.ComunDAOImpl;
 

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioServicesImpl.
 */
@Service("usuarioServices")
public class UsuarioServicesImpl implements UsuarioServices {

	/** The usuario dao. */
	@Autowired
	private UsuarioIDao usuarioDao;
	
	/** The usuario transformer to BO. */
	@Autowired
	private UsuarioTransformerToBO usuarioTransformerToBO;
	
	/** The comun DAO. */
	private ComunDAOImpl comunDAO;
	
	/**
	 * Instantiates a new usuario services impl.
	 */
	public UsuarioServicesImpl(){
		setComunDAO(new ComunDAOImpl());
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#obtenerUsuario(java.lang.String)
	 */
	public UsuarioBO obtenerUsuario(String usuario) throws Exception {
		//System.out.println("usuario "+ usuario);
		Usuario usuarioEntidad = usuarioDao.obtenerUsuario(usuario);
		
		return usuarioTransformerToBO.transformer(usuarioEntidad);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#obtenerRoles(int)
	 */
	public List<UsuarioBO> obtenerRoles(int proceso){	
		return getComunDAO().obtenerRoles(proceso);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#grabarUsuarioObservados(pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO)
	 */
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception{
		usuarioDao.grabarUsuarioObservados(usuarioNuevo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#obtenerTutorActividad(int)
	 */
	@Override
	public AlumnoParBO obtenerTutorActividad(int codigoActividad) {
		return usuarioDao.obtenerTutorActividad(codigoActividad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#grabarUsuarioRegulares(pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO)
	 */
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception{
		usuarioDao.grabarUsuarioRegulares(usuarioNuevo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#buscarUsuario(java.lang.String)
	 */
	public String buscarUsuario(String codUsuario) throws Exception{
		return usuarioDao.buscarUsuario(codUsuario);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.UsuarioServices#buscarUsuarioEquivalencia(java.lang.String)
	 */
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception{
		return usuarioDao.buscarUsuarioEquivalencia(codUsuario);
	}
	
	/**
	 * Gets the comun DAO.
	 *
	 * @return the comun DAO
	 */
	public ComunDAOImpl getComunDAO() {
		return comunDAO;
	}

	/**
	 * Sets the comun DAO.
	 *
	 * @param comunDAO the new comun DAO
	 */
	public void setComunDAO(ComunDAOImpl comunDAO) {
		this.comunDAO = comunDAO;
	}
}
