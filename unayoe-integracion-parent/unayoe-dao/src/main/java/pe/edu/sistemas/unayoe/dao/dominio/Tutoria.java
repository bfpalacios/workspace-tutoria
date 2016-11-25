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

// TODO: Auto-generated Javadoc
/**
 * The Class Tutoria.
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
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The tutoria PK. */
    @EmbeddedId
    protected TutoriaPK tutoriaPK;
    
    /** The a codigo. */
    @JoinColumn(name = "A_CODIGO", referencedColumnName = "A_CODIGO")
    @ManyToOne
    private Alumno aCodigo;
    
    /** The p codigo. */
    @JoinColumn(name = "P_CODIGO", referencedColumnName = "P_CODIGO")
    @ManyToOne
    private Profesor pCodigo;

    /**
     * Instantiates a new tutoria.
     */
    public Tutoria() {
    }

    /**
     * Instantiates a new tutoria.
     *
     * @param tutoriaPK the tutoria PK
     */
    public Tutoria(TutoriaPK tutoriaPK) {
        this.tutoriaPK = tutoriaPK;
    }

    /**
     * Instantiates a new tutoria.
     *
     * @param tAnio the t anio
     * @param tPeriodo the t periodo
     * @param tCodigo the t codigo
     */
    public Tutoria(BigInteger tAnio, BigInteger tPeriodo, String tCodigo) {
        this.tutoriaPK = new TutoriaPK(tAnio, tPeriodo, tCodigo);
    }

    /**
     * Gets the tutoria PK.
     *
     * @return the tutoria PK
     */
    public TutoriaPK getTutoriaPK() {
        return tutoriaPK;
    }

    /**
     * Sets the tutoria PK.
     *
     * @param tutoriaPK the new tutoria PK
     */
    public void setTutoriaPK(TutoriaPK tutoriaPK) {
        this.tutoriaPK = tutoriaPK;
    }

    /**
     * Gets the a codigo.
     *
     * @return the a codigo
     */
    public Alumno getACodigo() {
        return aCodigo;
    }

    /**
     * Sets the a codigo.
     *
     * @param aCodigo the new a codigo
     */
    public void setACodigo(Alumno aCodigo) {
        this.aCodigo = aCodigo;
    }

    /**
     * Gets the p codigo.
     *
     * @return the p codigo
     */
    public Profesor getPCodigo() {
        return pCodigo;
    }

    /**
     * Sets the p codigo.
     *
     * @param pCodigo the new p codigo
     */
    public void setPCodigo(Profesor pCodigo) {
        this.pCodigo = pCodigo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tutoriaPK != null ? tutoriaPK.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBTUTORIA.Tutoria[ tutoriaPK=" + tutoriaPK + " ]";
    }
    
}
