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

// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaModel.
 */
@Component("tutoriaModel")
@RequestScoped
public class TutoriaModel extends PersonaModel{
	
	/** The profesor. */
	private ProfesorModel profesor;
	
	/** The alumno. */
	private AlumnoModel alumno;
	
	/** The cod tutoria. */
	private String codTutoria;
	
	/** The dia. */
	private String dia;
	
	/** The hora inicio. */
	private String horaInicio;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The periodo. */
	private String periodo;
	
	/** The descripcion. */
	private String descripcion; 
	
	/** The a codigo. */
	private String aCodigo;
	
	/** The a nombre. */
	private String aNombre;
	
	/** The p codigo. */
	private String pCodigo;
	
	/** The p nombre. */
	private String pNombre;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The c nombre. */
	private String cNombre;	
	
	/** The frecuencia. */
	private int frecuencia;
	
	/** The sesion. */
	private int sesion;
	
	/** The observacion. */
	/* atributos para observacion:*/
	private String observacion;
	
	/** The criticidad. */
	private String criticidad;//va
	
	/** The razon. */
	private String razon;//va
	
	/** The tarea. */
	private String tarea;//va
	
	/** The fecha cumplimiento. */
	private Date fechaCumplimiento;
	
	/** The fecha asignada. */
	private String fechaAsignada;//el sistema
	
	/** The fecha fin. */
	private String fechaFin;//va
	
	/** The estado. */
	private String estado;//va
	
	/** The fecha entregada. */
	private String fechaEntregada;//al comienzo es nulo
	
	/** The listar cursos. */
	/*fin observacion*/
	private List<CursoBO> listarCursos;
	
	/** The listar alumnos. */
	private List<AlumnoBO> listarAlumnos;
	
	/** The listar tutores. */
	private List<ProfesorBO> listarTutores;
	
	/** The listar sesiones. */
	private List<SesionBO> listarSesiones;
	
	/** The listar sesiones cierre. */
	private List<SesionBO> listarSesionesCierre;
	
	/** The lista hora inicio. */
	private List<ClaseMaestra> listaHoraInicio;
	
	/** The lista hora fin. */
	private List<ClaseMaestra> listaHoraFin;
	
	/** The lista encuestas. */
	private List<EncuestaBO> listaEncuestas;
	
	/** The lista observaciones pendientes. */
	private List<ObservacionBO> listaObservacionesPendientes;
	
	/** The lista observaciones finalizadas. */
	private List<ObservacionBO> listaObservacionesFinalizadas;
	
	/** The lista tutorias. */
	private List<TutoriaBO> listaTutorias;/*listar todas las tutorias para ver su asistencia */
	
	/** The lista indicadores. */
	private List<IndicadoresBO> listaIndicadores;
	
	/**
	 * Gets the profesor.
	 *
	 * @return the profesor
	 */
	public ProfesorModel getProfesor() {
		return profesor;
	}
	
	/**
	 * Sets the profesor.
	 *
	 * @param profesor the new profesor
	 */
	public void setProfesor(ProfesorModel profesor) {
		this.profesor = profesor;
	}
	
	/**
	 * Gets the alumno.
	 *
	 * @return the alumno
	 */
	public AlumnoModel getAlumno() {
		return alumno;
	}
	
