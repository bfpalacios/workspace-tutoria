package pe.edu.sistemas.unayoe.controlador;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.AlumnoModel;
import pe.edu.sistemas.unayoe.model.TutoriaModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ProfesorBO;
import pe.edu.sistemas.unayoe.unayoe.bo.SesionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

@Controller("gestionarActasMBean")
@ViewScoped
public class GestionarActasMBean {
	@Autowired
	private AlumnoModel alumnoModel;
	@Autowired
	private TutoriaModel tutoriaModel;
	@Autowired
	private ComunServices comunServices;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private TutoriaServices tutoriaServices;
	@Autowired
	private UsuarioServices usuarioServices;

	private TutoriaModel tutoriaModelSelect;
	private boolean desactivarCarga;
	private boolean desactivarDescarga;
	private boolean desactivarTarea;
	private StreamedContent file;

	private List<TutoriaModel> listaObservaciones;
	/*-------------------------------------------------------*/
	/*
	 * Estas variables no deben ser accedidas por ninguna clase por ello, no
	 * tendrán métodos GET y SET
	 */
	private int PROCESO;
	private int MODO;
	private int MODO_AUX;
	private int ANIO_ACTUAL;
	private int PERIODO_ACTUAL;

	private static int PROCESO_OBSERVADOS = 1;
	private static int PROCESO_REGULARES = 2;

	private static int MODO_ADMIN = 1;
	private static int MODO_TUTOR = 2;
	private static int MODO_OCAA = 3;
	private static int MODO_UNAYOE = 4;
	private static int MODO_DECANO = 5;

	// private static String REGISTRO_CORRECTO = "R";
	// private static String REGISTRO_EXTEMPORANEO = "E";
	private static String REGISTRO_FALTANTE = "F";
	/*-------------------------------------------------------*/
	private Date date;
	private UploadedFile acta;

	public GestionarActasMBean() {
		setTutoriaModelSelect(new TutoriaModel());
		System.out.println("carga : " + getDesactivarCarga());
		setDesactivarCarga(true);
		setDesactivarDescarga(true);
		setDesactivarTarea(true);
		System.out.println("TAREA : " + isDesactivarTarea());
		date = new Date();
		
	}

	public void inicializarClases() {
		if (getTutoriaModelSelect() != null) {
			setTutoriaModelSelect(null);
			setTutoriaModelSelect(new TutoriaModel());
		}

		if (getTutoriaModel() != null) {
			setTutoriaModel(null);
			setTutoriaModel(new TutoriaModel());
		}
		setDesactivarCarga(true);
		setDesactivarDescarga(true);
		setDesactivarTarea(true);

		setListaObservaciones(new ArrayList<TutoriaModel>());
	}

