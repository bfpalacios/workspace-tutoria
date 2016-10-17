package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

public interface TemaServices {

	public List<TemaBO> getTemas();

	public List<TemaBO> getTemas(String codigoCurso);

	public TemaBO getTema(Integer id);

	public boolean createTema(TemaBO tema);

	public boolean updateTema(TemaBO tema);

	public boolean deleteTema(TemaBO tema);

	public boolean deleteTema(Integer id);

	public List<TemaBO> listarTemasPorCurso(String codigoCurso);

	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria);

	public List<TemaBO> listarTemasAprobadosPorCurso(String codigoTutor, String codigoCurso);

	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso);

}
