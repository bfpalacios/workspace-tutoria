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
 
// TODO: Auto-generated Javadoc
/**
 * The Class VisualizarReporteMBean.
 */
@Controller("visualizarReporteMBean")
@ViewScoped
public class VisualizarReporteMBean {

    /** The curso services. */
    @Autowired
    private CursoServices cursoServices;
    
    /** The tutoria services. */
    @Autowired
    private TutoriaServices tutoriaServices;
    
    /** The alumno services. */
    @Autowired
    private AlumnoServices alumnoServices;
    
    /** The tutoria model. */
    @Autowired
    private TutoriaModel tutoriaModel;
    
    /** The asistencia services. */
    @Autowired
    private AsistenciaServices  asistenciaServices;
    
    /** ***********************Atributos*********************. */
    private Date dateDesde;
    
    /** The date hasta. */
    private Date dateHasta;
    
    /** The reporte. */
    private StreamedContent reporte;
    
    /** The asistencia clases. */
    private  List<AsistenciaAlumnosAClasesModel> asistenciaClases;
    
    /** The c alumno. */
    private String c_alumno;
    
    /** The n alumno. */
    private String n_alumno;
    
    /** The periodo. */
    private Integer periodo;
    
    /** **********************************************. */
    
    // atributos para la seleccion del combo
    private String nombre;
    
    /** The descripcion. */
    private String descripcion;
    
    /** The select. */
    private boolean select;
    
    /** The asistencia tutoria model. */
    private AsistenciaTutoriaModel asistenciaTutoriaModel;
    
    /** The tutoria model select. */
    private TutoriaModel tutoriaModelSelect;
    
    /** The list tutoria. */
    private List<TutoriaModel> listTutoria;
    
    /** The list asistencia alumnos tutoria. */
    private List<AlumnoBO> listAsistenciaAlumnosTutoria;
    
    /** The list visualizar asistencia tutoria. */
    private List<TutoriaBO>  listVisualizarAsistenciaTutoria;
    
    /** The list asistencia docente tutoria. */
    private List<AsistenciaTProfBO> listAsistenciaDocenteTutoria;
  	

	/**
	 * Instantiates a new visualizar reporte M bean.
	 */
	public VisualizarReporteMBean() {
        System.out.println("::::: VISUALIZAR reporte ::::::::");
        asistenciaClases= new ArrayList<AsistenciaAlumnosAClasesModel>();
        tutoriaModelSelect = new TutoriaModel();
        listTutoria = new ArrayList<TutoriaModel>();
        listAsistenciaAlumnosTutoria = new ArrayList<AlumnoBO>();
        listVisualizarAsistenciaTutoria = new ArrayList<TutoriaBO>();
        
        InputStream in = getClass().getResourceAsStream("archivos utiles/downloaded_optimus.jpg");
        reporte = new DefaultStreamedContent(in, "application/pdf","downloaded_file.pdf");
    }
    
	/**
	 * Obtener periodo.
	 *
	 * @return the integer
	 */
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
    
    /**
     * On row select.
     *
     * @param event the event
     */
    // metodos que domina de la pantallas
    public void onRowSelect(SelectEvent event) {
        System.out.println("entra a la seleccion");
        nombre = tutoriaModelSelect.getNombres();
        descripcion = tutoriaModelSelect.getDescripcion();
        select = false;
        listarCursos();
    }


    /**
     * Visualizar reporte asistencia alumnos tutoria user.
     *
     * @return the string
     */
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

    /**
     * Visualizar reporte asistencia alumnos tutoria dir aca.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia alumnos tutoria unayoe.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia alumnos tutoria.
     *
     * @return the string
     */
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

    /**
     * Visualizar reporte asistencia de tutor user.
     *
     * @return the string
     */
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

    /**
     * Visualizar reporte asistencia de tutor dir aca.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia de tutor unayoe.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia de tutor.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia alumnos clase.
     *
     * @return the string
     */
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

    /**
     * Visualizar reporte asistencia alumnos clase user.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar reporte asistencia alumnos clase dir aca.
     *
     * @return the string
     */
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
    

    /**
     * Visualizar reporte asistencia alumnos clase unayoe.
     *
     * @return the string
     */
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
    
    /**
     * Visualizar horario de tutoria semanal.
     *
     * @return the string
     */
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

