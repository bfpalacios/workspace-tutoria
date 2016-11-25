package pe.edu.sistemas.unayoe.unayoe.bo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ActividadAcademicaBO.
 * 
 */
public class ActividadAcademicaBO implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The codigo. */
    private int codigo; //De la actividad
    
    /** The nombre. */
    private String nombre; //De la actividad

    /** The codigo tutor. */
    private String codigoTutor; //Del tutor
    
    /** The codigo postulacion. */
    private int codigoPostulacion;

    /** The codigo tema. */
    private int codigoTema;
    
    /** The numero vacantes. */
    private int numeroVacantes;
    
    /** The vacantes restantes. */
    private int vacantesRestantes;

    /**
     * Gets the vacantes restantes.
     *
     * @return the vacantes restantes
     */
    public int getVacantesRestantes() {
        return vacantesRestantes;
    }

    /**
     * Sets the vacantes restantes.
     *
     * @param vacantesRestantes the new vacantes restantes
     */
    public void setVacantesRestantes(int vacantesRestantes) {
        this.vacantesRestantes = vacantesRestantes;
    }

    /**
     * Gets the codigo.
     *
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the codigo tutor.
     *
     * @return the codigo tutor
     */
    public String getCodigoTutor() {
        return codigoTutor;
    }

    /**
     * Sets the codigo tutor.
     *
     * @param codigoTutor the new codigo tutor
     */
    public void setCodigoTutor(String codigoTutor) {
        this.codigoTutor = codigoTutor;
    }

    /**
     * Gets the codigo postulacion.
     *
     * @return the codigo postulacion
     */
    public int getCodigoPostulacion() {
        return codigoPostulacion;
    }

    /**
     * Sets the codigo postulacion.
     *
     * @param codigoPostulacion the new codigo postulacion
     */
    public void setCodigoPostulacion(int codigoPostulacion) {
        this.codigoPostulacion = codigoPostulacion;
    }

    /**
     * Gets the codigo tema.
     *
     * @return the codigo tema
     */
    public int getCodigoTema() {
        return codigoTema;
    }

    /**
     * Sets the codigo tema.
     *
     * @param codigoTema the new codigo tema
     */
    public void setCodigoTema(int codigoTema) {
        this.codigoTema = codigoTema;
    }

    /**
     * Gets the numero vacantes.
     *
     * @return the numero vacantes
     */
    public int getNumeroVacantes() {
        return numeroVacantes;
    }

    /**
     * Sets the numero vacantes.
     *
     * @param numeroVacantes the new numero vacantes
     */
    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