	public void listarCursos() {
		System.out.println("Listando los cursos:");
		List<CursoBO> listarCursos = null;
		try {
			listarCursos = cursoServices.listarCursosTutorias();
			tutoriaModel.setListarCursos(listarCursos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatearFecha(String fecha) {
		String fechaFormateada = "";
		try {
			SimpleDateFormat entrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat salida = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaAuxiliar = entrada.parse(fecha);
			fechaFormateada = salida.format(fechaAuxiliar);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return fechaFormateada;
	}

	public void algo() {
		System.out.println("Hola gg");
	}

	public void cargarTareas() {

		try {

			TutoriaBO sesionTutoria = comunServices.obtenerSesionTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
					getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
					String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(), MODO,
					PROCESO);

			if (getListaObservaciones().size() > 0) {
				for (TutoriaModel observacionTutoria : getListaObservaciones()) {
					observacionTutoria.setSesion(sesionTutoria.getSesion());
					getTutoriaServices().guardarObservacionesAsistencia(sesionTutoria.gettCodigo(),
							observacionTutoria.getObservacion(), observacionTutoria.getCriticidad(),
							observacionTutoria.getSesion(), observacionTutoria.getRazon(),
							observacionTutoria.getFechaFin());
				}
				mostrarMensaje(12);
				inicializarClases();
				listarCursos();
			} else {
				mostrarMensaje(11);
			}

		} catch (IOException IOEx) {
			IOEx.printStackTrace();
		} catch (SerialException SEx) {
			SEx.printStackTrace();
		} catch (SQLException SQLEx) {
			SQLEx.printStackTrace();
		} catch (NumberFormatException NFE) {
			NFE.printStackTrace();
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void cargarTareasTutor() {

		try {

			TutoriaBO sesionTutoria = comunServices.obtenerSesionTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
					getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
					String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(), MODO,
					PROCESO);

			if (getListaObservaciones().size() > 0) {
				for (TutoriaModel observacionTutoria : getListaObservaciones()) {
					observacionTutoria.setSesion(sesionTutoria.getSesion());
					getTutoriaServices().guardarObservacionesAsistencia(sesionTutoria.gettCodigo(),
							observacionTutoria.getObservacion(), observacionTutoria.getCriticidad(),
							observacionTutoria.getSesion(), observacionTutoria.getRazon(),
							observacionTutoria.getFechaFin());
				}
				mostrarMensaje(12);
				inicializarClases();
				listarCursosxDocente();
			} else {
				mostrarMensaje(11);
			}

		} catch (IOException IOEx) {
			IOEx.printStackTrace();
		} catch (SerialException SEx) {
			SEx.printStackTrace();
		} catch (SQLException SQLEx) {
			SQLEx.printStackTrace();
		} catch (NumberFormatException NFE) {
			NFE.printStackTrace();
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void gestorCargaActas(FileUploadEvent e) {
		try {
			UploadedFile archivoPDF = e.getFile();
			byte[] contenidoArchivo = IOUtils.toByteArray(archivoPDF.getInputstream());

			TutoriaBO sesionTutoria = comunServices.obtenerSesionTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
					getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
					String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(), MODO,
					PROCESO);

			SesionBO actaTutoria = tutoriaServices.obtenerActaTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
					getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
					String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(), PROCESO,
					MODO);

			String nombreActa = sesionTutoria.gettAnio() + sesionTutoria.gettPeriodo() + sesionTutoria.gettCodigo()
					+ String.valueOf(sesionTutoria.getSesion());

			if (actaTutoria.getEstadoActa() < 2) {
				if (actaTutoria.getEstadoActa() == 0) {
					tutoriaServices.guardarActaTutoria(Integer.parseInt(sesionTutoria.gettAnio()),
							Integer.parseInt(sesionTutoria.gettPeriodo()), sesionTutoria.gettCodigo(),
							sesionTutoria.getSesion(), contenidoArchivo, nombreActa, 1);
					mostrarMensaje(4);
				} else {
					tutoriaServices.guardarActaTutoria(Integer.parseInt(sesionTutoria.gettAnio()),
							Integer.parseInt(sesionTutoria.gettPeriodo()), sesionTutoria.gettCodigo(),
							sesionTutoria.getSesion(), contenidoArchivo, archivoPDF.getFileName(), 2);
					mostrarMensaje(5);
				}
			} else {
				mostrarMensaje(3);
			}
		} catch (IOException IOEx) {
			IOEx.printStackTrace();
		} catch (SerialException SEx) {
			SEx.printStackTrace();
		} catch (SQLException SQLEx) {
			SQLEx.printStackTrace();
		} catch (NumberFormatException NFE) {
			NFE.printStackTrace();
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public void actualizarDocente(ValueChangeEvent e) throws Exception {
		setListaObservaciones(new ArrayList<TutoriaModel>());
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
		getTutoriaModel().setListarTutores(listaProfesores);
	}

	public void actualizarAlumno(ValueChangeEvent e) throws Exception {
		String codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		getTutoriaModelSelect().setpCodigo(codDocente);

		if (getTutoriaModel().getListarAlumnos() != null) {
			getTutoriaModel().getListarAlumnos().clear();
		}

		List<AlumnoBO> listaAlumnos = new ArrayList<AlumnoBO>();
		listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, getTutoriaModelSelect().getcCodigo(), PROCESO,
				MODO);
		getTutoriaModel().setListarAlumnos(listaAlumnos);
	}

	public void actualizarAlumnoGenerico(ValueChangeEvent e) throws Exception {
		setListaObservaciones(new ArrayList<TutoriaModel>());
		String codDocente = "";
		String codCurso = "";
		if (MODO == MODO_ADMIN) {
//			codCurso = getTutoriaModelSelect().getcCodigo() == null ? "" : getTutoriaModelSelect().getcCodigo();
			codCurso= (String) (e.getNewValue() == null ? "" : e.getNewValue());
			codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		} else {
			if (MODO == MODO_TUTOR) {
				codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue());
				codDocente = obtenerUsuario().getUsername();
			}else{
	//			if (MODO == MODO_UNAYOE) {
				//	codCurso = (String) (e.getNewValue() == null ? "" : e.getNewValue()););
		//			codDocente = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		//		}
			}
		}
		List<AlumnoBO> listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, codCurso, PROCESO, MODO);
		getTutoriaModel().setListarAlumnos(listaAlumnos);
	}

	public void listarCursosxDocente() throws Exception {
		String codDocente = "";
		if (MODO == MODO_ADMIN) {
			codDocente = getTutoriaModelSelect().getpCodigo() == null ? "" : getTutoriaModelSelect().getpCodigo();
		} else {
			if (MODO == MODO_TUTOR) {
				codDocente = obtenerUsuario().getUsername();
				getTutoriaModelSelect().setpCodigo(getUsuarioServices().buscarUsuarioEquivalencia(codDocente));
			}
		}
		List<CursoBO> listaCursos = cursoServices.listarCursosDocente(codDocente, PROCESO, MODO);
		getTutoriaModel().setListarCursos(listaCursos);
	}

	public void actualizarSesion(ValueChangeEvent e) throws Exception {
		setListaObservaciones(new ArrayList<TutoriaModel>());
		String codDocente = "";
		String codAlumno = (String) (e.getNewValue() == null ? "" : e.getNewValue());
		getTutoriaModelSelect().setaCodigo(codAlumno);
		AlumnoBO alumno = tutoriaServices.buscarDatosAlumno(codAlumno);
		getTutoriaModelSelect().setaNombre(alumno.getaNombre() + " " + alumno.getaApellido());
		if (MODO == MODO_ADMIN) {
			codDocente = getTutoriaModelSelect().getpCodigo() == null ? "" : getTutoriaModelSelect().getpCodigo();
		} else {
			if (MODO == MODO_TUTOR) {
				codDocente = obtenerUsuario().getUsername();
				getTutoriaModelSelect().setpCodigo(codDocente);
			}
		}

		List<SesionBO> listaSesiones = new ArrayList<SesionBO>();
		listaSesiones = tutoriaServices.listarSesionTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
				getTutoriaModelSelect().getcCodigo(), codDocente, codAlumno, PROCESO, MODO);

		List<SesionBO> sesiones = new ArrayList<SesionBO>();
		for (SesionBO sesion : listaSesiones) {
			sesion.setDescSesion(
					String.valueOf(sesion.getNroSesion()) + " - " + formatearFecha(sesion.getFechaTutoria()));
			sesiones.add(sesion);
		}
		getTutoriaModel().setListarSesiones(sesiones);

	}

	public User obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User usuario = null;
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			usuario = (User) auth.getPrincipal();
		}
		return usuario;
	}

