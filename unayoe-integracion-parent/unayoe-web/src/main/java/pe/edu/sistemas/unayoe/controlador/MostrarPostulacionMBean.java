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

@Controller("mostrarPostulacionMBean")
@ViewScoped
public class MostrarPostulacionMBean implements Serializable {

	private static final long serialVersionUID = -1094056852928420585L;

	@Autowired
	private MostrarPostulacionModel mostrarPostulacionModel;

	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	@Autowired
	private CursoServices cursoServices;

	@Autowired
	private PostulacionServices postulacionServices;

	@Autowired
	private TemaServices temaServices;

	public MostrarPostulacionMBean() {

	}

	public String getConvocatoriaActual() {
		ConvocatoriaBO convocatoria = convocatoriaServices.getConvocatoriaActual();
		if (convocatoria == null) {
			return null;
		}
		this.mostrarPostulacionModel.setIdConvocatoria(convocatoria.getId());
		return convocatoria.getNombre();
	}

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

	public List<CursoBO> getListaCursos() {
		Integer idConvocatoria = this.mostrarPostulacionModel.getIdConvocatoria();
		if (idConvocatoria == null) {
			return null;
		}
		return this.cursoServices.listarCursosPorConvocatoria(idConvocatoria);
	}

	public List<TemaBO> getListaTemas() {
		Integer idConvocatoria = this.mostrarPostulacionModel.getIdConvocatoria();
		String codigoCurso = this.mostrarPostulacionModel.getCodigoCurso();
		if (idConvocatoria == null || codigoCurso == null) {
			return null;
		}
		// return this.temaServices.getTemasPorConvocatoria(idConvocatoria);
		return this.temaServices.getTemasPorConvocatoriaCurso(idConvocatoria, codigoCurso);
	}

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

	public void aprobarPostulante(Integer idPostulacion) {
		Integer idTema = this.mostrarPostulacionModel.getIdTema();

		System.out.println("Aprobar postulacion: " + idPostulacion);
		System.out.println("Aprobar tema: " + idTema);

		if (postulacionServices.aprobarPostulante(idPostulacion, idTema)) {

		} else {

		}
	}

	public MostrarPostulacionModel getMostrarPostulacionModel() {
		return mostrarPostulacionModel;
	}

	public void setMostrarPostulacionModel(MostrarPostulacionModel mostrarPostulacionModel) {
		this.mostrarPostulacionModel = mostrarPostulacionModel;
	}

}
