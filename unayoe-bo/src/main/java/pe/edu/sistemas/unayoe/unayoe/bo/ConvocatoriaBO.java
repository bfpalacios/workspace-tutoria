package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ConvocatoriaBO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date fechaInicio;
	private Date fechaFinal;
	private String nombre;
	private Integer idPeriodo;
	private List<Integer> idTemas;

	public ConvocatoriaBO() {
		this.id = null;
		this.fechaInicio = null;
		this.fechaFinal = null;

		this.nombre = null;
		this.idPeriodo = null;
		this.idTemas = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public List<Integer> getIdTemas() {
		return idTemas;
	}

	public void setIdTemas(List<Integer> idTemas) {
		this.idTemas = idTemas;
	}

}
