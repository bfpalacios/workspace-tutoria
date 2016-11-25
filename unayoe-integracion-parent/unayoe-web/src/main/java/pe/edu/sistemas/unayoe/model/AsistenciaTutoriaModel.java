package pe.edu.sistemas.unayoe.model;
 
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaTutoriaModel.
 */
@Component("asistenciaTutoriaModel")
@RequestScoped
public class AsistenciaTutoriaModel {
	
	/** The nombre. */
	private String nombre;
	
	/** The c codigo. */
	private String c_codigo;
	
	/** The c nombre. */
	private String c_nombre;
	
	/** The a codigo. */
	private String a_codigo;
	
	/** The a nombre. */
	private String a_nombre;
	
	/** The a apellido. */
	private String a_apellido;
	
	/** The p codigo. */
	private String p_codigo;
	
	/** The p nombre. */
	private String p_nombre;
	
	/** The p apellidos. */
	private String p_apellidos;
	
	/** The t anio. */
	private String tAnio;
	
	/** The t periodo. */
	private String tPeriodo;
	
	/** The a fecha. */
	private String a_fecha;
	
	/** The t codigo. */
	private String tCodigo;
	
	/** The asistencia. */
	private String asistencia;
	
	/** The listar cursos. */
	private List<CursoBO> listarCursos;
	
	/** The listar alumnos. */
	private List<AlumnoBO> listarAlumnos;
	
	/** The listar profesores. */
	private List<ProfesorBO> listarProfesores;
	
	/** The listar profesores DAO. */
	private List<ProfesorBO> listarProfesoresDAO;
	
	/** The listar hora inicio. */
	private List<ClaseMaestra> listarHoraInicio;
	
	/** The listar hora fin. */
	private List<ClaseMaestra> listarHoraFin;
	
	/** The hora ini. */
	private String horaIni;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The dia. */
	private String dia;
	
	/** The repitencia. */
	private String repitencia;
	
	/** The id hora inicio. */
	private int idHoraInicio;
	
	/** The id hora fin. */
	private int idHoraFin;
	
	/** The desc frecuencia. */
	private String descFrecuencia;
	
	/** The num sesiones. */
	private int num_sesiones;
	
	/** The num asistencia asistio. */
	private int num_asistencia_asistio;
	
	/** The num asistencia falto. */
	private int num_asistencia_falto;
	
	/** The num asistencia tardanza. */
	private int num_asistencia_tardanza;
	
	/** The num tareas pendiente. */
	private int num_tareas_pendiente;
	
	/** The num tareas parcialmente. */
	private int num_tareas_parcialmente;
	
	/** The num tareas cerrado. */
	private int num_tareas_cerrado;
	
	/** The num actas. */
	private int num_actas;
	
	/**
	 * Gets the t anio.
	 *
	 * @return the t anio
	 */
	public String gettAnio() {
		return tAnio;
	}

