package pe.edu.sistemas.unayoe.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.unayoe.core.util.FormateadorFecha;
import pe.edu.sistemas.unayoe.model.AsistenciaAlumnosAClasesModel;
import pe.edu.sistemas.unayoe.model.AsistenciaTutoriaModel;
import pe.edu.sistemas.unayoe.model.TutoriaModel;
import pe.edu.sistemas.unayoe.services.AlumnoServices;
import pe.edu.sistemas.unayoe.services.AsistenciaServices;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.TutoriaServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaTProfBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import controladorReporte.Asistencia;
import controladorReporte.ControladorReporte;
import controladorReporte.HorarioTutoria;

import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
 
@Controller("visualizarReporteMBean")
@ViewScoped
public class VisualizarReporteMBean {

    @Autowired
    private CursoServices cursoServices;
    @Autowired
    private TutoriaServices tutoriaServices;
    @Autowired
    private AlumnoServices alumnoServices;
    @Autowired
    private TutoriaModel tutoriaModel;
    @Autowired
    private AsistenciaServices  asistenciaServices;
    
    /*************************Atributos**********************/
    private Date dateDesde;
    private Date dateHasta;
    private StreamedContent reporte;
    private  List<AsistenciaAlumnosAClasesModel> asistenciaClases;
    private String c_alumno;
    private String n_alumno;
    private Integer periodo;
    
    /*************************************************/
    
    // atributos para la seleccion del combo
    private String nombre;
    private String descripcion;
    private boolean select;
    private AsistenciaTutoriaModel asistenciaTutoriaModel;
    private TutoriaModel tutoriaModelSelect;
    private List<TutoriaModel> listTutoria;
    private List<AlumnoBO> listAsistenciaAlumnosTutoria;
    private List<TutoriaBO>  listVisualizarAsistenciaTutoria;
    private List<AsistenciaTProfBO> listAsistenciaDocenteTutoria;
  	

	public VisualizarReporteMBean() {
        System.out.println("::::: LOADING ::::::::");
        asistenciaClases= new ArrayList<AsistenciaAlumnosAClasesModel>();
        tutoriaModelSelect = new TutoriaModel();
        listTutoria = new ArrayList<TutoriaModel>();
        listAsistenciaAlumnosTutoria = new ArrayList<AlumnoBO>();
        listVisualizarAsistenciaTutoria = new ArrayList<TutoriaBO>();
        
        InputStream in = getClass().getResourceAsStream("archivos utiles/downloaded_optimus.jpg");
        reporte = new DefaultStreamedContent(in, "application/pdf","downloaded_file.pdf");
    }
    
	private Integer obtenerPeriodo(){
    	Integer periodo=0;
    	Date fecha_actual=new Date();
    	Integer mes=fecha_actual.getMonth();
    	if(12>=mes&&mes>=8)
    		periodo=2;
    	else
    		if(mes>=4)
    			periodo=1;
    	
    	return periodo;
    }
    
    // metodos que domina de la pantallas
    public void onRowSelect(SelectEvent event) {
        System.out.println("entra a la seleccion");
        nombre = tutoriaModelSelect.getNombres();
        descripcion = tutoriaModelSelect.getDescripcion();
        select = false;
        listarCursos();
    }


    public String visualizarReporteAsistenciaAlumnosTutoriaUser() {
		System.out.println("visualizarReporteAsistenciaAlumnosTutoria");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/user/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosTutoria.xhtml";

	}

    public String visualizarReporteAsistenciaAlumnosTutoriaDirAca() {
		System.out.println("visualizarReporteAsistenciaAlumnosTutoriaDirAca");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/diraca/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosTutoria.xhtml";

	}
    
    public String visualizarReporteAsistenciaAlumnosTutoriaUnayoe() {
		System.out.println("visualizarReporteAsistenciaAlumnosTutoria");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/unayoe/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosTutoria.xhtml";

	}
    public String visualizarReporteAsistenciaAlumnosTutoria() {
		System.out.println("visualizarReporteAsistenciaAlumnosTutoria");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/ModuloRegulares/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosTutoria.xhtml";

	}

