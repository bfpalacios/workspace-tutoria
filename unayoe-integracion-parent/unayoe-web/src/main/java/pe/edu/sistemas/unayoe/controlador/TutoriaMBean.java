package pe.edu.sistemas.unayoe.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;  

import javax.faces.bean.ViewScoped;  

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;  

import pe.edu.sistemas.unayoe.core.util.Convertidor;
import pe.edu.sistemas.unayoe.core.util.FormateadorFecha;
import pe.edu.sistemas.unayoe.model.TutoriaModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaMBean.
 */
@Controller("tutoriaMBean")
@ViewScoped
public class TutoriaMBean {

	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;
	
	/** The tutoria services. */
	@Autowired
	private TutoriaServices tutoriaServices;
	
	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The tutoria model. */
	@Autowired
	private TutoriaModel tutoriaModel;

	/** The nombre. */
	// atributos para la seleccion del combo
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The select. */
	private boolean select;
	
	/** The tutoria model select. */
	private TutoriaModel tutoriaModelSelect;
	
	/** The list tutoria. */
	private List<TutoriaModel> listTutoria;
	
	/** The list asistencia alumnos tutoria. */
	private List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria;
	
	/** The list visualizar asistencia tutoria. */
	private List<TutoriaBO> listVisualizarAsistenciaTutoria;
	
	/** The list asistencia docente tutoria. */
	private List<AsistenciaTProfBO> listAsistenciaDocenteTutoria;
	
	/** The date. */
	private Date date;
	
	/** The usuario logueado. */
	private String usuarioLogueado;
	
	/** The fecha formateada. */
	private FormateadorFecha fechaFormateada;

	/**
	 * Instantiates a new tutoria M bean.
	 */
	public TutoriaMBean() {
		System.out.println("::::: TUTORIA MBEAN ::::::::");
		tutoriaModelSelect = new TutoriaModel();
		listTutoria = new ArrayList<TutoriaModel>();
		listAsistenciaAlumnosTutoria = new ArrayList<AsistenciaTAlumBO>();
		listVisualizarAsistenciaTutoria = new ArrayList<TutoriaBO>();
		new Convertidor();
		fechaFormateada = new FormateadorFecha();
		date = new Date();
	}

	/**
	 * On row select.
	 *
	 * @param event the event
	 */
	// metodos que domina de la pantallas
	public void onRowSelect(SelectEvent event) {
		System.out.println("entra a la seleccion");
		nombre = tutoriaModelSelect.getNombres();
		descripcion = tutoriaModelSelect.getDescripcion();
		select = false;
		listarCursos();
	}

