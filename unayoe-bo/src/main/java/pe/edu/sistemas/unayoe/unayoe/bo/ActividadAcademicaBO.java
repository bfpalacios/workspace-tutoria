package pe.edu.sistemas.unayoe.unayoe.bo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class ActividadAcademicaBO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int codigo; //De la actividad
    private String nombre; //De la actividad

    private String codigoTutor; //Del tutor
    private int codigoPostulacion;

    private int codigoTema;
    private int numeroVacantes;
    private int vacantesRestantes;

    public int getVacantesRestantes() {
        return vacantesRestantes;
    }

    public void setVacantesRestantes(int vacantesRestantes) {
        this.vacantesRestantes = vacantesRestantes;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTutor() {
        return codigoTutor;
    }

    public void setCodigoTutor(String codigoTutor) {
        this.codigoTutor = codigoTutor;
    }

    public int getCodigoPostulacion() {
        return codigoPostulacion;
    }

    public void setCodigoPostulacion(int codigoPostulacion) {
        this.codigoPostulacion = codigoPostulacion;
    }

    public int getCodigoTema() {
        return codigoTema;
    }

    public void setCodigoTema(int codigoTema) {
        this.codigoTema = codigoTema;
    }

    public int getNumeroVacantes() {
        return numeroVacantes;
    }

    public void setNumeroVacantes(int numeroVacantes) {
        this.numeroVacantes = numeroVacantes;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
