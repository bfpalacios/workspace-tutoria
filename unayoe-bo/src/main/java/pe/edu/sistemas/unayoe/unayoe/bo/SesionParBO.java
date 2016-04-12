package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.Date;

public class SesionParBO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int codigoActividadAcademica; //Código de Actividad Académica
	private Date fecha;
	private int numero; //Número de la sesión
	private int tipo;
	private String descripcion;
	private String horaInicio;
	private String horaFin;
	private String estado; //Estado de la sesión
	
	
	public int getCodigoActividadAcademica() {
		return codigoActividadAcademica;
	}
	public void setCodigoActividadAcademica(int codigoActividadAcademica) {
		this.codigoActividadAcademica = codigoActividadAcademica;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
