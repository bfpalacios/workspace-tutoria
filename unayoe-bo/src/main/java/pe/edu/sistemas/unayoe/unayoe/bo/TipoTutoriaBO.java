package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class TipoTutoriaBO.
 */
public class TipoTutoriaBO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo tutoria. */
	private int codigoTutoria;
	
	/** The nombre tutoria. */
	private String nombreTutoria;
	
	/**
	 * Instantiates a new tipo tutoria BO.
	 */
	public TipoTutoriaBO(){
		
	}
	
	/**
	 * Instantiates a new tipo tutoria BO.
	 *
	 * @param n the n
	 * @param name the name
	 */
	// Luego borrar esto
	public TipoTutoriaBO(int n, String name){
		this.codigoTutoria = n;
		this.nombreTutoria = name;
	}
	
	/**
	 * Gets the codigo tutoria.
	 *
	 * @return the codigo tutoria
	 */
	public int getCodigoTutoria() {
		return codigoTutoria;
	}
	
	/**
	 * Sets the codigo tutoria.
	 *
	 * @param codigoTutoria the new codigo tutoria
	 */
	public void setCodigoTutoria(int codigoTutoria) {
		this.codigoTutoria = codigoTutoria;
	}
	
	/**
	 * Gets the nombre tutoria.
	 *
	 * @return the nombre tutoria
	 */
	public String getNombreTutoria() {
		return nombreTutoria;
	}
	
	/**
	 * Sets the nombre tutoria.
	 *
	 * @param nombreTutoria the new nombre tutoria
	 */
	public void setNombreTutoria(String nombreTutoria) {
		this.nombreTutoria = nombreTutoria;
	}
	
}
