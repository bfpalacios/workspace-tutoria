package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class IndicadoresBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String indicador;
	private int indicadorObservados;
	private int indicadorRegulares;
	
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public int getIndicadorObservados() {
		return indicadorObservados;
	}
	public void setIndicadorObservados(int indicadorObservados) {
		this.indicadorObservados = indicadorObservados;
	}
	public int getIndicadorRegulares() {
		return indicadorRegulares;
	}
	public void setIndicadorRegulares(int indicadorRegulares) {
		this.indicadorRegulares = indicadorRegulares;
	}

}
