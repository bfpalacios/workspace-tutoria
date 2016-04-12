package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class NotasAlumnoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int anio;
	private int periodo;
	private int plan;
	private String codCurso;
	private String nomCurso;
	private String codAlumno;
	private String nomAlumno;
	private String nomDocente;
	private int creditos;
	private int notaFinal;
	
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
	
	public int getPlan() {
		return plan;
	}
	
	public void setPlan(int plan) {
		this.plan = plan;
	}
	
	public String getCodCurso() {
		return codCurso;
	}
	
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	
	public String getNomCurso() {
		return nomCurso;
	}
	
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	
	public String getCodAlumno() {
		return codAlumno;
	}
	
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	
	public String getNomAlumno() {
		return nomAlumno;
	}
	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}
	
	public String getNomDocente() {
		return nomDocente;
	}
	
	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}
	
	public int getCreditos() {
		return creditos;
	}
	
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	public int getNotaFinal() {
		return notaFinal;
	}
	
	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}
}
