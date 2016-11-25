package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GrupoBO.
 */
public class GrupoBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The p codigo. */
	private String pCodigo;
	
	/** The grupo. */
	private String grupo;
	
	/** The anio. */
	private String anio;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The periodo. */
	private String periodo;
	
	/**
	 * Gets the p codigo.
	 *
	 * @return the p codigo
	 */
	public String getpCodigo() {
		return pCodigo;
	}
	
	/**
	 * Sets the p codigo.
	 *
	 * @param pCodigo the new p codigo
	 */
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	
	/**
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	
	/**
	 * Sets the grupo.
	 *
	 * @param grupo the new grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}
	
	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getcCodigo() {
		return cCodigo;
	}
	
	/**
	 * Sets the c codigo.
	 *
	 * @param cCodigo the new c codigo
	 */
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
