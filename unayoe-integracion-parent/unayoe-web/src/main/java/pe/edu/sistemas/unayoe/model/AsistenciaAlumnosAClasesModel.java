package pe.edu.sistemas.unayoe.model;
 
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

@Component("asistenciaAlumnosAClasesModel")
@RequestScoped
public class AsistenciaAlumnosAClasesModel {
	
	//metodos de instancia 
	private String fecha;
	private String dia;
	private String a_codigo;
	private String c_nombre;
	private String c_codigo;
	private String a_nombre;
	private String a_apellido;
	private String repitencias; 
	private String asistencia;
	private String observacion;
	private List<CursoBO> listarCursos;
	
	
	
	public AsistenciaAlumnosAClasesModel() {
		super();
	}
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
	public String getA_codigo() {
		return a_codigo;
	}
	public void setA_codigo(String a_codigo) {
		this.a_codigo = a_codigo;
	}
	public String getC_codigo() {
		return c_codigo;
	}
	public void setC_codigo(String c_codigo) {
		this.c_codigo = c_codigo;
	}
	public String getA_nombre() {
		return a_nombre;
	}
	public void setA_nombre(String a_nombre) {
		this.a_nombre = a_nombre;
	}
	public String getA_apellido() {
		return a_apellido;
	}
	public void setA_apellido(String a_apellido) {
		this.a_apellido = a_apellido;
	}
	public String getRepitencias() {
		return repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	public String getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public List<CursoBO> getListarCursos() {
		return listarCursos;
	}
	public void setListarCursos(List<CursoBO> listarCursos) {
		this.listarCursos = listarCursos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getC_nombre() {
		return c_nombre;
	}
	public void setC_nombre(String c_nombre) {
		this.c_nombre = c_nombre;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
