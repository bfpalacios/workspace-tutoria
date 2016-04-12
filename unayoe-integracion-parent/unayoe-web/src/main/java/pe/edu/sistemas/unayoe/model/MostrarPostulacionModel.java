package pe.edu.sistemas.unayoe.model;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component("mostrarPostulacionModel")
@ViewScoped
public class MostrarPostulacionModel implements Serializable {

	private static final long serialVersionUID = 5775645745264837971L;

	private Integer idConvocatoria;
	private Integer idTema;
	private String codigoCurso;

	public MostrarPostulacionModel() {
		this.idConvocatoria = null;
		this.idTema = null;
		this.codigoCurso = null;
	}

	public void limpiar() {
		this.idConvocatoria = null;
		this.idTema = null;
		this.codigoCurso = null;
	}

	public Integer getIdConvocatoria() {
		return idConvocatoria;
	}

	public Integer getIdTema() {
		return idTema;
	}

	public void setIdConvocatoria(Integer idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
		this.idTema = null;
	}

	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

}
