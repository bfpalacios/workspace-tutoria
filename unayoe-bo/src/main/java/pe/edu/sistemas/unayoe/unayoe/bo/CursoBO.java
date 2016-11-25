package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.math.BigInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class CursoBO.
 */
public class CursoBO implements Serializable , Comparable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c codigo. */
	private String cCodigo;
	
	/** The nombre. */
	private String nombre;
	
	/** The creditos. */
	private BigInteger creditos;
	
	/**
	 * Gets the c codigo.
	 *
	 * @return the c codigo
	 */
	public String getcCodigo() {
		return cCodigo==null?"":cCodigo;
	}
	
	/**
	 * Sets the c codigo.
	 *
	 * @param cCodigo the new c codigo
	 */
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre==null?"":nombre;
	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @param bigInteger the new creditos
	 */
	public void setCreditos(BigInteger bigInteger) {
		this.creditos = bigInteger;
	} 
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object c ){
		boolean esIgual=false;
		CursoBO curso = (CursoBO)c;
		if(curso.getcCodigo().equalsIgnoreCase(this.cCodigo))
			esIgual=true;
		return esIgual;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object arg0) {
		CursoBO curso=(CursoBO) arg0;
		// TODO Auto-generated method stub
		return this.getNombre().compareTo(curso.getNombre());
	}
}
