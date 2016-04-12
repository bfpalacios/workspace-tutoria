package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class DisponibilidadBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomAreaConocimiento;
	private String codAreaConocimiento;
	private String codCurso;
	private String nomCurso;
	private String codDocente;
	private String nomDocente;
	private String codAlumno;
	private String nomAlumno;
	private String dia;
	private String horaInicio;
	private String horaFin;	
	
	public String getNomAreaConocimiento() {
		return nomAreaConocimiento==null?"":nomAreaConocimiento;
	}
	public void setNomAreaConocimiento(String nomAreaConocimiento) {
		this.nomAreaConocimiento = nomAreaConocimiento;
	}
	public String getCodAreaConocimiento() {
		return codAreaConocimiento==null?"":codAreaConocimiento;
	}
	public void setCodAreaConocimiento(String codAreaConocimiento) {
		this.codAreaConocimiento = codAreaConocimiento;
	}
	public String getCodCurso() {
		return codCurso==null?"":codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getNomCurso() {
		return nomCurso==null?"":nomCurso;
	}
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	public String getCodDocente() {
		return codDocente==null?"":codDocente;
	}
	public void setCodDocente(String codDocente) {
		this.codDocente = codDocente;
	}
	public String getNomDocente() {
		return nomDocente==null?"":nomDocente;
	}
	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}
	public String getCodAlumno() {
		return codAlumno==null?"":codAlumno;
	}
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	public String getNomAlumno() {
		return nomAlumno==null?"":nomAlumno;
	}
	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}
	public String getDia() {
		return dia==null?"":dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHoraInicio() {
		return horaInicio==null?"":horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin==null?"":horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
}
