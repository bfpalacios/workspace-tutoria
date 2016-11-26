package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface TutoriaServices.
 */
public interface TutoriaServices {
	
	/**
	 * Listar profesores.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ProfesorBO>   		listarProfesores() throws Exception;
	
	/**
	 * Listar alumnos regulares.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AlumnoBO>           listarAlumnosRegulares() throws Exception;
	
	/**
	 * Listar tutores regulares.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ProfesorBO>         listarTutoresRegulares() throws Exception;
	
	/**
	 * Listar area conocimiento.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AreaConocimientoBO> listarAreaConocimiento() throws Exception;
	
	/**
	 * Buscar datos alumno.
	 *
	 * @param codAlumno the cod alumno
	 * @return the alumno BO
	 * @throws Exception the exception
	 */
	public AlumnoBO                 buscarDatosAlumno(String codAlumno) throws Exception;
	
	/**
	 * Actualizar hora fin.
	 *
	 * @param idHoraInicio the id hora inicio
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> 		actualizarHoraFin(int idHoraInicio) throws Exception;
	
	/**
	 * Listar preguntas.
	 *
	 * @param tipoPregunta the tipo pregunta
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<EncuestaBO>         listarPreguntas(String tipoPregunta) throws Exception;
	
	/**
	 * Buscar datos encuesta.
	 *
	 * @param codTutoria the cod tutoria
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<EncuestaBO>         buscarDatosEncuesta(String codTutoria) throws Exception;
	
	/**
	 * Listar tutores regulares.
	 *
	 * @param codCurso the cod curso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ProfesorBO>   		listarTutoresRegulares(String codCurso) throws Exception;
	
	/**
	 * Buscar tutoria encuesta.
	 *
	 * @param codTutoria the cod tutoria
	 * @return the string
	 * @throws Exception the exception
	 */
	public String                   buscarTutoriaEncuesta(String codTutoria) throws Exception;
	
	/**
	 * Listar tutores observados.
	 *
	 * @param codCurso the cod curso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ProfesorBO>         listarTutoresObservados(String codCurso) throws Exception;
	
	/**
	 * Guardar datos tutoria.
	 *
	 * @param tutoriabo the tutoriabo
	 * @throws Exception the exception
	 */
	public void  					guardarDatosTutoria(TutoriaBO tutoriabo) throws Exception;	
	
	/**
	 * Listar hora fin.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> 		listarHoraFin(String tabla, String campo) throws Exception;
	
	/**
	 * Listar cursosx alumno regular.
	 *
	 * @param codAlumno the cod alumno
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO>            listarCursosxAlumnoRegular(String codAlumno) throws Exception;
	
	/**
	 * Listar hora inicio.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> 		listarHoraInicio(String tabla, String campo) throws Exception;	
	
	/**
	 * Listar alumnos regularesx curso.
	 *
	 * @param codCurso the cod curso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AlumnoBO>           listarAlumnosRegularesxCurso(String codCurso) throws Exception;
	
	/**
	 * Listar sesiones cierre.
	 *
	 * @param codTutoria the cod tutoria
	 * @param sesion the sesion
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<SesionBO>           listarSesionesCierre(String codTutoria, int sesion) throws Exception;
	
	/**
	 * Buscar tutoriax codigo alumno regular.
	 *
	 * @param codAlumno the cod alumno
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO>          buscarTutoriaxCodigoAlumnoRegular(String codAlumno) throws Exception;
	
	/**
	 * Procesar tutoria regulares.
	 *
	 * @param tutoriaBO the tutoria BO
	 * @param modo the modo
	 * @throws Exception the exception
	 */
	public void                     procesarTutoriaRegulares(TutoriaBO tutoriaBO, int modo) throws Exception;				
	
	/**
	 * Listar cursosx area conocimiento.
	 *
	 * @param codAreaConocimiento the cod area conocimiento
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> 			listarCursosxAreaConocimiento(String codAreaConocimiento) throws Exception;	
	
	/**
	 * Buscar tutoriax codigo alumno.
	 *
	 * @param codAlumno the cod alumno
	 * @param tipoAlumno the tipo alumno
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO>          buscarTutoriaxCodigoAlumno(String codAlumno, int tipoAlumno) throws Exception;	
	
	/**
	 * Verificar existencia observacion.
	 *
	 * @param codTutoria the cod tutoria
	 * @param sesion the sesion
	 * @return the int
	 * @throws Exception the exception
	 */
	public int                      verificarExistenciaObservacion(String codTutoria, int sesion) throws Exception;
	
	/**
	 * Buscar asistencia de profesor tutoria.
	 *
	 * @param fecha the fecha
	 * @param codigoCurso the codigo curso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaTProfBO>  buscarAsistenciaDeProfesorTutoria(String fecha, String codigoCurso) throws Exception;			
	
	/**
	 * Guardar encuesta inicial.
	 *
	 * @param encuesta the encuesta
	 * @param codTutoria the cod tutoria
	 * @param modo the modo
	 * @throws Exception the exception
	 */
	public void                     guardarEncuestaInicial(EncuestaBO encuesta, String codTutoria, int modo) throws Exception;
	
	/**
	 * Listar disponibilidades.
	 *
	 * @param codCurso the cod curso
	 * @param codUsuario the cod usuario
	 * @param tipoUsuario the tipo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<DisponibilidadBO>   listarDisponibilidades(String codCurso, String codUsuario, int tipoUsuario) throws Exception;
	
	/**
	 * Procesar tutoria observados.
	 *
	 * @param tutoria the tutoria
	 * @param codUsuario the cod usuario
	 * @param tipoAlumno the tipo alumno
	 * @throws Exception the exception
	 */
	public void 					procesarTutoriaObservados(TutoriaBO tutoria, String codUsuario, int tipoAlumno) throws Exception;	
	
