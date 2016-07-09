package pe.edu.sistemas.unayoe.model;
 
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

@Component("asistenciaTutoriaModel")
@RequestScoped
public class AsistenciaTutoriaModel {
	private String nombre;
	private String c_codigo;
	private String c_nombre;
	private String a_codigo;
	private String a_nombre;
	private String a_apellido;
	private String p_codigo;
	private String p_nombre;
	private String p_apellidos;
	private String tAnio;
	private String tPeriodo;
	private String a_fecha;
	private String tCodigo;
	private String asistencia;
	private List<CursoBO> listarCursos;
	private List<AlumnoBO> listarAlumnos;
	private List<ProfesorBO> listarProfesores;
	private List<ProfesorBO> listarProfesoresDAO;
	private List<ClaseMaestra> listarHoraInicio;
	private List<ClaseMaestra> listarHoraFin;
	private String horaIni;
	private String horaFin;
	private String dia;
	private String repitencia;
	private int idHoraInicio;
	private int idHoraFin;
	private String descFrecuencia;
	private int num_sesiones;
	private int num_asistencia_asistio;
	private int num_asistencia_falto;
	private int num_asistencia_tardanza;
	private int num_tareas_pendiente;
	private int num_tareas_parcialmente;
	private int num_tareas_cerrado;
	private int num_actas;
	
	public String gettAnio() {
		return tAnio;
	}

	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}

	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	public String gettCodigo() {
		return tCodigo;
	}

	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}

	public String gettPeriodo() {
		return tPeriodo;
	}

	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}

	public List<ProfesorBO> getListarProfesores() {
		return listarProfesores;
	}

	public void setListarProfesores(List<ProfesorBO> listarProfesores) {
		this.listarProfesores = listarProfesores;
	}
	
	public List<ClaseMaestra> getListarHoraInicio() {
		return listarHoraInicio;
	}

	public void setListarHoraInicio(List<ClaseMaestra> listarHoraInicio) {
		this.listarHoraInicio = listarHoraInicio;
	}
	
	public List<ClaseMaestra> getListarHoraFin() {
		return listarHoraFin;
	}

	public void setListarHoraFin(List<ClaseMaestra> listarHoraFin) {
		this.listarHoraFin = listarHoraFin;
	}

	public String getA_fecha() {
		return a_fecha;
	}

	public void setA_fecha(String a_fecha) {
		this.a_fecha = a_fecha;
	}

	public List<CursoBO> getListarCursos() {
		return listarCursos;
	}

	public void setListarCursos(List<CursoBO> listarCursos) {
		this.listarCursos = listarCursos;
	}

	public List<AlumnoBO> getListarAlumnos() {
		return listarAlumnos;
	}

	public void setListarAlumnos(List<AlumnoBO> listarAlumnos) {
		this.listarAlumnos = listarAlumnos;
	}

	public AsistenciaTutoriaModel() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getC_codigo() {
		return c_codigo;
	}

	public void setC_codigo(String c_codigo) {
		this.c_codigo = c_codigo;
	}

	public String getA_codigo() {
		return a_codigo;
	}

	public void setA_codigo(String a_codigo) {
		this.a_codigo = a_codigo;
	}

	public String getA_nombre() {
		return a_nombre;
	}

	public void setA_nombre(String a_nombre) {
		this.a_nombre = a_nombre;
	}

	public String getA_apellido() {
		return a_apellido;
	}

	public void setA_apellido(String a_apellido) {
		this.a_apellido = a_apellido;
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

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getP_codigo() {
		return p_codigo;
	}

	public void setP_codigo(String p_codigo) {
		this.p_codigo = p_codigo;
	}

	public String getC_nombre() {
		return c_nombre;
	}

	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}

	public String getRepitencia() {
		return repitencia;
	}

	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}

	public String getP_nombre() {
		return p_nombre;
	}

	public void setP_nombre(String p_nombre) {
		this.p_nombre = p_nombre;
	}

	public String getP_apellidos() {
		return p_apellidos;
	}

	public void setP_apellidos(String p_apellidos) {
		this.p_apellidos = p_apellidos;
	}

	public List<ProfesorBO> getListarProfesoresDAO() {
		return listarProfesoresDAO;
	}

	public void setListarProfesoresDAO(List<ProfesorBO> listarProfesoresDAO) {
		this.listarProfesoresDAO = listarProfesoresDAO;
	}

	public int getIdHoraInicio() {
		return idHoraInicio;
	}

	public void setIdHoraInicio(int idHoraInicio) {
		this.idHoraInicio = idHoraInicio;
	}

	public int getIdHoraFin() {
		return idHoraFin;
	}

	public void setIdHoraFin(int idHoraFin) {
		this.idHoraFin = idHoraFin;
	}

	public String getDescFrecuencia() {
		return descFrecuencia;
	}

	public void setDescFrecuencia(String descFrecuencia) {
		this.descFrecuencia = descFrecuencia;
	}

	public int getNum_sesiones() {
		return num_sesiones;
	}

	public void setNum_sesiones(int num_sesiones) {
		this.num_sesiones = num_sesiones;
	}

	public int getNum_asistencia_asistio() {
		return num_asistencia_asistio;
	}

	public void setNum_asistencia_asistio(int num_asistencia_asistio) {
		this.num_asistencia_asistio = num_asistencia_asistio;
	}

	public int getNum_asistencia_falto() {
		return num_asistencia_falto;
	}

	public void setNum_asistencia_falto(int num_asistencia_falto) {
		this.num_asistencia_falto = num_asistencia_falto;
	}

	public int getNum_asistencia_tardanza() {
		return num_asistencia_tardanza;
	}

	public void setNum_asistencia_tardanza(int num_asistencia_tardanza) {
		this.num_asistencia_tardanza = num_asistencia_tardanza;
	}

	public int getNum_tareas_pendiente() {
		return num_tareas_pendiente;
	}

	public void setNum_tareas_pendiente(int num_tareas_pendiente) {
		this.num_tareas_pendiente = num_tareas_pendiente;
	}

	public int getNum_tareas_parcialmente() {
		return num_tareas_parcialmente;
	}

	public void setNum_tareas_parcialmente(int num_tareas_parcialmente) {
		this.num_tareas_parcialmente = num_tareas_parcialmente;
	}

	public int getNum_tareas_cerrado() {
		return num_tareas_cerrado;
	}

	public void setNum_tareas_cerrado(int num_tareas_cerrado) {
		this.num_tareas_cerrado = num_tareas_cerrado;
	}

	public int getNum_actas() {
		return num_actas;
	}

	public void setNum_actas(int num_actas) {
		this.num_actas = num_actas;
	}
	
}
