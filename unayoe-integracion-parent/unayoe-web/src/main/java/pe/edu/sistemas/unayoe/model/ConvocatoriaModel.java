package pe.edu.sistemas.unayoe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvocatoriaModel.
 */
@Component("convocatoriaModel")
@ViewScoped
public class ConvocatoriaModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4636725782691209125L;

	/** The id. */
	private Integer id;
	
	/** The id periodo. */
	private Integer idPeriodo;
	
	/** The nombre. */
	private String nombre;
	
	/** The fecha inicio. */
	private Date fechaInicio;
	
	/** The fecha final. */
	private Date fechaFinal;
	
	/** The codigo curso. */
	private String codigoCurso;
	
	/** The temas curso. */
	private Map<String, List<Integer>> temasCurso;

	/**
	 * Instantiates a new convocatoria model.
	 */
	public ConvocatoriaModel() {
		this.inicializar();
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		this.id = null;
		this.nombre = null;
		this.idPeriodo = null;
		this.fechaFinal = null;
		this.fechaInicio = null;
		this.codigoCurso = null;
		this.temasCurso = new HashMap<String, List<Integer>>();
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
	 * Gets the fecha final.
	 *
	 * @return the fecha final
	 */
	public Date getFechaFinal() {
		return fechaFinal;
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
	 * Sets the fecha final.
	 *
	 * @param fechaFinal the new fecha final
	 */
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
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
	 * Sets the codigo curso.
	 *
	 * @param codigoCurso the new codigo curso
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
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
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	public List<Integer> getTemas() {
		if (this.codigoCurso != "" && this.codigoCurso != null) {
			return this.temasCurso.get(this.codigoCurso);
		}
		return null;
	}

	/**
	 * Sets the temas.
	 *
	 * @param temas the new temas
	 */
	public void setTemas(List<Integer> temas) {
		if (this.codigoCurso != "" && this.codigoCurso != null) {
			this.temasCurso.put(this.codigoCurso, temas);
		}
	}

	/**
	 * Gets the temas curso.
	 *
	 * @return the temas curso
	 */
	public Map<String, List<Integer>> getTemasCurso() {
		return temasCurso;
	}

	/**
	 * Sets the temas curso.
	 *
	 * @param temasCurso the temas curso
	 */
	public void setTemasCurso(Map<String, List<Integer>> temasCurso) {
		this.temasCurso = temasCurso;
	}

}
