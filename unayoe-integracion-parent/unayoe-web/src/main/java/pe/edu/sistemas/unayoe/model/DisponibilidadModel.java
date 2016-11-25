package pe.edu.sistemas.unayoe.model;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;

// TODO: Auto-generated Javadoc
/**
 * The Class DisponibilidadModel.
 */
@Component("disponibilidadModel")
@RequestScoped
public class DisponibilidadModel {
	
	/** The nom area conocimiento. */
	private String nomAreaConocimiento;
	
	/** The cod area conocimiento. */
	private String codAreaConocimiento;
	
	/** The cod curso. */
	private String codCurso;
	
	/** The nom curso. */
	private String nomCurso;
	
	/** The cod docente. */
	private String codDocente;
	
	/** The nom docente. */
	private String nomDocente;
	
	/** The cod alumno. */
	private String codAlumno;
	
	/** The nom alumno. */
	private String nomAlumno;
	
	/** The dia. */
	private String dia;
	
	/** The hora inicio. */
	private String horaInicio;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The lista area conocimiento. */
	private List<AreaConocimientoBO> listaAreaConocimiento;
	
	/** The lista cursos. */
	private List<CursoBO> listaCursos;
	
	/** The lista tutores regulares. */
	private List<ProfesorBO> listaTutoresRegulares;
	
	/** The lista hora inicio. */
	private List<ClaseMaestra> listaHoraInicio;
	
	/** The lista hora fin. */
	private List<ClaseMaestra> listaHoraFin;	
	
	/** The lista alumnos regulares. */
	private List<AlumnoBO> listaAlumnosRegulares;
	
	/**
	 * Gets the nom area conocimiento.
	 *
	 * @return the nom area conocimiento
	 */
	public String getNomAreaConocimiento() {
		return nomAreaConocimiento;
	}
	
	/**
	 * Sets the nom area conocimiento.
	 *
	 * @param nomAreaConocimiento the new nom area conocimiento
	 */
	public void setNomAreaConocimiento(String nomAreaConocimiento) {
		this.nomAreaConocimiento = nomAreaConocimiento;
	}
	
	/**
	 * Gets the cod area conocimiento.
	 *
	 * @return the cod area conocimiento
	 */
	public String getCodAreaConocimiento() {
		return codAreaConocimiento;
	}
	
	/**
	 * Sets the cod area conocimiento.
	 *
	 * @param codAreaConocimiento the new cod area conocimiento
	 */
	public void setCodAreaConocimiento(String codAreaConocimiento) {
		this.codAreaConocimiento = codAreaConocimiento;
	}
	
	/**
	 * Gets the cod curso.
	 *
	 * @return the cod curso
	 */
	public String getCodCurso() {
		return codCurso==null?"":codCurso;
	}
	
	/**
	 * Sets the cod curso.
	 *
	 * @param codCurso the new cod curso
	 */
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	
	/**
	 * Gets the nom curso.
	 *
	 * @return the nom curso
	 */
	public String getNomCurso() {
		return nomCurso==null?"":nomCurso;
	}
	
	/**
	 * Sets the nom curso.
	 *
	 * @param nomCurso the new nom curso
	 */
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	
	/**
	 * Gets the cod docente.
	 *
	 * @return the cod docente
	 */
	public String getCodDocente() {
		return codDocente==null?"":codDocente;
	}
	
	/**
	 * Sets the cod docente.
	 *
	 * @param codDocente the new cod docente
	 */
	public void setCodDocente(String codDocente) {
		this.codDocente = codDocente;
	}
	
	/**
	 * Gets the nom docente.
	 *
	 * @return the nom docente
	 */
	public String getNomDocente() {
		return nomDocente==null?"":nomDocente;
	}
	
