package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class SesionBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int anio;
	private int periodo;
	private String codTutoria;
	private String fechaTutoria;
	private int nroSesion;
	private String descSesion;
	private String horaIni;
	private String horaFin;
	private String estadoSesion;
	private byte[] acta;
	private String nombreActa;
	private int estadoActa;
	
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
	public String getFechaTutoria() {
		return fechaTutoria;
	}
	public void setFechaTutoria(String fechaTutoria) {
		this.fechaTutoria = fechaTutoria;
	}
	public int getNroSesion() {
		return nroSesion;
	}
	public void setNroSesion(int nroSesion) {
		this.nroSesion = nroSesion;
	}
	public String getDescSesion() {
		return descSesion;
	}
	public void setDescSesion(String descSesion) {
		this.descSesion = descSesion;
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
	public String getEstadoSesion() {
		return estadoSesion;
	}
	public void setEstadoSesion(String estadoSesion) {
		this.estadoSesion = estadoSesion;
	}
	public String getNombreActa() {
		return nombreActa==null?"":nombreActa;
	}
	public void setNombreActa(String nombreActa) {
		this.nombreActa = nombreActa;
	}
	public int getEstadoActa() {
		return estadoActa;
	}
	public void setEstadoActa(int estadoActa) {
		this.estadoActa = estadoActa;
	}
	public byte[] getActa() {
		return acta;
	}
	public void setActa(byte[] acta) {
		this.acta = acta;
	}	
}
