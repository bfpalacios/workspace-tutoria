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


@Service("asistenciaTutoriaServices") 	
public class TutoriaServicesImpl implements TutoriaServices {
	@Autowired
	private TutoriaIDao asistenciaTutoriaIDao;
	@Autowired
	private TutoriaIJdbcDao asistenciaTutoriaIJdbcDao;	
	@Autowired
	private ProfesorTransformerToBo profesorTransformerToBo;
	@Autowired
	private TutoriaIJdbcDao tutoriaIDao;	
	
	public List<AsistenciaTAlumBO> buscarAsistenciaDeAlumnosTutoria(
			String periodo, String anio, String dia, String curso, String fecha) throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoria(periodo, anio, dia, curso, fecha);
	}
	
	public List<AsistenciaTAlumBO>  buscarAsistenciaDeAlumnosTutoriaUser(String periodo, String anio, String dia, String curso, String codUser, String horaIni, String horaFin)
	throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDeAlumnosTutoriaUser(periodo, anio, dia, curso, codUser, horaIni, horaFin);
	}

	public List<AsistenciaTProfBO> buscarAsistenciaDeProfesorTutoria(
			String fecha, String codigoCurso) throws Exception {
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaDocenteTutoria(fecha, codigoCurso);
	}

	public List<ProfesorBO> listarProfesores() throws Exception {
		return profesorTransformerToBo.transformer(asistenciaTutoriaIDao.listarProfesores());
	}
	
	public List<ProfesorBO> listarTutoresRegulares(String codCurso) throws Exception {
		return tutoriaIDao.listarTutoresRegulares(codCurso);
	}
	
	public List<ProfesorBO> listarTutoresObservados(String codCurso) throws Exception{
		return tutoriaIDao.listarTutoresObservados(codCurso);
	}
	
	public void  guardarDatosTutoria(TutoriaBO  tutoriabo) throws Exception{
		tutoriaIDao.insertarTutoria(tutoriabo);
	}	
	
	public List<TutoriaBO> listarHorariosDeTutoria(Integer anio, Integer periodo, String a_codigo, String codUsuario, int procesoTutoria) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoria(anio, periodo, a_codigo, codUsuario, procesoTutoria);
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaProfesor(Integer anio , Integer periodo ,String p_codigo) throws Exception{
		return asistenciaTutoriaIJdbcDao.listarHorariosDeTutoriaProfesor(anio, periodo, p_codigo);
	}
	
	public List<TutoriaBO> listarHorariosDeTutoriaxSemana(Integer anio , Integer periodo ,String c_codigo , String dia) throws Exception{
		return tutoriaIDao.listarHorariosDeTutoriaxSemana(anio, periodo, c_codigo ,  dia);
	}
	
	public List<ClaseMaestra> listarHoraInicio(String tabla, String campo) throws Exception{
		return tutoriaIDao.llenarCombo(tabla, campo);
	}
	
	public List<ClaseMaestra> listarHoraFin(String tabla, String campo) throws Exception{
		return tutoriaIDao.llenarCombo(tabla, campo);
	}
	
	public List<ClaseMaestra> actualizarHoraFin(int idHoraInicio) throws Exception{
		return tutoriaIDao.actualizarHoraFin(idHoraInicio);
	}
	
	public AlumnoBO buscarDatosAlumno(String codAlumno) throws Exception{
		return tutoriaIDao.buscarDatosAlumno(codAlumno);
	}
	
	public String buscarTutoria(int anio, int periodo, String codCurso, String codAlumno, String codDocente) throws Exception{
		return tutoriaIDao.buscarTutoria(anio, periodo, codCurso, codAlumno, codDocente);
	}
	
	public void procesarTutoriaRegulares(TutoriaBO tutoriaBO, int modo) throws Exception{
		tutoriaIDao.procesarTutoriaRegulares(tutoriaBO, modo);
	}
	
	public List<TutoriaBO> buscarAsistenciaAlumnosTutoria(String codDocente, String codCurso, String codAlumno, String fechaTutoria, int tipoAlumno, int modo) throws Exception{
		return asistenciaTutoriaIJdbcDao.buscarAsistenciaAlumnosTutoria(codDocente, codCurso, codAlumno, fechaTutoria, tipoAlumno, modo);
	}
	
	public void guardarRegistroAsistencia(TutoriaBO asistenciaTutorias, String fecha, int tipoAlumno, int modo) throws Exception{
		tutoriaIDao.guardarRegistroAsistencia(asistenciaTutorias, fecha, tipoAlumno, modo);
	}
	
	public List<AreaConocimientoBO> listarAreaConocimiento() throws Exception{
		return tutoriaIDao.listarAreaConocimiento();
	}
	
	public List<CursoBO> listarCursosxAreaConocimiento(String codAreaConocimiento) throws Exception{
		return tutoriaIDao.listarCursosxAreaConocimiento(codAreaConocimiento);
	}
	
	public void procesarTutoriaObservados(TutoriaBO tutoria, String codUsuario, int tipoAlumno) throws Exception{
		tutoriaIDao.procesarTutoriaObservados(tutoria, codUsuario, tipoAlumno);
	}
	
	public List<SesionBO> listarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modo) throws Exception{
		return tutoriaIDao.listarSesionTutoria(anio, periodo, codCurso, codDocente, codAlumno, procesoTutoria, modo);
	}
	
	public SesionBO obtenerActaTutoria(int anio, int periodo, String codCurso, String codDocente, 
			                           String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		return tutoriaIDao.obtenerActaTutoria(anio, periodo, codCurso, codDocente, codAlumno, sesion, procesoTutoria, modo);
	}
	
	public void guardarActaTutoria(int anio, int periodo, String codTutoria, int sesion, byte[] actaTutoria, String nombre, int estadoActa) throws Exception{
		tutoriaIDao.guardarActaTutoria(anio, periodo, codTutoria, sesion, actaTutoria, nombre, estadoActa);
	}
	
	public void guardarRegistroDisponibilidad (DisponibilidadBO disponibilidad, String usuario, int modo, int procesoRegistro) throws Exception{
		tutoriaIDao.guardarRegistroDisponibilidad(disponibilidad, usuario, modo, procesoRegistro);
	}
	public List<ProfesorBO> listarTutoresRegulares() throws Exception{
		return tutoriaIDao.listarTutoresRegulares();
	}
	
	public List<AlumnoBO> listarAlumnosRegulares() throws Exception{
		return tutoriaIDao.listarAlumnosRegulares();
	}
	
	public List<CursoBO> listarCursosxAlumnoRegular(String codAlumno) throws Exception{
		return tutoriaIDao.listarCursosxAlumnoRegular(codAlumno);
	}
	
	public List<EncuestaBO> listarPreguntas(String tipoPregunta) throws Exception{
		return tutoriaIDao.listarPreguntas(tipoPregunta);
	}
	
	public List<TutoriaBO> buscarTutoriaxCodigoAlumno(String codAlumno, int tipoAlumno) throws Exception{
		return tutoriaIDao.buscarTutoriaxCodigoAlumno(codAlumno, tipoAlumno);
	}
	
	public void guardarEncuestaInicial(EncuestaBO encuesta, String codTutoria, int modo) throws Exception{
		tutoriaIDao.guardarEncuestaInicial(encuesta, codTutoria, modo);
	}
	
	public String buscarTutoriaEncuesta(String codTutoria) throws Exception{
		return tutoriaIDao.buscarTutoriaEncuesta(codTutoria);
	}
	
	public List<AlumnoBO> listarAlumnosRegularesxCurso(String codCurso) throws Exception{
		return tutoriaIDao.listarAlumnosRegularesxCurso(codCurso);
	}
	
	public List<EncuestaBO> buscarDatosEncuesta(String codTutoria) throws Exception{
		return tutoriaIDao.buscarDatosEncuesta(codTutoria);
	}
	
	public List<TutoriaBO> buscarTutoriaxCodigoAlumnoRegular(String codAlumno) throws Exception{
		return tutoriaIDao.buscarTutoriaxCodigoAlumnoRegular(codAlumno);
	}
	
	public void guardarObservacionesAsistencia(String codTutoria, String observacion, String criticidad, int sesion,String razon,String fechaCumplimiento) throws Exception{
		tutoriaIDao.guardarObservacionesAsistencia(codTutoria, observacion, criticidad, sesion,razon,fechaCumplimiento);
	}
	
	public SesionBO buscarSesionTutoria(int anio, int periodo, String codCurso, String codDocente, String codAlumno, int sesion, int procesoTutoria, int modo) throws Exception{
		return tutoriaIDao.buscarSesionTutoria(anio, periodo, codCurso, codDocente, codAlumno, sesion, procesoTutoria, modo);
	}
	
	public List<ObservacionBO> listarObservaciones(String codCurso, String codDocente, String codAlumno, int procesoTutoria) throws Exception{
		return tutoriaIDao.listarObservaciones(codCurso, codDocente, codAlumno, procesoTutoria);
	}
	
	public void actualizarEstadoObservacion(ObservacionBO observacion, int indicadorObservacion) throws Exception{
		tutoriaIDao.actualizarEstadoObservacion(observacion, indicadorObservacion);
	}
	
	public List<SesionBO> listarSesionesCierre(String codTutoria, int sesion) throws Exception{
		return tutoriaIDao.listarSesionesCierre(codTutoria, sesion);
	}
	
	public List<DisponibilidadBO> listarDisponibilidades(String codCurso, String codUsuario, int tipoUsuario) throws Exception{
		return tutoriaIDao.listarDisponibilidades(codCurso, codUsuario, tipoUsuario);
	}
	
	public List<TutoriaBO> listarDatosTutoria(String codCurso, String codDocente, String codAlumno, int procesoTutoria, int modoUsuario) throws Exception{
		return tutoriaIDao.listarDatosTutoria(codCurso, codDocente, codAlumno, procesoTutoria, modoUsuario);
	}
	
	public int verificarExistenciaObservacion(String codTutoria, int sesion) throws Exception{
		return tutoriaIDao.verificarExistenciaObservacion(codTutoria, sesion);
	}
}
