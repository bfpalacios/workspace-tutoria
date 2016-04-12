package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

public class ObservacionBO implements Serializable{

	private static final long serialVersionUID = 1L;
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
	private String estadoControl;
	private String diasCierre;
	private String sesionesCierre;
	private List<ClaseMaestra> listaEstados;
	private List<SesionBO> listaSesionesCierre;
	
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
	public List<ClaseMaestra> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(List<ClaseMaestra> listaEstados) {
		this.listaEstados = listaEstados;
	}
	public List<SesionBO> getListaSesionesCierre() {
		return listaSesionesCierre;
	}
	public void setListaSesionesCierre(List<SesionBO> listaSesionesCierre) {
		this.listaSesionesCierre = listaSesionesCierre;
	}
	public String getEstadoControl() {
		return estadoControl;
	}
	public void setEstadoControl(String estadoControl) {
		this.estadoControl = estadoControl;
	}
	public String getDiasCierre() {
		return diasCierre;
	}
	public void setDiasCierre(String diasCierre) {
		this.diasCierre = diasCierre;
	}
	public String getSesionesCierre() {
		return sesionesCierre;
	}
	public void setSesionesCierre(String sesionesCierre) {
		this.sesionesCierre = sesionesCierre;
	}	
}
