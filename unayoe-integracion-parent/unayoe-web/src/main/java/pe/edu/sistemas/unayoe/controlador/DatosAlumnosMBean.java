package pe.edu.sistemas.unayoe.controlador;

import javax.faces.bean.ViewScoped;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.DatosAlumnoExcelModel;
import pe.edu.sistemas.unayoe.core.util.FormatoExcel;
import pe.edu.sistemas.unayoe.model.ArchivoModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.GrupoServices;
import pe.edu.sistemas.unayoe.services.MatriculaServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosAlumnosMBean.
 * Esta clase se encarga de procesar la lectura del archivo excel
 * de los datos de los alumnos 
 * procesa el excel valida los campos y los envia a la vista
 * para mostrarlos
 * ademas gestiona que estos se puedan guardar a traves de los services
 */
@Controller("datosAlumnosMBean")
@ViewScoped
public class DatosAlumnosMBean {

	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The curso services. */
	@Autowired
	private CursoServices cursoServices;
	
	/** The grupo services. */
	@Autowired
	private GrupoServices grupoServices;
	
	/** The matricula services. */
	@Autowired
	private MatriculaServices matriculaServices;
	
	/** The tutoria services. */
	@Autowired
	private TutoriaServices tutoriaServices;
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;
	
	/** The datos alumno excel models. */
	@Autowired
	private List<DatosAlumnoExcelModel> datosAlumnoExcelModels;
	
	/** The datos alumno excel model. */
	@Autowired
	private DatosAlumnoExcelModel datosAlumnoExcelModel;
	
	/** The archivo model. */
	@Autowired
	private ArchivoModel archivoModel;

	/** The datos alumno excel model grid. */
	private List<DatosAlumnoExcelModel> datosAlumnoExcelModelGrid;
	
	/** The nombre archivo. */
	private String nombreArchivo;
	
	/** The file. */
	private UploadedFile file;

	/** The cod alumno. */
	private static int COD_ALUMNO = 0;
	
	/** The ape paterno. */
	private static int APE_PATERNO = 1;
	
	/** The ape materno. */
	private static int APE_MATERNO = 2;
	
	/** The nom alumno. */
	private static int NOM_ALUMNO = 3;
	
	/** The cod plan. */
	private static int COD_PLAN = 4;
	
	/** The cod curso. */
	private static int COD_CURSO = 5;
	
	/** The nom curso. */
	private static int NOM_CURSO = 6;
	
	/** The num cred. */
	private static int NUM_CRED = 7;
	
	/** The repitencias. */
	private static int REPITENCIAS = 8;
	
	/** The cod docente. */
	private static int COD_DOCENTE = 9;
	
	/** The nom docente. */
	private static int NOM_DOCENTE = 10;
	
	/** The frecuencia. */
	private static int FRECUENCIA = 11;
	
	/** The dia. */
	private static int DIA = 12;
	
	/** The hora inicio. */
	private static int HORA_INICIO = 13;
	
	/** The hora fin. */
	private static int HORA_FIN = 14;

	/** The alumno observado. */
	private static int ALUMNO_OBSERVADO = 1;

	/** The modo usuario. */
	private int MODO_USUARIO;
	
	/** The modo admin. */
	private static int MODO_ADMIN = 1;
	
	/** The modo ocaa. */
	private static int MODO_OCAA = 2;
	
	/** The modo decano. */
	private static int MODO_DECANO = 5;

	/**
	 * Instantiates a new datos alumnos M bean.
	 */
	public DatosAlumnosMBean() {
		inicializarClases();
	}

	/**
	 * Inicializar clases.
	 */
	private void inicializarClases() {
		setDatosAlumnoExcelModelGrid(new ArrayList<DatosAlumnoExcelModel>());
	}

	/**
	 * Reiniciar clases.
	 */
	private void reiniciarClases() {
		if (getDatosAlumnoExcelModelGrid() != null) {
			getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
		}
		datosAlumnoExcelModels.clear();
	}

