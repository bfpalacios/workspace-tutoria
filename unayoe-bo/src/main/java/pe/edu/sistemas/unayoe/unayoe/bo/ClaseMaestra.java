package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ClaseMaestra.
 */
public class ClaseMaestra implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/** The id campo. */
	private Integer idCampo;
	
	/** The valor campo. */
	private String valorCampo;
	
	/**
	 * Gets the id campo.
	 *
	 * @return the id campo
	 */
	public int getIdCampo(){
		return idCampo;
	}
	
	/**
	 * Sets the id campo.
	 *
	 * @param IdCampo the new id campo
	 */
	public void setIdCampo(Integer IdCampo){
		this.idCampo = IdCampo;
	}
	
	/**
	 * Gets the valor campo.
	 *
	 * @return the valor campo
	 */
	public String getValorCampo(){
		return valorCampo;
	}
	
	/**
	 * Sets the valor campo.
	 *
	 * @param ValorCampo the new valor campo
	 */
	public void setValorCampo(String ValorCampo){
		this.valorCampo = ValorCampo;
	}
}
