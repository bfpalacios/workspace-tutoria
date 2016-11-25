package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SesionBO.
 */
public class SesionBO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The anio. */
	private int anio;
	
	/** The periodo. */
	private int periodo;
	
	/** The cod tutoria. */
	private String codTutoria;
	
	/** The fecha tutoria. */
	private String fechaTutoria;
	
	/** The nro sesion. */
	private int nroSesion;
	
	/** The desc sesion. */
	private String descSesion;
	
	/** The hora ini. */
	private String horaIni;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The estado sesion. */
	private String estadoSesion;
	
	/** The acta. */
	private byte[] acta;
	
	/** The nombre acta. */
	private String nombreActa;
	
	/** The estado acta. */
	private int estadoActa;
	
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
	 * Gets the cod tutoria.
	 *
	 * @return the cod tutoria
	 */
	public String getCodTutoria() {
		return codTutoria;
	}
	
	/**
	 * Sets the cod tutoria.
	 *
	 * @param codTutoria the new cod tutoria
	 */
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	
	/**
	 * Gets the fecha tutoria.
	 *
	 * @return the fecha tutoria
	 */
	public String getFechaTutoria() {
		return fechaTutoria;
	}
	
	/**
	 * Sets the fecha tutoria.
	 *
	 * @param fechaTutoria the new fecha tutoria
	 */
	public void setFechaTutoria(String fechaTutoria) {
		this.fechaTutoria = fechaTutoria;
	}
	
	/**
	 * Gets the nro sesion.
	 *
	 * @return the nro sesion
	 */
	public int getNroSesion() {
		return nroSesion;
	}
	
	/**
	 * Sets the nro sesion.
	 *
	 * @param nroSesion the new nro sesion
	 */
	public void setNroSesion(int nroSesion) {
		this.nroSesion = nroSesion;
	}
	
	/**
	 * Gets the desc sesion.
	 *
	 * @return the desc sesion
	 */
	public String getDescSesion() {
		return descSesion;
	}
	
	/**
	 * Sets the desc sesion.
	 *
	 * @param descSesion the new desc sesion
	 */
	public void setDescSesion(String descSesion) {
		this.descSesion = descSesion;
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
	
	/**
	 * Gets the estado sesion.
	 *
	 * @return the estado sesion
	 */
	public String getEstadoSesion() {
		return estadoSesion;
	}
	
	/**
	 * Sets the estado sesion.
	 *
	 * @param estadoSesion the new estado sesion
	 */
	public void setEstadoSesion(String estadoSesion) {
		this.estadoSesion = estadoSesion;
	}
	
	/**
	 * Gets the nombre acta.
	 *
	 * @return the nombre acta
	 */
	public String getNombreActa() {
		return nombreActa==null?"":nombreActa;
	}
	
	/**
	 * Sets the nombre acta.
	 *
	 * @param nombreActa the new nombre acta
	 */
	public void setNombreActa(String nombreActa) {
		this.nombreActa = nombreActa;
	}
	
	/**
	 * Gets the estado acta.
	 *
	 * @return the estado acta
	 */
	public int getEstadoActa() {
		return estadoActa;
	}
	
	/**
	 * Sets the estado acta.
	 *
	 * @param estadoActa the new estado acta
	 */
	public void setEstadoActa(int estadoActa) {
		this.estadoActa = estadoActa;
	}
	
	/**
	 * Gets the acta.
	 *
	 * @return the acta
	 */
	public byte[] getActa() {
		return acta;
	}
	
	/**
	 * Sets the acta.
	 *
	 * @param acta the new acta
	 */
	public void setActa(byte[] acta) {
		this.acta = acta;
	}	
}