	/**
	 * Validar formato columnas excel.
	 *
	 * @param fila_alumno the fila alumno
	 * @return true, if successful
	 */
	private boolean validarFormatoColumnasExcel(XSSFRow fila_alumno) {
		if (fila_alumno.getCell(COD_ALUMNO) == null)
			return false;
		else if (!fila_alumno.getCell(COD_ALUMNO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_1.getTituloColum())) return false;
			
		if (fila_alumno.getCell(APE_PATERNO) == null)
			return false;
		else if(!fila_alumno.getCell(APE_PATERNO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_2.getTituloColum())) return false;
		if (fila_alumno.getCell(APE_MATERNO) == null)
			return false;
		else if(!fila_alumno.getCell(APE_MATERNO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_3.getTituloColum()))return false;
		if (fila_alumno.getCell(NOM_ALUMNO) == null)
			return false;
		else if(!fila_alumno.getCell(NOM_ALUMNO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_4.getTituloColum()))return false;
		if (fila_alumno.getCell(COD_PLAN) == null)
			return false;
		else if(!fila_alumno.getCell(COD_PLAN).toString().toUpperCase().equals(FormatoExcel.COLUMNA_5.getTituloColum()))return false;
		if (fila_alumno.getCell(COD_CURSO) == null)
			return false;
		else if(!fila_alumno.getCell(COD_CURSO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_6.getTituloColum()))return false;
		if (fila_alumno.getCell(NOM_CURSO) == null)
			return false;
		else if(!fila_alumno.getCell(NOM_CURSO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_7.getTituloColum()))return false;
		if (fila_alumno.getCell(NUM_CRED) == null)
			return false;
		else if(!fila_alumno.getCell(NUM_CRED).toString().toUpperCase().equals(FormatoExcel.COLUMNA_8.getTituloColum()))return false;
		if (fila_alumno.getCell(REPITENCIAS) == null)
			return false;
		else if(!fila_alumno.getCell(REPITENCIAS).toString().toUpperCase().equals(FormatoExcel.COLUMNA_9.getTituloColum()))return false;
		if (fila_alumno.getCell(COD_DOCENTE) == null)
			return false;
		else if(!fila_alumno.getCell(COD_DOCENTE).toString().toUpperCase().equals(FormatoExcel.COLUMNA_10.getTituloColum()))return false;
		if (fila_alumno.getCell(NOM_DOCENTE) == null)
			return false;
		else if(!fila_alumno.getCell(NOM_DOCENTE).toString().toUpperCase().equals(FormatoExcel.COLUMNA_11.getTituloColum()))return false;
		if (fila_alumno.getCell(FRECUENCIA) == null)
			return false;
		else if(!fila_alumno.getCell(FRECUENCIA).toString().toUpperCase().equals(FormatoExcel.COLUMNA_12.getTituloColum()))return false;
		if (fila_alumno.getCell(DIA) == null)
			return false;
		else if(!fila_alumno.getCell(DIA).toString().toUpperCase().equals(FormatoExcel.COLUMNA_13.getTituloColum()))return false;
		if (fila_alumno.getCell(HORA_INICIO) == null)
			return false;
		else if(!fila_alumno.getCell(HORA_INICIO).toString().toUpperCase().equals(FormatoExcel.COLUMNA_14.getTituloColum()))return false;
		if (fila_alumno.getCell(HORA_FIN) == null)
			return false;
		else if(!fila_alumno.getCell(HORA_FIN).toString().toUpperCase().equals(FormatoExcel.COLUMNA_15.getTituloColum()))return false;
		
		return true;
	}

