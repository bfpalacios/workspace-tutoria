package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

@Component("diaSemanaModel")
@RequestScoped
public class DiaSemanaModel {
	private  String indice ;
	private String nombre;
	
	
	public DiaSemanaModel(String indice, String nombre) {
		super();
		this.indice = indice;
		this.nombre = nombre;
	}
	
	public DiaSemanaModel() {
		
	}
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
