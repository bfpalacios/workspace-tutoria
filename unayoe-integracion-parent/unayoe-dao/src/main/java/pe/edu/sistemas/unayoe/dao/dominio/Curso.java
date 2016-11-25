/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Curso.
 *
 * @author bpalacios
 */
@Entity
@Table(name = "CURSO", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCCodigo", query = "SELECT c FROM Curso c WHERE c.cCodigo = :cCodigo"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Curso.findByCreditos", query = "SELECT c FROM Curso c WHERE c.creditos = :creditos")})
public class Curso implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The c codigo. */
    @Id
    @Basic(optional = false)
    @Column(name = "C_CODIGO")
    private String cCodigo;
    
    /** The nombre. */
    @Column(name = "NOMBRE")
    private String nombre;
    
    /** The creditos. */
    @Basic(optional = false)
    @Column(name = "CREDITOS")
    private BigInteger creditos;

    /**
     * Instantiates a new curso.
     */
    public Curso() {
    }

    /**
     * Instantiates a new curso.
     *
     * @param cCodigo the c codigo
     */
    public Curso(String cCodigo) {
        this.cCodigo = cCodigo;
    }

    /**
     * Instantiates a new curso.
     *
     * @param cCodigo the c codigo
     * @param creditos the creditos
     */
    public Curso(String cCodigo, BigInteger creditos) {
        this.cCodigo = cCodigo;
        this.creditos = creditos;
    }

    /**
     * Gets the c codigo.
     *
     * @return the c codigo
     */
    public String getCCodigo() {
        return cCodigo;
    }

    /**
     * Sets the c codigo.
     *
     * @param cCodigo the new c codigo
     */
    public void setCCodigo(String cCodigo) {
        this.cCodigo = cCodigo;
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
     * Gets the creditos.
     *
     * @return the creditos
     */
    public BigInteger getCreditos() {
        return creditos;
    }

    /**
     * Sets the creditos.
     *
     * @param creditos the new creditos
     */
    public void setCreditos(BigInteger creditos) {
        this.creditos = creditos;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cCodigo != null ? cCodigo.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.cCodigo == null && other.cCodigo != null) || (this.cCodigo != null && !this.cCodigo.equals(other.cCodigo))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBTUTORIA.Curso[ cCodigo=" + cCodigo + " ]";
    }
    
}
