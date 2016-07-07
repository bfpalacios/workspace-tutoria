package pe.edu.sistemas.unayoe.controlador;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import pe.edu.sistemas.unayoe.core.util.Convertidor;
import pe.edu.sistemas.unayoe.core.util.FormateadorFecha;
import pe.edu.sistemas.unayoe.model.AlumnoModel;
import pe.edu.sistemas.unayoe.model.TutoriaModel;
import pe.edu.sistemas.unayoe.model.NotasAlumnoExcelModel;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.EncuestaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTAlumBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;

@Controller("alumnoMBean")
@ViewScoped
public class AlumnoMBean {
	@Autowired
	private AlumnoModel alumnoModel;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private ComunServices comunServices;
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private TutoriaServices tutoriaServices;

	private List<AlumnoModel> listAlumnoModel;
	private List<AsistenciaCAlumnoBO> listAlumnoBO;

	private Date date;
	private boolean select;
	private int sesionTutoria;
	private String codTutoria;
	private boolean activarTablaNotas;
	private boolean activarObservacion;
	private AlumnoModel alumnoModelSelect;
	private List<TutoriaModel> listaObservaciones;
	private TutoriaBO listaAsistenciaAlumnosTutoriaAux;
	private List<TutoriaBO> listaAsistenciaAlumnosTutoria;
	private TutoriaBO listaAsistenciaAlumnosTutoriaSelect;
	private List<TutoriaBO> listaAsistenciaAlumnosTutoriaGrid;
	private List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria;
	private List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid;
	
	/*-------------------------------------------------------*/
	/*
	 * Estas variables no deben ser accedidas por ninguna clase por ello, no
	 * tendrán métodos GET y SET
	 */
	private int PROCESO;
	private int MODO;
	private int TIPO_SESION;
	private int ANIO_ACTUAL;
	private int PERIODO_ACTUAL;

	private static int PROCESO_OBSERVADOS = 1;
	private static int PROCESO_REGULARES = 2;

	private static int MODO_ADMIN = 1;
	private static int MODO_TUTOR = 2;

	private static int PRIMERA_SESION = 1;
	private static int SESION_NORMAL = 2;
	private static int ULTIMA_SESION = 3;

	private static String REGISTRO_CORRECTO = "R";
	private static String REGISTRO_EXTEMPORANEO = "E";
	/*-------------------------------------------------------*/

	public AlumnoMBean() {
		System.out.println("::::: LOADING ::::::::");
		listAsistenciaAlumnosTutoria = new ArrayList<AsistenciaTAlumBO>();
		inicializarClases();
		new Convertidor();
		new FormateadorFecha();
		date = new Date();
	}

	private void inicializarClases() {
		setAlumnoModelSelect(new AlumnoModel());
		setListaObservaciones(new ArrayList<TutoriaModel>());
		setListaAsistenciaAlumnosTutoriaAux(new TutoriaBO());
		setListaAsistenciaAlumnosTutoriaSelect(new TutoriaBO());
		setListaAsistenciaAlumnosTutoria(new ArrayList<TutoriaBO>());
		setListaAsistenciaAlumnosTutoriaGrid(new ArrayList<TutoriaBO>());
		setVisualizarNotasAlumnoGrid(new ArrayList<NotasAlumnoExcelModel>());
		setListAsistenciaAlumnosTutoria(new ArrayList<AsistenciaTAlumBO>());
	}

	private void reiniciarObjetos() {
		if (getAlumnoModel() != null) {
			setAlumnoModel(null);
			setAlumnoModel(new AlumnoModel());
		}

		if (getAlumnoModelSelect() != null) {
			setAlumnoModelSelect(null);
			setAlumnoModelSelect(new AlumnoModel());
		}
		if (getListaObservaciones() != null) {
			setListaObservaciones(null);
			setListaObservaciones(new ArrayList<TutoriaModel>());
		}
		if (getListaAsistenciaAlumnosTutoria() != null) {
			setListaAsistenciaAlumnosTutoria(null);
			setListaAsistenciaAlumnosTutoria(new ArrayList<TutoriaBO>());
		}
		if (getListaAsistenciaAlumnosTutoriaGrid() != null) {
			setListaAsistenciaAlumnosTutoriaGrid(null);
			setListaAsistenciaAlumnosTutoriaGrid(new ArrayList<TutoriaBO>());
		}
	}

