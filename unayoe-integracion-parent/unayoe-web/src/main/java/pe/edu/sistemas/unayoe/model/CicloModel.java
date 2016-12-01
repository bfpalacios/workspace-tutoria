package pe.edu.sistemas.unayoe.model;

import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;

@Component("cicloModel")
@RequestScoped
public class CicloModel {
	private CicloBO ciclo;

	public CicloBO getCiclo() {
		return ciclo;
	}

	public void setCiclo(CicloBO ciclo) {
		this.ciclo = ciclo;
	}
	
	
	
}
