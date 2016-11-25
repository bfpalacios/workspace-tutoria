package pe.edu.sistemas.unayoe.services.transformer;

import java.util.ArrayList;
import java.util.List;  

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component; 

import pe.edu.sistemas.unayoe.core.transformer.Transformer; 
import pe.edu.sistemas.unayoe.dao.dominio.Profesor; 
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;


// TODO: Auto-generated Javadoc
/**
 * The Class ProfesorTransformerToBo.
 */
@Component("profesorTransformerToBo")
@Scope("singleton")
public class ProfesorTransformerToBo implements  Transformer<Profesor,ProfesorBO> {
	 

	  
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.transformer.Transformer#transformer(java.lang.Object)
	 */
	public ProfesorBO transformer(Profesor profesor) {
		ProfesorBO profesorBO = null;
		if(profesor!=null){
			profesorBO = new ProfesorBO();
			profesorBO.setpCodigo(profesor.getPCodigo().trim());
			profesorBO.setpNombre(profesor.getPNombre().toUpperCase().trim());
			profesorBO.setpApellidos(profesor.getPApellidos().toUpperCase().trim());
			  
		} 
		return profesorBO;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.transformer.Transformer#transformer(java.util.List)
	 */
	public List<ProfesorBO> transformer(List<Profesor> lista) {
		List<ProfesorBO> listaProfesorBO = new ArrayList<ProfesorBO>();
		for (Profesor profesor : lista) {
			listaProfesorBO.add(transformer(profesor));
		}
		return listaProfesorBO; 
	}

	 

}
