package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

public interface PostulacionDAO {

	public PostulacionBO getPostulacion(Integer id);

	public List<PostulacionBO> getPostulaciones();

	public boolean createPostulacion(PostulacionBO postulacion);

	public boolean updatePostulacion(PostulacionBO postulacion);

	public boolean deletePostulacion(Integer id);

	public List<PostulacionBO> getPostulacionesPorConvocatoriaTema(Integer idConvocatoria, Integer idTema);
	
	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema);

}
