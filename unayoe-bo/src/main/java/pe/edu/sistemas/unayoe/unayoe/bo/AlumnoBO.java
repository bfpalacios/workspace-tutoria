package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.math.BigInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoBO.
 */
public class AlumnoBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The a codigo. */
	private String aCodigo;
	
	/** The a nombre. */
	private String aNombre;
	
	/** The a apellido. */
	private String aApellido;
	
	/** The a F nacimiento. */
	private String aFNacimiento;
	
	/** The a direccion. */
	private String aDireccion;
	
	/** The a email. */
	private String aEmail;
	
	/** The a telefono. */
	private String aTelefono;
	
	/** The a DNI. */
	private String aDNI;
	
	/** The a codigo sem. */
	private String aCodigoSem;
	
	/** The a anio ing. */
	private String aAnioIng;
	
	/** The a codigo plan. */
	private String aCodigoPlan;
	
	/** The a ciclo. */
	private String aCiclo;
	
	/** The repitencia. */
	private String repitencia;
	
	/** The asistencia. */
	private String asistencia;
	
	/** The creditos. */
	private BigInteger creditos;
	
	/** The dias clases. */
	private DiasClaseBO diasClases;
	
	/**
	 * Gets the dias clases.
	 *
	 * @return the dias clases
	 */
	public DiasClaseBO getDiasClases() {
		return diasClases;
	}
	
	/**
	 * Sets the dias clases.
	 *
	 * @param diasClases the new dias clases
	 */
	public void setDiasClases(DiasClaseBO diasClases) {
		this.diasClases = diasClases;
	}
	
	/**
	 * Gets the repitencia.
	 *
	 * @return the repitencia
	 */
	public String getRepitencia() {
		return repitencia==null?"":repitencia;
	}
	
	/**
	 * Sets the repitencia.
	 *
	 * @param repitencia the new repitencia
	 */
	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}
	
	/**
	 * Gets the asistencia.
	 *
	 * @return the asistencia
	 */
	public String getAsistencia() {
		return asistencia==null?"":asistencia;
	}
	
	/**
	 * Sets the asistencia.
	 *
	 * @param asistencia the new asistencia
	 */
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	
	/**
	 * Gets the a codigo.
	 *
	 * @return the a codigo
	 */
	public String getaCodigo() {
		return aCodigo==null?"":aCodigo;
	}
	
	/**
	 * Sets the a codigo.
	 *
	 * @param aCodigo the new a codigo
	 */
	public void setaCodigo(String aCodigo) {
		this.aCodigo = aCodigo;
	}
	
	/**
	 * Gets the a nombre.
	 *
	 * @return the a nombre
	 */
	public String getaNombre() {
		return aNombre==null?"":aNombre;
	}
	
	/**
	 * Sets the a nombre.
	 *
	 * @param aNombre the new a nombre
	 */
	public void setaNombre(String aNombre) {
		this.aNombre = aNombre;
	}
	
	/**
	 * Gets the a apellido.
	 *
	 * @return the a apellido
	 */
	public String getaApellido() {
		return aApellido==null?"":aApellido;
	}
	
	/**
	 * Sets the a apellido.
	 *
	 * @param aApellido the new a apellido
	 */
	public void setaApellido(String aApellido) {
		this.aApellido = aApellido;
	}
	
	/**
	 * Gets the a F nacimiento.
	 *
	 * @return the a F nacimiento
	 */
	public String getaFNacimiento() {
		return aFNacimiento==null?"":aFNacimiento;
	}
	
	/**
	 * Sets the a F nacimiento.
	 *
	 * @param aFNacimiento the new a F nacimiento
	 */
	public void setaFNacimiento(String aFNacimiento) {
		this.aFNacimiento = aFNacimiento;
	}
	
	/**
	 * Gets the a direccion.
	 *
	 * @return the a direccion
	 */
	public String getaDireccion() {
		return aDireccion==null?"":aDireccion;
	}
	
	/**
	 * Sets the a direccion.
	 *
	 * @param aDireccion the new a direccion
	 */
	public void setaDireccion(String aDireccion) {
		this.aDireccion = aDireccion;
	}
	
	/**
	 * Gets the a email.
	 *
	 * @return the a email
	 */
	public String getaEmail() {
		return aEmail==null?"":aEmail;
	}
	
	/**
	 * Sets the a email.
	 *
	 * @param aEmail the new a email
	 */
	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}
	
	/**
	 * Gets the a telefono.
	 *
	 * @return the a telefono
	 */
	public String getaTelefono() {
		return aTelefono==null?"":aTelefono;
	}
	
	/**
	 * Sets the a telefono.
	 *
	 * @param aTelefono the new a telefono
	 */
	public void setaTelefono(String aTelefono) {
		this.aTelefono = aTelefono;
	}
	
	/**
	 * Gets the a DNI.
	 *
	 * @return the a DNI
	 */
	public String getaDNI() {
		return aDNI==null?"":aDNI;
	}
	
	/**
	 * Sets the a DNI.
	 *
	 * @param aDNI the new a DNI
	 */
	public void setaDNI(String aDNI) {
		this.aDNI = aDNI;
	}
	
	/**
	 * Gets the a codigo sem.
	 *
	 * @return the a codigo sem
	 */
	public String getaCodigoSem() {
		return aCodigoSem;
	}
	
	/**
	 * Sets the a codigo sem.
	 *
	 * @param aCodigoSem the new a codigo sem
	 */
	public void setaCodigoSem(String aCodigoSem) {
		this.aCodigoSem = aCodigoSem;
	}
	
	/**
	 * Gets the a anio ing.
	 *
	 * @return the a anio ing
	 */
	public String getaAnioIng() {
		return aAnioIng;
	}
	
	/**
	 * Sets the a anio ing.
	 *
	 * @param aAnioIng the new a anio ing
	 */
	public void setaAnioIng(String aAnioIng) {
		this.aAnioIng = aAnioIng;
	}
	
	/**
	 * Gets the a codigo plan.
	 *
	 * @return the a codigo plan
	 */
	public String getaCodigoPlan() {
		return aCodigoPlan;
	}
	
	/**
	 * Sets the a codigo plan.
	 *
	 * @param aCodigoPlan the new a codigo plan
	 */
	public void setaCodigoPlan(String aCodigoPlan) {
		this.aCodigoPlan = aCodigoPlan;
	}
	
	/**
	 * Gets the a ciclo.
	 *
	 * @return the a ciclo
	 */
	public String getaCiclo() {
		return aCiclo;
	}
	
	/**
	 * Sets the a ciclo.
	 *
	 * @param aCiclo the new a ciclo
	 */
	public void setaCiclo(String aCiclo) {
		this.aCiclo = aCiclo;
	}
	
	/**
	 * Gets the creditos.
	 *
	 * @return the creditos
	 */
	public BigInteger getCreditos() {
		return creditos;
	}
	
	/**
	 * Sets the creditos.
	 *
	 * @param creditos the new creditos
	 */
	public void setCreditos(BigInteger creditos) {
		this.creditos = creditos;
	} 
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o ){
		boolean esIgual=false;
		AlumnoBO alumno = (AlumnoBO)o;
		if(alumno.getaCodigo().equalsIgnoreCase(this.aCodigo))
			esIgual=true;
		return esIgual;
	}
	

}
