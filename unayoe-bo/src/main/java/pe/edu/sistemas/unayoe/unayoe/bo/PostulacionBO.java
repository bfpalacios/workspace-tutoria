package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

public class PostulacionBO implements Serializable {

	private static final long serialVersionUID = 950698450622064116L;

	private String nombres;
	private String apellidos;
	private Integer edad;

	private Integer id;

	private String codigoAlumno;
	private Integer idConvocatoria;
	private byte[] archivoCV;

	private List<Integer> idTemas;
	private List<Integer> idTemasAprobados;

	private List<DisponibilidadTutoriaParBO> disponibilidad;

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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setArchivoCV(byte[] archivoCV) {
		this.archivoCV = archivoCV;
	}

	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void setIdConvocatoria(Integer idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

	public void setIdTemas(List<Integer> idTemas) {
		this.idTemas = idTemas;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public byte[] getArchivoCV() {
		return archivoCV;
	}

	public String getCodigoAlumno() {
		return codigoAlumno;
	}

	public Integer getEdad() {
		return edad;
	}

	public Integer getIdConvocatoria() {
		return idConvocatoria;
	}

	public List<Integer> getIdTemas() {
		return idTemas;
	}

	public String getNombres() {
		return nombres;
	}

	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public List<Integer> getIdTemasAprobados() {
		return idTemasAprobados;
	}

	public void setIdTemasAprobados(List<Integer> idTemasAprobados) {
		this.idTemasAprobados = idTemasAprobados;
	}

}