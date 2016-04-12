package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class AreaConocimientoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codAreaConocimiento;
	private String nomAreaConocimiento;
	
	public String getCodAreaConocimiento() {
		return codAreaConocimiento;
	}
	public void setCodAreaConocimiento(String codAreaConocimiento) {
		this.codAreaConocimiento = codAreaConocimiento;
	}
	public String getNomAreaConocimiento() {
		return nomAreaConocimiento;
	}
	public void setNomAreaConocimiento(String nomAreaConocimiento) {
		this.nomAreaConocimiento = nomAreaConocimiento;
	}

}
