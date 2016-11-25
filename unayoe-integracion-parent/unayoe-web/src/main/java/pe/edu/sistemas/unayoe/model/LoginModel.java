package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginModel.
 */
@Component("loginModel")
@SessionScoped
public class LoginModel {

	/** The usuario. */
	private String usuario;
	
	/** The clave. */
	private String clave;
	
	/** The rol. */
	private String rol;

	/**
	 * Instantiates a new login model.
	 */
	public LoginModel() {

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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
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
	 * Sets the usuario.
	 *
	 * @param usuario the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Sets the rol.
	 *
	 * @param rol the new rol
	 */
	public void setRol(String rol) {
		System.out.println("setea rol: " + rol);
		this.rol = rol;
	}

	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public String getRol() {
		System.out.println("pide rol: " + this.rol);
		return rol;
	}

}
