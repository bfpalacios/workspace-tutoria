package pe.edu.sistemas.unayoe.core.dao;

import java.io.Serializable;
import java.util.List;

public interface IDAO<Entidad, Id> {

	public void insertar(Entidad entidad) throws Exception;
	public void actualizar(Entidad entidad) throws Exception;
	public void eliminar(Serializable id) throws Exception;
	public Entidad obtenerEntidadPorId(Class<Entidad> clase, Serializable id) throws Exception;
	public List<Entidad> listarTodos(Class<Entidad> clase) throws Exception;
}
