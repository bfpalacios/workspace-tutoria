package pe.edu.sistemas.unayoe.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.ActividadAcademicaModel;
import pe.edu.sistemas.unayoe.services.ActividadAcademicaServices;
import pe.edu.sistemas.unayoe.services.AlumnoParServices;
import pe.edu.sistemas.unayoe.services.AreaConocimientoServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ActividadAcademicaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class MostrarTutoriasAlumnoMBean.
 */
@Controller("mostrarTutoriasAlumnoMBean")
@ViewScoped
public class MostrarTutoriasAlumnoMBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3925196291459547082L;

	/** The actividad academica services. */
	@Autowired
	private ActividadAcademicaServices actividadAcademicaServices;

	/** The usuario services. */
	@Autowired
	private UsuarioServices usuarioServices;

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The area conocimiento services. */
	@Autowired
	private AreaConocimientoServices areaConocimientoServices;

	/** The alumno par services. */
	@Autowired
	private AlumnoParServices alumnoParServices;

	/**
	 * Instantiates a new mostrar tutorias alumno M bean.
	 */
	public MostrarTutoriasAlumnoMBean() {

	}

	/**
	 * Obtener usuario.
	 *
	 * @return the string
	 */
	public String obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			nombre = user.getUsername();
		}
		return nombre;
	}

	/**
	 * Gets the actividades academicas matriculadas.
	 *
	 * @return the actividades academicas matriculadas
	 */
	public List<ActividadAcademicaModel> getActividadesAcademicasMatriculadas() {

		List<ActividadAcademicaModel> models = new ArrayList<ActividadAcademicaModel>();

		String username = obtenerUsuario();
		String codAlumno = alumnoParServices.getAlumnoPorUsuario(username).getCodigo();

		for (ActividadAcademicaBO activity : actividadAcademicaServices
				.listarActividadesPorAlumnoMatriculado(codAlumno)) {
			ActividadAcademicaModel model = new ActividadAcademicaModel();

			model.setCodigoTema(activity.getCodigoTema());
			model.setHorarios(actividadAcademicaServices.horariosPorActividad(activity.getCodigo()));
			
			model.setNombre(activity.getNombre());
			model.setNumeroVacantes(activity.getNumeroVacantes());
			model.setCodigo(activity.getCodigo());

			model.setNombreTutor(usuarioServices.obtenerTutorActividad(activity.getCodigo()).getNombreCompleto());
			model.setNombreCurso(cursoServices.obtnerCursoTema(activity.getCodigoTema()).getNombre());
			model.setNombreArea(
					areaConocimientoServices.obtnerAreaTema(activity.getCodigoTema()).getNomAreaConocimiento());
			
			model.setSesiones(actividadAcademicaServices.sesionesPorActividad(activity.getCodigo()));

			models.add(model);
		}

		return models;

	}

}
