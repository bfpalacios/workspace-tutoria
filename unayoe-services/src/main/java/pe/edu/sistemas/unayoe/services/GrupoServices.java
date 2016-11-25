package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;

/**
 * Interfaz Servicios de Grupos
 * @author Bruno Palacios
 *
 */

public interface GrupoServices {
	
	public void  guardarGrupos(List<GrupoBO> lista) throws Exception;

}
