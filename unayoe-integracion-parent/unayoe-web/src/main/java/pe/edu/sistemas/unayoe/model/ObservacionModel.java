package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ObservacionModel.
 */
@Component("observacionModel")
@RequestScoped
public class ObservacionModel {
	
	/** The anio. */
	private int anio;
	
	/** The periodo. */
	private int periodo;
	
	/** The cod tutoria. */
	private String codTutoria;
	
	/** The id observacion. */
	private String idObservacion;
	
	/** The observacion. */
	private String observacion;
	
	/** The criticidad. */
	private String criticidad;
	
	/** The sesion registro. */
	private String sesionRegistro;
	
	/** The fecha registro. */
	private String fechaRegistro;
	
	/** The sesion cierre. */
	private String sesionCierre;
	
	/** The observacion cierre. */
	private String observacionCierre;
	
	/** The fecha cierre. */
	private String fechaCierre;
	
	/** The estado observacion. */
	private int estadoObservacion;
	
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
	 * Gets the id observacion.
	 *
	 * @return the id observacion
	 */
	public String getIdObservacion() {
		return idObservacion;
	}
	
	/**
	 * Sets the id observacion.
	 *
	 * @param idObservacion the new id observacion
	 */
	public void setIdObservacion(String idObservacion) {
		this.idObservacion = idObservacion;
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
	
	/**
	 * Gets the criticidad.
	 *
	 * @return the criticidad
	 */
	public String getCriticidad() {
		return criticidad;
	}
	
	/**
	 * Sets the criticidad.
	 *
	 * @param criticidad the new criticidad
	 */
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	
	/**
	 * Gets the sesion registro.
	 *
	 * @return the sesion registro
	 */
	public String getSesionRegistro() {
		return sesionRegistro;
	}
	
	/**
	 * Sets the sesion registro.
	 *
	 * @param sesionRegistro the new sesion registro
	 */
	public void setSesionRegistro(String sesionRegistro) {
		this.sesionRegistro = sesionRegistro;
	}
	
	/**
	 * Gets the fecha registro.
	 *
	 * @return the fecha registro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * Sets the fecha registro.
	 *
	 * @param fechaRegistro the new fecha registro
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * Gets the sesion cierre.
	 *
	 * @return the sesion cierre
	 */
	public String getSesionCierre() {
		return sesionCierre;
	}
	
	/**
	 * Sets the sesion cierre.
	 *
	 * @param sesionCierre the new sesion cierre
	 */
	public void setSesionCierre(String sesionCierre) {
		this.sesionCierre = sesionCierre;
	}
	
	/**
	 * Gets the observacion cierre.
	 *
	 * @return the observacion cierre
	 */
	public String getObservacionCierre() {
		return observacionCierre;
	}
	
	/**
	 * Sets the observacion cierre.
	 *
	 * @param observacionCierre the new observacion cierre
	 */
	public void setObservacionCierre(String observacionCierre) {
		this.observacionCierre = observacionCierre;
	}
	
	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fecha cierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}
	
	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre the new fecha cierre
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	/**
	 * Gets the estado observacion.
	 *
	 * @return the estado observacion
	 */
	public int getEstadoObservacion() {
		return estadoObservacion;
	}
	
	/**
	 * Sets the estado observacion.
	 *
	 * @param estadoObservacion the new estado observacion
	 */
	public void setEstadoObservacion(int estadoObservacion) {
		this.estadoObservacion = estadoObservacion;
	}
}