	/**
	 * Handle file upload.
	 *
	 * @param event the event
	 * @return the string
	 * @throws Exception the exception
	 */
	public String handleFileUpload(FileUploadEvent event) throws Exception {
		String pagina = "";
		System.out.println("cargar");
		boolean formatoExcelValido = true; // Indica si el formato de la columnas del excel son validos
		datosAlumnoExcelModels.clear();
		UploadedFile archivoCargado = event.getFile();
		InputStream file;

		try {
			file = event.getFile().getInputstream();
			XSSFWorkbook wb = new XSSFWorkbook(file);

			XSSFSheet ws = wb.getSheetAt(0);
			int rowNum = ws.getPhysicalNumberOfRows(); // Cantidad de fila del archivo
			//rowNum=9;										// excel

			System.out.println("rows "+ rowNum);

			/*
			 * Se limpia los elementos que contiene la lista
			 * "DatosAlumnoExcelModelGrid" para poder realizar la carga de la
			 * data
			 */
			if (getDatosAlumnoExcelModelGrid() != null) {
				getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
			}

			datosAlumnoExcelModels.removeAll(datosAlumnoExcelModels);

			CicloBO ciclo = comunServices.buscarCicloActual();

			/*
			 * Validar formato excel (columnas)
			 */
			formatoExcelValido = validarFormatoColumnasExcel(ws.getRow(0));
			if (formatoExcelValido) {
				for (int i = 1; i < rowNum; i++) {
					
					/*
					 * Realiza la validacion inicial del codigo de alumno
					 */
					XSSFRow row = ws.getRow(i);

					if (row.getCell(COD_ALUMNO) == null || row.getCell(COD_ALUMNO).toString() == "") {
						continue;
					}
					
					// validando repitencias
					Integer repitencias = validarDatoEntero(row.getCell(REPITENCIAS));	
					if(esRepitenciaInvalida(repitencias)){
						continue;
					}
					
					/*
					 * Fin validacion del codigo del alumno
					 */
					
					/*
					 * Empieza la carga de los datos del alumno
					 * al convertir tambien hace una validacion de todos los campos 
					 * asi que se vuelve a validar las repitencias
					 * para ponerle el valor de valido = Si o No
					 */
					DatosAlumnoExcelModel dataModel = convertirAModelAlumno(row);

					if (tutoriaServices.buscarTutoria(ciclo.getAnio(), ciclo.getPeriodo(), dataModel.getCod_curso(),
							dataModel.getCod_alumno(), dataModel.getCod_docente()) != "") {
						dataModel.setExiste("Si");
					} else {
						dataModel.setExiste("No");
					}

					datosAlumnoExcelModelGrid.add(dataModel);
					System.out.println("existe " + dataModel.getExiste());
					System.out.println("valido " + dataModel.getValido());
					if (dataModel.getExiste() != "Si" && dataModel.getValido() != "No") {
						datosAlumnoExcelModels.add(dataModel);
						System.out.println("se añade a datosalumnosexcelmodels");
					}else{
						System.out.println("NO se añade a datosalumnosexcelmodels");
					}
				}
				
				setExcel(archivoCargado, event);      //Valida si excel fue cargado , muestra un mensaje
			}else{
				getArchivoModel().setNombre("");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo Excel no cumple con el formato válido.", ""));
			}

			wb.close();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrió un error al leer el archivo" + e, null));
			e.printStackTrace();
		}

		switch (MODO_USUARIO) {
		case 1:
			pagina = "/paginas/ModuloObservados/admin/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		case 2:
			pagina = "/paginas/ModuloObservados/ocaa/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Metodo que muestra un mensaje si el archivo excel se ingreso de manera
	 * correcta.
	 *
	 * @param excelCargado            archivo excel
	 * @param event the event
	 */
	public void setExcel(UploadedFile excelCargado, FileUploadEvent event) {
		if (excelCargado != null) {
			file = event.getFile();
			archivoModel.setNombre(excelCargado.getFileName());
			FacesMessage message = new FacesMessage("Éxito", excelCargado.getFileName() + " ha sido cargado con éxito");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			System.out.println("No hay  ningun archivo");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "El archivo no se ha cargado correctamente", null));
		}
	}

