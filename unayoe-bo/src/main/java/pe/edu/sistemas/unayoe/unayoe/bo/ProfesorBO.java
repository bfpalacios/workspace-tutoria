package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class ProfesorBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String pCodigo;
	private String pNombre;
	private String pApellidos;
	private String pFNacimiento;
	private String pDireccion;
	private String pEmail;
	private String pTelefono;
	private String pDNI;
	private String pPass;
	
	public String getpPass() {
		return pPass;
	}
	public void setpPass(String pPass) {
		this.pPass = pPass;
	}
	public String getpCodigo() {
		return pCodigo;
	}
	public void setpCodigo(String pCodigo) {
		this.pCodigo = pCodigo;
	}
	public String getpNombre() {
		return pNombre;
	}
	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}
	public String getpApellidos() {
		return pApellidos;
	}
	public void setpApellidos(String pApellidos) {
		this.pApellidos = pApellidos;
	}
	public String getpFNacimiento() {
		return pFNacimiento;
	}
	public void setpFNacimiento(String pFNacimiento) {
		this.pFNacimiento = pFNacimiento;
	}
	public String getpDireccion() {
		return pDireccion;
	}
	public void setpDireccion(String pDireccion) {
		this.pDireccion = pDireccion;
	}
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	public String getpTelefono() {
		return pTelefono;
	}
	public void setpTelefono(String pTelefono) {
		this.pTelefono = pTelefono;
	}
	public String getpDNI() {
		return pDNI;
	}
	public void setpDNI(String pDNI) {
		this.pDNI = pDNI;
	}
}
