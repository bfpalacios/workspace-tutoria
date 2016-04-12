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
 

@Service("usuarioServices")
public class UsuarioServicesImpl implements UsuarioServices {

	@Autowired
	private UsuarioIDao usuarioDao;
	@Autowired
	private UsuarioTransformerToBO usuarioTransformerToBO;
	
	private ComunDAOImpl comunDAO;
	
	public UsuarioServicesImpl(){
		setComunDAO(new ComunDAOImpl());
	}
	
	public UsuarioBO obtenerUsuario(String usuario) throws Exception {
		//System.out.println("usuario "+ usuario);
		Usuario usuarioEntidad = usuarioDao.obtenerUsuario(usuario);
		return usuarioTransformerToBO.transformer(usuarioEntidad);
	}
	
	public List<UsuarioBO> obtenerRoles(int proceso){	
		return getComunDAO().obtenerRoles(proceso);
	}

	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception{
		usuarioDao.grabarUsuarioObservados(usuarioNuevo);
	}

	@Override
	public AlumnoParBO obtenerTutorActividad(int codigoActividad) {
		return usuarioDao.obtenerTutorActividad(codigoActividad);
	}

	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception{
		usuarioDao.grabarUsuarioRegulares(usuarioNuevo);
	}
	
	public String buscarUsuario(String codUsuario) throws Exception{
		return usuarioDao.buscarUsuario(codUsuario);
	}
	
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception{
		return usuarioDao.buscarUsuarioEquivalencia(codUsuario);
	}
	
	public ComunDAOImpl getComunDAO() {
		return comunDAO;
	}

	public void setComunDAO(ComunDAOImpl comunDAO) {
		this.comunDAO = comunDAO;
	}
}
