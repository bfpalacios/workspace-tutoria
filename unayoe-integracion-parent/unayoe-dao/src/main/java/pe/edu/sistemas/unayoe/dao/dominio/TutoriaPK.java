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
import javax.persistence.Embeddable;

// TODO: Auto-generated Javadoc
/**
 * The Class TutoriaPK.
 *
 * @author bpalacios
 */
@Embeddable
public class TutoriaPK implements Serializable {
    
    /** The t anio. */
    @Basic(optional = false)
    @Column(name = "T_ANIO")
    private BigInteger tAnio;
    
    /** The t periodo. */
    @Basic(optional = false)
    @Column(name = "T_PERIODO")
    private BigInteger tPeriodo;
    
    /** The t codigo. */
    @Basic(optional = false)
    @Column(name = "T_CODIGO")
    private String tCodigo;

    /**
     * Instantiates a new tutoria PK.
     */
    public TutoriaPK() {
    }

    /**
     * Instantiates a new tutoria PK.
     *
     * @param tAnio the t anio
     * @param tPeriodo the t periodo
     * @param tCodigo the t codigo
     */
    public TutoriaPK(BigInteger tAnio, BigInteger tPeriodo, String tCodigo) {
        this.tAnio = tAnio;
        this.tPeriodo = tPeriodo;
        this.tCodigo = tCodigo;
    }

    /**
     * Gets the t anio.
     *
     * @return the t anio
     */
    public BigInteger getTAnio() {
        return tAnio;
    }

    /**
     * Sets the t anio.
     *
     * @param tAnio the new t anio
     */
    public void setTAnio(BigInteger tAnio) {
        this.tAnio = tAnio;
    }

    /**
     * Gets the t periodo.
     *
     * @return the t periodo
     */
    public BigInteger getTPeriodo() {
        return tPeriodo;
    }

    /**
     * Sets the t periodo.
     *
     * @param tPeriodo the new t periodo
     */
    public void setTPeriodo(BigInteger tPeriodo) {
        this.tPeriodo = tPeriodo;
    }

    /**
     * Gets the t codigo.
     *
     * @return the t codigo
     */
    public String getTCodigo() {
        return tCodigo;
    }

    /**
     * Sets the t codigo.
     *
     * @param tCodigo the new t codigo
     */
    public void setTCodigo(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tAnio != null ? tAnio.hashCode() : 0);
        hash += (tPeriodo != null ? tPeriodo.hashCode() : 0);
        hash += (tCodigo != null ? tCodigo.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutoriaPK)) {
            return false;
        }
        TutoriaPK other = (TutoriaPK) object;
        if ((this.tAnio == null && other.tAnio != null) || (this.tAnio != null && !this.tAnio.equals(other.tAnio))) {
            return false;
        }
        if ((this.tPeriodo == null && other.tPeriodo != null) || (this.tPeriodo != null && !this.tPeriodo.equals(other.tPeriodo))) {
            return false;
        }
        if ((this.tCodigo == null && other.tCodigo != null) || (this.tCodigo != null && !this.tCodigo.equals(other.tCodigo))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBTUTORIA.TutoriaPK[ tAnio=" + tAnio + ", tPeriodo=" + tPeriodo + ", tCodigo=" + tCodigo + " ]";
    }
    
}
