package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class DiaSemanaModel.
 */
@Component("diaSemanaModel")
@RequestScoped
public class DiaSemanaModel {
	
	/** The indice. */
	private  String indice ;
	
	/** The nombre. */
	private String nombre;
	
	
	/**
	 * Instantiates a new dia semana model.
	 *
	 * @param indice the indice
	 * @param nombre the nombre
	 */
	public DiaSemanaModel(String indice, String nombre) {
		super();
		this.indice = indice;
		this.nombre = nombre;
	}
	
	/**
	 * Instantiates a new dia semana model.
	 */
	public DiaSemanaModel() {
		
	}
	
	/**
	 * Gets the indice.
	 *
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}
	
	/**
	 * Sets the indice.
	 *
	 * @param indice the new indice
	 */
	public void setIndice(String indice) {
		this.indice = indice;
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
	
	
}
