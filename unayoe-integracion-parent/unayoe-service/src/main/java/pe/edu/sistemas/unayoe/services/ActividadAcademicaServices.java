package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface ActividadAcademicaServices.
 */
public interface ActividadAcademicaServices {

    /**
     * Guardar actividad academica.
     *
     * @param actividadAcademica the actividad academica
     * @param horarios the horarios
     * @param sesiones the sesiones
     * @return true, if successful
     */
    boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios, List<SesionParBO> sesiones);

    /**
     * Listar actividades.
     *
     * @return the list
     */
    List<ActividadAcademicaBO> listarActividades();

    /**
     * Listar actividades por tutor.
     *
     * @param codTutor the cod tutor
     * @return the list
     */
    List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor);
    
    /**
     * Listar actividades por alumno matriculado.
     *
     * @param codAlumno the cod alumno
     * @return the list
     */
    List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno);

    /**
     * Listar actividades por alumno.
     *
     * @param codAlumno the cod alumno
     * @return the list
     */
    List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno);

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
     * Obtener actividad.
     *
     * @param codigoActividad the codigo actividad
     * @return the actividad academica BO
     */
    ActividadAcademicaBO obtenerActividad(int codigoActividad);

}
