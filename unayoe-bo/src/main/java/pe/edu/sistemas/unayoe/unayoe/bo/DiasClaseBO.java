package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class DiasClaseBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String anio;
	private String periodo;
	private String grupo;
	private String cCodigo;
	private String dia;
	private String horaIni;
	private String horaFin;

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraIni() {
		return horaIni;
	}

	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

}
