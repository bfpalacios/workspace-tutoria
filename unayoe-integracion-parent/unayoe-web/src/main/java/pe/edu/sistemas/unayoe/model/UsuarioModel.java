package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioRolBO;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioModel.
 */
@Component("usuarioModel")
@RequestScoped
public class UsuarioModel {

	/** The id usuario. */
	private String idUsuario;
	
	/** The clave. */
	private String clave;
	
	/** The nombres. */
	private String nombres;
	
	/** The paterno. */
	private String paterno;
	
	/** The materno. */
	private String materno;
	
	/** The correo. */
	private String correo;
	
	/** The direccion. */
	private String direccion;
	
	/** The telefono. */
	private String telefono;
	
	/** The rol. */
	private String rol;
	
	/** The usuario. */
	private UsuarioBO usuario;
	
	/** The cod alumno. */
	private String codAlumno;
	
	/** The plan alumno. */
	private int planAlumno;
	
	/** The usuario rol. */
	private UsuarioRolBO usuarioRol;
	
	/** The roles usuario. */
	private List<UsuarioBO> rolesUsuario;
	
	/** The lista planes. */
	private List<ClaseMaestra> listaPlanes;
	
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
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	
	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 * Gets the paterno.
	 *
	 * @return the paterno
	 */
	public String getPaterno() {
		return paterno;
	}
	
	/**
	 * Sets the paterno.
	 *
	 * @param paterno the new paterno
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	
	/**
	 * Gets the materno.
	 *
	 * @return the materno
	 */
	public String getMaterno() {
		return materno;
	}
	
	/**
	 * Sets the materno.
	 *
	 * @param materno the new materno
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public UsuarioBO getUsuario() {
		return usuario;
	}
	
	/**
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Gets the usuario rol.
	 *
	 * @return the usuario rol
	 */
	public UsuarioRolBO getUsuarioRol() {
		return usuarioRol;
	}
	
	/**
	 * Sets the usuario rol.
	 *
	 * @param usuarioRol the new usuario rol
	 */
	public void setUsuarioRol(UsuarioRolBO usuarioRol) {
		this.usuarioRol = usuarioRol;
	}
	
	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}
	
	/**
	 * Sets the rol.
	 *
	 * @param rol the new rol
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	/**
	 * Gets the roles usuario.
	 *
	 * @return the roles usuario
	 */
	public List<UsuarioBO> getRolesUsuario() {
		return rolesUsuario;
	}
	
	/**
	 * Sets the roles usuario.
	 *
	 * @param rolesUsuario the new roles usuario
	 */
	public void setRolesUsuario(List<UsuarioBO> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
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
	 * Gets the lista planes.
	 *
	 * @return the lista planes
	 */
	public List<ClaseMaestra> getListaPlanes() {
		return listaPlanes;
	}
	
	/**
	 * Sets the lista planes.
	 *
	 * @param listaPlanes the new lista planes
	 */
	public void setListaPlanes(List<ClaseMaestra> listaPlanes) {
		this.listaPlanes = listaPlanes;
	} 	
}
