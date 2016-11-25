package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaTProfBO.
 */
public class AsistenciaTProfBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The fecha T. */
	private String fechaT;
	
	/** The t anio. */
	private String tAnio;
	
	/** The t periodo. */
	private String tPeriodo;
	
	/** The t codigo. */
	private String tCodigo;
	
	/** The asistencia T. */
	private String asistenciaT;
	
	/** The observacion T. */
	private String observacionT;
	
	/** The profesor. */
	private ProfesorBO profesor;
	
	/** The curso. */
	private CursoBO curso;
	
	/** The dias clase. */
	private DiasClaseBO diasClase;	
	
	/**
	 * Gets the dias clase.
	 *
	 * @return the dias clase
	 */
	public DiasClaseBO getDiasClase() {
		return diasClase;
	}
	
	/**
	 * Sets the dias clase.
	 *
	 * @param diasClase the new dias clase
	 */
	public void setDiasClase(DiasClaseBO diasClase) {
		this.diasClase = diasClase;
	}
	
	/**
	 * Gets the fecha T.
	 *
	 * @return the fecha T
	 */
	public String getFechaT() {
		return fechaT;
	}
	
	/**
	 * Sets the fecha T.
	 *
	 * @param fechaT the new fecha T
	 */
	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
	}
	
	/**
	 * Gets the t anio.
	 *
	 * @return the t anio
	 */
	public String gettAnio() {
		return tAnio;
	}
	
	/**
	 * Sets the t anio.
	 *
	 * @param tAnio the new t anio
	 */
	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}
	
	/**
	 * Gets the t periodo.
	 *
	 * @return the t periodo
	 */
	public String gettPeriodo() {
		return tPeriodo;
	}
	
	/**
	 * Sets the t periodo.
	 *
	 * @param tPeriodo the new t periodo
	 */
	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}
	
	/**
	 * Gets the t codigo.
	 *
	 * @return the t codigo
	 */
	public String gettCodigo() {
		return tCodigo;
	}
	
	/**
	 * Sets the t codigo.
	 *
	 * @param tCodigo the new t codigo
	 */
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}
	
	/**
	 * Gets the asistencia T.
	 *
	 * @return the asistencia T
	 */
	public String getAsistenciaT() {
		return asistenciaT;
	}
	
	/**
	 * Sets the asistencia T.
	 *
	 * @param asistenciaT the new asistencia T
	 */
	public void setAsistenciaT(String asistenciaT) {
		this.asistenciaT = asistenciaT;
	}
	
	/**
	 * Gets the observacion T.
	 *
	 * @return the observacion T
	 */
	public String getObservacionT() {
		return observacionT;
	}
	
	/**
	 * Sets the observacion T.
	 *
	 * @param observacionT the new observacion T
	 */
	public void setObservacionT(String observacionT) {
		this.observacionT = observacionT;
	}
	
	/**
	 * Gets the profesor.
	 *
	 * @return the profesor
	 */
	public ProfesorBO getProfesor() {
		return profesor;
	}
	
	/**
	 * Sets the profesor.
	 *
	 * @param profesor the new profesor
	 */
	public void setProfesor(ProfesorBO profesor) {
		this.profesor = profesor;
	}
	
	/**
	 * Gets the curso.
	 *
	 * @return the curso
	 */
	public CursoBO getCurso() {
		return curso;
	}
	
	/**
	 * Sets the curso.
	 *
	 * @param curso the new curso
	 */
	public void setCurso(CursoBO curso) {
		this.curso = curso;
	}
	

}
