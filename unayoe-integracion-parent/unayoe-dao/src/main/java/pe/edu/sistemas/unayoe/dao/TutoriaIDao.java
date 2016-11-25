/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import java.util.List;
import pe.edu.sistemas.unayoe.dao.dominio.Profesor; 
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface TutoriaIDao.
 */
public interface TutoriaIDao {	
	
	/**
	 * Listar profesores.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Profesor> listarProfesores()  throws Exception;	
	
	/**
	 * Listar cursos docente.
	 *
	 * @param usuarioDocente the usuario docente
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosDocente(String usuarioDocente) throws Exception;	
}
