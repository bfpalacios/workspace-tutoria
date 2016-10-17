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


@Controller("notasAlumnoMBean")
@ViewScoped
public class NotasAlumnosMBean {
	@Autowired
	private ArchivoModel archivoModel;
	@Autowired
	private ComunServices comunServices;
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired 
	private ClaseMaestraModel claseMaestraModel;
	@Autowired
	private NotasAlumnoExcelModel notasAlumnoExcelModel;
	@Autowired
	private List<NotasAlumnoExcelModel> notasAlumnoExcelModels;	
	@Autowired
	private List<NotasAlumnoExcelModel> notasAlumnoBuscarModel;
	
	private List<NotasAlumnoBO> listNotasAlumnoBO;	
	private AlumnoModel alumnoBuscarNotasModelSelect;
	private ClaseMaestraModel claseMaestraModelSelect;
	private List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid;
	private List<NotasAlumnoExcelModel> notasAlumnoExcelModelsGrid;
		
	private int existe = 0;
	private int valido = 1;
	
	private int MODO_USUARIO;
	private static int MODO_ADMIN = 1;
	private static int MODO_OCAA = 2;
	private static int MODO_DIR_ACA = 3;
	private static int MODO_UNAYOE = 4;
	private static int MODO_TUTOR = 5;
	
	private int PROCESO;	
	
	private static int ANIO=0;
	private static int PERIODO=1;
	private static int PLAN=2;
	private static int COD_CURSO=3;
	private static int NOM_CURSO=4;
	private static int CREDITOS=5;
	private static int COD_ALUMNO=6;
	private static int NOM_ALUMNO=7;
	private static int NOM_DOCENTE=8;
	private static int NOTA_FINAL=9; 
	
	

	private int MODO;
	private int MODO_AUX;
	private int ANIO_ACTUAL;
	private int PERIODO_ACTUAL;	
	
	private static int PROCESO_OBSERVADOS = 1;
	private static int PROCESO_REGULARES = 2;			

	public NotasAlumnosMBean(){
		System.out.println("::NOTAS ALUMNOS BEAN::");	
		inicializarClases();
	}
	
	private void inicializarClases(){
		System.out.println("inicializa");
		setAlumnoBuscarNotasModelSelect(new AlumnoModel());	
		setClaseMaestraModelSelect(new ClaseMaestraModel());
		setListNotasAlumnoBO(new ArrayList<NotasAlumnoBO>());
		setVisualizarNotasAlumnoGrid(new ArrayList<NotasAlumnoExcelModel>());
		setNotasAlumnoExcelModelsGrid(new ArrayList<NotasAlumnoExcelModel>());
	}
	
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
	        
	        	int rowNum = ws.getLastRowNum() + 1;
		        	//rowNum=rowNum-6;
	        	rowNum=10;
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
	        	//mostrarMensaje(1);
	        	//e.printStackTrace();
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
	
	public boolean setExcel(UploadedFile excelCargado, FileUploadEvent event){
		boolean archivoCargado = false;
		if(excelCargado != null) {
			System.out.println("EXCELCARGADO LA DVD");
			archivoCargado = true;
            getArchivoModel().setNombre(excelCargado.getFileName());
			//mostrarMensaje(2);            
        }else{
        	//mostrarMensaje(3);
        }
		return archivoCargado;
	}
	
	private void limpiarListas(){
		if (getNotasAlumnoExcelModels() != null){
			getNotasAlumnoExcelModels().removeAll(getNotasAlumnoExcelModels());
		}
		
		if (getNotasAlumnoExcelModelsGrid() != null){
			getNotasAlumnoExcelModelsGrid().removeAll(getNotasAlumnoExcelModelsGrid());
		}
	}
	
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
	
