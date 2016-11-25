/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

// TODO: Auto-generated Javadoc
/**
 * The Class AuditoriaPK.
 *
 * @author bpalacios
 */
@Embeddable
public class AuditoriaPK implements Serializable {
    
    /** The id rol. */
    @Basic(optional = false)
    @Column(name = "ID_ROL")
    private String idRol;
    
    /** The id usuario. */
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private String idUsuario;

    /**
     * Instantiates a new auditoria PK.
     */
    public AuditoriaPK() {
    }

    /**
     * Instantiates a new auditoria PK.
     *
     * @param idRol the id rol
     * @param idUsuario the id usuario
     */
    public AuditoriaPK(String idRol, String idUsuario) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    /**
     * Gets the id rol.
     *
     * @return the id rol
     */
    public String getIdRol() {
        return idRol;
    }

    /**
     * Sets the id rol.
     *
     * @param idRol the new id rol
     */
    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

    /**
     * Gets the id usuario.
     *
     * @return the id usuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Sets the id usuario.
     *
     * @param idUsuario the new id usuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaPK)) {
            return false;
        }
        AuditoriaPK other = (AuditoriaPK) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "pe.edu.sistemas.unayoe.dao.dominio.AuditoriaPK[ idRol=" + idRol + ", idUsuario=" + idUsuario + " ]";
    }
    
}
