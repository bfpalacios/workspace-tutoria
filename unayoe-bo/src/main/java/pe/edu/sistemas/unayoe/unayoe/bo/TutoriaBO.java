package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.ArrayList;

import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

public class TutoriaBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String tAnio;
	private String tPeriodo;
	private String tCodigo;
	private String aCodigo;
	private String aNombre;
	private String aApellidos;
	private String aApellidoPaterno;
	private String aApellidoMaterno;
	private String pCodigo;
	private String pNombre;
	private String cCodigo;
	private String cNombre;
	private int plan;
	private int creditos;
	private String dia;
	private String horaIni;
	private String horaFin;	
	private String nombre;
	private String repitencias;
	private int frecuencia;
	private String desc_frecuencia;
	private int sesion;
	private String asistencia;
	private String justificacion;
	private String objetivos;
	private String fecha;
	private String observacion;
	private int tipoAlumno;
	private String estadoSesion;
	private int estadoActa;
	private String criticidad;
	private String validacionAsistencia;
	private String validacionCargaActa;
	private String validacionObservacion;
	private ArrayList<ClaseMaestra> listaAsistencia;
	private ArrayList<ClaseMaestra> listaJustificacion;
	private int num_sesiones;
	private int num_asistencia_asistio;
	private int num_asistencia_falto;
	private int num_asistencia_tardanza;
	private int num_tareas_pendiente;
	private int num_tareas_parcialmente;
	private int num_tareas_cerrado;
	private int num_actas;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRepitencias() {
		return repitencias==null?"0":repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	public String gettAnio() {
		return tAnio==null?"0":tAnio;
	}
	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}
	public String gettPeriodo() {
		return tPeriodo==null?"0":tPeriodo;
	}
	public void settPeriodo(String tPeriodo) {
		this.tPeriodo = tPeriodo;
	}
	public String gettCodigo() {
		return tCodigo==null?"":tCodigo;
	}
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}
	public String getaCodigo() {
		return aCodigo==null?"":aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getpCodigo() {
		return pCodigo==null?"":pCodigo;
	}
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	public String getcCodigo() {
		return cCodigo==null?"":cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getcNombre() {
		return cNombre==null?"":cNombre;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	public String getDia() {
		return dia==null?"":dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHoraIni() {
		return horaIni==null?"":horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin==null?"":horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getpNombre() {
		return pNombre==null?"":pNombre;
	}
	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}
	public String getaNombre() {
		return aNombre==null?"":aNombre;
	}
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	public String getDesc_frecuencia() {
		return desc_frecuencia;
	}
	public void setDesc_frecuencia(String desc_frecuencia) {
		this.desc_frecuencia = desc_frecuencia;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
	public String getaApellidos() {
		return aApellidos;
	}
	public void setaApellidos(String aApellidos) {
		this.aApellidos = aApellidos;
	}
	public String getaApellidoPaterno() {
		return aApellidoPaterno;
	}
	public void setaApellidoPaterno(String aApellidoPaterno) {
		this.aApellidoPaterno = aApellidoPaterno;
	}
	public String getaApellidoMaterno() {
		return aApellidoMaterno;
	}
	public void setaApellidoMaterno(String aApellidoMaterno) {
		this.aApellidoMaterno = aApellidoMaterno;
	} 
	public String getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public String getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getTipoAlumno() {
		return tipoAlumno;
	}
	public void setTipoAlumno(int tipoAlumno) {
		this.tipoAlumno = tipoAlumno;
	}
	public ArrayList<ClaseMaestra> getListaAsistencia() {
		return listaAsistencia;
	}
	public void setListaAsistencia(ArrayList<ClaseMaestra> listaAsistencia) {
		this.listaAsistencia = listaAsistencia;
	}
	public ArrayList<ClaseMaestra> getListaJustificacion() {
		return listaJustificacion;
	}
	public void setListaJustificacion(ArrayList<ClaseMaestra> listaJustificacion) {
		this.listaJustificacion = listaJustificacion;
	}
	public String getEstadoSesion() {
		return estadoSesion;
	}
	public void setEstadoSesion(String estadoSesion) {
		this.estadoSesion = estadoSesion;
	}
	public String getCriticidad() {
		return criticidad;
	}
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	public String getValidacionAsistencia() {
		return validacionAsistencia;
	}
	public void setValidacionAsistencia(String validacionAsistencia) {
		this.validacionAsistencia = validacionAsistencia;
	}
	public String getValidacionCargaActa() {
		return validacionCargaActa;
	}
	public void setValidacionCargaActa(String validacionCargaActa) {
		this.validacionCargaActa = validacionCargaActa;
	}
	public String getValidacionObservacion() {
		return validacionObservacion;
	}
	public void setValidacionObservacion(String validacionObservacion) {
		this.validacionObservacion = validacionObservacion;
	}
	public int getEstadoActa() {
		return estadoActa;
	}
	public void setEstadoActa(int estadoActa) {
		this.estadoActa = estadoActa;
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
