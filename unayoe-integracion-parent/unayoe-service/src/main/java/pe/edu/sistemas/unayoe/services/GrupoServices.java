package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface GrupoServices.
 */
public interface GrupoServices {
	
	/**
	 * Guardar grupos.
	 *
	 * @param lista the lista
	 * @throws Exception the exception
	 */
	public void  guardarGrupos(List<GrupoBO> lista) throws Exception;

}
