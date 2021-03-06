package pe.edu.sistemas.unayoe.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.model.AlumnoModel;
import pe.edu.sistemas.unayoe.model.ArchivoModel;
import pe.edu.sistemas.unayoe.model.ClaseMaestraModel;
import pe.edu.sistemas.unayoe.model.NotasAlumnoExcelModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO;
import controladorReporte.ControladorReporte;


// TODO: Auto-generated Javadoc
/**
 * The Class NotasAlumnosMBean.
 */
@Controller("notasAlumnoMBean")
@ViewScoped
public class NotasAlumnosMBean {
	
	/** The archivo model. */
	@Autowired
	private ArchivoModel archivoModel;
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;
	
	/** The alumno services. */
	@Autowired
	private AlumnoServices alumnoServices;
	
	/** The clase maestra model. */
	@Autowired 
	private ClaseMaestraModel claseMaestraModel;
	
	/** The notas alumno excel model. */
	@Autowired
	private NotasAlumnoExcelModel notasAlumnoExcelModel;
	
	/** The notas alumno excel models. */
	@Autowired
	private List<NotasAlumnoExcelModel> notasAlumnoExcelModels;	
	
	/** The notas alumno buscar model. */
	@Autowired
	private List<NotasAlumnoExcelModel> notasAlumnoBuscarModel;
	
	/** The list notas alumno BO. */
	private List<NotasAlumnoBO> listNotasAlumnoBO;	
	
	/** The alumno buscar notas model select. */
	private AlumnoModel alumnoBuscarNotasModelSelect;
	
	/** The clase maestra model select. */
	private ClaseMaestraModel claseMaestraModelSelect;
	
	/** The visualizar notas alumno grid. */
	private List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid;
	
	/** The notas alumno excel models grid. */
	private List<NotasAlumnoExcelModel> notasAlumnoExcelModelsGrid;
		
	/** The existe. */
	private int existe = 0;
	
	/** The valido. */
	private int valido = 1;
	
	/** The modo usuario. */
	private int MODO_USUARIO;
	
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
	
	/** The proceso. */
	private int PROCESO;	
	
	/** The anio. */
	private static int ANIO=0;
	
	/** The periodo. */
	private static int PERIODO=1;
	
	/** The plan. */
	private static int PLAN=2;
	
	/** The cod curso. */
	private static int COD_CURSO=3;
	
	/** The nom curso. */
	private static int NOM_CURSO=4;
	
	/** The creditos. */
	private static int CREDITOS=5;
	
	/** The cod alumno. */
	private static int COD_ALUMNO=6;
	
	/** The nom alumno. */
	private static int NOM_ALUMNO=7;
	
	/** The nom docente. */
	private static int NOM_DOCENTE=8;
	
	/** The nota final. */
	private static int NOTA_FINAL=9; 
	
	

	/** The modo. */
	private int MODO;
	
	/** The modo aux. */
	private int MODO_AUX;
	
	/** The anio actual. */
	private int ANIO_ACTUAL;
	
	/** The periodo actual. */
	private int PERIODO_ACTUAL;	
	
	/** The proceso observados. */
	private static int PROCESO_OBSERVADOS = 1;
	
	/** The proceso regulares. */
	private static int PROCESO_REGULARES = 2;			

	/**
	 * Instantiates a new notas alumnos M bean.
	 */
	public NotasAlumnosMBean(){
		System.out.println("::NOTAS ALUMNOS BEAN::");	
		inicializarClases();
	}
	
	/**
	 * Inicializar clases.
	 */
	private void inicializarClases(){
		System.out.println("inicializa");
		setAlumnoBuscarNotasModelSelect(new AlumnoModel());	
		setClaseMaestraModelSelect(new ClaseMaestraModel());
		setListNotasAlumnoBO(new ArrayList<NotasAlumnoBO>());
		setVisualizarNotasAlumnoGrid(new ArrayList<NotasAlumnoExcelModel>());
		setNotasAlumnoExcelModelsGrid(new ArrayList<NotasAlumnoExcelModel>());
	}
	
