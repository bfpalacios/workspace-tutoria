package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

public class EncuestaBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int anio;
	private int periodo;
	private String codTutoria;
	private String codPsicologa;
	private int codPregunta;
	private String pregunta;
	private String respuesta;
	private String calificacionTutor;
	private String calificacionPsicologa;
	private List<ClaseMaestra> listaCalificacion;
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public String getCodTutoria() {
		return codTutoria==null?"":codTutoria;
	}
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	public String getCodPsicologa() {
		return codPsicologa==null?"":codPsicologa;
	}
	public void setCodPsicologa(String codPsicologa) {
		this.codPsicologa = codPsicologa;
	}
	public int getCodPregunta() {
		return codPregunta;
	}
	public void setCodPregunta(int codPregunta) {
		this.codPregunta = codPregunta;
	}
	public String getPregunta() {
		return pregunta==null?"":pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuesta==null?"":respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getCalificacionTutor() {
		return calificacionTutor==null?"":calificacionTutor;
	}
	public void setCalificacionTutor(String calificacionTutor) {
		this.calificacionTutor = calificacionTutor;
	}
	public String getCalificacionPsicologa() {
		return calificacionPsicologa==null?"":calificacionPsicologa;
	}
	public void setCalificacionPsicologa(String calificacionPsicologa) {
		this.calificacionPsicologa = calificacionPsicologa;
	}
	public List<ClaseMaestra> getListaCalificacion() {
		return listaCalificacion;
	}
	public void setListaCalificacion(List<ClaseMaestra> listaCalificacion) {
		this.listaCalificacion = listaCalificacion;
	}	
}