    /**
     * Imprimir.
     *
     * @return the string
     */
    public String imprimir() {
		System.out.println("imprimir");
		 
		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
    

     
    /**
     * Listar cursos.
     */
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

    /**
     * Asistencia alumno tutoria.
     */
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
    
    /**
     * Asistencia alumno clase.
     */
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
    
    /**
     * Asistencia tutor.
     */
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

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    // metodos get y set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the date desde.
     *
     * @return the date desde
     */
    public Date getDateDesde() {
		return dateDesde;
	}

	/**
	 * Sets the date desde.
	 *
	 * @param dateDesde the new date desde
	 */
	public void setDateDesde(Date dateDesde) {
		this.dateDesde = dateDesde;
	}

	/**
	 * Gets the date hasta.
	 *
	 * @return the date hasta
	 */
	public Date getDateHasta() {
		return dateHasta;
	}


	/**
	 * Sets the date hasta.
	 *
	 * @param dateHasta the new date hasta
	 */
	public void setDateHasta(Date dateHasta) {
		this.dateHasta = dateHasta;
	}


	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
	 * Gets the list tutoria.
	 *
	 * @return the list tutoria
	 */
	public List<TutoriaModel> getListTutoria() {
		return listTutoria;
	}


	/**
	 * Sets the list tutoria.
	 *
	 * @param listTutoria the new list tutoria
	 */
	public void setListTutoria(List<TutoriaModel> listTutoria) {
		this.listTutoria = listTutoria;
	}
 


	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	   
    /**
     * Gets the list asistencia alumnos tutoria.
     *
     * @return the list asistencia alumnos tutoria
     */
    public List<AlumnoBO> getListAsistenciaAlumnosTutoria() {
		return listAsistenciaAlumnosTutoria;
	}


	/**
	 * Sets the list asistencia alumnos tutoria.
	 *
	 * @param listAsistenciaAlumnosTutoria the new list asistencia alumnos tutoria
	 */
	public void setListAsistenciaAlumnosTutoria(
			List<AlumnoBO> listAsistenciaAlumnosTutoria) {
		this.listAsistenciaAlumnosTutoria = listAsistenciaAlumnosTutoria;
	}


	/**
	 * Gets the reporte.
	 *
	 * @return the reporte
	 */
	public StreamedContent getReporte() {
		return reporte;
	}


	/**
	 * Sets the reporte.
	 *
	 * @param reporte the new reporte
	 */
	public void setReporte(StreamedContent reporte) {
		this.reporte = reporte;
	}
    


    /**
     * Gets the list asistencia docente tutoria.
     *
     * @return the list asistencia docente tutoria
     */
    public List<AsistenciaTProfBO> getListAsistenciaDocenteTutoria() {
		return listAsistenciaDocenteTutoria;
	}


	/**
	 * Sets the list asistencia docente tutoria.
	 *
	 * @param listAsistenciaDocenteTutoria the new list asistencia docente tutoria
	 */
	public void setListAsistenciaDocenteTutoria(
			List<AsistenciaTProfBO> listAsistenciaDocenteTutoria) {
		this.listAsistenciaDocenteTutoria = listAsistenciaDocenteTutoria;
	}


	/**
	 * Gets the list visualizar asistencia tutoria.
	 *
	 * @return the list visualizar asistencia tutoria
	 */
	public List<TutoriaBO> getListVisualizarAsistenciaTutoria() {
		return listVisualizarAsistenciaTutoria;
	}


	/**
	 * Sets the list visualizar asistencia tutoria.
	 *
	 * @param listVisualizarAsistenciaTutoria the new list visualizar asistencia tutoria
	 */
	public void setListVisualizarAsistenciaTutoria(
			List<TutoriaBO> listVisualizarAsistenciaTutoria) {
		this.listVisualizarAsistenciaTutoria = listVisualizarAsistenciaTutoria;
	}


	/**
	 * Gets the asistencia clases.
	 *
	 * @return the asistencia clases
	 */
	public List<AsistenciaAlumnosAClasesModel> getAsistenciaClases() {
		return asistenciaClases;
	}


	/**
	 * Sets the asistencia clases.
	 *
	 * @param asistenciaClases the new asistencia clases
	 */
	public void setAsistenciaClases(
			List<AsistenciaAlumnosAClasesModel> asistenciaClases) {
		this.asistenciaClases = asistenciaClases;
	}


	/**
	 * Gets the c alumno.
	 *
	 * @return the c alumno
	 */
	public String getC_alumno() {
		return c_alumno;
	}


	/**
	 * Sets the c alumno.
	 *
	 * @param c_alumno the new c alumno
	 */
	public void setC_alumno(String c_alumno) {
		this.c_alumno = c_alumno;
	}


	/**
	 * Gets the n alumno.
	 *
	 * @return the n alumno
	 */
	public String getN_alumno() {
		return n_alumno;
	}


	/**
	 * Sets the n alumno.
	 *
	 * @param n_alumno the new n alumno
	 */
	public void setN_alumno(String n_alumno) {
		this.n_alumno = n_alumno;
	}


	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public Integer getPeriodo() {
		return periodo;
	}


	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Cancelar.
	 *
	 * @return the string
	 */
	public String cancelar() {
		System.out.println("cancelar");

		return "/paginas/visualizar/visualizarHorariosDeTutoriaSemanal.xhtml";

	}
	
	/**
	 * Mostrar asistencias.
	 *
	 * @param lista the lista
	 */
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
	
	/**
	 * Llenar data asistencia ejemplo.
	 *
	 * @param TIPO_ASI the tipo asi
	 */
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
	
	/**
	 * Imprimir reporte asistencia alumno tutoria.
	 */
	public void imprimirReporteAsistenciaAlumnoTutoria(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("AT")  ,obtenerCampos() );
    	
    }
	
	/**
	 * Imprimir reporte asistencia alumno clase.
	 */
	public void imprimirReporteAsistenciaAlumnoClase(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("AC")  ,obtenerCampos() );
    	
    }
	
	/**
	 * Imprimir reporte asistencia tutor tutoria.
	 */
	public void imprimirReporteAsistenciaTutorTutoria(){
    	ControladorReporte reporte =  new ControladorReporte();
    	reporte.setNombreReporte("reporteAsistencia");
    	reporte.generarReporteHorarioDocente(obtenerParametros("TT")  ,obtenerCampos() );
    	
    }
	
	/**
	 * Obtener parametros.
	 *
	 * @param TIPO_ASI the tipo asi
	 * @return the map
	 */
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
	
	/**
	 * Obtener campos.
	 *
	 * @return the collection
	 */
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
