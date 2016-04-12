package pe.edu.sistemas.unayoe.model;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;

@Component("alumnoModel")
@RequestScoped
public class AlumnoModel{

	private String codigo;
	private String nombres;
	private String ap_paterno;
	private String ap_materno;
	private String anio_ingreso;
	private String cod_plan;
	private String cod_especial;
	private String des_curso;
	private String cod_curso;
	private String repitencias;
	private String correo;
	private String telefono;
	private String creditos;
	private String cod_grupo;
	private String cod_sem;
	private String ciclo;
	private String pCodigo;

	private List<CursoBO> listarCursos;
	private List<ProfesorBO> listarTutores;
	private List<AlumnoBO> listarAlumnos;
	
	public List<CursoBO> getListarCursos() {
		return listarCursos;
	}
	public void setListarCursos(List<CursoBO> listarCursos) {
		this.listarCursos = listarCursos;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getAp_paterno() {
		return ap_paterno;
	}
	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}
	public String getAp_materno() {
		return ap_materno;
	}
	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}
	public String getDes_curso() {
		return des_curso;
	}
	public void setDes_curso(String curso) {
		this.des_curso = curso;
	}
	public String getCod_curso() {
		return cod_curso;
	}
	public void setCod_curso(String cod_curso) {
		this.cod_curso = cod_curso;
	}
	public String getRepitencias() {
		return repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getAnio_ingreso() {
		return anio_ingreso;
	}
	public void setAnio_ingreso(String anio_ingreso) {
		this.anio_ingreso = anio_ingreso;
	}
	public String getCod_plan() {
		return cod_plan;
	}
	public void setCod_plan(String cod_plan) {
		this.cod_plan = cod_plan;
	}
	public String getCod_especial() {
		return cod_especial;
	}
	public void setCod_especial(String cod_especial) {
		this.cod_especial = cod_especial;
	}
	public String getCreditos() {
		return creditos;
	}
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	public String getCod_grupo() {
		return cod_grupo;
	}
	public void setCod_grupo(String cod_grupo) {
		this.cod_grupo = cod_grupo;
	}
	public String getCod_sem() {
		return cod_sem;
	}
	public void setCod_sem(String cod_sem) {
		this.cod_sem = cod_sem;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public List<AlumnoBO> getListarAlumnos() {
		return listarAlumnos;
	}
	public void setListarAlumnos(List<AlumnoBO> listarAlumnos) {
		this.listarAlumnos = listarAlumnos;
	}
	public List<ProfesorBO> getListarTutores() {
		return listarTutores;
	}
	public void setListarTutores(List<ProfesorBO> listarTutores) {
		this.listarTutores = listarTutores;
	}
	public String getpCodigo() {
		return pCodigo;
	}
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}	
}
