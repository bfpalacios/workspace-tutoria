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

/**
 * Created by Alex on 31/10/2015
 */

@Component
@RequestScoped
public class ActividadAcademicaModel {

    private int codigoTema; //Un tema del banco de temas
    private String codigoTutor; //Código del tutor que crea la actividad Académica
    private String nombre; //Nombre de la Actividad Académica
    private String descripcion; //Descripción de la Actvidad
    private int numeroVacantes; //Número de vacantes para la Actividad
	private int vacantesRestantes;

    private int numeroSesiones; //Numero de sesiones de la actividad
    private int tipoSesion; // Presencial o Virtual
    private int modalidadTutoria; // Expositiva, taller, seminario

    private List<AreaConocimientoBO> areas;
    private List<CursoBO> cursos;
    private List<TemaBO> temas;


    private List<SesionParBO> sesiones; //Lista de sesiones
    private List<HorarioBO> horarios; //Lista de horarios

	private String nombreTutor;
	private String nombreCurso;
	private String nombreArea;
	private int codigo;

    public ActividadAcademicaModel(){
    	this.numeroVacantes = 1;
    	this.areas = new ArrayList<>();
    	this.cursos = new ArrayList<>();
    	this.temas = new ArrayList<>();

		this.sesiones = new ArrayList<>();
		this.horarios = new ArrayList<>();

    }

    public int getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(int tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

	public int getVacantesRestantes() {
		return vacantesRestantes;
	}

	public void setVacantesRestantes(int vacantesRestantes) {
		this.vacantesRestantes = vacantesRestantes;
	}

	public int getNumeroVacantes() {
        return numeroVacantes;
    }

    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    public int getNumeroSesiones() {
        return numeroSesiones;
    }

    public void setNumeroSesiones(int numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }

	public int getModalidadTutoria() {
		return modalidadTutoria;
	}

	public void setModalidadTutoria(int modalidadTutoria) {
		this.modalidadTutoria = modalidadTutoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoTema() {
		return codigoTema;
	}

	public void setCodigoTema(int codigoTema) {
		this.codigoTema = codigoTema;
	}

	public String getCodigoTutor() {
		return codigoTutor;
	}

	public void setCodigoTutor(String codigoTutor) {
		this.codigoTutor = codigoTutor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<AreaConocimientoBO> getAreas() {
		return areas;
	}

	public void setAreas(List<AreaConocimientoBO> areas) {
		this.areas = areas;
	}

	public List<CursoBO> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoBO> cursos) {
		this.cursos = cursos;
	}

	public List<TemaBO> getTemas() {
		return temas;
	}

	public void setTemas(List<TemaBO> temas) {
		this.temas = temas;
	}

	public List<SesionParBO> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<SesionParBO> sesiones) {
		this.sesiones = sesiones;
	}

	public List<HorarioBO> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioBO> horarios) {
		this.horarios = horarios;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public boolean isVacantesAgotadas(){
		return  (vacantesRestantes <= 0);
	}

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
