package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.ActividadAcademicaDao;
import pe.edu.sistemas.unayoe.services.ActividadAcademicaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

@Service
public class ActividadAcademicaServicesImpl implements ActividadAcademicaServices {

	@Autowired
	ActividadAcademicaDao actividadAcademicaDao;

	@Override
	public boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones) {
		return actividadAcademicaDao.guardarActividadAcademica(actividadAcademica, horarios, sesiones);
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno) {
		return actividadAcademicaDao.listarActividadesPorAlumnoMatriculado(codAlumno);
	}

	@Override
	public List<ActividadAcademicaBO> listarActividades() {
		return actividadAcademicaDao.listarActividades();
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor) {
		return actividadAcademicaDao.listarActividadesPorTutor(codTutor);
	}

	@Override
	public List<HorarioBO> horariosPorActividad(int codigoActividad) {
		return actividadAcademicaDao.horariosPorActividad(codigoActividad);
	}

	@Override
	public List<SesionParBO> sesionesPorActividad(int codigoActividad) {
		return actividadAcademicaDao.sesionesPorActividad(codigoActividad);
	}

	@Override
	public ActividadAcademicaBO obtenerActividad(int codigoActividad) {
		return actividadAcademicaDao.obtenerActividad(codigoActividad);
	}

	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno) {
		return actividadAcademicaDao.listarActividadesPorAlumno(codAlumno);
	}

}