	/**
	 * Visualizar horario de tutoria.
	 *
	 * @return the string
	 */
	public String visualizarHorarioDeTutoria() {
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();

		return "/paginas/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}

	/**
	 * Visualizar horario de tutoria semanal.
	 *
	 * @return the string
	 */
	public String visualizarHorarioDeTutoriaSemanal() {
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

	/**
	 * Imprimir.
	 *
	 * @return the string
	 */
	public String imprimir() {
		System.out.println("imprimir");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		System.out.println("cancelar");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

	/**
	 * Listar cursos.
	 */
	public void listarCursos() {
		System.out.println("Listando los cursos:");

		List<CursoBO> listarCursos = null;
		try {
			listarCursos = cursoServices.listarCursos();

			tutoriaModel.setListarCursos(listarCursos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Buscar asistencia docentes tutoria.
	 */
	public void buscarAsistenciaDocentesTutoria() {
		System.out.println("::::::: buscarAsistenciaDocentesTutoria:::::::::");
		System.out.println(":::::: Fecha " + getDate());
		System.out.println("::::::: Codigo " + tutoriaModelSelect.getCodigo());

		// antes de enviar la fecha la formateadmo a dd/MM/yyyy
		Date fechaAsistencia = getDate();
		
		String fecha = fechaFormateada.formatoFechaDDMMAAAA(fechaAsistencia);
		String codigoCurso = tutoriaModelSelect.getCodigo();

		// se llama por el service a la conexion de base de datos
		try {
			listAsistenciaDocenteTutoria = tutoriaServices
					.buscarAsistenciaDeProfesorTutoria(fecha, codigoCurso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public String guardarAsistenciaTutoriaTutor() {

		System.out.println("guardarListaAsistencia");
		// antes de enviar la fecha la formateadmo a dd/MM/yyyy
		Date fechaAsistencia = getDate();
		FormateadorFecha fechaFormateada = new FormateadorFecha();

		String fecha = fechaFormateada.formatoFechaDDMMAAAA(fechaAsistencia);
		String codigoCurso = tutoriaModelSelect.getCodigo();

		try {
			tutoriaServices.actualizarListaAsistenciaTutoriaTutor(getListAsistenciaDocenteTutoria(), codigoCurso, fecha);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/paginas/ingresarTutoriaProfesor.xhtml";

	}*/

	/**
	 * Registrar asistencia tutoria tutor.
	 *
	 * @return the string
	 */
	public String registrarAsistenciaTutoriaTutor() {
		System.out.println("[TutoriaMBean]:registrarAsistenciaTutoriaAlumnos");
		listarCursos();
		return "/paginas/admin/registrar/registrarAsistenciaTutoriaTutor.xhtml";
	}

	/**
	 * Registrar asistencia tutoria tutor user.
	 *
	 * @return the string
	 */
	public String registrarAsistenciaTutoriaTutorUser() {
		System.out.println("ingresarTutoriaProfesor");
		// validacion de roles - ROL_USER. ROL_ADMIN
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println("::::::::::::::: ENTRO ACA ::::::::::::::::");

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			String name = user.getUsername();
			System.out.println("USER INGRESADO" + name);

		}
		return "/paginas/user/registrar/registrarAsistenciaTutoriaTutor.xhtml";

	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	// metodos get y set
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Checks if is select.
	 *
	 * @return true, if is select
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * Sets the select.
	 *
	 * @param select the new select
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}

	/**
	 * Gets the tutoria model.
	 *
	 * @return the tutoria model
	 */
	public TutoriaModel getTutoriaModel() {
		return tutoriaModel;
	}

	/**
	 * Sets the tutoria model.
	 *
	 * @param tutoriaModel the new tutoria model
	 */
	public void setTutoriaModel(TutoriaModel tutoriaModel) {
		this.tutoriaModel = tutoriaModel;
	}

	/**
	 * Gets the tutoria model select.
	 *
	 * @return the tutoria model select
	 */
	public TutoriaModel getTutoriaModelSelect() {
		return tutoriaModelSelect;
	}

	/**
	 * Sets the tutoria model select.
	 *
	 * @param tutoriaModelSelect the new tutoria model select
	 */
	public void setTutoriaModelSelect(TutoriaModel tutoriaModelSelect) {
		this.tutoriaModelSelect = tutoriaModelSelect;
	}

	/**
	 * Gets the list tutoria.
	 *
	 * @return the list tutoria
	 */
	public List<TutoriaModel> getListTutoria() {
		return listTutoria;
	}

	/**
	 * Sets the list tutoria.
	 *
	 * @param listTutoria the new list tutoria
	 */
	public void setListTutoria(List<TutoriaModel> listTutoria) {
		this.listTutoria = listTutoria;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Gets the list asistencia alumnos tutoria.
	 *
	 * @return the list asistencia alumnos tutoria
	 */
	public List<AsistenciaTAlumBO> getListAsistenciaAlumnosTutoria() {
		return listAsistenciaAlumnosTutoria;
	}

	/**
	 * Sets the list asistencia alumnos tutoria.
	 *
	 * @param listAsistenciaAlumnosTutoria the new list asistencia alumnos tutoria
	 */
	public void setListAsistenciaAlumnosTutoria(
			List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}

	/**
	 * Gets the usuario logueado.
	 *
	 * @return the usuario logueado
	 */
	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	/**
	 * Sets the usuario logueado.
	 *
	 * @param usuarioLogueado the new usuario logueado
	 */
	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	/**
	 * Gets the list visualizar asistencia tutoria.
	 *
	 * @return the list visualizar asistencia tutoria
	 */
	public List<TutoriaBO> getListVisualizarAsistenciaTutoria() {
		return listVisualizarAsistenciaTutoria;
	}

	/**
	 * Gets the list asistencia docente tutoria.
	 *
	 * @return the list asistencia docente tutoria
	 */
	public List<AsistenciaTProfBO> getListAsistenciaDocenteTutoria() {
		return listAsistenciaDocenteTutoria;
	}

	/**
	 * Sets the list asistencia docente tutoria.
	 *
	 * @param listAsistenciaDocenteTutoria the new list asistencia docente tutoria
	 */
	public void setListAsistenciaDocenteTutoria(
			List<AsistenciaTProfBO> listAsistenciaDocenteTutoria) {
		this.listAsistenciaDocenteTutoria = listAsistenciaDocenteTutoria;
	}

	/**
	 * Sets the list visualizar asistencia tutoria.
	 *
	 * @param listVisualizarAsistenciaTutoria the new list visualizar asistencia tutoria
	 */
	public void setListVisualizarAsistenciaTutoria(
			List<TutoriaBO> listVisualizarAsistenciaTutoria) {
		this.listVisualizarAsistenciaTutoria = listVisualizarAsistenciaTutoria;
	}
}
