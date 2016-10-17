package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

public interface PeriodoServices {

	public List<PeriodoBO> getPeriodos();

	public PeriodoBO getPeriodo(Integer id);

	public PeriodoBO getPeriodoActual();

}
