package pe.edu.sistemas.unayoe.model;

import java.util.List;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Component;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

// TODO: Auto-generated Javadoc
/**
 * The Class ClaseMaestraModel.
 */
@Component("claseMaestraModel")
@RequestScoped
public class ClaseMaestraModel {
	
	/** The id campo. */
	private int idCampo;
	
	/** The valor campo. */
	private String valorCampo;
	
	/** The lista clase maestra. */
	private List<ClaseMaestra> listaClaseMaestra;
	
	/**
	 * Gets the id campo.
	 *
	 * @return the id campo
	 */
	public int getIdCampo() {
		return idCampo;
	}
	
	/**
	 * Sets the id campo.
	 *
	 * @param idCampo the new id campo
	 */
	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}
	
	/**
	 * Gets the valor campo.
	 *
	 * @return the valor campo
	 */
	public String getValorCampo() {
		return valorCampo;
	}
	
	/**
	 * Sets the valor campo.
	 *
	 * @param valorCampo the new valor campo
	 */
	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}
	
	/**
	 * Gets the lista clase maestra.
	 *
	 * @return the lista clase maestra
	 */
	public List<ClaseMaestra> getListaClaseMaestra() {
		return listaClaseMaestra;
	}
	
	/**
	 * Sets the lista clase maestra.
	 *
	 * @param listaClaseMaestra the new lista clase maestra
	 */
	public void setListaClaseMaestra(List<ClaseMaestra> listaClaseMaestra) {
		this.listaClaseMaestra = listaClaseMaestra;
	}
}
