package pe.edu.sistemas.unayoe.model;

import org.springframework.stereotype.Component;

import javax.faces.bean.RequestScoped;
import java.util.Date;

/**
 * Created by Alex on 09/11/2015
 */
@Component
@RequestScoped
public class SesionModel {
    private int numero;
    private int tipo; // Presencial o virtual
    private String descripcion;
    private Date fecha;

    public SesionModel(){
        this.tipo = 2;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void reset(){
        descripcion = null;
        fecha = null;
    }
}
