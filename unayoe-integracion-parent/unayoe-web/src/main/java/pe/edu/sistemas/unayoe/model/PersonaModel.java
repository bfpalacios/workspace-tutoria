package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonaModel.
 */
@Component("personaModel")
@RequestScoped
public class PersonaModel {
	
	/** The codigo. */
	private String codigo;
	
	/** The nombres. */
	private String nombres;
	
	/** The apellido pat. */
	private String apellidoPat;
	
	/** The apellido mat. */
	private String apellidoMat;
	
	/** The direccion. */
	private String direccion;
	
	/** The fecha. */
	private String fecha;
	
	/** The fec nacimiento. */
	private String fecNacimiento;
	
	/** The asistencia. */
	private String asistencia;
	
	/** The telefono. */
	private String telefono;
	
	/** The dni. */
	private String dni;
	
	
	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Sets the telefono.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
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
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the nombres.
	 *
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * Sets the nombres.
	 *
	 * @param nombres the new nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * Gets the apellido pat.
	 *
	 * @return the apellido pat
	 */
	public String getApellidoPat() {
		return apellidoPat;
	}
	
	/**
	 * Sets the apellido pat.
	 *
	 * @param apellidoPat the new apellido pat
	 */
	public void setApellidoPat(String apellidoPat) {
		this.apellidoPat = apellidoPat;
	}
	
	/**
	 * Gets the apellido mat.
	 *
	 * @return the apellido mat
	 */
	public String getApellidoMat() {
		return apellidoMat;
	}
	
	/**
	 * Sets the apellido mat.
	 *
	 * @param apellidoMat the new apellido mat
	 */
	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}
	
	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
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
	 * Gets the fec nacimiento.
	 *
	 * @return the fec nacimiento
	 */
	public String getFecNacimiento() {
		return fecNacimiento;
	}
	
	/**
	 * Sets the fec nacimiento.
	 *
	 * @param fecNacimiento the new fec nacimiento
	 */
	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	
	

}
