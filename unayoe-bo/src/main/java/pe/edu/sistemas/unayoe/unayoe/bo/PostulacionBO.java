package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PostulacionBO.
 */
public class PostulacionBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 950698450622064116L;

	/** The nombres. */
	private String nombres;
	
	/** The apellidos. */
	private String apellidos;
	
	/** The edad. */
	private Integer edad;

	/** The id. */
	private Integer id;

	/** The codigo alumno. */
	private String codigoAlumno;
	
	/** The id convocatoria. */
	private Integer idConvocatoria;
	
	/** The archivo CV. */
	private byte[] archivoCV;

	/** The id temas. */
	private List<Integer> idTemas;
	
	/** The id temas aprobados. */
	private List<Integer> idTemasAprobados;

	/** The disponibilidad. */
	private List<DisponibilidadTutoriaParBO> disponibilidad;

	/**
	 * Instantiates a new postulacion BO.
	 */
	public PostulacionBO() {
		this.id = null;
		this.nombres = null;
		this.apellidos = null;
		this.edad = null;
		this.codigoAlumno = null;
		this.idConvocatoria = null;
		this.idTemas = null;
		this.archivoCV = null;
		this.disponibilidad = null;
		this.idTemasAprobados = null;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the apellidos.
	 *
	 * @param apellidos the new apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Sets the archivo CV.
	 *
	 * @param archivoCV the new archivo CV
	 */
	public void setArchivoCV(byte[] archivoCV) {
		this.archivoCV = archivoCV;
	}

	/**
	 * Sets the codigo alumno.
	 *
	 * @param codigoAlumno the new codigo alumno
	 */
	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}

	/**
	 * Sets the edad.
	 *
	 * @param edad the new edad
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * Sets the id convocatoria.
	 *
	 * @param idConvocatoria the new id convocatoria
	 */
	public void setIdConvocatoria(Integer idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	/**
	 * Sets the id temas.
	 *
	 * @param idTemas the new id temas
	 */
	public void setIdTemas(List<Integer> idTemas) {
		this.idTemas = idTemas;
	}

	/**
	 * Sets the nombres.
	 *
	 * @param nombres the new nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Gets the apellidos.
	 *
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Gets the archivo CV.
	 *
	 * @return the archivo CV
	 */
	public byte[] getArchivoCV() {
		return archivoCV;
	}

	/**
	 * Gets the codigo alumno.
	 *
	 * @return the codigo alumno
	 */
	public String getCodigoAlumno() {
		return codigoAlumno;
	}

	/**
	 * Gets the edad.
	 *
	 * @return the edad
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * Gets the id convocatoria.
	 *
	 * @return the id convocatoria
	 */
	public Integer getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Gets the id temas.
	 *
	 * @return the id temas
	 */
	public List<Integer> getIdTemas() {
		return idTemas;
	}

	/**
	 * Gets the nombres.
	 *
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Gets the disponibilidad.
	 *
	 * @return the disponibilidad
	 */
	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * Sets the disponibilidad.
	 *
	 * @param disponibilidad the new disponibilidad
	 */
	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	/**
	 * Gets the id temas aprobados.
	 *
	 * @return the id temas aprobados
	 */
	public List<Integer> getIdTemasAprobados() {
		return idTemasAprobados;
	}

	/**
	 * Sets the id temas aprobados.
	 *
	 * @param idTemasAprobados the new id temas aprobados
	 */
	public void setIdTemasAprobados(List<Integer> idTemasAprobados) {
		this.idTemasAprobados = idTemasAprobados;
	}

}