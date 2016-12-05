package pe.edu.sistemas.unayoe.security;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.listener.ConexionLog;


// TODO: Auto-generated Javadoc
/**
 * The Class MyAuthenticationFailureHandler.
 * Esta clase se encarga de enviar un mensaje de error para ser
 * mostrado en el login, si es que el usuario que ingresó
 * es incorrecto
 * se redirige al login.xhtmk y además envia el mensaje que se mostrado
 * en el login
 * 
 * @author ULLOA
 */
@Component("myAuthenticationFailureHandler")
@ViewScoped
public class MyAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {
	
	   private static final String MESSAGES_RESOURCE_BUNDLE_NAME = "msgs";
	   private static final String ACCESS_DENIED_MESSAGE_KEY     = "accessDeniedMessage";
	   private static final String BAD_CREDENTIALS_MESSAGE_KEY   = "badCredentialsMessage";

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	final static Logger log = Logger.getLogger(MyAuthenticationFailureHandler.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		if(exception instanceof BadCredentialsException){			
			System.out.println("Usuario o pass incorrecta");
			
			log.warn("Usuario o pass incorrecta");
			
			 FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Usuario y/o contraseña incorrecta.", "");
			 System.out.println("msj "+msj);
			 
			 
			 FacesContext facesContext = FacesContext.getCurrentInstance();
			/* Flash flash = facesContext.getExternalContext().getFlash();
			 flash.setKeepMessages(true);
			 flash.setRedirect(true);*/
			 
        	facesContext.addMessage(null, msj);
        	
		   //  setDefaultFailureUrl("/login.xhtml");
		     
		     redirectStrategy.sendRedirect(request, response, "/login.xhtml");
		}
	}
}
