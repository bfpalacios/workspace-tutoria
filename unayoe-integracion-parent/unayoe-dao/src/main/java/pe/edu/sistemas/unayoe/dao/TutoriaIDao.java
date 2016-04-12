package pe.edu.sistemas.unayoe.dao;

import java.util.List;
import pe.edu.sistemas.unayoe.dao.dominio.Profesor; 
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

public interface TutoriaIDao {	
	public List<Profesor> listarProfesores()  throws Exception;	
	public List<CursoBO> listarCursosDocente(String usuarioDocente) throws Exception;	
}
