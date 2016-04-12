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
	
	

}
