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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioRol.
 *
 * @author bpalacios
 */
@Entity
@Table(name = "USUARIO_ROL", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u"),
    @NamedQuery(name = "UsuarioRol.findByIdRol", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idRol = :idRol"),
    @NamedQuery(name = "UsuarioRol.findByRole", query = "SELECT u FROM UsuarioRol u WHERE u.role = :role"),
    @NamedQuery(name = "UsuarioRol.findByIdUsuario", query = "SELECT u FROM UsuarioRol u WHERE u.usuarioRolPK.idUsuario = :idUsuario")})
public class UsuarioRol implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The usuario rol PK. */
    @EmbeddedId
    protected UsuarioRolPK usuarioRolPK;
    
    /** The role. */
    @Basic(optional = false)
    @Column(name = "ROLE")
    private String role;

    /**
     * Instantiates a new usuario rol.
     */
    public UsuarioRol() {
    }

    /**
     * Instantiates a new usuario rol.
     *
     * @param usuarioRolPK the usuario rol PK
     */
    public UsuarioRol(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    /**
     * Instantiates a new usuario rol.
     *
     * @param usuarioRolPK the usuario rol PK
     * @param role the role
     */
    public UsuarioRol(UsuarioRolPK usuarioRolPK, String role) {
        this.usuarioRolPK = usuarioRolPK;
        this.role = role;
    }

    /**
     * Instantiates a new usuario rol.
     *
     * @param idRol the id rol
     * @param idUsuario the id usuario
     */
    public UsuarioRol(BigInteger idRol, String idUsuario) {
        this.usuarioRolPK = new UsuarioRolPK(idRol, idUsuario);
    }

    /**
     * Gets the usuario rol PK.
     *
     * @return the usuario rol PK
     */
    public UsuarioRolPK getUsuarioRolPK() {
        return usuarioRolPK;
    }

    /**
     * Sets the usuario rol PK.
     *
     * @param usuarioRolPK the new usuario rol PK
     */
    public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.usuarioRolPK == null && other.usuarioRolPK != null) || (this.usuarioRolPK != null && !this.usuarioRolPK.equals(other.usuarioRolPK))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "pe.edu.sistemas.unayoe.dao.dominio.UsuarioRol[ usuarioRolPK=" + usuarioRolPK + " ]";
    }
    
}
