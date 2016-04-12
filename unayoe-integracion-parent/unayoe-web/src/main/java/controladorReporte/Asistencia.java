package controladorReporte;

public class Asistencia {
	private String fecha;
	private String dia;
	private String codCurso;
	private String nomCurso;
	private String repitencia;
	private String asistencia;
	private String obs;
	
	public Asistencia() {
		super();
	}
	
	
	
	public Asistencia(String fecha, String dia, String codCurso,
			String nomCurso, String repitencia, String asistencia, String obs) {
		super();
		this.fecha = fecha;
		this.dia = dia;
		this.codCurso = codCurso;
		this.nomCurso = nomCurso;
		this.repitencia = repitencia;
		this.asistencia = asistencia;
		this.obs = obs;
	}



	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
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
	public String getRepitencia() {
		return repitencia;
	}
	public void setRepitencia(String repitencia) {
		this.repitencia = repitencia;
	}
	public String getAsistencia() {
		return asistencia;
	}
	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	
	
	
	

}
