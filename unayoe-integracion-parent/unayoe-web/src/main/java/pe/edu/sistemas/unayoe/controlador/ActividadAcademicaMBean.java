package pe.edu.sistemas.unayoe.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import org.springframework.util.*;
import pe.edu.sistemas.unayoe.core.util.Filter;
import pe.edu.sistemas.unayoe.model.ActividadAcademicaModel;
import pe.edu.sistemas.unayoe.model.HorarioModel;
import pe.edu.sistemas.unayoe.model.SesionModel;
import pe.edu.sistemas.unayoe.services.*;
import pe.edu.sistemas.unayoe.unayoe.bo.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 31/10/2015.
 */
@Controller
public class ActividadAcademicaMBean {

    /** The actividad academica model. */
    @Autowired
    private ActividadAcademicaModel actividadAcademicaModel;

    /** The horario model. */
    @Autowired
    private HorarioModel horarioModel;

    /** The sesion model. */
    @Autowired
    private SesionModel sesionModel;

    /** The area conocimiento services. */
    @Autowired
    private AreaConocimientoServices areaConocimientoServices;

    /** The curso services. */
    @Autowired
    private CursoServices cursoServices;

    /** The tema services. */
    @Autowired
    private TemaServices temaServices;

    /** The comun services. */
    @Autowired
    private ComunServices comunServices;

    /** The usuario services. */
    @Autowired
    private UsuarioServices usuarioServices;

    /** The actividad academica services. */
    @Autowired
    private ActividadAcademicaServices actividadAcademicaServices;

    /** The matricula par services. */
    @Autowired
    MatriculaParServices matriculaParServices;

    /** The filter. */
    @Autowired
    private Filter filter;

    /** The codigo area. */
    private String codigoArea;
    
    /** The codigo curso. */
    private String codigoCurso;

    /** The codigo sistema. */
    private String codigoSistema; //Código en el sistema del usuario

    /** The filtro todos. */
    private static int FILTRO_TODOS = 1; // Mostrar todas las actividades académicas
    
    /** The Constant MODO_ALUMNO. */
    private static final int MODO_ALUMNO = 1;

    /** The actividad academica models. */
    private List<ActividadAcademicaModel> actividadAcademicaModels;

    /**
     * Inits the.
     */
    @PostConstruct
    public void init(){
        actividadAcademicaModels = new ArrayList<>();
        System.out.println("PostConstruct ...");
    }
    

    /**
     * Guardar actividad.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String guardarActividad() throws Exception{
        if (validarActividad()){
            ActividadAcademicaBO academicaBO = new ActividadAcademicaBO();
            academicaBO.setCodigoTema(actividadAcademicaModel.getCodigoTema());
            academicaBO.setCodigoTutor(obtenerCodigoSistema());
            academicaBO.setNombre(actividadAcademicaModel.getNombre());
            academicaBO.setNumeroVacantes(actividadAcademicaModel.getNumeroVacantes());

            if (actividadAcademicaServices.guardarActividadAcademica(academicaBO,
                    actividadAcademicaModel.getHorarios(), actividadAcademicaModel.getSesiones())){
                infoMessage("Actividad guardada","Actvidad académica guadado correctamente");
                actividadAcademicaModel.reset();
            }
            else errorMessage("Algo salió mal","La actividad no se guardó correctamente");

        }
        return null;
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
            User user = (User)auth.getPrincipal();
            nombre = user.getUsername();
        }
        return nombre;
    }

    /**
     * Obtener codigo sistema.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String obtenerCodigoSistema() throws Exception {
        String username = obtenerUsuario();
        codigoSistema = usuarioServices.buscarUsuarioEquivalencia(username);
        return  codigoSistema;
    }

    /**
     * Validar actividad.
     *
     * @return true, if successful
     */
    public boolean validarActividad(){
        boolean isOk = true;
        if (actividadAcademicaModel.getCodigoTema() < 1){
            errorMessage("El tema es necesario","Elija un tema");
            isOk = false;
        }
        if ( StringUtils.isEmpty(StringUtils.trimWhitespace(actividadAcademicaModel.getNombre()) )){
            errorMessage("El nombre es necesario","Escriba un nombre para la Actividad");
            isOk = false;
        }
        if(actividadAcademicaModel.getHorarios().size() < 1){
        	errorMessage("Horario Vacio","Añada al menos un horario a la Actividad");
            isOk = false;
        }
        if(actividadAcademicaModel.getSesiones().size() < 1){
        	errorMessage("Actividad sin sesiones","Añada al menos una sesión a la Actividad");
            isOk = false;
        }

        for (SesionParBO sesionParBO : actividadAcademicaModel.getSesiones()){
            if (!fechaEnHorario(sesionParBO.getFecha())){
                errorMessage("Fecha de sesión errada","Todas las sesiones deben coincidir con el horario");
                return false;
            }
        }

        return isOk;
    }

