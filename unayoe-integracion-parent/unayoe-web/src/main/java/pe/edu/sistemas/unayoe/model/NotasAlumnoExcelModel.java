package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class NotasAlumnoExcelModel.
 */
@Component("notasAlumnoExcelModel")
@RequestScoped
public class NotasAlumnoExcelModel {
	
	/** The anio. */
	private int anio;
	
	/** The periodo. */
	private int periodo;
	
	/** The plan. */
	private int plan;
	
	/** The cod curso. */
	private String codCurso;
	
	/** The nom curso. */
	private String nomCurso;
	
	/** The cod alumno. */
	private String codAlumno;
	
	/** The nota final. */
	private int notaFinal;
	
	/** The creditos. */
	private int creditos;
	
	/** The nom alumno. */
	private String nomAlumno;
	
	/** The nom docente. */
	private String nomDocente;	
	
	/** The existe. */
	private String existe;
	
	/** The valido. */
	private String valido;
	
	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}
	
	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the plan.
	 *
	 * @return the plan
	 */
	public int getPlan() {
		return plan;
	}
	
	/**
	 * Sets the plan.
	 *
	 * @param plan the new plan
	 */
	public void setPlan(int plan) {
		this.plan = plan;
	}
	
	/**
	 * Gets the cod curso.
	 *
	 * @return the cod curso
	 */
	public String getCodCurso() {
		return codCurso;
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
	 * Gets the cod alumno.
	 *
	 * @return the cod alumno
	 */
	public String getCodAlumno() {
		return codAlumno;
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
	 * Gets the nota final.
	 *
	 * @return the nota final
	 */
	public int getNotaFinal() {
		return notaFinal;
	}
	
	/**
	 * Sets the nota final.
	 *
	 * @param notaFinal the new nota final
	 */
	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	/**
	 * Gets the creditos.
	 *
	 * @return the creditos
	 */
	public int getCreditos() {
		return creditos;
	}
	
	/**
	 * Sets the creditos.
	 *
	 * @param creditos the new creditos
	 */
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	/**
	 * Gets the nom alumno.
	 *
	 * @return the nom alumno
	 */
	public String getNomAlumno() {
		return nomAlumno;
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
	 * Gets the nom docente.
	 *
	 * @return the nom docente
	 */
	public String getNomDocente() {
		return nomDocente;
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
	 * Gets the existe.
	 *
	 * @return the existe
	 */
	public String getExiste() {
		return existe;
	}
	
	/**
	 * Sets the existe.
	 *
	 * @param existe the new existe
	 */
	public void setExiste(String existe) {
		this.existe = existe;
	}
	
	/**
	 * Gets the valido.
	 *
	 * @return the valido
	 */
	public String getValido() {
		return valido;
	}
	
	/**
	 * Sets the valido.
	 *
	 * @param valido the new valido
	 */
	public void setValido(String valido) {
		this.valido = valido;
	}
	
	/**
	 * Gets the nom curso.
	 *
	 * @return the nom curso
	 */
	public String getNomCurso() {
		return nomCurso;
	}
	
	/**
	 * Sets the nom curso.
	 *
	 * @param nomCurso the new nom curso
	 */
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}	
}
