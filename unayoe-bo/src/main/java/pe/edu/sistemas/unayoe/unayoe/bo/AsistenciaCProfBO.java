package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class AsistenciaCProfBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fecha;
	private String pCodigo;
	private String grupo;
	private String cCodigo;
	private String asistencia;
	private String observacion;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getpCodigo() {
		return pCodigo;
	}

	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
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

}