	/**
	 * Valida numero.
	 *
	 * @param valor the valor
	 * @return the string
	 */
	public String validaNumero(String valor){
		String esNumerico = "-1";
		try{
			Integer.parseInt(valor);
			esNumerico = valor; 
		}
		catch(NumberFormatException nfe){
			esNumerico = "-1";
		}
		return esNumerico;
	}
	
	/**
	 * Cargar notas alumnos.
	 *
	 * @param event the event
	 * @return the string
	 * @throws Exception the exception
	 */
	public String cargarNotasAlumnos(FileUploadEvent event) throws Exception {
		System.out.println("carga1");
		String pagina = "";
		getNotasAlumnoExcelModels().clear();
		UploadedFile archivoCargado = event.getFile();  
		if (setExcel(archivoCargado, event) == true){
			System.out.println("ENTRAS AL IF");
			InputStream file;
	        try{        	 
	        	file = event.getFile().getInputstream();       
	        	XSSFWorkbook wb = new XSSFWorkbook(file);        
	        	XSSFSheet ws = wb.getSheetAt(0);
	        
	        	int rowNum = ws.getPhysicalNumberOfRows();

	        	System.out.println("row " + rowNum);
	        	//rowNum=9;
	        	
	        	limpiarListas();
	        	getListNotasAlumnoBO().removeAll(getListNotasAlumnoBO());
	        	existe = 0;
	        	valido = 1;
	        	System.out.println("ENTRAS AL 2"+rowNum);
	        	for(int i = 1; i <rowNum; i++){
	        	
	        	XSSFRow row = ws.getRow(i);
	        		validarRegistro(row);        		
	        		NotasAlumnoExcelModel dataModel = convertirAModelAlumno(row);
	        		
	        		if (valido == 0){
	        			dataModel.setValido("No");
	        				        		}
	        		else{
	        			dataModel.setValido("Si");
	        		}
	        		 
	        		if (existe == 1){
	        			dataModel.setExiste("Si");
	        		}
	        		else{
	        			dataModel.setExiste("No");
	        		}
	        		
	        		getNotasAlumnoExcelModelsGrid().add(dataModel);        		
	        		if (valido != 0 && existe != 1){
	        			System.out.println("si se puede insertar");
	        			getNotasAlumnoExcelModels().add(dataModel);
	        			
	        		}
	        		existe = 0;
	            	valido = 1;
	        	}
	        	List<NotasAlumnoExcelModel> listNotasAlumno = getNotasAlumnoExcelModels();
	        	for (NotasAlumnoExcelModel notasAlumno:listNotasAlumno){
	    			NotasAlumnoBO notasAlumnoBO = convertirANotasAlumnoBO(notasAlumno);
	    			getListNotasAlumnoBO().add(notasAlumnoBO);
	        	}
	        	wb.close();
	        }      
	        catch (IOException e) {
	        	mostrarMensaje(1);
	        	e.printStackTrace();
	        }
        } 
		System.out.println("MODO"+MODO_USUARIO);
		System.out.println("PROCE"+PROCESO);
		
		switch(PROCESO){
		
			case 1: switch(MODO_USUARIO){
						case 1: pagina = "/paginas/ModuloObservados/admin/cargar/cargarNotasAlumnosObs.xhtml"; break;
						case 2: pagina = "/paginas/ModuloObservados/admin/cargar/cargarNotasAlumnosObs.xhtml"; break;
					} break;
			case 2: switch(MODO_USUARIO){
						case 1: pagina = "/paginas/ModuloObservados/admin/visualizar/visualizarNotasAlumnosObs.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/admin/ingresar/ingresarNotasAlumnosReg.xhtml"; break;
					} break;
		}	
		System.out.println("cargar "+pagina);
		return pagina;
    }
	
