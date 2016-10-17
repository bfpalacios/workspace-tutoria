package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

public interface UsuarioServices {
	public String buscarUsuario(String codUsuario) throws Exception;
	public UsuarioBO obtenerUsuario(String usuario) throws Exception;
	public List<UsuarioBO> obtenerRoles(int procesoTutoria) throws Exception;
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception;
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception;
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception;

	AlumnoParBO obtenerTutorActividad(int codigoActividad);
}
