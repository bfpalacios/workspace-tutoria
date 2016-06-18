package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

public interface CursoIDao {
	public List<Curso>   listarCursos()  throws Exception;
	public               Curso obtenerCurso(String codigo) throws Exception;
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception;
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception;
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento);
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoAreaConocimiento,String codigoTutor) throws Exception;

	CursoBO obtenerCursoTema(int codigoTema);
	
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria);
	
	
	public List<CursoBO> listarCursosTutorias();
}
