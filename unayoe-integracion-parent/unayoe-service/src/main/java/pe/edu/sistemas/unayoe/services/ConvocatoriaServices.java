package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

public interface ConvocatoriaServices {

	public boolean createConvocatoria(ConvocatoriaBO convocatoria);

	public List<ConvocatoriaBO> getConvocatorias();

	public ConvocatoriaBO getConvocatoria(Integer id);

	public boolean deleteConvocatoria(Integer id);
	
	public boolean updateConvocatoria(Integer id);
	
	public ConvocatoriaBO getConvocatoriaActual();

}
