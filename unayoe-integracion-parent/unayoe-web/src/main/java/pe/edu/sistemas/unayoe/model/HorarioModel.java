package pe.edu.sistemas.unayoe.model;

import org.springframework.stereotype.Component;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

import javax.faces.bean.RequestScoped;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 07/11/2015.
 */
@Component
@RequestScoped
public class HorarioModel {
    
    /** The codigo actividad academica. */
    private int codigoActividadAcademica;
    
    /** The dia. */
    private String dia;
    
    /** The hora inicio. */
    private String horaInicio;
    
    /** The hora fin. */
    private String horaFin;

    /** The horas inicio. */
    private List<ClaseMaestra> horasInicio;
    
    /** The horas fin. */
    private List<ClaseMaestra> horasFin;

    /**
     * Gets the codigo actividad academica.
     *
     * @return the codigo actividad academica
     */
    public int getCodigoActividadAcademica() {
        return codigoActividadAcademica;
    }

    /**
     * Sets the codigo actividad academica.
     *
     * @param codigoActividadAcademica the new codigo actividad academica
     */
    public void setCodigoActividadAcademica(int codigoActividadAcademica) {
        this.codigoActividadAcademica = codigoActividadAcademica;
    }

    /**
     * Gets the dia.
     *
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Sets the dia.
     *
     * @param dia the new dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Gets the hora inicio.
     *
     * @return the hora inicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Sets the hora inicio.
     *
     * @param horaInicio the new hora inicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Gets the hora fin.
     *
     * @return the hora fin
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Sets the hora fin.
     *
     * @param horaFin the new hora fin
     */
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }


    /**
     * Gets the horas inicio.
     *
     * @return the horas inicio
     */
    public List<ClaseMaestra> getHorasInicio() {
        return horasInicio;
    }

    /**
     * Sets the horas inicio.
     *
     * @param horasInicio the new horas inicio
     */
    public void setHorasInicio(List<ClaseMaestra> horasInicio) {
        this.horasInicio = horasInicio;
    }

    /**
     * Gets the horas fin.
     *
     * @return the horas fin
     */
    public List<ClaseMaestra> getHorasFin() {
        return horasFin;
    }

    /**
     * Sets the horas fin.
     *
     * @param horasFin the new horas fin
     */
    public void setHorasFin(List<ClaseMaestra> horasFin) {
        this.horasFin = horasFin;
    }

    /**
     * Reset.
     */
    public void reset(){
        dia = horaInicio = horaFin = null;
    }
}