    /**
     * Gets the codigo area.
     *
     * @return the codigo area
     */
    public String getCodigoArea(){
        return codigoArea;
    }

    /**
     * Sets the codigo area.
     *
     * @param codigoArea the new codigo area
     */
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    /**
     * Gets the codigo curso.
     *
     * @return the codigo curso
     */
    public String getCodigoCurso() {
        return codigoCurso;
    }

    /**
     * Sets the codigo curso.
     *
     * @param codigoCurso the new codigo curso
     */
    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    /**
     * On change area.
     *
     * @param e the e
     */
    public void onChangeArea(ValueChangeEvent e){
        try {
            String codigoArea = (String)e.getNewValue(); //Código de área
            List<CursoBO> listaCursos = cursoServices.listarCursosAprobadosPorAreaConocimiento(codigoSistema,codigoArea);
            actividadAcademicaModel.setCursos(listaCursos);
            this.codigoCurso = null;
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * On change curso.
     *
     * @param e the e
     */
    public void onChangeCurso(ValueChangeEvent e){
        try {
            String codigoCurso = (String)e.getNewValue(); //Código de curso
            List<TemaBO> listaTemas= temaServices.listarTemasAprobadosPorCurso(codigoSistema, codigoCurso);
            actividadAcademicaModel.setTemas(listaTemas);
            actividadAcademicaModel.setCodigoTema(0);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * On change tema.
     *
     * @param e the e
     */
    public void onChangeTema(ValueChangeEvent e){
        
        int codigoTema = e.getNewValue()!=null?(int)e.getNewValue():0 ;
        if (codigoTema > 0) {
            TemaBO temaBO = temaServices.getTema(codigoTema);
            actividadAcademicaModel.setNombre(temaBO.getNombre()); //Poner por nombre como el tema escogido
        } else actividadAcademicaModel.setNombre(null); //Poner por nombre como el tema escogido

    }

    /**
     * On change hora inicio.
     *
     * @param e the e
     */
    public void onChangeHoraInicio(ValueChangeEvent e){
        String codigoHora = (String)e.getNewValue();
        try {
        	if (!StringUtils.isEmpty(codigoHora))
            horarioModel.setHorasFin(comunServices.actualizarHoraFin(Integer.parseInt(codigoHora))); //Poner por nombre como el tema escogido
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    /**
     * Actualizar orden sesiones.
     */
    public void actualizarOrdenSesiones(){
        int tamano = actividadAcademicaModel.getSesiones().size();
        for (int index=0; index<tamano; index++){
            actividadAcademicaModel.getSesiones().get(index).setNumero(index+1);
        }
    }

    /**
     * On row reorder sesion.
     */
    public void onRowReorderSesion(){
        actualizarOrdenSesiones();
    }

    /**
     * Adds the horario.
     */
    public void addHorario() {
        HorarioBO horarioBO = new HorarioBO();
        horarioBO.setDia(horarioModel.getDia());
        horarioBO.setHoraInicio(horarioModel.getHoraInicio());
        horarioBO.setHoraFin(horarioModel.getHoraFin());

        if (validarHorario(horarioBO)){
            actividadAcademicaModel.getHorarios().add(horarioBO);
            horarioModel.reset();
        }

    }

    /**
     * Adds the sesion.
     */
    public void addSesion(){
        SesionParBO sesionParBO = new SesionParBO();
        sesionParBO.setNumero(actividadAcademicaModel.getSesiones().size()+1);
        sesionParBO.setTipo(sesionModel.getTipo());
        sesionParBO.setDescripcion(sesionModel.getDescripcion());
        
        if(sesionModel.getFecha() != null)
        	sesionParBO.setFecha(sesionModel.getFecha());
        else
        	sesionParBO.setFecha(null);

        if (validarSesion(sesionParBO)){
            actividadAcademicaModel.getSesiones().add(sesionParBO);
            actualizarOrdenSesiones();
            sesionModel.reset();
        }
    }

    /**
     * Eliminar horario.
     *
     * @param horarioBO the horario BO
     */
    public void eliminarHorario(HorarioBO horarioBO){
        actividadAcademicaModel.getHorarios().remove(horarioBO);
    }

    /**
     * Eliminar sesion.
     *
     * @param sesionParBO the sesion par BO
     */
    public void eliminarSesion(SesionParBO sesionParBO){
        actividadAcademicaModel.getSesiones().remove(sesionParBO);
        actualizarOrdenSesiones();
    }

    /**
     * Validar sesion.
     *
     * @param sesionParBO the sesion par BO
     * @return true, if successful
     */
    public boolean validarSesion(SesionParBO sesionParBO){
        boolean isOk = true;
        if (sesionParBO.getFecha() == null){
            errorMessage("Fecha inválida", "Elija una fecha válida"); isOk = false;
        }else if ( !fechaEnHorario(sesionParBO.getFecha())){
            errorMessage("Fecha fuera de horario","La fecha no coincide con el horario"); isOk=false;
        }
        return isOk;
    }

    /**
     * Validar horario.
     *
     * @param horarioBO the horario BO
     * @return true, if successful
     */
    public boolean validarHorario(HorarioBO horarioBO){
        boolean isOk = true;
        if (StringUtils.isEmpty(horarioBO.getDia())){
            errorMessage("Día vacio", "Elija un día de la semana"); isOk = false;
        }
        if (StringUtils.isEmpty(horarioBO.getHoraInicio()) || StringUtils.isEmpty(horarioBO.getHoraFin())){
            errorMessage("Hora incorrecta", "Especifique la hora de Inicio y Fin"); isOk = false;
        }
        return isOk;
    }

    /**
     * Fecha en horario.
     *
     * @param fecha the fecha
     * @return true, if successful
     */
    private  boolean fechaEnHorario(Date fecha){
        for (HorarioBO horarioBO : actividadAcademicaModel.getHorarios()){
            if (horarioBO.getDia().equalsIgnoreCase(filter.dayOfWeekText(fecha)))
                return true;
        }
        return false;
    }

    /**
     * Navigation.
     *
     * @return the string
     * @throws Exception the exception
     */
    public String navigation() throws Exception {
        actividadAcademicaModel.reset();

        codigoSistema = obtenerCodigoSistema(); //Del usuario conectado actualmente
        actividadAcademicaModel.setAreas(areaConocimientoServices.listarAreasAprobadasTutor(codigoSistema));
        horarioModel.setHorasInicio(comunServices.listarTablaMaestra("HORA","HORA_INICIO")); //Las horas de inicio
        //Las horas de fin

        return "/paginas/ModuloPares/tutor/registrarActividadAcademica.xhtml";
    }

    /**
     * Selector mostrar actividades.
     *
     * @param modo the modo
     * @param filtro the filtro
     * @return the string
     * @throws Exception the exception
     */
    public String selectorMostrarActividades(int modo,int filtro) throws Exception{
        codigoSistema = obtenerCodigoSistema(); //Del usuario conectado actualmente

        List<ActividadAcademicaBO> activities;
        if (filtro == FILTRO_TODOS)
            activities = actividadAcademicaServices.listarActividades();
        else if (modo == MODO_ALUMNO) {
                activities = actividadAcademicaServices.listarActividadesPorAlumno(codigoSistema);
        }else {
                activities = actividadAcademicaServices.listarActividadesPorTutor(codigoSistema);
        }

        actividadAcademicaModels = new ArrayList<>();
        for (ActividadAcademicaBO activity: activities){
            ActividadAcademicaModel model = new ActividadAcademicaModel();

            model.setCodigoTema(activity.getCodigoTema());
            model.setHorarios(actividadAcademicaServices.horariosPorActividad(activity.getCodigo()));
            //model.setSesiones(actividadAcademicaServices.sesionesPorActividad(activity.getCodigo()));
            model.setNombre(activity.getNombre());
            model.setNumeroVacantes(activity.getNumeroVacantes());
            model.setVacantesRestantes(activity.getVacantesRestantes());
            model.setCodigo(activity.getCodigo());

            model.setNombreTutor(usuarioServices.obtenerTutorActividad(activity.getCodigo()).getNombreCompleto());
            model.setNombreCurso(cursoServices.obtnerCursoTema(activity.getCodigoTema()).getNombre());
            model.setNombreArea(areaConocimientoServices.obtnerAreaTema(activity.getCodigoTema()).getNomAreaConocimiento());

            actividadAcademicaModels.add(model);
        }
        System.out.println("FILTRO: ===  "+filtro);
        actividadAcademicaModel.setAreas(areaConocimientoServices.listarAreasAprobadasTutor(codigoSistema));
        if (modo == MODO_ALUMNO)
            return "/paginas/ModuloPares/alumno/mostrarActividadAcademica.xhtml";
        else
            return "/paginas/ModuloPares/tutor/mostrarActividadAcademica.xhtml";
    }


    /**
     * Selector detalle.
     *
     * @param modo the modo
     * @param codigoActividad the codigo actividad
     * @return the string
     */
    public String selectorDetalle(int modo,int codigoActividad){
        actividadAcademicaModel.reset();
        sesionModel.reset();
        horarioModel.reset();

        ActividadAcademicaBO activity = actividadAcademicaServices.obtenerActividad(codigoActividad);

        actividadAcademicaModel.setCodigoTema(activity.getCodigoTema());
        actividadAcademicaModel.setHorarios(actividadAcademicaServices.horariosPorActividad(activity.getCodigo()));
        actividadAcademicaModel.setSesiones(actividadAcademicaServices.sesionesPorActividad(activity.getCodigo()));
        actividadAcademicaModel.setNombre(activity.getNombre());
        actividadAcademicaModel.setNumeroVacantes(activity.getNumeroVacantes());
        actividadAcademicaModel.setVacantesRestantes(activity.getVacantesRestantes());
        actividadAcademicaModel.setCodigo(activity.getCodigo());

        actividadAcademicaModel.setNombreTutor(usuarioServices.obtenerTutorActividad(activity.getCodigo()).getNombreCompleto());
        actividadAcademicaModel.setNombreCurso(cursoServices.obtnerCursoTema(activity.getCodigoTema()).getNombre());
        actividadAcademicaModel.setNombreArea(areaConocimientoServices.obtnerAreaTema(activity.getCodigoTema()).getNomAreaConocimiento());

        System.out.println(codigoActividad);
        if (modo == MODO_ALUMNO)
            return "/paginas/ModuloPares/alumno/detalleActividadAcademica.xhtml";
        else
            return "/paginas/ModuloPares/tutor/detalleActividadAcademica.xhtml";
    }

    /**
     * Matricular alumno.
     *
     * @param codigoProgramacion the codigo programacion
     * @throws Exception the exception
     */
    public void matricularAlumno(int codigoProgramacion) throws Exception {
        MatriculaParBO matriculaParBO = new MatriculaParBO();
        matriculaParBO.setCodigoAlumno(obtenerCodigoSistema());
        matriculaParBO.setCodigoProgramacion(codigoProgramacion);

        if (matriculaParServices.matricularAlumno(matriculaParBO) != null){
            infoMessage("Matricula correcta", "Estas matriculado en el curso");
        }else{
            errorMessage("Error al matricularte", "La matricula no se puedo realizar :(");
        }

    }

    /**
     * Checks if is matriculado.
     *
     * @param codigoActividad the codigo actividad
     * @return true, if is matriculado
     * @throws Exception the exception
     */
    public boolean isMatriculado(int codigoActividad) throws Exception {
        return matriculaParServices.buscarMatriculaPar(obtenerCodigoSistema(),codigoActividad) != null;
    }


    /**
     * Info message.
     *
     * @param title the title
     * @param detail the detail
     */
    public void infoMessage(String title, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail));
    }

    /**
     * Error message.
     *
     * @param title the title
     * @param detail the detail
     */
    public void errorMessage(String title, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail));
    }

    /**
     * Gets the actividad academica models.
     *
     * @return the actividad academica models
     */
    public List<ActividadAcademicaModel> getActividadAcademicaModels() {
        return actividadAcademicaModels;
    }

    /**
     * Sets the actividad academica models.
     *
     * @param actividadAcademicaModels the new actividad academica models
     */
    public void setActividadAcademicaModels(List<ActividadAcademicaModel> actividadAcademicaModels) {
        this.actividadAcademicaModels = actividadAcademicaModels;
    }
}
