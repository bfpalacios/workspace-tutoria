package pe.edu.sistemas.unayoe.security;

import java.io.IOException;
import java.util.List;

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

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;

// TODO: Auto-generated Javadoc
/**
 * The Class MyAuthenticationSuccessHandler.
 * Esta clase se encarga de redirigir a las vistas correspondientes
 * de acuerdo al rol del usuario logueado
 * 
 * @author ULLOA
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/** The redirect strategy. */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/** The usuario service. */
	@Autowired
	private UsuarioServices usuarioService;

	/** The rol service. */
	@Autowired
	private RolServices rolService;

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		if (authentication.isAuthenticated()) {
			System.out.println("en successhandler");
			UsuarioBO usuarioBO = null;
			try {
				User user = (User) authentication.getPrincipal();
				usuarioBO = usuarioService.obtenerUsuario(user.getUsername());
				List<RolBO> roles = rolService.getRolesByUser(usuarioBO.getIdUsuario());
				// de acuerdo a rol debe redireccionar
				if (roles.size() == 1) {
					String rol = roles.get(0).getNombre();
					String url = "";
					switch (rol) {
					case "ROLE_ADMIN_O":
						url = "/paginas/ModuloObservados/principal_o.xhtml";
						break;
					case "ROLE_DIR_ACA_O":
						url = "/paginas/ModuloObservados/principal_dir_aca_o.xhtml";
						break;
					case "ROLE_UNAYOE_O":
						url = "/paginas/ModuloObservados/principal_unayoe_o.xhtml";
						break;
					case "ROLE_OCAA_O":
						url = "/paginas/ModuloObservados/principal_ocaa_o.xhtml";
						break;
					case "ROLE_TUTOR_O":
						url = "/paginas/ModuloObservados/principal_tutor_o.xhtml";
						break;
					case "ROLE_DECANO_O":
						url = "/paginas/ModuloObservados/principal_decano_o.xhtml";
						break;
					case "ROLE_ADMIN_R":
						url = "/paginas/ModuloRegulares/principal_r.xhtml";
						break;
					case "ROLE_UNAYOE_R":
						url = "/paginas/ModuloRegulares/principal_unayoe_r.xhtml";
						break;
					case "ROLE_DIR_ACA_R":
						url = "/paginas/ModuloRegulares/principal_dir_aca_r.xhtml";
						break;
					case "ROLE_OCAA_R":
						url = "/paginas/ModuloRegulares/principal_ocaa_r.xhtml";
						break;
					case "ROLE_TUTOR_R":
						url = "/paginas/ModuloRegulares/principal_tutor_r.xhtml";
						break;
					case "ROLE_ALUMNO_R":
						url = "/paginas/ModuloRegulares/principal_alumno_r.xhtml";
						break;
					case "ROLE_DECANO_R":
						url = "/paginas/ModuloRegulares/principal_decano_r.xhtml";
						break;
					case "ROLE_ADMIN_P":
						url = "/paginas/ModuloPares/principal_p.xhtml";
						break;
					case "ROLE_TUTOR_P":
						url = "/paginas/ModuloPares/principal_tutor_p.xhtml";
						break;
					case "ROLE_ALUMNO_P":
						url = "/paginas/ModuloPares/principal_alumno_p.xhtml";
						break;
					case "ROLE_OCAA_P":
						url = "/paginas/ModuloPares/principal_ocaa_p.xhtml";
						break;
					case "ROLE_DECANO_P":
						url = "/paginas/ModuloPares/principal_decano_p.xhtml";
						break;
					}

					setDefaultTargetUrl(url);
System.out.println("url " + url);
				} else {
					// para antes de agregarlo deberia configurarse el spring
					// security
					// si es super admin se le mustra el home
					/*
					 * if (roles.size() == 3) {
					 * setDefaultTargetUrl("/home.xhtml"); }
					 */
				}
				// si se quire regresar a lo anterior :
				// comentar el if de arriba y descomentar la linea de abajo izi
				// setDefaultTargetUrl("/home.xhtml");
				
				redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				setDefaultTargetUrl("/login.xhtml");
			}
		}
	}
}
