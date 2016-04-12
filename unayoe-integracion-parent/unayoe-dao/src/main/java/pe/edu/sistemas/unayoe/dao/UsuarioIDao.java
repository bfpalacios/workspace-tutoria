package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

public interface UsuarioIDao {
	public Usuario obtenerUsuario(String usuario) throws Exception;
	public String buscarUsuario(String codUsuario) throws Exception;
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception;
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception; 
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception;

	AlumnoParBO obtenerTutorActividad(int codigoActividad);
}