	public void imprimirReporteNotasAlumno() throws Exception{
		if(!getVisualizarNotasAlumnoGrid().isEmpty()){
	    	System.out.println("Impresion de reporte de horario:");    	
	    	ControladorReporte reporte = new ControladorReporte();
	    	reporte.setNombreReporte("visualizarNotasAlumno");
	    	reporte.generarReporteNotasAlumno(obtenerParametros(),obtenerCampos());
    	}else
    		mostrarMensaje(6);
    }

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
    
    public String selectorCargaNotas(int proceso, int modo) throws Exception{
    	System.out.println("selectorctm"); 
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
    
    public void setDesactivarDescarga(boolean desactivarDescarga) {
//		this.desactivarDescarga = desactivarDescarga;
	}
    
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
    
    public String selectorVisualizacionNotas(int proceso, int modoUsuario) throws Exception{
		 String pagina = "";
		   System.out.println("SelectorVisaulizaciones");
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
    
    public List<NotasAlumnoExcelModel> getNotasAlumnoExcelModels() {
		return notasAlumnoExcelModels;
	}

	public void setNotasAlumnoExcelModels(List<NotasAlumnoExcelModel> notasAlumnoExcelModels) {
		this.notasAlumnoExcelModels = notasAlumnoExcelModels;
	}	

	public NotasAlumnoExcelModel getNotasAlumnoExcelModel() {
		return notasAlumnoExcelModel;
	}

	public void setNotasAlumnoExcelModel(NotasAlumnoExcelModel notasAlumnoExcelModel) {
		this.notasAlumnoExcelModel = notasAlumnoExcelModel;
	}

	public List<NotasAlumnoBO> getListNotasAlumnoBO() {
		return listNotasAlumnoBO;
	}

	public void setListNotasAlumnoBO(List<NotasAlumnoBO> listNotasAlumnoBO) {
		this.listNotasAlumnoBO = listNotasAlumnoBO;
	}
	
	public ClaseMaestraModel getClaseMaestraModelSelect() {
		return claseMaestraModelSelect;
	}

	public void setClaseMaestraModelSelect(ClaseMaestraModel claseMaestraModelSelect) {
		this.claseMaestraModelSelect = claseMaestraModelSelect;
	}

	public ClaseMaestraModel getClaseMaestraModel() {
		return claseMaestraModel;
	}

	public void setClaseMaestraModel(ClaseMaestraModel claseMaestraModel) {
		this.claseMaestraModel = claseMaestraModel;
	}

	public AlumnoModel getAlumnoBuscarNotasModelSelect() {
		return alumnoBuscarNotasModelSelect;
	}

	public void setAlumnoBuscarNotasModelSelect(
			AlumnoModel alumnoBuscarNotasModelSelect) {
		this.alumnoBuscarNotasModelSelect = alumnoBuscarNotasModelSelect;
	}

	public List<NotasAlumnoExcelModel> getNotasAlumnoBuscarModel() {
		return notasAlumnoBuscarModel;
	}

	public void setNotasAlumnoBuscarModel(List<NotasAlumnoExcelModel> notasAlumnoBuscarModel) {
		this.notasAlumnoBuscarModel = notasAlumnoBuscarModel;
	}

	public List<NotasAlumnoExcelModel> getNotasAlumnoExcelModelsGrid() {
		return notasAlumnoExcelModelsGrid;
	}

	public void setNotasAlumnoExcelModelsGrid(
			List<NotasAlumnoExcelModel> notasAlumnoExcelModelsGrid) {
		this.notasAlumnoExcelModelsGrid = notasAlumnoExcelModelsGrid;
	}

	public List<NotasAlumnoExcelModel> getVisualizarNotasAlumnoGrid() {
		return visualizarNotasAlumnoGrid;
	}

	public void setVisualizarNotasAlumnoGrid(
			List<NotasAlumnoExcelModel> visualizarNotasAlumnoGrid) {
		this.visualizarNotasAlumnoGrid = visualizarNotasAlumnoGrid;
	}

	public ArchivoModel getArchivoModel() {
		return archivoModel;
	}

	public void setArchivoModel(ArchivoModel archivoModel) {
		this.archivoModel = archivoModel;
	}	
}
