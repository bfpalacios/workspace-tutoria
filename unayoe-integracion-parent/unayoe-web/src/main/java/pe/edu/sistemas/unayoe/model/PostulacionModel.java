package pe.edu.sistemas.unayoe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

@Component("postulacionModel")
@ViewScoped
public class PostulacionModel {

	private UploadedFile archivoCV;
	private Integer codigoConvocatoria;
	private Map<String, List<Integer>> mapTemas;
	private String codigoCurso;

	private List<DisponibilidadTutoriaParBO> disponibilidad;

	public PostulacionModel() {
		this.archivoCV = null;
		this.codigoConvocatoria = null;
		this.mapTemas = new HashMap<String, List<Integer>>();
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
		this.codigoCurso = null;
	}

	public void limpiar() {
		this.archivoCV = null;
		this.codigoConvocatoria = null;
		this.mapTemas = new HashMap<String, List<Integer>>();
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
		this.codigoCurso = null;
	}

	public String getNombreArchivoCV() {
		if (this.archivoCV != null) {
			return this.archivoCV.getFileName();
		}
		return null;
	}

	public UploadedFile getArchivoCV() {
		return archivoCV;
	}

	public Integer getCodigoConvocatoria() {
		return codigoConvocatoria;
	}

	public Map<String, List<Integer>> getMapTemas() {
		return mapTemas;
	}

	public void setArchivoCV(UploadedFile archivoCV) {
		this.archivoCV = archivoCV;
	}

	public void setCodigoConvocatoria(Integer codigoConvocatoria) {
		this.codigoConvocatoria = codigoConvocatoria;
	}

	public void setMapTemas(Map<String, List<Integer>> mapTemas) {
		this.mapTemas = mapTemas;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public List<Integer> getTemas() {
		if (this.codigoCurso != null && this.codigoCurso.compareTo("") != 0) {
			return mapTemas.get(this.codigoCurso);
		}
		return null;
	}

	public void setTemas(List<Integer> temas) {
		if (this.codigoCurso != null && this.codigoCurso.compareTo("") != 0) {
			this.mapTemas.put(this.codigoCurso, temas);
		}
	}

	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}
