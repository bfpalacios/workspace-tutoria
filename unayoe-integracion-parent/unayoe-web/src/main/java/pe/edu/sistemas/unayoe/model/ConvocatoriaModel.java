package pe.edu.sistemas.unayoe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component("convocatoriaModel")
@ViewScoped
public class ConvocatoriaModel implements Serializable {
	private static final long serialVersionUID = 4636725782691209125L;

	private Integer id;
	private Integer idPeriodo;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFinal;
	private String codigoCurso;
	private Map<String, List<Integer>> temasCurso;

	public ConvocatoriaModel() {
		this.inicializar();
	}

	public void inicializar() {
		this.id = null;
		this.nombre = null;
		this.idPeriodo = null;
		this.fechaFinal = null;
		this.fechaInicio = null;
		this.codigoCurso = null;
		this.temasCurso = new HashMap<String, List<Integer>>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public List<Integer> getTemas() {
		if (this.codigoCurso != "" && this.codigoCurso != null) {
			return this.temasCurso.get(this.codigoCurso);
		}
		return null;
	}

	public void setTemas(List<Integer> temas) {
		if (this.codigoCurso != "" && this.codigoCurso != null) {
			this.temasCurso.put(this.codigoCurso, temas);
		}
	}

	public Map<String, List<Integer>> getTemasCurso() {
		return temasCurso;
	}

	public void setTemasCurso(Map<String, List<Integer>> temasCurso) {
		this.temasCurso = temasCurso;
	}

}
