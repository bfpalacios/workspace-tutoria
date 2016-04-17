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

/**
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
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AuditoriaPK auditoriaPK;
    @Column(name = "IP_MAQUINA")
    private String ipMaquina;
    @Column(name = "HORA")
    private String hora;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Auditoria() {
    }

    public Auditoria(AuditoriaPK auditoriaPK) {
        this.auditoriaPK = auditoriaPK;
    }

    public Auditoria(String idRol, String idUsuario) {
        this.auditoriaPK = new AuditoriaPK(idRol, idUsuario);
    }

    public AuditoriaPK getAuditoriaPK() {
        return auditoriaPK;
    }

    public void setAuditoriaPK(AuditoriaPK auditoriaPK) {
        this.auditoriaPK = auditoriaPK;
    }

    public String getIpMaquina() {
        return ipMaquina;
    }

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auditoriaPK != null ? auditoriaPK.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "pe.edu.sistemas.unayoe.dao.dominio.Auditoria[ auditoriaPK=" + auditoriaPK + " ]";
    }
    
}
