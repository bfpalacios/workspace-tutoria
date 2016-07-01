package pe.edu.sistemas.unayoe.controlador;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.application.FacesMessage;

import org.springframework.stereotype.Controller;
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

@Controller("disponibilidadMBean")
@ViewScoped
public class DisponibilidadMBean {
	@Autowired
    private DisponibilidadModel disponibilidadModel;
	@Autowired
	private TutoriaServices tutoriaServices;	
	@Autowired
	private ComunServices comunServices;
	@Autowired
	private AlumnoServices alumnoServices;
	@Autowired
	private CursoServices cursoServices;
	
	private List<ClaseMaestra> listarCursos;
	private List<DisponibilidadBO> listaDisponibilidades;
	private DisponibilidadModel disponibilidadModelSelect;
	private List<DisponibilidadModel> listaDisponibilidad;
	private List<DisponibilidadModel> listaDisponibilidadGrid;
	
	private int MODO;
	private int PROCESO_REGISTRO;
	private int PROCESO_BUSQUEDA;
	
	private static int MODO_ADMIN = 1;
	private static int MODO_TUTOR = 2;
	private static int MODO_ALUMNO = 3;
	private static int MODO_OCAA = 4;
	private static int PROCESO_TUTOR = 1;
	private static int PROCESO_ALUMNO = 2;	
	
	public DisponibilidadMBean(){
		inicializarClases();		
	}

	public void inicializarClases(){
		setListarCursos(new ArrayList<ClaseMaestra>());
		setDisponibilidadModelSelect(new DisponibilidadModel());
		setListaDisponibilidades(new ArrayList<DisponibilidadBO>());
		setListaDisponibilidad(new ArrayList<DisponibilidadModel>());
		setListaDisponibilidadGrid(new ArrayList<DisponibilidadModel>());
	}
	
	public void limpiarClases(){
		if (getDisponibilidadModel() != null){
			setDisponibilidadModel(null);
			setDisponibilidadModel(new DisponibilidadModel());
		}
		
		if (getListaDisponibilidadGrid() != null){
			setListaDisponibilidadGrid(null);
			setListaDisponibilidadGrid(new ArrayList<DisponibilidadModel>());
		}
	}
	
