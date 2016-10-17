package pe.edu.sistemas.unayoe.model;

import org.springframework.stereotype.Component;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;

import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Alex on 07/11/2015
 */
@Component
@RequestScoped
public class HorarioModel {
    private int codigoActividadAcademica;
    private String dia;
    private String horaInicio;
    private String horaFin;

    private List<ClaseMaestra> horasInicio;
    private List<ClaseMaestra> horasFin;

    public int getCodigoActividadAcademica() {
        return codigoActividadAcademica;
    }

    public void setCodigoActividadAcademica(int codigoActividadAcademica) {
        this.codigoActividadAcademica = codigoActividadAcademica;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }


    public List<ClaseMaestra> getHorasInicio() {
        return horasInicio;
    }

    public void setHorasInicio(List<ClaseMaestra> horasInicio) {
        this.horasInicio = horasInicio;
    }

    public List<ClaseMaestra> getHorasFin() {
        return horasFin;
    }

    public void setHorasFin(List<ClaseMaestra> horasFin) {
        this.horasFin = horasFin;
    }

    public void reset(){
        dia = horaInicio = horaFin = null;
    }
}
