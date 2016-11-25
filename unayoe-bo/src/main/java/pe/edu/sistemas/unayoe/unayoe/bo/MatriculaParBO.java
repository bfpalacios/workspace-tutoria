package pe.edu.sistemas.unayoe.unayoe.bo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class MatriculaParBO.
 */
public class MatriculaParBO implements Serializable{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The codigo matricula. */
    int codigoMatricula;
    
    /** The codigo programacion. */
    int codigoProgramacion;
    
    /** The codigo alumno. */
    String codigoAlumno;

    /**
     * Gets the codigo matricula.
     *
     * @return the codigo matricula
     */
    public int getCodigoMatricula() {
        return codigoMatricula;
    }

    /**
     * Sets the codigo matricula.
     *
     * @param codigoMatricula the new codigo matricula
     */
    public void setCodigoMatricula(int codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    /**
     * Gets the codigo programacion.
     *
     * @return the codigo programacion
     */
    public int getCodigoProgramacion() {
        return codigoProgramacion;
    }

    /**
     * Sets the codigo programacion.
     *
     * @param codigoProgramacion the new codigo programacion
     */
    public void setCodigoProgramacion(int codigoProgramacion) {
        this.codigoProgramacion = codigoProgramacion;
    }

    /**
     * Gets the codigo alumno.
     *
     * @return the codigo alumno
     */
    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    /**
     * Sets the codigo alumno.
     *
     * @param codigoAlumno the new codigo alumno
     */
    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
