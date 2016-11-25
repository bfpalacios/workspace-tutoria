package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class SesionParBO.
 */
public class SesionParBO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The codigo actividad academica. */
	private int codigoActividadAcademica; //Código de Actividad Académica
	
	/** The fecha. */
	private Date fecha;
	
	/** The numero. */
	private int numero; //Número de la sesión
	
	/** The tipo. */
	private int tipo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The hora inicio. */
	private String horaInicio;
	
	/** The hora fin. */
	private String horaFin;
	
	/** The estado. */
	private String estado; //Estado de la sesión
	
	
	/**
	 * Gets the codigo actividad academica.
	 *
	 * @return the codigo actividad academica
	 */
	public int getCodigoActividadAcademica() {
		return codigoActividadAcademica;
	}
	
	/**
	 * Sets the codigo actividad academica.
	 *
	 * @param codigoActividadAcademica the new codigo actividad academica
	 */
	public void setCodigoActividadAcademica(int codigoActividadAcademica) {
		this.codigoActividadAcademica = codigoActividadAcademica;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Gets the hora inicio.
	 *
	 * @return the hora inicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}
	
	/**
	 * Sets the hora inicio.
	 *
	 * @param horaInicio the new hora inicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	/**
	 * Gets the hora fin.
	 *
	 * @return the hora fin
	 */
	public String getHoraFin() {
		return horaFin;
	}
	
	/**
	 * Sets the hora fin.
	 *
	 * @param horaFin the new hora fin
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
