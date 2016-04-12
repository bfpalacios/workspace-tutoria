package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

@Component("loginModel")
@SessionScoped
public class LoginModel {

	private String usuario;
	private String clave;
	private String rol;

	public LoginModel() {

	}

	public String getClave() {
		return clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setRol(String rol) {
		System.out.println("setea rol: " + rol);
		this.rol = rol;
	}

	public String getRol() {
		System.out.println("pide rol: " + this.rol);
		return rol;
	}

}
