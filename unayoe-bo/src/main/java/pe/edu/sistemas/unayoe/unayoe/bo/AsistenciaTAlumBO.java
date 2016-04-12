package pe.edu.sistemas.unayoe.unayoe.bo;

public class AsistenciaTAlumBO {
	private String fechaT;
	private String asistenciaT;
	private String observacionT;
	private String tAnio;
	private String tCodigo;
	private DiasClaseBO diasClases;
	private AlumnoBO alumno;
	private CursoBO curso;
	private TutoriaBO tutoria;
	
	public String getFechaT() {
		return fechaT;
	}
	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
	}
	public String getAsistenciaT() {
		return asistenciaT;
	}
	public void setAsistenciaT(String asistenciaT) {
		this.asistenciaT = asistenciaT;
	}
	public String getObservacionT() {
		return observacionT;
	}
	public void setObservacionT(String observacionT) {
		this.observacionT = observacionT;
	}
	public String gettAnio() {
		return tAnio;
	}
	public void settAnio(String tAnio) {
		this.tAnio = tAnio;
	}
	public String gettCodigo() {
		return tCodigo;
	}
	public void settCodigo(String tCodigo) {
		this.tCodigo = tCodigo;
	}
	public DiasClaseBO getDiasClases() {
		return diasClases;
	}
	public void setDiasClases(DiasClaseBO diasClases) {
		this.diasClases = diasClases;
	}
	public AlumnoBO getAlumno() {
		return alumno;
	}
	public void setAlumno(AlumnoBO alumno) {
		this.alumno = alumno;
	}
	public CursoBO getCurso() {
		return curso;
	}
	public void setCurso(CursoBO curso) {
		this.curso = curso;
	}
	public TutoriaBO getTutoria() {
		return tutoria;
	}
	public void setTutoria(TutoriaBO tutoria) {
		this.tutoria = tutoria;
	} 
	
}
