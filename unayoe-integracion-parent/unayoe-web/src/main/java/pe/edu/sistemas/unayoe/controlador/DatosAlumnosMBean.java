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

@Controller("datosAlumnosMBean")
@ViewScoped
public class DatosAlumnosMBean {

	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private CursoServices cursoServices;
	@Autowired
	private GrupoServices grupoServices;
	@Autowired
	private MatriculaServices matriculaServices;
	@Autowired
	private TutoriaServices tutoriaServices;
	@Autowired
	private ComunServices comunServices;
	@Autowired
	private List<DatosAlumnoExcelModel> datosAlumnoExcelModels;
	@Autowired
	private DatosAlumnoExcelModel datosAlumnoExcelModel;
	@Autowired
	private ArchivoModel archivoModel;

	private List<DatosAlumnoExcelModel> datosAlumnoExcelModelGrid;
	private String nombreArchivo;
	private UploadedFile file;

	private static int COD_ALUMNO = 0;
	private static int APE_PATERNO = 1;
	private static int APE_MATERNO = 2;
	private static int NOM_ALUMNO = 3;
	private static int COD_PLAN = 4;
	private static int COD_CURSO = 5;
	private static int NOM_CURSO = 6;
	private static int NUM_CRED = 7;
	private static int REPITENCIAS = 8;
	private static int COD_DOCENTE = 9;
	private static int NOM_DOCENTE = 10;
	private static int FRECUENCIA = 11;
	private static int DIA = 12;
	private static int HORA_INICIO = 13;
	private static int HORA_FIN = 14;

	private static int ALUMNO_OBSERVADO = 1;

	private int MODO_USUARIO;
	private static int MODO_ADMIN = 1;
	private static int MODO_OCAA = 2;

	public DatosAlumnosMBean() {
		inicializarClases();
	}

	private void inicializarClases() {
		setDatosAlumnoExcelModelGrid(new ArrayList<DatosAlumnoExcelModel>());
	}

	private void reiniciarClases() {
		if (getDatosAlumnoExcelModelGrid() != null) {
			getDatosAlumnoExcelModelGrid().removeAll(getDatosAlumnoExcelModelGrid());
		}
		datosAlumnoExcelModels.clear();
	}

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

	public String handleFileUpload(FileUploadEvent event) throws Exception {
		String pagina = "";

		boolean formatoExcelValido = true; // Indica si el formato de la columnas del excel son validos
		datosAlumnoExcelModels.clear();
		UploadedFile archivoCargado = event.getFile();
		InputStream file;

		try {
			file = event.getFile().getInputstream();
			XSSFWorkbook wb = new XSSFWorkbook(file);

			XSSFSheet ws = wb.getSheetAt(0);
			int rowNum = ws.getLastRowNum() + 1; // Cantidad de fila del archivo
													// excel

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
					/*
					 * Fin validacion del codigo del alumno
					 */

					/*
					 * Empieza la carga de los datos del alumno
					 */
					DatosAlumnoExcelModel dataModel = convertirAModelAlumno(row);

					if (tutoriaServices.buscarTutoria(ciclo.getAnio(), ciclo.getPeriodo(), dataModel.getCod_curso(),
							dataModel.getCod_alumno(), dataModel.getCod_docente()) != "") {
						dataModel.setExiste("Si");
					} else {
						dataModel.setExiste("No");
					}

					datosAlumnoExcelModelGrid.add(dataModel);

					if (dataModel.getExiste() != "Si" && dataModel.getValido() != "No") {
						datosAlumnoExcelModels.add(dataModel);
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
	 * correcta
	 * 
	 * @param excelCargado
	 *            archivo excel
	 * @param event
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

	private DatosAlumnoExcelModel validarRegistroTutoria(DatosAlumnoExcelModel datos) {
		if (datos.getCod_alumno() == "" || datos.getCod_alumno().length() > 8 || datos.getAp_paterno() == ""
				|| datos.getAp_materno() == "" || datos.getNombres() == "" || datos.getCod_plan() == "0"
				|| datos.getCod_curso() == "" || datos.getRepitencias() == "0" || datos.getCod_docente() == ""
				|| datos.getCod_frecuencia() == "0" || datos.getDia() == "" || datos.getHora_inicio() == ""
				|| datos.getHora_fin() == "" || Integer.parseInt(datos.getRepitencias()) < 4) {
			datos.setValido("No");
		} else {
			datos.setValido("Si");
		}
		return datos;
	}

	public AlumnoBO convertirAAlumnoBO(DatosAlumnoExcelModel alumnoData) {
		AlumnoBO abo = new AlumnoBO();
		abo.setaCodigo(alumnoData.getCod_alumno());
		abo.setaApellido(alumnoData.getAp_paterno() + " " + alumnoData.getAp_materno());
		abo.setaNombre(alumnoData.getNombres());
		return abo;
	}

	public String guardarTutoriaObservados() {
		String pagina = "";
		try {
			int validador = 0;
			List<TutoriaBO> listaTutorias = convertirATutoriaBO(datosAlumnoExcelModels);
			CicloBO ciclo = comunServices.buscarCicloActual();
			for (TutoriaBO tutoria : listaTutorias) {
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

	public String obtenerUsuario() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String nombre = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = (User) auth.getPrincipal();
			nombre = user.getUsername();
		}
		return nombre;
	}

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

	public void actualizarDatatable() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.reset("pnlGridLoginAcceso:tablaAlumnos");
	}

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
		}
		return pagina;
	}

	public void validarDatosAlumnosCargados() {
		if (datosAlumnoExcelModelGrid.isEmpty()) {
			mostrarMensaje(4);
		}
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<DatosAlumnoExcelModel> getDatosAlumnoExcelModels() {
		return datosAlumnoExcelModels;
	}

	public void setDatosAlumnoExcelModels(List<DatosAlumnoExcelModel> DatosAlumnoExcelModels) {
		this.datosAlumnoExcelModels = DatosAlumnoExcelModels;
	}

	public DatosAlumnoExcelModel getDatosAlumnoExcelModel() {
		return datosAlumnoExcelModel;
	}

	public void setDatosAlumnoExcelModel(DatosAlumnoExcelModel DatosAlumnoExcelModel) {
		this.datosAlumnoExcelModel = DatosAlumnoExcelModel;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public ArchivoModel getArchivoModel() {
		return archivoModel;
	}

	public void setArchivoModel(ArchivoModel archivoModel) {
		this.archivoModel = archivoModel;
	}

	public List<DatosAlumnoExcelModel> getDatosAlumnoExcelModelGrid() {
		return datosAlumnoExcelModelGrid;
	}

	public void setDatosAlumnoExcelModelGrid(List<DatosAlumnoExcelModel> datosAlumnoExcelModelGrid) {
		this.datosAlumnoExcelModelGrid = datosAlumnoExcelModelGrid;
	}
}