	/**
	 * Convertir A model alumno.
	 *
	 * @param fila_alumno the fila alumno
	 * @return the datos alumno excel model
	 */
	public DatosAlumnoExcelModel convertirAModelAlumno(XSSFRow fila_alumno) {
		DatosAlumnoExcelModel datos = new DatosAlumnoExcelModel();

		try {
			datos.setCod_alumno(fila_alumno.getCell(COD_ALUMNO) != null
					? validarDatoEntero(fila_alumno.getCell(COD_ALUMNO)).toString() : "0");
			datos.setAp_paterno(
					fila_alumno.getCell(APE_PATERNO) != null ? fila_alumno.getCell(APE_PATERNO).toString() : "");
			datos.setAp_materno(
					fila_alumno.getCell(APE_MATERNO) != null ? fila_alumno.getCell(APE_MATERNO).toString() : "");
			datos.setNombres(fila_alumno.getCell(NOM_ALUMNO) != null ? fila_alumno.getCell(NOM_ALUMNO).toString() : "");
			datos.setCod_plan(fila_alumno.getCell(COD_PLAN) != null
					? validarDatoEntero(fila_alumno.getCell(COD_PLAN)).toString() : "0");
			datos.setCod_curso(fila_alumno.getCell(COD_CURSO) != null ? fila_alumno.getCell(COD_CURSO).toString() : "");
			datos.setDes_curso(fila_alumno.getCell(NOM_CURSO) != null
					? validarDatoCadena(fila_alumno.getCell(NOM_CURSO)).toString() : "");
			datos.setCreditos(fila_alumno.getCell(NUM_CRED) != null
					? validarDatoEntero(fila_alumno.getCell(NUM_CRED)).toString() : "0");
			datos.setRepitencias(fila_alumno.getCell(REPITENCIAS) != null
					? validarDatoEntero(fila_alumno.getCell(REPITENCIAS)).toString() : "0");
			datos.setCod_docente(
					fila_alumno.getCell(COD_DOCENTE) != null ? fila_alumno.getCell(COD_DOCENTE).toString() : "");
			datos.setNom_docente(fila_alumno.getCell(NOM_DOCENTE) != null
					? validarDatoCadena(fila_alumno.getCell(NOM_DOCENTE)).toString() : "");
			datos.setCod_frecuencia(fila_alumno.getCell(FRECUENCIA) != null
					? validarDatoEntero(fila_alumno.getCell(FRECUENCIA)).toString() : "0");
			datos.setNom_frecuencia(
					comunServices
							.obtenerDatosFrecuencia(Integer.parseInt(fila_alumno.getCell(FRECUENCIA) != null
									? validarDatoEntero(fila_alumno.getCell(FRECUENCIA)).toString() : "0"))
					.getValorCampo());
			datos.setDia(fila_alumno.getCell(DIA) != null ? fila_alumno.getCell(DIA).toString() : "");
			datos.setHora_inicio(
					fila_alumno.getCell(HORA_INICIO) != null ? fila_alumno.getCell(HORA_INICIO).toString() : "");
			datos.setHora_fin(fila_alumno.getCell(HORA_FIN) != null ? fila_alumno.getCell(HORA_FIN).toString() : "");

			datos = validarRegistroTutoria(datos);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return datos;
	}

	/**
	 * Es repitencia invalida.
	 *
	 * @param iRepitencias the i repitencias
	 * @return true, if successful
	 */
	private boolean esRepitenciaInvalida(Integer iRepitencias){
		return (iRepitencias!=2 && iRepitencias!=3);
	}
	
	/**
	 * Validar registro tutoria.
	 *
	 * @param datos the datos
	 * @return the datos alumno excel model
	 */
	private DatosAlumnoExcelModel validarRegistroTutoria(DatosAlumnoExcelModel datos) {
		System.out.println("valida " + Integer.parseInt(datos.getRepitencias()));
		if (datos.getCod_alumno() == "" || datos.getCod_alumno().length() > 8 || datos.getAp_paterno() == ""
				|| datos.getAp_materno() == "" || datos.getNombres() == "" || datos.getCod_plan() == "0"
				|| datos.getCod_curso() == "" || datos.getRepitencias() == "0" || datos.getCod_docente() == ""
				|| datos.getCod_frecuencia() == "0" || datos.getDia() == "" || datos.getHora_inicio() == ""
				|| datos.getHora_fin() == "" || esRepitenciaInvalida(Integer.parseInt(datos.getRepitencias())) ) {
			datos.setValido("No");
		} else {
			datos.setValido("Si");
		}
		return datos;
	}

	/**
	 * Convertir A alumno BO.
	 *
	 * @param alumnoData the alumno data
	 * @return the alumno BO
	 */
	public AlumnoBO convertirAAlumnoBO(DatosAlumnoExcelModel alumnoData) {
		AlumnoBO abo = new AlumnoBO();
		abo.setaCodigo(alumnoData.getCod_alumno());
		abo.setaApellido(alumnoData.getAp_paterno() + " " + alumnoData.getAp_materno());
		abo.setaNombre(alumnoData.getNombres());
		return abo;
	}

	/**
	 * Guardar tutoria observados.
	 *
	 * @return the string
	 */
	public String guardarTutoriaObservados() {
		String pagina = "";
		try {
			int validador = 0;
			List<TutoriaBO> listaTutorias = convertirATutoriaBO(datosAlumnoExcelModels);
			System.out.println("lista de excel "+ datosAlumnoExcelModels.size());
			CicloBO ciclo = comunServices.buscarCicloActual();
			for (TutoriaBO tutoria : listaTutorias) {
				System.out.println("hallado " + tutoriaServices.buscarTutoria(ciclo.getAnio(), ciclo.getPeriodo(), tutoria.getcCodigo(),
						tutoria.getaCodigo(), tutoria.getpCodigo()) );
				if (tutoriaServices.buscarTutoria(ciclo.getAnio(), ciclo.getPeriodo(), tutoria.getcCodigo(),
						tutoria.getaCodigo(), tutoria.getpCodigo()) == "") {
					tutoriaServices.procesarTutoriaObservados(tutoria, obtenerUsuario(), ALUMNO_OBSERVADO);
					validador = 1;
				}
			}
			reiniciarClases();
			getArchivoModel().setNombre("");

			if (validador == 1) {
				mostrarMensaje(1);
			} else {
				mostrarMensaje(2);
			}
		} catch (Exception ex) {
			mostrarMensaje(3);
			ex.printStackTrace();
		}
		switch (MODO_USUARIO) {
		case 1:
			pagina = "/paginas/ModuloObservados/admin/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		case 2:
			pagina = "/paginas/ModuloObservados/ocaa/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Convertir A tutoria BO.
	 *
	 * @param datosTutoriaModel the datos tutoria model
	 * @return the list
	 */
	private List<TutoriaBO> convertirATutoriaBO(List<DatosAlumnoExcelModel> datosTutoriaModel) {
		List<TutoriaBO> listaTutorias = new ArrayList<TutoriaBO>();
		for (DatosAlumnoExcelModel tutoriaModel : datosTutoriaModel) {
			TutoriaBO tutoria = new TutoriaBO();
			tutoria.setaCodigo(tutoriaModel.getCod_alumno().trim());
			tutoria.setaApellidoPaterno(tutoriaModel.getAp_paterno().trim());
			tutoria.setaApellidoMaterno(tutoriaModel.getAp_materno().trim());
			tutoria.setaNombre(tutoriaModel.getNombres().trim());
			tutoria.setPlan(Integer.parseInt(tutoriaModel.getCod_plan().trim()));
			tutoria.setcCodigo(tutoriaModel.getCod_curso().trim());
			tutoria.setcNombre(tutoriaModel.getDes_curso().trim());
			tutoria.setCreditos(Integer.parseInt(tutoriaModel.getCreditos().trim()));
			tutoria.setRepitencias(tutoriaModel.getRepitencias());
			tutoria.setpCodigo(tutoriaModel.getCod_docente().trim());
			tutoria.setpNombre(tutoriaModel.getNom_docente().trim());
			tutoria.setFrecuencia(Integer.parseInt(tutoriaModel.getCod_frecuencia().trim()));
			tutoria.setDesc_frecuencia(tutoriaModel.getNom_frecuencia());
			tutoria.setDia(tutoriaModel.getDia().trim());
			tutoria.setHoraIni(tutoriaModel.getHora_inicio().trim());
			tutoria.setHoraFin(tutoriaModel.getHora_fin().trim());
			listaTutorias.add(tutoria);
		}
		return listaTutorias;
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
	 * Mostrar.
	 */
	public void mostrar() {
		if (file != null) {
			FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			System.out.println("No hay  ningun archivo");
		}
		System.out.println("nombre del acchivo excel es : " + this.nombreArchivo);
		nombreArchivo = "un nombre";
	}

	/**
	 * Actualizar datatable.
	 */
	public void actualizarDatatable() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.reset("pnlGridLoginAcceso:tablaAlumnos");
	}

	/**
	 * Validar dato entero.
	 *
	 * @param valorCelda the valor celda
	 * @return the integer
	 */
	public Integer validarDatoEntero(XSSFCell valorCelda) {
		String valorCadena = "";
		Double valorNumerico = 0D;
		if (valorCelda.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			switch (valorCelda.getCachedFormulaResultType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				valorNumerico = valorCelda.getNumericCellValue();
				return valorNumerico.intValue();
			case XSSFCell.CELL_TYPE_STRING:
				valorCadena = valorCelda.getStringCellValue();
				return Integer.parseInt(valorCadena);
			case XSSFCell.CELL_TYPE_BLANK:
				return 0;
			default:
				return 0;
			}
		} else {
			switch (valorCelda.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				valorNumerico = valorCelda.getNumericCellValue();
				return valorNumerico.intValue();
			case XSSFCell.CELL_TYPE_STRING:
				valorCadena = valorCelda.getStringCellValue();
				return Integer.parseInt(valorCadena);
			case XSSFCell.CELL_TYPE_BLANK:
				return 0;
			default:
				return 0;
			}
		}
	}

	/**
	 * Validar dato cadena.
	 *
	 * @param valorCelda the valor celda
	 * @return the string
	 */
	public String validarDatoCadena(XSSFCell valorCelda) {
		String valorCadena = "";
		int valorNumerico = 0;

		if (valorCelda.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
			switch (valorCelda.getCachedFormulaResultType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				valorNumerico = (int) valorCelda.getNumericCellValue();
				return Integer.toString(valorNumerico);
			case XSSFCell.CELL_TYPE_STRING:
				valorCadena = valorCelda.getStringCellValue();
				return valorCadena;
			case XSSFCell.CELL_TYPE_BLANK:
				return "";
			default:
				return "";
			}
		} else {
			switch (valorCelda.getCellType()) {
			case XSSFCell.CELL_TYPE_NUMERIC:
				valorNumerico = (int) valorCelda.getNumericCellValue();
				return Integer.toString(valorNumerico);
			case XSSFCell.CELL_TYPE_STRING:
				valorCadena = valorCelda.getStringCellValue();
				return valorCadena;
			case XSSFCell.CELL_TYPE_BLANK:
				return "";
			default:
				return "";
			}
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
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Los alumnos se registraron correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Ya no quedan registros para guardar");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Ocurrió un error al guardar los datos");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "No hay un documento cargado");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		}
	}

	/**
	 * Selector datos alumnos.
	 *
	 * @param modo the modo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorDatosAlumnos(int modo) throws Exception {
		String pagina = "";

		switch (modo) {
		case 1:
			MODO_USUARIO = MODO_ADMIN;
			inicializarClases();
			if (getDatosAlumnoExcelModelGrid() != null) {
				getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
			}
			pagina = "/paginas/ModuloObservados/admin/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		case 2:
			MODO_USUARIO = MODO_OCAA;
			inicializarClases();
			if (getDatosAlumnoExcelModelGrid() != null) {
				getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
			}
			pagina = "/paginas/ModuloObservados/ocaa/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		case 5:
			MODO_USUARIO = MODO_DECANO;
			inicializarClases();
			if (getDatosAlumnoExcelModelGrid() != null) {
				getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
			}
			pagina = "/paginas/ModuloObservados/decano/cargar/cargarDatosAlumnosObs.xhtml";
			break;
		}
		return pagina;
	}

	/**
	 * Validar datos alumnos cargados.
	 */
	public void validarDatosAlumnosCargados() {
		if (datosAlumnoExcelModelGrid.isEmpty()) {
			mostrarMensaje(4);
		}
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Gets the datos alumno excel models.
	 *
	 * @return the datos alumno excel models
	 */
	public List<DatosAlumnoExcelModel> getDatosAlumnoExcelModels() {
		return datosAlumnoExcelModels;
	}

	/**
	 * Sets the datos alumno excel models.
	 *
	 * @param DatosAlumnoExcelModels the new datos alumno excel models
	 */
	public void setDatosAlumnoExcelModels(List<DatosAlumnoExcelModel> DatosAlumnoExcelModels) {
		this.datosAlumnoExcelModels = DatosAlumnoExcelModels;
	}

	/**
	 * Gets the datos alumno excel model.
	 *
	 * @return the datos alumno excel model
	 */
	public DatosAlumnoExcelModel getDatosAlumnoExcelModel() {
		return datosAlumnoExcelModel;
	}

	/**
	 * Sets the datos alumno excel model.
	 *
	 * @param DatosAlumnoExcelModel the new datos alumno excel model
	 */
	public void setDatosAlumnoExcelModel(DatosAlumnoExcelModel DatosAlumnoExcelModel) {
		this.datosAlumnoExcelModel = DatosAlumnoExcelModel;
	}

	/**
	 * Gets the nombre archivo.
	 *
	 * @return the nombre archivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Sets the nombre archivo.
	 *
	 * @param nombreArchivo the new nombre archivo
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Gets the archivo model.
	 *
	 * @return the archivo model
	 */
	public ArchivoModel getArchivoModel() {
		return archivoModel;
	}

	/**
	 * Sets the archivo model.
	 *
	 * @param archivoModel the new archivo model
	 */
	public void setArchivoModel(ArchivoModel archivoModel) {
		this.archivoModel = archivoModel;
	}

	/**
	 * Gets the datos alumno excel model grid.
	 *
	 * @return the datos alumno excel model grid
	 */
	public List<DatosAlumnoExcelModel> getDatosAlumnoExcelModelGrid() {
		return datosAlumnoExcelModelGrid;
	}

	/**
	 * Sets the datos alumno excel model grid.
	 *
	 * @param datosAlumnoExcelModelGrid the new datos alumno excel model grid
	 */
	public void setDatosAlumnoExcelModelGrid(List<DatosAlumnoExcelModel> datosAlumnoExcelModelGrid) {
		this.datosAlumnoExcelModelGrid = datosAlumnoExcelModelGrid;
	}
}
