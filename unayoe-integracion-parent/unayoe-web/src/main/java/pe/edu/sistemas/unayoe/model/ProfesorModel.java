package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.dao.dominio.Curso;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfesorModel.
 */
@Component("profesorModel")
@RequestScoped
public class ProfesorModel extends PersonaModel{
	
	/** The curso. */
	private Curso curso;

	/**
	 * Gets the curso.
	 *
	 * @return the curso
	 */
	public Curso getCurso() {
		return curso;
	}

	/**
	 * Sets the curso.
	 *
	 * @param curso the new curso
	 */
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	

}
