package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AreaConocimientoBO.
 */
public class AreaConocimientoBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cod area conocimiento. */
	private String codAreaConocimiento;
	
	/** The nom area conocimiento. */
	private String nomAreaConocimiento;
	
	/**
	 * Gets the cod area conocimiento.
	 *
	 * @return the cod area conocimiento
	 */
	public String getCodAreaConocimiento() {
		return codAreaConocimiento;
	}
	
	/**
	 * Sets the cod area conocimiento.
	 *
	 * @param codAreaConocimiento the new cod area conocimiento
	 */
	public void setCodAreaConocimiento(String codAreaConocimiento) {
		this.codAreaConocimiento = codAreaConocimiento;
	}
	
	/**
	 * Gets the nom area conocimiento.
	 *
	 * @return the nom area conocimiento
	 */
	public String getNomAreaConocimiento() {
		return nomAreaConocimiento;
	}
	
	/**
	 * Sets the nom area conocimiento.
	 *
	 * @param nomAreaConocimiento the new nom area conocimiento
	 */
	public void setNomAreaConocimiento(String nomAreaConocimiento) {
		this.nomAreaConocimiento = nomAreaConocimiento;
	}

}