	public void listarAreaConocimiento(){
		try{
			List<AreaConocimientoBO> listaAreaConocimiento = tutoriaServices.listarAreaConocimiento();
			getDisponibilidadModel().setListaAreaConocimiento(listaAreaConocimiento);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void listarDocenteRegular(){
		try{
			List<ProfesorBO> listaTutoresRegulares = tutoriaServices.listarTutoresRegulares();
			getDisponibilidadModel().setListaTutoresRegulares(listaTutoresRegulares);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void listarAlumnoRegular(){
		try{
			List<AlumnoBO> listaAlumnosRegulares = tutoriaServices.listarAlumnosRegulares();
			getDisponibilidadModel().setListaAlumnosRegulares(listaAlumnosRegulares);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
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
		
	public void listarCursos() {       
        List<CursoBO> listarCursos = null;
        try {
            listarCursos = cursoServices.listarCursos();            
            getDisponibilidadModel().setListaCursos(listarCursos);
        } 
        catch (Exception e) {            
            e.printStackTrace();
        }
    } 
	
	public void actualizarAreaConocimiento(ValueChangeEvent e){
		try{
			String codAreaConocimiento = (String)(e.getNewValue()==null?"":e.getNewValue());
			List<CursoBO> listaCursos = tutoriaServices.listarCursosxAreaConocimiento(codAreaConocimiento);
			getDisponibilidadModel().setListaCursos(listaCursos);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void actualizarHoraFin(ValueChangeEvent e) {		
		try{
			int idHoraInicio = Integer.parseInt((String) (e.getNewValue()==null?"0":e.getNewValue()));		
			List<ClaseMaestra> listaHoraFin = new ArrayList<ClaseMaestra>();;
			listaHoraFin = comunServices.actualizarHoraFin(idHoraInicio);					
			getDisponibilidadModel().setListaHoraFin(listaHoraFin);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public void actualizarDocente(ValueChangeEvent e) throws Exception{		
		String codCurso = (String) (e.getNewValue()==null?"": e.getNewValue());		
		List<ProfesorBO> listaProfesores = new ArrayList<ProfesorBO>();
		System.out.println("PROCESO DE BUSQUEDA "+PROCESO_BUSQUEDA);
		PROCESO_BUSQUEDA=1;
		switch(PROCESO_BUSQUEDA){
			case 2: listaProfesores = tutoriaServices.listarTutoresObservados(codCurso); break;
			case 1: listaProfesores = tutoriaServices.listarTutoresRegulares(codCurso); break;
		}		
		getDisponibilidadModel().setListaTutoresRegulares(listaProfesores);
	}
	
	public void actualizarAlumno(ValueChangeEvent e) throws Exception{		
		String codCurso = getDisponibilidadModelSelect().getCodCurso()==null?"":getDisponibilidadModelSelect().getCodCurso();
		String codDocente = (String) (e.getNewValue()==null?"": e.getNewValue());
		
		List<AlumnoBO> listaAlumnos = alumnoServices.listarAlumnoTutoria(codDocente, codCurso, 2, 1);
		
		getDisponibilidadModel().setListaAlumnosRegulares(listaAlumnos);
	}
	
	public String agregarDisponibilidad(){
		String pagina = "";
		try{	
			String codDocente = "";
			String codAlumno = "";
			DisponibilidadModel disponibilidadHoraria = new DisponibilidadModel();
		
			if (getListaDisponibilidadGrid().size() == 3){
				mostrarMensaje(8);
			}
			else{
				String codAreaConocimiento = getDisponibilidadModelSelect().getCodAreaConocimiento()==""?"Invalido":getDisponibilidadModelSelect().getCodAreaConocimiento();
				String codCurso = getDisponibilidadModelSelect().getCodCurso()==""?"Invalido":getDisponibilidadModelSelect().getCodCurso();
				String dia = getDisponibilidadModelSelect().getDia()==""?"Invalido":getDisponibilidadModelSelect().getDia();
				String horaInicio = getDisponibilidadModelSelect().getHoraInicio()==""?"Invalido":getDisponibilidadModelSelect().getHoraInicio();
				String horaFin = getDisponibilidadModelSelect().getHoraFin()==""?"Invalido":getDisponibilidadModelSelect().getHoraFin();
							
				if (MODO == MODO_ADMIN){
					if (PROCESO_REGISTRO == PROCESO_TUTOR){
						codDocente = getDisponibilidadModelSelect().getCodDocente()==""?"Invalido":getDisponibilidadModelSelect().getCodDocente();
					}
					else{
						if (PROCESO_REGISTRO == PROCESO_ALUMNO){
							codAlumno = getDisponibilidadModelSelect().getCodAlumno()==""?"Invalido":getDisponibilidadModelSelect().getCodAlumno();
						}
					}					
				}
				
				if (validarCamposDisponibilidad(codAreaConocimiento, codCurso, dia, horaInicio, horaFin, codDocente, codAlumno)){
					AreaConocimientoBO areaConocimiento = comunServices.buscarDatosAreaConocimiento(codAreaConocimiento);
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
					getListaDisponibilidadGrid().add(disponibilidadHoraria);
				}
			}		
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		switch(MODO){
			case 1: switch(PROCESO_REGISTRO){
						case 1: pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;
					} break;
			
			case 2: pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
			case 3: pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;	
		}
		return pagina;
	}
	
	public String mostrarDisponibilidad(){
		String pagina = "";
		String codAlumno = "";
		String codUsuario = "";
		int tipoUsuario = 0;
		
		try{
			String codCurso = getDisponibilidadModelSelect().getCodCurso()==""?"Invalido":getDisponibilidadModelSelect().getCodCurso();
			String codDocente = getDisponibilidadModelSelect().getCodDocente()==""?"Invalido":getDisponibilidadModelSelect().getCodDocente();
			
			if (PROCESO_BUSQUEDA == PROCESO_ALUMNO){
				codAlumno = getDisponibilidadModelSelect().getCodAlumno()==""?"Invalido":getDisponibilidadModelSelect().getCodAlumno();
			}
						
			if (validarCamposDisponibilidad("", codCurso, "", "", "", codDocente, codAlumno)){
				switch(PROCESO_BUSQUEDA){
					case 1: codUsuario = codDocente; tipoUsuario = 1; break;
					case 2: codUsuario = codAlumno; tipoUsuario = 2; break;
				}			
				List<DisponibilidadBO> listaDisponibilidades = tutoriaServices.listarDisponibilidades(codCurso, codUsuario, tipoUsuario);			
				setListaDisponibilidades(listaDisponibilidades);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		switch(MODO){
			case 1: switch(PROCESO_BUSQUEDA){
						case 1: pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadDocente.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadAlumno.xhtml"; break;
					} break;
			case 4: switch(PROCESO_BUSQUEDA){
						case 1: pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadDocente.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadAlumno.xhtml"; break;
					} break;
		}		
		return pagina;
	}
	
	private boolean validarCamposDisponibilidad(String codAreaConocimiento, String codCurso, String dia, String horaInicio, String horaFin, String codDocente, String codAlumno){		
		boolean valido = true;
		
		if (codAreaConocimiento.equals("Invalido")){
			mostrarMensaje(1);			
			valido = false;
		}	
		
		if (codCurso.equals("Invalido")){
			mostrarMensaje(2);			
			valido = false;
		}
		
		if (dia.equals("Invalido")){
			mostrarMensaje(3);			
			valido = false;
		}
		
		if (horaInicio.equals("Invalido")){
			mostrarMensaje(4);			
			valido = false;
		}
		
		if (horaFin.equals("Invalido")){
			mostrarMensaje(5);			
			valido = false;
		}
		
		if (MODO == MODO_ADMIN){
			if (PROCESO_REGISTRO == PROCESO_TUTOR){
				if (codDocente.equals("Invalido")){
					mostrarMensaje(10);	
					valido = false;
				}
			}
			else{
				if (PROCESO_REGISTRO == PROCESO_ALUMNO){
					if (codAlumno.equals("Invalido")){
						mostrarMensaje(11);	
						valido = false;
					}
				}
			}					
		}
		return valido;
	}
	
	private void mostrarMensaje(int opcionMensaje){
		FacesMessage message = null;
		
		switch(opcionMensaje){
			case 1: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar un área del conocimiento");
	        		FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 2: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar un curso");
	        		FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 3: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar un día");
    				FacesContext.getCurrentInstance().addMessage(null, message); break;			
			case 4: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar una hora de inicio");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 5: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar una hora de fin");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 6: message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro correcto - ", "Las disponibilidades se guardaron correctamente");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 7: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Registro fallido - ", "Ha ocurrido un error en el registro de las disponibilidades");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 8: message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Límite de disponibilidades", "Ha llegado al límite de registros de disponibilidad");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 9: message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Sin disponibilidades", "Debe ingresar al menos una disponibilidad");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 10: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar un tutor");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
			case 11: message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"", "Debe seleccionar un alumno");
					FacesContext.getCurrentInstance().addMessage(null, message); break;
		}
	}
	
	public DisponibilidadBO convertirADisponibilidadBO(DisponibilidadModel disponibilidad){
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
	
	
	public String guardarRegistroDisponibilidad(){
		String pagina = "";
		String usuario = "";
		List<DisponibilidadModel> listaDisponibilidades = getListaDisponibilidadGrid();
		
		try{
			if (listaDisponibilidades.size() == 0){
				mostrarMensaje(9);
			}
			else{
				for (DisponibilidadModel disponibilidad : listaDisponibilidades){
					if (MODO == MODO_ADMIN){
						if (PROCESO_REGISTRO == PROCESO_TUTOR){
							usuario = disponibilidad.getCodDocente();
						}
						else{
							if (PROCESO_REGISTRO == PROCESO_ALUMNO){
								usuario = disponibilidad.getCodAlumno();
							}
						}
					}
					else{
						if (MODO == MODO_TUTOR || MODO == MODO_ALUMNO){
							usuario = obtenerUsuario();
						}
					}					
					tutoriaServices.guardarRegistroDisponibilidad(convertirADisponibilidadBO(disponibilidad), usuario, MODO, PROCESO_REGISTRO);
					mostrarMensaje(6);
					limpiarClases();
					listarAreaConocimiento();
					listarHoraInicio();
				}
			}
		}
		catch(Exception Ex){
			mostrarMensaje(7);
			Ex.printStackTrace();
		}		
		switch(MODO){
			case 1: switch(PROCESO_REGISTRO){
						case 1: pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
						case 2: pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;
					} break;
		
			case 2: pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
			case 3: pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;	
		}
		return pagina;
	}
	
	public String obtenerUsuario() {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();        
        String nombre = "";
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User)auth.getPrincipal();
            nombre = user.getUsername();
        }
        return nombre;
    }
	
	public static <T> List<T> buscarLista(List<T> lista, Matcher m){
		List<T> resultado = new ArrayList<T>();
		for(T t : lista){
			if(m.matches()){
				resultado.add(t);
			}
		}
		return resultado;
	}
	
	public String selectorRegistroDisponibilidad(int modoUsuario, int tipoBusqueda) throws Exception{
		String pagina = "";
		inicializarClases();
		listarAreaConocimiento();
		listarHoraInicio();
		switch(modoUsuario){
			case 1: switch(tipoBusqueda){
						case 1: MODO = MODO_ADMIN;
								PROCESO_REGISTRO = PROCESO_TUTOR;								
								listarDocenteRegular();								
								pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
						case 2: MODO = MODO_ADMIN;		
								PROCESO_REGISTRO = PROCESO_ALUMNO;								
								listarAlumnoRegular();								
								pagina = "/paginas/ModuloRegulares/admin/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;
					} break;
			case 2: MODO = MODO_TUTOR;
					pagina = "/paginas/ModuloRegulares/tutor/registrar/registrarDisponibilidadHorariaDocentes.xhtml"; break;
			case 3: MODO = MODO_ALUMNO;
					pagina = "/paginas/ModuloRegulares/alumno/registrar/registrarDisponibilidadHorariaAlumnos.xhtml"; break;
		}		
		return pagina;		
	}
	
	public String selectorVisualizarDisponibilidad(int modoUsuario, int procesoBusqueda) throws Exception{
		String pagina = "";
		inicializarClases();		
		listarCursos();
		switch(modoUsuario){
			case 1: switch(procesoBusqueda){
						case 1: MODO = MODO_ADMIN;
								PROCESO_BUSQUEDA = PROCESO_TUTOR;
								pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadDocente.xhtml"; break;
						case 2: MODO = MODO_ADMIN;
								PROCESO_BUSQUEDA = PROCESO_ALUMNO;								
								pagina = "/paginas/ModuloRegulares/admin/visualizar/visualizarDisponibilidadAlumno.xhtml"; break;
					} break;
			case 2: switch(procesoBusqueda){
						case 1: MODO = MODO_OCAA;
								PROCESO_BUSQUEDA = PROCESO_TUTOR;
								pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadDocente.xhtml"; break;
						case 2: MODO = MODO_OCAA;
								PROCESO_BUSQUEDA = PROCESO_ALUMNO;								
								pagina = "/paginas/ModuloRegulares/ocaa/visualizar/visualizarDisponibilidadAlumno.xhtml"; break;
					} break;
		}	
		return pagina;		
	}
	
	public DisponibilidadModel getDisponibilidadModel() {
		return disponibilidadModel;
	}

	public void setDisponibilidadModel(DisponibilidadModel disponibilidadModel) {
		this.disponibilidadModel = disponibilidadModel;
	}

	public DisponibilidadModel getDisponibilidadModelSelect() {
		return disponibilidadModelSelect;
	}

	public void setDisponibilidadModelSelect(DisponibilidadModel disponibilidadModelSelect) {
		this.disponibilidadModelSelect = disponibilidadModelSelect;
	}

	public List<DisponibilidadModel> getListaDisponibilidad() {
		return listaDisponibilidad;
	}

	public void setListaDisponibilidad(List<DisponibilidadModel> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}

	public List<DisponibilidadModel> getListaDisponibilidadGrid() {
		return listaDisponibilidadGrid;
	}

	public void setListaDisponibilidadGrid(List<DisponibilidadModel> listaDisponibilidadGrid) {
		this.listaDisponibilidadGrid = listaDisponibilidadGrid;
	}

	public List<DisponibilidadBO> getListaDisponibilidades() {
		return listaDisponibilidades;
	}

	public void setListaDisponibilidades(List<DisponibilidadBO> listaDisponibilidades) {
		this.listaDisponibilidades = listaDisponibilidades;
	}

	public List<ClaseMaestra> getListarCursos() {
		return listarCursos;
	}

	public void setListarCursos(List<ClaseMaestra> listarCursos) {
		this.listarCursos = listarCursos;
	}
}
