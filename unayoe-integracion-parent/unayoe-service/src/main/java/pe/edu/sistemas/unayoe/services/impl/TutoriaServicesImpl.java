package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;








import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.unayoe.dao.TutoriaIDao;
import pe.edu.sistemas.unayoe.dao.TutoriaIJdbcDao;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.services.transformer.ProfesorTransformerToBo;


// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaServicesImpl.
 */
@Service("asistenciaTutoriaServices") 	
public class TutoriaServicesImpl implements TutoriaServices {
	
	/** The asistencia tutoria I dao. */
	@Autowired
	private TutoriaIDao asistenciaTutoriaIDao;
	
	/** The asistencia tutoria I jdbc dao. */
	@Autowired
	private TutoriaIJdbcDao asistenciaTutoriaIJdbcDao;	
	
	/** The profesor transformer to bo. */
	@Autowired
	private ProfesorTransformerToBo profesorTransformerToBo;
	
	/** The tutoria I dao. */
	@Autowired
	private TutoriaIJdbcDao tutoriaIDao;	
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarAsistenciaDeAlumnosTutoria(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoria(
			String periodo, String anio, String dia, String curso, String fecha) throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoria(periodo, anio, dia, curso, fecha);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarAsistenciaDeAlumnosTutoriaUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codUser, String horaIni, String horaFin)
	throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoriaUser(periodo, anio, dia, curso, codUser, horaIni, horaFin);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarAsistenciaDeProfesorTutoria(java.lang.String, java.lang.String)
	 */
	public List<AsistenciaTProfBO> buscarAsistenciaDeProfesorTutoria(
			String fecha, String codigoCurso) throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDocenteTutoria(fecha, codigoCurso);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarProfesores()
	 */
	public List<ProfesorBO> listarProfesores() throws Exception {
		return profesorTransformerToBo.transformer(asistenciaTutoriaIDao.listarProfesores());
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarTutoresRegulares(java.lang.String)
	 */
	public List<ProfesorBO> listarTutoresRegulares(String codCurso) throws Exception {
		return tutoriaIDao.listarTutoresRegulares(codCurso);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarTutoresObservados(java.lang.String)
	 */
	public List<ProfesorBO> listarTutoresObservados(String codCurso) throws Exception{
		return tutoriaIDao.listarTutoresObservados(codCurso);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarDatosTutoria(pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO)
	 */
	public void  guardarDatosTutoria(TutoriaBO  tutoriabo) throws Exception{
		tutoriaIDao.insertarTutoria(tutoriabo);
	}	
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarHorariosDeTutoria(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio, Integer periodo, String a_codigo, String codUsuario, int procesoTutoria) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoria(anio, periodo, a_codigo, codUsuario, procesoTutoria);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarHorariosDeTutoriaProfesor(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoriaProfesor(anio, periodo, p_codigo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarHorariosDeTutoriaxSemana(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia) throws Exception{
		return tutoriaIDao.listarHorariosDeTutoriaxSemana(anio, periodo, c_codigo ,  dia);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarHoraInicio(java.lang.String, java.lang.String)
	 */
	public List<ClaseMaestra> listarHoraInicio(String tabla, String campo) throws Exception{
		return tutoriaIDao.llenarCombo(tabla, campo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarHoraFin(java.lang.String, java.lang.String)
	 */
	public List<ClaseMaestra> listarHoraFin(String tabla, String campo) throws Exception{
		return tutoriaIDao.llenarCombo(tabla, campo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#actualizarHoraFin(int)
	 */
	public List<ClaseMaestra> actualizarHoraFin(int idHoraInicio) throws Exception{
		return tutoriaIDao.actualizarHoraFin(idHoraInicio);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarDatosAlumno(java.lang.String)
	 */
	public AlumnoBO buscarDatosAlumno(String codAlumno) throws Exception{
		return tutoriaIDao.buscarDatosAlumno(codAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarTutoria(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String buscarTutoria(int anio, int periodo, String codCurso, String codAlumno, String codDocente) throws Exception{
		return tutoriaIDao.buscarTutoria(anio, periodo, codCurso, codAlumno, codDocente);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#procesarTutoriaRegulares(pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO, int)
	 */
	public void procesarTutoriaRegulares(TutoriaBO tutoriaBO, int modo) throws Exception{
		tutoriaIDao.procesarTutoriaRegulares(tutoriaBO, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarAsistenciaAlumnosTutoria(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public List<TutoriaBO> buscarAsistenciaAlumnosTutoria(String codDocente, String codCurso, String codAlumno, String fechaTutoria, int tipoAlumno, int modo) throws Exception{
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaAlumnosTutoria(codDocente, codCurso, codAlumno, fechaTutoria, tipoAlumno, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarRegistroAsistencia(pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO, java.lang.String, int, int)
	 */
	public void guardarRegistroAsistencia(TutoriaBO asistenciaTutorias, String fecha, int tipoAlumno, int modo) throws Exception{
		tutoriaIDao.guardarRegistroAsistencia(asistenciaTutorias, fecha, tipoAlumno, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarAreaConocimiento()
	 */
	public List<AreaConocimientoBO> listarAreaConocimiento() throws Exception{
		return tutoriaIDao.listarAreaConocimiento();
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarCursosxAreaConocimiento(java.lang.String)
	 */
	public List<CursoBO> listarCursosxAreaConocimiento(String codAreaConocimiento) throws Exception{
		return tutoriaIDao.listarCursosxAreaConocimiento(codAreaConocimiento);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#procesarTutoriaObservados(pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO, java.lang.String, int)
	 */
	public void procesarTutoriaObservados(TutoriaBO tutoria, String codUsuario, int tipoAlumno) throws Exception{
		tutoriaIDao.procesarTutoriaObservados(tutoria, codUsuario, tipoAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarSesionTutoria(int, int, java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public List<SesionBO> listarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modo) throws Exception{
		if(modo==5){
			modo=1;
		}
		return tutoriaIDao.listarSesionTutoria(anio, periodo, codCurso, codDocente, codAlumno, procesoTutoria, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#obtenerActaTutoria(int, int, java.lang.String, java.lang.String, java.lang.String, int, int, int)
	 */
	public SesionBO obtenerActaTutoria(int anio, int periodo, String codCurso, String codDocente, 
			                           String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		if(modo==5){
			modo=1;
		}
		return tutoriaIDao.obtenerActaTutoria(anio, periodo, codCurso, codDocente, codAlumno, sesion, procesoTutoria, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarActaTutoria(int, int, java.lang.String, int, byte[], java.lang.String, int)
	 */
	public void guardarActaTutoria(int anio, int periodo, String codTutoria, int sesion, byte[] actaTutoria, String nombre, int estadoActa) throws Exception{
		tutoriaIDao.guardarActaTutoria(anio, periodo, codTutoria, sesion, actaTutoria, nombre, estadoActa);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarRegistroDisponibilidad(pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadBO, java.lang.String, int, int)
	 */
	public void guardarRegistroDisponibilidad (DisponibilidadBO disponibilidad, String usuario, int modo, int procesoRegistro) throws Exception{
		tutoriaIDao.guardarRegistroDisponibilidad(disponibilidad, usuario, modo, procesoRegistro);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarTutoresRegulares()
	 */
	public List<ProfesorBO> listarTutoresRegulares() throws Exception{
		return tutoriaIDao.listarTutoresRegulares();
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarAlumnosRegulares()
	 */
	public List<AlumnoBO> listarAlumnosRegulares() throws Exception{
		return tutoriaIDao.listarAlumnosRegulares();
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarCursosxAlumnoRegular(java.lang.String)
	 */
	public List<CursoBO> listarCursosxAlumnoRegular(String codAlumno) throws Exception{
		return tutoriaIDao.listarCursosxAlumnoRegular(codAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarPreguntas(java.lang.String)
	 */
	public List<EncuestaBO> listarPreguntas(String tipoPregunta) throws Exception{
		return tutoriaIDao.listarPreguntas(tipoPregunta);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarTutoriaxCodigoAlumno(java.lang.String, int)
	 */
	public List<TutoriaBO> buscarTutoriaxCodigoAlumno(String codAlumno, int tipoAlumno) throws Exception{
		return tutoriaIDao.buscarTutoriaxCodigoAlumno(codAlumno, tipoAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarEncuestaInicial(pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO, java.lang.String, int)
	 */
	public void guardarEncuestaInicial(EncuestaBO encuesta, String codTutoria, int modo) throws Exception{
		tutoriaIDao.guardarEncuestaInicial(encuesta, codTutoria, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarTutoriaEncuesta(java.lang.String)
	 */
	public String buscarTutoriaEncuesta(String codTutoria) throws Exception{
		return tutoriaIDao.buscarTutoriaEncuesta(codTutoria);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarAlumnosRegularesxCurso(java.lang.String)
	 */
	public List<AlumnoBO> listarAlumnosRegularesxCurso(String codCurso) throws Exception{
		return tutoriaIDao.listarAlumnosRegularesxCurso(codCurso);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarDatosEncuesta(java.lang.String)
	 */
	public List<EncuestaBO> buscarDatosEncuesta(String codTutoria) throws Exception{
		return tutoriaIDao.buscarDatosEncuesta(codTutoria);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarTutoriaxCodigoAlumnoRegular(java.lang.String)
	 */
	public List<TutoriaBO> buscarTutoriaxCodigoAlumnoRegular(String codAlumno) throws Exception{
		return tutoriaIDao.buscarTutoriaxCodigoAlumnoRegular(codAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#guardarObservacionesAsistencia(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public void guardarObservacionesAsistencia(String codTutoria, String observacion, String criticidad, int sesion,String razon,String fechaCumplimiento) throws Exception{
		tutoriaIDao.guardarObservacionesAsistencia(codTutoria, observacion, criticidad, sesion,razon,fechaCumplimiento);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#buscarSesionTutoria(int, int, java.lang.String, java.lang.String, java.lang.String, int, int, int)
	 */
	public SesionBO buscarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		return tutoriaIDao.buscarSesionTutoria(anio, periodo, codCurso, codDocente, codAlumno, sesion, procesoTutoria, modo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarObservaciones(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	public List<ObservacionBO> listarObservaciones(String codCurso, String codDocente, String codAlumno, int procesoTutoria) throws Exception{
		return tutoriaIDao.listarObservaciones(codCurso, codDocente, codAlumno, procesoTutoria);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#actualizarEstadoObservacion(pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO, int)
	 */
	public void actualizarEstadoObservacion(ObservacionBO observacion, int indicadorObservacion) throws Exception{
		tutoriaIDao.actualizarEstadoObservacion(observacion, indicadorObservacion);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarSesionesCierre(java.lang.String, int)
	 */
	public List<SesionBO> listarSesionesCierre(String codTutoria, int sesion) throws Exception{
		return tutoriaIDao.listarSesionesCierre(codTutoria, sesion);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarDisponibilidades(java.lang.String, java.lang.String, int)
	 */
	public List<DisponibilidadBO> listarDisponibilidades(String codCurso, String codUsuario, int tipoUsuario) throws Exception{
		return tutoriaIDao.listarDisponibilidades(codCurso, codUsuario, tipoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#listarDatosTutoria(java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	public List<TutoriaBO> listarDatosTutoria(String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modoUsuario) throws Exception{
		if(modoUsuario==6){
			modoUsuario=1;
		}
		return tutoriaIDao.listarDatosTutoria(codCurso, codDocente, codAlumno, procesoTutoria, modoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.TutoriaServices#verificarExistenciaObservacion(java.lang.String, int)
	 */
	public int verificarExistenciaObservacion(String codTutoria, int sesion) throws Exception{
		return tutoriaIDao.verificarExistenciaObservacion(codTutoria, sesion);
	}

	@Override
	public AlumnoBO buscarDatosAlumnoRegular(String codAlumno) {
		return tutoriaIDao.buscarDatosAlumnoRegular(codAlumno);
	}
}
