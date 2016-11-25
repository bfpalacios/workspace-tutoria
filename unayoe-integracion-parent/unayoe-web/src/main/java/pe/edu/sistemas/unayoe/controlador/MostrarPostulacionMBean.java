package pe.edu.sistemas.unayoe.controlador;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.MostrarPostulacionModel;
import pe.edu.sistemas.unayoe.services.ConvocatoriaServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.PostulacionServices;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class MostrarPostulacionMBean.
 */
@Controller("mostrarPostulacionMBean")
@ViewScoped
public class MostrarPostulacionMBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1094056852928420585L;

	/** The mostrar postulacion model. */
	@Autowired
	private MostrarPostulacionModel mostrarPostulacionModel;

	/** The convocatoria services. */
	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The postulacion services. */
	@Autowired
	private PostulacionServices postulacionServices;

	/** The tema services. */
	@Autowired
	private TemaServices temaServices;

	/**
	 * Instantiates a new mostrar postulacion M bean.
	 */
	public MostrarPostulacionMBean() {

	}

	/**
	 * Gets the convocatoria actual.
	 *
	 * @return the convocatoria actual
	 */
	public String getConvocatoriaActual() {
		ConvocatoriaBO convocatoria = convocatoriaServices.getConvocatoriaActual();
		if (convocatoria == null) {
			return null;
		}
		this.mostrarPostulacionModel.setIdConvocatoria(convocatoria.getId());
		return convocatoria.getNombre();
	}

	/**
	 * Gets the lista postulaciones.
	 *
	 * @return the lista postulaciones
	 */
	public List<PostulacionBO> getListaPostulaciones() {
		Integer idConvocatoria = this.mostrarPostulacionModel.getIdConvocatoria();
		if (idConvocatoria == null) {
			return null;
		}

		Integer idTema = this.mostrarPostulacionModel.getIdTema();
		if (idTema == null) {
			return null;
		}

		List<PostulacionBO> postulaciones = this.postulacionServices.getPostulacionesPorConvocatoriaTema(idConvocatoria,
				idTema);

		// quitar las postulaciones aprobadas
		for (PostulacionBO postulacion : postulaciones) {
			List<Integer> temasAprobados = postulacion.getIdTemasAprobados();
			if (temasAprobados != null && temasAprobados.contains(idTema)) {
				postulaciones.remove(postulacion);
			}
		}

		return postulaciones;
	}

	/**
	 * Gets the lista cursos.
	 *
	 * @return the lista cursos
	 */
	public List<CursoBO> getListaCursos() {
		Integer idConvocatoria = this.mostrarPostulacionModel.getIdConvocatoria();
		if (idConvocatoria == null) {
			return null;
		}
		return this.cursoServices.listarCursosPorConvocatoria(idConvocatoria);
	}

	/**
	 * Gets the lista temas.
	 *
	 * @return the lista temas
	 */
	public List<TemaBO> getListaTemas() {
		Integer idConvocatoria = this.mostrarPostulacionModel.getIdConvocatoria();
		String codigoCurso = this.mostrarPostulacionModel.getCodigoCurso();
		if (idConvocatoria == null || codigoCurso == null) {
			return null;
		}
		// return this.temaServices.getTemasPorConvocatoria(idConvocatoria);
		return this.temaServices.getTemasPorConvocatoriaCurso(idConvocatoria, codigoCurso);
	}

	/**
	 * Descargar CV.
	 *
	 * @param idPostulacion the id postulacion
	 * @return the streamed content
	 */
	public StreamedContent descargarCV(Integer idPostulacion) {
		System.out.println("Descargando CV: " + idPostulacion);

		PostulacionBO postulacion = postulacionServices.getPostulacion(idPostulacion);
		if (postulacion == null) {
			System.out.println("Postulacion Nula");
			return null;
		}

		if (postulacion.getArchivoCV() == null) {
			System.out.println("Stream nulo");
			return null;
		}

		InputStream streamCV = new ByteArrayInputStream(postulacion.getArchivoCV());

		StreamedContent file = new DefaultStreamedContent(streamCV);
		return file;
	}

	/**
	 * Aprobar postulante.
	 *
	 * @param idPostulacion the id postulacion
	 */
	public void aprobarPostulante(Integer idPostulacion) {
		Integer idTema = this.mostrarPostulacionModel.getIdTema();

		System.out.println("Aprobar postulacion: " + idPostulacion);
		System.out.println("Aprobar tema: " + idTema);

		if (postulacionServices.aprobarPostulante(idPostulacion, idTema)) {

		} else {

		}
	}

	/**
	 * Gets the mostrar postulacion model.
	 *
	 * @return the mostrar postulacion model
	 */
	public MostrarPostulacionModel getMostrarPostulacionModel() {
		return mostrarPostulacionModel;
	}

	/**
	 * Sets the mostrar postulacion model.
	 *
	 * @param mostrarPostulacionModel the new mostrar postulacion model
	 */
	public void setMostrarPostulacionModel(MostrarPostulacionModel mostrarPostulacionModel) {
		this.mostrarPostulacionModel = mostrarPostulacionModel;
	}

}
