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

/**
 *
 * @author bpalacios
 */
@Embeddable
public class TutoriaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "T_ANIO")
    private BigInteger tAnio;
    @Basic(optional = false)
    @Column(name = "T_PERIODO")
    private BigInteger tPeriodo;
    @Basic(optional = false)
    @Column(name = "T_CODIGO")
    private String tCodigo;

    public TutoriaPK() {
    }

    public TutoriaPK(BigInteger tAnio, BigInteger tPeriodo, String tCodigo) {
        this.tAnio = tAnio;
        this.tPeriodo = tPeriodo;
        this.tCodigo = tCodigo;
    }

    public BigInteger getTAnio() {
        return tAnio;
    }

    public void setTAnio(BigInteger tAnio) {
        this.tAnio = tAnio;
    }

    public BigInteger getTPeriodo() {
        return tPeriodo;
    }

    public void setTPeriodo(BigInteger tPeriodo) {
        this.tPeriodo = tPeriodo;
    }

    public String getTCodigo() {
        return tCodigo;
    }

    public void setTCodigo(String tCodigo) {
        this.tCodigo = tCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tAnio != null ? tAnio.hashCode() : 0);
        hash += (tPeriodo != null ? tPeriodo.hashCode() : 0);
        hash += (tCodigo != null ? tCodigo.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "DBTUTORIA.TutoriaPK[ tAnio=" + tAnio + ", tPeriodo=" + tPeriodo + ", tCodigo=" + tCodigo + " ]";
    }
    
}
