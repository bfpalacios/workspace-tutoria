package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ObservacionBO.
 */
public class ObservacionBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
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
	
	/** The fecha entrega. */
	private String fecha_entrega;
	
	/** The razon. */
	private String razon;
	
	/** The fecha cumplimiento. */
	private String fecha_cumplimiento;
	
	/** The tarea. */
	private String tarea;
	
	/** The estado control. */
	private String estadoControl;
	
	/** The dias cierre. */
	private String diasCierre;
	
	/** The sesiones cierre. */
	private String sesionesCierre;
	
	/** The lista estados. */
	private List<ClaseMaestra> listaEstados;
	
	/** The lista sesiones cierre. */
	private List<SesionBO> listaSesionesCierre;
	
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
	
	/**
	 * Gets the lista estados.
	 *
	 * @return the lista estados
	 */
	public List<ClaseMaestra> getListaEstados() {
		return listaEstados;
	}
	
	/**
	 * Sets the lista estados.
	 *
	 * @param listaEstados the new lista estados
	 */
	public void setListaEstados(List<ClaseMaestra> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	/**
	 * Gets the lista sesiones cierre.
	 *
	 * @return the lista sesiones cierre
	 */
	public List<SesionBO> getListaSesionesCierre() {
		return listaSesionesCierre;
	}
	
	/**
	 * Sets the lista sesiones cierre.
	 *
	 * @param listaSesionesCierre the new lista sesiones cierre
	 */
	public void setListaSesionesCierre(List<SesionBO> listaSesionesCierre) {
		this.listaSesionesCierre = listaSesionesCierre;
	}
	
	/**
	 * Gets the estado control.
	 *
	 * @return the estado control
	 */
	public String getEstadoControl() {
		return estadoControl;
	}
	
	/**
	 * Sets the estado control.
	 *
	 * @param estadoControl the new estado control
	 */
	public void setEstadoControl(String estadoControl) {
		this.estadoControl = estadoControl;
	}
	
	/**
	 * Gets the dias cierre.
	 *
	 * @return the dias cierre
	 */
	public String getDiasCierre() {
		return diasCierre;
	}
	
	/**
	 * Sets the dias cierre.
	 *
	 * @param diasCierre the new dias cierre
	 */
	public void setDiasCierre(String diasCierre) {
		this.diasCierre = diasCierre;
	}
	
	/**
	 * Gets the sesiones cierre.
	 *
	 * @return the sesiones cierre
	 */
	public String getSesionesCierre() {
		return sesionesCierre;
	}
	
	/**
	 * Sets the sesiones cierre.
	 *
	 * @param sesionesCierre the new sesiones cierre
	 */
	public void setSesionesCierre(String sesionesCierre) {
		this.sesionesCierre = sesionesCierre;
	}
	
	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fecha entrega
	 */
	public String getFecha_entrega() {
		return fecha_entrega;
	}
	
	/**
	 * Sets the fecha entrega.
	 *
	 * @param fecha_entrega the new fecha entrega
	 */
	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	
	/**
	 * Gets the razon.
	 *
	 * @return the razon
	 */
	public String getRazon() {
		return razon;
	}
	
	/**
	 * Sets the razon.
	 *
	 * @param razon the new razon
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	/**
	 * Gets the fecha cumplimiento.
	 *
	 * @return the fecha cumplimiento
	 */
	public String getFecha_cumplimiento() {
		return fecha_cumplimiento;
	}
	
	/**
	 * Sets the fecha cumplimiento.
	 *
	 * @param fecha_cumplimiento the new fecha cumplimiento
	 */
	public void setFecha_cumplimiento(String fecha_cumplimiento) {
		this.fecha_cumplimiento = fecha_cumplimiento;
	}
	
	/**
	 * Gets the tarea.
	 *
	 * @return the tarea
	 */
	public String getTarea() {
		return tarea;
	}
	
	/**
	 * Sets the tarea.
	 *
	 * @param tarea the new tarea
	 */
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}	
	
}
