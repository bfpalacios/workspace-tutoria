package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class TemaModel.
 */
@Component("temaModel")
@ViewScoped
public class TemaModel {

	/** The nombre. */
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The codigo curso. */
	private String codigoCurso;

	/**
	 * Instantiates a new tema model.
	 */
	public TemaModel() {
		this.inicializar();
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		this.nombre = null;
		this.descripcion = null;
		this.codigoCurso = null;
	}

	/**
	 * Gets the codigo curso.
	 *
	 * @return the codigo curso
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the codigo curso.
	 *
	 * @param codigoCurso the new codigo curso
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
