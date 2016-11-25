package pe.edu.sistemas.unayoe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostulacionModel.
 */
@Component("postulacionModel")
@ViewScoped
public class PostulacionModel {

	/** The archivo CV. */
	private UploadedFile archivoCV;
	
	/** The codigo convocatoria. */
	private Integer codigoConvocatoria;
	
	/** The map temas. */
	private Map<String, List<Integer>> mapTemas;
	
	/** The codigo curso. */
	private String codigoCurso;

	/** The disponibilidad. */
	private List<DisponibilidadTutoriaParBO> disponibilidad;

	/**
	 * Instantiates a new postulacion model.
	 */
	public PostulacionModel() {
		this.archivoCV = null;
		this.codigoConvocatoria = null;
		this.mapTemas = new HashMap<String, List<Integer>>();
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
		this.codigoCurso = null;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {
		this.archivoCV = null;
		this.codigoConvocatoria = null;
		this.mapTemas = new HashMap<String, List<Integer>>();
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
		this.codigoCurso = null;
	}

	/**
	 * Gets the nombre archivo CV.
	 *
	 * @return the nombre archivo CV
	 */
	public String getNombreArchivoCV() {
		if (this.archivoCV != null) {
			return this.archivoCV.getFileName();
		}
		return null;
	}

	/**
	 * Gets the archivo CV.
	 *
	 * @return the archivo CV
	 */
	public UploadedFile getArchivoCV() {
		return archivoCV;
	}

	/**
	 * Gets the codigo convocatoria.
	 *
	 * @return the codigo convocatoria
	 */
	public Integer getCodigoConvocatoria() {
		return codigoConvocatoria;
	}

	/**
	 * Gets the map temas.
	 *
	 * @return the map temas
	 */
	public Map<String, List<Integer>> getMapTemas() {
		return mapTemas;
	}

	/**
	 * Sets the archivo CV.
	 *
	 * @param archivoCV the new archivo CV
	 */
	public void setArchivoCV(UploadedFile archivoCV) {
		this.archivoCV = archivoCV;
	}

	/**
	 * Sets the codigo convocatoria.
	 *
	 * @param codigoConvocatoria the new codigo convocatoria
	 */
	public void setCodigoConvocatoria(Integer codigoConvocatoria) {
		this.codigoConvocatoria = codigoConvocatoria;
	}

	/**
	 * Sets the map temas.
	 *
	 * @param mapTemas the map temas
	 */
	public void setMapTemas(Map<String, List<Integer>> mapTemas) {
		this.mapTemas = mapTemas;
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

	/**
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	public List<Integer> getTemas() {
		if (this.codigoCurso != null && this.codigoCurso.compareTo("") != 0) {
			return mapTemas.get(this.codigoCurso);
		}
		return null;
	}

	/**
	 * Sets the temas.
	 *
	 * @param temas the new temas
	 */
	public void setTemas(List<Integer> temas) {
		if (this.codigoCurso != null && this.codigoCurso.compareTo("") != 0) {
			this.mapTemas.put(this.codigoCurso, temas);
		}
	}

	/**
	 * Gets the disponibilidad.
	 *
	 * @return the disponibilidad
	 */
	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * Sets the disponibilidad.
	 *
	 * @param disponibilidad the new disponibilidad
	 */
	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}