	public void listarCursos() {
		System.out.println("Listando los cursos:");
		List<CursoBO> listarCursos = null;
		try {
			listarCursos = cursoServices.listarCursosTutorias();
			alumnoModel.setListarCursos(listarCursos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarCursosDocente() throws Exception {
		String usuarioDocente = obtenerUsuario();
		List<CursoBO> listaCursos = alumnoServices.listarCursosDocente(usuarioDocente);
		getAlumnoModel().setListarCursos(listaCursos);
	}

	public ArrayList<ClaseMaestra> llenarComboAsistencia() {
		String tabla = "ASISTENCIA";
		String campo = "OPC_ASISTENCIA";

		ArrayList<ClaseMaestra> listaComboAsistencia = new ArrayList<ClaseMaestra>();

		try {
			listaComboAsistencia = (ArrayList<ClaseMaestra>) comunServices.listarClaseMaestra(tabla, campo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaComboAsistencia;
	}

	public ArrayList<ClaseMaestra> llenarComboJustificacion() {
		String tabla = "JUSTIFICACION";
		String campo = "OPC_JUSTIFICACION";

		ArrayList<ClaseMaestra> listaComboAsistencia = new ArrayList<ClaseMaestra>();

		try {
			listaComboAsistencia = (ArrayList<ClaseMaestra>) comunServices.listarClaseMaestra(tabla, campo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaComboAsistencia;
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

	public void actualizarDocente(ValueChangeEvent e) throws Exception {
		String codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		List<ProfesorBO> listaProfesores = new ArrayList<ProfesorBO>();

		switch (PROCESO) {
		case 1:
			listaProfesores = tutoriaServices.listarTutoresObservados(codCurso);
			break;
		case 2:
			listaProfesores = tutoriaServices.listarTutoresRegulares(codCurso);
			break;
		}
		getAlumnoModel().setListarTutores(listaProfesores);
	}

	public void actualizarAlumno(ValueChangeEvent e) throws Exception {
		String codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		getAlumnoModelSelect().setpCodigo(codDocente);

		if (getAlumnoModel().getListarAlumnos() != null) {
			getAlumnoModel().getListarAlumnos().clear();
		}

		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, getAlumnoModelSelect().getCod_curso(), PROCESO,
				MODO);
		getAlumnoModel().setListarAlumnos(listaAlumnos);
	}

	public void actualizarAlumnoTutor(ValueChangeEvent e) throws Exception {
		String usuarioDocente = obtenerUsuario();
		String codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		getAlumnoModelSelect().setCod_curso(codCurso);

		if (getAlumnoModel().getListarAlumnos() != null) {
			getAlumnoModel().getListarAlumnos().clear();
		}

		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		listaAlumnos = alumnoServices.listarAlumnoTutoria(usuarioDocente, codCurso, PROCESO, MODO);
		getAlumnoModel().setListarAlumnos(listaAlumnos);
	}

	public void actualizarJustificacion(ValueChangeEvent e) throws Exception {
		ArrayList<ClaseMaestra> comboJustificacion = llenarComboJustificacion();
		getListaAsistenciaAlumnosTutoriaAux().setListaJustificacion(comboJustificacion);
	}

	private int compararFechas(String fechaActual, String fechaElegida) {
		int resultado = -1;
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaActualModificada = formateador.parse(fechaActual);
			Date fechaElegidaModificada = formateador.parse(fechaElegida);

			if (fechaElegidaModificada.before(fechaActualModificada)) {
				resultado = 1;
			} else {
				if (fechaActualModificada.before(fechaElegidaModificada)) {
					resultado = 2;
				} else {
					resultado = 0;
				}
			}
		} catch (ParseException PE) {
			PE.printStackTrace();
		}
		return resultado;
	}

	public static boolean ValidarFecha(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public String buscarAsistenciaAlumnosTutoria() {
		String pagina = "";
		try {
			String fechaActual = new FormateadorFecha().formatoFechaDDMMAAAA(new Date());
			String fechaElegida = "";

			if (MODO == MODO_ADMIN) {
				fechaElegida = new FormateadorFecha()
						.formatoFechaDDMMAAAA(getDate() == null ? new Date(Long.MIN_VALUE) : getDate());
			} else {
				//fechaElegida="09/07/2015";
				fechaElegida = new FormateadorFecha().formatoFechaDDMMAAAA(new Date());
			}

			if (ValidarFecha(fechaElegida) == true) {
				if (compararFechas(fechaActual, fechaElegida) == 2) {
					mostrarMensaje(5);
				} else {
					String usuarioDocente = "";

					ArrayList<ClaseMaestra> listaComboAsistencia = llenarComboAsistencia();

					if (getListaAsistenciaAlumnosTutoria() != null) {
						getListaAsistenciaAlumnosTutoria().clear();
					}

					if (getListaAsistenciaAlumnosTutoriaGrid() != null) {
						getListaAsistenciaAlumnosTutoriaGrid().clear();
					}

					if (MODO == MODO_ADMIN) {
						usuarioDocente = getAlumnoModelSelect().getpCodigo() == null ? ""
								: getAlumnoModelSelect().getpCodigo();
					} else {
						if (MODO == MODO_TUTOR) {
							usuarioDocente = obtenerUsuario();
						}
					}

					List<TutoriaBO> listarTutorias = new ArrayList<TutoriaBO>();
					listarTutorias = getTutoriaServices().buscarAsistenciaAlumnosTutoria(usuarioDocente,
							getAlumnoModelSelect().getCod_curso(), getAlumnoModelSelect().getCodigo(), fechaElegida,
							PROCESO, MODO);

					if (listarTutorias.size() > 0) {
						System.out.println("Hay tutoria");
						codTutoria = listarTutorias.get(0).gettCodigo();
						int ultimaSesion = comunServices.obtenerUltimaSesionTutoria(codTutoria);
						for (TutoriaBO tutoria : listarTutorias) {
							if (tutoria.getEstadoSesion().equals(REGISTRO_CORRECTO)
									|| tutoria.getEstadoSesion().equals(REGISTRO_EXTEMPORANEO)) {
								sesionTutoria = tutoria.getSesion();
								mostrarMensaje(3);
								break;
							} else {
								if (tutoria.getSesion() == 1) {
									if (TIPO_SESION == PRIMERA_SESION) {
										if (PROCESO == PROCESO_REGULARES) {
											List<EncuestaBO> listaEncuestas = tutoriaServices
													.buscarDatosEncuesta(codTutoria);
											if (listaEncuestas.size() > 0) {
												tutoria.setListaAsistencia(listaComboAsistencia);
												setListaAsistenciaAlumnosTutoria(listarTutorias);
												setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
												buscarNotasAlumno(tutoria.getaCodigo());
												setActivarTablaNotas(true);
											} else {
												mostrarMensaje(9);
											}
										} else {
											tutoria.setListaAsistencia(listaComboAsistencia);
											setListaAsistenciaAlumnosTutoria(listarTutorias);
											setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
											buscarNotasAlumno(tutoria.getaCodigo());
											setActivarTablaNotas(true);
										}
									} else {
										mostrarMensaje(10);
									}
								} else {
									if (TIPO_SESION == PRIMERA_SESION) {
										if (MODO != MODO_ADMIN) {
											mostrarMensaje(12);
										}
									} else {
										if (tutoria.getSesion() == ultimaSesion) {
											if (TIPO_SESION == ULTIMA_SESION) {
												tutoria.setListaAsistencia(listaComboAsistencia);
												setListaAsistenciaAlumnosTutoria(listarTutorias);
												setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
											} else {
												mostrarMensaje(11);
											}
										} else {
											if (TIPO_SESION == ULTIMA_SESION) {
												if (MODO != MODO_ADMIN) {
													mostrarMensaje(13);
												}
											} else {
												System.out.println("si hay datas");
												tutoria.setListaAsistencia(listaComboAsistencia);
												setListaAsistenciaAlumnosTutoria(listarTutorias);
												setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
											}
										}
										setActivarTablaNotas(false);
									}
								}
							}
						}
					} else {
						mostrarMensaje(1);
					}
				}
			} else {
				mostrarMensaje(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (MODO) {
		case 1:
			switch (PROCESO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarAsistenciaTutoriaAlumnosObs.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarAsistenciaTutoriaAlumnosReg.xhtml";
				break;

			}
			break;
		case 2:
			switch (PROCESO) {
			case 1:
				switch (TIPO_SESION) {
				case 1:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			case 2:
				switch (TIPO_SESION) {
				case 1:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			}
			break;
		}
		return pagina;
	}

	public String guardarRegistroAsistencia() {
		String pagina = "";
		try {
			if (getListaAsistenciaAlumnosTutoriaGrid().size() > 0) {
				List<TutoriaBO> listaAsistenciaTutorias = getListaAsistenciaAlumnosTutoriaGrid();
				for (TutoriaBO asistenciaTutorias : listaAsistenciaTutorias) {
					TutoriaBO sesionesTutoria = comunServices.obtenerSesionTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
							asistenciaTutorias.getcCodigo(),
							MODO == MODO_ADMIN ? asistenciaTutorias.getpCodigo() : obtenerUsuario(),
							asistenciaTutorias.getaCodigo(), asistenciaTutorias.getSesion(), MODO, PROCESO);

					if (sesionesTutoria.getEstadoSesion().equals(REGISTRO_CORRECTO)
							|| sesionesTutoria.getEstadoSesion().equals(REGISTRO_EXTEMPORANEO)) {
						sesionTutoria = asistenciaTutorias.getSesion();
						mostrarMensaje(4);
						break;
					} else {
						getTutoriaServices().guardarRegistroAsistencia(asistenciaTutorias,
								new FormateadorFecha().formatoFechaDDMMAAAA(getDate()), PROCESO, MODO);
						/*
						if (getListaObservaciones().size() > 0) {
							for (TutoriaModel observacionTutoria : getListaObservaciones()) {
								observacionTutoria.setSesion(sesionesTutoria.getSesion());
								getTutoriaServices().guardarObservacionesAsistencia(codTutoria,
										observacionTutoria.getObservacion(), observacionTutoria.getCriticidad(),
										observacionTutoria.getSesion());
							}
						}
						*/
						reiniciarObjetos();
						switch (MODO) {
						case 1:
							listarCursos();
							break;
						case 2:
							listarCursosDocente();
							break;
						}
						mostrarMensaje(6);
					}
				}
			} else {
				mostrarMensaje(8);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(7);
		}
		switch (MODO) {
		case 1:
			switch (PROCESO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarAsistenciaTutoriaAlumnosObs.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarAsistenciaTutoriaAlumnosReg.xhtml";
				break;

			}
			break;
		case 2:
			switch (PROCESO) {
			case 1:
				switch (TIPO_SESION) {
				case 1:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			case 2:
				switch (TIPO_SESION) {
				case 1:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			}
			break;
		}
		return pagina;
	}

	public void buscarNotasAlumno(String codAlumno) throws Exception {
		try {
			CicloBO cicloActual = comunServices.buscarCicloActual();
			getVisualizarNotasAlumnoGrid().removeAll(getVisualizarNotasAlumnoGrid());

			List<NotasAlumnoBO> notasAlumnoBO = alumnoServices.buscarNotasAlumnoTutoria(cicloActual.getAnio(),
					cicloActual.getPeriodo(), codAlumno);

			for (NotasAlumnoBO notasAlumno : notasAlumnoBO) {
				NotasAlumnoExcelModel notasAlumnoModel = convertirANotasAlumnoModel(notasAlumno);
				getVisualizarNotasAlumnoGrid().add(notasAlumnoModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NotasAlumnoExcelModel convertirANotasAlumnoModel(NotasAlumnoBO notasAlumno) {
		NotasAlumnoExcelModel notasAlumnoModel = new NotasAlumnoExcelModel();
		notasAlumnoModel.setAnio(notasAlumno.getAnio());
		notasAlumnoModel.setPeriodo(notasAlumno.getPeriodo());
		notasAlumnoModel.setPlan(notasAlumno.getPlan());
		notasAlumnoModel.setCodCurso(notasAlumno.getCodCurso());
		notasAlumnoModel.setNomCurso(notasAlumno.getNomCurso());
		notasAlumnoModel.setCodAlumno(notasAlumno.getCodAlumno());
		notasAlumnoModel.setNomAlumno(notasAlumno.getNomAlumno());
		notasAlumnoModel.setNomDocente(notasAlumno.getNomDocente());
		notasAlumnoModel.setCreditos(notasAlumno.getCreditos());
		notasAlumnoModel.setNotaFinal(notasAlumno.getNotaFinal());

		return notasAlumnoModel;
	}

	public void agregarObservacion() {
		if (getListaAsistenciaAlumnosTutoriaGrid().size() > 0) {
			TutoriaModel observacionTutoria = new TutoriaModel();
			getListaObservaciones().add(observacionTutoria);
		} else {
			mostrarMensaje(8);
		}
	}

	private void mostrarMensaje(int opcionMensaje) {
		FacesMessage message = null;

		switch (opcionMensaje) {
		case 1:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin Tutorías",
					"No se encontraron tutorías con los datos proporcionados");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe introducir una fecha válida");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Duplicidad: ", "La sesión de la tutoría Nº "
					+ String.valueOf(sesionTutoria) + ", ya ha sido registrada en otro momento");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sesión ya registrada: ",
					"La sesión de la tutoría Nº " + String.valueOf(sesionTutoria) + ", ya se encuentra registrada");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"La fecha elegida no debe ser mayor a la fecha del sistema");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto",
					"La asistencia a esta sesión se ha registrado correctamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Ha ocurrido un error al registrar la asistencia a la sesión");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
					"No se encontró una sesión de tutoría para registrar");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Encuesta no realizada",
					"El alumno todavía no ha registrado su encuesta");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 10:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"La asistencia a la primera sesión debe registrarse en la opción correspondiente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 11:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"La asistencia a la última sesión debe registrarse en la opción correspondiente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 12:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"En esta opción solamente está permitido el registro de la primera sesión");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 13:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"En esta opción solamente está permitido el registro de la última sesión");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		}
	}

	public String selectorRegistroAsistencia(int proceso, int modo, int tipoSesion) throws Exception {
		String pagina = "";
		CicloBO cicloActual = comunServices.buscarCicloActual();
		ANIO_ACTUAL = cicloActual.getAnio();
		PERIODO_ACTUAL = cicloActual.getPeriodo();
		inicializarClases();
		reiniciarObjetos();
		setActivarTablaNotas(false);
		switch (proceso) {
		case 1:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				listarCursos();
				pagina = "/paginas/ModuloObservados/admin/registrar/registrarAsistenciaTutoriaAlumnosObs.xhtml";
				break;

			case 2:
				switch (tipoSesion) {
				case 1:
					TIPO_SESION = PRIMERA_SESION;
					PROCESO = PROCESO_OBSERVADOS;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					TIPO_SESION = SESION_NORMAL;
					PROCESO = PROCESO_OBSERVADOS;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					TIPO_SESION = ULTIMA_SESION;
					PROCESO = PROCESO_OBSERVADOS;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloObservados/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			}
			break;
		case 2:
			switch (modo) {
			case 1:
				listarCursos();
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarAsistenciaTutoriaAlumnosReg.xhtml";
				break;

			case 2:
				switch (tipoSesion) {
				case 1:
					TIPO_SESION = PRIMERA_SESION;
					PROCESO = PROCESO_REGULARES;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosPS.xhtml";
					break;
				case 2:
					TIPO_SESION = SESION_NORMAL;
					PROCESO = PROCESO_REGULARES;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosSN.xhtml";
					break;
				case 3:
					TIPO_SESION = ULTIMA_SESION;
					PROCESO = PROCESO_REGULARES;
					MODO = MODO_TUTOR;
					listarCursosDocente();
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarAsistenciaTutoriaAlumnosUS.xhtml";
					break;
				}
				break;
			}
			break;
		}
		return pagina;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AlumnoModel getAlumnoModel() {
		return alumnoModel;
	}

	public void setAlumnoModel(AlumnoModel alumnoModel) {
		this.alumnoModel = alumnoModel;
	}

	public List<AsistenciaCAlumnoBO> getListAlumnoBO() {
		return listAlumnoBO;
	}

	public void setListAlumnoBO(List<AsistenciaCAlumnoBO> listAlumnoBO) {
		this.listAlumnoBO = listAlumnoBO;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public AlumnoModel getAlumnoModelSelect() {
		return alumnoModelSelect;
	}

	public void setAlumnoModelSelect(AlumnoModel alumnoModelSelect) {
		this.alumnoModelSelect = alumnoModelSelect;
	}

	public List<AlumnoModel> getListAlumnoModel() {
		return listAlumnoModel;
	}

	public void setListAlumnoModel(List<AlumnoModel> listAlumnoModel) {
		this.listAlumnoModel = listAlumnoModel;
	}

	public List<AsistenciaTAlumBO> getListAsistenciaAlumnosTutoria() {
		return listAsistenciaAlumnosTutoria;
	}

	public void setListAsistenciaAlumnosTutoria(List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}

	public List<TutoriaBO> getListaAsistenciaAlumnosTutoria() {
		return listaAsistenciaAlumnosTutoria;
	}

	public void setListaAsistenciaAlumnosTutoria(List<TutoriaBO> listaAsistenciaAlumnosTutoria) {
		this.listaAsistenciaAlumnosTutoria = listaAsistenciaAlumnosTutoria;
	}

	public TutoriaBO getListaAsistenciaAlumnosTutoriaSelect() {
		return listaAsistenciaAlumnosTutoriaSelect;
	}

	public void setListaAsistenciaAlumnosTutoriaSelect(TutoriaBO listaAsistenciaAlumnosTutoriaSelect) {
		this.listaAsistenciaAlumnosTutoriaSelect = listaAsistenciaAlumnosTutoriaSelect;
	}

	public List<TutoriaBO> getListaAsistenciaAlumnosTutoriaGrid() {
		return listaAsistenciaAlumnosTutoriaGrid;
	}

	public void setListaAsistenciaAlumnosTutoriaGrid(List<TutoriaBO> listaAsistenciaAlumnosTutoriaGrid) {
		this.listaAsistenciaAlumnosTutoriaGrid = listaAsistenciaAlumnosTutoriaGrid;
	}

	public TutoriaServices getTutoriaServices() {
		return tutoriaServices;
	}

	public void setTutoriaServices(TutoriaServices tutoriaServices) {
		this.tutoriaServices = tutoriaServices;
	}

	public TutoriaBO getListaAsistenciaAlumnosTutoriaAux() {
		return listaAsistenciaAlumnosTutoriaAux;
	}

	public void setListaAsistenciaAlumnosTutoriaAux(TutoriaBO listaAsistenciaAlumnosTutoriaAux) {
		this.listaAsistenciaAlumnosTutoriaAux = listaAsistenciaAlumnosTutoriaAux;
	}

	public List<NotasAlumnoExcelModel> getVisualizarNotasAlumnoGrid() {
		return visualizarNotasAlumnoGrid;
	}

	public void setVisualizarNotasAlumnoGrid(List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid) {
		this.visualizarNotasAlumnoGrid = visualizarNotasAlumnoGrid;
	}

	public List<TutoriaModel> getListaObservaciones() {
		return listaObservaciones;
	}

	public void setListaObservaciones(List<TutoriaModel> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}

	public boolean isActivarObservacion() {
		return activarObservacion;
	}

	public void setActivarObservacion(boolean activarObservacion) {
		this.activarObservacion = activarObservacion;
	}

	public boolean isActivarTablaNotas() {
		return activarTablaNotas;
	}

	public void setActivarTablaNotas(boolean activarTablaNotas) {
		this.activarTablaNotas = activarTablaNotas;
	}
}
