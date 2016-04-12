package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class MatriculaBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String aCodigo;
	private String anio;
	private String periodo;
	private String grupo;
	private String cCodigo;
	private String repitencias;
	private String cNombre;
	
	
	public String getaCodigo() {
		return aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getcCodigo() {
		return cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getRepitencias() {
		return repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	
	public String getcNombre() {
		return cNombre;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	
	@Override
	public boolean equals(Object m ){
		boolean esIgual=false;
		MatriculaBO matricula = (MatriculaBO)m;
		if(matricula.getcCodigo().equalsIgnoreCase(this.cCodigo)&&matricula.getaCodigo().equalsIgnoreCase(this.aCodigo)&&
				matricula.getGrupo().equalsIgnoreCase(this.grupo)&&matricula.getAnio().equalsIgnoreCase(this.anio)&&
				matricula.getPeriodo().equalsIgnoreCase(this.periodo))
			esIgual=true;
		return esIgual;
	}

	
	
}
