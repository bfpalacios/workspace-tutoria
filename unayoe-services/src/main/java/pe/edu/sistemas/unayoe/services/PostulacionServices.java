package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;

public interface PostulacionServices {

	public boolean registrarPostulacion(PostulacionBO postulacion);

	public List<PostulacionBO> getListaPostulaciones();

	public PostulacionBO getPostulacion(Integer id);

	public boolean updatePostulacion(PostulacionBO postulacion);

	public boolean removePostulacion(Integer id);

	public List<PostulacionBO> getPostulacionesPorConvocatoriaTema(Integer idConvocatoria, Integer idTema);

	public boolean aprobarPostulante(Integer idPostulacion, Integer idTema);

}