	/**
	 * Sets the alumno.
	 *
	 * @param alumno the new alumno
	 */
	public void setAlumno(AlumnoModel alumno) {
		this.alumno = alumno;
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
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public String getHoraInicio() {
		return horaInicio;
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
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the listar cursos.
	 *
	 * @return the listar cursos
	 */
	public List<CursoBO> getListarCursos() {
		if(listarCursos!=null)
		Collections.sort(listarCursos);
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
	
	/**
	 * Gets the p nombre.
	 *
	 * @return the p nombre
	 */
	public String getpNombre() {
		return pNombre;
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
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getaCodigo() {
		return aCodigo;
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
	 * Gets the a nombre.
	 *
	 * @return the a nombre
	 */
	public String getaNombre() {
		return aNombre;
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
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getcCodigo() {
		return cCodigo;
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
		return cNombre;
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
	 * Gets the listar sesiones.
	 *
	 * @return the listar sesiones
	 */
	public List<SesionBO> getListarSesiones() {
		return listarSesiones;
	}
	
	/**
	 * Sets the listar sesiones.
	 *
	 * @param listarSesiones the new listar sesiones
	 */
	public void setListarSesiones(List<SesionBO> listarSesiones) {
		this.listarSesiones = listarSesiones;
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
	 * Gets the lista encuestas.
	 *
	 * @return the lista encuestas
	 */
	public List<EncuestaBO> getListaEncuestas() {
		return listaEncuestas;
	}
	
	/**
	 * Sets the lista encuestas.
	 *
	 * @param listaEncuestas the new lista encuestas
	 */
	public void setListaEncuestas(List<EncuestaBO> listaEncuestas) {
		this.listaEncuestas = listaEncuestas;
	}
	
	/**
	 * Gets the cod tutoria.
	 *
	 * @return the cod tutoria
	 */
	public String getCodTutoria() {
		return codTutoria == null?"":codTutoria;
	}
	
	/**
	 * Sets the cod tutoria.
	 *
	 * @param codTutoria the new cod tutoria
	 */
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	
	/**
	 * Gets the lista observaciones pendientes.
	 *
	 * @return the lista observaciones pendientes
	 */
	public List<ObservacionBO> getListaObservacionesPendientes() {
		return listaObservacionesPendientes;
	}
	
	/**
	 * Sets the lista observaciones pendientes.
	 *
	 * @param listaObservacionesPendientes the new lista observaciones pendientes
	 */
	public void setListaObservacionesPendientes(
			List<ObservacionBO> listaObservacionesPendientes) {
		this.listaObservacionesPendientes = listaObservacionesPendientes;
	}
	
	/**
	 * Gets the lista observaciones finalizadas.
	 *
	 * @return the lista observaciones finalizadas
	 */
	public List<ObservacionBO> getListaObservacionesFinalizadas() {
		return listaObservacionesFinalizadas;
	}
	
	/**
	 * Sets the lista observaciones finalizadas.
	 *
	 * @param listaObservacionesFinalizadas the new lista observaciones finalizadas
	 */
	public void setListaObservacionesFinalizadas(
			List<ObservacionBO> listaObservacionesFinalizadas) {
		this.listaObservacionesFinalizadas = listaObservacionesFinalizadas;
	}
	
	/**
	 * Gets the listar sesiones cierre.
	 *
	 * @return the listar sesiones cierre
	 */
	public List<SesionBO> getListarSesionesCierre() {
		return listarSesionesCierre;
	}
	
	/**
	 * Sets the listar sesiones cierre.
	 *
	 * @param listarSesionesCierre the new listar sesiones cierre
	 */
	public void setListarSesionesCierre(List<SesionBO> listarSesionesCierre) {
		this.listarSesionesCierre = listarSesionesCierre;
	}
	
	/**
	 * Gets the lista tutorias.
	 *
	 * @return the lista tutorias
	 */
	public List<TutoriaBO> getListaTutorias() {
		return listaTutorias;
	}
	
	/**
	 * Sets the lista tutorias.
	 *
	 * @param listaTutorias the new lista tutorias
	 */
	public void setListaTutorias(List<TutoriaBO> listaTutorias) {
		this.listaTutorias = listaTutorias;
	}
	
	/**
	 * Gets the lista indicadores.
	 *
	 * @return the lista indicadores
	 */
	public List<IndicadoresBO> getListaIndicadores() {
		return listaIndicadores;
	}
	
	/**
	 * Sets the lista indicadores.
	 *
	 * @param listaIndicadores the new lista indicadores
	 */
	public void setListaIndicadores(List<IndicadoresBO> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
	}
	
	/**
	 * Gets the razon.
	 *
	 * @return the razon
	 */
	public String getRazon() {
		return razon;
	}
	
	/**
	 * Sets the razon.
	 *
	 * @param razon the new razon
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	/**
	 * Gets the tarea.
	 *
	 * @return the tarea
	 */
	public String getTarea() {
		return tarea;
	}
	
	/**
	 * Sets the tarea.
	 *
	 * @param tarea the new tarea
	 */
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	
	/**
	 * Gets the fecha asignada.
	 *
	 * @return the fecha asignada
	 */
	public String getFechaAsignada() {
		return fechaAsignada;
	}
	
	/**
	 * Sets the fecha asignada.
	 *
	 * @param fechaAsignada the new fecha asignada
	 */
	public void setFechaAsignada(String fechaAsignada) {
		this.fechaAsignada = fechaAsignada;
	}
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        fechaFin=formateador.format(fechaCumplimiento);
		return fechaFin;
	}
	
	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Gets the fecha entregada.
	 *
	 * @return the fecha entregada
	 */
	public String getFechaEntregada() {
		return fechaEntregada;
	}
	
	/**
	 * Sets the fecha entregada.
	 *
	 * @param fechaEntregada the new fecha entregada
	 */
	public void setFechaEntregada(String fechaEntregada) {
		this.fechaEntregada = fechaEntregada;
	}
	
	/**
	 * Gets the fecha cumplimiento.
	 *
	 * @return the fecha cumplimiento
	 */
	public Date getFechaCumplimiento() {
		return fechaCumplimiento;
	}
	
	/**
	 * Sets the fecha cumplimiento.
	 *
	 * @param fechaCumplimiento the new fecha cumplimiento
	 */
	public void setFechaCumplimiento(Date fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}
	
}
