package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class ModalidadTutoriaBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int modalidadCodigo;
	private String modalidadNombre;
	
	public ModalidadTutoriaBO(){
		
	}
	
	// Borrar despues ..
	public ModalidadTutoriaBO(int modalidadCodigo, String modalidadNombre) {
		this.modalidadCodigo = modalidadCodigo;
		this.modalidadNombre = modalidadNombre;
	}
	
	
	public int getModalidadCodigo() {
		return modalidadCodigo;
	}
	public void setModalidadCodigo(int modalidadCodigo) {
		this.modalidadCodigo = modalidadCodigo;
	}
	public String getModalidadNombre() {
		return modalidadNombre;
	}
	public void setModalidadNombre(String modalidadNombre) {
		this.modalidadNombre = modalidadNombre;
	}
	
	
	
}
