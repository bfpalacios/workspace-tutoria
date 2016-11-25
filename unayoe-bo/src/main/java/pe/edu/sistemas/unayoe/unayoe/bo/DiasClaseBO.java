package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DiasClaseBO.
 */
public class DiasClaseBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The anio. */
	private String anio;
	
	/** The periodo. */
	private String periodo;
	
	/** The grupo. */
	private String grupo;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The dia. */
	private String dia;
	
	/** The hora ini. */
	private String horaIni;
	
	/** The hora fin. */
	private String horaFin;

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Sets the grupo.
	 *
	 * @param grupo the new grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getcCodigo() {
		return cCodigo;
	}

	/**
	 * Sets the c codigo.
	 *
	 * @param cCodigo the new c codigo
	 */
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}

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
	 * Gets the hora ini.
	 *
	 * @return the hora ini
	 */
	public String getHoraIni() {
		return horaIni;
	}

	/**
	 * Sets the hora ini.
	 *
	 * @param horaIni the new hora ini
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
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

}
