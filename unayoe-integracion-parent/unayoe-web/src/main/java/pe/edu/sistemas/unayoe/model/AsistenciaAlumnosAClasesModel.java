package pe.edu.sistemas.unayoe.model;
 
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class AsistenciaAlumnosAClasesModel.
 */
@Component("asistenciaAlumnosAClasesModel")
@RequestScoped
public class AsistenciaAlumnosAClasesModel {
	
	/** The fecha. */
	//metodos de instancia 
	private String fecha;
	
	/** The dia. */
	private String dia;
	
	/** The a codigo. */
	private String a_codigo;
	
	/** The c nombre. */
	private String c_nombre;
	
	/** The c codigo. */
	private String c_codigo;
	
	/** The a nombre. */
	private String a_nombre;
	
	/** The a apellido. */
	private String a_apellido;
	
	/** The repitencias. */
	private String repitencias; 
	
	/** The asistencia. */
	private String asistencia;
	
	/** The observacion. */
	private String observacion;
	
	/** The listar cursos. */
	private List<CursoBO> listarCursos;
	
	
	
	/**
	 * Instantiates a new asistencia alumnos A clases model.
	 */
	public AsistenciaAlumnosAClasesModel() {
		super();
	}
	
	/**
	 * Instantiates a new asistencia alumnos A clases model.
	 *
	 * @param fecha the fecha
	 * @param dia the dia
	 * @param a_codigo the a codigo
	 * @param c_nombre the c nombre
	 * @param c_codigo the c codigo
	 * @param a_nombre the a nombre
	 * @param a_apellido the a apellido
	 * @param repitencias the repitencias
	 * @param asistencia the asistencia
	 * @param observacion the observacion
	 */
	public AsistenciaAlumnosAClasesModel(String fecha, String dia,
			String a_codigo, String c_nombre, String c_codigo, String a_nombre,
			String a_apellido, String repitencias, String asistencia,
			String observacion) {
		super();
		this.fecha = fecha;
		this.dia = dia;
		this.a_codigo = a_codigo;
		this.c_nombre = c_nombre;
		this.c_codigo = c_codigo;
		this.a_nombre = a_nombre;
		this.a_apellido = a_apellido;
		this.repitencias = repitencias;
		this.asistencia = asistencia;
		this.observacion = observacion;
	}
	
	/**
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getA_codigo() {
		return a_codigo;
	}
	
	/**
	 * Sets the a codigo.
	 *
	 * @param a_codigo the new a codigo
	 */
	public void setA_codigo(String a_codigo) {
		this.a_codigo = a_codigo;
	}
	
	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getC_codigo() {
		return c_codigo;
	}
	
	/**
	 * Sets the c codigo.
	 *
	 * @param c_codigo the new c codigo
	 */
	public void setC_codigo(String c_codigo) {
		this.c_codigo = c_codigo;
	}
	
	/**
	 * Gets the a nombre.
	 *
	 * @return the a nombre
	 */
	public String getA_nombre() {
		return a_nombre;
	}
	
	/**
	 * Sets the a nombre.
	 *
	 * @param a_nombre the new a nombre
	 */
	public void setA_nombre(String a_nombre) {
		this.a_nombre = a_nombre;
	}
	
	/**
	 * Gets the a apellido.
	 *
	 * @return the a apellido
	 */
	public String getA_apellido() {
		return a_apellido;
	}
	
	/**
	 * Sets the a apellido.
	 *
	 * @param a_apellido the new a apellido
	 */
	public void setA_apellido(String a_apellido) {
		this.a_apellido = a_apellido;
	}
	
	/**
	 * Gets the repitencias.
	 *
	 * @return the repitencias
	 */
	public String getRepitencias() {
		return repitencias;
	}
	
	/**
	 * Sets the repitencias.
	 *
	 * @param repitencias the new repitencias
	 */
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	
	/**
	 * Gets the asistencia.
	 *
	 * @return the asistencia
	 */
	public String getAsistencia() {
		return asistencia;
	}
	
	/**
	 * Sets the asistencia.
	 *
	 * @param asistencia the new asistencia
	 */
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	
	/**
	 * Gets the listar cursos.
	 *
	 * @return the listar cursos
	 */
	public List<CursoBO> getListarCursos() {
		return listarCursos;
	}
	
	/**
	 * Sets the listar cursos.
	 *
	 * @param listarCursos the new listar cursos
	 */
	public void setListarCursos(List<CursoBO> listarCursos) {
		this.listarCursos = listarCursos;
	}
	
	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}
	
	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	/**
	 * Gets the c nombre.
	 *
	 * @return the c nombre
	 */
	public String getC_nombre() {
		return c_nombre;
	}
	
	/**
	 * Sets the c nombre.
	 *
	 * @param c_nombre the new c nombre
	 */
	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}
	
	/**
	 * Gets the observacion.
	 *
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * Sets the observacion.
	 *
	 * @param observacion the new observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
