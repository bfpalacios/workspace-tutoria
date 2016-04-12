package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class AsistenciaCAlumnoBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private String aCodigo;
	private String grupo;
	private String cCodigo;
	private String asistencia;
	private String observacion;
	private String dia;
	private String cNombre;
	private String repitencia;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getaCodigo() {
		return aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getcCodigo() {
		return cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public String getRepitencia() {
		return repitencia;
	}
	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}
	public String getcNombre() {
		return cNombre;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	
	
	

}
