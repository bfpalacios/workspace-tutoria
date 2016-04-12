package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

public interface TemaDAO {

	public List<TemaBO> getTemas();
	public List<TemaBO> getTemasPorCurso(String codigoCurso);
	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria);

	public TemaBO getTema(Integer id);
	
	public boolean updateTema(TemaBO tema);

	public boolean deleteTema(Integer id);

	public boolean createTema(TemaBO tema);

	List<TemaBO> listarTemasAprobadosPorCurso(String codigoTutor,String codigoCurso);
	
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso);
	
}
