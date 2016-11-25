package pe.edu.sistemas.unayoe.unayoe.bo;

// TODO: Auto-generated Javadoc
/**
 * The Class TemaBO.
 */
public class TemaBO {

	/** The id. */
	private Integer id;
	
	/** The nombre. */
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The codigo curso. */
	private String codigoCurso;

	/**
	 * Instantiates a new tema BO.
	 */
	public TemaBO() {
		this.id = null;
		this.nombre = null;
		this.descripcion = null;
		this.codigoCurso = null;
	}

	/**
	 * Instantiates a new tema BO.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param descripcion the descripcion
	 * @param codigoCurso the codigo curso
	 */
	public TemaBO(Integer id, String nombre, String descripcion, String codigoCurso) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoCurso = codigoCurso;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
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
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Sets the codigo curso.
	 *
	 * @param codigoCurso the new codigo curso
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
}
