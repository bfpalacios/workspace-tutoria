package pe.edu.sistemas.unayoe.controlador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.TemaModel;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.TemaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class TemaMBean.
 */
@Controller("temaMBean")
@ViewScoped
public class TemaMBean {

	/** The tema model. */
	@Autowired
	private TemaModel temaModel;

	/** The tema services. */
	@Autowired
	private TemaServices temaServices;

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The codigo curso seleccionado. */
	private String codigoCursoSeleccionado;

	/**
	 * Instantiates a new tema M bean.
	 */
	public TemaMBean() {
		this.codigoCursoSeleccionado = null;
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
	 * Crear tema.
	 */
	public void crearTema() {
		String nombre = this.getTemaModel().getNombre();
		if (nombre == null || nombre.compareTo("") == 0) {
			this.errorMessage("Error", "Ingrese el nombre del tema.");
			return;
		}

		String descripcion = this.getTemaModel().getDescripcion();
		if (descripcion == null || descripcion.compareTo("") == 0) {
			this.errorMessage("Error", "Ingrese la descripcion del tema.");
			return;
		}

		String codigoCurso = this.getTemaModel().getCodigoCurso();
		if (codigoCurso == null || codigoCurso.compareTo("") == 0) {
			this.errorMessage("Error", "Seleccione un curso.");
			return;
		}

		TemaBO tema = new TemaBO();
		tema.setCodigoCurso(codigoCurso);
		tema.setDescripcion(descripcion);
		tema.setNombre(nombre);

		if (this.temaServices.createTema(tema)) {
			this.infoMessage("Tema Creado", "El tema se ha creado correctamente.");
			this.limpiarTema();
		} else {
			this.errorMessage("Error", "Ha ocurrido un error al crear el tema.");
		}
	}
	
	/**
	 * Limpiar tema.
	 */
	public void limpiarTema() {
		this.temaModel.inicializar();
	}

	/**
	 * Gets the lista temas.
	 *
	 * @return the lista temas
	 */
	public List<TemaBO> getListaTemas() {
		if (this.codigoCursoSeleccionado != "" && this.codigoCursoSeleccionado != null) {
			return temaServices.getTemas(this.codigoCursoSeleccionado);
		}
		return null;
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
			return null;
		}
	}

	/**
	 * Eliminar tema.
	 *
	 * @param id the id
	 */
	public void eliminarTema(Integer id) {
		if (this.temaServices.deleteTema(id)) {
			this.infoMessage("Tema Eliminado", "El tema se ha eliminado correctamente.");
		} else {
			this.errorMessage("Error", "Ha ocurrido un error al eliminar el tema.");
		}
	}

	/**
	 * Gets the tema model.
	 *
	 * @return the tema model
	 */
	public TemaModel getTemaModel() {
		return temaModel;
	}

	/**
	 * Sets the tema model.
	 *
	 * @param temaModel the new tema model
	 */
	public void setTemaModel(TemaModel temaModel) {
		this.temaModel = temaModel;
	}

	/**
	 * Gets the codigo curso seleccionado.
	 *
	 * @return the codigo curso seleccionado
	 */
	public String getCodigoCursoSeleccionado() {
		return codigoCursoSeleccionado;
	}

	/**
	 * Sets the codigo curso seleccionado.
	 *
	 * @param codigoCursoSeleccionado the new codigo curso seleccionado
	 */
	public void setCodigoCursoSeleccionado(String codigoCursoSeleccionado) {
		this.codigoCursoSeleccionado = codigoCursoSeleccionado;
	}

}