    public String visualizarReporteAsistenciaDeTutorUser() {
		System.out.println("visualizarReporteAsistenciaDeTutor");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/user/visualizar/reportes_asistencia/visualizarReporteAsistenciaDeTutor.xhtml";

    }

    public String visualizarReporteAsistenciaDeTutorDirAca() {
		System.out.println("visualizarReporteAsistenciaDeTutorDirAca");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/diraca/visualizar/reportes_asistencia/visualizarReporteAsistenciaDeTutor.xhtml";

    }
    public String visualizarReporteAsistenciaDeTutorUnayoe() {
		System.out.println("visualizarReporteAsistenciaDeTutor");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/unayoe/visualizar/reportes_asistencia/visualizarReporteAsistenciaDeTutor.xhtml";

    }
    public String visualizarReporteAsistenciaDeTutor() {
		System.out.println("visualizarReporteAsistenciaDeTutor");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/visualizar/reportes_asistencia/visualizarReporteAsistenciaDeTutor.xhtml";

    }
    
    public String visualizarReporteAsistenciaAlumnosClase() {
		System.out.println("visualizarReporteAsistenciaAlumnosClase");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosClase.xhtml";

    }

    public String visualizarReporteAsistenciaAlumnosClaseUser() {
		System.out.println("visualizarReporteAsistenciaAlumnosClase");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/user/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosClase.xhtml";

    }
    public String visualizarReporteAsistenciaAlumnosClaseDirAca() {
		System.out.println("visualizarReporteAsistenciaAlumnosClaseDirAca");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/diraca/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosClase.xhtml";

    }
    

    public String visualizarReporteAsistenciaAlumnosClaseUnayoe() {
		System.out.println("visualizarReporteAsistenciaAlumnosClase");
		listarCursos();
		if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		this.periodo=obtenerPeriodo();
		return "/paginas/unayoe/visualizar/reportes_asistencia/visualizarReporteAsistenciaAlumnosClase.xhtml";

    }
    
    public String visualizarHorarioDeTutoriaSemanal() {
    	listarCursos();
    	if(asistenciaClases!=null)
			asistenciaClases.clear();
		c_alumno="";
		n_alumno="";
		System.out.println("visualizarHorarioDeTutoriaSemanal");
		this.periodo=obtenerPeriodo();
		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}

