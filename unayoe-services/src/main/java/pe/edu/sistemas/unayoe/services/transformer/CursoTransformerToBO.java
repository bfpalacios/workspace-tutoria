package pe.edu.sistemas.unayoe.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.transformer.Transformer;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO; 

@Component("cursoTransformerToBO")
@Scope("singleton")
public class CursoTransformerToBO implements Transformer<Curso,CursoBO> {

	public CursoBO transformer(final Curso curso) {
		CursoBO cursoBO = null;
		if(curso!=null){
			cursoBO = new CursoBO();
			cursoBO.setcCodigo(curso.getCCodigo().trim());
			cursoBO.setCreditos(curso.getCreditos());
			cursoBO.setNombre(curso.getNombre().toUpperCase().trim());
			 
		}
		return cursoBO;
	}
	 
	

	public List<CursoBO> transformer(final List<Curso> lista) {
		List<CursoBO> listaCursoBO = new ArrayList<CursoBO>();
		for (Curso curso : lista) {
			listaCursoBO.add(transformer(curso));
		}
		return listaCursoBO;
	}

}
