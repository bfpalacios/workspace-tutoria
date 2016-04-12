package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

/**
 * author: alexh
 */
public class HorarioBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int codigo; //Código del horario
	private int codigoProgramacion;
	private String dia;
	private String horaInicio;
	private String horaFin;
	private String codAula;

	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
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
	public String getCodAula() {
		return codAula;
	}
	public void setCodAula(String codAula) {
		this.codAula = codAula;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoProgramacion() {
		return codigoProgramacion;
	}

	public void setCodigoProgramacion(int codigoProgramacion) {
		this.codigoProgramacion = codigoProgramacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
