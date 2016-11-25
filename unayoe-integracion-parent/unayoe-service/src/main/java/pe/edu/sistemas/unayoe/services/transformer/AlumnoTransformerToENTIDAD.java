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

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoTransformerToENTIDAD.
 */
@Component("alumnoTransformerToENTIDAD")
@Scope("singleton")
public class AlumnoTransformerToENTIDAD implements Transformer<AlumnoBO,Alumno> {

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.transformer.Transformer#transformer(java.lang.Object)
	 */
	public Alumno transformer(final AlumnoBO alumnoBO) {
		Alumno alumno = new Alumno();
		if(alumnoBO!=null){
			alumno.setACodigo(alumnoBO.getaCodigo());
			alumno.setAApellidos(alumnoBO.getaApellido());
			alumno.setANombre(alumnoBO.getaNombre());
			 
		}
		return alumno;
	}
	 
	

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.transformer.Transformer#transformer(java.util.List)
	 */
	public List<Alumno> transformer(final List<AlumnoBO> lista) {
		List<Alumno> listaAlumno = new ArrayList<Alumno>();
		for (AlumnoBO alumnoBO : lista) {
			listaAlumno.add(transformer(alumnoBO));
		}
		return listaAlumno;
	}

}
