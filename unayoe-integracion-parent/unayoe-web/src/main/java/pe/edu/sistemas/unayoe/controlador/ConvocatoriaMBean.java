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

@Controller("convocatoriaMBean")
@ViewScoped
public class ConvocatoriaMBean {

	@Autowired
	private ConvocatoriaModel convocatoriaModel;

	@Autowired
	private PeriodoServices periodoServices;

	@Autowired
	private ConvocatoriaServices convocatoriaServices;

	@Autowired
	private CursoServices cursoServices;

	@Autowired
	private TemaServices temaServices;

	public ConvocatoriaMBean() {
		this.inicializar();
	}

	private void inicializar() {
		this.convocatoriaModel = null;
	}

	public void infoMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail));
	}

	public void errorMessage(String title, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail));
	}

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

	public void cancelarConvocatoria(Integer id) {
		if (convocatoriaServices.deleteConvocatoria(id)) {
			infoMessage("Cancelar Convocatoria", "La convocatoria se ha cancelado correctamente.");
		} else {
			infoMessage("Error", "Ha ocurrido un error al cancelar la convocatoria.");
		}
	}
	
	public void limpiarConvocatoria() {
		this.convocatoriaModel.inicializar();
	}

	public List<PeriodoBO> getListaPeriodos() {
		return this.periodoServices.getPeriodos();
	}

	public String getPeriodoActual() {
		PeriodoBO periodo = this.periodoServices.getPeriodoActual();
		if (periodo != null) {
			this.convocatoriaModel.setIdPeriodo(periodo.getId());
			return periodo.getDescripcion();
		}
		return null;
	}

	public List<ConvocatoriaBO> getListaConvocatorias() {
		return this.convocatoriaServices.getConvocatorias();
	}

	public List<CursoBO> getListaCursos() {
		try {
			return this.cursoServices.listarCursos();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return null;
	}

	public List<TemaBO> getListaTemas() {
		String codigoCurso = this.convocatoriaModel.getCodigoCurso();
		if (codigoCurso != "" && codigoCurso != null) {
			return this.temaServices.getTemas(codigoCurso);
		}
		return null;
	}

	public ConvocatoriaModel getConvocatoriaModel() {
		return convocatoriaModel;
	}

	public void setConvocatoriaModel(ConvocatoriaModel convocatoriaModel) {
		this.convocatoriaModel = convocatoriaModel;
	}

}
