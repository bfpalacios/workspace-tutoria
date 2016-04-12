package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

import java.util.List;

public interface ActividadAcademicaServices {

    boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios, List<SesionParBO> sesiones);

    List<ActividadAcademicaBO> listarActividades();

    List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor);
    
    List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno);

    List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno);

    List<HorarioBO> horariosPorActividad(int codigoActividad);

    List<SesionParBO> sesionesPorActividad(int codigoActividad);

    ActividadAcademicaBO obtenerActividad(int codigoActividad);

}