	/**
	 * Sets the excel.
	 *
	 * @param excelCargado the excel cargado
	 * @param event the event
	 * @return true, if successful
	 */
	public boolean setExcel(UploadedFile excelCargado, FileUploadEvent event){
		boolean archivoCargado = false;
		if(excelCargado != null) {
			System.out.println("EXCELCARGADO LA DVD");
			archivoCargado = true;
            getArchivoModel().setNombre(excelCargado.getFileName());
			mostrarMensaje(2);            
        }else{
        	mostrarMensaje(3);
        }
		return archivoCargado;
	}
	
	/**
	 * Limpiar listas.
	 */
	private void limpiarListas(){
		if (getNotasAlumnoExcelModels() != null){
			getNotasAlumnoExcelModels().removeAll(getNotasAlumnoExcelModels());
		}
		
		if (getNotasAlumnoExcelModelsGrid() != null){
			getNotasAlumnoExcelModelsGrid().removeAll(getNotasAlumnoExcelModelsGrid());
		}
	}
	
	/**
	 * Validar registro.
	 *
	 * @param fila the fila
	 * @throws Exception the exception
	 */
	private void validarRegistro(XSSFRow fila) throws Exception{
		try {			
			int anio = fila.getCell(ANIO)!=null?validarDatoEntero(fila.getCell(ANIO)):-1;
			int periodo = fila.getCell(PERIODO)!=null?validarDatoEntero(fila.getCell(PERIODO)):-1;
			int plan = fila.getCell(PLAN)!=null?validarDatoEntero(fila.getCell(PLAN)):-1;		
			String cod_curso = fila.getCell(COD_CURSO)!=null?validarDatoCadena(fila.getCell(COD_CURSO)):"";
			String cod_alumno = fila.getCell(COD_ALUMNO)!=null?validarDatoCadena(fila.getCell(COD_ALUMNO)):"";
			int notaFinal = fila.getCell(NOTA_FINAL)!=null?validarDatoEntero(fila.getCell(NOTA_FINAL)):-1;
					
			if (anio == -1 || periodo == -1 || plan == -1 || cod_curso == "" || cod_alumno == "" || notaFinal == -1){
				valido = 0;
			}
			else{
				CicloBO cicloActual = comunServices.buscarCicloActual();
				if (anio >= cicloActual.getAnio() && periodo >= cicloActual.getPeriodo()){
					valido = 0;
				}
			}
		
			if (alumnoServices.validarCargaNotas(anio, periodo, plan, cod_curso, cod_alumno, notaFinal) != 0){
				existe = 1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Validar dato entero.
	 *
	 * @param valorCelda the valor celda
	 * @return the integer
	 */
	public Integer validarDatoEntero(XSSFCell  valorCelda){
		 String valorCadena="";
		 Double valorNumerico=0D;
		 switch(valorCelda.getCellType()) {
		 	case  XSSFCell.CELL_TYPE_NUMERIC : valorNumerico=valorCelda.getNumericCellValue(); return valorNumerico.intValue() ;
		 	case  XSSFCell.CELL_TYPE_STRING  : valorCadena=validaNumero(valorCelda.getStringCellValue()); return Integer.parseInt(valorCadena);
		 	case  XSSFCell.CELL_TYPE_BLANK   : return 0;
		 	default : return 0;
		 }
	 }
	
	/**
	 * Validar dato cadena.
	 *
	 * @param valorCelda the valor celda
	 * @return the string
	 */
	public String validarDatoCadena(XSSFCell  valorCelda){
		 String valorCadena="";
		 int valorNumerico=0;
		 switch(valorCelda.getCellType()) {
		 	case  XSSFCell.CELL_TYPE_NUMERIC : valorNumerico=(int)valorCelda.getNumericCellValue(); return Integer.toString(valorNumerico) ;
		 	case  XSSFCell.CELL_TYPE_STRING  : valorCadena=valorCelda.getStringCellValue(); return valorCadena;
		 	case  XSSFCell.CELL_TYPE_BLANK   : return "";
		 	default : return "";
		 }
	 }
	
	/**
	 * Convertir A model alumno.
	 *
	 * @param notas the notas
	 * @return the notas alumno excel model
	 */
	public NotasAlumnoExcelModel  convertirAModelAlumno(XSSFRow notas){
		NotasAlumnoExcelModel datos = new NotasAlumnoExcelModel();
		
		try{			
			datos.setAnio(notas.getCell(ANIO)!=null?validarDatoEntero(notas.getCell(ANIO)):-1);
			datos.setPeriodo(notas.getCell(PERIODO)!=null?validarDatoEntero(notas.getCell(PERIODO)):-1);
			datos.setPlan(notas.getCell(PLAN)!=null?validarDatoEntero(notas.getCell(PLAN)):-1);
			datos.setCodCurso(notas.getCell(COD_CURSO)!=null?validarDatoCadena(notas.getCell(COD_CURSO)):"");
			datos.setNomCurso(notas.getCell(NOM_CURSO)!=null?notas.getCell(NOM_CURSO).toString():"");
			datos.setCodAlumno(notas.getCell(COD_ALUMNO)!=null?validarDatoCadena(notas.getCell(COD_ALUMNO)):"");
			datos.setNotaFinal(notas.getCell(NOTA_FINAL)!=null?validarDatoEntero(notas.getCell(NOTA_FINAL)):-1);
			datos.setCreditos(notas.getCell(CREDITOS)!=null?validarDatoEntero(notas.getCell(CREDITOS)):-1);
			datos.setNomAlumno(notas.getCell(NOM_ALUMNO)!=null?notas.getCell(NOM_ALUMNO).toString():"");
			datos.setNomDocente(notas.getCell(NOM_DOCENTE)!=null?notas.getCell(NOM_DOCENTE).toString():"");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return datos;
	}
	
	/**
	 * Guardar notas.
	 */
	public void guardarNotas(){
		System.out.println("GUARDARNOTAS");
		try{
			List<NotasAlumnoBO> listNotasAlumno = getListNotasAlumnoBO();
			for (NotasAlumnoBO notasAlumno:listNotasAlumno){					
				if (alumnoServices.validarCargaNotas(notasAlumno.getAnio(), notasAlumno.getPeriodo(), notasAlumno.getPlan(), 
						                             notasAlumno.getCodCurso(), notasAlumno.getCodAlumno(), notasAlumno.getNotaFinal()) == 0){
					alumnoServices.guardarNotas(notasAlumno);
				}
			}
			getNotasAlumnoExcelModelsGrid().clear();
			getArchivoModel().setNombre("");
			mostrarMensaje(4);
		}
		catch(Exception ex){
			mostrarMensaje(5);
		}		
	}
	
	
	
	/**
	 * Convertir A notas alumno BO.
	 *
	 * @param notasAlumno the notas alumno
	 * @return the notas alumno BO
	 */
	public NotasAlumnoBO convertirANotasAlumnoBO(NotasAlumnoExcelModel notasAlumno){
		NotasAlumnoBO notasAlumnoBO = new NotasAlumnoBO();
				
		try{
			notasAlumnoBO.setAnio(notasAlumno.getAnio());
			notasAlumnoBO.setPeriodo(notasAlumno.getPeriodo());
			notasAlumnoBO.setPlan(notasAlumno.getPlan());
			notasAlumnoBO.setCodCurso(notasAlumno.getCodCurso());
			notasAlumnoBO.setNomCurso(notasAlumno.getNomCurso());
			notasAlumnoBO.setCodAlumno(notasAlumno.getCodAlumno());
			notasAlumnoBO.setNomAlumno(notasAlumno.getNomAlumno());
			notasAlumnoBO.setNomDocente(notasAlumno.getNomDocente());
			notasAlumnoBO.setCreditos(notasAlumno.getCreditos());
			notasAlumnoBO.setNotaFinal(notasAlumno.getNotaFinal());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return notasAlumnoBO;
	}

	/**
	 * Listar ciclo.
	 *
	 * @throws Exception the exception
	 */
	public void listarCiclo() throws Exception{
    	System.out.println("Listando los ciclos:");
    	
    	List<ClaseMaestra> listarCiclo = null;
        try {
        	String tabla = "CICLO";
        	String campo = "CICLO_TODOS";
        	listarCiclo = alumnoServices.listarCiclo(tabla, campo);
        	
        	claseMaestraModel.setListaClaseMaestra(listarCiclo);
        } catch (Exception e) {           
            e.printStackTrace();
        }
    }
	
	/**
	 * Buscar notas alumno.
	 *
	 * @throws Exception the exception
	 */
	public void buscarNotasAlumno() throws Exception{
		try{
			int ciclo = claseMaestraModelSelect.getIdCampo();
			String codAlumno = alumnoBuscarNotasModelSelect.getCodigo();
			
			getVisualizarNotasAlumnoGrid().removeAll(getVisualizarNotasAlumnoGrid());
			
			List<NotasAlumnoBO> notasAlumnoBO = alumnoServices.buscarNotasAlumno(ciclo, codAlumno);
			
			getNotasAlumnoExcelModelsGrid().removeAll(getNotasAlumnoExcelModelsGrid());
			
			for (NotasAlumnoBO notasAlumno: notasAlumnoBO){
				NotasAlumnoExcelModel notasAlumnoModel = convertirANotasAlumnoModel(notasAlumno);
				getVisualizarNotasAlumnoGrid().add(notasAlumnoModel);
			}
			
			if (getVisualizarNotasAlumnoGrid().size() != 0){
				getAlumnoBuscarNotasModelSelect().setNombres(getVisualizarNotasAlumnoGrid().get(0).getNomAlumno());
			}
			else{
				getAlumnoBuscarNotasModelSelect().setNombres("");
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Convertir A notas alumno model.
	 *
	 * @param notasAlumno the notas alumno
	 * @return the notas alumno excel model
	 */
	public NotasAlumnoExcelModel convertirANotasAlumnoModel(NotasAlumnoBO notasAlumno){
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
	 * Imprimir reporte notas alumno.
	 *
	 * @throws Exception the exception
	 */
	public void imprimirReporteNotasAlumno() throws Exception{
		System.out.println("imprimirpdf");
		if(!getVisualizarNotasAlumnoGrid().isEmpty()){
	    	System.out.println("Impresion de reporte de horario:");    	
	    	ControladorReporte reporte = new ControladorReporte();
	    	reporte.setNombreReporte("visualizarNotasAlumno");
	    	reporte.generarReporteNotasAlumno(obtenerParametros(),obtenerCampos());
    	}else
    		mostrarMensaje(6);
    }

    /**
     * Obtener parametros.
     *
     * @return the map
     * @throws Exception the exception
     */
    private Map<String,Object> obtenerParametros() throws Exception{
    	Map<String,Object> pars = new HashMap<String,Object>();    	
    	String ciclo = alumnoServices.buscarCicloAcademico(getClaseMaestraModelSelect().getIdCampo());
    	String plan = Integer.toString(getVisualizarNotasAlumnoGrid().get(0).getPlan());
    	pars.put("ciclo", ciclo);
    	pars.put("nomAlumno", alumnoBuscarNotasModelSelect.getNombres());
    	pars.put("facultad", "Ingeniería de Sistemas e Informática");
    	pars.put("escuela", "E.A.P. Sistemas");    	   
    	pars.put("plan", plan); 
    	return pars;
    }
    
    /**
     * Obtener campos.
     *
     * @return the array list
     */
    private ArrayList<Object> obtenerCampos(){
    	ArrayList<Object> list = new ArrayList<Object>();
    	List<NotasAlumnoExcelModel> listNotasAlumno = getVisualizarNotasAlumnoGrid();
    	for(NotasAlumnoExcelModel model : listNotasAlumno){
    		NotasAlumnoExcelModel notasAlumno = new NotasAlumnoExcelModel();
    		notasAlumno.setCodCurso(model.getCodCurso());
    		notasAlumno.setNomAlumno(model.getNomAlumno());
    		notasAlumno.setNomCurso(model.getNomCurso());
    		notasAlumno.setCreditos(model.getCreditos());
    		notasAlumno.setNomDocente(model.getNomDocente());
    		notasAlumno.setNotaFinal(model.getNotaFinal());
    		list.add(notasAlumno);
    	}
    	return list;
    }	
    
    /**
     * Mostrar mensaje.
     *
     * @param opcionMensaje the opcion mensaje
     */
    private void mostrarMensaje(int opcionMensaje){
		FacesMessage message = null;		
		
		switch(opcionMensaje){
			case 1: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Error de lectura");
    				FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 2: message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Éxito", "El archivo excel ha sido cargado con éxito");
            		FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 3: message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo excel no se ha cargado correctamente");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 4: message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto:", "Las notas se han guardado correctamente");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 5: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error:", "Ha ocurrido un error al guardar las notas");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 6 : message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error:", "Debe buscar un alumno");
					RequestContext.getCurrentInstance().showMessageInDialog(message); break;
		}
    }
    
    /**
     * Selector carga notas.
     *
     * @param proceso the proceso
     * @param modo the modo
     * @return the string
     * @throws Exception the exception
     */
    public String selectorCargaNotas(int proceso, int modo) throws Exception{
    	System.out.println("Selectorcargar"); 
    	System.out.println("proc"+proceso); 
    	System.out.println("modo"+modo); 
    	String pagina = "";

		 switch(proceso){
		 	case 1: switch(modo){ 
		 				case 1: MODO_USUARIO = MODO_ADMIN;									
		 						limpiarListas();
		 						inicializarClases();		 						
		 						pagina = "/paginas/ModuloObservados/admin/cargar/cargarNotasAlumnos.xhtml"; break;
		 				case 2: MODO_USUARIO = MODO_OCAA;
		 						limpiarListas();
		 						inicializarClases();
		 						pagina = "/paginas/ModuloObservados/ocaa/cargar/cargarNotasAlumnos.xhtml"; break;
		 			} break;
		 	case 2: switch(modo){ 
						case 1: MODO_USUARIO = MODO_ADMIN;	
							    limpiarListas();
								inicializarClases();								
								pagina = "/paginas/ModuloRegulares/admin/cargar/cargarNotasAlumnos.xhtml"; break;
						case 2: MODO_USUARIO = MODO_OCAA;
								limpiarListas();
								inicializarClases();
								pagina = "/paginas/ModuloRegulares/ocaa/cargar/cargarNotasAlumnos.xhtml"; break;
		 			} break;
		 }
		 return pagina;		
	} 
    
    /**
     * Listar cursos.
     */
    public void listarCursos() {
		System.out.println("Listando los cursos:");
		List<CursoBO> listarCursos = null;
		try {
//			listarCursos = cursoServices.listarCursos();
//			tutoriaModel.setListarCursos(listarCursos);
		} 
		catch (Exception e) {			
			e.printStackTrace();
		}
	}
    
    /**
     * Sets the desactivar descarga.
     *
     * @param desactivarDescarga the new desactivar descarga
     */
    public void setDesactivarDescarga(boolean desactivarDescarga) {
//		this.desactivarDescarga = desactivarDescarga;
	}
    
    /**
     * Listar cursosx docente.
     *
     * @throws Exception the exception
     */
    public void listarCursosxDocente() throws Exception{
		String codDocente = "";
		if (MODO == MODO_ADMIN){
//			codDocente = getTutoriaModelSelect().getpCodigo()==null?"":getTutoriaModelSelect().getpCodigo();	
		}
		else{
			if (MODO == MODO_TUTOR){
//				codDocente = obtenerUsuario().getUsername();				
//				getTutoriaModelSelect().setpCodigo(getUsuarioServices().buscarUsuarioEquivalencia(codDocente));
			}
		}
//		List<CursoBO> listaCursos = cursoServices.listarCursosDocente(codDocente, PROCESO, MODO);
//		getTutoriaModel().setListarCursos(listaCursos);
	}	
    
//    public String selectorDescargaActas(int proceso, int modo) throws Exception{
//		String pagina = "";
//		 
//		CicloBO cicloActual = comunServices.buscarCicloActual();
//		ANIO_ACTUAL = cicloActual.getAnio();
//		PERIODO_ACTUAL = cicloActual.getPeriodo();		
//		inicializarClases();		
//		switch(proceso){
//			case 1: switch(modo){ 
//		 				case 1: PROCESO = PROCESO_OBSERVADOS;
//		 						MODO = MODO_ADMIN;
//		 						MODO_AUX = MODO_ADMIN;
//		 						listarCursos();
//		 						setDesactivarDescarga(true);
//		 						pagina = "/paginas/ModuloObservados/unayoe/cargar/descargarActasTutoria.xhtml"; break;
//		 				
//		 				case 2: PROCESO = PROCESO_OBSERVADOS;
//		 						MODO = MODO_ADMIN;
//		 						MODO_AUX = MODO_OCAA;
//		 						listarCursos();
//		 						setDesactivarDescarga(true);
//		 						pagina = "/paginas/ModuloObservados/unayoe/cargar/descargarActasTutoria.xhtml"; break;
//		 				
//		 				case 3: PROCESO = PROCESO_OBSERVADOS;
// 								MODO = MODO_TUTOR;
// 								MODO_AUX = MODO_TUTOR;
// 								listarCursosxDocente();
// 								setDesactivarDescarga(true);
// 								pagina = "/paginas/ModuloObservados/unayoe/cargar/descargarActasTutoria.xhtml"; break;
//		 				
//		 				case 4: PROCESO = PROCESO_OBSERVADOS;
//		 						MODO = MODO_ADMIN;
//								MODO_AUX = MODO_UNAYOE;
//								listarCursos();
//								setDesactivarDescarga(true);
//								pagina = "/paginas/ModuloObservados/unayoe/cargar/descargarActasTutoria.xhtml"; break;
//		 			} break;
//		 	
//		}
//		return pagina;		
//	}	
    
    /**
 * Selector visualizacion notas.
 *
 * @param proceso the proceso
 * @param modoUsuario the modo usuario
 * @return the string
 * @throws Exception the exception
 */
public String selectorVisualizacionNotas(int proceso, int modoUsuario) throws Exception{
		 String pagina = "";
		   System.out.println("SelectorVisaulizarNotas");
		 inicializarClases();
	     listarCiclo();
	     System.out.println("procvisu"+proceso);
	     System.out.println("mod_usuario"+modoUsuario);
		 switch(proceso){
		 	case 1: switch(modoUsuario){ 
		 				case 1: MODO_USUARIO = MODO_ADMIN;
		 						pagina = "/paginas/ModuloObservados/admin/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 2: MODO_USUARIO = MODO_OCAA;	
		 						pagina = "/paginas/ModuloObservados/ocaa/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 3: MODO_USUARIO = MODO_DIR_ACA;	
		 						pagina = "/paginas/ModuloObservados/diraca/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 4: MODO_USUARIO = MODO_UNAYOE;	
		 						pagina = "/paginas/ModuloObservados/unayoe/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 5: MODO_USUARIO = MODO_TUTOR;	
		 						pagina = "/paginas/ModuloObservados/tutor/visualizar/visualizarNotasAlumnos.xhtml"; break;						
		 			} break;	
		 	case 2: switch(modoUsuario){ 
		 				case 1: MODO_USUARIO = MODO_ADMIN;
		 						pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 2: MODO_USUARIO = MODO_OCAA;	
		 						pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 3: MODO_USUARIO = MODO_DIR_ACA;	
		 						pagina = "/paginas/ModuloRegulares/diraca/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 4: MODO_USUARIO = MODO_UNAYOE;	
		 						pagina = "/paginas/ModuloRegulares/unayoe/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 				case 5: MODO_USUARIO = MODO_TUTOR;	
		 						pagina = "/paginas/ModuloRegulares/tutor/visualizar/visualizarNotasAlumnos.xhtml"; break;
		 			} break;
		 }
		 return pagina;		
	}
    
    /**
     * Gets the notas alumno excel models.
     *
     * @return the notas alumno excel models
     */
    public List<NotasAlumnoExcelModel> getNotasAlumnoExcelModels() {
		return notasAlumnoExcelModels;
	}

	/**
	 * Sets the notas alumno excel models.
	 *
	 * @param notasAlumnoExcelModels the new notas alumno excel models
	 */
	public void setNotasAlumnoExcelModels(List<NotasAlumnoExcelModel> notasAlumnoExcelModels) {
		this.notasAlumnoExcelModels = notasAlumnoExcelModels;
	}	

	/**
	 * Gets the notas alumno excel model.
	 *
	 * @return the notas alumno excel model
	 */
	public NotasAlumnoExcelModel getNotasAlumnoExcelModel() {
		return notasAlumnoExcelModel;
	}

	/**
	 * Sets the notas alumno excel model.
	 *
	 * @param notasAlumnoExcelModel the new notas alumno excel model
	 */
	public void setNotasAlumnoExcelModel(NotasAlumnoExcelModel notasAlumnoExcelModel) {
		this.notasAlumnoExcelModel = notasAlumnoExcelModel;
	}

	/**
	 * Gets the list notas alumno BO.
	 *
	 * @return the list notas alumno BO
	 */
	public List<NotasAlumnoBO> getListNotasAlumnoBO() {
		return listNotasAlumnoBO;
	}

	/**
	 * Sets the list notas alumno BO.
	 *
	 * @param listNotasAlumnoBO the new list notas alumno BO
	 */
	public void setListNotasAlumnoBO(List<NotasAlumnoBO> listNotasAlumnoBO) {
		this.listNotasAlumnoBO = listNotasAlumnoBO;
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
	 * Gets the alumno buscar notas model select.
	 *
	 * @return the alumno buscar notas model select
	 */
	public AlumnoModel getAlumnoBuscarNotasModelSelect() {
		return alumnoBuscarNotasModelSelect;
	}

	/**
	 * Sets the alumno buscar notas model select.
	 *
	 * @param alumnoBuscarNotasModelSelect the new alumno buscar notas model select
	 */
	public void setAlumnoBuscarNotasModelSelect(
			AlumnoModel alumnoBuscarNotasModelSelect) {
		this.alumnoBuscarNotasModelSelect = alumnoBuscarNotasModelSelect;
	}

	/**
	 * Gets the notas alumno buscar model.
	 *
	 * @return the notas alumno buscar model
	 */
	public List<NotasAlumnoExcelModel> getNotasAlumnoBuscarModel() {
		return notasAlumnoBuscarModel;
	}

	/**
	 * Sets the notas alumno buscar model.
	 *
	 * @param notasAlumnoBuscarModel the new notas alumno buscar model
	 */
	public void setNotasAlumnoBuscarModel(List<NotasAlumnoExcelModel> notasAlumnoBuscarModel) {
		this.notasAlumnoBuscarModel = notasAlumnoBuscarModel;
	}

	/**
	 * Gets the notas alumno excel models grid.
	 *
	 * @return the notas alumno excel models grid
	 */
	public List<NotasAlumnoExcelModel> getNotasAlumnoExcelModelsGrid() {
		return notasAlumnoExcelModelsGrid;
	}

	/**
	 * Sets the notas alumno excel models grid.
	 *
	 * @param notasAlumnoExcelModelsGrid the new notas alumno excel models grid
	 */
	public void setNotasAlumnoExcelModelsGrid(
			List<NotasAlumnoExcelModel> notasAlumnoExcelModelsGrid) {
		this.notasAlumnoExcelModelsGrid = notasAlumnoExcelModelsGrid;
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
	public void setVisualizarNotasAlumnoGrid(
			List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid) {
		this.visualizarNotasAlumnoGrid = visualizarNotasAlumnoGrid;
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
}
