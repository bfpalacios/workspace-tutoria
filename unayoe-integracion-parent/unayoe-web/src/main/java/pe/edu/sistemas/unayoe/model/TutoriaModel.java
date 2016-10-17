package pe.edu.sistemas.unayoe.model;
 

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

@Component("tutoriaModel")
@RequestScoped
public class TutoriaModel extends PersonaModel{
	
	private ProfesorModel profesor;
	private AlumnoModel alumno;
	private String codTutoria;
	private String dia;
	private String horaInicio;
	private String horaFin;
	private String periodo;
	private String descripcion; 
	private String aCodigo;
	private String aNombre;
	private String pCodigo;
	private String pNombre;
	private String cCodigo;
	private String cNombre;	
	private int frecuencia;
	private int sesion;
	/* atributos para observacion:*/
	private String observacion;
	
	private String criticidad;//va
	private String razon;//va
	private String tarea;//va
	private Date fechaCumplimiento;
	private String fechaAsignada;//el sistema
	private String fechaFin;//va
	private String estado;//va
	private String fechaEntregada;//al comienzo es nulo
	
	/*fin observacion*/
	private List<CursoBO> listarCursos;
	private List<AlumnoBO> listarAlumnos;
	private List<ProfesorBO> listarTutores;
	private List<SesionBO> listarSesiones;
	private List<SesionBO> listarSesionesCierre;
	private List<ClaseMaestra> listaHoraInicio;
	private List<ClaseMaestra> listaHoraFin;
	private List<EncuestaBO> listaEncuestas;
	private List<ObservacionBO> listaObservacionesPendientes;
	private List<ObservacionBO> listaObservacionesFinalizadas;
	private List<TutoriaBO> listaTutorias;/*listar todas las tutorias para ver su asistencia */
	private List<IndicadoresBO> listaIndicadores;
	
	public ProfesorModel getProfesor() {
		return profesor;
	}
	public void setProfesor(ProfesorModel profesor) {
		this.profesor = profesor;
	}
	public AlumnoModel getAlumno() {
		return alumno;
	}
	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<CursoBO> getListarCursos() {
		if(listarCursos!=null)
		Collections.sort(listarCursos);
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
	public String getpNombre() {
		return pNombre;
	}
	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}
	public String getaCodigo() {
		return aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getaNombre() {
		return aNombre;
	}
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	public String getcCodigo() {
		return cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getcNombre() {
		return cNombre;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	public List<SesionBO> getListarSesiones() {
		return listarSesiones;
	}
	public void setListarSesiones(List<SesionBO> listarSesiones) {
		this.listarSesiones = listarSesiones;
	}
	public int getSesion() {
		return sesion;
	}
	public void setSesion(int sesion) {
		this.sesion = sesion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getCriticidad() {
		return criticidad;
	}
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
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
	public List<EncuestaBO> getListaEncuestas() {
		return listaEncuestas;
	}
	public void setListaEncuestas(List<EncuestaBO> listaEncuestas) {
		this.listaEncuestas = listaEncuestas;
	}
	public String getCodTutoria() {
		return codTutoria == null?"":codTutoria;
	}
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	public List<ObservacionBO> getListaObservacionesPendientes() {
		return listaObservacionesPendientes;
	}
	public void setListaObservacionesPendientes(
			List<ObservacionBO> listaObservacionesPendientes) {
		this.listaObservacionesPendientes = listaObservacionesPendientes;
	}
	public List<ObservacionBO> getListaObservacionesFinalizadas() {
		return listaObservacionesFinalizadas;
	}
	public void setListaObservacionesFinalizadas(
			List<ObservacionBO> listaObservacionesFinalizadas) {
		this.listaObservacionesFinalizadas = listaObservacionesFinalizadas;
	}
	public List<SesionBO> getListarSesionesCierre() {
		return listarSesionesCierre;
	}
	public void setListarSesionesCierre(List<SesionBO> listarSesionesCierre) {
		this.listarSesionesCierre = listarSesionesCierre;
	}
	public List<TutoriaBO> getListaTutorias() {
		return listaTutorias;
	}
	public void setListaTutorias(List<TutoriaBO> listaTutorias) {
		this.listaTutorias = listaTutorias;
	}
	public List<IndicadoresBO> getListaIndicadores() {
		return listaIndicadores;
	}
	public void setListaIndicadores(List<IndicadoresBO> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public String getFechaAsignada() {
		return fechaAsignada;
	}
	public void setFechaAsignada(String fechaAsignada) {
		this.fechaAsignada = fechaAsignada;
	}
	public String getFechaFin() {
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        fechaFin=formateador.format(fechaCumplimiento);
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaEntregada() {
		return fechaEntregada;
	}
	public void setFechaEntregada(String fechaEntregada) {
		this.fechaEntregada = fechaEntregada;
	}
	public Date getFechaCumplimiento() {
		return fechaCumplimiento;
	}
	public void setFechaCumplimiento(Date fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}
	
}