	/**
	 * Listar horarios de tutoria profesor.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param p_codigo the p codigo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO> 			listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo) throws Exception;
	
	/**
	 * Actualizar estado observacion.
	 *
	 * @param observacion the observacion
	 * @param indicadorObservacion the indicador observacion
	 * @throws Exception the exception
	 */
	public void                     actualizarEstadoObservacion(ObservacionBO observacion, int indicadorObservacion) throws Exception;
	
	/**
	 * Buscar tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codCurso the cod curso
	 * @param codAlumno the cod alumno
	 * @param codDocente the cod docente
	 * @return the string
	 * @throws Exception the exception
	 */
	public String 					buscarTutoria(int anio, int periodo, String codCurso, String codAlumno, String codDocente) throws Exception;
	
	/**
	 * Listar horarios de tutoriax semana.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param c_codigo the c codigo
	 * @param dia the dia
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO> 			listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia)throws Exception;
	
	/**
	 * Listar observaciones.
	 *
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @param procesoTutoria the proceso tutoria
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ObservacionBO>      listarObservaciones(String codCurso, String codDocente, String codAlumno, int procesoTutoria) throws Exception;
	
	/**
	 * Guardar registro asistencia.
	 *
	 * @param asistenciaTutorias the asistencia tutorias
	 * @param fecha the fecha
	 * @param tipoAlumno the tipo alumno
	 * @param modo the modo
	 * @throws Exception the exception
	 */
	public void                     guardarRegistroAsistencia(TutoriaBO asistenciaTutorias, String fecha, int tipoAlumno, int modo) throws Exception;	
	
	/**
	 * Guardar observaciones asistencia.
	 *
	 * @param codTutoria the cod tutoria
	 * @param observacion the observacion
	 * @param criticidad the criticidad
	 * @param sesion the sesion
	 * @param razon the razon
	 * @param fechaCumplimiento the fecha cumplimiento
	 * @throws Exception the exception
	 */
	public void                     guardarObservacionesAsistencia(String codTutoria, String observacion, String criticidad, int sesion,String razon,String fechaCumplimiento) throws Exception;
	
	/**
	 * Buscar asistencia de alumnos tutoria.
	 *
	 * @param periodo the periodo
	 * @param anio the anio
	 * @param dia the dia
	 * @param curso the curso
	 * @param fecha the fecha
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoria(String periodo, String anio, String dia, String curso, String fecha) throws Exception;
	
	/**
	 * Listar datos tutoria.
	 *
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @param procesoTutoria the proceso tutoria
	 * @param modoUsuario the modo usuario
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO>          listarDatosTutoria(String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modoUsuario) throws Exception;
	
	/**
	 * Listar horarios de tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param a_codigo the a codigo
	 * @param codUsuario the cod usuario
	 * @param procesoTutoria the proceso tutoria
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO> 			listarHorariosDeTutoria(Integer anio, Integer periodo, String a_codigo, String codUsuario, int procesoTutoria)throws Exception;
	
	/**
	 * Guardar registro disponibilidad.
	 *
	 * @param disponibilidad the disponibilidad
	 * @param usuario the usuario
	 * @param modo the modo
	 * @param procesoRegistro the proceso registro
	 * @throws Exception the exception
	 */
	public void                     guardarRegistroDisponibilidad (DisponibilidadBO disponibilidad, String usuario, int modo, int procesoRegistro) throws Exception;	
	
	/**
	 * Guardar acta tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codTutoria the cod tutoria
	 * @param sesion the sesion
	 * @param actaTutoria the acta tutoria
	 * @param nombre the nombre
	 * @param estadoActa the estado acta
	 * @throws Exception the exception
	 */
	public void                     guardarActaTutoria(int anio, int periodo, String codTutoria, int sesion, byte[] actaTutoria, String nombre, int estadoActa) throws Exception;
	
	/**
	 * Listar sesion tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @param procesoTutoria the proceso tutoria
	 * @param modo the modo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<SesionBO>     	 	listarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modo) throws Exception;	
	
	/**
	 * Buscar asistencia alumnos tutoria.
	 *
	 * @param codDocente the cod docente
	 * @param codCurso the cod curso
	 * @param codAlumno the cod alumno
	 * @param fechaTutoria the fecha tutoria
	 * @param tipoAlumno the tipo alumno
	 * @param modo the modo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<TutoriaBO> 			buscarAsistenciaAlumnosTutoria(String codDocente, String codCurso, String codAlumno, String fechaTutoria, int tipoAlumno, int modo) throws Exception;
	
	/**
	 * Obtener acta tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @param sesion the sesion
	 * @param procesoTutoria the proceso tutoria
	 * @param modo the modo
	 * @return the sesion BO
	 * @throws Exception the exception
	 */
	public SesionBO                 obtenerActaTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception;
	
	/**
	 * Buscar sesion tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @param sesion the sesion
	 * @param procesoTutoria the proceso tutoria
	 * @param modo the modo
	 * @return the sesion BO
	 * @throws Exception the exception
	 */
	public SesionBO                 buscarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception;
	
	/**
	 * Buscar asistencia de alumnos tutoria user.
	 *
	 * @param periodo the periodo
	 * @param anio the anio
	 * @param dia the dia
	 * @param curso the curso
	 * @param codUser the cod user
	 * @param horaIni the hora ini
	 * @param horaFin the hora fin
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codUser, String horaIni, String horaFin) throws Exception;

	public AlumnoBO buscarDatosAlumnoRegular(String codAlumno);	
}
