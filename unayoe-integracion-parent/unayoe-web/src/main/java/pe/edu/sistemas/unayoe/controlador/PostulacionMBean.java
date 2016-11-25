package pe.edu.sistemas.unayoe.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.core.util.IOUtils;
import pe.edu.sistemas.unayoe.model.DisponibilidadTutoriaParModel;
import pe.edu.sistemas.unayoe.model.PostulacionModel;
import pe.edu.sistemas.unayoe.services.AlumnoParServices;
import pe.edu.sistemas.unayoe.services.ConvocatoriaServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.PostulacionServices;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class PostulacionMBean.
 */
@Controller("postulacionMBean")
@ViewScoped
public class PostulacionMBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The postulacion model. */
	@Autowired
	private PostulacionModel postulacionModel;

	/** The disponibilidad tutoria par model. */
	@Autowired
	private DisponibilidadTutoriaParModel disponibilidadTutoriaParModel;

	/** The convocatoria services. */
	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	/** The tema services. */
	@Autowired
	private TemaServices temaServices;

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The postulacion services. */
	@Autowired
	private PostulacionServices postulacionServices;

	/** The alumno par services. */
	@Autowired
	private AlumnoParServices alumnoParServices;

	/** The alumno. */
	private AlumnoParBO alumno;

	/**
	 * Instantiates a new postulacion M bean.
	 */
	public PostulacionMBean() {
		this.alumno = null;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		return this.alumno.getNombre();
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return the apellidos
	 */
	public String getApellidos() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		return this.alumno.getApellidos();
	}

	/**
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public Integer getEdad() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		if (alumno.getFecha_nac() != null)
			return (new Date().getYear() - alumno.getFecha_nac().getYear());
		return null;

	}

	/**
	 * Info message.
	 *
	 * @param title the title
	 * @param detail the detail
	 */
	public void infoMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail));
	}

	/**
	 * Error message.
	 *
	 * @param title the title
	 * @param detail the detail
	 */
	public void errorMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail));
	}

	/**
	 * Registrar postulacion.
	 */
	public void registrarPostulacion() {

		Integer idConvocatoria = this.postulacionModel.getCodigoConvocatoria();
		if (idConvocatoria == null) {
			errorMessage("Error", "No se ha definido una convocatoria para esta postulacion.");
			return;
		}

		List<Integer> idTemas = new ArrayList<Integer>();
		Map<String, List<Integer>> mapTemas = this.postulacionModel.getMapTemas();
		for (Map.Entry<String, List<Integer>> entry : mapTemas.entrySet()) {
			List<Integer> temas = entry.getValue();
			if (temas != null && !temas.isEmpty()) {
				idTemas.addAll(temas);
			}
		}

		if (idTemas.isEmpty()) {
			errorMessage("Error", "Seleccione al menos un tema en el que desea brindar tutoría.");
			return;
		}

		if (idTemas.size() > 5) {
			errorMessage("Error", "Solo puede seleccionar un máximo de 5 temas para tutoría.");
			return;
		}

		UploadedFile archivoCV = this.postulacionModel.getArchivoCV();
		if (archivoCV == null) {
			errorMessage("Error", "No ha cargado su curriculum.");
			return;
		}

		byte[] bytesCV = null;
		try {
			bytesCV = IOUtils.inputStreamToByteArray(archivoCV.getInputstream());
		} catch (Exception err) {
			errorMessage("Error", "Ha ocurrido un error al procesar su CV.");
			return;
		}

		List<DisponibilidadTutoriaParBO> disponibilidad = this.disponibilidadTutoriaParModel.getDisponibilidad();
		if (disponibilidad == null || disponibilidad.isEmpty()) {
			errorMessage("Error", "Seleccione al menos un turno de disponibilidad horaria.");
			return;
		}

		// String codigoAlumno = obtenerCodigoAlumno();
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		String codigoAlumno = this.alumno.getCodigo();
		
		if (codigoAlumno == null || codigoAlumno.trim().compareTo("") == 0) {
			errorMessage("Error", "No se ha podido obtener su código de alumno.");
			return;
		}

		PostulacionBO postulacion = new PostulacionBO();
		postulacion.setCodigoAlumno(codigoAlumno);
		postulacion.setIdConvocatoria(idConvocatoria);
		postulacion.setIdTemas(idTemas);
		postulacion.setArchivoCV(bytesCV);
		postulacion.setDisponibilidad(disponibilidad);

		if (postulacionServices.registrarPostulacion(postulacion)) {
			infoMessage("Registrar Postulación", "La postulación se ha registrado correctamente.");
		} else {
			errorMessage("Error", "Ha ocurrido un error al registrar la postulacion.");
		}

		// house cleaning
		this.disponibilidadTutoriaParModel.limpiar();
		this.postulacionModel.limpiar();

	}

	/**
	 * Gets the convocatoria actual.
	 *
	 * @return the convocatoria actual
	 */
	public String getConvocatoriaActual() {
		ConvocatoriaBO convocatoria = this.convocatoriaServices.getConvocatoriaActual();
		if (convocatoria == null) {
			System.out.println("Convocatoria: NULL");
			return null;
		}
		System.out.println("Convocatoria: " + convocatoria.toString());

		this.postulacionModel.setCodigoConvocatoria(convocatoria.getId());
		return convocatoria.getNombre();
	}

	/**
	 * Gets the lista cursos.
	 *
	 * @return the lista cursos
	 */
	public List<CursoBO> getListaCursos() {
		Integer idConvocatoria = this.convocatoriaServices.getConvocatoriaActual().getId();
		return this.cursoServices.listarCursosPorConvocatoria(idConvocatoria);
	}

	/**
	 * Gets the lista temas.
	 *
	 * @return the lista temas
	 */
	public List<TemaBO> getListaTemas() {
		Integer codigoConvocatoria = this.postulacionModel.getCodigoConvocatoria();
		if (codigoConvocatoria == null) {
			return null;
		}
		String codigoCurso = this.postulacionModel.getCodigoCurso();
		if (codigoCurso == null) {
			return null;
		}
		List<TemaBO> temasCurso = new ArrayList<TemaBO>();
		for (TemaBO tema : this.temaServices.getTemasPorConvocatoria(codigoConvocatoria)) {
			if (tema.getCodigoCurso().compareTo(codigoCurso) == 0) {
				temasCurso.add(tema);
			}
		}
		return temasCurso;
	}

	/**
	 * Cambiar archivo CV.
	 */
	public void cambiarArchivoCV() {
		this.postulacionModel.setArchivoCV(null);
	}

	/**
	 * Subir CV.
	 *
	 * @param event the event
	 */
	public void subirCV(FileUploadEvent event) {
		UploadedFile archivoCV = event.getFile();

		if (archivoCV == null) {
			this.errorMessage("Error", "Ha ocurrido un error al cargar el archivo.");
			return;
		}

		if (archivoCV.getSize() > (5 * 1024 * 1024)) { // 5 megas
			this.errorMessage("Error", "El archivo es mayor a 5 MB.");
			return;
		}

		this.postulacionModel.setArchivoCV(archivoCV);
		this.infoMessage("Cargar CV", "El archivo \"" + archivoCV.getFileName() + "\" se ha cargado correctamente.");

	}

	/**
	 * Gets the lista postulaciones.
	 *
	 * @return the lista postulaciones
	 */
	public List<PostulacionBO> getListaPostulaciones() {
		List<PostulacionBO> postulaciones = new ArrayList<PostulacionBO>();
		return postulaciones;
	}

	/**
	 * Gets the postulacion model.
	 *
	 * @return the postulacion model
	 */
	public PostulacionModel getPostulacionModel() {
		return postulacionModel;
	}

	/**
	 * Sets the postulacion model.
	 *
	 * @param postulacionModel the new postulacion model
	 */
	public void setPostulacionModel(PostulacionModel postulacionModel) {
		this.postulacionModel = postulacionModel;
	}

	/**
	 * Gets the disponibilidad tutoria par model.
	 *
	 * @return the disponibilidad tutoria par model
	 */
	public DisponibilidadTutoriaParModel getDisponibilidadTutoriaParModel() {
		return disponibilidadTutoriaParModel;
	}

	/**
	 * Sets the disponibilidad tutoria par model.
	 *
	 * @param disponibilidadTutoriaParModel the new disponibilidad tutoria par model
	 */
	public void setDisponibilidadTutoriaParModel(DisponibilidadTutoriaParModel disponibilidadTutoriaParModel) {
		this.disponibilidadTutoriaParModel = disponibilidadTutoriaParModel;
	}

}
