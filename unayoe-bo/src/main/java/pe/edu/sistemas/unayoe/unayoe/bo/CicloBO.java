package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CicloBO.
 */
public class CicloBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The anio. */
	private int anio;
	
	/** The periodo. */
	private int periodo;
	
	/** The fecha inicio. */
	private String fechaInicio;
	
	/** The fecha fin. */
	private String fechaFin;
	
	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}
	
	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
