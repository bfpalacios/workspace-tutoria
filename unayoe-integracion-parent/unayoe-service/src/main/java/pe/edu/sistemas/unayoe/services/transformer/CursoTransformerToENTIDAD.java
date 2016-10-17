package pe.edu.sistemas.unayoe.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.transformer.Transformer;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

@Component("cursoTransformerToENTIDAD")
@Scope("singleton")
public class CursoTransformerToENTIDAD implements Transformer<CursoBO,Curso> {

	public Curso transformer(final CursoBO cursoBO) {
		Curso curso = new Curso();
		if(cursoBO!=null){
			curso.setCCodigo(cursoBO.getcCodigo());
			curso.setNombre(cursoBO.getNombre());
			curso.setCreditos(cursoBO.getCreditos());	 
		}
		return curso;
	}

	public List<Curso> transformer(final List<CursoBO> lista) {
		List<Curso> listaCurso = new ArrayList<Curso>();
		for (CursoBO cursoBO : lista) {
			listaCurso.add(transformer(cursoBO));
		}
		return listaCurso;
	}
}
