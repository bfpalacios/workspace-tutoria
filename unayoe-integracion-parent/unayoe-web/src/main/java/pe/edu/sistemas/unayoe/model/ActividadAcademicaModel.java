package pe.edu.sistemas.unayoe.model;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.HorarioBO;

import pe.edu.sistemas.unayoe.unayoe.bo.SesionParBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;





import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 31/10/2015.
 */

@Component
@RequestScoped
public class ActividadAcademicaModel {

    /** The codigo tema. */
    private int codigoTema; //Un tema del banco de temas
    
    /** The codigo tutor. */
    private String codigoTutor; //Código del tutor que crea la actividad Académica
    
    /** The nombre. */
    private String nombre; //Nombre de la Actividad Académica
    
    /** The descripcion. */
    private String descripcion; //Descripción de la Actvidad
    
    /** The numero vacantes. */
    private int numeroVacantes; //Número de vacantes para la Actividad
	
	/** The vacantes restantes. */
	private int vacantesRestantes;

    /** The numero sesiones. */
    private int numeroSesiones; //Numero de sesiones de la actividad
    
    /** The tipo sesion. */
    private int tipoSesion; // Presencial o Virtual
    
    /** The modalidad tutoria. */
    private int modalidadTutoria; // Expositiva, taller, seminario

    /** The areas. */
    private List<AreaConocimientoBO> areas;
    
    /** The cursos. */
    private List<CursoBO> cursos;
    
    /** The temas. */
    private List<TemaBO> temas;


    /** The sesiones. */
    private List<SesionParBO> sesiones; //Lista de sesiones
    
    /** The horarios. */
    private List<HorarioBO> horarios; //Lista de horarios

	/** The nombre tutor. */
	private String nombreTutor;
	
	/** The nombre curso. */
	private String nombreCurso;
	
	/** The nombre area. */
	private String nombreArea;
	
	/** The codigo. */
	private int codigo;

    /**
     * Instantiates a new actividad academica model.
     */
    public ActividadAcademicaModel(){
    	this.numeroVacantes = 1;
    	this.areas = new ArrayList<>();
    	this.cursos = new ArrayList<>();
    	this.temas = new ArrayList<>();

		this.sesiones = new ArrayList<>();
		this.horarios = new ArrayList<>();

    }

    /**
     * Gets the tipo sesion.
     *
     * @return the tipo sesion
     */
    public int getTipoSesion() {
        return tipoSesion;
    }

    /**
     * Sets the tipo sesion.
     *
     * @param tipoSesion the new tipo sesion
     */
    public void setTipoSesion(int tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

	/**
	 * Gets the vacantes restantes.
	 *
	 * @return the vacantes restantes
	 */
	public int getVacantesRestantes() {
		return vacantesRestantes;
	}

	/**
	 * Sets the vacantes restantes.
	 *
	 * @param vacantesRestantes the new vacantes restantes
	 */
	public void setVacantesRestantes(int vacantesRestantes) {
		this.vacantesRestantes = vacantesRestantes;
	}

	/**
	 * Gets the numero vacantes.
	 *
	 * @return the numero vacantes
	 */
	public int getNumeroVacantes() {
        return numeroVacantes;
    }

    /**
     * Sets the numero vacantes.
     *
     * @param numeroVacantes the new numero vacantes
     */
    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    /**
     * Gets the numero sesiones.
     *
     * @return the numero sesiones
     */
    public int getNumeroSesiones() {
        return numeroSesiones;
    }

    /**
     * Sets the numero sesiones.
     *
     * @param numeroSesiones the new numero sesiones
     */
    public void setNumeroSesiones(int numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }

	/**
	 * Gets the modalidad tutoria.
	 *
	 * @return the modalidad tutoria
	 */
	public int getModalidadTutoria() {
		return modalidadTutoria;
	}

	/**
	 * Sets the modalidad tutoria.
	 *
	 * @param modalidadTutoria the new modalidad tutoria
	 */
	public void setModalidadTutoria(int modalidadTutoria) {
		this.modalidadTutoria = modalidadTutoria;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo tema.
	 *
	 * @return the codigo tema
	 */
	public int getCodigoTema() {
		return codigoTema;
	}

	/**
	 * Sets the codigo tema.
	 *
	 * @param codigoTema the new codigo tema
	 */
	public void setCodigoTema(int codigoTema) {
		this.codigoTema = codigoTema;
	}

	/**
	 * Gets the codigo tutor.
	 *
	 * @return the codigo tutor
	 */
	public String getCodigoTutor() {
		return codigoTutor;
	}

	/**
	 * Sets the codigo tutor.
	 *
	 * @param codigoTutor the new codigo tutor
	 */
	public void setCodigoTutor(String codigoTutor) {
		this.codigoTutor = codigoTutor;
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
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Gets the areas.
	 *
	 * @return the areas
	 */
	public List<AreaConocimientoBO> getAreas() {
		return areas;
	}

	/**
	 * Sets the areas.
	 *
	 * @param areas the new areas
	 */
	public void setAreas(List<AreaConocimientoBO> areas) {
		this.areas = areas;
	}

	/**
	 * Gets the cursos.
	 *
	 * @return the cursos
	 */
	public List<CursoBO> getCursos() {
		return cursos;
	}

	/**
	 * Sets the cursos.
	 *
	 * @param cursos the new cursos
	 */
	public void setCursos(List<CursoBO> cursos) {
		this.cursos = cursos;
	}

	/**
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	public List<TemaBO> getTemas() {
		return temas;
	}

	/**
	 * Sets the temas.
	 *
	 * @param temas the new temas
	 */
	public void setTemas(List<TemaBO> temas) {
		this.temas = temas;
	}

	/**
	 * Gets the sesiones.
	 *
	 * @return the sesiones
	 */
	public List<SesionParBO> getSesiones() {
		return sesiones;
	}

	/**
	 * Sets the sesiones.
	 *
	 * @param sesiones the new sesiones
	 */
	public void setSesiones(List<SesionParBO> sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * Gets the horarios.
	 *
	 * @return the horarios
	 */
	public List<HorarioBO> getHorarios() {
		return horarios;
	}

	/**
	 * Sets the horarios.
	 *
	 * @param horarios the new horarios
	 */
	public void setHorarios(List<HorarioBO> horarios) {
		this.horarios = horarios;
	}

	/**
	 * Gets the nombre tutor.
	 *
	 * @return the nombre tutor
	 */
	public String getNombreTutor() {
		return nombreTutor;
	}

	/**
	 * Sets the nombre tutor.
	 *
	 * @param nombreTutor the new nombre tutor
	 */
	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	/**
	 * Gets the nombre area.
	 *
	 * @return the nombre area
	 */
	public String getNombreArea() {
		return nombreArea;
	}

	/**
	 * Sets the nombre area.
	 *
	 * @param nombreArea the new nombre area
	 */
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	/**
	 * Gets the nombre curso.
	 *
	 * @return the nombre curso
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}

	/**
	 * Sets the nombre curso.
	 *
	 * @param nombreCurso the new nombre curso
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	/**
	 * Checks if is vacantes agotadas.
	 *
	 * @return true, if is vacantes agotadas
	 */
	public boolean isVacantesAgotadas(){
		return  (vacantesRestantes <= 0);
	}

	/**
	 * Reset.
	 */
	public void reset(){
		codigoTema = 0;
		nombreArea = nombreTutor = nombreCurso = null;
		nombre = null;
		numeroVacantes = 1;
		vacantesRestantes = 0;
		sesiones = new ArrayList<SesionParBO>();
		horarios = new ArrayList<HorarioBO>();
	}
}
