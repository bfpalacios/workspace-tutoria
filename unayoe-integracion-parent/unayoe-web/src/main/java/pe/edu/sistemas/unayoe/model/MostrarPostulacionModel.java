package pe.edu.sistemas.unayoe.model;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class MostrarPostulacionModel.
 */
@Component("mostrarPostulacionModel")
@ViewScoped
public class MostrarPostulacionModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5775645745264837971L;

	/** The id convocatoria. */
	private Integer idConvocatoria;
	
	/** The id tema. */
	private Integer idTema;
	
	/** The codigo curso. */
	private String codigoCurso;

	/**
	 * Instantiates a new mostrar postulacion model.
	 */
	public MostrarPostulacionModel() {
		this.idConvocatoria = null;
		this.idTema = null;
		this.codigoCurso = null;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {
		this.idConvocatoria = null;
		this.idTema = null;
		this.codigoCurso = null;
	}

	/**
	 * Gets the id convocatoria.
	 *
	 * @return the id convocatoria
	 */
	public Integer getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Gets the id tema.
	 *
	 * @return the id tema
	 */
	public Integer getIdTema() {
		return idTema;
	}

	/**
	 * Sets the id convocatoria.
	 *
	 * @param idConvocatoria the new id convocatoria
	 */
	public void setIdConvocatoria(Integer idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
		this.idTema = null;
	}

	/**
	 * Sets the id tema.
	 *
	 * @param idTema the new id tema
	 */
	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
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

}
