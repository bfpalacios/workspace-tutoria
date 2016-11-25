package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * author: alexh.
 */
public class HorarioBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo. */
	private int codigo; //Código del horario
	
	/** The codigo programacion. */
	private int codigoProgramacion;
	
	/** The dia. */
	private String dia;
	
	/** The hora inicio. */
	private String horaInicio;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The cod aula. */
	private String codAula;

	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}
	
	/**
	 * Sets the hora inicio.
	 *
	 * @param horaInicio the new hora inicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	/**
	 * Gets the hora fin.
	 *
	 * @return the hora fin
	 */
	public String getHoraFin() {
		return horaFin;
	}
	
	/**
	 * Sets the hora fin.
	 *
	 * @param horaFin the new hora fin
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	/**
	 * Gets the cod aula.
	 *
	 * @return the cod aula
	 */
	public String getCodAula() {
		return codAula;
	}
	
	/**
	 * Sets the cod aula.
	 *
	 * @param codAula the new cod aula
	 */
	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo programacion.
	 *
	 * @return the codigo programacion
	 */
	public int getCodigoProgramacion() {
		return codigoProgramacion;
	}

	/**
	 * Sets the codigo programacion.
	 *
	 * @param codigoProgramacion the new codigo programacion
	 */
	public void setCodigoProgramacion(int codigoProgramacion) {
		this.codigoProgramacion = codigoProgramacion;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
