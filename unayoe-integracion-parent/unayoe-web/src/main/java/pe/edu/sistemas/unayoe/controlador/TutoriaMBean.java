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

@Controller("tutoriaMBean")
@ViewScoped
public class TutoriaMBean {

	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private TutoriaServices tutoriaServices;
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private TutoriaModel tutoriaModel;

	// atributos para la seleccion del combo
	private String nombre;
	private String descripcion;
	private boolean select;
	private TutoriaModel tutoriaModelSelect;
	private List<TutoriaModel> listTutoria;
	private List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria;
	private List<TutoriaBO> listVisualizarAsistenciaTutoria;
	private List<AsistenciaTProfBO> listAsistenciaDocenteTutoria;
	private Date date;
	private String usuarioLogueado;
	private FormateadorFecha fechaFormateada;

	public TutoriaMBean() {
		System.out.println("::::: LOADING ::::::::");
		tutoriaModelSelect = new TutoriaModel();
		listTutoria = new ArrayList<TutoriaModel>();
		listAsistenciaAlumnosTutoria = new ArrayList<AsistenciaTAlumBO>();
		listVisualizarAsistenciaTutoria = new ArrayList<TutoriaBO>();
		new Convertidor();
		fechaFormateada = new FormateadorFecha();
		date = new Date();
	}

	// metodos que domina de la pantallas
	public void onRowSelect(SelectEvent event) {
		System.out.println("entra a la seleccion");
		nombre = tutoriaModelSelect.getNombres();
		descripcion = tutoriaModelSelect.getDescripcion();
		select = false;
		listarCursos();
	}

	public String visualizarHorarioDeTutoria() {
		System.out.println("visualizarHorarioDeTutoria");
		listarCursos();

		return "/paginas/visualizar/visualizarHorariosDeTutoriaDocente.xhtml";

	}

	public String visualizarHorarioDeTutoriaSemanal() {
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

	public String imprimir() {
		System.out.println("imprimir");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

	public String cancelar() {
		System.out.println("cancelar");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

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

	public String registrarAsistenciaTutoriaTutor() {
		System.out.println("[TutoriaMBean]:registrarAsistenciaTutoriaAlumnos");
		listarCursos();
		return "/paginas/admin/registrar/registrarAsistenciaTutoriaTutor.xhtml";
	}

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

	// metodos get y set
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public TutoriaModel getTutoriaModel() {
		return tutoriaModel;
	}

	public void setTutoriaModel(TutoriaModel tutoriaModel) {
		this.tutoriaModel = tutoriaModel;
	}

	public TutoriaModel getTutoriaModelSelect() {
		return tutoriaModelSelect;
	}

	public void setTutoriaModelSelect(TutoriaModel tutoriaModelSelect) {
		this.tutoriaModelSelect = tutoriaModelSelect;
	}

	public List<TutoriaModel> getListTutoria() {
		return listTutoria;
	}

	public void setListTutoria(List<TutoriaModel> listTutoria) {
		this.listTutoria = listTutoria;
	}

	public String getNombre() {
		return nombre;
	}

	public List<AsistenciaTAlumBO> getListAsistenciaAlumnosTutoria() {
		return listAsistenciaAlumnosTutoria;
	}

	public void setListAsistenciaAlumnosTutoria(
			List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}

	public String getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(String usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public List<TutoriaBO> getListVisualizarAsistenciaTutoria() {
		return listVisualizarAsistenciaTutoria;
	}

	public List<AsistenciaTProfBO> getListAsistenciaDocenteTutoria() {
		return listAsistenciaDocenteTutoria;
	}

	public void setListAsistenciaDocenteTutoria(
			List<AsistenciaTProfBO> listAsistenciaDocenteTutoria) {
		this.listAsistenciaDocenteTutoria = listAsistenciaDocenteTutoria;
	}

	public void setListVisualizarAsistenciaTutoria(
			List<TutoriaBO> listVisualizarAsistenciaTutoria) {
		this.listVisualizarAsistenciaTutoria = listVisualizarAsistenciaTutoria;
	}
}
