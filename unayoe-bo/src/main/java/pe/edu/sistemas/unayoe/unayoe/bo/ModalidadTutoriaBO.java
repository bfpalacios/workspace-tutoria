package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ModalidadTutoriaBO.
 */
public class ModalidadTutoriaBO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The modalidad codigo. */
	private int modalidadCodigo;
	
	/** The modalidad nombre. */
	private String modalidadNombre;
	
	/**
	 * Instantiates a new modalidad tutoria BO.
	 */
	public ModalidadTutoriaBO(){
		
	}
	
	/**
	 * Instantiates a new modalidad tutoria BO.
	 *
	 * @param modalidadCodigo the modalidad codigo
	 * @param modalidadNombre the modalidad nombre
	 */
	// Borrar despues ..
	public ModalidadTutoriaBO(int modalidadCodigo, String modalidadNombre) {
		this.modalidadCodigo = modalidadCodigo;
		this.modalidadNombre = modalidadNombre;
	}
	
	
	/**
	 * Gets the modalidad codigo.
	 *
	 * @return the modalidad codigo
	 */
	public int getModalidadCodigo() {
		return modalidadCodigo;
	}
	
	/**
	 * Sets the modalidad codigo.
	 *
	 * @param modalidadCodigo the new modalidad codigo
	 */
	public void setModalidadCodigo(int modalidadCodigo) {
		this.modalidadCodigo = modalidadCodigo;
	}
	
	/**
	 * Gets the modalidad nombre.
	 *
	 * @return the modalidad nombre
	 */
	public String getModalidadNombre() {
		return modalidadNombre;
	}
	
	/**
	 * Sets the modalidad nombre.
	 *
	 * @param modalidadNombre the new modalidad nombre
	 */
	public void setModalidadNombre(String modalidadNombre) {
		this.modalidadNombre = modalidadNombre;
	}
	
	
	
}
