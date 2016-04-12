package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class NotasBO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String anio;
	private String periodo;
	private String aCodigo;
	private String cCodigo;
	private String e1;
	private String e2;
	private String e3;
	private String e4;
	private String e5;
	private String ep;
	private String ef;

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getaCodigo() {
		return aCodigo;
	}

	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}

	public String getcCodigo() {
		return cCodigo;
	}

	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}

	public String getE1() {
		return e1;
	}

	public void setE1(String e1) {
		this.e1 = e1;
	}

	public String getE2() {
		return e2;
	}

	public void setE2(String e2) {
		this.e2 = e2;
	}

	public String getE3() {
		return e3;
	}

	public void setE3(String e3) {
		this.e3 = e3;
	}

	public String getE4() {
		return e4;
	}

	public void setE4(String e4) {
		this.e4 = e4;
	}

	public String getE5() {
		return e5;
	}

	public void setE5(String e5) {
		this.e5 = e5;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public String getEf() {
		return ef;
	}

	public void setEf(String ef) {
		this.ef = ef;
	}

}
