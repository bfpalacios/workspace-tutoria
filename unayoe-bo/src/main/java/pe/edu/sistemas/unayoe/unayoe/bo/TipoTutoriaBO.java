package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class TipoTutoriaBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codigoTutoria;
	private String nombreTutoria;
	
	public TipoTutoriaBO(){
		
	}
	
	// Luego borrar esto
	public TipoTutoriaBO(int n, String name){
		this.codigoTutoria = n;
		this.nombreTutoria = name;
	}
	public int getCodigoTutoria() {
		return codigoTutoria;
	}
	public void setCodigoTutoria(int codigoTutoria) {
		this.codigoTutoria = codigoTutoria;
	}
	public String getNombreTutoria() {
		return nombreTutoria;
	}
	public void setNombreTutoria(String nombreTutoria) {
		this.nombreTutoria = nombreTutoria;
	}
	
}
