package pe.edu.sistemas.unayoe.model;

import java.util.List;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Component;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

@Component("claseMaestraModel")
@RequestScoped
public class ClaseMaestraModel {
	private int idCampo;
	private String valorCampo;
	private List<ClaseMaestra> listaClaseMaestra;
	
	public int getIdCampo() {
		return idCampo;
	}
	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}
	public String getValorCampo() {
		return valorCampo;
	}
	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}
	public List<ClaseMaestra> getListaClaseMaestra() {
		return listaClaseMaestra;
	}
	public void setListaClaseMaestra(List<ClaseMaestra> listaClaseMaestra) {
		this.listaClaseMaestra = listaClaseMaestra;
	}
}
