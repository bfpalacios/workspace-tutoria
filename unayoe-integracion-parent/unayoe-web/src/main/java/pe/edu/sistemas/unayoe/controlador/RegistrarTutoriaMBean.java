package pe.edu.sistemas.unayoe.controlador;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import controladorReporte.Asistencia;
import controladorReporte.ControladorReporte;
import controladorReporte.HorarioTutoria;
import controladorReporte.Tarea;
import pe.edu.sistemas.unayoe.core.util.FormateadorFecha;
import pe.edu.sistemas.unayoe.model.AsistenciaTutoriaModel;
import pe.edu.sistemas.unayoe.model.ClaseMaestraModel;
import pe.edu.sistemas.unayoe.model.TutoriaModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.MatriculaServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ObservacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrarTutoriaMBean.
 */
@Controller("registrarTutoriaMBean")
@ViewScoped
public class RegistrarTutoriaMBean {
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;
	
	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;
	
	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The usuario services. */
	@Autowired
	private UsuarioServices usuarioServices;
	
	/** The tutoria services. */
	@Autowired
	private TutoriaServices tutoriaServices;
	
	/** The clase maestra model. */
	@Autowired
	private ClaseMaestraModel claseMaestraModel;
	
	/** The matricula services. */
	@Autowired
	private MatriculaServices matriculaServices;
	
	/** The asistencia tutoria model. */
	@Autowired
	private AsistenciaTutoriaModel asistenciaTutoriaModel;

	/** The hoy. */
	Calendar hoy;
	
	/** The date. */
	private Date date;
	
	/** The dia. */
	private String dia;
	
	/** The tutoria model. */
	private TutoriaModel tutoriaModel; 
 /** The tutoria model grid. */
 /*
										 * visualizar las tutorias en el reporte
										 * de asistencia
										 */
	private TutoriaModel tutoriaModelGrid;
	
	/** The tutoria model select. */
	private TutoriaModel tutoriaModelSelect;
	
	/** The listar ciclos. */
	private List<ClaseMaestra> listarCiclos;
	
	/** The tutoria model select grid. */
	private TutoriaModel tutoriaModelSelectGrid;
	
	/** The clase maestra frecuencia. */
	private ClaseMaestraModel claseMaestraFrecuencia;
	
	/** The clase maestra model select. */
	private ClaseMaestraModel claseMaestraModelSelect;
	
	/** The clase maestra frecuencia select. */
	private ClaseMaestraModel claseMaestraFrecuenciaSelect;
	
	/** The lista observaciones pendientes. */
	private List<ObservacionBO> listaObservacionesPendientes;
	
	/** The lista observaciones finalizadas. */
	private List<ObservacionBO> listaObservacionesFinalizadas;
	
	/** The list asistencia tutoria. */
	private List<AsistenciaTutoriaModel> listAsistenciaTutoria;
	
	/** The list asistencia tutoria docente. */
	private List<AsistenciaTutoriaModel> listAsistenciaTutoriaDocente;
	
	/** The asistencia tutoria model select. */
	private AsistenciaTutoriaModel asistenciaTutoriaModelSelect;

	/** The modo usuario. */
	private int MODO_USUARIO;
	
	/** The modo usuario aux. */
	private int MODO_USUARIO_AUX;
	
	/** The modo admin. */
	private static int MODO_ADMIN = 1;
	
	/** The modo ocaa. */
	private static int MODO_OCAA = 2;
	
	/** The modo dir aca. */
	private static int MODO_DIR_ACA = 3;
	
	/** The modo unayoe. */
	private static int MODO_UNAYOE = 4;
	
	/** The modo tutor. */
	private static int MODO_TUTOR = 5;
	
	/** The modo decano. */
	private static int MODO_DECANO = 6;

	/** The proceso. */
	private int PROCESO;
	
	/** The proceso observados. */
	private static int PROCESO_OBSERVADOS = 1;
	
	/** The proceso regulares. */
	private static int PROCESO_REGULARES = 2;

	/** The registro faltante. */
	private static String REGISTRO_FALTANTE = "F";

	/** The nombre origen. */
	private String nombreOrigen = "Nombre Tutor";
	
	/** The curso buscado. */
	private String cursoBuscado = "";
	
	/** The profesor buscado. */
	private String profesorBuscado = "";

	/**
	 * Instantiates a new registrar tutoria M bean.
	 */
	public RegistrarTutoriaMBean() {
		hoy = new GregorianCalendar();
		System.out.println("::::: REGISTRAR TUTORIA ::::::::");
		asistenciaTutoriaModelSelect = new AsistenciaTutoriaModel();
		listAsistenciaTutoria = new ArrayList<AsistenciaTutoriaModel>();
		listAsistenciaTutoriaDocente = new ArrayList<AsistenciaTutoriaModel>();
		inicializarClases();
	}

	/**
	 * Inicializar clases.
	 */
	private void inicializarClases() {
		setTutoriaModel(new TutoriaModel());
		setTutoriaModelGrid(new TutoriaModel());
		setTutoriaModelSelect(new TutoriaModel());
		setTutoriaModelSelectGrid(new TutoriaModel());
		setListarCiclos(new ArrayList<ClaseMaestra>());
		setClaseMaestraFrecuencia(new ClaseMaestraModel());
		setClaseMaestraModelSelect(new ClaseMaestraModel());
		setClaseMaestraFrecuenciaSelect(new ClaseMaestraModel());
		setListaObservacionesPendientes(new ArrayList<ObservacionBO>());
		setListaObservacionesFinalizadas(new ArrayList<ObservacionBO>());
		setListAsistenciaTutoria(new ArrayList<AsistenciaTutoriaModel>());
		setListAsistenciaTutoriaDocente(new ArrayList<AsistenciaTutoriaModel>());

		nombreOrigen = "Nombre Tutor";
		cursoBuscado = "";
		profesorBuscado = "";
	}

	/**
	 * Limpiar clases.
	 */
	private void limpiarClases() {
		if (getTutoriaModel() != null) {
			setTutoriaModel(null);
			setTutoriaModel(new TutoriaModel());
		}

		if (getTutoriaModelSelect() != null) {
			setTutoriaModelSelect(null);
			setTutoriaModelSelect(new TutoriaModel());
		}
	}

