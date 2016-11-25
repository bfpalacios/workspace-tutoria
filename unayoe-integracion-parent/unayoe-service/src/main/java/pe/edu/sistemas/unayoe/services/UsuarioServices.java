package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsuarioServices.
 */
public interface UsuarioServices {
	
	/**
	 * Buscar usuario.
	 *
	 * @param codUsuario the cod usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String buscarUsuario(String codUsuario) throws Exception;
	
	/**
	 * Obtener usuario.
	 *
	 * @param usuario the usuario
	 * @return the usuario BO
	 * @throws Exception the exception
	 */
	public UsuarioBO obtenerUsuario(String usuario) throws Exception;
	
	/**
	 * Obtener roles.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<UsuarioBO> obtenerRoles(int procesoTutoria) throws Exception;
	
	/**
	 * Buscar usuario equivalencia.
	 *
	 * @param codUsuario the cod usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String buscarUsuarioEquivalencia(String codUsuario) throws Exception;
	
	/**
	 * Grabar usuario regulares.
	 *
	 * @param usuarioNuevo the usuario nuevo
	 * @throws Exception the exception
	 */
	public void grabarUsuarioRegulares(UsuarioBO usuarioNuevo) throws Exception;
	
	/**
	 * Grabar usuario observados.
	 *
	 * @param usuarioNuevo the usuario nuevo
	 * @throws Exception the exception
	 */
	public void grabarUsuarioObservados(UsuarioBO usuarioNuevo) throws Exception;

	/**
	 * Obtener tutor actividad.
	 *
	 * @param codigoActividad the codigo actividad
	 * @return the alumno par BO
	 */
	AlumnoParBO obtenerTutorActividad(int codigoActividad);
}
