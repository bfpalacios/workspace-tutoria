package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.ArrayList;

import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaBO.
 */
public class TutoriaBO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The t anio. */
	private String tAnio;
	
	/** The t periodo. */
	private String tPeriodo;
	
	/** The t codigo. */
	private String tCodigo;
	
	/** The a codigo. */
	private String aCodigo;
	
	/** The a nombre. */
	private String aNombre;
	
	/** The a apellidos. */
	private String aApellidos;
	
	/** The a apellido paterno. */
	private String aApellidoPaterno;
	
	/** The a apellido materno. */
	private String aApellidoMaterno;
	
	/** The p codigo. */
	private String pCodigo;
	
	/** The p nombre. */
	private String pNombre;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The c nombre. */
	private String cNombre;
	
	/** The plan. */
	private int plan;
	
	/** The creditos. */
	private int creditos;
	
	/** The dia. */
	private String dia;
	
	/** The hora ini. */
	private String horaIni;
	
	/** The hora fin. */
	private String horaFin;	
	
	/** The nombre. */
	private String nombre;
	
	/** The repitencias. */
	private String repitencias;
	
	/** The frecuencia. */
	private int frecuencia;
	
	/** The desc frecuencia. */
	private String desc_frecuencia;
	
	/** The sesion. */
	private int sesion;
	
	/** The asistencia. */
	private String asistencia;
	
	/** The justificacion. */
	private String justificacion;
	
	/** The objetivos. */
	private String objetivos;
	
	/** The fecha. */
	private String fecha;
	
	/** The observacion. */
	private String observacion;
	
	/** The tipo alumno. */
	private int tipoAlumno;
	
	/** The estado sesion. */
	private String estadoSesion;
	
	/** The estado acta. */
	private int estadoActa;
	
	/** The criticidad. */
	private String criticidad;
	
	/** The validacion asistencia. */
	private String validacionAsistencia;
	
	/** The validacion carga acta. */
	private String validacionCargaActa;
	
	/** The validacion observacion. */
	private String validacionObservacion;
	
	/** The lista asistencia. */
	private ArrayList<ClaseMaestra> listaAsistencia;
	
	/** The lista justificacion. */
	private ArrayList<ClaseMaestra> listaJustificacion;
	
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
	 * Gets the repitencias.
	 *
	 * @return the repitencias
	 */
	public String getRepitencias() {
		return repitencias==null?"0":repitencias;
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
	 * Gets the t anio.
	 *
	 * @return the t anio
	 */
	public String gettAnio() {
		return tAnio==null?"0":tAnio;
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
	 * Gets the t periodo.
	 *
	 * @return the t periodo
	 */
	public String gettPeriodo() {
		return tPeriodo==null?"0":tPeriodo;
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
	 * Gets the t codigo.
	 *
	 * @return the t codigo
	 */
	public String gettCodigo() {
		return tCodigo==null?"":tCodigo;
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
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getaCodigo() {
		return aCodigo==null?"":aCodigo;
	}
	
	/**
	 * Sets the a codigo.
	 *
	 * @param aCodigo the new a codigo
	 */
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	
	/**
	 * Gets the p codigo.
	 *
	 * @return the p codigo
	 */
	public String getpCodigo() {
		return pCodigo==null?"":pCodigo;
	}
	
	/**
	 * Sets the p codigo.
	 *
	 * @param pCodigo the new p codigo
	 */
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	
	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getcCodigo() {
		return cCodigo==null?"":cCodigo;
	}
	
	/**
	 * Sets the c codigo.
	 *
	 * @param cCodigo the new c codigo
	 */
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	
	/**
	 * Gets the c nombre.
	 *
	 * @return the c nombre
	 */
	public String getcNombre() {
		return cNombre==null?"":cNombre;
	}
	
	/**
	 * Sets the c nombre.
	 *
	 * @param cNombre the new c nombre
	 */
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
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
	 * Gets the hora ini.
	 *
	 * @return the hora ini
	 */
	public String getHoraIni() {
		return horaIni==null?"":horaIni;
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
	 * Gets the p nombre.
	 *
	 * @return the p nombre
	 */
	public String getpNombre() {
		return pNombre==null?"":pNombre;
	}
	
	/**
	 * Sets the p nombre.
	 *
	 * @param pNombre the new p nombre
	 */
	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}
	
	/**
	 * Gets the a nombre.
	 *
	 * @return the a nombre
	 */
	public String getaNombre() {
		return aNombre==null?"":aNombre;
	}
	
	/**
	 * Sets the a nombre.
	 *
	 * @param aNombre the new a nombre
	 */
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	
	/**
	 * Gets the frecuencia.
	 *
	 * @return the frecuencia
	 */
	public int getFrecuencia() {
		return frecuencia;
	}
	
	/**
	 * Sets the frecuencia.
	 *
	 * @param frecuencia the new frecuencia
	 */
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	/**
	 * Gets the desc frecuencia.
	 *
	 * @return the desc frecuencia
	 */
	public String getDesc_frecuencia() {
		return desc_frecuencia;
	}
	
	/**
	 * Sets the desc frecuencia.
	 *
	 * @param desc_frecuencia the new desc frecuencia
	 */
	public void setDesc_frecuencia(String desc_frecuencia) {
		this.desc_frecuencia = desc_frecuencia;
	}
	
	/**
	 * Gets the sesion.
	 *
	 * @return the sesion
	 */
	public int getSesion() {
		return sesion;
	}
	
	/**
	 * Sets the sesion.
	 *
	 * @param sesion the new sesion
	 */
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
	
	/**
	 * Gets the a apellidos.
	 *
	 * @return the a apellidos
	 */
	public String getaApellidos() {
		return aApellidos;
	}
	
	/**
	 * Sets the a apellidos.
	 *
	 * @param aApellidos the new a apellidos
	 */
	public void setaApellidos(String aApellidos) {
		this.aApellidos = aApellidos;
	}
	
	/**
	 * Gets the a apellido paterno.
	 *
	 * @return the a apellido paterno
	 */
	public String getaApellidoPaterno() {
		return aApellidoPaterno;
	}
	
	/**
	 * Sets the a apellido paterno.
	 *
	 * @param aApellidoPaterno the new a apellido paterno
	 */
	public void setaApellidoPaterno(String aApellidoPaterno) {
		this.aApellidoPaterno = aApellidoPaterno;
	}
	
	/**
	 * Gets the a apellido materno.
	 *
	 * @return the a apellido materno
	 */
	public String getaApellidoMaterno() {
		return aApellidoMaterno;
	}
	
	/**
	 * Sets the a apellido materno.
	 *
	 * @param aApellidoMaterno the new a apellido materno
	 */
	public void setaApellidoMaterno(String aApellidoMaterno) {
		this.aApellidoMaterno = aApellidoMaterno;
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
	 * Gets the justificacion.
	 *
	 * @return the justificacion
	 */
	public String getJustificacion() {
		return justificacion;
	}
	
	/**
	 * Sets the justificacion.
	 *
	 * @param justificacion the new justificacion
	 */
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	
	/**
	 * Gets the objetivos.
	 *
	 * @return the objetivos
	 */
	public String getObjetivos() {
		return objetivos;
	}
	
	/**
	 * Sets the objetivos.
	 *
	 * @param objetivos the new objetivos
	 */
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * Sets the observacion.
	 *
	 * @param observacion the new observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	/**
	 * Gets the tipo alumno.
	 *
	 * @return the tipo alumno
	 */
	public int getTipoAlumno() {
		return tipoAlumno;
	}
	
	/**
	 * Sets the tipo alumno.
	 *
	 * @param tipoAlumno the new tipo alumno
	 */
	public void setTipoAlumno(int tipoAlumno) {
		this.tipoAlumno = tipoAlumno;
	}
	
	/**
	 * Gets the lista asistencia.
	 *
	 * @return the lista asistencia
	 */
	public ArrayList<ClaseMaestra> getListaAsistencia() {
		return listaAsistencia;
	}
	
	/**
	 * Sets the lista asistencia.
	 *
	 * @param listaAsistencia the new lista asistencia
	 */
	public void setListaAsistencia(ArrayList<ClaseMaestra> listaAsistencia) {
		this.listaAsistencia = listaAsistencia;
	}
	
	/**
	 * Gets the lista justificacion.
	 *
	 * @return the lista justificacion
	 */
	public ArrayList<ClaseMaestra> getListaJustificacion() {
		return listaJustificacion;
	}
	
	/**
	 * Sets the lista justificacion.
	 *
	 * @param listaJustificacion the new lista justificacion
	 */
	public void setListaJustificacion(ArrayList<ClaseMaestra> listaJustificacion) {
		this.listaJustificacion = listaJustificacion;
	}
	
	/**
	 * Gets the estado sesion.
	 *
	 * @return the estado sesion
	 */
	public String getEstadoSesion() {
		return estadoSesion;
	}
	
	/**
	 * Sets the estado sesion.
	 *
	 * @param estadoSesion the new estado sesion
	 */
	public void setEstadoSesion(String estadoSesion) {
		this.estadoSesion = estadoSesion;
	}
	
	/**
	 * Gets the criticidad.
	 *
	 * @return the criticidad
	 */
	public String getCriticidad() {
		return criticidad;
	}
	
	/**
	 * Sets the criticidad.
	 *
	 * @param criticidad the new criticidad
	 */
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	
	/**
	 * Gets the validacion asistencia.
	 *
	 * @return the validacion asistencia
	 */
	public String getValidacionAsistencia() {
		return validacionAsistencia;
	}
	
	/**
	 * Sets the validacion asistencia.
	 *
	 * @param validacionAsistencia the new validacion asistencia
	 */
	public void setValidacionAsistencia(String validacionAsistencia) {
		this.validacionAsistencia = validacionAsistencia;
	}
	
	/**
	 * Gets the validacion carga acta.
	 *
	 * @return the validacion carga acta
	 */
	public String getValidacionCargaActa() {
		return validacionCargaActa;
	}
	
	/**
	 * Sets the validacion carga acta.
	 *
	 * @param validacionCargaActa the new validacion carga acta
	 */
	public void setValidacionCargaActa(String validacionCargaActa) {
		this.validacionCargaActa = validacionCargaActa;
	}
	
	/**
	 * Gets the validacion observacion.
	 *
	 * @return the validacion observacion
	 */
	public String getValidacionObservacion() {
		return validacionObservacion;
	}
	
	/**
	 * Sets the validacion observacion.
	 *
	 * @param validacionObservacion the new validacion observacion
	 */
	public void setValidacionObservacion(String validacionObservacion) {
		this.validacionObservacion = validacionObservacion;
	}
	
	/**
	 * Gets the estado acta.
	 *
	 * @return the estado acta
	 */
	public int getEstadoActa() {
		return estadoActa;
	}
	
	/**
	 * Sets the estado acta.
	 *
	 * @param estadoActa the new estado acta
	 */
	public void setEstadoActa(int estadoActa) {
		this.estadoActa = estadoActa;
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