	public void validarCargaActas(ValueChangeEvent e) throws Exception {
		try {
			setListaObservaciones(new ArrayList<TutoriaModel>());
			int sesion = (Integer) (e.getNewValue() == null ? 0 : e.getNewValue());

			SesionBO actaTutoria = tutoriaServices.obtenerActaTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
					getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
					String.valueOf(getTutoriaModelSelect().getaCodigo()), sesion, PROCESO, MODO);
			if (actaTutoria != null) {

				if (actaTutoria.getEstadoSesion().equals(REGISTRO_FALTANTE)) {
					mostrarMensaje(1);
					setDesactivarCarga(true);
					setDesactivarTarea(true);
				} else {
					if (actaTutoria.getEstadoActa() == 2) {
						setDesactivarCarga(true);
						setDesactivarTarea(true);
						mostrarMensaje(3);
					} else {
						setDesactivarCarga(false);
						if (actaTutoria.getEstadoActa() == 0)
							setDesactivarTarea(true);
						else
							setDesactivarTarea(false);
					}
				}
			} else {
				setDesactivarTarea(true);
			}
		} catch (Exception exception) {

		}
	}

	public void agregarObservacion() {
		System.out.println("TAREA : " + isDesactivarTarea());
		if (!isDesactivarTarea()) {
			TutoriaModel observacionTutoria = new TutoriaModel();
			getListaObservaciones().add(observacionTutoria);
			System.out.println("YA HAY UNA TUTORIA CARGADA PARA TAREA");

		} else {
			mostrarMensaje(11);
			System.out.println("NO HAY UNA TUTORIA CARGADA PARA TAREA");
		}
	}

	public void validarSesion(ValueChangeEvent e) throws Exception {
		int sesion = (Integer) (e.getNewValue() == null ? 0 : e.getNewValue());
		getTutoriaModelSelect().setSesion(sesion);
	}

	public String descargarActa() {
		String pagina = "";
		String codProfesor = "";
		try {
			file = null;
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			if (MODO == 1) {
				codProfesor = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
						: getTutoriaModelSelect().getpCodigo();
			}
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();
			int sesion = getTutoriaModelSelect().getSesion() == 0 ? 0 : getTutoriaModelSelect().getSesion();

			if (validarOpciones(codCurso, codProfesor, codAlumno, sesion)) {
				SesionBO actaTutoria = tutoriaServices.obtenerActaTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
						getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
						String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(),
						PROCESO, MODO);

				if (actaTutoria.getEstadoSesion().equals(REGISTRO_FALTANTE)) {
					mostrarMensaje(1);
					setDesactivarCarga(true);
				} else {
					if (actaTutoria.getEstadoActa() == 0) {
						setDesactivarDescarga(true);
						mostrarMensaje(2);
					} else {
						InputStream datosActa = new ByteArrayInputStream(actaTutoria.getActa());
						setFile(new DefaultStreamedContent(datosActa, "application/pdf",
								actaTutoria.getNombreActa() + ".pdf"));

					}
				}
			}
		} catch (Exception e) {
			mostrarMensaje(6);
			e.printStackTrace();
		}
		switch (PROCESO) {
		case 1:
			switch (MODO_AUX) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/cargar/descargarActasTutoria.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/tutor/cargar/descargarActasTutoria.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloObservados/ocaa/cargar/descargarActasTutoria.xhtml";
				break;
			}
			break;

		case 2:
			switch (MODO_AUX) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/cargar/descargarActasTutoria.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/tutor/cargar/descargarActasTutoria.xhtml";
				break;
			case 3:
				pagina = "/paginas/ModuloRegulares/ocaa/cargar/descargarActasTutoria.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	public StreamedContent obtenerPDF() throws FileNotFoundException {
		String codProfesor = "";
		try {
			String codCurso = getTutoriaModelSelect().getcCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getcCodigo();
			if (MODO == 1) {
				codProfesor = getTutoriaModelSelect().getpCodigo() == "" ? "Invalido"
						: getTutoriaModelSelect().getpCodigo();
			}
			String codAlumno = getTutoriaModelSelect().getaCodigo() == "" ? "Invalido"
					: getTutoriaModelSelect().getaCodigo();
			int sesion = getTutoriaModelSelect().getSesion() == 0 ? 0 : getTutoriaModelSelect().getSesion();

			if (validarOpciones(codCurso, codProfesor, codAlumno, sesion)) {
				SesionBO actaTutoria = tutoriaServices.obtenerActaTutoria(ANIO_ACTUAL, PERIODO_ACTUAL,
						getTutoriaModelSelect().getcCodigo(), getTutoriaModelSelect().getpCodigo(),
						String.valueOf(getTutoriaModelSelect().getaCodigo()), getTutoriaModelSelect().getSesion(),
						PROCESO, MODO);

				if (actaTutoria.getEstadoSesion().equals(REGISTRO_FALTANTE)) {
					mostrarMensaje(1);
					setDesactivarCarga(true);
				} else {
					if (actaTutoria.getEstadoActa() == 0) {
						setDesactivarDescarga(true);
						mostrarMensaje(2);
					} else {
						InputStream datosActa = new ByteArrayInputStream(actaTutoria.getActa());
						file = new DefaultStreamedContent(datosActa, "application/pdf",
								actaTutoria.getNombreActa() + ".pdf");
					}
				}
			}
		} catch (Exception e) {
			mostrarMensaje(6);
			e.printStackTrace();
		}
		return file;
	}

	private boolean validarOpciones(String codCurso, String codProfesor, String codAlumno, int sesion) {
		boolean valido = true;

		if (codCurso.equals("Invalido")) {
			mostrarMensaje(7);
			valido = false;
		}

		if (codCurso.equals("Invalido")) {
			mostrarMensaje(8);
			valido = false;
		}

		if (codAlumno.equals("Invalido")) {
			mostrarMensaje(9);
			valido = false;
		}

		if (sesion == 0) {
			mostrarMensaje(10);
			valido = false;
		}
		return valido;
	}

	public String selectorCargaActas(int proceso, int modo) throws Exception {
		String pagina = "";

		CicloBO cicloActual = comunServices.buscarCicloActual();
		ANIO_ACTUAL = cicloActual.getAnio();
		PERIODO_ACTUAL = cicloActual.getPeriodo();
		inicializarClases();
		listarCursos();
		switch (proceso) {
		case 1:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				System.out.println("TAREA GG : " + isDesactivarTarea());
				pagina = "/paginas/ModuloObservados/admin/cargar/cargarActasTutoria.xhtml";
				break;

			case 2:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				setDesactivarCarga(true);
				setDesactivarTarea(true);

				pagina = "/paginas/ModuloObservados/ocaa/cargar/cargarActasTutoria.xhtml";
				break;

			case 3:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_TUTOR;
				listarCursosxDocente();
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				System.out.println("ENTRO");
				pagina = "/paginas/ModuloObservados/tutor/cargar/cargarActasTutoria.xhtml";
				break;
			case 5:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_DECANO;
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				System.out.println("TAREA GG : " + isDesactivarTarea());
				pagina = "/paginas/ModuloObservados/decano/cargar/cargarActasTutoria.xhtml";
				break;
			}
			break;
		case 2:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				pagina = "/paginas/ModuloRegulares/admin/cargar/cargarActasTutoria.xhtml";
				break;

			case 2:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				pagina = "/paginas/ModuloRegulares/ocaa/cargar/cargarActasTutoria.xhtml";
				break;
			case 3:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_TUTOR;
				listarCursosxDocente();
				setDesactivarCarga(true);
				setDesactivarTarea(true);
				System.out.println("ENTRO");
				pagina = "/paginas/ModuloRegulares/tutor/cargar/cargarActasTutoria.xhtml";
				break;
			}
			break;
		}
		System.out.println("retornar pagina" + pagina);
		return pagina;
	}

	public String selectorDescargaActas(int proceso, int modo) throws Exception {
		String pagina = "";

		CicloBO cicloActual = comunServices.buscarCicloActual();
		ANIO_ACTUAL = cicloActual.getAnio();
		PERIODO_ACTUAL = cicloActual.getPeriodo();
		inicializarClases();
		switch (proceso) {
		case 1:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_ADMIN;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloObservados/admin/cargar/descargarActasTutoria.xhtml";
				break;

			case 2:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_OCAA;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloObservados/ocaa/cargar/descargarActasTutoria.xhtml";
				break;

			case 3:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_TUTOR;
				MODO_AUX = MODO_TUTOR;
				listarCursosxDocente();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloObservados/tutor/cargar/descargarActasTutoria.xhtml";
				break;

			case 4:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_UNAYOE;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloObservados/unayoe/cargar/descargarActasTutoria.xhtml?faces-redirect=true";
				break;
			case 5:
				PROCESO = PROCESO_OBSERVADOS;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_DECANO;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloObservados/decano/cargar/descargarActasTutoria.xhtml?faces-redirect=true";
				break;
			}
			break;
		case 2:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_ADMIN;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloRegulares/admin/cargar/descargarActasTutoria.xhtml";
				break;

			case 2:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_OCAA;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloRegulares/ocaa/cargar/descargarActasTutoria.xhtml";
				break;

			case 3:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_TUTOR;
				MODO_AUX = MODO_TUTOR;
				listarCursosxDocente();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloRegulares/tutor/cargar/descargarActasTutoria.xhtml";
				break;

			case 4:
				PROCESO = PROCESO_REGULARES;
				MODO = MODO_ADMIN;
				MODO_AUX = MODO_UNAYOE;
				listarCursos();
				setDesactivarDescarga(true);
				pagina = "/paginas/ModuloRegulares/unayoe/cargar/descargarActasTutoria.xhtml";
				break;
			
			}
			break;
		}
		return pagina;
	}

	private void mostrarMensaje(int opcionMensaje) {
		FacesMessage message = null;

		switch (opcionMensaje) {
		case 1:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro de sesión inexistente",
					"La sesión de tutoría todavía no ha sido registrada");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acta inexistente",
					"No hay un acta cargada para esta sesión");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Límite de actas cargadas",
					"El proceso de carga de tutorías para esta sesión ha finalizado");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carga correcta",
					"El documento se ha cargado correctamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización correcta",
					"El documento se ha actualizado correctamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error",
					"Ha ocurrido un error en el proceso de descarga del acta");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un curso");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un docente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar un alumno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 10:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Debe seleccionar una sesión");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 11:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error",
					"No se encontró una sesión de tutoría con acta para registrar tareas");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		case 12:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Tareas registrada con éxito");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			break;
		}
	}

	public AlumnoModel getAlumnoModel() {
		return alumnoModel;
	}

	public void setAlumnoModel(AlumnoModel alumnoModel) {
		this.alumnoModel = alumnoModel;
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

	public boolean getDesactivarCarga() {
		return desactivarCarga;
	}

	public void setDesactivarCarga(boolean desactivarCarga) {
		this.desactivarCarga = desactivarCarga;
	}

	public boolean isDesactivarDescarga() {
		return desactivarDescarga;
	}

	public void setDesactivarDescarga(boolean desactivarDescarga) {
		this.desactivarDescarga = desactivarDescarga;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public UsuarioServices getUsuarioServices() {
		return usuarioServices;
	}

	public void setUsuarioServices(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	public List<TutoriaModel> getListaObservaciones() {
		return listaObservaciones;
	}

	public void setListaObservaciones(List<TutoriaModel> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String fecha() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		return formateador.format(ahora);
	}

	public UploadedFile getActa() {
		return acta;
	}

	public void setActa(UploadedFile acta) {
		this.acta = acta;
	}

	public TutoriaServices getTutoriaServices() {
		return tutoriaServices;
	}

	public void setTutoriaServices(TutoriaServices tutoriaServices) {
		this.tutoriaServices = tutoriaServices;
	}

	public boolean isDesactivarTarea() {
		return desactivarTarea;
	}

	public void setDesactivarTarea(boolean desactivarTarea) {
		this.desactivarTarea = desactivarTarea;
	}

}
