package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class GrupoBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pCodigo;
	private String grupo;
	private String anio;
	private String cCodigo;
	private String periodo;
	
	public String getpCodigo() {
		return pCodigo;
	}
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getcCodigo() {
		return cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public boolean equals(Object g ){
		boolean esIgual=false;
		GrupoBO grupo = (GrupoBO)g;
		if(grupo.getcCodigo().equalsIgnoreCase(this.cCodigo)&&grupo.getAnio().equalsIgnoreCase(this.anio) &&
			grupo.getPeriodo().equalsIgnoreCase(this.periodo)&&grupo.getGrupo().equalsIgnoreCase(this.grupo))
			esIgual=true;
		return esIgual;
	}

}
