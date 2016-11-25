package pe.edu.sistemas.unayoe.unayoe.bo;

 
import java.io.Serializable;
 
// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioBO.
 */
public class UsuarioBO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id rol. */
	private String idRol;
	
	/** The role. */
	private String role;
	
	/** The id usuario. */
	private String idUsuario;
	
	/** The contrasenia. */
	private String contrasenia;
	
	/** The nombres. */
	private String nombres;
	
	/** The apellido paterno. */
	private String apellidoPaterno;
	
	/** The apellido materno. */
	private String apellidoMaterno;
	
	/** The correo. */
	private String correo;
	
	/** The direccion. */
	private String direccion;
	
	/** The telefono. */
	private String telefono;
	
	/** The cod alumno. */
	private String codAlumno;
	
	/** The plan alumno. */
	private int planAlumno;	
	
	/**
	 * Gets the contrasenia.
	 *
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	
	/**
	 * Sets the contrasenia.
	 *
	 * @param contrasenia the new contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * Gets the id rol.
	 *
	 * @return the id rol
	 */
	public String getIdRol() {
		return idRol;
	}
	
	/**
	 * Sets the id rol.
	 *
	 * @param idRol the new id rol
	 */
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
	 * Gets the apellido paterno.
	 *
	 * @return the apellido paterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	/**
	 * Sets the apellido paterno.
	 *
	 * @param apellidoPaterno the new apellido paterno
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	/**
	 * Gets the apellido materno.
	 *
	 * @return the apellido materno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	/**
	 * Sets the apellido materno.
	 *
	 * @param apellidoMaterno the new apellido materno
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	
	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
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
	 * Gets the cod alumno.
	 *
	 * @return the cod alumno
	 */
	public String getCodAlumno() {
		return codAlumno;
	}
	
	/**
	 * Sets the cod alumno.
	 *
	 * @param codAlumno the new cod alumno
	 */
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	
	/**
	 * Gets the plan alumno.
	 *
	 * @return the plan alumno
	 */
	public int getPlanAlumno() {
		return planAlumno;
	}
	
	/**
	 * Sets the plan alumno.
	 *
	 * @param planAlumno the new plan alumno
	 */
	public void setPlanAlumno(int planAlumno) {
		this.planAlumno = planAlumno;
	}

	/**
	 * Gets the nombre completo.
	 *
	 * @return the nombre completo
	 */
	public String getNombreCompleto(){
		return nombres+" "+apellidoPaterno+" "+apellidoMaterno;
	}
}

