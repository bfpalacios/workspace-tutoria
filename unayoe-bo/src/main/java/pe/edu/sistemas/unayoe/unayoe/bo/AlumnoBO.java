package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.math.BigInteger;

public class AlumnoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String aCodigo;
	private String aNombre;
	private String aApellido;
	private String aFNacimiento;
	private String aDireccion;
	private String aEmail;
	private String aTelefono;
	private String aDNI;
	private String aCodigoSem;
	private String aAnioIng;
	private String aCodigoPlan;
	private String aCiclo;
	private String repitencia;
	private String asistencia;
	private BigInteger creditos;
	private DiasClaseBO diasClases;
	
	public DiasClaseBO getDiasClases() {
		return diasClases;
	}
	public void setDiasClases(DiasClaseBO diasClases) {
		this.diasClases = diasClases;
	}
	public String getRepitencia() {
		return repitencia==null?"":repitencia;
	}
	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}
	public String getAsistencia() {
		return asistencia==null?"":asistencia;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public String getaCodigo() {
		return aCodigo==null?"":aCodigo;
	}
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	public String getaNombre() {
		return aNombre==null?"":aNombre;
	}
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	public String getaApellido() {
		return aApellido==null?"":aApellido;
	}
	public void setaApellido(String aApellido) {
		this.aApellido = aApellido;
	}
	public String getaFNacimiento() {
		return aFNacimiento==null?"":aFNacimiento;
	}
	public void setaFNacimiento(String aFNacimiento) {
		this.aFNacimiento = aFNacimiento;
	}
	public String getaDireccion() {
		return aDireccion==null?"":aDireccion;
	}
	public void setaDireccion(String aDireccion) {
		this.aDireccion = aDireccion;
	}
	public String getaEmail() {
		return aEmail==null?"":aEmail;
	}
	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}
	public String getaTelefono() {
		return aTelefono==null?"":aTelefono;
	}
	public void setaTelefono(String aTelefono) {
		this.aTelefono = aTelefono;
	}
	public String getaDNI() {
		return aDNI==null?"":aDNI;
	}
	public void setaDNI(String aDNI) {
		this.aDNI = aDNI;
	}
	public String getaCodigoSem() {
		return aCodigoSem;
	}
	public void setaCodigoSem(String aCodigoSem) {
		this.aCodigoSem = aCodigoSem;
	}
	public String getaAnioIng() {
		return aAnioIng;
	}
	public void setaAnioIng(String aAnioIng) {
		this.aAnioIng = aAnioIng;
	}
	public String getaCodigoPlan() {
		return aCodigoPlan;
	}
	public void setaCodigoPlan(String aCodigoPlan) {
		this.aCodigoPlan = aCodigoPlan;
	}
	public String getaCiclo() {
		return aCiclo;
	}
	public void setaCiclo(String aCiclo) {
		this.aCiclo = aCiclo;
	}
	public BigInteger getCreditos() {
		return creditos;
	}
	public void setCreditos(BigInteger creditos) {
		this.creditos = creditos;
	} 
	
	@Override
	public boolean equals(Object o ){
		boolean esIgual=false;
		AlumnoBO alumno = (AlumnoBO)o;
		if(alumno.getaCodigo().equalsIgnoreCase(this.aCodigo))
			esIgual=true;
		return esIgual;
	}
	

}
