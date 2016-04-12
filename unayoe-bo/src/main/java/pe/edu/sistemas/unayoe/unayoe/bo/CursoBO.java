package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.math.BigInteger;

public class CursoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cCodigo;
	private String nombre;
	private BigInteger creditos;
	
	public String getcCodigo() {
		return cCodigo==null?"":cCodigo;
	}
	public void setcCodigo(String cCodigo) {
		this.cCodigo = cCodigo;
	}
	public String getNombre() {
		return nombre==null?"":nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigInteger getCreditos() {
		return creditos;
	}
	public void setCreditos(BigInteger bigInteger) {
		this.creditos = bigInteger;
	} 
	
	@Override
	public boolean equals(Object c ){
		boolean esIgual=false;
		CursoBO curso = (CursoBO)c;
		if(curso.getcCodigo().equalsIgnoreCase(this.cCodigo))
			esIgual=true;
		return esIgual;
	}
}
