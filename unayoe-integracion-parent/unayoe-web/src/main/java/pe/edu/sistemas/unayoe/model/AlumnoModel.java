package pe.edu.sistemas.unayoe.model;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoModel.
 */
@Component("alumnoModel")
@RequestScoped
public class AlumnoModel{

	/** The codigo. */
	private String codigo;
	
	/** The nombres. */
	private String nombres;
	
	/** The ap paterno. */
	private String ap_paterno;
	
	/** The ap materno. */
	private String ap_materno;
	
	/** The anio ingreso. */
	private String anio_ingreso;
	
	/** The cod plan. */
	private String cod_plan;
	
	/** The cod especial. */
	private String cod_especial;
	
	/** The des curso. */
	private String des_curso;
	
	/** The cod curso. */
	private String cod_curso;
	
	/** The repitencias. */
	private String repitencias;
	
	/** The correo. */
	private String correo;
	
	/** The telefono. */
	private String telefono;
	
	/** The creditos. */
	private String creditos;
	
	/** The cod grupo. */
	private String cod_grupo;
	
	/** The cod sem. */
	private String cod_sem;
	
	/** The ciclo. */
	private String ciclo;
	
	/** The p codigo. */
	private String pCodigo;

	/** The listar cursos. */
	private List<CursoBO> listarCursos;
	
	/** The listar tutores. */
	private List<ProfesorBO> listarTutores;
	
	/** The listar alumnos. */
	private List<AlumnoBO> listarAlumnos;
	
	/**
	 * Gets the listar cursos.
	 *
	 * @return the listar cursos
	 */
	public List<CursoBO> getListarCursos() {
		return listarCursos;
	}
	
	/**
	 * Sets the listar cursos.
	 *
	 * @param listarCursos the new listar cursos
	 */
	public void setListarCursos(List<CursoBO> listarCursos) {
		this.listarCursos = listarCursos;
	}
	
	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the nombres.
	 *
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	
	/**
	 * Sets the nombres.
	 *
	 * @param nombres the new nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	/**
	 * Gets the ap paterno.
	 *
	 * @return the ap paterno
	 */
	public String getAp_paterno() {
		return ap_paterno;
	}
	
	/**
	 * Sets the ap paterno.
	 *
	 * @param ap_paterno the new ap paterno
	 */
	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}
	
	/**
	 * Gets the ap materno.
	 *
	 * @return the ap materno
	 */
	public String getAp_materno() {
		return ap_materno;
	}
	
	/**
	 * Sets the ap materno.
	 *
	 * @param ap_materno the new ap materno
	 */
	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}
	
	/**
	 * Gets the des curso.
	 *
	 * @return the des curso
	 */
	public String getDes_curso() {
		return des_curso;
	}
	
	/**
	 * Sets the des curso.
	 *
	 * @param curso the new des curso
	 */
	public void setDes_curso(String curso) {
		this.des_curso = curso;
	}
	
	/**
	 * Gets the cod curso.
	 *
	 * @return the cod curso
	 */
	public String getCod_curso() {
		return cod_curso;
	}
	
	/**
	 * Sets the cod curso.
	 *
	 * @param cod_curso the new cod curso
	 */
	public void setCod_curso(String cod_curso) {
		this.cod_curso = cod_curso;
	}
	
	/**
	 * Gets the repitencias.
	 *
	 * @return the repitencias
	 */
	public String getRepitencias() {
		return repitencias;
	}
	
	/**
	 * Sets the repitencias.
	 *
	 * @param repitencias the new repitencias
	 */
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	
	/**
	 * Gets the correo.
	 *
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * Sets the correo.
	 *
	 * @param correo the new correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Sets the telefono.
	 *
	 * @param telefono the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Gets the anio ingreso.
	 *
	 * @return the anio ingreso
	 */
	public String getAnio_ingreso() {
		return anio_ingreso;
	}
	
	/**
	 * Sets the anio ingreso.
	 *
	 * @param anio_ingreso the new anio ingreso
	 */
	public void setAnio_ingreso(String anio_ingreso) {
		this.anio_ingreso = anio_ingreso;
	}
	
	/**
	 * Gets the cod plan.
	 *
	 * @return the cod plan
	 */
	public String getCod_plan() {
		return cod_plan;
	}
	
	/**
	 * Sets the cod plan.
	 *
	 * @param cod_plan the new cod plan
	 */
	public void setCod_plan(String cod_plan) {
		this.cod_plan = cod_plan;
	}
	
	/**
	 * Gets the cod especial.
	 *
	 * @return the cod especial
	 */
	public String getCod_especial() {
		return cod_especial;
	}
	
	/**
	 * Sets the cod especial.
	 *
	 * @param cod_especial the new cod especial
	 */
	public void setCod_especial(String cod_especial) {
		this.cod_especial = cod_especial;
	}
	
	/**
	 * Gets the creditos.
	 *
	 * @return the creditos
	 */
	public String getCreditos() {
		return creditos;
	}
	
	/**
	 * Sets the creditos.
	 *
	 * @param creditos the new creditos
	 */
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	
	/**
	 * Gets the cod grupo.
	 *
	 * @return the cod grupo
	 */
	public String getCod_grupo() {
		return cod_grupo;
	}
	
	/**
	 * Sets the cod grupo.
	 *
	 * @param cod_grupo the new cod grupo
	 */
	public void setCod_grupo(String cod_grupo) {
		this.cod_grupo = cod_grupo;
	}
	
	/**
	 * Gets the cod sem.
	 *
	 * @return the cod sem
	 */
	public String getCod_sem() {
		return cod_sem;
	}
	
	/**
	 * Sets the cod sem.
	 *
	 * @param cod_sem the new cod sem
	 */
	public void setCod_sem(String cod_sem) {
		this.cod_sem = cod_sem;
	}
	
	/**
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public String getCiclo() {
		return ciclo;
	}
	
	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo the new ciclo
	 */
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	
	/**
	 * Gets the listar alumnos.
	 *
	 * @return the listar alumnos
	 */
	public List<AlumnoBO> getListarAlumnos() {
		return listarAlumnos;
	}
	
	/**
	 * Sets the listar alumnos.
	 *
	 * @param listarAlumnos the new listar alumnos
	 */
	public void setListarAlumnos(List<AlumnoBO> listarAlumnos) {
		this.listarAlumnos = listarAlumnos;
	}
	
	/**
	 * Gets the listar tutores.
	 *
	 * @return the listar tutores
	 */
	public List<ProfesorBO> getListarTutores() {
		return listarTutores;
	}
	
	/**
	 * Sets the listar tutores.
	 *
	 * @param listarTutores the new listar tutores
	 */
	public void setListarTutores(List<ProfesorBO> listarTutores) {
		this.listarTutores = listarTutores;
	}
	
	/**
	 * Gets the p codigo.
	 *
	 * @return the p codigo
	 */
	public String getpCodigo() {
		return pCodigo;
	}
	
	/**
	 * Sets the p codigo.
	 *
	 * @param pCodigo the new p codigo
	 */
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}	
}