	/**
	 * Listar ciclo todos.
	 *
	 * @throws Exception the exception
	 */
	public void listarCicloTodos() throws Exception {
		System.out.println("Listando los ciclos:");

		List<ClaseMaestra> listarCiclo = null;
		try {
			String tabla = "CICLO";
			String campo = "CICLO_TODOS";
			listarCiclo = alumnoServices.listarCiclo(tabla, campo);
			setListarCiclos(listarCiclo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar ciclo.
	 *
	 * @throws Exception the exception
	 */
	public void listarCiclo() throws Exception {
		List<ClaseMaestra> listarCiclo = null;
		try {
			String tabla = "CICLO";
			String campo = "CICLO_TODOS";
			listarCiclo = alumnoServices.listarCiclo(tabla, campo);
			setListarCiclos(listarCiclo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar frecuencia.
	 *
	 * @throws Exception the exception
	 */
	public void listarFrecuencia() throws Exception {
		List<ClaseMaestra> listaFrecuencias = null;
		try {
			String tabla = "FRECUENCIA";
			String campo = "FRECUENCIA_TUTORIA";
			listaFrecuencias = comunServices.listarClaseMaestra(tabla, campo);
			claseMaestraFrecuencia.setListaClaseMaestra(listaFrecuencias);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar cursos.
	 */
	public void listarCursos() {
		List<CursoBO> listarCursos = null;
		try {
			// listarCursos = cursoServices.listarCursos();
			listarCursos = cursoServices.listarCursosTutorias();

			/*
			 * Falta crear un procedure para listar los cursos que pertenecen a
			 * una tutoria
			 * 
			 * 
			 */
			asistenciaTutoriaModelSelect.setListarCursos(listarCursos);
			asistenciaTutoriaModel.setListarCursos(listarCursos);
			getTutoriaModel().setListarCursos(listarCursos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar profesores.
	 */
	public void listarProfesores() {
		List<ProfesorBO> listarProfesores = null;
		try {
			listarProfesores = tutoriaServices.listarProfesores();

			System.out.println("lista " + listarProfesores);
			System.out.println("tamaño de la lista " + listarProfesores.size());
			ProfesorBO profe = listarProfesores.get(0);

			System.out.println("Profesor de nombre " + profe.getpNombre());
			System.out.println("Profesor de codig " + profe.getpCodigo());

			asistenciaTutoriaModel.setListarProfesores(listarProfesores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar hora inicio.
	 */
	public void listarHoraInicio() {
		System.out.println("Listando los inicios de tutoría:");

		List<ClaseMaestra> listaHoraInicio = null;
		try {
			String tabla = "HORA";
			String campo = "HORA_INICIO";
			listaHoraInicio = tutoriaServices.listarHoraInicio(tabla, campo);
			getTutoriaModel().setListaHoraInicio(listaHoraInicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar preguntas encuesta.
	 */
	public void listarPreguntasEncuesta() {
		String tipoPregunta = "HABITOS_ESTUDIO";
		try {
			List<EncuestaBO> listaPreguntas = tutoriaServices.listarPreguntas(tipoPregunta);
			getTutoriaModel().setListaEncuestas(listaPreguntas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar cursosx docente regular.
	 *
	 * @throws Exception the exception
	 */
	public void listarCursosxDocenteRegular() throws Exception {
		String codDocente = obtenerUsuario().getUsername();
		List<CursoBO> cursos = cursoServices.listarCursosxDocenteRegular(codDocente);
		getTutoriaModel().setListarCursos(cursos);
	}

	/**
	 * Listar calificacion.
	 *
	 * @return the list
	 */
	private List<ClaseMaestra> listarCalificacion() {
		List<ClaseMaestra> listaCalificaciones = null;
		try {
			String tabla = "ENCUESTA";
			String campo = "CALIFICACION";
			listaCalificaciones = comunServices.listarClaseMaestra(tabla, campo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCalificaciones;
	}

	/**
	 * Listar cursosx docente.
	 *
	 * @throws Exception the exception
	 */
	public void listarCursosxDocente() throws Exception {
		String codDocente = "";
		if (MODO_USUARIO == MODO_ADMIN) {
			codDocente = getTutoriaModelSelect().getpCodigo() == null ? "" : getTutoriaModelSelect().getpCodigo();
		} else {
			if (MODO_USUARIO == MODO_TUTOR) {
				codDocente = obtenerUsuario().getUsername();
				getTutoriaModelSelect().setpCodigo(usuarioServices.buscarUsuarioEquivalencia(codDocente));
			}
		}
		List<CursoBO> listaCursos = cursoServices.listarCursosDocente(codDocente, PROCESO, MODO_USUARIO);
		getTutoriaModel().setListarCursos(listaCursos);
	}

	/**
	 * Actualizar docente.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarDocente(ValueChangeEvent e) throws Exception {
		String codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		List<ProfesorBO> listaTutores = new ArrayList<ProfesorBO>();
		listaTutores = tutoriaServices.listarTutoresRegulares(codCurso);
		getTutoriaModel().setListarTutores(listaTutores);
	}

	/**
	 * Actualizar hora fin.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarHoraFin(ValueChangeEvent e) throws Exception {
		int idHoraInicio = Integer.parseInt((String) (e.getNewValue() == null ? "0" : e.getNewValue()));
		List<ClaseMaestra> listaHoraFin = new ArrayList<ClaseMaestra>();
		;
		listaHoraFin = tutoriaServices.actualizarHoraFin(idHoraInicio);
		getTutoriaModel().setListaHoraFin(listaHoraFin);
	}

	/**
	 * Actualizar alumno.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarAlumno(ValueChangeEvent e) throws Exception {
		String codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		String codCurso = getTutoriaModelSelect().getcCodigo() == null ? "" : getTutoriaModelSelect().getcCodigo();
		List<AlumnoBO> listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, codCurso, 2, 1);
		getTutoriaModel().setListarAlumnos(listaAlumnos);
	}

	/**
	 * Actualizar alumno generico.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarAlumnoGenerico(ValueChangeEvent e) throws Exception {
		String codDocente = "";
		String codCurso = "";
		if (MODO_USUARIO == MODO_ADMIN) {
			codCurso = getTutoriaModelSelect().getcCodigo() == null ? "" : getTutoriaModelSelect().getcCodigo();
			codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		} else {
			if (MODO_USUARIO == MODO_TUTOR) {
				codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
				codDocente = obtenerUsuario().getUsername();
			}
		}
		List<AlumnoBO> listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, codCurso, PROCESO, MODO_USUARIO);
		getTutoriaModel().setListarAlumnos(listaAlumnos);
	}

	/**
	 * Actualizar alumno regular.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarAlumnoRegular(ValueChangeEvent e) throws Exception {
		String codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		List<AlumnoBO> listaAlumnos = tutoriaServices.listarAlumnosRegularesxCurso(codCurso);
		getTutoriaModel().setListarAlumnos(listaAlumnos);
	}

	/**
	 * Actualizar docente generico.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarDocenteGenerico(ValueChangeEvent e) throws Exception {

		String codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		List<ProfesorBO> listaTutores = new ArrayList<ProfesorBO>();

		switch (PROCESO) {
		case 1:
			listaTutores = tutoriaServices.listarTutoresObservados(codCurso);
			break;
		case 2:
			listaTutores = tutoriaServices.listarTutoresRegulares(codCurso);
			break;

		}
		getTutoriaModel().setListarTutores(listaTutores);
	}

	/**
	 * Obtener usuario.
	 *
	 * @return the user
	 */
	public User obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usuario = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			usuario = (User) auth.getPrincipal();
		}
		return usuario;
	}

	/**
	 * Diferencia fechas en dias.
	 *
	 * @param fecha1 the fecha 1
	 * @param fecha2 the fecha 2
	 * @return the long
	 */
	public static long diferenciaFechasEnDias(String fecha1, String fecha2) {
		long diasDiferencia = 0;
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		try {
			SimpleDateFormat formatoFechaInicial = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFechaInicial.setLenient(false);
			Date fechaInicial = formatoFechaInicial.parse(fecha2);

			SimpleDateFormat formatoFechaFinal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFechaFinal.setLenient(false);
			Date fechaFinal = formatoFechaFinal.parse(fecha1);

			diasDiferencia = (fechaFinal.getTime() - fechaInicial.getTime()) / MILLSECS_PER_DAY;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diasDiferencia;
	}

	/**
	 * Buscar datos alumno.
	 *
	 * @return the string
	 */
	public String buscarDatosAlumno() {
		String pagina = "";
		try {
			String codAlumno = getTutoriaModelSelect().getaCodigo();

			if (validaNumero(codAlumno)) {
				AlumnoBO alumno = tutoriaServices.buscarDatosAlumnoRegular(codAlumno);

				if (!alumno.getaCodigo().equals("")) {
					String nombreAlumno = alumno.getaNombre();
					getTutoriaModelSelect().setaNombre(nombreAlumno);

					List<CursoBO> listaCursos = tutoriaServices.listarCursosxAlumnoRegular(alumno.getaCodigo());

					if (tutoriaServices.listarCursosxAlumnoRegular(codAlumno).size() > 0) {
						getTutoriaModel().setListarCursos(listaCursos);
					}
				} else {
					getTutoriaModelSelect().setaNombre("Alumno no encontrado");
				}
			} else {
				mostrarMensaje(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (MODO_USUARIO) {
		case 1:
			pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDatosDeTutoria.xhtml";
			break;
		case 2:
			pagina = "/paginas/ModuloRegulares/ocaa/registrar/registrarDatosDeTutoria.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Buscar encuesta inicial.
	 *
	 * @return the string
	 */
	public String buscarEncuestaInicial() {
		String pagina = "";

		try {
			String codDocente = "";
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();
			if (MODO_USUARIO == MODO_UNAYOE) {
				codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
						: getTutoriaModelSelect().getpCodigo();
			}

			if (validarCamposPrincipalesTutoria(codCurso, codDocente, codAlumno)) {
				List<TutoriaBO> tutoria = tutoriaServices.buscarTutoriaxCodigoAlumnoRegular(codAlumno);
				List<EncuestaBO> listaEncuestas = tutoriaServices.buscarDatosEncuesta(tutoria.get(0).gettCodigo());
				List<EncuestaBO> listaEncuestasGrid = new ArrayList<EncuestaBO>();
				if (listaEncuestas.size() == 0) {
					mostrarMensaje(15);
				} else {
					List<ClaseMaestra> listaCalificaciones = listarCalificacion();
					for (EncuestaBO encuesta : listaEncuestas) {
						switch (MODO_USUARIO) {
						case 4:
							if (encuesta.getCalificacionTutor().equals("")) {
								encuesta.setCalificacionTutor("No registrada");
							}
							break;
						case 5:
							if (encuesta.getCalificacionPsicologa().equals("")) {
								encuesta.setCalificacionPsicologa("No registrada");
							}
							break;
						}
						getTutoriaModelSelectGrid().setaCodigo(codAlumno);
						getTutoriaModelSelectGrid().setCodTutoria(tutoria.get(0).gettCodigo());
						encuesta.setListaCalificacion(listaCalificaciones);
						listaEncuestasGrid.add(encuesta);
					}
					getTutoriaModel().setListaEncuestas(listaEncuestasGrid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(12);
		}
		switch (MODO_USUARIO) {
		case 4:
			pagina = "/paginas/ModuloRegulares/unayoe/registrar/registrarCalificacionEncuesta.xhtml";
		case 5:
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarCalificacionEncuesta.xhtml";
		}
		return pagina;
	}

	/**
	 * Buscar tareas tutoria.
	 */
	public void buscarTareasTutoria() {

		try {
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getpCodigo();
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();

			if (validarCamposPrincipalesTutoria(codCurso, codDocente, codAlumno)) {
				List<ObservacionBO> listaObservacionesTotales = tutoriaServices.listarObservaciones(codCurso,
						codDocente, codAlumno, PROCESO);
				if (listaObservacionesTotales.size() > 0) {
					List<ObservacionBO> listaObservacionesPendientes = new ArrayList<ObservacionBO>();
					List<ObservacionBO> listaObservacionesFinalizadas = new ArrayList<ObservacionBO>();
					List<ClaseMaestra> listaEstadosTotales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_TOTALES");
					List<ClaseMaestra> listaEstadosParciales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_PARCIALES");
					for (ObservacionBO observacion : listaObservacionesTotales) {
						switch (observacion.getEstadoObservacion()) {
						case 1:
							observacion.setListaEstados(listaEstadosTotales);
							// observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(observacion.getCodTutoria(),
							// Integer.parseInt(observacion.getSesionRegistro())));
							listaObservacionesPendientes.add(observacion);
							break;
						case 2:
							// observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(observacion.getCodTutoria(),
							// Integer.parseInt(observacion.getSesionRegistro())));
							System.out.println("CASO 2 gg");
							observacion.setListaEstados(listaEstadosParciales);
							listaObservacionesPendientes.add(observacion);
							break;
						case 3: // observacion.setDiasCierre(String.valueOf(diferenciaFechasEnDias(observacion.getFechaCierre(),
								// observacion.getFechaRegistro())));
								// observacion.setSesionesCierre(String.valueOf((Integer.parseInt(observacion.getSesionCierre())
								// -
								// Integer.parseInt(observacion.getSesionRegistro()))));
							System.out.println("CASO 3 gg");
							listaObservacionesFinalizadas.add(observacion);
							break;
						}
					}
					getTutoriaModel().setListaObservacionesPendientes(listaObservacionesPendientes);
					getTutoriaModel().setListaObservacionesFinalizadas(listaObservacionesFinalizadas);
				} else {
					mostrarMensaje(17);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Guardar control tareas.
	 */
	public void guardarControlTareas() {

		try {
			if (getTutoriaModel().getListaObservacionesPendientes().size() == 0) {

			} else {
				int indicadorEstado;
				List<ObservacionBO> listaObservacionesPendientes = getTutoriaModel().getListaObservacionesPendientes();
				for (ObservacionBO observacion : listaObservacionesPendientes) {
					if (observacion.getEstadoControl().equals("PENDIENTE")) {
						observacion.setObservacionCierre("");
						indicadorEstado = 1;
					} else {
						if (observacion.getEstadoControl().equals("PARCIALMENTE LEVANTADO")) {
							indicadorEstado = 2;
						} else {
							indicadorEstado = 3;
						}

					}
					observacion.setFecha_entrega(new FormateadorFecha().formatoFechaDDMMAAAA(new Date()));
					tutoriaServices.actualizarEstadoObservacion(observacion, indicadorEstado);
				}
				limpiarClases();
				listarCursos();
				mostrarMensaje(18);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Guardar control tareas tutor.
	 */
	public void guardarControlTareasTutor() {

		try {
			if (getTutoriaModel().getListaObservacionesPendientes().size() == 0) {

			} else {
				int indicadorEstado;
				List<ObservacionBO> listaObservacionesPendientes = getTutoriaModel().getListaObservacionesPendientes();
				for (ObservacionBO observacion : listaObservacionesPendientes) {
					if (observacion.getEstadoControl().equals("PENDIENTE")) {
						observacion.setObservacionCierre("");
						indicadorEstado = 1;
					} else {
						if (observacion.getEstadoControl().equals("PARCIALMENTE LEVANTADO")) {
							indicadorEstado = 2;
						} else {
							indicadorEstado = 3;
						}

					}
					observacion.setFecha_entrega(new FormateadorFecha().formatoFechaDDMMAAAA(new Date()));
					tutoriaServices.actualizarEstadoObservacion(observacion, indicadorEstado);
				}
				limpiarClases();
				listarCursosxDocente();
				mostrarMensaje(18);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Buscar tareas tutoria profesor.
	 */
	public void buscarTareasTutoriaProfesor() {

		try {
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getpCodigo();
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();
			System.out.println("DOCENTE : " + codDocente);
			if (validarCamposPrincipalesTutoria(codCurso, codDocente, codAlumno)) {
				List<ObservacionBO> listaObservacionesTotales = tutoriaServices.listarObservaciones(codCurso,
						codDocente, codAlumno, PROCESO);
				if (listaObservacionesTotales.size() > 0) {
					List<ObservacionBO> listaObservacionesPendientes = new ArrayList<ObservacionBO>();
					List<ObservacionBO> listaObservacionesFinalizadas = new ArrayList<ObservacionBO>();
					List<ClaseMaestra> listaEstadosTotales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_TOTALES");
					List<ClaseMaestra> listaEstadosParciales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_PARCIALES");
					for (ObservacionBO observacion : listaObservacionesTotales) {
						switch (observacion.getEstadoObservacion()) {
						case 1:
							Long diasTerminacion2 = diferenciaFechasEnDias(observacion.getFecha_cumplimiento(),
									new FormateadorFecha().formatoFechaDDMMAAAA(new Date()));
							if (diasTerminacion2 < 0) {
								observacion.setFecha_entrega("No cumplió");
								listaObservacionesFinalizadas.add(observacion);
							} else {
								observacion.setListaEstados(listaEstadosTotales);
								// observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(observacion.getCodTutoria(),
								// Integer.parseInt(observacion.getSesionRegistro())));
								listaObservacionesPendientes.add(observacion);
							}

							break;
						case 2:
							// observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(observacion.getCodTutoria(),
							// Integer.parseInt(observacion.getSesionRegistro())));
							System.out.println("CASO 2 gg");
							Long diasTerminacion = diferenciaFechasEnDias(observacion.getFecha_cumplimiento(),
									new FormateadorFecha().formatoFechaDDMMAAAA(new Date()));
							if (diasTerminacion < 0) {
								listaObservacionesFinalizadas.add(observacion);
							} else {
								observacion.setListaEstados(listaEstadosParciales);
								listaObservacionesPendientes.add(observacion);
							}
							break;
						case 3: // observacion.setDiasCierre(String.valueOf(diferenciaFechasEnDias(observacion.getFechaCierre(),
								// observacion.getFechaRegistro())));
								// observacion.setSesionesCierre(String.valueOf((Integer.parseInt(observacion.getSesionCierre())
								// -
								// Integer.parseInt(observacion.getSesionRegistro()))));
							System.out.println("CASO 3 gg");
							listaObservacionesFinalizadas.add(observacion);
							break;
						}
					}
					getTutoriaModel().setListaObservacionesPendientes(listaObservacionesPendientes);
					getTutoriaModel().setListaObservacionesFinalizadas(listaObservacionesFinalizadas);
				} else {
					mostrarMensaje(17);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Estado tarea.
	 *
	 * @param estadoObservacion the estado observacion
	 * @return the string
	 */
	public String estadoTarea(int estadoObservacion) {
		if (estadoObservacion == 1) {
			return "PENDIENTE";
		} else {
			if (estadoObservacion == 2)
				return "PARCIALMENTE LAVANTADO";
			else
				return "CERRADO";
		}

	}

	/**
	 * Buscar observaciones tutoria.
	 *
	 * @return the string
	 */
	public String buscarObservacionesTutoria() {
		String pagina = "";

		try {
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getpCodigo();
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();

			if (validarCamposPrincipalesTutoria(codCurso, codDocente, codAlumno)) {
				List<ObservacionBO> listaObservacionesTotales = tutoriaServices.listarObservaciones(codCurso,
						codDocente, codAlumno, PROCESO);
				if (listaObservacionesTotales.size() > 0) {
					List<ObservacionBO> listaObservacionesPendientes = new ArrayList<ObservacionBO>();
					List<ObservacionBO> listaObservacionesFinalizadas = new ArrayList<ObservacionBO>();
					List<ClaseMaestra> listaEstadosTotales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_TOTALES");
					List<ClaseMaestra> listaEstadosParciales = comunServices.listarClaseMaestra("ESTADOS_OBSERVACION",
							"ESTADOS_PARCIALES");
					for (ObservacionBO observacion : listaObservacionesTotales) {
						switch (observacion.getEstadoObservacion()) {
						case 1:
							observacion.setListaEstados(listaEstadosTotales);
							observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(
									observacion.getCodTutoria(), Integer.parseInt(observacion.getSesionRegistro())));
							listaObservacionesPendientes.add(observacion);
							break;
						case 2:
							observacion.setListaSesionesCierre(tutoriaServices.listarSesionesCierre(
									observacion.getCodTutoria(), Integer.parseInt(observacion.getSesionRegistro())));
							observacion.setListaEstados(listaEstadosParciales);
							listaObservacionesPendientes.add(observacion);
							break;
						case 3:
							observacion
									.setDiasCierre(String.valueOf(diferenciaFechasEnDias(observacion.getFechaCierre(),
											observacion.getFechaRegistro())));
							observacion
									.setSesionesCierre(String.valueOf((Integer.parseInt(observacion.getSesionCierre())
											- Integer.parseInt(observacion.getSesionRegistro()))));
							listaObservacionesFinalizadas.add(observacion);
							break;
						}
					}
					getTutoriaModel().setListaObservacionesPendientes(listaObservacionesPendientes);
					getTutoriaModel().setListaObservacionesFinalizadas(listaObservacionesFinalizadas);
				} else {
					mostrarMensaje(17);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (PROCESO) {
		case 1:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		case 2:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Guardar control observaciones.
	 *
	 * @return the string
	 */
	public String guardarControlObservaciones() {
		String pagina = "";

		try {
			if (getTutoriaModel().getListaObservacionesPendientes().size() == 0) {

			} else {
				int indicadorEstado;
				List<ObservacionBO> listaObservacionesPendientes = getTutoriaModel().getListaObservacionesPendientes();
				for (ObservacionBO observacion : listaObservacionesPendientes) {
					if (observacion.getEstadoControl().equals("PENDIENTE")
							|| observacion.getEstadoControl().equals("PARCIALMENTE LEVANTADO")) {
						observacion.setObservacionCierre("");
						indicadorEstado = 1;
					} else {
						indicadorEstado = 2;
					}
					observacion.setFechaCierre(new FormateadorFecha().formatoFechaDDMMAAAA(new Date()));
					tutoriaServices.actualizarEstadoObservacion(observacion, indicadorEstado);
				}
				limpiarClases();
				listarCursosxDocente();
				mostrarMensaje(18);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (PROCESO) {
		case 1:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		case 2:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Valida numero.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 */
	public boolean validaNumero(String valor) {
		boolean esNumerico = false;
		try {
			Integer.parseInt(valor);
			esNumerico = true;
		} catch (NumberFormatException nfe) {
			esNumerico = false;
		}
		return esNumerico;
	}

	/**
	 * Asistencia model.
	 *
	 * @param tutoriaBO the tutoria BO
	 * @return the asistencia tutoria model
	 */
	public AsistenciaTutoriaModel asistenciaModel(TutoriaBO tutoriaBO) {
		AsistenciaTutoriaModel horario = new AsistenciaTutoriaModel();
		horario.setC_codigo(tutoriaBO.getcCodigo());
		horario.setC_nombre(tutoriaBO.getcNombre());
		horario.setA_codigo(tutoriaBO.getaCodigo());
		horario.setA_nombre(tutoriaBO.getaNombre());
		horario.setP_codigo(tutoriaBO.getpCodigo());
		horario.setP_nombre(tutoriaBO.getpNombre());
		horario.setRepitencia(tutoriaBO.getRepitencias());
		horario.setDia(tutoriaBO.getDia());
		horario.setHoraIni(tutoriaBO.getHoraIni());
		horario.setHoraFin(tutoriaBO.getHoraFin());
		horario.setDescFrecuencia(tutoriaBO.getDesc_frecuencia());
		horario.settPeriodo(tutoriaBO.gettPeriodo());
		horario.settAnio(tutoriaBO.gettAnio());
		horario.setNum_sesiones(tutoriaBO.getNum_sesiones());
		horario.setNum_asistencia_asistio(tutoriaBO.getNum_asistencia_asistio());
		horario.setNum_asistencia_falto(tutoriaBO.getNum_asistencia_falto());
		horario.setNum_asistencia_tardanza(tutoriaBO.getNum_asistencia_tardanza());
		horario.setNum_tareas_cerrado(tutoriaBO.getNum_tareas_cerrado());
		horario.setNum_tareas_parcialmente(tutoriaBO.getNum_tareas_parcialmente());
		horario.setNum_tareas_pendiente(tutoriaBO.getNum_tareas_pendiente());
		horario.setNum_actas(tutoriaBO.getNum_actas());

		return horario;
	}

	/**
	 * Buscar horarios tutoria.
	 *
	 * @param procesoTutoria the proceso tutoria
	 */
	public void buscarHorariosTutoria(int procesoTutoria) {

		List<TutoriaBO> listaHorarios = new ArrayList<TutoriaBO>();
		listAsistenciaTutoria.clear();
		cursoBuscado = "";
		profesorBuscado = "";

		if (!validaNumero(getTutoriaModelSelect().getaCodigo())) {
			mostrarMensaje(1);
		} else {
			try {
				setNombreOrigen("Nombre Tutor");
				CicloBO cicloActual = comunServices.buscarCicloActual();
				listaHorarios = tutoriaServices.listarHorariosDeTutoria(cicloActual.getAnio(), cicloActual.getPeriodo(),
						getTutoriaModelSelect().getaCodigo(), obtenerUsuario().getUsername(), procesoTutoria);

				if (listaHorarios.size() > 0) {
					for (TutoriaBO tutoria : listaHorarios) {
						listAsistenciaTutoria.add(asistenciaModel(tutoria));
					}

					AlumnoBO datosAlumno = tutoriaServices.buscarDatosAlumno(listaHorarios.get(0).getaCodigo());
					getTutoriaModelSelect().setaNombre(datosAlumno.getaNombre() + " " + datosAlumno.getaApellido());
					getTutoriaModelSelect().setcCodigo(listaHorarios.get(0).getcCodigo());

					getTutoriaModelSelect().setpCodigo(listaHorarios.get(0).getpCodigo());

				} else {
					getTutoriaModelSelect().setaNombre("Alumno no encontrado");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Buscar horarios tutoria docente.
	 *
	 * @param procesoTutoria the proceso tutoria
	 */
	/* ver tutoria docente */
	public void buscarHorariosTutoriaDocente(int procesoTutoria) {
		listAsistenciaTutoriaDocente.clear();
		List<TutoriaBO> listaHorarios = new ArrayList<TutoriaBO>();

		setNombreOrigen("Nombre Alumno");

		try {
			CicloBO cicloActual = comunServices.buscarCicloActual();
			listaHorarios = tutoriaServices.listarHorariosDeTutoriaProfesor(cicloActual.getAnio(),
					cicloActual.getPeriodo(), getTutoriaModelSelect().getpCodigo());

			if (listaHorarios.size() > 0) {
				for (TutoriaBO tutoria : listaHorarios) {
					listAsistenciaTutoriaDocente.add(asistenciaModel(tutoria));
				}

				setCursoBuscado(listAsistenciaTutoriaDocente.get(0).getC_nombre());
				setProfesorBuscado(listAsistenciaTutoriaDocente.get(0).getA_nombre());
				getTutoriaModelSelect().setaCodigo("");
				getTutoriaModelSelect().setaNombre("");

			} else {
				getTutoriaModelSelect().setaNombre("Profesor no encontrado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Buscar horarios tutoria semanal.
	 */
	/* fin ver tutoria docente */
	public void buscarHorariosTutoriaSemanal() {
		listarProfesores();
		List<TutoriaBO> listaHorarios = new ArrayList<TutoriaBO>();

		listAsistenciaTutoria.clear();

		try {
			CicloBO ciclo = comunServices.buscarCicloActual();
			listaHorarios = tutoriaServices.listarHorariosDeTutoriaxSemana(ciclo.getAnio(), ciclo.getPeriodo(),
					asistenciaTutoriaModelSelect.getC_codigo(),
					asistenciaTutoriaModelSelect.getDia() == "" ? "T" : asistenciaTutoriaModelSelect.getDia());
			if (listaHorarios.size() > 0) {
				for (TutoriaBO tutoria : listaHorarios) {
					listAsistenciaTutoria.add(asistenciaModel(tutoria));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Guardar.
	 *
	 * @return the string
	 */
	public String guardar() {
		String pagina = "";
		try {
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getpCodigo();
			String dia = getTutoriaModelSelect().getDia() == "" ? "Invalido" : getTutoriaModelSelect().getDia();
			String horaInicio = getTutoriaModelSelect().getHoraInicio() == "" ? "Invalido"
					: getTutoriaModelSelect().getHoraInicio();
			String horaFin = getTutoriaModelSelect().getHoraFin() == "" ? "Invalido"
					: getTutoriaModelSelect().getHoraFin();
			System.out.println("antes del if ");
			if (validarCamposTutoria(codAlumno, codCurso, codDocente, dia, horaInicio, horaFin)) {
				System.out.println("antes de ciclo service");
				CicloBO ciclo = comunServices.buscarCicloActual();
				
				
				String tutoriaExistente = tutoriaServices.buscarTutoria(ciclo.getAnio(), ciclo.getPeriodo(), codCurso,
						codAlumno, codDocente);
				TutoriaBO tutoriabo = new TutoriaBO();

				tutoriabo.settAnio(String.valueOf(ciclo.getAnio()));
				tutoriabo.settPeriodo(String.valueOf(ciclo.getPeriodo()));
				tutoriabo.setcCodigo(codCurso);
				tutoriabo.setpCodigo(codDocente);
				tutoriabo.setDia(dia);
				tutoriabo.setHoraIni(horaInicio + ":00");
				tutoriabo.setHoraFin(horaFin + ":00");
				tutoriabo.setFrecuencia(getTutoriaModelSelect().getFrecuencia());
				System.out.println("VALIDA CAMPOS TUTORIA");
				if (tutoriaExistente.isEmpty()) {
					System.out.println("Tutoria no existente");
					tutoriabo.setaCodigo(codAlumno);
					tutoriaServices.procesarTutoriaRegulares(tutoriabo, 1);
					mostrarMensaje(7);

				} else {
					System.out.println("Tutoria existente **");
					tutoriabo.settCodigo(tutoriaExistente);
					tutoriaServices.procesarTutoriaRegulares(tutoriabo, 2);
					mostrarMensaje(8);

				}
				limpiarClases();
			} else {
				System.out.println("NO VALIDA CAMPOS DE TUTORIA");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(9);
		}
		switch (MODO_USUARIO) {
		case 1:
			pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDatosDeTutoria.xhtml";
			break;
		case 2:
			pagina = "/paginas/ModuloRegulares/ocaa/registrar/registrarDatosDeTutoria.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Buscar datos asistencia tutoria.
	 *
	 * @return the string
	 */
	public String buscarDatosAsistenciaTutoria() {
		String pagina = "";

		try {
			String codDocente = "";
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();

			switch (MODO_USUARIO) {
			case 1:
				codDocente = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
						: getTutoriaModelSelect().getpCodigo();
				break;
			case 5:
				codDocente = obtenerUsuario().getUsername();
			}

			if (validarCamposPrincipalesTutoria(codCurso, codDocente, codAlumno)) {
				List<TutoriaBO> listaDatosTutoria = tutoriaServices.listarDatosTutoria(codCurso, codDocente, codAlumno,
						PROCESO, MODO_USUARIO);

				if (listaDatosTutoria.size() > 0) {
					List<TutoriaBO> listaCronograma = new ArrayList<TutoriaBO>();
					listaCronograma.add(listaDatosTutoria.get(0));
					for (TutoriaBO tutoria : listaDatosTutoria) {
						tutoria.setValidacionAsistencia(
								tutoria.getEstadoSesion().equals(REGISTRO_FALTANTE) ? "No" : "Si");
						tutoria.setValidacionCargaActa(tutoria.getEstadoActa() > 0 ? "Si" : "No");
						tutoria.setValidacionObservacion(
								tutoriaServices.verificarExistenciaObservacion(tutoria.gettCodigo(),
										tutoria.getSesion()) > 0 ? "Si" : "No");
					}
					getTutoriaModel().setListaTutorias(listaDatosTutoria);
					getTutoriaModelGrid().setListaTutorias(listaCronograma);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (PROCESO) {
		case 1:
			switch (MODO_USUARIO_AUX) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 4:
				pagina = "/paginas/ModuloObservados/tutor/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			}
			break;
		case 2:
			switch (MODO_USUARIO_AUX) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloRegulares/diraca/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 4:
				pagina = "/paginas/ModuloRegulares/tutor/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Validar campos tutoria.
	 *
	 * @param codAlumno the cod alumno
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param dia the dia
	 * @param horaInicio the hora inicio
	 * @param horaFin the hora fin
	 * @return true, if successful
	 */
	private boolean validarCamposTutoria(String codAlumno, String codCurso, String codDocente, String dia,
			String horaInicio, String horaFin) {
		boolean valido = true;

		if (codAlumno.equals("Invalido")) {
			mostrarMensaje(1);
			valido = false;
		}

		if (codCurso.equals("Invalido")) {
			mostrarMensaje(2);
			valido = false;
		}

		if (codDocente.equals("Invalido")) {
			mostrarMensaje(3);
			valido = false;
		}

		if (dia.equals("Invalido")) {
			mostrarMensaje(4);
			valido = false;
		}

		if (horaInicio.equals("Invalido")) {
			mostrarMensaje(5);
			valido = false;
		}

		if (horaFin.equals("Invalido")) {
			mostrarMensaje(6);
			valido = false;
		}
		return valido;
	}

	/**
	 * Validar campos principales tutoria.
	 *
	 * @param codCurso the cod curso
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @return true, if successful
	 */
	private boolean validarCamposPrincipalesTutoria(String codCurso, String codDocente, String codAlumno) {
		boolean valido = true;

		if (codCurso.equals("Invalido")) {
			mostrarMensaje(2);
			valido = false;
		}

		if (codDocente.equals("Invalido")) {
			mostrarMensaje(3);
			valido = false;
		}

		if (codAlumno.equals("Invalido")) {
			mostrarMensaje(14);
			valido = false;
		}
		return valido;
	}

	/**
	 * Guardar encuesta.
	 *
	 * @return the string
	 */
	public String guardarEncuesta() {
		try {
			String codAlumno = obtenerUsuario().getUsername();
			List<TutoriaBO> tutoria = tutoriaServices.buscarTutoriaxCodigoAlumno(codAlumno, 2);

			if (tutoria.size() == 0) {
				mostrarMensaje(10);
			} else {
				String tutoriaEncuesta = tutoriaServices.buscarTutoriaEncuesta(tutoria.get(0).gettCodigo());
				if (!tutoriaEncuesta.equals("")) {
					mostrarMensaje(13);
				} else {
					List<EncuestaBO> listaEncuestas = getTutoriaModel().getListaEncuestas();
					for (EncuestaBO encuesta : listaEncuestas) {
						tutoriaServices.guardarEncuestaInicial(encuesta, tutoria.get(0).gettCodigo(), 1);
					}
					mostrarMensaje(11);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(12);
		}
		return "/paginas/ModuloRegulares/alumno/registrar/registrarEncuestaInicial.xhtml";
	}

	/**
	 * Calificar encuesta.
	 *
	 * @return the string
	 */
	public String calificarEncuesta() {
		String pagina = "";

		try {
			String codTutoria = getTutoriaModelSelectGrid().getCodTutoria();
			List<EncuestaBO> listaEncuestas = getTutoriaModel().getListaEncuestas();
			for (EncuestaBO encuesta : listaEncuestas) {
				switch (MODO_USUARIO) {
				case 4:
					encuesta.setCodPsicologa(obtenerUsuario().getUsername());
					tutoriaServices.guardarEncuestaInicial(encuesta, codTutoria, 3);
					break;
				case 5:
					tutoriaServices.guardarEncuestaInicial(encuesta, codTutoria, 2);
					break;
				}
			}
			mostrarMensaje(16);
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(12);
		}
		switch (MODO_USUARIO) {
		case 4:
			pagina = "/paginas/ModuloRegulares/unayoe/registrar/registrarCalificacionEncuesta.xhtml";
		case 5:
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarCalificacionEncuesta.xhtml";
		}
		return pagina;
	}

	/**
	 * Buscar indicadores.
	 */
	public void buscarIndicadores() {
		try {
			List<IndicadoresBO> listaIndicadores = new ArrayList<IndicadoresBO>();
			for (int i = 1; i < 11; i++) {
				IndicadoresBO indicador = new IndicadoresBO();
				indicador = comunServices.buscarIndicador(i);
				listaIndicadores.add(indicador);
			}
			getTutoriaModel().setListaIndicadores(listaIndicadores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param opcionMensaje the opcion mensaje
	 */
	private void mostrarMensaje(int opcionMensaje) {
		FacesMessage message = null;
		switch (opcionMensaje) {
		case 1:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El código del alumno debe ser numérico");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un curso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un tutor");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un día");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una hora de inicio");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una hora de fin");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La tutoría se registró correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La tutoría se actualizó correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Hubo un error al procesar la tutoría");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 10:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"Ud todavía no ha sido registrado(a) en una tutoría");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 11:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Encuesta inicial guardada satisfactoriamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 12:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Hubo un error al procesar la encuesta");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 13:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "La encuesta ya ha sido registrada");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 14:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un alumno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 15:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"El alumno todavía no ha registrado la encuesta inicial");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 16:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"La calificación de la encuesta se ha guardado correctamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 17:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"No se encontraron observaciones con los datos ingresados");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 18:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Las observaciones de tutoría se procesaron correctamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 19:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un alumno");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 20:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un docente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		}
	}

	/**
	 * Selector paginas registro tutoria.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param modo the modo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorPaginasRegistroTutoria(int procesoTutoria, int modo) throws Exception {
		String pagina = "";
		inicializarClases();
		listarFrecuencia();
		listarHoraInicio();
		
		List<CursoBO> lsCursos = cursoServices.listarCursos();
		getTutoriaModel().setListarCursos(lsCursos);
		
		List<ProfesorBO> lsProfesoresRegulares = tutoriaServices.listarTutoresRegulares();

		
		switch (procesoTutoria) {
		case 1:
			switch (modo) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				getTutoriaModel().setListarTutores(lsProfesoresRegulares);
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDatosDeTutoria.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				getTutoriaModel().setListarTutores(lsProfesoresRegulares);
				pagina = "/paginas/ModuloRegulares/ocaa/registrar/registrarDatosDeTutoria.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				getTutoriaModel().setListarTutores(lsProfesoresRegulares);
				pagina = "/paginas/ModuloRegulares/diraca/registrar/registrarDatosDeTutoria.xhtml";
				break;
			}
			break;
		case 2:
			switch (modo) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarDatosDeTutoria.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector paginas consulta tutoria.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorPaginasConsultaTutoria(int procesoTutoria, int tipoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		switch (procesoTutoria) {
		case 1:
			PROCESO = PROCESO_OBSERVADOS;
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloObservados/unayoe/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 5:
				MODO_USUARIO = MODO_TUTOR;
				pagina = "/paginas/ModuloObservados/tutor/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			}
			break;
		case 2:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				pagina = "/paginas/ModuloRegulares/admin/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloRegulares/diraca/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloRegulares/unayoe/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 5:
				MODO_USUARIO = MODO_TUTOR;
				pagina = "/paginas/ModuloRegulares/tutor/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector paginas reportes.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorPaginasReportes(int procesoTutoria, int tipoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		switch (procesoTutoria) {
		case 1:
			PROCESO = PROCESO_OBSERVADOS;
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/reportes/verReporteAlumnos.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloObservados/unayoe/verHorariosTutoriaAlumno.xhtml";
				break;
			case 5:
				MODO_USUARIO = MODO_TUTOR;
				pagina = "/paginas/ModuloObservados/tutor/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			}
			break;
		case 2:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/reportes/verReporteTutores.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloRegulares/diraca/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloRegulares/unayoe/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			case 5:
				MODO_USUARIO = MODO_TUTOR;
				pagina = "/paginas/ModuloRegulares/tutor/visualizar/verHorariosTutoriaAlumno.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector paginas consulta tutoria semanal.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorPaginasConsultaTutoriaSemanal(int procesoTutoria, int tipoUsuario) throws Exception {
		String pagina = "";
		listarCursos();
		switch (procesoTutoria) {
		case 1:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				pagina = "/paginas/ModuloObservados/admin/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloObservados/diraca/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloObservados/unayoe/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			}
			break;
		case 2:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_OCAA;
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_DIR_ACA;
				pagina = "/paginas/ModuloRegulares/diraca/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_UNAYOE;
				pagina = "/paginas/ModuloRegulares/unayoe/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector registro encuesta.
	 *
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorRegistroEncuesta(int tipoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		switch (tipoUsuario) {
		case 1:
			listarPreguntasEncuesta();
			pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarEncuestaInicial.xhtml";
			break;
		case 2:
			MODO_USUARIO = MODO_TUTOR;
			listarCursosxDocenteRegular();
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarCalificacionEncuesta.xhtml";
			break;
		case 3:
			MODO_USUARIO = MODO_UNAYOE;
			listarCursos();
			pagina = "/paginas/ModuloRegulares/unayoe/registrar/registrarCalificacionEncuesta.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Selector control observaciones.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorControlObservaciones(int procesoTutoria, int tipoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		switch (procesoTutoria) {
		case 1:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				PROCESO = PROCESO_OBSERVADOS;
				listarCursosxDocente();
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_TUTOR;
				PROCESO = PROCESO_OBSERVADOS;
				listarCursosxDocente();
				pagina = "/paginas/ModuloObservados/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		case 2:
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				PROCESO = PROCESO_REGULARES;
				listarCursosxDocente();
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarControlObservaciones.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_TUTOR;
				PROCESO = PROCESO_REGULARES;
				listarCursosxDocente();
				pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarControlObservaciones.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector visualizacion datos tutoria.
	 *
	 * @param procesoTutoria the proceso tutoria
	 * @param tipoUsuario the tipo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorVisualizacionDatosTutoria(int procesoTutoria, int tipoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		switch (procesoTutoria) {
		case 1:
			PROCESO = PROCESO_OBSERVADOS;
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_OCAA;
				listarCursos();
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_DIR_ACA;
				listarCursos();
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_TUTOR;
				MODO_USUARIO_AUX = MODO_TUTOR;
				listarCursosxDocente();
				pagina = "/paginas/ModuloObservados/tutor/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
				
			case 5:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/visualizar/verHistorialTareasAlumno.xhtml";
				break;
			case 6:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_DECANO;
				listarCursos();
				pagina = "/paginas/ModuloObservados/decano/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			}
			break;
		case 2:
			PROCESO = PROCESO_REGULARES;
			switch (tipoUsuario) {
			case 1:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloRegulares/admin/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 2:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_OCAA;
				listarCursos();
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 3:
				MODO_USUARIO = MODO_ADMIN;
				MODO_USUARIO_AUX = MODO_DIR_ACA;
				listarCursos();
				pagina = "/paginas/ModuloRegulares/diraca/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			case 4:
				MODO_USUARIO = MODO_TUTOR;
				MODO_USUARIO_AUX = MODO_TUTOR;
				listarCursosxDocente();
				pagina = "/paginas/ModuloRegulares/tutor/visualizar/verAsistenciaTutoriaAlumnos.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Selector paginas indicadores.
	 *
	 * @param proceso the proceso
	 * @param modoUsuario the modo usuario
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorPaginasIndicadores(int proceso, int modoUsuario) throws Exception {
		String pagina = "";
		inicializarClases();
		buscarIndicadores();
		switch (proceso) {
		case 1:
			switch (modoUsuario) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/visualizar/verIndicadoresSistema.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/ocaa/visualizar/verIndicadoresSistema.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verIndicadoresSistema.xhtml";
				break;
			case 4:
				pagina = "/paginas/ModuloObservados/decano/visualizar/verIndicadoresSistema.xhtml";
				break;
			}
			break;
		case 2:
			switch (modoUsuario) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/visualizar/verIndicadoresSistema.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/verIndicadoresSistema.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloObservados/diraca/visualizar/verIndicadoresSistema.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Imprimir reporte horario alumno.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteHorarioAlumno() throws Exception {
		if (!listAsistenciaTutoria.isEmpty()) {
			System.out.println("Impresion de reporte de horario:");

			ControladorReporte reporte = new ControladorReporte();
			reporte.setNombreReporte("horarioTutoriaAlumno");
			reporte.generarReporteHorarioDocente(obtenerParametrosAlumno(), obtenerCamposAlumno());
			// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
			// System.out.println(horario);
			// }
		} else
			mostrarMensaje(19);
	}

	/**
	 * Imprimir reporte tareas tutoria.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteTareasTutoria() throws Exception {
		System.out.println("Impresion de reporte de horario:");

		ControladorReporte reporte = new ControladorReporte();
		reporte.setNombreReporte("reporteTareasAlumno");
		reporte.generarReporteHorarioDocente(obtenerParametrosTareas(), obtenerCamposTareas());
		// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
		// System.out.println(horario);
		// }
	}

	/**
	 * Imprimir reporte tareas tutoria tutor.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteTareasTutoriaTutor() throws Exception {
		System.out.println("Impresion de reporte de horario:");

		ControladorReporte reporte = new ControladorReporte();
		reporte.setNombreReporte("reporteTareasAlumno");
		reporte.generarReporteHorarioDocente(obtenerParametrosTareasTutor(), obtenerCamposTareas());
		// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
		// System.out.println(horario);
		// }
	}

	/**
	 * Obtener parametros tareas tutor.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	private Map<Object, Object> obtenerParametrosTareasTutor() throws Exception {
		Map<Object, Object> pars = new HashMap<Object, Object>();
		String alumno = "";
		for (int i = 0; i < this.tutoriaModel.getListarAlumnos().size(); i++) {
			if (tutoriaModel.getListarAlumnos().get(i).getaCodigo().equals(tutoriaModelSelect.getaCodigo())) {
				alumno = tutoriaModel.getListarAlumnos().get(i).getaNombre();
				i = tutoriaModel.getListarAlumnos().size();
			}
		}
		System.out.println("usuario: " + usuarioServices.buscarUsuarioEquivalencia(obtenerUsuario().getUsername()));
		String docente = "";
		List<ProfesorBO> listaProfesores = tutoriaServices.listarProfesores();
		for (int i = 0; i < listaProfesores.size(); i++) {
			if (listaProfesores.get(i).getpCodigo()
					.equals(usuarioServices.buscarUsuarioEquivalencia(obtenerUsuario().getUsername()))) {
				docente = listaProfesores.get(i).getpNombre() + " " + listaProfesores.get(i).getpApellidos();
				i = listaProfesores.size();
			}
		}

		String curso = "";
		for (int i = 0; i < this.tutoriaModel.getListarCursos().size(); i++) {
			if (tutoriaModel.getListarCursos().get(i).getcCodigo().equals(tutoriaModelSelect.getcCodigo())) {
				curso = tutoriaModel.getListarCursos().get(i).getNombre();
				i = tutoriaModel.getListarCursos().size();
			}
		}

		pars.put("curso", curso);
		pars.put("docente", docente);
		pars.put("alumno", alumno);
		return pars;
	}

	/**
	 * Obtener parametros tareas.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	private Map<Object, Object> obtenerParametrosTareas() throws Exception {
		Map<Object, Object> pars = new HashMap<Object, Object>();
		String alumno = "";
		for (int i = 0; i < this.tutoriaModel.getListarAlumnos().size(); i++) {
			if (tutoriaModel.getListarAlumnos().get(i).getaCodigo().equals(tutoriaModelSelect.getaCodigo())) {
				alumno = tutoriaModel.getListarAlumnos().get(i).getaNombre();
				i = tutoriaModel.getListarAlumnos().size();
			}
		}
		String docente = "";
		for (int i = 0; i < this.tutoriaModel.getListarTutores().size(); i++) {
			if (tutoriaModel.getListarTutores().get(i).getpCodigo().equals(tutoriaModelSelect.getpCodigo())) {
				docente = tutoriaModel.getListarTutores().get(i).getpNombre();
				i = tutoriaModel.getListarTutores().size();
			}
		}

		String curso = "";
		for (int i = 0; i < this.tutoriaModel.getListarCursos().size(); i++) {
			if (tutoriaModel.getListarCursos().get(i).getcCodigo().equals(tutoriaModelSelect.getcCodigo())) {
				curso = tutoriaModel.getListarCursos().get(i).getNombre();
				i = tutoriaModel.getListarCursos().size();
			}
		}

		pars.put("curso", curso);
		pars.put("docente", docente);
		pars.put("alumno", alumno);
		return pars;
	}

	/**
	 * Obtener campos tareas.
	 *
	 * @return the array list
	 */
	private ArrayList<Object> obtenerCamposTareas() {
		ArrayList<Object> list = new ArrayList<Object>();
		for (ObservacionBO model : tutoriaModel.getListaObservacionesFinalizadas()) {
			Tarea tarea = new Tarea();
			tarea.setSesion(model.getSesionRegistro());
			tarea.setRazon(model.getRazon());
			tarea.setTarea(model.getTarea());
			tarea.setCriticidad(model.getCriticidad());
			tarea.setFechaInicio(model.getFechaRegistro());
			tarea.setFechaFin(model.getFecha_cumplimiento());
			tarea.setFechaCumplida(model.getFecha_entrega());
			tarea.setEstado(estadoTarea(model.getEstadoObservacion()));
			list.add(tarea);
		}
		for (ObservacionBO model : tutoriaModel.getListaObservacionesPendientes()) {
			Tarea tarea = new Tarea();
			tarea.setSesion(model.getSesionRegistro());
			tarea.setRazon(model.getRazon());
			tarea.setTarea(model.getTarea());
			tarea.setCriticidad(model.getCriticidad());
			tarea.setFechaInicio(model.getFechaRegistro());
			tarea.setFechaFin(model.getFecha_cumplimiento());
			tarea.setFechaCumplida(model.getFecha_entrega());
			tarea.setEstado(model.getEstadoControl());
			list.add(tarea);
		}
		return list;
	}

	/**
	 * Imprimir reporte asistencia tutoria alumno.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteAsistenciaTutoriaAlumno() throws Exception {
		System.out.println("Impresion de reporte de horario:");

		ControladorReporte reporte = new ControladorReporte();
		reporte.setNombreReporte("reporteAsistenciaAlumno");
		reporte.generarReporteHorarioDocente(obtenerParametrosAsistenciaAlumno(), obtenerCamposAsistenciaAlumno());

	}

	/**
	 * Obtener parametros asistencia alumno.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	private Map<Object, Object> obtenerParametrosAsistenciaAlumno() throws Exception {
		Map<Object, Object> pars = new HashMap<Object, Object>();
		// String ruta_imagen="classpath:reportes/";
		// String ciclo =
		// alumnoServices.buscarCicloAcademico(getClaseMaestraModelSelect().getIdCampo());

		// File imagen = new File("classpath:reportes/Logo.png");
		// BufferedImage imagenIO = ImageIO.read(imagen);
		// BufferedImage imagen =
		// ImageIO.read(getClass().getResource("/Imagenes/Logo.png"));

		// pars.put("logo", imagen);

		String alumno = "";
		for (int i = 0; i < this.tutoriaModel.getListarAlumnos().size(); i++) {
			if (tutoriaModel.getListarAlumnos().get(i).getaCodigo().equals(tutoriaModelSelect.getaCodigo())) {
				alumno = tutoriaModel.getListarAlumnos().get(i).getaNombre();
				i = tutoriaModel.getListarAlumnos().size();
			}
		}
		String docente = "";
		for (int i = 0; i < this.tutoriaModel.getListarTutores().size(); i++) {
			if (tutoriaModel.getListarTutores().get(i).getpCodigo().equals(tutoriaModelSelect.getpCodigo())) {
				docente = tutoriaModel.getListarTutores().get(i).getpNombre();
				i = tutoriaModel.getListarTutores().size();
			}
		}

		String curso = "";
		for (int i = 0; i < this.tutoriaModel.getListarCursos().size(); i++) {
			if (tutoriaModel.getListarCursos().get(i).getcCodigo().equals(tutoriaModelSelect.getcCodigo())) {
				curso = tutoriaModel.getListarCursos().get(i).getNombre();
				i = tutoriaModel.getListarCursos().size();
			}
		}

		pars.put("curso", curso);
		pars.put("docente", docente);
		pars.put("alumno", alumno);
		pars.put("dia", tutoriaModelGrid.getListaTutorias().get(0).getDia());
		pars.put("inicio", tutoriaModelGrid.getListaTutorias().get(0).getHoraIni());
		pars.put("fin", tutoriaModelGrid.getListaTutorias().get(0).getHoraFin());
		// pars.put("ciclo", ciclo);
		// pars.put("facultad", "Ingeniería de Sistemas e Informática");
		// pars.put("escuela", "Ingeniería de Sistemas");
		// pars.put("escudounmsm", "imagenes/unmsm.gif");
		return pars;
	}

	/**
	 * Obtener campos asistencia alumno.
	 *
	 * @return the array list
	 */
	private ArrayList<Object> obtenerCamposAsistenciaAlumno() {
		ArrayList<Object> list = new ArrayList<Object>();
		System.out.println("Tamaño de la lista1 :" + getTutoriaModel().getListaTutorias().size());
		for (TutoriaBO model : getTutoriaModel().getListaTutorias()) {
			Asistencia asistenciaAlumno = new Asistencia();
			asistenciaAlumno.setSesion(Integer.toString(model.getSesion()));
			asistenciaAlumno.setFecha(model.getFecha());
			asistenciaAlumno.setAsistencia(model.getValidacionAsistencia());
			asistenciaAlumno.setActa(model.getValidacionCargaActa());
			asistenciaAlumno.setObs(model.getValidacionObservacion());
			asistenciaAlumno.setDia("");
			asistenciaAlumno.setCodCurso("");
			asistenciaAlumno.setNomCurso("");
			asistenciaAlumno.setRepitencia("");
			list.add(asistenciaAlumno);
		}
		System.out.println("Tamaño de la lista2 :" + list.size());
		return list;
	}

	/**
	 * Imprimir reporte horario docente.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteHorarioDocente() throws Exception {
		System.out.println("entra a reporte de docente");
		if (!listAsistenciaTutoriaDocente.isEmpty()) {
			System.out.println("Impresion de reporte de horario:");

			ControladorReporte reporte = new ControladorReporte();
			reporte.setNombreReporte("horarioTutoriaProfesor");
			reporte.generarReporteHorarioDocente(obtenerParametros(), obtenerCampos());
			// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
			// System.out.println(horario);
			// }
		} else
			mostrarMensaje(20);
	}

	/**
	 * Imprimir reporte general alumno.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteGeneralAlumno() throws Exception {
		System.out.println("entra a reporte de docente");
		if (!listAsistenciaTutoriaDocente.isEmpty()) {
			System.out.println("Impresion de reporte de horario:");

			ControladorReporte reporte = new ControladorReporte();
			reporte.setNombreReporte("ReporteGeneralAlumnos");
			reporte.generarReporteHorarioDocente(obtenerParametros(), obtenerCamposReporteGeneralAlumnos());
			// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
			// System.out.println(horario);
			// }
		} else
			mostrarMensaje(20);
	}

	/**
	 * Imprimir reporte general tutor.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteGeneralTutor() throws Exception {
		System.out.println("entra a reporte de docente");
		if (!listAsistenciaTutoriaDocente.isEmpty()) {
			System.out.println("Impresion de reporte de horario:");

			ControladorReporte reporte = new ControladorReporte();
			reporte.setNombreReporte("ReporteGeneralProfesores");
			reporte.generarReporteHorarioDocente(obtenerParametros(), obtenerCamposReporteGeneralAlumnos());
			// for(AsistenciaTutoriaModel horario : listAsistenciaTutoria){
			// System.out.println(horario);
			// }
		} else
			mostrarMensaje(20);
	}

	/**
	 * Obtener parametros alumno.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	private Map<Object, Object> obtenerParametrosAlumno() throws Exception {
		Map<Object, Object> pars = new HashMap<Object, Object>();
		// String ruta_imagen="classpath:reportes/";
		String ciclo = alumnoServices.buscarCicloAcademico(getClaseMaestraModelSelect().getIdCampo());

		// File imagen = new File("classpath:reportes/Logo.png");
		// BufferedImage imagenIO = ImageIO.read(imagen);
		// BufferedImage imagen =
		// ImageIO.read(getClass().getResource("/Imagenes/Logo.png"));

		// pars.put("logo", imagen);
		pars.put("nomAlu", tutoriaModelSelect.getaNombre());
		// pars.put("ciclo", ciclo);
		// pars.put("facultad", "Ingeniería de Sistemas e Informática");
		// pars.put("escuela", "Ingeniería de Sistemas");
		// pars.put("escudounmsm", "imagenes/unmsm.gif");
		return pars;
	}

	/**
	 * Obtener parametros.
	 *
	 * @return the map
	 * @throws Exception the exception
	 */
	private Map<Object, Object> obtenerParametros() throws Exception {
		Map<Object, Object> pars = new HashMap<Object, Object>();
		// String ruta_imagen="classpath:reportes/";
		String ciclo = alumnoServices.buscarCicloAcademico(getClaseMaestraModelSelect().getIdCampo());

		// File imagen = new File("classpath:reportes/Logo.png");
		// BufferedImage imagenIO = ImageIO.read(imagen);
		// BufferedImage imagen =
		// ImageIO.read(getClass().getResource("/Imagenes/Logo.png"));

		// pars.put("logo", imagen);
		pars.put("nomPro", profesorBuscado);
		pars.put("nomCurso", cursoBuscado);
		// pars.put("ciclo", ciclo);
		// pars.put("facultad", "Ingeniería de Sistemas e Informática");
		// pars.put("escuela", "Ingeniería de Sistemas");
		// pars.put("escudounmsm", "imagenes/unmsm.gif");
		return pars;
	}

	/**
	 * Obtener campos alumno.
	 *
	 * @return the array list
	 */
	private ArrayList<Object> obtenerCamposAlumno() {
		ArrayList<Object> list = new ArrayList<Object>();
		for (AsistenciaTutoriaModel model : listAsistenciaTutoria) {
			HorarioTutoria horarioDocente = new HorarioTutoria();
			// horarioDocente.setCodCurso(model.getC_codigo());
			horarioDocente.setDia(model.getDia());
			horarioDocente.setHoraFin(model.getHoraFin());
			horarioDocente.setHoraIni(model.getHoraIni());
			horarioDocente.setNomCurso(model.getC_nombre());
			horarioDocente.setNomProfesor(model.getP_nombre());
			horarioDocente.setRepitencias(model.getRepitencia());
			horarioDocente.setCodAlu(model.getA_codigo());
			horarioDocente.setNomAlu(model.getP_nombre());
			horarioDocente.setCiclo(model.gettAnio() + "-" + model.gettPeriodo());
			horarioDocente.setFrecuencia(model.getDescFrecuencia());

			list.add(horarioDocente);
		}
		return list;
	}

	/**
	 * Obtener campos.
	 *
	 * @return the array list
	 */
	private ArrayList<Object> obtenerCampos() {
		ArrayList<Object> list = new ArrayList<Object>();
		for (AsistenciaTutoriaModel model : listAsistenciaTutoriaDocente) {
			HorarioTutoria horarioDocente = new HorarioTutoria();
			horarioDocente.setCodCurso(model.getC_codigo());
			horarioDocente.setDia(model.getDia());
			horarioDocente.setHoraFin(model.getHoraFin());
			horarioDocente.setHoraIni(model.getHoraIni());
			horarioDocente.setNomCurso(model.getC_nombre());
			horarioDocente.setNomProfesor(model.getP_nombre() + " " + model.getP_apellidos());
			horarioDocente.setRepitencias(model.getRepitencia());
			horarioDocente.setCodAlu(model.getA_codigo());
			horarioDocente.setNomAlu(model.getP_nombre());
			horarioDocente.setCiclo(model.gettAnio() + "-" + model.gettPeriodo());
			horarioDocente.setFrecuencia(model.getDescFrecuencia());
			list.add(horarioDocente);
		}
		return list;
	}

	/**
	 * Obtener campos reporte general alumnos.
	 *
	 * @return the array list
	 */
	private ArrayList<Object> obtenerCamposReporteGeneralAlumnos() {
		ArrayList<Object> list = new ArrayList<Object>();
		for (AsistenciaTutoriaModel model : listAsistenciaTutoriaDocente) {
			HorarioTutoria horarioDocente = new HorarioTutoria();
			horarioDocente.setCodCurso(model.getC_codigo());
			horarioDocente.setDia(model.getDia());
			horarioDocente.setHoraFin(model.getHoraFin());
			horarioDocente.setHoraIni(model.getHoraIni());
			horarioDocente.setNomCurso(model.getC_nombre());
			horarioDocente.setNomProfesor(model.getP_nombre() + " " + model.getP_apellidos());
			horarioDocente.setRepitencias(model.getRepitencia());
			horarioDocente.setCodAlu(model.getA_codigo());
			horarioDocente.setNomAlu(model.getP_nombre());
			horarioDocente.setCiclo(model.gettAnio() + "-" + model.gettPeriodo());
			horarioDocente.setFrecuencia(model.getDescFrecuencia());
			horarioDocente.setNum_sesiones(model.getNum_sesiones());
			horarioDocente.setNum_asistencia_asistio(model.getNum_asistencia_asistio());
			horarioDocente.setNum_asistencia_falto(model.getNum_asistencia_falto());
			horarioDocente.setNum_asistencia_tardanza(model.getNum_asistencia_tardanza());
			horarioDocente.setNum_tareas_cerrado(model.getNum_tareas_cerrado());
			horarioDocente.setNum_tareas_parcialmente(model.getNum_tareas_parcialmente());
			horarioDocente.setNum_tareas_pendiente(model.getNum_tareas_pendiente());
			double porcentaje = ((model.getNum_asistencia_asistio() + model.getNum_asistencia_tardanza())) * 100
					/ model.getNum_sesiones();
			System.out.println("porcentaje :" + porcentaje);
			DecimalFormat df = new DecimalFormat("#.##");
			porcentaje = Double.valueOf(df.format(porcentaje));
			horarioDocente.setPorcentaje_asistencias(porcentaje);
			horarioDocente.setNum_actas(model.getNum_actas());
			list.add(horarioDocente);
		}
		return list;
	}

	/**
	 * Sumar.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the int
	 */
	public int sumar(int a, int b) {
		return a + b;
	}

	/**
	 * Sumar tres.
	 *
	 * @param a the a
	 * @param b the b
	 * @param c the c
	 * @return the int
	 */
	public int sumarTres(int a, int b, int c) {
		return a + b + c;
	}

	/**
	 * Porcentaje.
	 *
	 * @param asistio the asistio
	 * @param tardanza the tardanza
	 * @param sesiones the sesiones
	 * @return the double
	 */
	public double porcentaje(int asistio, int tardanza, int sesiones) {
		double porcentaje = ((asistio + tardanza)) * 100 / sesiones;
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(porcentaje));
	}

	/**
	 * Gets the asistencia tutoria model select.
	 *
	 * @return the asistencia tutoria model select
	 */
	public AsistenciaTutoriaModel getAsistenciaTutoriaModelSelect() {
		return asistenciaTutoriaModelSelect;
	}

	/**
	 * Gets the asistencia tutoria model.
	 *
	 * @return the asistencia tutoria model
	 */
	public AsistenciaTutoriaModel getAsistenciaTutoriaModel() {
		return asistenciaTutoriaModel;
	}

	/**
	 * Sets the asistencia tutoria model select.
	 *
	 * @param asistenciaTutoriaModelSelect the new asistencia tutoria model select
	 */
	public void setAsistenciaTutoriaModelSelect(AsistenciaTutoriaModel asistenciaTutoriaModelSelect) {
		this.asistenciaTutoriaModelSelect = asistenciaTutoriaModelSelect;
	}

	/**
	 * Gets the list asistencia tutoria.
	 *
	 * @return the list asistencia tutoria
	 */
	public List<AsistenciaTutoriaModel> getListAsistenciaTutoria() {
		return listAsistenciaTutoria;
	}

	/**
	 * Sets the list asistencia tutoria.
	 *
	 * @param listAsistenciaTutoria the new list asistencia tutoria
	 */
	public void setListAsistenciaTutoria(List<AsistenciaTutoriaModel> listAsistenciaTutoria) {
		this.listAsistenciaTutoria = listAsistenciaTutoria;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
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
	 * Sets the asistencia tutoria model.
	 *
	 * @param asistenciaTutoriaModel the new asistencia tutoria model
	 */
	public void setAsistenciaTutoriaModel(AsistenciaTutoriaModel asistenciaTutoriaModel) {
		this.asistenciaTutoriaModel = asistenciaTutoriaModel;
	}

	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the clase maestra model.
	 *
	 * @return the clase maestra model
	 */
	public ClaseMaestraModel getClaseMaestraModel() {
		return claseMaestraModel;
	}

	/**
	 * Sets the clase maestra model.
	 *
	 * @param claseMaestraModel the new clase maestra model
	 */
	public void setClaseMaestraModel(ClaseMaestraModel claseMaestraModel) {
		this.claseMaestraModel = claseMaestraModel;
	}

	/**
	 * Gets the clase maestra model select.
	 *
	 * @return the clase maestra model select
	 */
	public ClaseMaestraModel getClaseMaestraModelSelect() {
		return claseMaestraModelSelect;
	}

	/**
	 * Sets the clase maestra model select.
	 *
	 * @param claseMaestraModelSelect the new clase maestra model select
	 */
	public void setClaseMaestraModelSelect(ClaseMaestraModel claseMaestraModelSelect) {
		this.claseMaestraModelSelect = claseMaestraModelSelect;
	}

	/**
	 * Gets the comun services.
	 *
	 * @return the comun services
	 */
	public ComunServices getComunServices() {
		return comunServices;
	}

	/**
	 * Sets the comun services.
	 *
	 * @param comunServices the new comun services
	 */
	public void setComunServices(ComunServices comunServices) {
		this.comunServices = comunServices;
	}

	/**
	 * Gets the clase maestra frecuencia.
	 *
	 * @return the clase maestra frecuencia
	 */
	public ClaseMaestraModel getClaseMaestraFrecuencia() {
		return claseMaestraFrecuencia;
	}

	/**
	 * Sets the clase maestra frecuencia.
	 *
	 * @param claseMaestraFrecuencia the new clase maestra frecuencia
	 */
	public void setClaseMaestraFrecuencia(ClaseMaestraModel claseMaestraFrecuencia) {
		this.claseMaestraFrecuencia = claseMaestraFrecuencia;
	}

	/**
	 * Gets the clase maestra frecuencia select.
	 *
	 * @return the clase maestra frecuencia select
	 */
	public ClaseMaestraModel getClaseMaestraFrecuenciaSelect() {
		return claseMaestraFrecuenciaSelect;
	}

	/**
	 * Sets the clase maestra frecuencia select.
	 *
	 * @param claseMaestraFrecuenciaSelect the new clase maestra frecuencia select
	 */
	public void setClaseMaestraFrecuenciaSelect(ClaseMaestraModel claseMaestraFrecuenciaSelect) {
		this.claseMaestraFrecuenciaSelect = claseMaestraFrecuenciaSelect;
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
	 * Gets the listar ciclos.
	 *
	 * @return the listar ciclos
	 */
	public List<ClaseMaestra> getListarCiclos() {
		return listarCiclos;
	}

	/**
	 * Sets the listar ciclos.
	 *
	 * @param listarCiclos the new listar ciclos
	 */
	public void setListarCiclos(List<ClaseMaestra> listarCiclos) {
		this.listarCiclos = listarCiclos;
	}

	/**
	 * Gets the tutoria model select grid.
	 *
	 * @return the tutoria model select grid
	 */
	public TutoriaModel getTutoriaModelSelectGrid() {
		return tutoriaModelSelectGrid;
	}

	/**
	 * Sets the tutoria model select grid.
	 *
	 * @param tutoriaModelSelectGrid the new tutoria model select grid
	 */
	public void setTutoriaModelSelectGrid(TutoriaModel tutoriaModelSelectGrid) {
		this.tutoriaModelSelectGrid = tutoriaModelSelectGrid;
	}

	/**
	 * Gets the lista observaciones pendientes.
	 *
	 * @return the lista observaciones pendientes
	 */
	public List<ObservacionBO> getListaObservacionesPendientes() {
		return listaObservacionesPendientes;
	}

	/**
	 * Sets the lista observaciones pendientes.
	 *
	 * @param listaObservacionesPendientes the new lista observaciones pendientes
	 */
	public void setListaObservacionesPendientes(List<ObservacionBO> listaObservacionesPendientes) {
		this.listaObservacionesPendientes = listaObservacionesPendientes;
	}

	/**
	 * Gets the lista observaciones finalizadas.
	 *
	 * @return the lista observaciones finalizadas
	 */
	public List<ObservacionBO> getListaObservacionesFinalizadas() {
		return listaObservacionesFinalizadas;
	}

	/**
	 * Sets the lista observaciones finalizadas.
	 *
	 * @param listaObservacionesFinalizadas the new lista observaciones finalizadas
	 */
	public void setListaObservacionesFinalizadas(List<ObservacionBO> listaObservacionesFinalizadas) {
		this.listaObservacionesFinalizadas = listaObservacionesFinalizadas;
	}

	/**
	 * Gets the tutoria model grid.
	 *
	 * @return the tutoria model grid
	 */
	public TutoriaModel getTutoriaModelGrid() {
		return tutoriaModelGrid;
	}

	/**
	 * Sets the tutoria model grid.
	 *
	 * @param tutoriaModelGrid the new tutoria model grid
	 */
	public void setTutoriaModelGrid(TutoriaModel tutoriaModelGrid) {
		this.tutoriaModelGrid = tutoriaModelGrid;
	}

	/**
	 * Gets the nombre origen.
	 *
	 * @return the nombre origen
	 */
	public String getNombreOrigen() {
		return nombreOrigen;
	}

	/**
	 * Sets the nombre origen.
	 *
	 * @param nombreOrigen the new nombre origen
	 */
	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	/**
	 * Gets the curso buscado.
	 *
	 * @return the curso buscado
	 */
	public String getCursoBuscado() {
		return cursoBuscado;
	}

	/**
	 * Sets the curso buscado.
	 *
	 * @param cursoBuscado the new curso buscado
	 */
	public void setCursoBuscado(String cursoBuscado) {
		this.cursoBuscado = cursoBuscado;
	}

	/**
	 * Gets the profesor buscado.
	 *
	 * @return the profesor buscado
	 */
	public String getProfesorBuscado() {
		return profesorBuscado;
	}

	/**
	 * Sets the profesor buscado.
	 *
	 * @param profesorBuscado the new profesor buscado
	 */
	public void setProfesorBuscado(String profesorBuscado) {
		this.profesorBuscado = profesorBuscado;
	}

	/**
	 * Gets the list asistencia tutoria docente.
	 *
	 * @return the list asistencia tutoria docente
	 */
	public List<AsistenciaTutoriaModel> getListAsistenciaTutoriaDocente() {
		return listAsistenciaTutoriaDocente;
	}

	/**
	 * Sets the list asistencia tutoria docente.
	 *
	 * @param listAsistenciaTutoriaDocente the new list asistencia tutoria docente
	 */
	public void setListAsistenciaTutoriaDocente(List<AsistenciaTutoriaModel> listAsistenciaTutoriaDocente) {
		this.listAsistenciaTutoriaDocente = listAsistenciaTutoriaDocente;
	}

}