	/**
	 * Sets the nom docente.
	 *
	 * @param nomDocente the new nom docente
	 */
	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}
	
	/**
	 * Gets the cod alumno.
	 *
	 * @return the cod alumno
	 */
	public String getCodAlumno() {
		return codAlumno==null?"":codAlumno;
	}
	
	/**
	 * Sets the cod alumno.
	 *
	 * @param codAlumno the new cod alumno
	 */
	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}
	
	/**
	 * Gets the nom alumno.
	 *
	 * @return the nom alumno
	 */
	public String getNomAlumno() {
		return nomAlumno==null?"":nomAlumno;
	}
	
	/**
	 * Sets the nom alumno.
	 *
	 * @param nomAlumno the new nom alumno
	 */
	public void setNomAlumno(String nomAlumno) {
		this.nomAlumno = nomAlumno;
	}
	
	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia==null?"":dia;
	}
	
	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public String getHoraInicio() {
		return horaInicio==null?"":horaInicio;
	}
	
	/**
	 * Sets the hora inicio.
	 *
	 * @param horaInicio the new hora inicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	/**
	 * Gets the hora fin.
	 *
	 * @return the hora fin
	 */
	public String getHoraFin() {
		return horaFin==null?"":horaFin;
	}
	
	/**
	 * Sets the hora fin.
	 *
	 * @param horaFin the new hora fin
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	/**
	 * Gets the lista area conocimiento.
	 *
	 * @return the lista area conocimiento
	 */
	public List<AreaConocimientoBO> getListaAreaConocimiento() {
		return listaAreaConocimiento;
	}
	
	/**
	 * Sets the lista area conocimiento.
	 *
	 * @param listaAreaConocimiento the new lista area conocimiento
	 */
	public void setListaAreaConocimiento(List<AreaConocimientoBO> listaAreaConocimiento) {
		this.listaAreaConocimiento = listaAreaConocimiento;
	}
	
	/**
	 * Gets the lista cursos.
	 *
	 * @return the lista cursos
	 */
	public List<CursoBO> getListaCursos() {
		return listaCursos;
	}
	
	/**
	 * Sets the lista cursos.
	 *
	 * @param listaCursos the new lista cursos
	 */
	public void setListaCursos(List<CursoBO> listaCursos) {
		this.listaCursos = listaCursos;
	}
	
	/**
	 * Gets the lista hora inicio.
	 *
	 * @return the lista hora inicio
	 */
	public List<ClaseMaestra> getListaHoraInicio() {
		return listaHoraInicio;
	}
	
	/**
	 * Sets the lista hora inicio.
	 *
	 * @param listaHoraInicio the new lista hora inicio
	 */
	public void setListaHoraInicio(List<ClaseMaestra> listaHoraInicio) {
		this.listaHoraInicio = listaHoraInicio;
	}
	
	/**
	 * Gets the lista hora fin.
	 *
	 * @return the lista hora fin
	 */
	public List<ClaseMaestra> getListaHoraFin() {
		return listaHoraFin;
	}
	
	/**
	 * Sets the lista hora fin.
	 *
	 * @param listaHoraFin the new lista hora fin
	 */
	public void setListaHoraFin(List<ClaseMaestra> listaHoraFin) {
		this.listaHoraFin = listaHoraFin;
	}
	
	/**
	 * Gets the lista tutores regulares.
	 *
	 * @return the lista tutores regulares
	 */
	public List<ProfesorBO> getListaTutoresRegulares() {
		return listaTutoresRegulares;
	}
	
	/**
	 * Sets the lista tutores regulares.
	 *
	 * @param listaTutoresRegulares the new lista tutores regulares
	 */
	public void setListaTutoresRegulares(List<ProfesorBO> listaTutoresRegulares) {
		this.listaTutoresRegulares = listaTutoresRegulares;
	}
	
	/**
	 * Gets the lista alumnos regulares.
	 *
	 * @return the lista alumnos regulares
	 */
	public List<AlumnoBO> getListaAlumnosRegulares() {
		return listaAlumnosRegulares;
	}
	
	/**
	 * Sets the lista alumnos regulares.
	 *
	 * @param listaAlumnosRegulares the new lista alumnos regulares
	 */
	public void setListaAlumnosRegulares(List<AlumnoBO> listaAlumnosRegulares) {
		this.listaAlumnosRegulares = listaAlumnosRegulares;
	}	
}
