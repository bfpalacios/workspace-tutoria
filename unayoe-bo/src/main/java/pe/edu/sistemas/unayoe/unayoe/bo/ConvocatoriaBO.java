package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvocatoriaBO.
 */
public class ConvocatoriaBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;
	
	/** The fecha inicio. */
	private Date fechaInicio;
	
	/** The fecha final. */
	private Date fechaFinal;
	
	/** The nombre. */
	private String nombre;
	
	/** The id periodo. */
	private Integer idPeriodo;
	
	/** The id temas. */
	private List<Integer> idTemas;

	/**
	 * Instantiates a new convocatoria BO.
	 */
	public ConvocatoriaBO() {
		this.id = null;
		this.fechaInicio = null;
		this.fechaFinal = null;

		this.nombre = null;
		this.idPeriodo = null;
		this.idTemas = null;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the fecha final.
	 *
	 * @return the fecha final
	 */
	public Date getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Sets the fecha final.
	 *
	 * @param fechaFinal the new fecha final
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the id periodo.
	 *
	 * @return the id periodo
	 */
	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	/**
	 * Sets the id periodo.
	 *
	 * @param idPeriodo the new id periodo
	 */
	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	/**
	 * Gets the id temas.
	 *
	 * @return the id temas
	 */
	public List<Integer> getIdTemas() {
		return idTemas;
	}

	/**
	 * Sets the id temas.
	 *
	 * @param idTemas the new id temas
	 */
	public void setIdTemas(List<Integer> idTemas) {
		this.idTemas = idTemas;
	}

}
