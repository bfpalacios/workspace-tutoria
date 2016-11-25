package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DisponibilidadTutoriaParBO.
 */
public class DisponibilidadTutoriaParBO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2051164578297553669L;

	/** The Constant LUNES. */
	public static final Integer LUNES = 0;
	
	/** The Constant MARTES. */
	public static final Integer MARTES = 1;
	
	/** The Constant MIERCOLES. */
	public static final Integer MIERCOLES = 2;
	
	/** The Constant JUEVES. */
	public static final Integer JUEVES = 3;
	
	/** The Constant VIERNES. */
	public static final Integer VIERNES = 4;
	
	/** The Constant SABADO. */
	public static final Integer SABADO = 5;
	
	/** The Constant DOMINGO. */
	public static final Integer DOMINGO = 6;

	/** The Constant TURNO_MANANA. */
	public static final Integer TURNO_MANANA = 0;
	
	/** The Constant TURNO_TARDE. */
	public static final Integer TURNO_TARDE = 1;
	
	/** The Constant TURNO_NOCHE. */
	public static final Integer TURNO_NOCHE = 2;

	/** The id. */
	private Integer id;
	
	/** The dia. */
	private Integer dia;
	
	/** The turno. */
	private Integer turno;

	/** The nombre dia. */
	private String nombreDia;
	
	/** The nombre turno. */
	private String nombreTurno;

	/**
	 * Instantiates a new disponibilidad tutoria par BO.
	 */
	public DisponibilidadTutoriaParBO() {
		this.id = null;
		this.dia = null;
		this.turno = null;
	}

	/**
	 * Convertir nombre dia.
	 *
	 * @return the string
	 */
	private String convertirNombreDia() {
		String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		return dias[this.dia];
	}

	/**
	 * Convertir nombre turno.
	 *
	 * @return the string
	 */
	private String convertirNombreTurno() {
		String[] turnos = { "Mañana", "Tarde", "Noche" };
		return turnos[this.turno];
	}

	/**
	 * Gets the dia.
	 *
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 *
	 * @param dia the new dia
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
		this.nombreDia = convertirNombreDia();
	}

	/**
	 * Gets the turno.
	 *
	 * @return the turno
	 */
	public Integer getTurno() {
		return turno;
	}

	/**
	 * Sets the turno.
	 *
	 * @param turno the new turno
	 */
	public void setTurno(Integer turno) {
		this.turno = turno;
		this.nombreTurno = convertirNombreTurno();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the nombre dia.
	 *
	 * @return the nombre dia
	 */
	public String getNombreDia() {
		return this.nombreDia;
	}

	/**
	 * Gets the nombre turno.
	 *
	 * @return the nombre turno
	 */
	public String getNombreTurno() {
		return this.nombreTurno;
	}

	/**
	 * Sets the nombre dia.
	 *
	 * @param nombreDia the new nombre dia
	 */
	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	/**
	 * Sets the nombre turno.
	 *
	 * @param nombreTurno the new nombre turno
	 */
	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

}