package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

@Component("datosAlumnoExcelModel")
@RequestScoped
public class DatosAlumnoExcelModel {

	private String cod_alumno;
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
	private String cod_seccion;
	private String cod_docente;
	private String nom_docente;
	private String cod_frecuencia;
	private String nom_frecuencia;
	private String dia;
	private String hora_inicio;
	private String hora_fin;
	private String existe;
	private String valido;

	public String getCod_alumno() {
		return cod_alumno;
	}
	public void setCod_alumno(String cod_alumno) {
		this.cod_alumno = cod_alumno;
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
	public String getCod_seccion() {
		return cod_seccion;
	}
	public void setCod_seccion(String cod_seccion) {
		this.cod_seccion = cod_seccion;
	}
	public String getCod_docente() {
		return cod_docente;
	}
	public void setCod_docente(String cod_docente) {
		this.cod_docente = cod_docente;
	}
	public String getNom_docente() {
		return nom_docente;
	}
	public void setNom_docente(String nom_docente) {
		this.nom_docente = nom_docente;
	}
	public String getCod_frecuencia() {
		return cod_frecuencia;
	}
	public void setCod_frecuencia(String cod_frecuencia) {
		this.cod_frecuencia = cod_frecuencia;
	}
	public String getNom_frecuencia() {
		return nom_frecuencia;
	}
	public void setNom_frecuencia(String nom_frecuencia) {
		this.nom_frecuencia = nom_frecuencia;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}
	public String getExiste() {
		return existe;
	}
	public void setExiste(String existe) {
		this.existe = existe;
	}
	public String getValido() {
		return valido;
	}
	public void setValido(String valido) {
		this.valido = valido;
	}
}
