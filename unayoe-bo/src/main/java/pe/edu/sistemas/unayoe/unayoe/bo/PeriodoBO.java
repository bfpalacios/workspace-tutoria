package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class PeriodoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String descripcion;

	public PeriodoBO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
