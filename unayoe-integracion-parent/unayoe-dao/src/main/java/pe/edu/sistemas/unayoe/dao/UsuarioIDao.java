/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UsuarioIDao.
 */
public interface UsuarioIDao {
	
	/**
	 * Obtener usuario.
	 *
	 * @param usuario the usuario
	 * @return the usuario
	 * @throws Exception the exception
	 */
	public Usuario obtenerUsuario(String usuario) throws Exception;
	
	/**
	 * Buscar usuario.
	 *
	 * @param codUsuario the cod usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String buscarUsuario(String codUsuario) throws Exception;
	
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
