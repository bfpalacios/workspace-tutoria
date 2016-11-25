package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadoresBO.
 */
public class IndicadoresBO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The indicador. */
	private String indicador;
	
	/** The indicador observados. */
	private int indicadorObservados;
	
	/** The indicador regulares. */
	private int indicadorRegulares;
	
	/**
	 * Gets the indicador.
	 *
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}
	
	/**
	 * Sets the indicador.
	 *
	 * @param indicador the new indicador
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	
	/**
	 * Gets the indicador observados.
	 *
	 * @return the indicador observados
	 */
	public int getIndicadorObservados() {
		return indicadorObservados;
	}
	
	/**
	 * Sets the indicador observados.
	 *
	 * @param indicadorObservados the new indicador observados
	 */
	public void setIndicadorObservados(int indicadorObservados) {
		this.indicadorObservados = indicadorObservados;
	}
	
	/**
	 * Gets the indicador regulares.
	 *
	 * @return the indicador regulares
	 */
	public int getIndicadorRegulares() {
		return indicadorRegulares;
	}
	
	/**
	 * Sets the indicador regulares.
	 *
	 * @param indicadorRegulares the new indicador regulares
	 */
	public void setIndicadorRegulares(int indicadorRegulares) {
		this.indicadorRegulares = indicadorRegulares;
	}

}
