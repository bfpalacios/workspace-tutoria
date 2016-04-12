package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

public class DisponibilidadTutoriaParBO implements Serializable {

	private static final long serialVersionUID = 2051164578297553669L;

	public static final Integer LUNES = 0;
	public static final Integer MARTES = 1;
	public static final Integer MIERCOLES = 2;
	public static final Integer JUEVES = 3;
	public static final Integer VIERNES = 4;
	public static final Integer SABADO = 5;
	public static final Integer DOMINGO = 6;

	public static final Integer TURNO_MANANA = 0;
	public static final Integer TURNO_TARDE = 1;
	public static final Integer TURNO_NOCHE = 2;

	private Integer id;
	private Integer dia;
	private Integer turno;

	private String nombreDia;
	private String nombreTurno;

	public DisponibilidadTutoriaParBO() {
		this.id = null;
		this.dia = null;
		this.turno = null;
	}

	private String convertirNombreDia() {
		String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		return dias[this.dia];
	}

	private String convertirNombreTurno() {
		String[] turnos = { "Mañana", "Tarde", "Noche" };
		return turnos[this.turno];
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
		this.nombreDia = convertirNombreDia();
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
		this.nombreTurno = convertirNombreTurno();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreDia() {
		return this.nombreDia;
	}

	public String getNombreTurno() {
		return this.nombreTurno;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

}