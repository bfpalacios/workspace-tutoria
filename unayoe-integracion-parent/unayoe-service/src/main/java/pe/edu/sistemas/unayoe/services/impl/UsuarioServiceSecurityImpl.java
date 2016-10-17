package pe.edu.sistemas.unayoe.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.unayoe.bo.RolBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
import pe.edu.sistemas.unayoe.services.RolServices;
import pe.edu.sistemas.unayoe.services.UsuarioServiceSecurity;
import pe.edu.sistemas.unayoe.services.UsuarioServices;

@SuppressWarnings("deprecation")
@Service("usuarioServiceSecurity")
@Transactional(value="transactionManager")
public class UsuarioServiceSecurityImpl implements UsuarioServiceSecurity {

	@Autowired
	private UsuarioServices usuarioService;
	@Autowired
	private RolServices rolService;
	
	@Override
	public UserDetails loadUserByUsername(String usuario)
			throws UsernameNotFoundException {
		UsuarioBO usuarioBO = null;
		try {
			usuarioBO = usuarioService.obtenerUsuario(usuario);
			if(usuarioBO==null){
				throw new UsernameNotFoundException("Usuario no encontrado");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage()); 
		}
		return  makeUser(usuarioBO);
	}

	private User makeUser(UsuarioBO usuario){
		return new User(usuario.getIdUsuario(), usuario.getContrasenia(),
                true, true, true, true, makeGrantedAuthorities(usuario));
	}

	private Collection<GrantedAuthority> makeGrantedAuthorities(UsuarioBO usuario){
		Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		List<RolBO> listRol = rolService.getRolesByUser(usuario.getIdUsuario());
		for (RolBO rolBO : listRol) {
		
		System.out.println( " role: " + rolBO.getNombre());
		result.add(new GrantedAuthorityImpl(rolBO.getNombre()));
		}
        return result;
	}
	
}
