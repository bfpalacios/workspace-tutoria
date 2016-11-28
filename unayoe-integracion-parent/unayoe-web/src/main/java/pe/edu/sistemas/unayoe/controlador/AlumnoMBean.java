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

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoMBean.
 */
@Controller("alumnoMBean")
@ViewScoped
public class AlumnoMBean {

	/** The alumno model. */
	@Autowired
	private AlumnoModel alumnoModel;
	
	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;
	
	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The tutoria services. */
	@Autowired
	private TutoriaServices tutoriaServices;

	/** The list alumno model. */
	private List<AlumnoModel> listAlumnoModel;
	
	/** The list alumno BO. */
	private List<AsistenciaCAlumnoBO> listAlumnoBO;

	/** The date. */
	private Date date;
	
	/** The select. */
	private boolean select;
	
	/** The sesion tutoria. */
	private int sesionTutoria;
	
	/** The cod tutoria. */
	private String codTutoria;
	
	/** The activar tabla notas. */
	private boolean activarTablaNotas;
	
	/** The activar observacion. */
	private boolean activarObservacion;
	
	/** The alumno model select. */
	private AlumnoModel alumnoModelSelect;
	
	/** The lista observaciones. */
	private List<TutoriaModel> listaObservaciones;
	
	/** The lista asistencia alumnos tutoria aux. */
	private TutoriaBO listaAsistenciaAlumnosTutoriaAux;
	
	/** The lista asistencia alumnos tutoria. */
	private List<TutoriaBO> listaAsistenciaAlumnosTutoria;
	
	/** The lista asistencia alumnos tutoria select. */
	private TutoriaBO listaAsistenciaAlumnosTutoriaSelect;
	
	/** The lista asistencia alumnos tutoria grid. */
	private List<TutoriaBO> listaAsistenciaAlumnosTutoriaGrid;
	
	/** The list asistencia alumnos tutoria. */
	private List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria;
	
	/** The visualizar notas alumno grid. */
	private List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid;
	
	/*-------------------------------------------------------*/
	/** The proceso. */
	/*
	 * Estas variables no deben ser accedidas por ninguna clase por ello, no
	 * tendrán métodos GET y SET
	 */
	private int PROCESO;
	
	/** The modo. */
	private int MODO;
	
	/** The tipo sesion. */
	private int TIPO_SESION;
	
	/** The anio actual. */
	private int ANIO_ACTUAL;
	
	/** The periodo actual. */
	private int PERIODO_ACTUAL;

	/** The proceso observados. */
	private static int PROCESO_OBSERVADOS = 1;
	
	/** The proceso regulares. */
	private static int PROCESO_REGULARES = 2;

	/** The modo admin. */
	private static int MODO_ADMIN = 1;
	
	/** The modo tutor. */
	private static int MODO_TUTOR = 2;

	/** The primera sesion. */
	private static int PRIMERA_SESION = 1;
	
	/** The sesion normal. */
	private static int SESION_NORMAL = 2;
	
	/** The ultima sesion. */
	private static int ULTIMA_SESION = 3;

	/** The registro correcto. */
	private static String REGISTRO_CORRECTO = "R";
	
	/** The registro extemporaneo. */
	private static String REGISTRO_EXTEMPORANEO = "E";
	/*-------------------------------------------------------*/

	/**
	 * Instantiates a new alumno M bean.
	 */
	public AlumnoMBean() {
		System.out.println("::::: ALUMNOBEAN ::::::::");
		listAsistenciaAlumnosTutoria = new ArrayList<AsistenciaTAlumBO>();
		inicializarClases();
		new Convertidor();
		new FormateadorFecha();
		date = new Date();
	}

	/**
	 * Inicializar clases.
	 */
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

	/**
	 * Reiniciar objetos.
	 */
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

	/**
	 * Listar cursos.
	 */
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

	/**
	 * Listar cursos docente.
	 *
	 * @throws Exception the exception
	 */
	public void listarCursosDocente() throws Exception {
		String usuarioDocente = obtenerUsuario();
		List<CursoBO> listaCursos = alumnoServices.listarCursosDocente(usuarioDocente);
		getAlumnoModel().setListarCursos(listaCursos);
	}

	/**
	 * Llenar combo asistencia.
	 *
	 * @return the array list
	 */
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

	/**
	 * Llenar combo justificacion.
	 *
	 * @return the array list
	 */
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

