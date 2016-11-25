/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Usuario.
 *
 * @author bpalacios
 */
@Entity
@Table(name = "USUARIO", catalog = "", schema = "DBTUTORIA")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuario.findByMaterno", query = "SELECT u FROM Usuario u WHERE u.materno = :materno"),
    @NamedQuery(name = "Usuario.findByPaterno", query = "SELECT u FROM Usuario u WHERE u.paterno = :paterno"),
    @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The id usuario. */
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private String idUsuario;
    
    /** The clave. */
    @Column(name = "CLAVE")
    private String clave;
    
    /** The nombres. */
    @Column(name = "NOMBRES")
    private String nombres;
    
    /** The materno. */
    @Column(name = "MATERNO")
    private String materno;
    
    /** The paterno. */
    @Column(name = "PATERNO")
    private String paterno;
    
    /** The correo. */
    @Column(name = "CORREO")
    private String correo;
    
    /** The direccion. */
    @Column(name = "DIRECCION")
    private String direccion;
    
    /** The telefono. */
    @Column(name = "TELEFONO")
    private String telefono;
    
    /** The estado. */
    @Column(name = "ESTADO")
    private Character estado;
     
    /**
     * Instantiates a new usuario.
     */
    public Usuario() {
    }

    /**
     * Instantiates a new usuario.
     *
     * @param idUsuario the id usuario
     */
    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    /**
     * Gets the clave.
     *
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Sets the clave.
     *
     * @param clave the new clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Gets the nombres.
     *
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets the nombres.
     *
     * @param nombres the new nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Gets the materno.
     *
     * @return the materno
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * Sets the materno.
     *
     * @param materno the new materno
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * Gets the paterno.
     *
     * @return the paterno
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * Sets the paterno.
     *
     * @param paterno the new paterno
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * Gets the correo.
     *
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets the correo.
     *
     * @param correo the new correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Gets the direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the direccion.
     *
     * @param direccion the new direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Gets the telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono.
     *
     * @param telefono the new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the estado.
     *
     * @return the estado
     */
    public Character getEstado() {
        return estado;
    }

    /**
     * Sets the estado.
     *
     * @param estado the new estado
     */
    public void setEstado(Character estado) {
        this.estado = estado;
    }

   

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
        return "pe.unmsm.ceups.dao.dominio.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
