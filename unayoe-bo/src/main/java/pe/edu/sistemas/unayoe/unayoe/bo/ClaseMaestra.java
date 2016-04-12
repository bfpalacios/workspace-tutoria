package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class ClaseMaestra implements Serializable{
	private static final long serialVersionUID = 1L;	
	private Integer idCampo;
	private String valorCampo;
	
	public int getIdCampo(){
		return idCampo;
	}
	
	public void setIdCampo(Integer IdCampo){
		this.idCampo = IdCampo;
	}
	
	public String getValorCampo(){
		return valorCampo;
	}
	
	public void setValorCampo(String ValorCampo){
		this.valorCampo = ValorCampo;
	}
}
