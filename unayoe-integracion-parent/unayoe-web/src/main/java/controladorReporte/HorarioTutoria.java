package controladorReporte;

public class HorarioTutoria {
	private String codCurso;
	private String nomCurso;
	private String repitencias;
	private String nomProfesor;
	private String dia;
	private String horaIni;
	private String horaFin;
	private String codAlu;
	private String nomAlu;
	private String ciclo;
	private String frecuencia;
	private int num_sesiones;
	private int num_asistencia_asistio;
	private int num_asistencia_falto;
	private int num_asistencia_tardanza;
	private int num_tareas_pendiente;
	private int num_tareas_parcialmente;
	private int num_tareas_cerrado;
	private double porcentaje_asistencias;
	private int num_actas;
	
	public HorarioTutoria() {
		super();
	}

	
	public HorarioTutoria(String codCurso, String nomCurso, String repitencias,
			String nomProfesor, String dia, String horaIni, String horaFin,
			String codAlu, String nomAlu) {
		super();
		this.codCurso = codCurso;
		this.nomCurso = nomCurso;
		this.repitencias = repitencias;
		this.nomProfesor = nomProfesor;
		this.dia = dia;
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.codAlu = codAlu;
		this.nomAlu = nomAlu;
	}



	public String getCodAlu() {
		return codAlu;
	}

	public void setCodAlu(String codAlu) {
		this.codAlu = codAlu;
	}

	public String getNomAlu() {
		return nomAlu;
	}

	public void setNomAlu(String nomAlu) {
		this.nomAlu = nomAlu;
	}

	public String getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getNomCurso() {
		return nomCurso;
	}
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	public String getRepitencias() {
		return repitencias;
	}
	public void setRepitencias(String repitencias) {
		this.repitencias = repitencias;
	}
	public String getNomProfesor() {
		return nomProfesor;
	}
	public void setNomProfesor(String nomProfesor) {
		this.nomProfesor = nomProfesor;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {
		this.horaIni = horaIni;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}


	public String getCiclo() {
		return ciclo;
	}


	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}


	public String getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


	public int getNum_sesiones() {
		return num_sesiones;
	}


	public void setNum_sesiones(int num_sesiones) {
		this.num_sesiones = num_sesiones;
	}


	public int getNum_asistencia_asistio() {
		return num_asistencia_asistio;
	}


	public void setNum_asistencia_asistio(int num_asistencia_asistio) {
		this.num_asistencia_asistio = num_asistencia_asistio;
	}


	public int getNum_asistencia_falto() {
		return num_asistencia_falto;
	}


	public void setNum_asistencia_falto(int num_asistencia_falto) {
		this.num_asistencia_falto = num_asistencia_falto;
	}


	public int getNum_asistencia_tardanza() {
		return num_asistencia_tardanza;
	}


	public void setNum_asistencia_tardanza(int num_asistencia_tardanza) {
		this.num_asistencia_tardanza = num_asistencia_tardanza;
	}


	public int getNum_tareas_pendiente() {
		return num_tareas_pendiente;
	}


	public void setNum_tareas_pendiente(int num_tareas_pendiente) {
		this.num_tareas_pendiente = num_tareas_pendiente;
	}


	public int getNum_tareas_parcialmente() {
		return num_tareas_parcialmente;
	}


	public void setNum_tareas_parcialmente(int num_tareas_parcialmente) {
		this.num_tareas_parcialmente = num_tareas_parcialmente;
	}


	public int getNum_tareas_cerrado() {
		return num_tareas_cerrado;
	}


	public void setNum_tareas_cerrado(int num_tareas_cerrado) {
		this.num_tareas_cerrado = num_tareas_cerrado;
	}


	public double getPorcentaje_asistencias() {
		return porcentaje_asistencias;
	}


	public void setPorcentaje_asistencias(double porcentaje_asistencias) {
		this.porcentaje_asistencias = porcentaje_asistencias;
	}


	public int getNum_actas() {
		return num_actas;
	}


	public void setNum_actas(int num_actas) {
		this.num_actas = num_actas;
	}
	
	

}
