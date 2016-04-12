package pe.edu.sistemas.unayoe.controlador;

import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.LoginModel;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

@Controller("loginMBean")
@ViewScoped
public class LoginMBean {

	@Autowired
	private LoginModel loginModel;

	@Autowired
	private UsuarioServices usuarioServices;

	private String rol = "0";
	private String[] roles = null;

	@Autowired
	private RolServices rolServices;

	public LoginMBean() {
		System.out.println(":::::::INICIO :::::::::");
		rol = "0";
	}

	public String obtieneRol() {
		System.out.println("::::::::::::::: ENTRO ACA1 ::::::::::::::::");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("::::::::::::::: ENTRO ACA ::::::::::::::::");

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			String name = user.getUsername();
			System.out.println("USER INGRESADO: " + name);
			Object[] roles = user.getAuthorities().toArray();

			for (Object rol : roles) {
				System.out.println("rol: " + rol.toString());
			}

			System.out.println("USUARIO " + name + "  CON ROL " + roles[0].toString());
			return roles[0].toString();
		}

		return "0";
	}
	
	
	public String[] obtieneRoles() {
		System.out.println("::::::::::::::: ENTRA EN GET ROLES ::::::::::::::::");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("::::::::::::::: SE OBTUVO EL AUTH ::::::::::::::::");

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			String name = user.getUsername();
			
			System.out.println("USER INGRESADO: " + name);
			Object[] roles = user.getAuthorities().toArray();
			
			String[] sroles = new String[roles.length];
			for(int i=0; i<roles.length; i++) {
				sroles[i] = roles[i].toString();
				System.out.println("rol: " + sroles[i]);
			}

		System.out.println("USUARIO " + name + "  con #" + sroles.length + " roles");
			return sroles;
		}

		return null;
	}
	

	public String entrar() {
		try {

			// httpServletRequest = null;
			UsuarioBO usuarioBO = new UsuarioBO();
			usuarioBO = usuarioServices.obtenerUsuario(this.loginModel.getUsuario());
			System.out.println("usuarioBO: " + usuarioBO);

			// mostrar la interface del rol seleccionado
			this.rol = this.loginModel.getRol();

			if (usuarioBO != null) {
				// httpServletRequest.setAttribute("sessionUsuario",
				// usuarioBO.getUsuario());
				// FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto",
				// null);
				// faceContext.addMessage(null, facesMessage);
				return "/paginas/principal.xhtml";
			} else {
				// facesMessage=new FacesMessage(FacesMessage.SEVERITY_ERROR,
				// "Usuario o contraseña incorrecto", null);
				// faceContext.addMessage(null, facesMessage);
				return "/login.xhtml";
			}

		} catch (Exception e) {
			System.out.println("" + e.toString());
		}
		return "/login.xhtml";
	}

	public List<RolBO> getlistaRoles() {
		return this.rolServices.listarRoles();
	}

	public LoginModel getLoginModel() {
		return loginModel;
	}

	public String getRol() {
		rol = obtieneRol();
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String[] getRoles() {
		this.roles = obtieneRoles();
		return this.roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
