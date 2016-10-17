package pe.edu.sistemas.unayoe.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.transformer.Transformer;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
 

@Component("usuarioTransformerToBO")
@Scope("singleton")
public class UsuarioTransformerToBO implements Transformer<Usuario,UsuarioBO> {

	public UsuarioBO transformer(final Usuario usuario) {
		UsuarioBO usuarioBO = null;
		if(usuario!=null){
			usuarioBO = new UsuarioBO();
			usuarioBO.setIdUsuario(usuario.getIdUsuario());
			usuarioBO.setContrasenia(usuario.getClave());
		}
		return usuarioBO;
	}

	public List<UsuarioBO> transformer(final List<Usuario> lista) {
		List<UsuarioBO> listaUsuarioBO = new ArrayList<UsuarioBO>();
		for (Usuario usuario : lista) {
			listaUsuarioBO.add(transformer(usuario));
		}
		return listaUsuarioBO;
	}

}
