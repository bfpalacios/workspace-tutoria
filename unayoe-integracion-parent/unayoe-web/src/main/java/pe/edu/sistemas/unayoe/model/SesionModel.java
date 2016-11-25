package pe.edu.sistemas.unayoe.model;

import org.springframework.stereotype.Component;

import javax.faces.bean.RequestScoped;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 09/11/2015.
 */
@Component
@RequestScoped
public class SesionModel {
    
    /** The numero. */
    private int numero;
    
    /** The tipo. */
    private int tipo; // Presencial o virtual
    
    /** The descripcion. */
    private String descripcion;
    
    /** The fecha. */
    private Date fecha;

    /**
     * Instantiates a new sesion model.
     */
    public SesionModel(){
        this.tipo = 2;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the new numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Gets the tipo.
     *
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Sets the tipo.
     *
     * @param tipo the new tipo
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets the descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the descripcion.
     *
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the fecha.
     *
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Sets the fecha.
     *
     * @param fecha the new fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Reset.
     */
    public void reset(){
        descripcion = null;
        fecha = null;
    }
}
