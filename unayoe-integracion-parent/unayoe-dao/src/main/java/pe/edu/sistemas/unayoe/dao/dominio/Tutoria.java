/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bpalacios
 */
@Entity
@Table(name = "TUTORIA", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutoria.findAll", query = "SELECT t FROM Tutoria t"),
    @NamedQuery(name = "Tutoria.findByTAnio", query = "SELECT t FROM Tutoria t WHERE t.tutoriaPK.tAnio = :tAnio"),
    @NamedQuery(name = "Tutoria.findByTPeriodo", query = "SELECT t FROM Tutoria t WHERE t.tutoriaPK.tPeriodo = :tPeriodo"),
    @NamedQuery(name = "Tutoria.findByTCodigo", query = "SELECT t FROM Tutoria t WHERE t.tutoriaPK.tCodigo = :tCodigo")})
public class Tutoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TutoriaPK tutoriaPK;
    @JoinColumn(name = "A_CODIGO", referencedColumnName = "A_CODIGO")
    @ManyToOne
    private Alumno aCodigo;
    @JoinColumn(name = "P_CODIGO", referencedColumnName = "P_CODIGO")
    @ManyToOne
    private Profesor pCodigo;

    public Tutoria() {
    }

    public Tutoria(TutoriaPK tutoriaPK) {
        this.tutoriaPK = tutoriaPK;
    }

    public Tutoria(BigInteger tAnio, BigInteger tPeriodo, String tCodigo) {
        this.tutoriaPK = new TutoriaPK(tAnio, tPeriodo, tCodigo);
    }

    public TutoriaPK getTutoriaPK() {
        return tutoriaPK;
    }

    public void setTutoriaPK(TutoriaPK tutoriaPK) {
        this.tutoriaPK = tutoriaPK;
    }

    public Alumno getACodigo() {
        return aCodigo;
    }

    public void setACodigo(Alumno aCodigo) {
        this.aCodigo = aCodigo;
    }

    public Profesor getPCodigo() {
        return pCodigo;
    }

    public void setPCodigo(Profesor pCodigo) {
        this.pCodigo = pCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutoriaPK != null ? tutoriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutoria)) {
            return false;
        }
        Tutoria other = (Tutoria) object;
        if ((this.tutoriaPK == null && other.tutoriaPK != null) || (this.tutoriaPK != null && !this.tutoriaPK.equals(other.tutoriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBTUTORIA.Tutoria[ tutoriaPK=" + tutoriaPK + " ]";
    }
    
}
