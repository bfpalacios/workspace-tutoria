package pe.edu.sistemas.unayoe.unayoe.bo;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoParBO.
 */
public class AlumnoParBO {

	/** The codigo. */
	private String codigo;
	
	/** The nombre. */
	private String nombre;
	
	/** The apellidos. */
	private String apellidos;
	
	/** The fecha nac. */
	private Date fecha_nac;
	
	/** The direccion. */
	private String direccion;
	
	/** The email. */
	private String email;
	
	/** The telefono. */
	private String telefono;
	
	/** The dni. */
	private String dni;
	
	/** The plan. */
	private Integer plan;
	
	/** The flag tutor. */
	private boolean flag_tutor;

	/**
	 * Instantiates a new alumno par BO.
	 */
	public AlumnoParBO() {
		
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
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
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the fecha nac.
	 *
	 * @return the fecha nac
	 */
	public Date getFecha_nac() {
		return fecha_nac;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the plan.
	 *
	 * @return the plan
	 */
	public Integer getPlan() {
		return plan;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Checks if is flag tutor.
	 *
	 * @return true, if is flag tutor
	 */
	public boolean isFlag_tutor() {
		return flag_tutor;
	}

	/**
	 * Sets the apellidos.
	 *
	 * @param apellidos the new apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the fecha nac.
	 *
	 * @param fecha_nac the new fecha nac
	 */
	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	/**
	 * Sets the flag tutor.
	 *
	 * @param flag_tutor the new flag tutor
	 */
	public void setFlag_tutor(boolean flag_tutor) {
		this.flag_tutor = flag_tutor;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Sets the plan.
	 *
	 * @param plan the new plan
	 */
	public void setPlan(Integer plan) {
		this.plan = plan;
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
	 * Gets the nombre completo.
	 *
	 * @return the nombre completo
	 */
	public String getNombreCompleto(){
		return nombre+" "+ apellidos;
	}
}