	/**
	 * Sets the t anio.
	 *
	 * @param tAnio the new t anio
	 */
	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}

	/**
	 * Gets the asistencia.
	 *
	 * @return the asistencia
	 */
	public String getAsistencia() {
		return asistencia;
	}

	/**
	 * Sets the asistencia.
	 *
	 * @param asistencia the new asistencia
	 */
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}

	/**
	 * Gets the t codigo.
	 *
	 * @return the t codigo
	 */
	public String gettCodigo() {
		return tCodigo;
	}

	/**
	 * Sets the t codigo.
	 *
	 * @param tCodigo the new t codigo
	 */
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}

	/**
	 * Gets the t periodo.
	 *
	 * @return the t periodo
	 */
	public String gettPeriodo() {
		return tPeriodo;
	}

	/**
	 * Sets the t periodo.
	 *
	 * @param tPeriodo the new t periodo
	 */
	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}

	/**
	 * Gets the listar profesores.
	 *
	 * @return the listar profesores
	 */
	public List<ProfesorBO> getListarProfesores() {
		return listarProfesores;
	}

	/**
	 * Sets the listar profesores.
	 *
	 * @param listarProfesores the new listar profesores
	 */
	public void setListarProfesores(List<ProfesorBO> listarProfesores) {
		this.listarProfesores = listarProfesores;
	}
	
	/**
	 * Gets the listar hora inicio.
	 *
	 * @return the listar hora inicio
	 */
	public List<ClaseMaestra> getListarHoraInicio() {
		return listarHoraInicio;
	}

	/**
	 * Sets the listar hora inicio.
	 *
	 * @param listarHoraInicio the new listar hora inicio
	 */
	public void setListarHoraInicio(List<ClaseMaestra> listarHoraInicio) {
		this.listarHoraInicio = listarHoraInicio;
	}
	
	/**
	 * Gets the listar hora fin.
	 *
	 * @return the listar hora fin
	 */
	public List<ClaseMaestra> getListarHoraFin() {
		return listarHoraFin;
	}

	/**
	 * Sets the listar hora fin.
	 *
	 * @param listarHoraFin the new listar hora fin
	 */
	public void setListarHoraFin(List<ClaseMaestra> listarHoraFin) {
		this.listarHoraFin = listarHoraFin;
	}

	/**
	 * Gets the a fecha.
	 *
	 * @return the a fecha
	 */
	public String getA_fecha() {
		return a_fecha;
	}

	/**
	 * Sets the a fecha.
	 *
	 * @param a_fecha the new a fecha
	 */
	public void setA_fecha(String a_fecha) {
		this.a_fecha = a_fecha;
	}

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
	 * Instantiates a new asistencia tutoria model.
	 */
	public AsistenciaTutoriaModel() {

	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getC_codigo() {
		return c_codigo;
	}

	/**
	 * Sets the c codigo.
	 *
	 * @param c_codigo the new c codigo
	 */
	public void setC_codigo(String c_codigo) {
		this.c_codigo = c_codigo;
	}

	/**
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getA_codigo() {
		return a_codigo;
	}

	/**
	 * Sets the a codigo.
	 *
	 * @param a_codigo the new a codigo
	 */
	public void setA_codigo(String a_codigo) {
		this.a_codigo = a_codigo;
	}

	/**
	 * Gets the a nombre.
	 *
	 * @return the a nombre
	 */
	public String getA_nombre() {
		return a_nombre;
	}

	/**
	 * Sets the a nombre.
	 *
	 * @param a_nombre the new a nombre
	 */
	public void setA_nombre(String a_nombre) {
		this.a_nombre = a_nombre;
	}

	/**
	 * Gets the a apellido.
	 *
	 * @return the a apellido
	 */
	public String getA_apellido() {
		return a_apellido;
	}

	/**
	 * Sets the a apellido.
	 *
	 * @param a_apellido the new a apellido
	 */
	public void setA_apellido(String a_apellido) {
		this.a_apellido = a_apellido;
	}

	/**
	 * Gets the hora ini.
	 *
	 * @return the hora ini
	 */
	public String getHoraIni() {
		return horaIni;
	}

	/**
	 * Sets the hora ini.
	 *
	 * @param horaIni the new hora ini
	 */
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}

	/**
	 * Gets the hora fin.
	 *
	 * @return the hora fin
	 */
	public String getHoraFin() {
		return horaFin;
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
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
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
	 * Gets the p codigo.
	 *
	 * @return the p codigo
	 */
	public String getP_codigo() {
		return p_codigo;
	}

	/**
	 * Sets the p codigo.
	 *
	 * @param p_codigo the new p codigo
	 */
	public void setP_codigo(String p_codigo) {
		this.p_codigo = p_codigo;
	}

	/**
	 * Gets the c nombre.
	 *
	 * @return the c nombre
	 */
	public String getC_nombre() {
		return c_nombre;
	}

	/**
	 * Sets the c nombre.
	 *
	 * @param c_nombre the new c nombre
	 */
	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}

	/**
	 * Gets the repitencia.
	 *
	 * @return the repitencia
	 */
	public String getRepitencia() {
		return repitencia;
	}

	/**
	 * Sets the repitencia.
	 *
	 * @param repitencia the new repitencia
	 */
	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}

	/**
	 * Gets the p nombre.
	 *
	 * @return the p nombre
	 */
	public String getP_nombre() {
		return p_nombre;
	}

	/**
	 * Sets the p nombre.
	 *
	 * @param p_nombre the new p nombre
	 */
	public void setP_nombre(String p_nombre) {
		this.p_nombre = p_nombre;
	}

	/**
	 * Gets the p apellidos.
	 *
	 * @return the p apellidos
	 */
	public String getP_apellidos() {
		return p_apellidos;
	}

	/**
	 * Sets the p apellidos.
	 *
	 * @param p_apellidos the new p apellidos
	 */
	public void setP_apellidos(String p_apellidos) {
		this.p_apellidos = p_apellidos;
	}

	/**
	 * Gets the listar profesores DAO.
	 *
	 * @return the listar profesores DAO
	 */
	public List<ProfesorBO> getListarProfesoresDAO() {
		return listarProfesoresDAO;
	}

	/**
	 * Sets the listar profesores DAO.
	 *
	 * @param listarProfesoresDAO the new listar profesores DAO
	 */
	public void setListarProfesoresDAO(List<ProfesorBO> listarProfesoresDAO) {
		this.listarProfesoresDAO = listarProfesoresDAO;
	}

	/**
	 * Gets the id hora inicio.
	 *
	 * @return the id hora inicio
	 */
	public int getIdHoraInicio() {
		return idHoraInicio;
	}

	/**
	 * Sets the id hora inicio.
	 *
	 * @param idHoraInicio the new id hora inicio
	 */
	public void setIdHoraInicio(int idHoraInicio) {
		this.idHoraInicio = idHoraInicio;
	}

	/**
	 * Gets the id hora fin.
	 *
	 * @return the id hora fin
	 */
	public int getIdHoraFin() {
		return idHoraFin;
	}

	/**
	 * Sets the id hora fin.
	 *
	 * @param idHoraFin the new id hora fin
	 */
	public void setIdHoraFin(int idHoraFin) {
		this.idHoraFin = idHoraFin;
	}

	/**
	 * Gets the desc frecuencia.
	 *
	 * @return the desc frecuencia
	 */
	public String getDescFrecuencia() {
		return descFrecuencia;
	}

	/**
	 * Sets the desc frecuencia.
	 *
	 * @param descFrecuencia the new desc frecuencia
	 */
	public void setDescFrecuencia(String descFrecuencia) {
		this.descFrecuencia = descFrecuencia;
	}

	/**
	 * Gets the num sesiones.
	 *
	 * @return the num sesiones
	 */
	public int getNum_sesiones() {
		return num_sesiones;
	}

	/**
	 * Sets the num sesiones.
	 *
	 * @param num_sesiones the new num sesiones
	 */
	public void setNum_sesiones(int num_sesiones) {
		this.num_sesiones = num_sesiones;
	}

	/**
	 * Gets the num asistencia asistio.
	 *
	 * @return the num asistencia asistio
	 */
	public int getNum_asistencia_asistio() {
		return num_asistencia_asistio;
	}

	/**
	 * Sets the num asistencia asistio.
	 *
	 * @param num_asistencia_asistio the new num asistencia asistio
	 */
	public void setNum_asistencia_asistio(int num_asistencia_asistio) {
		this.num_asistencia_asistio = num_asistencia_asistio;
	}

	/**
	 * Gets the num asistencia falto.
	 *
	 * @return the num asistencia falto
	 */
	public int getNum_asistencia_falto() {
		return num_asistencia_falto;
	}

	/**
	 * Sets the num asistencia falto.
	 *
	 * @param num_asistencia_falto the new num asistencia falto
	 */
	public void setNum_asistencia_falto(int num_asistencia_falto) {
		this.num_asistencia_falto = num_asistencia_falto;
	}

	/**
	 * Gets the num asistencia tardanza.
	 *
	 * @return the num asistencia tardanza
	 */
	public int getNum_asistencia_tardanza() {
		return num_asistencia_tardanza;
	}

	/**
	 * Sets the num asistencia tardanza.
	 *
	 * @param num_asistencia_tardanza the new num asistencia tardanza
	 */
	public void setNum_asistencia_tardanza(int num_asistencia_tardanza) {
		this.num_asistencia_tardanza = num_asistencia_tardanza;
	}

	/**
	 * Gets the num tareas pendiente.
	 *
	 * @return the num tareas pendiente
	 */
	public int getNum_tareas_pendiente() {
		return num_tareas_pendiente;
	}

	/**
	 * Sets the num tareas pendiente.
	 *
	 * @param num_tareas_pendiente the new num tareas pendiente
	 */
	public void setNum_tareas_pendiente(int num_tareas_pendiente) {
		this.num_tareas_pendiente = num_tareas_pendiente;
	}

	/**
	 * Gets the num tareas parcialmente.
	 *
	 * @return the num tareas parcialmente
	 */
	public int getNum_tareas_parcialmente() {
		return num_tareas_parcialmente;
	}

	/**
	 * Sets the num tareas parcialmente.
	 *
	 * @param num_tareas_parcialmente the new num tareas parcialmente
	 */
	public void setNum_tareas_parcialmente(int num_tareas_parcialmente) {
		this.num_tareas_parcialmente = num_tareas_parcialmente;
	}

	/**
	 * Gets the num tareas cerrado.
	 *
	 * @return the num tareas cerrado
	 */
	public int getNum_tareas_cerrado() {
		return num_tareas_cerrado;
	}

	/**
	 * Sets the num tareas cerrado.
	 *
	 * @param num_tareas_cerrado the new num tareas cerrado
	 */
	public void setNum_tareas_cerrado(int num_tareas_cerrado) {
		this.num_tareas_cerrado = num_tareas_cerrado;
	}

	/**
	 * Gets the num actas.
	 *
	 * @return the num actas
	 */
	public int getNum_actas() {
		return num_actas;
	}

	/**
	 * Sets the num actas.
	 *
	 * @param num_actas the new num actas
	 */
	public void setNum_actas(int num_actas) {
		this.num_actas = num_actas;
	}
	
}
