package pe.edu.sistemas.unayoe.controlador;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;

import org.springframework.stereotype.Controller;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.model.DisponibilidadModel;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class DisponibilidadMBean.
 */
@Controller("disponibilidadMBean")
@ViewScoped
public class DisponibilidadMBean {
	
	/** The disponibilidad model. */
	@Autowired
	private DisponibilidadModel disponibilidadModel;
	
	/** The tutoria services. */
	@Autowired
	private TutoriaServices tutoriaServices;
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;
	
	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;

	/** The listar cursos. */
	private List<ClaseMaestra> listarCursos;
	
	/** The lista disponibilidades. */
	private List<DisponibilidadBO> listaDisponibilidades;
	
	/** The disponibilidad model select. */
	private DisponibilidadModel disponibilidadModelSelect;
	
	/** The lista disponibilidad. */
	private List<DisponibilidadModel> listaDisponibilidad;
	
	/** The lista disponibilidad grid. */
	private List<DisponibilidadModel> listaDisponibilidadGrid;

	/** The proceso registro. */
	private int PROCESO_REGISTRO;// 1 docente , 2 alumno para registros
	
	/** The proceso busqueda. */
	private int PROCESO_BUSQUEDA;// 1 docente , 2 alumno para visualizaciones
	
	/** The modo. */
	private int MODO; // modo los siguentes
	
	/** The modo admin. */
	private static int MODO_ADMIN = 1;
	
	/** The modo tutor. */
	private static int MODO_TUTOR = 2;
	
	/** The modo alumno. */
	private static int MODO_ALUMNO = 3;
	
	/** The modo ocaa. */
	private static int MODO_OCAA = 4;

	/** The proceso tutor. */
	private static int PROCESO_TUTOR = 1;
	
	/** The proceso alumno. */
	private static int PROCESO_ALUMNO = 2;

	/**
	 * Instantiates a new disponibilidad M bean.
	 */
	public DisponibilidadMBean() {
		inicializarClases();
	}

	/**
	 * Inicializar clases.
	 */
	public void inicializarClases() {
		setListarCursos(new ArrayList<ClaseMaestra>());
		setDisponibilidadModelSelect(new DisponibilidadModel());
		setListaDisponibilidades(new ArrayList<DisponibilidadBO>());
		setListaDisponibilidad(new ArrayList<DisponibilidadModel>());
		setListaDisponibilidadGrid(new ArrayList<DisponibilidadModel>());
	}

	/**
	 * Limpiar clases.
	 */
	public void limpiarClases() {
		if (getDisponibilidadModel() != null) {
			setDisponibilidadModel(null);
			setDisponibilidadModel(new DisponibilidadModel());
		}

		if (getListaDisponibilidadGrid() != null) {
			setListaDisponibilidadGrid(null);
			setListaDisponibilidadGrid(new ArrayList<DisponibilidadModel>());
		}
	}