    public String imprimir() {
		System.out.println("imprimir");
		 
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

    public void asistenciaAlumnoTutoria(){
    	System.out.println("datos"+asistenciaTutoriaModel);
    	System.out.println(dateDesde);
    	System.out.println(dateHasta);
    	System.out.println(c_alumno);
    	System.out.println(periodo);
    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
    	if(dateDesde!=null && dateDesde!=null && !c_alumno.isEmpty())
    	try {
    		AlumnoBO alu=alumnoServices.obtenerAlumno(c_alumno);
    		if(alu!=null)
    			n_alumno=alu.getaNombre()+" "+alu.getaApellido();
    		String fini = formateador.format(dateDesde);
        	String ffin = formateador.format(dateHasta);
			List<AsistenciaCAlumnoBO> lista =asistenciaServices.buscarAsistenciaAlumnoTutotria(c_alumno, fini, ffin);
			if(lista.size()==0)
				llenarDataAsistenciaEjemplo("AT");
			else
				mostrarAsistencias(lista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void asistenciaAlumnoClase(){
    	System.out.println("datos"+asistenciaTutoriaModel);
    	System.out.println(dateDesde);
    	System.out.println(dateHasta);
    	System.out.println(c_alumno);
    	System.out.println(periodo);
    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
    	if(dateDesde!=null && dateDesde!=null&&!c_alumno.isEmpty())
    	try {
    		AlumnoBO alu=alumnoServices.obtenerAlumno(c_alumno);
    		if(alu!=null)
    			n_alumno=alu.getaNombre()+" "+alu.getaApellido();
    		String fini = formateador.format(dateDesde);
        	String ffin = formateador.format(dateHasta);
			List<AsistenciaCAlumnoBO> lista =asistenciaServices.buscarAsistenciaAlumnoClase(c_alumno, 2014, periodo, fini, ffin);
			if(lista.size()==0)
				llenarDataAsistenciaEjemplo("AC");
			else
				mostrarAsistencias(lista);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void asistenciaTutor(){
    	System.out.println("datos"+asistenciaTutoriaModel);
    	System.out.println(dateDesde);
    	System.out.println(dateHasta);
    	System.out.println(c_alumno);
    	System.out.println(periodo);
    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
    	if(dateDesde!=null && dateDesde!=null&&!c_alumno.isEmpty())
    	try {
    		AlumnoBO alu=alumnoServices.obtenerAlumno(c_alumno);
    		if(alu!=null)
    			n_alumno=alu.getaNombre()+" "+alu.getaApellido();
    		String fini = formateador.format(dateDesde);
        	String ffin = formateador.format(dateHasta);
			List<AsistenciaCAlumnoBO> lista =asistenciaServices.buscarAsistenciaTutorTutotria(c_alumno, fini, ffin);
			if(lista.size()==0)
				llenarDataAsistenciaEjemplo("TT");
			else
				mostrarAsistencias(lista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // metodos get y set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDateDesde() {
		return dateDesde;
	}

	public void setDateDesde(Date dateDesde) {
		this.dateDesde = dateDesde;
	}

	public Date getDateHasta() {
		return dateHasta;
	}


	public void setDateHasta(Date dateHasta) {
		this.dateHasta = dateHasta;
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
	   
    public List<AlumnoBO> getListAsistenciaAlumnosTutoria() {
		return listAsistenciaAlumnosTutoria;
	}


	public void setListAsistenciaAlumnosTutoria(
			List<AlumnoBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}


	public StreamedContent getReporte() {
		return reporte;
	}


	public void setReporte(StreamedContent reporte) {
		this.reporte = reporte;
	}
    


    public List<AsistenciaTProfBO> getListAsistenciaDocenteTutoria() {
		return listAsistenciaDocenteTutoria;
	}


	public void setListAsistenciaDocenteTutoria(
			List<AsistenciaTProfBO> listAsistenciaDocenteTutoria) {
		this.listAsistenciaDocenteTutoria = listAsistenciaDocenteTutoria;
	}


	public List<TutoriaBO> getListVisualizarAsistenciaTutoria() {
		return listVisualizarAsistenciaTutoria;
	}


	public void setListVisualizarAsistenciaTutoria(
			List<TutoriaBO> listVisualizarAsistenciaTutoria) {
		this.listVisualizarAsistenciaTutoria = listVisualizarAsistenciaTutoria;
	}


	public List<AsistenciaAlumnosAClasesModel> getAsistenciaClases() {
		return asistenciaClases;
	}


	public void setAsistenciaClases(
			List<AsistenciaAlumnosAClasesModel> asistenciaClases) {
		this.asistenciaClases = asistenciaClases;
	}


	public String getC_alumno() {
		return c_alumno;
	}


	public void setC_alumno(String c_alumno) {
		this.c_alumno = c_alumno;
	}


	public String getN_alumno() {
		return n_alumno;
	}


	public void setN_alumno(String n_alumno) {
		this.n_alumno = n_alumno;
	}


	public Integer getPeriodo() {
		return periodo;
	}


	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	public String cancelar() {
		System.out.println("cancelar");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	
	void mostrarAsistencias(List<AsistenciaCAlumnoBO> lista){
		asistenciaClases.clear();
		for(AsistenciaCAlumnoBO  abo : lista){
			AsistenciaAlumnosAClasesModel asistencia = new  AsistenciaAlumnosAClasesModel();
			asistencia.setDia(abo.getDia());
			asistencia.setFecha(abo.getFecha());
			asistencia.setA_codigo(abo.getaCodigo());
			asistencia.setC_codigo(abo.getcCodigo());
			asistencia.setAsistencia(abo.getAsistencia());
			asistencia.setObservacion(abo.getObservacion());
			asistencia.setC_nombre(abo.getcNombre());
			asistencia.setRepitencias(abo.getRepitencia());
			asistenciaClases.add(asistencia);
		}
	}
	
	void llenarDataAsistenciaEjemplo(String TIPO_ASI){
		asistenciaClases.clear();
		
		AsistenciaAlumnosAClasesModel asistencia = new AsistenciaAlumnosAClasesModel();
		if(TIPO_ASI.equalsIgnoreCase("AT")){
			asistencia = new AsistenciaAlumnosAClasesModel("20/10/14","LUNES","08200001","INGENIERIA DE NEGOCIOS","209003","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("29/10/14","MIERCOLES","08200001","INGENIERIA DE NEGOCIOS","209003","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("05/11/14","MIERCOLES","08200001","INGENIERIA DE NEGOCIOS","209003","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		}
		if(TIPO_ASI.equalsIgnoreCase("AC")){
			asistencia = new AsistenciaAlumnosAClasesModel("11/11/14","MARTES","08200003","INGENIERÍA DE SOFTWARE","208007","jessica","pariona ramos" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("18/08/14","MARTES","08200003","INGENIERÍA DE SOFTWARE","208007","jessica","pariona ramos" , "3","A","El alumno ha faltado a clases ultimamente. No esta cumpliendo con entrega de su proyecto curso.");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("12/08/14","MIERCOLES","08200001","INGENIERÍA DE SOFTWARE","208007","jessica","pariona ramos" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		}
		
		if(TIPO_ASI.equalsIgnoreCase("TT")){
			asistencia = new AsistenciaAlumnosAClasesModel("06/11/14","JUEVES","08200003","METODOLOGÍA PARA LA ELABORACIÓN DE TESIS","208008","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("13/08/14","JUEVES","08200003","METODOLOGÍA PARA LA ELABORACIÓN DE TESIS","208008","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		asistencia = new AsistenciaAlumnosAClasesModel("20/08/14","JUEVES","08200003","SISTEMAS DISTRIBUIDOS","208003","cristian ","rios cornejo" , "3","A","Ninguna");
		asistenciaClases.add(asistencia);
		}
		
		
	}
	
	public void imprimirReporteAsistenciaAlumnoTutoria(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("AT")  ,obtenerCampos() );
    	
    }
	
	public void imprimirReporteAsistenciaAlumnoClase(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("AC")  ,obtenerCampos() );
    	
    }
	
	public void imprimirReporteAsistenciaTutorTutoria(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("TT")  ,obtenerCampos() );
    	
    }
	
	private Map obtenerParametros(String TIPO_ASI){
    	Map pars = new HashMap();
    	
    	if(TIPO_ASI.equalsIgnoreCase("AT")){
    		pars.put("persona", "Alumno");
    		pars.put("tipoAsi", "Tutoria");
    	}
    	if(TIPO_ASI.equalsIgnoreCase("AC")){
    		pars.put("persona", "Alumno");
    		pars.put("tipoAsi", "Clase");
    	}
    	
    	if(TIPO_ASI.equalsIgnoreCase("TT")){
    		pars.put("persona", "Tutor");
    		pars.put("tipoAsi", "Tutoria");
    	}
    	
    	pars.put("escudounmsm", "imagenes/unmsm.gif");
    	return pars;
    }
	
	private Collection<Asistencia> obtenerCampos(){
    	Collection<Asistencia> list = new ArrayList<>();
    	for(AsistenciaAlumnosAClasesModel model : asistenciaClases){
    		Asistencia asistencia =new Asistencia();
    		asistencia.setFecha( model.getFecha());
    		asistencia.setDia(model.getDia());
    		asistencia.setCodCurso(model.getC_codigo());
    		asistencia.setNomCurso(model.getC_nombre());
    		asistencia.setRepitencia(model.getRepitencias());
    		asistencia.setAsistencia(model.getAsistencia());
    		asistencia.setObs(model.getObservacion());
    		list.add(asistencia);
    	}
    	return list;
    }

}
