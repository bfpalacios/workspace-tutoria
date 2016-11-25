package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaCProfBO.
 */
public class AsistenciaCProfBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The fecha. */
	private String fecha;
	
	/** The p codigo. */
	private String pCodigo;
	
	/** The grupo. */
	private String grupo;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The asistencia. */
	private String asistencia;
	
	/** The observacion. */
	private String observacion;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the p codigo.
	 *
	 * @return the p codigo
	 */
	public String getpCodigo() {
		return pCodigo;
	}

	/**
	 * Sets the p codigo.
	 *
	 * @param pCodigo the new p codigo
	 */
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
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
	 * Gets the asistencia.
	 *
	 * @return the asistencia
	 */
	public String getAsistencia() {
		return asistencia;
	}

	/**
	 * Sets the asistencia.
	 *
	 * @param asistencia the new asistencia
	 */
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Sets the observacion.
	 *
	 * @param observacion the new observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
