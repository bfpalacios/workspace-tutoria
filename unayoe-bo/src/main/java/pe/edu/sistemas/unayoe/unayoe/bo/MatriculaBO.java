package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class MatriculaBO.
 */
public class MatriculaBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The a codigo. */
	private String aCodigo;
	
	/** The anio. */
	private String anio;
	
	/** The periodo. */
	private String periodo;
	
	/** The grupo. */
	private String grupo;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The repitencias. */
	private String repitencias;
	
	/** The c nombre. */
	private String cNombre;
	
	
	/**
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getaCodigo() {
		return aCodigo;
	}
	
	/**
	 * Sets the a codigo.
	 *
	 * @param aCodigo the new a codigo
	 */
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
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
	 * Gets the repitencias.
	 *
	 * @return the repitencias
	 */
	public String getRepitencias() {
		return repitencias;
	}
	
	/**
	 * Sets the repitencias.
	 *
	 * @param repitencias the new repitencias
	 */
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	
	/**
	 * Gets the c nombre.
	 *
	 * @return the c nombre
	 */
	public String getcNombre() {
		return cNombre;
	}
	
	/**
	 * Sets the c nombre.
	 *
	 * @param cNombre the new c nombre
	 */
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