	/**
	 * Obtener usuario.
	 *
	 * @return the string
	 */
	public String obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			nombre = user.getUsername();
		}
		return nombre;
	}

	/**
	 * Actualizar docente.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
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

	/**
	 * Actualizar alumno.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
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

	/**
	 * Actualizar alumno tutor.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
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

	/**
	 * Actualizar justificacion.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarJustificacion(ValueChangeEvent e) throws Exception {
		ArrayList<ClaseMaestra> comboJustificacion = llenarComboJustificacion();
		getListaAsistenciaAlumnosTutoriaAux().setListaJustificacion(comboJustificacion);
	}

	/**
	 * Comparar fechas.
	 *
	 * @param fechaActual the fecha actual
	 * @param fechaElegida the fecha elegida
	 * @return the int
	 */
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

	/**
	 * Validar fecha.
	 *
	 * @param fecha the fecha
	 * @return true, if successful
	 */
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

	/**
	 * Buscar asistencia alumnos tutoria.
	 *
	 * @return the string
	 */
	public String buscarAsistenciaAlumnosTutoria() {
		System.out.println("busca asistencia");
		String pagina = "";
		try {
			String fechaActual = new FormateadorFecha().formatoFechaDDMMAAAA(new Date());
			String fechaElegida = "";
			System.out.println("MODO1:"+MODO);
			if (MODO == MODO_ADMIN) {
				System.out.println("15");
				fechaElegida = new FormateadorFecha()
						.formatoFechaDDMMAAAA(getDate() == null ? new Date(Long.MIN_VALUE) : getDate());
			} else {
				System.out.println("16");
				fechaElegida = new FormateadorFecha().formatoFechaDDMMAAAA(getDate() == null ? new Date(Long.MIN_VALUE) : getDate());
			}

			if (ValidarFecha(fechaElegida) == true) {
				System.out.println("18");
				if (compararFechas(fechaActual, fechaElegida) == 2) {
					System.out.println("17");
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
System.out.println("MODO2:"+MODO);
					if (MODO == MODO_ADMIN) {
						System.out.println("21");
						usuarioDocente = getAlumnoModelSelect().getpCodigo() == null ? ""
								: getAlumnoModelSelect().getpCodigo();
					} else {
						if (MODO == MODO_TUTOR) {
							System.out.println("20");
							usuarioDocente = obtenerUsuario();
						}
					}

					System.out.println("usuario docente " + usuarioDocente);
					
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
									System.out.println("1");
									TIPO_SESION=1;
									System.out.println("MODO"+MODO);
									System.out.println("tipo1"+TIPO_SESION);
									System.out.println("p_sesio1"+PRIMERA_SESION);
									if (TIPO_SESION == PRIMERA_SESION) {
										System.out.println("2");
										if (PROCESO == PROCESO_REGULARES) {
											System.out.println("3");
											List<EncuestaBO> listaEncuestas = tutoriaServices
													.buscarDatosEncuesta(codTutoria);
											if (listaEncuestas.size() > 0) {
												System.out.println("4");
												tutoria.setListaAsistencia(listaComboAsistencia);
												setListaAsistenciaAlumnosTutoria(listarTutorias);
												setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
												buscarNotasAlumno(tutoria.getaCodigo());
												setActivarTablaNotas(true);
											} else {
												mostrarMensaje(9);
											}
										} else {
											System.out.println("5");
											tutoria.setListaAsistencia(listaComboAsistencia);
											setListaAsistenciaAlumnosTutoria(listarTutorias);
											setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
											buscarNotasAlumno(tutoria.getaCodigo());
											setActivarTablaNotas(true);
											PRIMERA_SESION=0;
										}
									} else {
										mostrarMensaje(10);
									}
								} else {
									System.out.println("11");
									System.out.println("MODO"+MODO);
									System.out.println("tipo2"+TIPO_SESION);
									System.out.println("p_sesio2"+PRIMERA_SESION);
									if (TIPO_SESION == PRIMERA_SESION) {
										System.out.println("6");
										if (MODO != MODO_ADMIN) {
											mostrarMensaje(12);
										}
									} else {
										System.out.println("7");
										if (tutoria.getSesion() == ultimaSesion) {
											System.out.println("9");
											if (TIPO_SESION == ULTIMA_SESION) {
												System.out.println("8");
												tutoria.setListaAsistencia(listaComboAsistencia);
												setListaAsistenciaAlumnosTutoria(listarTutorias);
												setListaAsistenciaAlumnosTutoriaGrid(listarTutorias);
											} else {
												mostrarMensaje(11);
											}
										} else {
											System.out.println("MODO3"+MODO);
											System.out.println("tipo3"+TIPO_SESION);
											System.out.println("p_sesio3"+PRIMERA_SESION);
											if (TIPO_SESION == ULTIMA_SESION) {
												System.out.println("10");
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

	/**
	 * Guardar registro asistencia.
	 *
	 * @return the string
	 */
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

	/**
	 * Buscar notas alumno.
	 *
	 * @param codAlumno the cod alumno
	 * @throws Exception the exception
	 */
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

	/**
	 * Convertir A notas alumno model.
	 *
	 * @param notasAlumno the notas alumno
	 * @return the notas alumno excel model
	 */
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

	/**
	 * Agregar observacion.
	 */
	public void agregarObservacion() {
		if (getListaAsistenciaAlumnosTutoriaGrid().size() > 0) {
			TutoriaModel observacionTutoria = new TutoriaModel();
			getListaObservaciones().add(observacionTutoria);
		} else {
			mostrarMensaje(8);
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

	/**
	 * Selector registro asistencia.
	 *
	 * @param proceso the proceso
	 * @param modo the modo
	 * @param tipoSesion the tipo sesion
	 * @return the string
	 * @throws Exception the exception
	 */
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
	 * Gets the alumno model.
	 *
	 * @return the alumno model
	 */
	public AlumnoModel getAlumnoModel() {
		return alumnoModel;
	}

	/**
	 * Sets the alumno model.
	 *
	 * @param alumnoModel the new alumno model
	 */
	public void setAlumnoModel(AlumnoModel alumnoModel) {
		this.alumnoModel = alumnoModel;
	}

	/**
	 * Gets the list alumno BO.
	 *
	 * @return the list alumno BO
	 */
	public List<AsistenciaCAlumnoBO> getListAlumnoBO() {
		return listAlumnoBO;
	}

	/**
	 * Sets the list alumno BO.
	 *
	 * @param listAlumnoBO the new list alumno BO
	 */
	public void setListAlumnoBO(List<AsistenciaCAlumnoBO> listAlumnoBO) {
		this.listAlumnoBO = listAlumnoBO;
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
	 * Gets the alumno model select.
	 *
	 * @return the alumno model select
	 */
	public AlumnoModel getAlumnoModelSelect() {
		return alumnoModelSelect;
	}

	/**
	 * Sets the alumno model select.
	 *
	 * @param alumnoModelSelect the new alumno model select
	 */
	public void setAlumnoModelSelect(AlumnoModel alumnoModelSelect) {
		this.alumnoModelSelect = alumnoModelSelect;
	}

	/**
	 * Gets the list alumno model.
	 *
	 * @return the list alumno model
	 */
	public List<AlumnoModel> getListAlumnoModel() {
		return listAlumnoModel;
	}

	/**
	 * Sets the list alumno model.
	 *
	 * @param listAlumnoModel the new list alumno model
	 */
	public void setListAlumnoModel(List<AlumnoModel> listAlumnoModel) {
		this.listAlumnoModel = listAlumnoModel;
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
	public void setListAsistenciaAlumnosTutoria(List<AsistenciaTAlumBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}

	/**
	 * Gets the lista asistencia alumnos tutoria.
	 *
	 * @return the lista asistencia alumnos tutoria
	 */
	public List<TutoriaBO> getListaAsistenciaAlumnosTutoria() {
		return listaAsistenciaAlumnosTutoria;
	}

	/**
	 * Sets the lista asistencia alumnos tutoria.
	 *
	 * @param listaAsistenciaAlumnosTutoria the new lista asistencia alumnos tutoria
	 */
	public void setListaAsistenciaAlumnosTutoria(List<TutoriaBO> listaAsistenciaAlumnosTutoria) {
		this.listaAsistenciaAlumnosTutoria = listaAsistenciaAlumnosTutoria;
	}

	/**
	 * Gets the lista asistencia alumnos tutoria select.
	 *
	 * @return the lista asistencia alumnos tutoria select
	 */
	public TutoriaBO getListaAsistenciaAlumnosTutoriaSelect() {
		return listaAsistenciaAlumnosTutoriaSelect;
	}

	/**
	 * Sets the lista asistencia alumnos tutoria select.
	 *
	 * @param listaAsistenciaAlumnosTutoriaSelect the new lista asistencia alumnos tutoria select
	 */
	public void setListaAsistenciaAlumnosTutoriaSelect(TutoriaBO listaAsistenciaAlumnosTutoriaSelect) {
		this.listaAsistenciaAlumnosTutoriaSelect = listaAsistenciaAlumnosTutoriaSelect;
	}

	/**
	 * Gets the lista asistencia alumnos tutoria grid.
	 *
	 * @return the lista asistencia alumnos tutoria grid
	 */
	public List<TutoriaBO> getListaAsistenciaAlumnosTutoriaGrid() {
		return listaAsistenciaAlumnosTutoriaGrid;
	}

	/**
	 * Sets the lista asistencia alumnos tutoria grid.
	 *
	 * @param listaAsistenciaAlumnosTutoriaGrid the new lista asistencia alumnos tutoria grid
	 */
	public void setListaAsistenciaAlumnosTutoriaGrid(List<TutoriaBO> listaAsistenciaAlumnosTutoriaGrid) {
		this.listaAsistenciaAlumnosTutoriaGrid = listaAsistenciaAlumnosTutoriaGrid;
	}

	/**
	 * Gets the tutoria services.
	 *
	 * @return the tutoria services
	 */
	public TutoriaServices getTutoriaServices() {
		return tutoriaServices;
	}

	/**
	 * Sets the tutoria services.
	 *
	 * @param tutoriaServices the new tutoria services
	 */
	public void setTutoriaServices(TutoriaServices tutoriaServices) {
		this.tutoriaServices = tutoriaServices;
	}

	/**
	 * Gets the lista asistencia alumnos tutoria aux.
	 *
	 * @return the lista asistencia alumnos tutoria aux
	 */
	public TutoriaBO getListaAsistenciaAlumnosTutoriaAux() {
		return listaAsistenciaAlumnosTutoriaAux;
	}

	/**
	 * Sets the lista asistencia alumnos tutoria aux.
	 *
	 * @param listaAsistenciaAlumnosTutoriaAux the new lista asistencia alumnos tutoria aux
	 */
	public void setListaAsistenciaAlumnosTutoriaAux(TutoriaBO listaAsistenciaAlumnosTutoriaAux) {
		this.listaAsistenciaAlumnosTutoriaAux = listaAsistenciaAlumnosTutoriaAux;
	}

	/**
	 * Gets the visualizar notas alumno grid.
	 *
	 * @return the visualizar notas alumno grid
	 */
	public List<NotasAlumnoExcelModel> getVisualizarNotasAlumnoGrid() {
		return visualizarNotasAlumnoGrid;
	}

	/**
	 * Sets the visualizar notas alumno grid.
	 *
	 * @param visualizarNotasAlumnoGrid the new visualizar notas alumno grid
	 */
	public void setVisualizarNotasAlumnoGrid(List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid) {
		this.visualizarNotasAlumnoGrid = visualizarNotasAlumnoGrid;
	}

	/**
	 * Gets the lista observaciones.
	 *
	 * @return the lista observaciones
	 */
	public List<TutoriaModel> getListaObservaciones() {
		return listaObservaciones;
	}

	/**
	 * Sets the lista observaciones.
	 *
	 * @param listaObservaciones the new lista observaciones
	 */
	public void setListaObservaciones(List<TutoriaModel> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}

	/**
	 * Checks if is activar observacion.
	 *
	 * @return true, if is activar observacion
	 */
	public boolean isActivarObservacion() {
		return activarObservacion;
	}

	/**
	 * Sets the activar observacion.
	 *
	 * @param activarObservacion the new activar observacion
	 */
	public void setActivarObservacion(boolean activarObservacion) {
		this.activarObservacion = activarObservacion;
	}

	/**
	 * Checks if is activar tabla notas.
	 *
	 * @return true, if is activar tabla notas
	 */
	public boolean isActivarTablaNotas() {
		return activarTablaNotas;
	}

	/**
	 * Sets the activar tabla notas.
	 *
	 * @param activarTablaNotas the new activar tabla notas
	 */
	public void setActivarTablaNotas(boolean activarTablaNotas) {
		this.activarTablaNotas = activarTablaNotas;
	}
}
