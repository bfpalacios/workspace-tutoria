package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class AsistenciaTProfBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fechaT;
	private String tAnio;
	private String tPeriodo;
	private String tCodigo;
	private String asistenciaT;
	private String observacionT;
	private ProfesorBO profesor;
	private CursoBO curso;
	private DiasClaseBO diasClase;	
	
	public DiasClaseBO getDiasClase() {
		return diasClase;
	}
	public void setDiasClase(DiasClaseBO diasClase) {
		this.diasClase = diasClase;
	}
	public String getFechaT() {
		return fechaT;
	}
	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
	}
	public String gettAnio() {
		return tAnio;
	}
	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}
	public String gettPeriodo() {
		return tPeriodo;
	}
	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}
	public String gettCodigo() {
		return tCodigo;
	}
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}
	public String getAsistenciaT() {
		return asistenciaT;
	}
	public void setAsistenciaT(String asistenciaT) {
		this.asistenciaT = asistenciaT;
	}
	public String getObservacionT() {
		return observacionT;
	}
	public void setObservacionT(String observacionT) {
		this.observacionT = observacionT;
	}
	public ProfesorBO getProfesor() {
		return profesor;
	}
	public void setProfesor(ProfesorBO profesor) {
		this.profesor = profesor;
	}
	public CursoBO getCurso() {
		return curso;
	}
	public void setCurso(CursoBO curso) {
		this.curso = curso;
	}
	

}
