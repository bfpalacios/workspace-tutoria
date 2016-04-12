package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

public interface ConvocatoriaDAO {

	public ConvocatoriaBO getConvocatoria(Integer id);

	public List<ConvocatoriaBO> getConvocatorias();

	public boolean deleteConvocatoria(Integer id);

	public boolean createConvocatoria(ConvocatoriaBO convocatoria);

	public boolean updateConvocatoria(ConvocatoriaBO convocatoria);

	public ConvocatoriaBO getConvocatoriaActual();

}
