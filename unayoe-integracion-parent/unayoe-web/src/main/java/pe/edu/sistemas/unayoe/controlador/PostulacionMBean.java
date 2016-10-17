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

@Controller("postulacionMBean")
@ViewScoped
public class PostulacionMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PostulacionModel postulacionModel;

	@Autowired
	private DisponibilidadTutoriaParModel disponibilidadTutoriaParModel;

	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	@Autowired
	private TemaServices temaServices;

	@Autowired
	private CursoServices cursoServices;

	@Autowired
	private PostulacionServices postulacionServices;

	@Autowired
	private AlumnoParServices alumnoParServices;

	private AlumnoParBO alumno;

	public PostulacionMBean() {
		this.alumno = null;
	}

	public String obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			nombre = user.getUsername();
		}
		return nombre;
	}

	public String getNombre() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		return this.alumno.getNombre();
	}

	public String getApellidos() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		return this.alumno.getApellidos();
	}

	public Integer getEdad() {
		String usuario = obtenerUsuario();
		this.alumno = alumnoParServices.getAlumnoPorUsuario(usuario);
		if (alumno.getFecha_nac() != null)
			return (new Date().getYear() - alumno.getFecha_nac().getYear());
		return null;

	}

	public void infoMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail));
	}

	public void errorMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail));
	}

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

	public List<CursoBO> getListaCursos() {
		Integer idConvocatoria = this.convocatoriaServices.getConvocatoriaActual().getId();
		return this.cursoServices.listarCursosPorConvocatoria(idConvocatoria);
	}

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

	public void cambiarArchivoCV() {
		this.postulacionModel.setArchivoCV(null);
	}

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

	public List<PostulacionBO> getListaPostulaciones() {
		List<PostulacionBO> postulaciones = new ArrayList<PostulacionBO>();
		return postulaciones;
	}

	public PostulacionModel getPostulacionModel() {
		return postulacionModel;
	}

	public void setPostulacionModel(PostulacionModel postulacionModel) {
		this.postulacionModel = postulacionModel;
	}

	public DisponibilidadTutoriaParModel getDisponibilidadTutoriaParModel() {
		return disponibilidadTutoriaParModel;
	}

	public void setDisponibilidadTutoriaParModel(DisponibilidadTutoriaParModel disponibilidadTutoriaParModel) {
		this.disponibilidadTutoriaParModel = disponibilidadTutoriaParModel;
	}

}
