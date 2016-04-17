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

/**
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
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioRolPK usuarioRolPK;
    @Basic(optional = false)
    @Column(name = "ROLE")
    private String role;

    public UsuarioRol() {
    }

    public UsuarioRol(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public UsuarioRol(UsuarioRolPK usuarioRolPK, String role) {
        this.usuarioRolPK = usuarioRolPK;
        this.role = role;
    }

    public UsuarioRol(BigInteger idRol, String idUsuario) {
        this.usuarioRolPK = new UsuarioRolPK(idRol, idUsuario);
    }

    public UsuarioRolPK getUsuarioRolPK() {
        return usuarioRolPK;
    }

    public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "pe.edu.sistemas.unayoe.dao.dominio.UsuarioRol[ usuarioRolPK=" + usuarioRolPK + " ]";
    }
    
}
