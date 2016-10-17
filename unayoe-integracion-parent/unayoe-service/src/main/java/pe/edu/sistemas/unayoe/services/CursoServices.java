package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

public interface CursoServices {
	public List<CursoBO> listarCursos() throws Exception;
	public void          guardarCursos(List<CursoBO> lista) throws Exception;
	public List<CursoBO> listarCursosPorTutor(String codTutor) throws Exception;
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception;
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception;
	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento) throws Exception;
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento(String codigoTutor, String codigoAreaConocimiento) throws Exception;

	CursoBO obtnerCursoTema(int codigoTema);
	
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria);
	
	
	public List<CursoBO> listarCursosTutorias();
	
}
