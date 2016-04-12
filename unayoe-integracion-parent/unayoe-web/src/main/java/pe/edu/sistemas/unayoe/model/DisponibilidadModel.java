package pe.edu.sistemas.unayoe.model;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;

@Component("disponibilidadModel")
@RequestScoped
public class DisponibilidadModel {
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
	
	private List<AreaConocimientoBO> listaAreaConocimiento;
	private List<CursoBO> listaCursos;
	private List<ProfesorBO> listaTutoresRegulares;
	private List<ClaseMaestra> listaHoraInicio;
	private List<ClaseMaestra> listaHoraFin;	
	private List<AlumnoBO> listaAlumnosRegulares;
	
	public String getNomAreaConocimiento() {
		return nomAreaConocimiento;
	}
	public void setNomAreaConocimiento(String nomAreaConocimiento) {
		this.nomAreaConocimiento = nomAreaConocimiento;
	}
	public String getCodAreaConocimiento() {
		return codAreaConocimiento;
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
	public List<AreaConocimientoBO> getListaAreaConocimiento() {
		return listaAreaConocimiento;
	}
	public void setListaAreaConocimiento(List<AreaConocimientoBO> listaAreaConocimiento) {
		this.listaAreaConocimiento = listaAreaConocimiento;
	}
	public List<CursoBO> getListaCursos() {
		return listaCursos;
	}
	public void setListaCursos(List<CursoBO> listaCursos) {
		this.listaCursos = listaCursos;
	}
	public List<ClaseMaestra> getListaHoraInicio() {
		return listaHoraInicio;
	}
	public void setListaHoraInicio(List<ClaseMaestra> listaHoraInicio) {
		this.listaHoraInicio = listaHoraInicio;
	}
	public List<ClaseMaestra> getListaHoraFin() {
		return listaHoraFin;
	}
	public void setListaHoraFin(List<ClaseMaestra> listaHoraFin) {
		this.listaHoraFin = listaHoraFin;
	}
	public List<ProfesorBO> getListaTutoresRegulares() {
		return listaTutoresRegulares;
	}
	public void setListaTutoresRegulares(List<ProfesorBO> listaTutoresRegulares) {
		this.listaTutoresRegulares = listaTutoresRegulares;
	}
	public List<AlumnoBO> getListaAlumnosRegulares() {
		return listaAlumnosRegulares;
	}
	public void setListaAlumnosRegulares(List<AlumnoBO> listaAlumnosRegulares) {
		this.listaAlumnosRegulares = listaAlumnosRegulares;
	}	
}
