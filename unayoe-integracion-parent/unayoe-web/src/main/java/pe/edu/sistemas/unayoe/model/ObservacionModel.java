package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

@Component("observacionModel")
@RequestScoped
public class ObservacionModel {
	private int anio;
	private int periodo;
	private String codTutoria;
	private String idObservacion;
	private String observacion;
	private String criticidad;
	private String sesionRegistro;
	private String fechaRegistro;
	private String sesionCierre;
	private String observacionCierre;
	private String fechaCierre;
	private int estadoObservacion;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public String getCodTutoria() {
		return codTutoria;
	}
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	public String getIdObservacion() {
		return idObservacion;
	}
	public void setIdObservacion(String idObservacion) {
		this.idObservacion = idObservacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getCriticidad() {
		return criticidad;
	}
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	public String getSesionRegistro() {
		return sesionRegistro;
	}
	public void setSesionRegistro(String sesionRegistro) {
		this.sesionRegistro = sesionRegistro;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getSesionCierre() {
		return sesionCierre;
	}
	public void setSesionCierre(String sesionCierre) {
		this.sesionCierre = sesionCierre;
	}
	public String getObservacionCierre() {
		return observacionCierre;
	}
	public void setObservacionCierre(String observacionCierre) {
		this.observacionCierre = observacionCierre;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public int getEstadoObservacion() {
		return estadoObservacion;
	}
	public void setEstadoObservacion(int estadoObservacion) {
		this.estadoObservacion = estadoObservacion;
	}
}
