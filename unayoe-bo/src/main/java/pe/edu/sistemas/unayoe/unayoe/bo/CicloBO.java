package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class CicloBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int anio;
	private int periodo;
	private String fechaInicio;
	private String fechaFin;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
