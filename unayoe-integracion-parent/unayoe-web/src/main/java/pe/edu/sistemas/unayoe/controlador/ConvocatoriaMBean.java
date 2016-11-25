package pe.edu.sistemas.unayoe.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.ConvocatoriaModel;
import pe.edu.sistemas.unayoe.services.ConvocatoriaServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.PeriodoServices;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class ConvocatoriaMBean.
 */
@Controller("convocatoriaMBean")
@ViewScoped
public class ConvocatoriaMBean {

	/** The convocatoria model. */
	@Autowired
	private ConvocatoriaModel convocatoriaModel;

	/** The periodo services. */
	@Autowired
	private PeriodoServices periodoServices;

	/** The convocatoria services. */
	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The tema services. */
	@Autowired
	private TemaServices temaServices;

	/**
	 * Instantiates a new convocatoria M bean.
	 */
	public ConvocatoriaMBean() {
		this.inicializar();
	}

	/**
	 * Inicializar.
	 */
	private void inicializar() {
		this.convocatoriaModel = null;
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
	 * Iniciar convocatoria.
	 */
	public void iniciarConvocatoria() {
		String nombre = this.convocatoriaModel.getNombre();
		if (nombre == null || nombre.trim().compareTo("") == 0) {
			this.errorMessage("Error", "Ingrese el nombre de la convocatoria.");
			return;
		}

		Date fechaInicio = this.convocatoriaModel.getFechaInicio();
		if (fechaInicio == null) {
			this.errorMessage("Error", "Seleccione una fecha de inicio.");
			return;
		}

		Date fechaFinal = this.convocatoriaModel.getFechaFinal();
		if (fechaFinal == null) {
			this.errorMessage("Error", "Seleccione una fecha final.");
			return;
		}

		if (fechaInicio.compareTo(fechaFinal) >= 0) {
			this.errorMessage("Error", "La fecha de inicio no puede ser mayor o igual a la fecha final.");
			return;
		}

		Integer idPeriodo = this.convocatoriaModel.getIdPeriodo();
		if (idPeriodo == null) {
			this.errorMessage("Error", "No se ha definido el periodo.");
			return;
		}

		// obtener todos los ids de los temas seleccionados
		List<Integer> idTemas = new ArrayList<Integer>();
		Map<String, List<Integer>> mapTemas = this.convocatoriaModel.getTemasCurso();
		for (Map.Entry<String, List<Integer>> entry : mapTemas.entrySet()) {
			List<Integer> temas = entry.getValue();
			if (temas != null && !temas.isEmpty()) {
				idTemas.addAll(temas);
			}
		}

		if (idTemas.isEmpty()) {
			this.errorMessage("Error", "No ha seleccionado ningun tema de tutoría.");
			return;
		}

		ConvocatoriaBO convocatoria = new ConvocatoriaBO();
		convocatoria.setNombre(nombre);
		convocatoria.setFechaFinal(fechaFinal);
		convocatoria.setFechaInicio(fechaInicio);
		convocatoria.setIdPeriodo(idPeriodo);
		convocatoria.setIdTemas(idTemas);

		// registrar la convocatoria
		if (convocatoriaServices.createConvocatoria(convocatoria)) {
			this.infoMessage("Iniciar Convocatoria", "La convocatoria se ha registrado correctamente.");
			this.limpiarConvocatoria();
		} else {
			this.errorMessage("Error", "Ha ocurrido un error al registrar la convocatoria.");
		}
	}

	/**
	 * Cancelar convocatoria.
	 *
	 * @param id the id
	 */
	public void cancelarConvocatoria(Integer id) {
		if (convocatoriaServices.deleteConvocatoria(id)) {
			infoMessage("Cancelar Convocatoria", "La convocatoria se ha cancelado correctamente.");
		} else {
			infoMessage("Error", "Ha ocurrido un error al cancelar la convocatoria.");
		}
	}
	
	/**
	 * Limpiar convocatoria.
	 */
	public void limpiarConvocatoria() {
		this.convocatoriaModel.inicializar();
	}

	/**
	 * Gets the lista periodos.
	 *
	 * @return the lista periodos
	 */
	public List<PeriodoBO> getListaPeriodos() {
		return this.periodoServices.getPeriodos();
	}

	/**
	 * Gets the periodo actual.
	 *
	 * @return the periodo actual
	 */
	public String getPeriodoActual() {
		PeriodoBO periodo = this.periodoServices.getPeriodoActual();
		if (periodo != null) {
			this.convocatoriaModel.setIdPeriodo(periodo.getId());
			return periodo.getDescripcion();
		}
		return null;
	}

	/**
	 * Gets the lista convocatorias.
	 *
	 * @return the lista convocatorias
	 */
	public List<ConvocatoriaBO> getListaConvocatorias() {
		return this.convocatoriaServices.getConvocatorias();
	}

	/**
	 * Gets the lista cursos.
	 *
	 * @return the lista cursos
	 */
	public List<CursoBO> getListaCursos() {
		try {
			return this.cursoServices.listarCursos();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the lista temas.
	 *
	 * @return the lista temas
	 */
	public List<TemaBO> getListaTemas() {
		String codigoCurso = this.convocatoriaModel.getCodigoCurso();
		if (codigoCurso != "" && codigoCurso != null) {
			return this.temaServices.getTemas(codigoCurso);
		}
		return null;
	}

	/**
	 * Gets the convocatoria model.
	 *
	 * @return the convocatoria model
	 */
	public ConvocatoriaModel getConvocatoriaModel() {
		return convocatoriaModel;
	}

	/**
	 * Sets the convocatoria model.
	 *
	 * @param convocatoriaModel the new convocatoria model
	 */
	public void setConvocatoriaModel(ConvocatoriaModel convocatoriaModel) {
		this.convocatoriaModel = convocatoriaModel;
	}

}
