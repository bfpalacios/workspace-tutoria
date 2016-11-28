package pe.edu.sistemas.unayoe.controlador;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;


import pe.edu.sistemas.unayoe.model.LoginModel;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.listener.*;
// TODO: Auto-generated Javadoc
/**
 * The Class LoginMBean.
 * Esta clase se encarga de gestionar el ingreso al sistema 
 * 
 */
@Controller("loginMBean")
@ViewScoped
public class LoginMBean /*implements PhaseListener*/ {
     /*log*/
	
      
      
	/** The login model. */
	@Autowired
	private LoginModel loginModel;

	/** The usuario services. */
	@Autowired
	private UsuarioServices usuarioServices;

	/** The rol. */
	private String rol = "0";
	
	/** The roles. */
	private String[] roles = null;

	/** The rol services. */
	@Autowired
	private RolServices rolServices;

	/** The sha password encoder. */
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;

	/**
	 * Instantiates a new login M bean.
	 */
	public LoginMBean() {
		System.out.println(":::::::INICIO :::::::::");
		rol = "0";
	}

	/**
	 * Obtiene rol.
	 *
	 * @return the string
	 */
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

	/**
	 * Obtiene roles.
	 *
	 * @return the string[]
	 */
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
			for (int i = 0; i < roles.length; i++) {
				sroles[i] = roles[i].toString();
				System.out.println("rol: " + sroles[i]);
			}

			System.out.println("USUARIO " + name + "  con #" + sroles.length + " roles");
			return sroles;
		}
		return null;
	}

	/**
	 * Entrar.
	 *
	 * @return the string
	 */
	public String entrar() {
		try {
			System.out.println(" INICIO DE ENTRAR");
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

	/**
	 * Do login.
	 *
	 * @return the string
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String doLogin() throws ServletException, IOException {
		// try{

		System.out.println("do login");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String password = shaPasswordEncoder.encodePassword(loginModel.getClave(), null);
		
		ConexionLog.registrarMensaje(0,"***LOGIN***");
		ConexionLog.registrarMensaje(0,"Usuario"+loginModel.getUsuario());
		ConexionLog.registrarMensaje(0,"Clave"+loginModel.getClave());
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(
				"/j_spring_security_check?j_username=" + loginModel.getUsuario() + "&j_password=" + password);
		// try{
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		/*
		 * }catch(Exception e){ mostrarError(e.getMessage()); } FacesContext
		 * facesContext = FacesContext.getCurrentInstance();
		 * facesContext.getExternalContext().getSessionMap().put("user",
		 * loginModel);
		 * 
		 * return "";
		 * 
		 * }catch(Exception e){
		 * 
		 * }
		 */
		return null;

	}

	/**
	 * Gets the lista roles.
	 *
	 * @return the lista roles
	 */
	public List<RolBO> getlistaRoles() {
		return this.rolServices.listarRoles();
	}

	/**
	 * Gets the login model.
	 *
	 * @return the login model
	 */
	public LoginModel getLoginModel() {
		return loginModel;
	}

	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public String getRol() {
		rol = obtieneRol();
		return this.rol;
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
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public String[] getRoles() {
		this.roles = obtieneRoles();
		return this.roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * Mostrar error.
	 *
	 * @param msjResumen the msj resumen
	 */
	public static void mostrarError(String msjResumen) {
		FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, msjResumen, "Credenciales no válidas");
		FacesContext.getCurrentInstance().addMessage("", msj);
	}
	/*
	 * @Override public void afterPhase(PhaseEvent event) {
	 * 
	 * 
	 * }
	 * 
	 * @Override public PhaseId getPhaseId() { return PhaseId.RENDER_RESPONSE; }
	 */
/** The msj. */
	/*
	@Override
	public void afterPhase(PhaseEvent event) {
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);
		System.out.println("antes de bad");
		if (e instanceof BadCredentialsException) {

			System.out.println("user incorrecto ");
			/*FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", ""));
			*/FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario o password incorrecto.", "");

			/** The faces context. */
			FacesContext facesContext = FacesContext.getCurrentInstance();
		/*	
			  Flash flash = facesContext.getExternalContext().getFlash();
			  flash.setKeepMessages(true); flash.setRedirect(true);*/
			 

		/*	facesContext.addMessage(null, msj);
		}

	}*/
/*
	@Override
	public void beforePhase(PhaseEvent event) {
	
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}*/

}
