package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.ActividadAcademicaDao;
import pe.edu.sistemas.unayoe.services.ActividadAcademicaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;

// TODO: Auto-generated Javadoc
/**
 * The Class ActividadAcademicaServicesImpl.
 */
@Service
public class ActividadAcademicaServicesImpl implements ActividadAcademicaServices {

	/** The actividad academica dao. */
	@Autowired
	ActividadAcademicaDao actividadAcademicaDao;

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#guardarActividadAcademica(pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO, java.util.List, java.util.List)
	 */
	@Override
	public boolean guardarActividadAcademica(ActividadAcademicaBO actividadAcademica, List<HorarioBO> horarios,
			List<SesionParBO> sesiones) {
		return actividadAcademicaDao.guardarActividadAcademica(actividadAcademica, horarios, sesiones);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#listarActividadesPorAlumnoMatriculado(java.lang.String)
	 */
	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumnoMatriculado(String codAlumno) {
		return actividadAcademicaDao.listarActividadesPorAlumnoMatriculado(codAlumno);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#listarActividades()
	 */
	@Override
	public List<ActividadAcademicaBO> listarActividades() {
		return actividadAcademicaDao.listarActividades();
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#listarActividadesPorTutor(java.lang.String)
	 */
	@Override
	public List<ActividadAcademicaBO> listarActividadesPorTutor(String codTutor) {
		return actividadAcademicaDao.listarActividadesPorTutor(codTutor);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#horariosPorActividad(int)
	 */
	@Override
	public List<HorarioBO> horariosPorActividad(int codigoActividad) {
		return actividadAcademicaDao.horariosPorActividad(codigoActividad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#sesionesPorActividad(int)
	 */
	@Override
	public List<SesionParBO> sesionesPorActividad(int codigoActividad) {
		return actividadAcademicaDao.sesionesPorActividad(codigoActividad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#obtenerActividad(int)
	 */
	@Override
	public ActividadAcademicaBO obtenerActividad(int codigoActividad) {
		return actividadAcademicaDao.obtenerActividad(codigoActividad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ActividadAcademicaServices#listarActividadesPorAlumno(java.lang.String)
	 */
	@Override
	public List<ActividadAcademicaBO> listarActividadesPorAlumno(String codAlumno) {
		return actividadAcademicaDao.listarActividadesPorAlumno(codAlumno);
	}

}
