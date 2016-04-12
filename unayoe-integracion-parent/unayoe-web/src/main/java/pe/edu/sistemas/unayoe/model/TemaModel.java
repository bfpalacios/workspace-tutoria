package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@Component("temaModel")
@ViewScoped
public class TemaModel {

	private String nombre;
	private String descripcion;
	private String codigoCurso;

	public TemaModel() {
		this.inicializar();
	}

	public void inicializar() {
		this.nombre = null;
		this.descripcion = null;
		this.codigoCurso = null;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
