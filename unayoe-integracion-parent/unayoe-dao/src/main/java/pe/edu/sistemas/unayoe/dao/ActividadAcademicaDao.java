package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

import java.util.List;

public interface ActividadAcademicaDao {

	boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones);

	HorarioBO guardarHorarioActividad(HorarioBO horarioBO);

	SesionParBO guardarSesionPar(SesionParBO sesionParBO);

	List<ActividadAcademicaBO> listarActividades();

	ActividadAcademicaBO obtenerActividad(int codigoActividad);

	List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor);

	List<HorarioBO> horariosPorActividad(int codigoActividad);

	List<SesionParBO> sesionesPorActividad(int codigoActividad);

	List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno);

	List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno);

}
