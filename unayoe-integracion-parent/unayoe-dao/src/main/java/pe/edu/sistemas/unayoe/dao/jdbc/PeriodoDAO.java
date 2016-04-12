package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

public interface PeriodoDAO {

	public PeriodoBO getPeriodo(Integer id);

	public List<PeriodoBO> getPeriodos();

	public PeriodoBO getPeriodoActual();

}
