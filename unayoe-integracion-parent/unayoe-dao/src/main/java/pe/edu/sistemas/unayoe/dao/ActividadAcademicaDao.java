/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ActividadAcademicaDao.
 */
public interface ActividadAcademicaDao {

	/**
	 * Guardar actividad academica.
	 *
	 * @param actividadAcademica the actividad academica
	 * @param horarios the horarios
	 * @param sesiones the sesiones
	 * @return true, if successful
	 */
	boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones);

	/**
	 * Guardar horario actividad.
	 *
	 * @param horarioBO the horario BO
	 * @return the horario BO
	 */
	HorarioBO guardarHorarioActividad(HorarioBO horarioBO);

	/**
	 * Guardar sesion par.
	 *
	 * @param sesionParBO the sesion par BO
	 * @return the sesion par BO
	 */
	SesionParBO guardarSesionPar(SesionParBO sesionParBO);

	/**
	 * Listar actividades.
	 *
	 * @return the list
	 */
	List<ActividadAcademicaBO> listarActividades();

	/**
	 * Obtener actividad.
	 *
	 * @param codigoActividad the codigo actividad
	 * @return the actividad academica BO
	 */
	ActividadAcademicaBO obtenerActividad(int codigoActividad);

	/**
	 * Listar actividades por tutor.
	 *
	 * @param codTutor the cod tutor
	 * @return the list
	 */
	List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor);

	/**
	 * Horarios por actividad.
	 *
	 * @param codigoActividad the codigo actividad
	 * @return the list
	 */
	List<HorarioBO> horariosPorActividad(int codigoActividad);

	/**
	 * Sesiones por actividad.
	 *
	 * @param codigoActividad the codigo actividad
	 * @return the list
	 */
	List<SesionParBO> sesionesPorActividad(int codigoActividad);

	/**
	 * Listar actividades por alumno.
	 *
	 * @param codAlumno the cod alumno
	 * @return the list
	 */
	List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno);

	/**
	 * Listar actividades por alumno matriculado.
	 *
	 * @param codAlumno the cod alumno
	 * @return the list
	 */
	List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno);

}
