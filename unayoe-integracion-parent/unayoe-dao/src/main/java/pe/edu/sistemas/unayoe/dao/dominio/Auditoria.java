/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Auditoria.
 *
 * @author bpalacios
 */
@Entity
@Table(name = "AUDITORIA", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a"),
    @NamedQuery(name = "Auditoria.findByIdRol", query = "SELECT a FROM Auditoria a WHERE a.auditoriaPK.idRol = :idRol"),
    @NamedQuery(name = "Auditoria.findByIdUsuario", query = "SELECT a FROM Auditoria a WHERE a.auditoriaPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "Auditoria.findByIpMaquina", query = "SELECT a FROM Auditoria a WHERE a.ipMaquina = :ipMaquina"),
    @NamedQuery(name = "Auditoria.findByHora", query = "SELECT a FROM Auditoria a WHERE a.hora = :hora"),
    @NamedQuery(name = "Auditoria.findByFecha", query = "SELECT a FROM Auditoria a WHERE a.fecha = :fecha")})
public class Auditoria implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The auditoria PK. */
    @EmbeddedId
    protected AuditoriaPK auditoriaPK;
    
    /** The ip maquina. */
    @Column(name = "IP_MAQUINA")
    private String ipMaquina;
    
    /** The hora. */
    @Column(name = "HORA")
    private String hora;
    
    /** The fecha. */
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    /**
     * Instantiates a new auditoria.
     */
    public Auditoria() {
    }

    /**
     * Instantiates a new auditoria.
     *
     * @param auditoriaPK the auditoria PK
     */
    public Auditoria(AuditoriaPK auditoriaPK) {
        this.auditoriaPK = auditoriaPK;
    }

    /**
     * Instantiates a new auditoria.
     *
     * @param idRol the id rol
     * @param idUsuario the id usuario
     */
    public Auditoria(String idRol, String idUsuario) {
        this.auditoriaPK = new AuditoriaPK(idRol, idUsuario);
    }

    /**
     * Gets the auditoria PK.
     *
     * @return the auditoria PK
     */
    public AuditoriaPK getAuditoriaPK() {
        return auditoriaPK;
    }

    /**
     * Sets the auditoria PK.
     *
     * @param auditoriaPK the new auditoria PK
     */
    public void setAuditoriaPK(AuditoriaPK auditoriaPK) {
        this.auditoriaPK = auditoriaPK;
    }

    /**
     * Gets the ip maquina.
     *
     * @return the ip maquina
     */
    public String getIpMaquina() {
        return ipMaquina;
    }

    /**
     * Sets the ip maquina.
     *
     * @param ipMaquina the new ip maquina
     */
    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    /**
     * Gets the hora.
     *
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * Sets the hora.
     *
     * @param hora the new hora
     */
    public void setHora(String hora) {
        this.hora = hora;
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditoriaPK != null ? auditoriaPK.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.auditoriaPK == null && other.auditoriaPK != null) || (this.auditoriaPK != null && !this.auditoriaPK.equals(other.auditoriaPK))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "pe.edu.sistemas.unayoe.dao.dominio.Auditoria[ auditoriaPK=" + auditoriaPK + " ]";
    }
    
}
