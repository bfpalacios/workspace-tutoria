package pe.edu.sistemas.unayoe.services.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.transformer.Transformer;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.dao.dominio.Usuario;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;
 

@Component("alumnoTransformerToBO")
@Scope("singleton")
public class AlumnoTransformerToBO implements Transformer<Alumno,AlumnoBO> {

	public AlumnoBO transformer(final Alumno alumno) {
		AlumnoBO alumnoBO = null;
		if(alumno!=null){
			alumnoBO = new AlumnoBO();
			alumnoBO.setaCodigo(alumno.getACodigo().trim());
			alumnoBO.setaNombre(alumno.getANombre().toUpperCase().trim());
			alumnoBO.setaApellido(alumno.getAApellidos().toUpperCase().trim());
			
		}
		return alumnoBO;
	}

	public List<AlumnoBO> transformer(final List<Alumno> lista) {
		List<AlumnoBO> listaAlumnoBO = new ArrayList<AlumnoBO>();
		for (Alumno alumno : lista) {
			listaAlumnoBO.add(transformer(alumno));
		}
		return listaAlumnoBO;
	}

}