	/**
	 * Listar area conocimiento.
	 */
	public void listarAreaConocimiento() {
		try {
			List<AreaConocimientoBO> listaAreaConocimiento = tutoriaServices.listarAreaConocimiento();
			getDisponibilidadModel().setListaAreaConocimiento(listaAreaConocimiento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar docente regular.
	 */
	public void listarDocenteRegular() {
		try {

			List<ProfesorBO> listaTutoresRegulares = tutoriaServices.listarTutoresRegulares();
			System.out.println("listadocenteregular");
			getDisponibilidadModel().setListaTutoresRegulares(listaTutoresRegulares);
			for (ProfesorBO v : listaTutoresRegulares) {
				System.out.print(v.getpCodigo() + " ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar alumno regular.
	 */
	public void listarAlumnoRegular() {
		try {
			List<AlumnoBO> listaAlumnosRegulares = tutoriaServices.listarAlumnosRegulares();
			getDisponibilidadModel().setListaAlumnosRegulares(listaAlumnosRegulares);
			System.out.println("listarAlumnosRegular");
			for (AlumnoBO v : listaAlumnosRegulares) {
				System.out.print(v.getaCodigo() + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar hora inicio.
	 */
	public void listarHoraInicio() {
		List<ClaseMaestra> listaHoraInicio = null;
		try {
			String tabla = "HORA";
			String campo = "HORA_INICIO";
			listaHoraInicio = tutoriaServices.listarHoraInicio(tabla, campo);

			getDisponibilidadModel().setListaHoraInicio(listaHoraInicio);
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
			listarCursos = cursoServices.listarCursos();
			getDisponibilidadModel().setListaCursos(listarCursos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualizar area conocimiento.
	 *
	 * @param e the e
	 */
	public void actualizarAreaConocimiento(ValueChangeEvent e) {
		try {
			String codAreaConocimiento = (String) (e.getNewValue() == null ? "" : e.getNewValue());
			List<CursoBO> listaCursos = tutoriaServices.listarCursosxAreaConocimiento(codAreaConocimiento);
			getDisponibilidadModel().setListaCursos(listaCursos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Actualizar hora fin.
	 *
	 * @param e the e
	 */
	public void actualizarHoraFin(ValueChangeEvent e) {
		try {
			int idHoraInicio = Integer.parseInt((String) (e.getNewValue() == null ? "0" : e.getNewValue()));
			List<ClaseMaestra> listaHoraFin = new ArrayList<ClaseMaestra>();
			;
			listaHoraFin = comunServices.actualizarHoraFin(idHoraInicio);
			getDisponibilidadModel().setListaHoraFin(listaHoraFin);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
		System.out.println("PROCESO DE BUSQUEDA_docente " + PROCESO_BUSQUEDA);
		PROCESO_BUSQUEDA = 1;
		switch (PROCESO_BUSQUEDA) {
		case 2:
			listaProfesores = tutoriaServices.listarTutoresObservados(codCurso);
			break;
		case 1:
			listaProfesores = tutoriaServices.listarTutoresRegulares(codCurso);
			break;
		}
		// se añadio esto
		// PROCESO_BUSQUEDA=2;
		getDisponibilidadModel().setListaTutoresRegulares(listaProfesores);
	}

	/**
	 * Actualizar alumno.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void actualizarAlumno(ValueChangeEvent e) throws Exception {
		String codCurso = getDisponibilidadModelSelect().getCodCurso() == null ? ""
				: getDisponibilidadModelSelect().getCodCurso();
		String codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		System.out.println("codcurso " + codCurso);
		List<AlumnoBO> listaAlumnos = alumnoServices.listarAlumnoDisponibilidad(codCurso);

		System.out.println(" size " + listaAlumnos.size());

		getDisponibilidadModel().setListaAlumnosRegulares(listaAlumnos);
	}

	/**
	 * Agregar disponibilidad.
	 *
	 * @return the string
	 */
	public String agregarDisponibilidad() {
		String pagina = "";
		try {
			String codDocente = "";
			String codAlumno = "";
			DisponibilidadModel disponibilidadHoraria = new DisponibilidadModel();

			if (getListaDisponibilidadGrid().size() == 3) {
				mostrarMensaje(8);
			} else {
				String codAreaConocimiento = getDisponibilidadModelSelect().getCodAreaConocimiento() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getCodAreaConocimiento();
				String codCurso = getDisponibilidadModelSelect().getCodCurso() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getCodCurso();
				String dia = getDisponibilidadModelSelect().getDia() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getDia();
				String horaInicio = getDisponibilidadModelSelect().getHoraInicio() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getHoraInicio();
				String horaFin = getDisponibilidadModelSelect().getHoraFin() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getHoraFin();

				if (MODO == MODO_ADMIN) {
					if (PROCESO_REGISTRO == PROCESO_TUTOR) {
						codDocente = getDisponibilidadModelSelect().getCodDocente() == "" ? "Invalido"
								: getDisponibilidadModelSelect().getCodDocente();
					} else {
						if (PROCESO_REGISTRO == PROCESO_ALUMNO) {
							codAlumno = getDisponibilidadModelSelect().getCodAlumno() == "" ? "Invalido"
									: getDisponibilidadModelSelect().getCodAlumno();
						}
					}
				}

				if (validarCamposDisponibilidad(codAreaConocimiento, codCurso, dia, horaInicio, horaFin, codDocente,
						codAlumno)) {
					AreaConocimientoBO areaConocimiento = comunServices
							.buscarDatosAreaConocimiento(codAreaConocimiento);
					CursoBO curso = comunServices.buscarDatosCurso(codCurso);

					disponibilidadHoraria.setNomAreaConocimiento(areaConocimiento.getNomAreaConocimiento());
					disponibilidadHoraria.setCodAreaConocimiento(areaConocimiento.getCodAreaConocimiento());
					disponibilidadHoraria.setCodCurso(curso.getcCodigo());
					disponibilidadHoraria.setNomCurso(curso.getNombre());
					disponibilidadHoraria.setDia(getDisponibilidadModelSelect().getDia());
					disponibilidadHoraria.setHoraInicio(getDisponibilidadModelSelect().getHoraInicio() + ":00");
					disponibilidadHoraria.setHoraFin(getDisponibilidadModelSelect().getHoraFin() + ":00");
					disponibilidadHoraria.setCodDocente(getDisponibilidadModelSelect().getCodDocente());
					disponibilidadHoraria.setCodAlumno(getDisponibilidadModelSelect().getCodAlumno());
					System.out.println("codDocente" + getDisponibilidadModelSelect().getCodDocente());
					System.out.println("codAlmnno" + getDisponibilidadModelSelect().getCodAlumno());
					getListaDisponibilidadGrid().add(disponibilidadHoraria);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (MODO) {
		case 1:
			switch (PROCESO_REGISTRO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
				break;
			}
			break;

		case 2:
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
			break;
		case 3:
			pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Mostrar disponibilidad.
	 *
	 * @return the string
	 */
	public String mostrarDisponibilidad() {
		String pagina = "";
		String codAlumno = "";
		String codUsuario = "";
		int tipoUsuario = 0;
		String codDocente = "";
		try {
			String codCurso = getDisponibilidadModelSelect().getCodCurso() == "" ? "Invalido"
					: getDisponibilidadModelSelect().getCodCurso();
			System.out.println("p_busq" + PROCESO_BUSQUEDA);
			System.out.println("p_alum" + PROCESO_ALUMNO);
			if (PROCESO_BUSQUEDA == PROCESO_ALUMNO) {
				codAlumno = getDisponibilidadModelSelect().getCodAlumno() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getCodAlumno();
			} else {
				codDocente = getDisponibilidadModelSelect().getCodDocente() == "" ? "Invalido"
						: getDisponibilidadModelSelect().getCodDocente();

			}

			System.out.println("codC " + codCurso);
			System.out.println("codD " + codDocente);
			System.out.println("codA " + getDisponibilidadModelSelect().getCodAlumno());
			if (validarCamposDisponibilidad("", codCurso, "", "", "", codDocente, codAlumno)) {
				switch (PROCESO_BUSQUEDA) {
				case 1:
					codUsuario = codDocente;
					tipoUsuario = 1;
					break;
				case 2:
					codUsuario = codAlumno;
					tipoUsuario = 2;
					break;
				}
				List<DisponibilidadBO> listaDisponibilidades = tutoriaServices.listarDisponibilidades(codCurso,
						codUsuario, tipoUsuario);
				setListaDisponibilidades(listaDisponibilidades);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		switch (MODO) {
		case 1:
			switch (PROCESO_BUSQUEDA) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadDocente.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadAlumno.xhtml";
				break;
			}
			break;
		case 4:
			switch (PROCESO_BUSQUEDA) {
			case 1:
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadDocente.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadAlumno.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Validar campos disponibilidad.
	 *
	 * @param codAreaConocimiento the cod area conocimiento
	 * @param codCurso the cod curso
	 * @param dia the dia
	 * @param horaInicio the hora inicio
	 * @param horaFin the hora fin
	 * @param codDocente the cod docente
	 * @param codAlumno the cod alumno
	 * @return true, if successful
	 */
	private boolean validarCamposDisponibilidad(String codAreaConocimiento, String codCurso, String dia,
			String horaInicio, String horaFin, String codDocente, String codAlumno) {
		boolean valido = true;

		if (codAreaConocimiento.equals("Invalido")) {
			mostrarMensaje(1);
			valido = false;
		}

		if (codCurso.equals("Invalido")) {
			mostrarMensaje(2);
			valido = false;
		}

		if (dia.equals("Invalido")) {
			mostrarMensaje(3);
			valido = false;
		}

		if (horaInicio.equals("Invalido")) {
			mostrarMensaje(4);
			valido = false;
		}

		if (horaFin.equals("Invalido")) {
			mostrarMensaje(5);
			valido = false;
		}

		if (MODO == MODO_ADMIN) {
			if (PROCESO_REGISTRO == PROCESO_TUTOR) {
				if (codDocente.equals("Invalido")) {
					mostrarMensaje(10);
					valido = false;
				}
			} else {
				if (PROCESO_REGISTRO == PROCESO_ALUMNO) {
					if (codAlumno.equals("Invalido")) {
						mostrarMensaje(11);
						valido = false;
					}
				}
			}
		}
		return valido;
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
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un área del conocimiento");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un curso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un día");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una hora de inicio");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una hora de fin");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto - ",
					"Las disponibilidades se guardaron correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido - ",
					"Ha ocurrido un error en el registro de las disponibilidades");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Límite de disponibilidades",
					"Ha llegado al límite de registros de disponibilidad");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sin disponibilidades",
					"Debe ingresar al menos una disponibilidad");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 10:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un tutor");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 11:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un alumno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		}
	}

	/**
	 * Convertir A disponibilidad BO.
	 *
	 * @param disponibilidad the disponibilidad
	 * @return the disponibilidad BO
	 */
	public DisponibilidadBO convertirADisponibilidadBO(DisponibilidadModel disponibilidad) {
		DisponibilidadBO disponibilidadUsuario = new DisponibilidadBO();

		disponibilidadUsuario.setCodAreaConocimiento(disponibilidad.getCodAreaConocimiento());
		disponibilidadUsuario.setNomAreaConocimiento(disponibilidad.getNomAreaConocimiento());
		disponibilidadUsuario.setCodCurso(disponibilidad.getCodCurso());
		disponibilidadUsuario.setNomCurso(disponibilidad.getNomCurso());
		disponibilidadUsuario.setCodDocente(disponibilidad.getCodDocente());
		disponibilidadUsuario.setNomDocente(disponibilidad.getNomDocente());
		disponibilidadUsuario.setCodAlumno(disponibilidad.getCodAlumno());
		disponibilidadUsuario.setNomAlumno(disponibilidad.getNomAlumno());
		disponibilidadUsuario.setDia(disponibilidad.getDia());
		disponibilidadUsuario.setHoraInicio(disponibilidad.getHoraInicio());
		disponibilidadUsuario.setHoraFin(disponibilidad.getHoraFin());
		return disponibilidadUsuario;
	}

	/**
	 * Guardar registro disponibilidad.
	 *
	 * @return the string
	 */
	public String guardarRegistroDisponibilidad() {
		String pagina = "";
		String usuario = "";
		List<DisponibilidadModel> listaDisponibilidades = getListaDisponibilidadGrid();

		try {
			if (listaDisponibilidades.size() == 0) {
				mostrarMensaje(9);
			} else {
				for (DisponibilidadModel disponibilidad : listaDisponibilidades) {
					if (MODO == MODO_ADMIN) {
						if (PROCESO_REGISTRO == PROCESO_TUTOR) {
							usuario = disponibilidad.getCodDocente();
						} else {
							if (PROCESO_REGISTRO == PROCESO_ALUMNO) {
								usuario = disponibilidad.getCodAlumno();
							}
						}
					} else {
						if (MODO == MODO_TUTOR || MODO == MODO_ALUMNO) {
							usuario = obtenerUsuario();
						}
					}
					tutoriaServices.guardarRegistroDisponibilidad(convertirADisponibilidadBO(disponibilidad), usuario,
							MODO, PROCESO_REGISTRO);
					mostrarMensaje(6);
					limpiarClases();
					listarAreaConocimiento();
					listarHoraInicio();
				}
			}
		} catch (Exception Ex) {
			mostrarMensaje(7);
			Ex.printStackTrace();
		}
		switch (MODO) {
		case 1:
			switch (PROCESO_REGISTRO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
				break;
			}
			break;

		case 2:
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
			break;
		case 3:
			pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
			break;
		}
		return pagina;
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
	 * Buscar lista.
	 *
	 * @param <T> the generic type
	 * @param lista the lista
	 * @param m the m
	 * @return the list
	 */
	public static <T> List<T> buscarLista(List<T> lista, Matcher m) {
		List<T> resultado = new ArrayList<T>();
		for (T t : lista) {
			if (m.matches()) {
				resultado.add(t);
			}
		}
		return resultado;
	}

	/**
	 * Selector registro disponibilidad.
	 *
	 * @param modoUsuario the modo usuario
	 * @param tipoBusqueda the tipo busqueda
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorRegistroDisponibilidad(int modoUsuario, int tipoBusqueda) throws Exception {
		String pagina = "";
		inicializarClases();
		listarAreaConocimiento();
		listarHoraInicio();
		switch (modoUsuario) {
		case 1:
			switch (tipoBusqueda) {
			case 1:
				MODO = MODO_ADMIN;
				PROCESO_REGISTRO = PROCESO_TUTOR;
				listarDocenteRegular();
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
				break;
			case 2:
				MODO = MODO_ADMIN;
				PROCESO_REGISTRO = PROCESO_ALUMNO;
				listarAlumnoRegular();
				pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
				break;
			}
			break;
		case 2:
			MODO = MODO_TUTOR;
			pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml";
			break;
		case 3:
			MODO = MODO_ALUMNO;
			pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Selector visualizar disponibilidad.
	 *
	 * @param modoUsuario the modo usuario
	 * @param procesoBusqueda the proceso busqueda
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorVisualizarDisponibilidad(int modoUsuario, int procesoBusqueda) throws Exception {

		String pagina = "";
		inicializarClases();
		listarCursos();
		System.out.println("proc_busqueda" + procesoBusqueda);
		switch (modoUsuario) {
		case 1:
			switch (procesoBusqueda) {
			case 1:
				MODO = MODO_ADMIN;
				PROCESO_BUSQUEDA = PROCESO_TUTOR;
				System.out.println("proc_busqueda" + PROCESO_BUSQUEDA);
				pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadDocente.xhtml";
				break;
			case 2:
				MODO = MODO_ADMIN;
				PROCESO_BUSQUEDA = PROCESO_ALUMNO;
				System.out.println("proc_busqueda" + PROCESO_BUSQUEDA);
				pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadAlumno.xhtml";
				break;
			}
			break;
		case 2:
			switch (procesoBusqueda) {
			case 1:
				MODO = MODO_OCAA;
				PROCESO_BUSQUEDA = PROCESO_TUTOR;
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadDocente.xhtml";
				break;
			case 2:
				MODO = MODO_OCAA;
				PROCESO_BUSQUEDA = PROCESO_ALUMNO;
				pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadAlumno.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Gets the disponibilidad model.
	 *
	 * @return the disponibilidad model
	 */
	public DisponibilidadModel getDisponibilidadModel() {
		return disponibilidadModel;
	}

	/**
	 * Sets the disponibilidad model.
	 *
	 * @param disponibilidadModel the new disponibilidad model
	 */
	public void setDisponibilidadModel(DisponibilidadModel disponibilidadModel) {
		this.disponibilidadModel = disponibilidadModel;
	}

	/**
	 * Gets the disponibilidad model select.
	 *
	 * @return the disponibilidad model select
	 */
	public DisponibilidadModel getDisponibilidadModelSelect() {
		return disponibilidadModelSelect;
	}

	/**
	 * Sets the disponibilidad model select.
	 *
	 * @param disponibilidadModelSelect the new disponibilidad model select
	 */
	public void setDisponibilidadModelSelect(DisponibilidadModel disponibilidadModelSelect) {
		this.disponibilidadModelSelect = disponibilidadModelSelect;
	}

	/**
	 * Gets the lista disponibilidad.
	 *
	 * @return the lista disponibilidad
	 */
	public List<DisponibilidadModel> getListaDisponibilidad() {
		return listaDisponibilidad;
	}

	/**
	 * Sets the lista disponibilidad.
	 *
	 * @param listaDisponibilidad the new lista disponibilidad
	 */
	public void setListaDisponibilidad(List<DisponibilidadModel> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}

	/**
	 * Gets the lista disponibilidad grid.
	 *
	 * @return the lista disponibilidad grid
	 */
	public List<DisponibilidadModel> getListaDisponibilidadGrid() {
		return listaDisponibilidadGrid;
	}

	/**
	 * Sets the lista disponibilidad grid.
	 *
	 * @param listaDisponibilidadGrid the new lista disponibilidad grid
	 */
	public void setListaDisponibilidadGrid(List<DisponibilidadModel> listaDisponibilidadGrid) {
		this.listaDisponibilidadGrid = listaDisponibilidadGrid;
	}

	/**
	 * Gets the lista disponibilidades.
	 *
	 * @return the lista disponibilidades
	 */
	public List<DisponibilidadBO> getListaDisponibilidades() {
		return listaDisponibilidades;
	}

	/**
	 * Sets the lista disponibilidades.
	 *
	 * @param listaDisponibilidades the new lista disponibilidades
	 */
	public void setListaDisponibilidades(List<DisponibilidadBO> listaDisponibilidades) {
		this.listaDisponibilidades = listaDisponibilidades;
	}

	/**
	 * Gets the listar cursos.
	 *
	 * @return the listar cursos
	 */
	public List<ClaseMaestra> getListarCursos() {
		return listarCursos;
	}

	/**
	 * Sets the listar cursos.
	 *
	 * @param listarCursos the new listar cursos
	 */
	public void setListarCursos(List<ClaseMaestra> listarCursos) {
		this.listarCursos = listarCursos;
	}
}
