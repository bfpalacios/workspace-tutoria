package pe.edu.sistemas.unayoe.unayoe.bo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class MatriculaParBO implements Serializable{
    private static final long serialVersionUID = 1L;

    int codigoMatricula;
    int codigoProgramacion;
    String codigoAlumno;

    public int getCodigoMatricula() {
        return codigoMatricula;
    }

    public void setCodigoMatricula(int codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
    }

    public int getCodigoProgramacion() {
        return codigoProgramacion;
    }

    public void setCodigoProgramacion(int codigoProgramacion) {
        this.codigoProgramacion = codigoProgramacion;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
