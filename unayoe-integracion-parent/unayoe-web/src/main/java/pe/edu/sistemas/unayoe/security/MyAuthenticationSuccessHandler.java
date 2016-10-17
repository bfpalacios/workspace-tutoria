package pe.edu.sistemas.unayoe.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.services.UsuarioServices;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UsuarioServices usuarioService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication.isAuthenticated()){
			UsuarioBO usuarioBO = null;
			try {
				User user = (User) authentication.getPrincipal();
				usuarioBO = usuarioService.obtenerUsuario(user.getUsername());
				/* Meter validacion*/
				setDefaultTargetUrl("/home.xhtml");
				redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				setDefaultTargetUrl("/login.xhtml");
			}
		}	
	}
}
