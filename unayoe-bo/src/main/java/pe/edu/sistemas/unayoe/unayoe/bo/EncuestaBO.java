package pe.edu.sistemas.unayoe.unayoe.bo;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EncuestaBO.
 */
public class EncuestaBO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The anio. */
	private int anio;
	
	/** The periodo. */
	private int periodo;
	
	/** The cod tutoria. */
	private String codTutoria;
	
	/** The cod psicologa. */
	private String codPsicologa;
	
	/** The cod pregunta. */
	private int codPregunta;
	
	/** The pregunta. */
	private String pregunta;
	
	/** The respuesta. */
	private String respuesta;
	
	/** The calificacion tutor. */
	private String calificacionTutor;
	
	/** The calificacion psicologa. */
	private String calificacionPsicologa;
	
	/** The lista calificacion. */
	private List<ClaseMaestra> listaCalificacion;
	
	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}
	
	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	
	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * Gets the cod tutoria.
	 *
	 * @return the cod tutoria
	 */
	public String getCodTutoria() {
		return codTutoria==null?"":codTutoria;
	}
	
	/**
	 * Sets the cod tutoria.
	 *
	 * @param codTutoria the new cod tutoria
	 */
	public void setCodTutoria(String codTutoria) {
		this.codTutoria = codTutoria;
	}
	
	/**
	 * Gets the cod psicologa.
	 *
	 * @return the cod psicologa
	 */
	public String getCodPsicologa() {
		return codPsicologa==null?"":codPsicologa;
	}
	
	/**
	 * Sets the cod psicologa.
	 *
	 * @param codPsicologa the new cod psicologa
	 */
	public void setCodPsicologa(String codPsicologa) {
		this.codPsicologa = codPsicologa;
	}
	
	/**
	 * Gets the cod pregunta.
	 *
	 * @return the cod pregunta
	 */
	public int getCodPregunta() {
		return codPregunta;
	}
	
	/**
	 * Sets the cod pregunta.
	 *
	 * @param codPregunta the new cod pregunta
	 */
	public void setCodPregunta(int codPregunta) {
		this.codPregunta = codPregunta;
	}
	
	/**
	 * Gets the pregunta.
	 *
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta==null?"":pregunta;
	}
	
	/**
	 * Sets the pregunta.
	 *
	 * @param pregunta the new pregunta
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta==null?"":respuesta;
	}
	
	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta the new respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	/**
	 * Gets the calificacion tutor.
	 *
	 * @return the calificacion tutor
	 */
	public String getCalificacionTutor() {
		return calificacionTutor==null?"":calificacionTutor;
	}
	
	/**
	 * Sets the calificacion tutor.
	 *
	 * @param calificacionTutor the new calificacion tutor
	 */
	public void setCalificacionTutor(String calificacionTutor) {
		this.calificacionTutor = calificacionTutor;
	}
	
	/**
	 * Gets the calificacion psicologa.
	 *
	 * @return the calificacion psicologa
	 */
	public String getCalificacionPsicologa() {
		return calificacionPsicologa==null?"":calificacionPsicologa;
	}
	
	/**
	 * Sets the calificacion psicologa.
	 *
	 * @param calificacionPsicologa the new calificacion psicologa
	 */
	public void setCalificacionPsicologa(String calificacionPsicologa) {
		this.calificacionPsicologa = calificacionPsicologa;
	}
	
	/**
	 * Gets the lista calificacion.
	 *
	 * @return the lista calificacion
	 */
	public List<ClaseMaestra> getListaCalificacion() {
		return listaCalificacion;
	}
	
	/**
	 * Sets the lista calificacion.
	 *
	 * @param listaCalificacion the new lista calificacion
	 */
	public void setListaCalificacion(List<ClaseMaestra> listaCalificacion) {
		this.listaCalificacion = listaCalificacion;
	}	
}
